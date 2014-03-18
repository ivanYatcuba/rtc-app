<html>
<#import "/spring.ftl" as spring/>
<head>

</head>
<body>

<form name="course" id = "course" action="<@spring.url "/admin/courses/update" />" method="post">
    <h2>Create course</h2>
    <@spring.formHiddenInput "course.id" />
    <@spring.formHiddenInput "course.code" />
    <div>
        <dl>
            <dt><label for="name">Course name:</label></dt>
            <dd><@spring.formInput "course.name" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
            <dt><label for="type">Category:</label></dt>
            <dd>
            <@spring.bind "categories" />
                <@spring.formSingleSelect "course.type", categories, " " />
            </dd>
            <dt><label for="startDate">Start Date:</label></dt>
            <dd><@spring.formInput "course.startDate" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
            <dt><label for="endDate">Finish Date:</label></dt>
            <dd><@spring.formInput "course.endDate" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
        </dl>
    </div>
    <div>
        <dl>
            <dt><label for="author.firstName">Author:</label></dt>
            <dd><@spring.formInput "course.author.firstName" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
            <dd><@spring.formInput "course.author.lastName" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
            <dt><label for="author.firstName">Email:</label></dt>
            <dd><@spring.formInput "course.author.email" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
            <dt><label for="author.tags">Tags:</label></dt>
            <dd><@spring.formInput "course.tags" /></dd>
            <dd><@spring.showErrors "<br>" /></dd>
        </dl>
    </div>
    <input type="submit" value="Update" />
</form>
</body>
</html>