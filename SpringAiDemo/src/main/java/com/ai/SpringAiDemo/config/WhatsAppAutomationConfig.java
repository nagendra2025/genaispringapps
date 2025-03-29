package com.ai.SpringAiDemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.SpringAiDemo.utils.WhatsAppAutomation;

@Configuration
public class WhatsAppAutomationConfig {

    @Bean
    public WhatsAppAutomation whatsAppAutomation() {
        return new WhatsAppAutomation();
    }
}

