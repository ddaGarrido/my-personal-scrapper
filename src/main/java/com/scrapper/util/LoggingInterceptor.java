package com.scrapper.util;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.scrapper.models.RequestLog;
import com.scrapper.service.OperationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private OperationService operationService; // Supondo que você tenha um serviço para gerenciar Operations

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestLog requestLog = new RequestLog();
        requestLog.setUrl(request.getRequestURL().toString());
        requestLog.setHeaders(Collections.list(request.getHeaderNames()).stream()
            .collect(Collectors.toMap(h -> h, request::getHeader)));
        // requestLog.setCookies(Arrays.stream(request.getCookies())
        //     .collect(Collectors.toMap(Cookies::getName, Cookie::getValue)));
        requestLog.setType(request.getMethod());
        // Adicione o requestLog à Operation atual
        operationService.addRequest(requestLog);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Aqui você pode capturar a resposta, se necessário
    }
}
