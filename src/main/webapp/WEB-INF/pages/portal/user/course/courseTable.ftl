<#import "../../../datatables.ftl" as datatables/>
<#import "../../../fieldMacro.ftl" as formMacro/>

<div id="content">
<#list courses as course>
    <div class="col-md-3 thumbnail">
        <a style="text-decoration: underline;" href="<@spring.url'/user/courseDetails/${course.code}'/>">${course.name} </a>
        <br/>
        <#list course.experts as expert>
        ${expert.name}&nbsp;${expert.surname}
        </#list>
        <img src="<@spring.url'/resources/images/profile.jpg'/>"  alt="..." style="width: 242px;height: 200px">
    </div>
</#list>
</div>

<div class="col-md-6" style="text-align: right">
    <@datatables.addPagination/>
</div>