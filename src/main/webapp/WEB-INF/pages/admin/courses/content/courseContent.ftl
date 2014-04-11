<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
        <p><@spring.message "course.name"/>&nbsp${course.name}</p>
        <p><@spring.message "course.category"/>&nbsp${course.type}</p>
        <p><@spring.message "course.capacity"/>&nbsp${course.capacity}</p>
    </div>

    <div class="span5">

        <p><@spring.message "course.startDate"/>${course.startDate?date}</p>
        <p><@spring.message "course.endDate"/>&nbsp${course.endDate?date}</p>
        <p><@spring.message "course.tag"/>
        <#list course.tag as tag>${tag.value}<#if tag_has_next>,</#if> </#list></p>

    </div>

</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px; ">
    <div class="span6" >
        <p><@spring.message "course.author.lastName"/>&nbsp${course.author.lastName}</p>
        <p><@spring.message  "course.author.firstName"/>&nbsp${course.author.firstName}</p>
    </div>

    <div class="span5">
        <p><@spring.message  "course.author.email"/>&nbsp${course.author.email}</p>
    </div>
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <@spring.message  "course.description"/>&nbsp${course.description}
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
        <@spring.message  "course.status"/>&nbsp${course.status}
    </div>

    <div class="span5">
        <@spring.message "course.pudlishDate"/>&nbsp
        <#if course.publishDate??>${course.publishDate?date}</#if>
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

