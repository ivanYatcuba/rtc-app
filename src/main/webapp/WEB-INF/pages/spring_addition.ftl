<#ftl strip_whitespace=true>
<#include "/spring.ftl"/>

<#macro includeLink>
<link href="<@spring.url'/resources/Bootstrap/css/bootstrap.css'/>"
      rel="stylesheet"/>
<link href="<@spring.url'/resources/Bootstrap/css/bootstrap.min.css'/>"
      rel="stylesheet"/>
<link href="<@spring.url '/resources/css/style.css'/>" rel="stylesheet"/>
<link href="<@spring.url'/resources/tag-it/css/jquery.tagit.css'/>"
      rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/tag-it/css/tagit.ui-zendesk.css'/>"
      rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.css'/>"
      rel="stylesheet" type="text/css">
</#macro>

<#macro includeScript>
<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/Bootstrap/js/bootstrap.min.js'/>"
        type="text/javascript"></script>
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.js'/>"
        type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url'/resources/tag-it/js/tag-it.js'/>"
        type="text/javascript" charset="utf-8"></script>
</#macro>

<#macro formItem path attributes="" type="text" collectionAttribute="">
    <@bind path/>
<div class="form-group">
    <label class="control-label col-md-2"
           for="${status.expression?replace('[','')?replace(']','')}"><@message path/></label>

    <div class="col-md-4">
        <#if type == "text"><@formInput path attributes/>
            <#elseif type == "textArea"><@formTextarea path attributes/>
        <#elseif type == "multiSelect"><@formMultiSelect path collectionAttribute attributes/>
        <#elseif type == "password"><@formPasswordInput path attributes/>
        <#elseif type == "singleSelect"><@formSingleSelect path collectionAttribute attributes/>
        <#elseif type == "datepiker"><@formDatepicker path attributes/>
        <#elseif type == "tag"><@formTagsInput path attributes/>
        </#if>
    </div>
</div>
</#macro>


<#macro formDatepicker path attributes="">
    <@formInput path attributes/>
<script type="text/javascript">

    $(function () {
        $("#${status.expression?replace('[','')?replace(']','')}").datepicker(
                {
                    dateFormat: "dd.mm.yy"

                    <#if "${status.expression?replace('[','')?replace(']','')}"=="birthDate">
                        , changeMonth: true,
                        changeYear: true,
                        yearRange: "-100:+0",
                        maxDate: '-1d'
                    </#if>
                }
        );

    });
</script>
</#macro>

<#macro formTagsInput path attributes="">
    <@formHiddenInput path attributes/>
<ul id="${status.expression?replace('[','')?replace(']','')}Tag"></ul>
<script type="text/javascript">
    $(function () {
        $('#${status.expression?replace('[','')?replace(']','')}Tag').tagit();
    });
</script>
</#macro>

<#macro formValidation formName jsonRules >
<script src="<@spring.url'/resources/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
<script>
    $(document).ready(function () {
        $("#${formName}").validate({
        ${jsonRules}
            submitHandler: function (form) {
                form.submit();
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



