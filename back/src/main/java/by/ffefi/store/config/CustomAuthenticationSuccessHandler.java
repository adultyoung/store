package by.ffefi.store.config;

import by.ffefi.store.domain.User;
import by.ffefi.store.dto.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        if (user.getStatus().contains(Status.INACTIVE)) {
            authentication.setAuthenticated(false);
            response.sendError(403, "This account is blocked.");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("https://doghouse.by");

    }
}
