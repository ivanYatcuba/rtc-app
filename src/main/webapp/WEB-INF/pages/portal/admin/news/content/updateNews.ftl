<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "newsForm" "/admin/news/edit" "news.edit" "${validationRules}" "multipart/form-data">
    <@spring.formHiddenInput "news.code" />
    <@spring.formHiddenInput "news.id" />
    <#include "newsForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/admin/news/${news.code}"/>
</@formMacro.rtcForm>
