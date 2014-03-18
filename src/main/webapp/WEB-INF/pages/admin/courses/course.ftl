<html>
<#import "/spring.ftl" as spring/>
<head>
</head>
<body style="background: #fff">
<center><h3>View Courses</h3></center>
<hr width="70%" align="center">
<div style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: center;">
    <div style="float: left; margin-right: 50px">
        <p>Course name: ${course.name}</p>
        <p>Category: ${course.type}</p>
        <p>Start Date: ${course.startDate?date}</p>
        <p>Finish Date: ${course.endDate?date}</p>
    </div>
    <div style="float: left; margin-right: 15px">
        <p>Author: ${course.author.firstName} &nbsp; ${course.author.lastName}</p>
        <p>Email: ${course.author.email}</p>
        <p>Tags:<#list course.tags as tag>${tag.value}</#list>:</p>
    </div>
</div>
<div style="width:10%">
    <a href="<@spring.url "/courses/${course.id}/update" />"><@spring.message "coursesPage.action.update"/></a>
    <a href="<@spring.url "/courses/" />"><@spring.message "coursesPage.action.back"/></a>
</div>
</body>
</html>
