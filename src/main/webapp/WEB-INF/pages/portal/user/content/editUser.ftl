<#import "../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "user" "/user/update" "user.editTitle" "${validationRules}">
    <@spring.formHiddenInput "user.code" />
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.registerDate" />
    <@spring.formHiddenInput "user.status" />
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/user/view/"/>
</@formMacro.rtcForm>
