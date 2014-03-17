<html>
<#import "/spring.ftl" as spring/>
<head>

</head>
<body>

    <table class="datatable">
        <tr>
            <th><@spring.message "courses.category"/></th>  <th><@spring.message "courses.name"/></th>  <th><@spring.message "courses.startDate"/></th>  <th><@spring.message "courses.author"/></th>  <th><@spring.message "coursesPage.action"/></th>
        </tr>
    <#list courses as course>
        <tr>
            <td>${course.type}</td> <td><a href="<@spring.url "/courses/${course.id}" />">${course.name}</a></td> <td>${course.startDate?date}</td> <td>${course.author.firstName}&nbsp;${course.author.lastName}</td> <td><a href="<@spring.url "/courses/delete/${course.id}" />"><@spring.message "coursesPage.action.delete"/></a> </td>
        </tr>
    </#list>
    </table>
    <a href="<@spring.url "/courses/create" />"><@spring.message "coursesPage.action.create"/></a>
</body>
</html>