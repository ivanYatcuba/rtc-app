<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>

<@formMacro.rtcForm "user" "/user/profile/update" "user.editTitle" "${validationRules}">
    <@spring.formHiddenInput "user.code" />
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.registerDate" />
    <@spring.formHiddenInput "user.status" />
    <@spring.formHiddenInput "user.photo" />
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/user/profile/"/>
</@formMacro.rtcForm>

</@layout.layout>