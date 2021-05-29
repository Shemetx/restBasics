package com.epam.esm.filter;

import com.epam.esm.domain.Error;
import com.epam.esm.exception.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Exception filter which catches exceptions JwtTokenFilter throws.
 */
public class ExceptionHandleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException ex) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, ex);
        }
    }

    /**
     * Sets error response.
     *
     * @param status   the status
     * @param response the response
     * @param ex       the ex
     * @throws IOException the io exception
     */
    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) throws IOException {
        response.addHeader("Content-Type", "application/json");
        response.setStatus(status.value());
        Error error = new Error(status.value(), ex.getMessage());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.flush();
    }
}
