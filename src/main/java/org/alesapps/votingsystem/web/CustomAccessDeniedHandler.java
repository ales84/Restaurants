package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.ErrorInfo;
import org.alesapps.votingsystem.web.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anatoliy Kozhayev on 20.06.2017.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warn("User: " + auth.getName() + "attempted to access the protected URL: " + request.getRequestURI());
        }
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.writeValue(
                new ErrorInfo(request.getRequestURL(), ValidationUtil.getRootCause(accessDeniedException))));
        writer.flush();
        writer.close();
    }
}
