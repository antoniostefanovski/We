package mk.ukim.finki.wp.laboratory1.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.laboratory1.model.User;

import java.io.IOException;

@WebFilter(filterName="auth-filter", urlPatterns = "/*",
                dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
                initParams = @WebInitParam(name="ignore-path", value="/login"))

public class LoginFilter implements Filter {

    private String ignorePath;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");

        String path = request.getServletPath();

        if(ignorePath.startsWith(path) || user != null) {

            System.out.println("WebFilter preprocessing...");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("WebFilter postprocessing...");

        } else {

            response.sendRedirect("/login");

        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
