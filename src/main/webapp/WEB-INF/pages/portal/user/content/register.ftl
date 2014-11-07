<#import "../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "user" "/register/save" "user.registrationTitle" "${validationRules}">
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.status" />
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Register" "Cancel" "/"/>
</@formMacro.rtcForm>
