<h3 class="page-header">Courses List</h3>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-dropdown.js'/>"></script>

<#include "../layout/searchPanel.ftl">
    <div class="head-2"><strong>Category</strong></div>
    <div class="head-2"><strong>Name</strong></div>
    <div class="head-2"><strong>Date</strong></div>
    <div class="head-2"><strong>Author</strong></div>
    <div class="head-2"><strong>Status</strong></div>
    <div class="head-2">&nbsp;</div>
<#list course as course>
    <div class="col-xs-2">${course.type}</div>
    <div class="col-xs-2"><a href="<@spring.url "/admin/course/${course.code}" />">${course.name}</a></div>
    <div class="col-xs-2">${course.startDate?date}&nbsp;-&nbsp;${course.endDate?date}</div>
    <div class="col-xs-2">${course.author.firstName}&nbsp;${course.author.lastName}</div>
    <div class="col-xs-2">${course.status}</div>
    <div class="col-xs-2">

        <ul class="nav" role="navigation">
        <li class="dropdown">
            <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                <#if "${course.status}" == "DRAFT">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url "/admin/course/publish/${course.code}"/>">Publish!</a></li>
                </#if>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url "/admin/course/delete/${course.code}"/>">Delete</a></li>
            </ul>
        </li>
        </ul>
    </div>
</#list>

<table style="width: 100%;">
    <tr>
        <td>Pages: </td>
        <#if startPage != currentPage>
            <td><a href="<@spring.url "/admin/course?page=${startPage}" />" />First</a></td>
        </#if>
        <#if prevPage??>
            <td><a href="<@spring.url "/admin/course?page=${prevPage}" />" />${prevPage}</a></td>
        </#if>
        <td>${currentPage}</td>
        <#if nextPage??>
            <td><a href="<@spring.url "/admin/course?page=${nextPage}" />" />${nextPage}</a></td>
        </#if>
        <#if lastPage != currentPage>
            <td><a href="<@spring.url "/admin/course?page=${lastPage}" />" />Last</a></td>
        </#if>
    </tr>
</table>


<a style="float: right" href="<@spring.url "/admin/course/create" />">
    <button class="btn">Create New</button>
</a>

