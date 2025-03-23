package com.sbelusky.microservices.appointment_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Logs incoming requests to the {@link org.slf4j.Logger} with name "com.sbelusky.microservices.appointment_service.config.RequestLoggingFilter".
 * <p>
 * This filter is used to log incoming requests. It logs the request method, request URI, headers and request body.
 * <p>
 * The request body is only logged if the request is not made to the "/actuator" endpoint and the application is not running in a test environment.
 * <p>
 * This filter is a Spring {@link org.springframework.stereotype.Component} and is registered in the Spring application context.
 * <p>
 * The filter is only invoked if the request is not made to the "/actuator" endpoint and the application is not running in a test environment.
 * <p>
 * The request body is read from the request and cached in a byte array. The request body is then logged to the {@link org.slf4j.Logger} with name
 * "com.sbelusky.microservices.appointment_service.config.RequestLoggingFilter".
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
     * @param request the request to get the headers from
     * @return the headers as a string
     */
    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                headers.append(header).append(": ").append(request.getHeader(header)).append("\n"));
        return headers.toString();
    }

    /**
     * Returns the request body of the given request as a string.
     *
     * @param request the request to get the request body from
     * @return the request body as a string
     * @throws IOException if there is an error reading the request body
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        return new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns true if the application is running in a test environment.
     *
     * @return true if the application is running in a test environment, false otherwise
     */
    private boolean isTestEnvironment() {
        return "test".equals(System.getProperty("spring.profiles.active"));
    }

    /**
     * A wrapper around the {@link HttpServletRequest} that caches the request body.
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
     * A {@link ServletInputStream} that returns the cached request body.
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



