<html>
<#import "/spring.ftl" as spring/>
<head>
</head>
<body>

<div style="margin-left: 5%; margin-right: 5%;">
    <h3>${course.name} Details:</h3>
    <hr width="100%" >
    <div style="float: left; margin-right: 15px" align="right">
        <p>Course name: </p>
        <p>Category: </p>
        <p>Start Date: </p>
        <p>Finish Date: </p>
    </div>
    <div style="float: left; margin-right: 20%">
        <p>${course.name}</p>
        <p>${course.type}</p>
        <p>${course.startDate?date}</p>
        <p>${course.endDate?date}</p>
    </div>
    <div style="float: left; margin-right: 15px" align="right">
        <p>Author: </p>
        <p>Email: </p>
        <p>Tags: </p>
    </div>
    <div style="float: left; margin-right: 15px">
        <p>${course.author.firstName} &nbsp; ${course.author.lastName}</p>
        <p>${course.author.email}</p>
        <p><#list course.tags as tag>${tag.value}<#if tag_has_next>,</#if> </#list></p>
    </div>
</div>
<div style="clear:both; margin-top: 180px; margin-left: 50%">
    <a href="<@spring.url "/courses/${course.id}/update" />"><button><@spring.message "coursesPage.action.edit"/></button></a> or
    <a href="<@spring.url "/courses/" />"><@spring.message "coursesPage.action.cancel"/></a>
</div>
</body>
</html>
