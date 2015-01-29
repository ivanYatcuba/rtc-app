<#import "layout/layout.ftl" as layout/>
<#import "../fieldMacro.ftl" as formMacro />

<@layout.layout>
<@formMacro.rtcForm "user" "/register/save" "user.registrationTitle" "${validationRules}">
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.status" />
    <#include "../portal/user/profile/userForm.ftl" />
    <@formMacro.rtcSubmit "Register" "Cancel" "/"/>
</@formMacro.rtcForm>
</@layout.layout>