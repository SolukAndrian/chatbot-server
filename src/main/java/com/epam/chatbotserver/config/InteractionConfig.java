package com.epam.chatbotserver.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:interaction/convenience_request.properties","classpath:interaction/convenience_response.properties"})
public class InteractionConfig {
}
