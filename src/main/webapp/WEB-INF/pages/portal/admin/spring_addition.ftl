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

                        <#if "${status.expression?replace('[','')?replace(']','')}"=="startDate">
                            ,minDate:  0,
                            onSelect: function(date){
                                var date1 = $('#startDate').datepicker('getDate');
                                var date = new Date( Date.parse( date1 ) );
                                date.setDate( date.getDate() + 1 );
                                var newDate = date.toDateString();
                                newDate = new Date( Date.parse( newDate ) );
                                $('#endDate').datepicker("option","minDate",newDate);
                            }


                        </#if>

                        <#if "${status.expression?replace('[','')?replace(']','')}"=="birthDate">
                            ,changeMonth : true,
                            changeYear : true,
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

<#macro formItem path attributes="" type="text">
    <@bind path/>
    <div>
         <div class="controls">
             <label  for="${status.expression?replace('[','')?replace(']','')}">
                 <@message path/>
             </label>
            <#if type == "text">
                <@formInput path attributes/>
            <#elseif type == "datepiker">
                <@formDatepicker path attributes/>
            <#elseif type == "tag">
                <@formTagsInput path attributes/>
            </#if>
            <@showErrors "<br/>"/>
    </div>
</div>
</#macro>

<#macro formValidation formName jsonRules >
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/js/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/css/js/jquery-validate.bootstrap-tooltip.min.js'/>"></script>


<script>
    $(document).ready(function() {

        $("#${formName}").validate({
            ${jsonRules}
            submitHandler: function(form) {
                form.submit();
            }

        });
        if($("#email").length){
            $("#email").rules('add', {
                remote: {
                    url: "<@spring.url "/mailExist" />",
                    type: "post",
                    data : {email: function(){return $("#email").val();}}
                },
                messages: {
                    remote: "Email already exist!"
                }
            });
        }
        $(".required").each(function() {
            var myid = this.id;
            var label = $("label[for=\'"+myid+"\']");
            var text = label.text();
            label.html('* ' + text);
        });

    });
    function IsEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
</script>
</#macro>

<#--
* radioButtons
*
* @param path the name of the field to bind to
* @param options a map (value=label) of all the available options
* @param classes any additional classes for the surrounding label element (such as 'inline')
* @param messageKey key to lookup in resource bundle, defaults to path
-->
<#macro radioButtons path options classes="" checkId=false messageKey=path>
    <@spring.bind path/>
    <#assign error><#if spring.status.errorMessages?has_content>error</#if></#assign>
<div class="control-group ${error}">
<#-- use id for selected check if property is an entity -->
    <#if checkId && spring.status.value??><#assign stringStatusValue=spring.status.value.id?string in spring></#if>
    <label class="control-label">
        <@spring.message messageKey/>
        <#if error?has_content><span class="help-block">${spring.status.errorMessages?first}</span></#if>
    </label>
    <div class="controls">
        <#list options?keys as value>
            <#assign id="${spring.status.expression?replace('[','')?replace(']','')}${value_index}">
            <label class="radio ${classes}">
                <input type="radio" id="${id}" name="${spring.status.expression}" value="${value?html}"<#if spring.stringStatusValue == value?string> checked</#if> >
            ${options[value]?html}
            </label>
        </#list>
    </div>
</div>
</#macro>



