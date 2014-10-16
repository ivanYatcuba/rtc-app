<#import "../../../fieldMacro.ftl" as formMacro />
<#import "../../../rtcmacroses.ftl" as rtcmacroses />

<@formMacro.rtcForm "user" "/user/save" "user.registrationTitle" "${validationRules}">
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Register" "Cancel" "/"/>
</@formMacro.rtcForm>
