<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>

<@formMacro.rtcForm "userName" "/admin/user/update/${user.code}" "user.editUser" "${validationRules}" "multipart/form-data">
    <@spring.formHiddenInput "user.code" />
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.registerDate" />
    <@spring.formHiddenInput "user.status" />
    <@spring.formHiddenInput "user.photo" />
    <#include "userForm.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/admin/user/userPage/${user.code}"/>
</@formMacro.rtcForm>

</@layout.layout>
