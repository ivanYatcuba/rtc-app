<#ftl strip_whitespace=true>
<#include "/spring.ftl"/>

<#macro includeHeader>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="<@spring.url'/resources/tag-it/js/tag-it.js'/>" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
    <link href="<@spring.url'/resources/tag-it/css/jquery.tagit.css'/>" rel="stylesheet" type="text/css">
    <link href="<@spring.url'/resources/tag-it/css/tagit.ui-zendesk.css'/>" rel="stylesheet" type="text/css"></head>
</#macro>

<#macro formDatepicker path attributes="">
    <@formInput path attributes/>
    <script type="text/javascript">
        $(function() {
            $("#${status.expression?replace('[','')?replace(']','')}").datepicker(
                    {
                        dateFormat: "dd.mm.yy"
                    }
            );
        });
    </script>
</#macro>

<#macro formTagsInput path attributes="">
    <@formHiddenInput path attributes/>
    <ul id="${status.expression?replace('[','')?replace(']','')}Tag" style="margin: 0px"></ul>
    <script type="text/javascript">
        $(function() {
            $('#${status.expression?replace('[','')?replace(']','')}Tag').tagit({
                singleField: true,
                singleFieldNode: $('#${status.expression?replace('[','')?replace(']','')}')
            });
        });
    </script>
</#macro>

<#macro formItem path type="text" attributes="">
    <@bind path/>
    <div>
        <label class="control-label" for="${status.expression?replace('[','')?replace(']','')}">
            <@message path/>
        </label>
         <div class="controls">
            <#if type == "text">
                <@formInput path attributes/>
            <#else>
                <#if type == "datepiker">
                    <@formDatepicker path attributes/>
                <#else>
                    <#if type == "tag">
                        <@formTagsInput path attributes/>
                    </#if>
                </#if>
            </#if>
            <@showErrors "<br/>"/>
    </div>
</div>
</#macro>