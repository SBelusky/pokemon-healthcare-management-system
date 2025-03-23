package com.sbelusky.microservices.pokemon_center_service.config;

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
 * Filter for logging HTTP requests. It captures request method, URI, headers, and body.
 * Requests to "/actuator" or in a test environment are bypassed.
 */
@Component
public class RequestLoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Logs details of incoming HTTP requests and passes them down the filter chain.
     *
     * @param request  the ServletRequest object contains the client's request
     * @param response the ServletResponse object contains the filter's response
     * @param chain    the FilterChain for invoking the next filter or resource
     * @throws IOException      if an I/O error occurs during request processing
     * @throws ServletException if the request cannot be processed
     */
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
     * Extracts headers from the request as a formatted string.
     *
     * @param request the HttpServletRequest to extract headers from
     * @return a string representation of the headers
     */
    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                headers.append(header).append(": ").append(request.getHeader(header)).append("\n"));
        return headers.toString();
    }

    /**
     * Reads the request body and returns it as a string.
     *
     * @param request the HttpServletRequest to read the body from
     * @return the request body as a string
     * @throws IOException if an I/O error occurs while reading the body
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        return new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Determines if the current environment is a test environment.
     *
     * @return true if the environment is set to "test", false otherwise
     */
    private boolean isTestEnvironment() {
        return "test".equals(System.getProperty("spring.profiles.active"));
    }

    /**
     * Wrapper class for caching the request body.
     */
    private static class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
        private final byte[] cachedBody;

        /**
         * Constructs a new CachedBodyHttpServletRequest with the specified request.
         *
         * @param request the HttpServletRequest to wrap
         * @throws IOException if an I/O error occurs while reading the request body
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
     * ServletInputStream implementation for reading a cached request body.
     */
    private static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream buffer;

        /**
         * Constructs a new CachedBodyServletInputStream with the specified cached body.
         *
         * @param cachedBody the byte array representing the cached request body
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