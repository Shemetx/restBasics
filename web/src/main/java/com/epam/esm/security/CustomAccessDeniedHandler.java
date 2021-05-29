package com.epam.esm.security;

import com.epam.esm.domain.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Return json response when user try to get page without corresponding authorities
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.addHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Error error = new Error(403, "You have no authority to this resource");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.flush();
    }
}
