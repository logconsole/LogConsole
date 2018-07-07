package info.logconsole.admin.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author xiahongjian
 * @time 2018-06-26 21:55:17
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        System.out.println(request.getRequestURL().toString());
        if (isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } else {
            response.sendRedirect("/login.html");
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
