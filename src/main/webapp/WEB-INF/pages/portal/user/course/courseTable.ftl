<#import "../../../datatables.ftl" as datatables/>
<#import "../../../fieldMacro.ftl" as formMacro/>

<div id="courseTable" class="row-fluid">
<#list courses as course>
    <div class="col-md-4">
        <div class="thumbnail" style="display:inline-block; float:none;text-align:left; padding-left: 10px; padding-right: 10px">
            <div style="width: 242px;">
                <a style="text-decoration: underline;" href="<@spring.url'/user/courseDetails/${course.code}'/>">${course.name} </a>
                <br/>
                <#list course.experts as expert>
                    ${expert.name}&nbsp;${expert.surname}
                </#list>
            </div>
            <img src="<@spring.url'/resources/images/profile.jpg'/>"  alt="..." style="width: 242px;height: 200px"><br/>
            <div class="description" style="width: 242px;">
                    <p>${course.description}<br/><a>More >></a></p>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div style="width: 102px; height: 26px">
                        <img src="<@spring.url'/resources/images/user/star.png'/>"  alt="...">
                        <img src="<@spring.url'/resources/images/user/star.png'/>"  alt="...">
                        <img src="<@spring.url'/resources/images/user/star.png'/>"  alt="...">
                        <img src="<@spring.url'/resources/images/user/star.png'/>"  alt="...">
                        <img src="<@spring.url'/resources/images/user/star.png'/>"  alt="...">
                    </div>
                </div>

                <div class="col-md-6">
                    <div  style="text-align: right">
                        <img src="<@spring.url'/resources/images/user/c1.png'/>"  alt="..." style="width: 37px; height: 26">
                        &nbsp;0/15<br/>&nbsp;
                    </div>
                </div>
            </div>
        </div>
    </div>
</#list>
</div>

<#if currentPage == lastPage>
<div class="row">
    <div  class="col-md-offset-6 col-md-6">
        <button type="button" class="btn btn-primary"><@spring.message "courses.archive"/></button>
    </div>
</div>
</#if>

<div class="row">
<div  class="col-md-offset-6 col-md-6" style="text-align: right">
    <@datatables.addPagination/>
</div>
</div>