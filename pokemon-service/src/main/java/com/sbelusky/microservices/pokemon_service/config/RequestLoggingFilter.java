package com.sbelusky.microservices.pokemon_service.config;

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
 * This filter is responsible for logging incoming requests to the application.
 * It logs the HTTP method, URI, headers and body of the request.
 * The filter is disabled for requests to the Actuator endpoints and for test environments.
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

        try {
            chain.doFilter(cachedBodyHttpServletRequest, response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the request headers.
     * @param request the HTTP request
     * @return a string with the request headers
     */
    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                headers.append(header).append(": ").append(request.getHeader(header)).append("\n"));
        return headers.toString();
    }

    /**
     * Returns the request body as a string.
     * @param request the HTTP request
     * @return the request body as a string
     * @throws IOException if reading the request body fails
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        return new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns true if the application is running in test environment.
     * @return true if the application is running in test environment, false otherwise
     */
    private boolean isTestEnvironment() {
        return "test".equals(System.getProperty("spring.profiles.active"));
    }

    /**
     * A wrapper for the HTTP request that allows caching of the request body.
     */
    private static class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
        private final byte[] cachedBody;

        /**
         * Creates a new instance of the class.
         * @param request the HTTP request
         * @throws IOException if reading the request body fails
         */
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
     * A wrapper for the ServletInputStream that allows caching of the request body.
     */
    private static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream buffer;

        /**
         * Creates a new instance of the class.
         * @param cachedBody the cached request body
         */
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



