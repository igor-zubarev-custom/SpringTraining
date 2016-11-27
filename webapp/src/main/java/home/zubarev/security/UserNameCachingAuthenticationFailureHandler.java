package home.zubarev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor Zubarev on 27.11.2016.
 */

public class UserNameCachingAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /*@Autowired
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;*/

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
//        String usernameParameter = usernamePasswordAuthenticationFilter.getUsernameParameter();
        String lastUsername = request.getParameter("username");
        System.out.println(lastUsername);
        request.getSession().setAttribute("lastUsername", lastUsername);
    }
}
