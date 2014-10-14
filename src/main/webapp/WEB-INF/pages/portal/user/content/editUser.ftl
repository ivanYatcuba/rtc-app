<#import "../../../fieldMacro.ftl" as formMacro />
<#import "../../../rtcmacroses.ftl" as rtcmacroses />

<@formMacro.FormMacro "user" "/user/update" "user.editTitle" "${validationRules}">
    <#include "userForm.ftl" />
    <@formMacro.SubmitMacro "Save" "Cancel" "/user/view/"/>
</@formMacro.FormMacro>
