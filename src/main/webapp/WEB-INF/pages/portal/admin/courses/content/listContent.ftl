<h3 class="page-header">Courses List</h3>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-dropdown.js'/>"></script>

<#include "searchPanel.ftl">
<br/>
<div class="row">
    <div class="col-md-12" align="right">
        Pages:
    <#if startPage != currentPage>
        <a href="<@spring.url "/admin/course?page=${startPage}" />" />&laquo;</a>
    </#if>
    <#if prevPage??>
        <a href="<@spring.url "/admin/course?page=${prevPage}" />" />${prevPage}</a>
    </#if>
    ${currentPage}
    <#if nextPage??>
        <a href="<@spring.url "/admin/course?page=${nextPage}" />" />${nextPage}</a>
    </#if>
    <#if lastPage != currentPage>
        <a href="<@spring.url "/admin/course?page=${lastPage}" />" />&raquo;</a>
    </#if>
    </div>

</div>
<table width="100%" class="table-bordered table">
    <tr bgcolor="#d3d3d3" style="font-weight:bold">
        <td>Name</td>
        <td>Category</td>
        <td>Dates</td>
        <td>Author</td>
        <td>Status</td>
        <td>&nbsp;</td>
    </tr>
<#list courses as course>
    <tr>
        <td><a href="<@spring.url "/admin/course/${course.code}" />">${course.name}</a></td>
        <td>${course.type}</td>
        <td>${course.startDate?datetime?string("dd-MM-yyyy")}&nbsp;-&nbsp;${course.endDate?datetime?string("dd-MM-yyyy")}</td>
        <td>${course.author.firstName}&nbsp;${course.author.lastName}</td>
        <td>${course.status}</td>
        <td><ul class="nav" role="navigation">
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
        </td>
    </tr>
</#list>
</table>

<br>
<a style="float: right" href="<@spring.url "/admin/course/create" />">
    <button class="btn">Create New</button>
</a>

