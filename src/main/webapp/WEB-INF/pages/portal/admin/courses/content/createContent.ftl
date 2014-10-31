<#import "../../../../fieldMacro.ftl" as formMacro />
<#import "../../../../rtcmacroses.ftl" as rtcmacroses />

<@formMacro.rtcForm "courseForm" "/admin/course/save" "create.message" "${validationRules}">
    <#include "courseForm.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/course"/>
</@formMacro.rtcForm>


