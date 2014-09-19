package net.github.rtc.app.model.user;


/**
 * @author Vladislav Pikus
 */
public enum RoleType {
    ROLE_USER("User") ,ROLE_ADMIN("Admin"), ROLE_EXPERT("Expert");

    private final String roleViewName;

    RoleType(final String s) {roleViewName = s;}
    public String getRoleViewName() {return roleViewName;}
}
