<#ftl strip_whitespace=true>
<#include "/spring.ftl"/>
<

<#macro includeLink>
<#--<link href="<@spring.url'/resources/css/bootstrap.css'/>" rel="stylesheet"/>-->
<#--<link href="<@spring.url'/resources/css/dashboard.css'/>" rel="stylesheet" type="text/css"-->
<link href="<@spring.url'/resources/css/bootstrap.min.css'/>" rel="stylesheet"/>
<link href="<@spring.url '/resources/css/style.css'/>" rel="stylesheet" type="text/css"/>
<link href="<@spring.url '/resources/css/style.css'/>" rel="stylesheet" type="text/css"/>
<link href="<@spring.url'/resources/css/jquery.tagit.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/css/tagit.ui-zendesk.css'/>" rel="stylesheet" type="text/css">
<link href="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.css'/>" rel="stylesheet" type="text/css">
</#macro>

<#macro includeScript>
<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<#--<script src="<@spring.url'/resources/Bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>-->
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery-ui/jquery-ui.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url'/resources/js/tag-it.js'/>" type="text/javascript" charset="utf-8"></script>
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
        $("#${status.expression?replace('[','')?replace(']','')}").attr('readonly','readonly');
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
<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
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

<#macro addPagination switchUrl>
<div id="navigation">
    <#if startPage??>
        <div class="row">
            <div class="col-md-12" align="right">
                Pages:
                <a href="#" onclick="switchPage(${startPage})">&laquo;&nbsp</a>
                <#if prevPage??>
                    <a href="#" onclick="switchPage(${prevPage})">&lsaquo;&nbsp</a>
                <#else >
                    <a href="#" onclick="switchPage(${startPage})">&lsaquo;&nbsp</a>
                </#if>

                <#list currentPage-5..currentPage+5 as i>
                    <#if i <= lastPage && i &gt; 0>
                        <#if currentPage == i>
                            <u>${i}</u>
                        <#else >
                            <a href="#" onclick="switchPage(${i})">${i}</a>
                        </#if>
                    </#if>
                </#list>

                <#if nextPage??>
                    <a href="#" onclick="switchPage(${nextPage})">&nbsp&rsaquo;</a>
                <#else >
                    <a href="#" onclick="switchPage(${lastPage})
                            ">&nbsp&rsaquo;</a>
                </#if>
                <a href="#" onclick="switchPage(${lastPage})">&nbsp&raquo;</a>

            </div>
        </div>
    </#if>
</div>

  <#--mine try-->
        <#--<ul class="pagination inline-box">-->
        <#--<li><a href="#" onclick="switchPage(${startPage})">&laquo;&nbsp</a></li>-->
        <#--<#if prevPage??>-->
        <#--<li><a href="#" onclick="switchPage(${prevPage})">&lsaquo;&nbsp</a></li>-->
        <#--<#else >-->
        <#--<li><a href="#" onclick="switchPage(${startPage})">&lsaquo;&nbsp</a></li>-->
        <#--</#if>-->

        <#--<#list currentPage-5..currentPage+5 as i>-->
        <#--<#if i <= lastPage && i &gt; 0>-->
        <#--<#if currentPage == i>-->
        <#--<#--<u>${i}</u>-->
        <#--<#else >-->
        <#--<li><a href="#" onclick="switchPage(${i})">${i}</a></li>-->
        <#--</#if>-->
        <#--</#if>-->
        <#--</#list>-->
        <#--<#if nextPage??>-->
        <#--<li><a href="#" onclick="switchPage(${nextPage})">&nbsp&rsaquo;</a></li>-->
        <#--<#else>-->
        <#--<li><a href="#" onclick="switchPage(${lastPage})">&nbsp&rsaquo;</a></li>-->
        <#--</#if>-->
        <#--<li><a href="#" onclick="switchPage(${lastPage})">&nbsp&raquo;</a></li>-->

        <#--</ul>-->

<#--</div>-->
<#--</#if>-->

<script>
    function switchPage(page) {
        $.ajax({
            type: "POST",
            url: "<@spring.url "${switchUrl}" />" + page + "/",
            success: function (result) {
                var str = result;
                var live_str = $('<div>', {html: str});
                var nav = live_str.find('#navigation').html();
                var data = live_str.find('#data').html();
                $('#navigation').html(nav);
                $('#data').html(data);
            }, error: function (xhr, status, error) {
                alert("error")
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    }
</script>
</#macro>
