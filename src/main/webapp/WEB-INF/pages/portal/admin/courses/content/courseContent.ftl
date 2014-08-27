<h3 class="page-header"><@spring.message "course.details"/></h3>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
        <label><@spring.message "course.name"/></label><p>&nbsp${course.name}</p>
        <label><@spring.message "course.category"/></label><p>&nbsp${course.type}</p>
        <label><@spring.message "course.capacity"/></label><p>&nbsp${course.capacity}</p>
    </div>

    <div class="span5">

        <label><@spring.message "course.startDate"/></label><p>${course.startDate?date}</p>
        <label><@spring.message "course.endDate"/></label><p>&nbsp${course.endDate?date}</p>
        <label><@spring.message "course.tags"/></label><p>
        <#list course.tags as tag>${tag.value}<#if tag_has_next>,</#if> </#list></p>

    </div>

</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px; ">
    <div class="span6" >
        <label><@spring.message "course.author.lastName"/></label><p>&nbsp${course.author.lastName}</p>
        <label><@spring.message  "course.author.firstName"/></label><p>&nbsp${course.author.firstName}</p>
    </div>

    <div class="span5">
        <label><@spring.message  "course.author.email"/></label><p>&nbsp${course.author.email}</p>
    </div>
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <label><@spring.message  "course.description"/></label><p>&nbsp${course.description}
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
        <label><@spring.message  "course.status"/></label><p>&nbsp${course.status}
    </div>

    <div class="span5">
        <label><@spring.message "course.pudlishDate"/>&nbsp</label>
        <#if course.publishDate??><p>${course.publishDate?date}</p></#if>
    </div>
</div>

&NonBreakingSpace;
<hr>
<div class = "row">
    <div class="span12" align="right">
        <a href="<@spring.url "/admin/course/${course.getCode()}/update" />"><button class="btn"><@spring.message "coursesPage.action.edit"/></button></a> or
        <a href="<@spring.url "/admin/course" />"><@spring.message "coursesPage.action.cancel"/></a>
    </div>
</div>

