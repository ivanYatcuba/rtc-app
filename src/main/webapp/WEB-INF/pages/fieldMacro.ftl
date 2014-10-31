<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring/>
<#--<#include "/spring.ftl"/>-->
<#include "rtcmacroses.ftl"/>

<#macro rtcIncludeLink>
<link href="<@spring.url'/resources/css/bootstrap.min.css'/>" rel="stylesheet"/>
<link href="<@spring.url '/resources/css/application.css'/>" rel="stylesheet" type="text/css"/>
<link href="<@spring.url'/resources/css/jquery.tagit.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/css/tagit.ui-zendesk.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.css'/>" rel="stylesheet" type="text/css">
</#macro>

<#macro rtcIncludeScript>
<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/js/bootstrap.min.js'/>" type="text/javascript"></script>
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url'/resources/js/tag-it.js'/>" type="text/javascript" charset="utf-8"></script>
</#macro>

<#--
* rtcFieldWrapper

*Just a wrapper for inputs
-->
<#macro rtcFieldWrapper label path>
    <@spring.bind path/>
    <div class="form-group">
        <label  class="control-label col-md-3" for="${path?substring(path?index_of(".")+1,
        path?length)}"><@spring.message "${label}"/></label>
    <div class="col-md-5">
        <#nested>
    </div>
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
<#macro rtcForm name action title validationRules="" enctype="">
<form class="form-horizontal" name="${name}" id="${name}" action="<@spring.url "${action}" />" method="post" enctype="${enctype}">
    <@rtcFormValidation formName="${name}" jsonRules="${validationRules}"/>
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
        <@spring.formInput path "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormPasswordInput label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formPasswordInput path "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormSingleSelect label path options class="" style="" messagePrefix="" noSelection={"" : ""} >
    <@rtcFieldWrapper label path>
    <select id="${status.expression?replace('[','')?replace(']','')}" name="${status.expression}" class = "form-control ${class}" style="${style}">
        <#if noSelection?is_hash>
            <#list noSelection?keys as noSelectionKey>
                <option value="${noSelectionKey}">
                    ${noSelection[noSelectionKey]}
                </option>
            </#list>
        </#if>
        <#if options?is_hash>
            <#list options?keys as value>
                <option value=""<@checkSelected value/>>
                <#-- value="" means that you will receive in controller empty string instead of
                role, status, author e.t.c. so you should provide in search class chech if (myproperty!= null && !"".equals(myproperty) -->
                    <#if messagePrefix == "">
                        ${options[value]?html}
                    <#else>
                        <@message "${messagePrefix + options[value]?html}"/>
                    </#if>
                </option>
            </#list>
        <#else>
            <#list options as value>
                <option value="${value?html}"<@checkSelected value/>>
                    <#if messagePrefix == "">
                    ${value?html}
                    <#else>
                        <@message "${messagePrefix + value?html}"/>
                    </#if>
                </option>
            </#list>
        </#if>
    </select>
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormMultiSelect label path options class="" style="" messagePrefix="">
        <@rtcFieldWrapper label path>

        <select multiple="multiple"
                <#if status.expression??>id="${status.expression?replace('[','')?replace(']','')}"</#if>
                name="${status.expression!""}"
                class = "form-control ${class}"  style = "${style}">
            <#if options?is_hash>
                <#list options?keys as value>
                    <option value="${value?html}"<@checkSelected value/>>
                        <#if messagePrefix == "">
                            ${options[value]?html}
                        <#else>
                            <@message "${messagePrefix + options[value]?html}"/>
                        </#if>
                    </option>
                </#list>
            <#else>

            <#list options as value>
                <#assign isSelected = contains(status.actualValue?default([""]), value)>
                <option value="${value}"<#if isSelected>selected="selected"</#if>>
                    <#if messagePrefix == "">
                        ${value?html}
                    <#else>
                        <@message "${messagePrefix + value?html}"/>
                    </#if>
                </option>
            </#list>
            </#if>
        </select>
        </@rtcFieldWrapper>
</#macro>

<#macro rtcFormTextarea label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formTextarea path "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormCheckbox label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formCheckbox path "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormCheckboxes label path options class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formCheckboxes path options "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormUserEmailInput label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formInput path "class = \"form-control "+"${class}"+"\" "+"${style}" />
    </@rtcFieldWrapper>
    <script type="text/javascript" src="<@spring.url'/resources/js/pages/userMailValidation.js'/>"></script>
    <script type="text/javascript">
        $(function () {addMailValidation("<@spring.url "/mailExist/" />", "${user.email!""}")});
    </script>
</#macro>

<#macro rtcFormPasswordInputWithCheckbox label path class="" style="">
    <@rtcFieldWrapper label path>
        <@spring.formPasswordInput path "class = \"form-control "+"${class}"+"\" "+"${style}" />
        <div class="controls">
        <label for="showPassword"></label>
        <input id="showPassword"
               onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
               name="showPassword" type="checkbox" value="false"
               style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
        </div>
    </@rtcFieldWrapper>
</#macro>

<#macro rtcFormDateField label path class="" style="">
    <@rtcFieldWrapper path path>
        <@spring.formInput path "class = \"form-control "+"${class}"+"\" "+"${style}"/>
        <script type="text/javascript">

            $(document).ready(function () {
                var selector = "${path?substring(path?index_of(".")+1,path?length)}";
                $("#"+selector).datepicker(
                        {
                            dateFormat: "dd.mm.yy"
                            <#if path?substring(path?index_of(".")+1,path?length) == "birthDate">
                                , changeMonth: true,
                                changeYear: true,
                                yearRange: "-100:+0",
                                maxDate: '-1d'
                            </#if>
                        }
                );
                $("#"+selector).attr('readonly', 'readonly');
            });
        </script>
    </@rtcFieldWrapper>
</#macro>

<#--<#macro rtcFormMultiSelect label path options attributes="">-->
    <#--<@rtcFieldWrapper label path>-->
        <#--<select multiple="multiple"-->
                <#--id="${status.expression?replace('[','')?replace(']','')}"-->
                <#--name="${status.expression}" ${attributes}>-->
            <#--<#list options as value>-->
                <#--<#assign isSelected = contains(status.actualValue?default([""]), value)>-->
                <#--<option value="${value}"<#if isSelected>selected="selected"</#if>>${value}</option>-->
            <#--</#list>-->
        <#--</select>-->
    <#--</@rtcFieldWrapper>-->
<#--</#macro>-->

<#macro rtcFormTagsInput label path attributes="">
    <@rtcFieldWrapper label path>
        <@formHiddenInput path attributes/>
        <ul id="${status.expression?replace('[','')?replace(']','')}Tag"></ul>
        <script type="text/javascript">
            $(function () {
                $('#${status.expression?replace('[','')?replace(']','')}Tag').tagit({
                    singleField: true,
                    singleFieldNode: $('#${status.expression?replace('[','')?replace(']','')}')
                });
            });
        </script>
    </@rtcFieldWrapper>
</#macro>


<#macro formDateSearch pathSingleSelect pathDatepicker class="" style="">
<div class="form-group">
<label for="compare" class="control-label col-md-3" ><@spring.message pathDatepicker/></label>
  <div id="compare"class="col-md-2"><@formSingleSelect pathSingleSelect, ["=", "<", ">"], 'class=form-control'/></div>
    <div class="col-md-3"><@formDatepicker pathDatepicker 'class=form-control'/></div>
</div>
</#macro>


<#macro rtcFormValidation formName jsonRules >
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script>
    $(document).ready(function () {
        $("#${formName}").validate({
        ${jsonRules}

            submitHandler: function (form) {
                if (!this.wasSent) {
                    this.wasSent = true;
                    $(':submit', form).val('Please wait...')
                            .attr('disabled', 'disabled')
                            .addClass('disabled');
                    form.submit();
                } else {
                    return false;
                }
            }
        });
        $(".required").each(function () {
            var myid = this.id;
            var label = $("label[for=\'" + myid + "\']");
            var text = label.text();
            label.html('* ' + text);
        });
    });
</script>
</#macro>

