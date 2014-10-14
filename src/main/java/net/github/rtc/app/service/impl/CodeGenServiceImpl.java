package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.CodeGenService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by denis on 13.10.14.
 */
@Service("CodeGenerator")
public class CodeGenServiceImpl implements CodeGenService {
    public String generateCode() {
        return UUID.randomUUID().toString();
    }
}
