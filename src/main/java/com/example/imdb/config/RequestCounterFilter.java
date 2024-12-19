package com.example.imdb.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.io.IOException;

public class RequestCounterFilter implements Filter {
    @Getter
    private static long requestCount = 0;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (request instanceof HttpServletRequest) {
            synchronized (RequestCounterFilter.class) {
                requestCount++;
            }
        }
        chain.doFilter(request, response);
    }


}
