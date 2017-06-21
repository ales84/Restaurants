package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.util.ValidationUtil;
import org.alesapps.votingsystem.util.exception.ErrorInfo;
import org.alesapps.votingsystem.web.json.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anatoliy Kozhayev on 21.06.2017.
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.writeValue(
                new ErrorInfo(request.getRequestURL(), ValidationUtil.getRootCause(authException))));
        writer.flush();
        writer.close();
    }
}
