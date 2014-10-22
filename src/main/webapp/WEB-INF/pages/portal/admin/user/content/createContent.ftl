<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "userName" "/admin/user/save" "user.createUser" "${validationRules}" "multipart/form-data">
    <#include "createUser.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/user/viewAll"/>
</@formMacro.rtcForm>