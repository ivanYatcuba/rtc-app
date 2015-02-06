package net.github.rtc.app.utils.enums;

import junit.framework.Assert;
import net.github.rtc.app.model.entity.news.NewsStatus;
import net.github.rtc.app.model.entity.user.RoleType;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class EnumHelperTest {

    @Test
    public void findAllTest(){
        List<String> res = EnumHelper.getNames(NewsStatus.class);
        Assert.assertEquals(NewsStatus.values().length, res.size());
        for (final NewsStatus status : NewsStatus.values()) {
            assertTrue(res.contains(status.name()));
        }
    }

    @Test
    public void roleGetTypeByStringTest() {
        assertEquals(RoleType.ROLE_ADMIN, EnumHelper.getTypeByString(RoleType.class, "ROLE_ADMIN"));
    }
}
