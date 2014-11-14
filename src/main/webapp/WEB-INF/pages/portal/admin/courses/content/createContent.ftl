<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "courseForm" "/admin/course/save" "create.message" "${validationRules}">
    <#include "courseForm.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/search"/>
</@formMacro.rtcForm>


