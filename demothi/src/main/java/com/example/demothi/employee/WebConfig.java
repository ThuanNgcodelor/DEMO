package com.example.demothi.employee;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new org.springframework.format.Formatter<LocalDate>() {
            private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public LocalDate parse(String text, java.util.Locale locale) {
                return LocalDate.parse(text, DF);
            }
            @Override
            public String print(LocalDate object, java.util.Locale locale) {
                return object.format(DF);
            }
        });
    }
}