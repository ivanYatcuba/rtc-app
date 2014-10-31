<#import "../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "user" "/user/save" "user.registrationTitle" "${validationRules}">
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Register" "Cancel" "/"/>
</@formMacro.rtcForm>
