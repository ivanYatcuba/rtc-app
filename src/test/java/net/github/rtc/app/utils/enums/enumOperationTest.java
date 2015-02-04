package net.github.rtc.app.utils.enums;

import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.Assert;
import net.github.rtc.app.controller.admin.MenuItems;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.user.RoleType;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class enumOperationTest {

    @Test
    public void findAllTest(){
        List<String> res = EnumOperation.findAllName(NewsStatus.class);
        Assert.assertEquals(NewsStatus.values().length, res.size());
        for (final NewsStatus status : NewsStatus.values()) {
            assertTrue(res.contains(status.name()));
        }
    }

    @Test
    public void roleGetTypeByStringTest(){
        assertEquals(RoleType.ROLE_ADMIN, EnumOperation.getTypeByString(RoleType.class, "ROLE_ADMIN"));
    }

    @Ignore
    @Test
    public void testLogic() {
        final List<String> allName = EnumOperation.findAllName(MenuItems.class);
        final List<String> allString = EnumOperation.findAllValue(MenuItems.class);
        final List<MenuItems> allType = EnumOperation.findAll(MenuItems.class);
    }


}
