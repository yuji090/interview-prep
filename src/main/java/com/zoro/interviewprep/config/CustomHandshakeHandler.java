package com.zoro.interviewprep.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        Principal principal = (Principal) attributes.get("jwtPrincipal");
        System.out.println("👤 [CustomHandshakeHandler] Returning Principal: " +
                (principal != null ? principal.getName() : "null"));
        return principal;
    }
}
