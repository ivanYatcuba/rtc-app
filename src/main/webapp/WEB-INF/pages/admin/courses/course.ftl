<html>
<#import "/spring.ftl" as spring/>
<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/grid.css'/>" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="<@spring.url '/resources/css/admin.css'/>"/>
</head>
<body>
<#include "header.ftl">
<#include "menu.ftl">
<div  class="container">
    <h3>${course.name} Details:</h3>
    <hr width="100%" >

        <div class="panel panel-default">
            <div class="panel-body">
    <div class="row-fluid">
        <div class="span2"  align="right">
            <p>Course name: </p>
            <p>Category: </p>
            <p>Start Date: </p>
            <p>Finish Date: </p>
        </div>

        <div class="span2" style="margin-right: 0px">
            <p>${course.name}</p>
            <p>${course.type}</p>
            <p>${course.startDate?date}</p>
            <p>${course.endDate?date}</p>
        </div>

        <div class="span2" align="right">
            <p>Author: </p>
            <p>Email: </p>
            <p>Tags: </p>
        </div>

        <div class="span2" >
            <p>${course.author.firstName} &nbsp; ${course.author.lastName}</p>
            <p>${course.author.email}</p>
            <p><#list course.tags as tag>${tag.value}<#if tag_has_next>,</#if> </#list></p>
        </div>
        </div>

    <div class = "row">
        <div class="span8" align="right">
            <a href="<@spring.url "/admin/courses/${course.getCode()}/update" />"><button class="btn"><@spring.message "coursesPage.action.edit"/></button></a> or
            <a href="<@spring.url "/admin/courses" />"><@spring.message "coursesPage.action.cancel"/></a>
        </div>
    </div>
            </div>

        </div>

</div>

<#include "footer.ftl">


</body>
</html>
