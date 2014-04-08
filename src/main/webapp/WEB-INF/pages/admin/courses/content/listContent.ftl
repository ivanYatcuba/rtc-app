<h3 class="page-header">Courses List</h3>


<#include "../layout/searchPanel.ftl">
    <div class="head-2"><strong>Category</strong></div>
    <div class="head-2"><strong>Name</strong></div>
    <div class="head-2"><strong>Date</strong></div>
    <div class="head-2"><strong>Author</strong></div>
    <div class="head-2"><strong>Status</strong></div>
    <div class="head-2">&nbsp;</div>
<#list courses as course>
    <div class="col-xs-2">${course.type}</div>
    <div class="col-xs-2"><a href="<@spring.url "/admin/courses/${course.code}" />">${course.name}</a></div>
    <div class="col-xs-2">${course.startDate?date}&nbsp;-&nbsp;${course.endDate?date}</div>
    <div class="col-xs-2">${course.author.firstName}&nbsp;${course.author.lastName}</div>
    <div class="col-xs-2">${course.status}</div>
    <div class="col-xs-2 dropdown">
        <p><#if "${course.status}" == "DRAFT">
            <a href="<@spring.url "/admin/courses/publish/${course.code}"/>">Publish!</a>
        </#if></p>
        <p><a href="<@spring.url "/admin/courses/delete/${course.code}"/>">Delete</a></p>
    </div>
</#list>

<table style="width: 100%;">
    <tr>
        <td>Pages: </td>
        <#if startPage != currentPage>
            <td><a href="<@spring.url "/admin/courses?page=${startPage}" />" />First</a></td>
        </#if>
        <#if prevPage??>
            <td><a href="<@spring.url "/admin/courses?page=${prevPage}" />" />${prevPage}</a></td>
        </#if>
        <td>${currentPage}</td>
        <#if nextPage??>
            <td><a href="<@spring.url "/admin/courses?page=${nextPage}" />" />${nextPage}</a></td>
        </#if>
        <#if lastPage != currentPage>
            <td><a href="<@spring.url "/admin/courses?page=${lastPage}" />" />Last</a></td>
        </#if>
    </tr>
</table>


<a style="float: right" href="<@spring.url "/admin/courses/create" />">
    <button class="btn">Create New</button>
</a>

