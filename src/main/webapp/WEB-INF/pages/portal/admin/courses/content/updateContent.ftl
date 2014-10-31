<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "courseForm" "/admin/course/update" "update.message" "${validationRules}" "multipart/form-data">
    <@spring.formHiddenInput "course.id" />
    <@spring.formHiddenInput "course.code" />
    <@spring.formHiddenInput "course.status" />
    <@spring.formHiddenInput "course.publishDate" />
    <#include "courseForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/admin/course/view/${course.code}"/>
</@formMacro.rtcForm>