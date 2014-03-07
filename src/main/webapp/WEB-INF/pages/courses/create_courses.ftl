<html>
<#import "/spring.ftl" as spring/>
<head>

    </head>
<body>

<form name="create" id = "create" action="${rc.getContextUrl('/course/create')}" method="post">
    <h2>Create Course</h2>
    <div>
        <dl>
            <dt><label for="name"><@spring.message "courses.name"/></label></dt>
            <dd><@spring.formInput "course.name" />
            <dd><@spring.showErrors "<br>" />
            <dt><label for="type"><@spring.message "courses.category"/></label></dt>
            <!--<dd><@spring.formMultiSelect "course.type" ""/>-->
            <dt><label for="startDate"><@spring.message "courses.startDate"/></label></dt>
            <dd><@spring.formInput "course.startDate" />
            <dd><@spring.showErrors "<br>" />
            <dt><label for="endDate"><@spring.message "courses.endDate"/></label></dt>
            <dd><@spring.formInput "course.endDate" />
            <dd><@spring.showErrors "<br>" />
        </dl>
    </div>
    <div>
        <dl>
            <dt><label for="author.Name"><@spring.message "courses.author"/></label></dt>
            <dd><@spring.formInput "course.author.lastName" />
            <dd><@spring.showErrors "<br>" />
            <dd><@spring.formInput "course.author.firsName" /><
            <dd><@spring.showErrors "<br>" />
            <dt><label for="author.Email"><@spring.message "courses.email"/></label></dt>
            <dd><@spring.formInput "course.author.email" /><
            <dd><@spring.showErrors "<br>" />
            <dt><label for="endDate"><@spring.message "courses.tags"/></label></dt>
            <dd><@spring.formInput "course.Tags" />
            <dd><@spring.showErrors "<br>" />
        </dl>
    </div>
    <input type="submit" value="Create" /><@spring.message "courses.or"/> <a href="#"><@spring.message "courses.cancel"/></a>
</form>


</body>
</html>
