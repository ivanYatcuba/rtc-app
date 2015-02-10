package net.github.rtc.app.service.generic;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodeGenerationServiceImpl implements CodeGenerationService {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
