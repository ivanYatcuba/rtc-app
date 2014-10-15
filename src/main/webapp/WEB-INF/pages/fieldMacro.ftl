<#import "/spring.ftl" as spring/>
<#import "rtcmacroses.ftl" as rtcmacroses/>

<#--
* rtcFieldWrapper
*Just a wrapper for inputs
-->
<#macro rtcFieldWrapper label path>
    <@spring.bind path/>
    <div class="form-group">
        <label  class="control-label col-md-3" for="${path?substring(path?index_of(".")+1,
        path?length)}"><@spring.message "${label}"/></label>
        <#nested>
    </div>
</#macro>

<#--
* rtcForm
*
* Create a bootstrap form with validation
*
* @param name form name
* @param action form action url
* @param !!!!title !111 here title is not a plain text but a key from bundle message
* @param validationRules json for jquery validate plugin
-->
<#macro rtcForm name action title validationRules="">
<form class="form-horizontal" name="${name}" id="${name}" action="<@spring.url "${action}" />" method="post">
    <@rtcmacroses.formValidation formName="${name}" jsonRules="${validationRules}"/>
    <h3 class="page-header"><@spring.message "${title}"/></h3><br/>
    <#nested>
</form>
</#macro>

<#--
* rtcSubmit
*
* create submit input with button and link
*
* @param buttonText a text used on submit button
* @param urlText a text used on link  button
* @param urlAdress href param for a link
-->
<#macro rtcSubmit buttonText urlText urlAdress>
    <div class="span2" style="text-align: right">
        <input type="submit" class="btn btn-primary" value="${buttonText}"/> or
        <a href="<@spring.url "${urlAdress}" />">${urlText}</a>
    </div>
</#macro>

<#--
* rtcFormCustomInput
*
* !!!! Use this macro if you need a very very very specific input just wrap it in this macro
*
* @param !!!!label!!11 here label is not a plain text but a key from bundle message
* @param path the name of the field to bind to
-->
<#macro rtcFormCustomInput label path>
    <@rtcFieldWrapper label path>
        <#nested>
    </@rtcFieldWrapper>
</#macro>


<#--
* rtcForm...
*
* Use this macro form form handling
*
* @param !!!!label!!11 here label is not a plain text but a key from bundle message
* @param path the name of the field to bind to
* @param options  a map (value=label) or list of all the available options if you use input like single select
* @param class  a class  of input
* @param class  style custom css for input
-->

<#macro rtcFormTextInput label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formInput path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormPasswordInput label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formPasswordInput path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormSingleSelect label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formSingleSelect path options "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormSingleSelect label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formSingleSelect path options "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormMultiSelect label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formMultiSelect path options "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormTextarea label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formTextarea path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormCheckbox label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formCheckbox path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormCheckboxes label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formCheckboxes path options "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormCheckboxes label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formRadioButtons path options "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormUserEmailInput label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formInput path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
    <script type="text/javascript" src="<@spring.url'/resources/js/pages/userMailValidation.js'/>"></script>
    <script type="text/javascript">
        $(function () {addMailValidation("<@spring.url "/mailExist/" />", "${user.email!""}")});
    </script>
</#macro>

<#macro rtcFormPasswordInputWithCheckbox label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formPasswordInput path "class = \"fieldGroup "+"${class}"+"\" "+"${style}" />
        <div class="controls">
        <label for="showPassword"></label>
        <input id="showPassword"
               onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
               name="showPassword" type="checkbox" value="false"
               style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
        </div>
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormDatePicker label path class="" style="">
    <@rtcFieldWrapper label path>
        <@rtcmacroses.formDatepicker path "class = \"fieldGroup "+"${class}"+"\" "+"${style}"/>
    </@rtcFieldWrapper>
</#macro>


