package com.zoro.interviewprep.config;

import com.zoro.interviewprep.auth.JwtHandshakeInterceptor;
import com.zoro.interviewprep.auth.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.*;

import java.security.Principal;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtUtil jwtUtil;

    public WebSocketConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // 🔁 Clients subscribe here
        config.setApplicationDestinationPrefixes("/app"); // 💬 Client sends here
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .addInterceptors(new JwtHandshakeInterceptor(jwtUtil))
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOrigins("http://localhost:5173", "http://localhost:5173", "http://127.0.0.1:5500") ;// ✅ Dev frontend origins
                //.withSockJS(); // ✅ SockJS fallback needed for client
        System.out.println("🔌 WebSocket '/ws' with SockJS registered.");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                Principal principal = (Principal) accessor.getSessionAttributes().get("jwtPrincipal");
                if (principal != null) {
                    accessor.setUser(principal);
                    System.out.println("🔗 Injected Principal: " + principal.getName());
                } else {
                    System.out.println("🚫 No Principal found in WebSocket session.");
                }
                return message;
            }
        });
    }
}
