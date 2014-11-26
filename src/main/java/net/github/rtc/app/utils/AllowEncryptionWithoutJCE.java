package net.github.rtc.app.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component("allowEncryptionWithoutJCE")
public class AllowEncryptionWithoutJCE implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            final Field field = Class.forName("javax.crypto.JceSecurity").
                    getDeclaredField("isRestricted");
            field.setAccessible(true);
            field.set(null, java.lang.Boolean.FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
