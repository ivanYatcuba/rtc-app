<#import "../../../datatables.ftl" as datatables/>
<#import "../../../fieldMacro.ftl" as formMacro/>

<div id="courseTable" class="row-fluid">
<#list courses as course>
    <div class="col-md-4">
        <div class="thumbnail" style="font-size: small; display:inline-block; float:none;text-align:left; padding-left: 25px; padding-right: 25px;
        <#if course.status == "ARCHIVED">background-color: #f3eaea;</#if>">
            <div style="width: 242px;">

                <a style="text-decoration: underline;  font-size: large; " href="<@spring.url'/user/courseDetails/${course.code}'/>">${course.name} </a>
                <br/>
                <div class="col-md-6" style="padding-left: 0px">
                    <span style="font-style: italic; font-size: small;">${course.experts?first.name}&nbsp;${course.experts?first.surname}</span>
                </div>
                <div class="col-md-6" style="text-align: right">
                    <#if course.startDate?date?string('yyyy') != course.endDate?date?string('yyyy')>
                        <span style="font-style: italic;font-size: smaller;">${course.startDate?date?string('dd-MMM-yyyy')}&nbsp;-&nbsp;${course.endDate?date?string('dd-MMM-yyyy')}</span>
                    <#else >
                        <span style="font-style: italic;font-size: smaller;">${course.startDate?date?string('dd-MMM')}&nbsp;-&nbsp;${course.endDate?date?string('dd-MMM')}&nbsp;${course.endDate?date?string('yyyy')}</span>
                    </#if>
                </div>
            </div>
            <img src="<@spring.url'/resources/images/profile.jpg'/>"  alt="..." style="width: 242px;height: 200px"><br/>
            <div style="width: 242px; min-height: 8em; height: 8em">
                    <p><span class="description">${course.description}</span><br/><a>More >></a></p>
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
                        <img src="<@spring.url'/resources/images/user/c1.png'/>"  alt="..."  data-toggle="tooltip" data-placement="bottom" title="<@spring.message 'course.freeCapacity'/>">
                        &nbsp;<span data-toggle="tooltip" data-placement="bottom" title="<@spring.message 'course.appliedCapacity'/>">0</span>/
                        <span data-toggle="tooltip" data-placement="bottom" title="<@spring.message 'course.totalCapacity'/>">${course.capacity}</span><br/>&nbsp;
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
        <button id="withArchiveButton" type="button" class="btn btn-primary btn-xs"><@spring.message "courses.archive"/></button>
    </div>
</div>
</#if>

<div class="row">
<div  class="col-md-offset-6 col-md-6" style="text-align: right">
    <@datatables.addPagination/>
</div>
</div>

<script>
    $(function() {
        $.each($('.description'), function() {
            var str = $(this).html();
            $(this).html(shorten(str, 150));
        });
    });
    $("#withArchiveButton").click(function (event) {
        $("#withArchive").val("true");
        search($("#currentType").attr("type"), 1);
    });
</script>