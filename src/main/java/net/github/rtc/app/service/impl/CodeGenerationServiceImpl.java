package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.CodeGenerationService;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Created by denis on 13.10.14.
 */
@Component("CodeGenerator")
public class CodeGenerationServiceImpl implements CodeGenerationService {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
