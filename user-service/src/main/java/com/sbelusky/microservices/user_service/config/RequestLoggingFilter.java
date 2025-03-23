package com.sbelusky.microservices.user_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * This filter logs incoming requests. It logs the HTTP method, the request URI, headers and the body. It does not log requests to the actuator endpoints
 * and it does not log requests in the test environment.
 */
@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getRequestURI().startsWith("/actuator") || isTestEnvironment()) {
            chain.doFilter(request, response);
            return;
        }

        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(httpRequest);

        log.info("Incoming request: [{} {}]", cachedBodyHttpServletRequest.getMethod(), cachedBodyHttpServletRequest.getRequestURI());
        log.info("Headers: {}", getHeaders(cachedBodyHttpServletRequest));

        String body = getRequestBody(cachedBodyHttpServletRequest);

        if (!body.isEmpty()) {
            log.info("Request Body: {}", body);
        }

        chain.doFilter(cachedBodyHttpServletRequest, response);
    }

    /**
     * Returns the headers of the given request as a string.
     *
     * @param request the request
     * @return the headers of the request as a string
     */
    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                headers.append(header).append(": ").append(request.getHeader(header)).append("\n"));
        return headers.toString();
    }

    /**
     * Returns the body of the given request as a string.
     *
     * @param request the request
     * @return the body of the request as a string
     * @throws IOException if an I/O error occurs
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        return new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Checks if the application is running in a test environment.
     *
     * @return true if the application is running in a test environment, false otherwise
     */
    private boolean isTestEnvironment() {
        return "test".equals(System.getProperty("spring.profiles.active"));
    }

    /**
     * A wrapper around the HttpServletRequest that caches the body of the request.
     */
    private static class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
        private final byte[] cachedBody;

        public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
            super(request);
            this.cachedBody = request.getInputStream().readAllBytes();
        }

        @Override
        public ServletInputStream getInputStream() {
            return new CachedBodyServletInputStream(this.cachedBody);
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(this.getInputStream()));
        }
    }

    /**
     * A wrapper around the ServletInputStream that returns the cached body of the request.
     */
    private static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream buffer;

        public CachedBodyServletInputStream(byte[] cachedBody) {
            this.buffer = new ByteArrayInputStream(cachedBody);
        }

        @Override
        public int read() {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            // No implementation needed
        }
    }
}


