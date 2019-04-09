package com.pcg.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CrossDomainFilter implements Filter {

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Authorization-Token");
        res.setHeader("Access-Control-Max-Age", "3600");

        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("CrossDomainFilter - destroy");
    }

    @Override
    public void init( FilterConfig filterConfig) {
    }
}