<#import "/spring.ftl" as spring/>
<#import "rtcmacroses.ftl" as rtcmacroses/>

<#macro FieldWrapper label path>
    <@spring.bind path/>
    <div>
        <label for="${status.expression?replace('[','')?replace(']','')}">label</label>
        <#nested>
    </div>
</#macro>

<#macro InputWrapper label path type style>
    <@FieldWrapper label=label path=path />
    <input id="${status.expression?replace('[','')?replace(']','')}" class="fieldGroup" style="${style}" type="${type}">
</#macro>

<#macro FormMacro name action title validationRules>
<form class="form-horizontal" name="${name}" id="${name}" action="<@spring.url "${action}" />" method="post">
    <h3 class="page-header"><@spring.message "${title}"/></h3><br/>
    <#nested>
    <@rtcmacroses.formValidation formName="${name}" jsonRules="${validationRules}"/>
</form>
</#macro>

<#macro SubmitMacro buttonText urlText urlAdress>
    <div class="span2" style="text-align: right">
        <input type="submit" class="btn btn-primary" value="${buttonText}"/> or
        <a href="<@spring.url "${urlAdress}" />">${urlText}</a>
    </div>
</#macro>


