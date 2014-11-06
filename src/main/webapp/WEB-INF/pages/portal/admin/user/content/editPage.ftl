<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "userName" "/admin/user/update/${user.code}" "user.editUser" "${validationRules}" "multipart/form-data">
    <@spring.formHiddenInput "user.code" />
    <@spring.formHiddenInput "user.id" />
    <@spring.formHiddenInput "user.registerDate" />
    <@spring.formHiddenInput "user.status" />
    <#include "createUser.ftl" />
    <@formMacro.rtcSubmit "Save" "Cancel" "/admin/user/userPage/${user.code}"/>
</@formMacro.rtcForm>
