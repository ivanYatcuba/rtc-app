<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "user" "/admin/user/save" "user.createUser" "${validationRules}">
    <#include "createUser.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/user/viewAll"/>
</@formMacro.rtcForm>