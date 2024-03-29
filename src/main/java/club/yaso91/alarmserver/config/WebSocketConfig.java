/**
 * projectName: alarmserver
 * fileName: WebSocketConfig.java
 * packageName: club.yaso91.alarmserver.config
 * date: 2019-11-11 17:10
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSocketConfig
 * @packageName: club.yaso91.alarmserver.config
 * @description: webSocket配置.
 * @data: 2019-11-11 17:10
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

    }
}
