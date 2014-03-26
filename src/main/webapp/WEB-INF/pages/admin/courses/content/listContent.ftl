<h3 class="page-header">Courses List</h3>

<#include "../layout/searchPanel.ftl">
    <div class="head-2"><strong>Category</strong></div>
    <div class="head-3"><strong>Name</strong></div>
    <div class="head-3"><strong>Date</strong></div>
    <div class="head-2"><strong>Author</strong></div>
    <div class="head-2"><strong>Action</strong></div>
<#list courses as course>
    <div class="col-xs-2">${course.type}</div>
    <div class="col-xs-3"><a href="<@spring.url "/admin/courses/${course.code}" />">${course.name}</a></div>
    <div class="col-xs-3">${course.startDate?date}&nbsp;-&nbsp;${course.endDate?date}</div>
    <div class="col-xs-2">${course.author.firstName}&nbsp;${course.author.lastName}</div>
    <div class="col-xs-2"><a href="<@spring.url "/admin/courses/delete/${course.code}" />">delete</a></div>
</#list>

<a style="float: right" href="<@spring.url "/admin/courses/create" />">
    <button class="btn">Create New</button>
</a>