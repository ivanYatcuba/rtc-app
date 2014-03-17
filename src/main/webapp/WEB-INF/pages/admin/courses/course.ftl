<html>
<#import "/spring.ftl" as spring/>
<head>

</head>
<body>
<center><h3>View Courses</h3></center>

<hr width="70%" align="center">

<table border="0" align="center">
    <tr>
        <td width = "20%">Course Name:</td>
        <td>
            ${course.name}
        </td>
        <td width = "20%" align="right">Author:</td>
        <td>
        ${course.author.firstName} &nbsp; ${course.author.lastName}
        </td>
    </tr>
    </td>
    <td>
        <tr>
            <td width = "20%">Category:</td>
            <td>${course.type}
            </td>
            <td width = "20%" align="right">Email:</td>
            <td>
                ${course.author.email}
            </td>
        </tr>
    </td>
    <td>
        <tr>
            <td width = "20%">Start Date</td>
            <td>
                ${course.startDate?date}
            </td>
            <td width = "20%" align="right">Tags:</td>
            <td>
                <#list course.tags as tag>
                    ${tag.value}
                </#list>
            </td>
        </tr>
    </td>
    <td>
        <tr>
            <td width = "20%">Finish Date</td> &nbsp;
            <td>
            ${course.endDate?date}
            </td>
        </tr>
    </td>
    </tr>
</table>
<a href="<@spring.url "/courses/${course.id}/update" />"><@spring.message "coursesPage.action.update"/></a>
<a href="<@spring.url "/courses/" />"><@spring.message "coursesPage.action.back"/></a>
</body>
</html>