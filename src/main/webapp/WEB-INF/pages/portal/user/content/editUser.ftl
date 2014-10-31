<#import "../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "user" "/user/update" "user.editTitle" "${validationRules}">
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/user/view/"/>
</@formMacro.rtcForm>
