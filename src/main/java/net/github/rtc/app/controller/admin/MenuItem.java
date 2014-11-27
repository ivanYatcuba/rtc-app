package net.github.rtc.app.controller.admin;

import org.springframework.web.bind.annotation.ModelAttribute;

//add model attribute that indicates what menu item should be chosen
public interface MenuItem {
    String MENU_ITEM = "menuItem";

    @ModelAttribute(MENU_ITEM)
    String getMenuItem();

}
