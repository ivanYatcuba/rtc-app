<style>
    label {
        float: left;
        width: 13em;
        margin-right: 1em;
        text-align: right;
        font-size: 10pt;
        margin-bottom: 10px;
    }
</style>
<h3 class="page-header"><@spring.message "course.details"/></h3><br>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
        <label><@spring.message "course.name"/></label>

        <p>&nbsp${course.name}</p>
        <label><@spring.message "course.category"/></label>

        <p>&nbsp${course.types?join(", ")}</p>
        <label><@spring.message "course.capacity"/></label>

        <p>&nbsp${course.capacity}</p>
    </div>

    <div class="span5">

        <label><@spring.message "course.startDate"/></label>

        <p>${course.startDate?date}</p>
        <label><@spring.message "course.endDate"/></label>

        <p>&nbsp${course.endDate?date}</p>
        <label><@spring.message "course.tags"/></label>

        <p>
        <#list course.tags as tag>${tag.value}<#if tag_has_next>
            ,</#if> </#list></p>

    </div>

</div>

&NonBreakingSpace;
<hr>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "course.experts"/></label>

        <div id="experts">
        <#list course.experts as expert>
            <label></label>

            <p>&nbsp${expert.name}&nbsp${expert.surname}&nbsp${expert.email}</p>
        </#list>
        </div>
    </div>
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <label><@spring.message  "course.description"/></label>

    <p>&nbsp${course.description}
</div>

&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
        <label><@spring.message  "course.status"/></label>

        <p>&nbsp${course.status}
    </div>

    <div class="span5">
        <label><@spring.message "course.pudlishDate"/>&nbsp</label>
    <#if course.publishDate??><p>${course.publishDate?date}</p></#if>
    </div>
</div>

&NonBreakingSpace;
<hr>
<div class="row">
    <div class="span12" align="right">
        <a href="<@spring.url "/admin/course/${course.getCode()}/update" />">
            <button class="btn"><@spring.message "coursesPage.action.edit"/></button>
        </a> or
        <a href="<@spring.url "/admin/course" />"><@spring.message "coursesPage.action.cancel"/></a>
    </div>
</div>

