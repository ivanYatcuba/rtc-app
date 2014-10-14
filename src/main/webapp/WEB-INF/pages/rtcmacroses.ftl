<#ftl strip_whitespace=true>
<#include "/spring.ftl"/>


<#macro includeLink>
<link href="<@spring.url'/resources/css/bootstrap.min.css'/>" rel="stylesheet"/>
<link href="<@spring.url '/resources/css/style.css'/>" rel="stylesheet" type="text/css"/>
<link href="<@spring.url '/resources/css/style.css'/>" rel="stylesheet" type="text/css"/>
<link href="<@spring.url'/resources/css/jquery.tagit.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/css/tagit.ui-zendesk.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.css'/>" rel="stylesheet" type="text/css">
</#macro>

<#macro includeScript>
<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/js/bootstrap.min.js'/>" type="text/javascript"></script>
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<#--<script src="<@spring.url'/reso/urces/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>-->
<script src="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url'/resources/js/tag-it.js'/>" type="text/javascript" charset="utf-8"></script>
</#macro>

<#macro formItem path attributes="" type="text" collectionAttribute="" messagePrefix="">
    <@bind path/>
<div class="form-group">
    <label class="control-label col-md-2"
           for="${status.expression?replace('[','')?replace(']','')}"><@message path/></label>

    <div class="col-md-4">
        <#if type == "text"><@formInput path attributes/>
        <#elseif type == "textArea"><@formTextarea path attributes/>
        <#elseif type == "multiSelect"><@formMultiSelect path collectionAttribute attributes/>
        <#elseif type == "password"><@formPasswordInput path attributes/>
        <#elseif type == "singleSelect"><@formSingleSelect path collectionAttribute attributes messagePrefix/>
        <#elseif type == "datepiker"><@formDatepicker path attributes/>
        <#elseif type == "tag"><@formTagsInput path attributes/>
        </#if>
    </div>
</div>
</#macro>

<#macro formSingleSelect path options attributes="" messagePrefix="">
    <@bind path/>
<select id="${status.expression?replace('[','')?replace(']','')}" name="${status.expression}" ${attributes}>
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
</#macro>

<#macro formMultiSelect path options attributes="">
    <@bind path/>
<select multiple="multiple"
        id="${status.expression?replace('[','')?replace(']','')}"
        name="${status.expression}" ${attributes}>
    <#list options as value>
        <#assign isSelected = contains(status.actualValue?default([""]), value)>
        <option value="${value}"<#if isSelected>
                selected="selected"</#if>>${value}</option>
    </#list>
</select>
</#macro>

<#macro formDateSearch pathSingleSelect pathDatepicker attributes="">
    <@bind pathSingleSelect/>
<div class="form-group">
    <label class="control-label col-md-2"
           for="${status.expression?replace('[','')?replace(']','')}"><@message pathDatepicker/></label>
    <#assign seq = ["=", "<", ">"]>
    <@formSingleSelect pathSingleSelect seq attributes/>
    <@formDatepicker pathDatepicker attributes/>
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
        $("#${status.expression?replace('[','')?replace(']','')}").attr('readonly', 'readonly');
    });
</script>
</#macro>

<#macro formTagsInput path attributes="">
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
</#macro>

<#macro formValidation formName jsonRules >
<#--<script src="<@spring.url'/resources/Bootstrap/js/bootstrap.min.js'/>"></script>-->
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<#--<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>-->
<script>
    $(document).ready(function () {
        $("#${formName}").validate({
        ${jsonRules}
            /*submitHandler: function (form) {
                form.submit();
            }*/
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


<#macro addPagination switchUrl>
<div id="navigation">
    <#if startPage??>
        <div class="row" style="margin-right: 0px">
            <ul class="pagination" style="margin: 0px;">
                <li><a href="#" onclick="switchPage(${startPage})">&laquo;&nbsp</a></li>
                <#if currentPage &gt; startPage+1>
                <#-- -2 -->
                    <#if currentPage < lastPage-1 && currentPage &gt; 0>
                    <#-- -2 +2 -->
                        <#list currentPage-2..currentPage+2 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" onclick="switchPage(${i})">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    <#else>
                        <#if currentPage < lastPage <#-- currentPage &gt; 0 --> >
                        <#-- -3 +1 -->
                            <#list currentPage-3..currentPage+1 as i>
                                <#if currentPage == i>
                                    <li class="active"><a href="#">${i}</a></li>
                                <#else>
                                    <#if i<=lastPage && i &gt; startPage || i==startPage>
                                        <li><a href="#" onclick="switchPage(${i})">${i}</a></li>
                                    </#if>
                                </#if>
                            </#list>
                        <#else>
                        <#-- -4 0 -->
                            <#list currentPage-4..currentPage as i>
                                <#if currentPage == i>
                                    <li class="active"><a href="#">${i}</a></li>
                                <#else>
                                    <#if i<=lastPage && i &gt; startPage || i==startPage>
                                        <li><a href="#" onclick="switchPage(${i})">${i}</a></li>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
                    </#if>
                <#else>
                    <#if currentPage &gt; startPage>
                    <#-- -1 +3 -->
                        <#list currentPage-1..currentPage+3 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" onclick="switchPage(${i})">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    <#else>
                    <#-- 0 +4-->
                        <#list currentPage..currentPage+4 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" onclick="switchPage(${i})">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                </#if>
                <li><a href="#" onclick="switchPage(${lastPage})">&nbsp&raquo;</a></li>
            </ul>
        </div>
    </#if>
</div>

<script>
    function switchPage(page) {
        $.ajax({
            type: "POST",
            url: "<@spring.url "${switchUrl}" />",
            data: {   page: page},
            success: function (result) {
                var str = result;
                var live_str = $('<div>', {html: str});
                var nav = live_str.find('#navigation').html();
                var data = live_str.find('#data').html();
                $('#navigation').html(nav);
                $('#data').html(data);
            }, error: function (xhr, status, error) {
                console.error()
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    }
</script>
</#macro>





