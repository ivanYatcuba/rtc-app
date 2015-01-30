package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.CodeGenerationService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("CodeGenerator")
public class CodeGenerationServiceImpl implements CodeGenerationService {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
