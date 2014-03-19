<html>
<#import "/spring.ftl" as spring/>
<head>

</head>
<body>

<form name="course" id = "course" action="<@spring.url "/admin/courses/create" />" method="post">
    <h2><@spring.message "create.message"/></h2><br>
    <hr>

    <!--Course name & Author-->
    <div class="row-fluid span12">

        <div class="container">

            <div class = "span6">
                <label for="name"><@spring.message "courses.name"/></label>
                <div id="name">
                    <input type="text"  id="name" name="course.name" />
                </div>
            </div>

            <div class="span3">
                <label for="course.author.firstName"><@spring.message "courses.author"/> </label>
                <input type="text"  id="course.author.firstName" name="course.author.firstName" />
            </div>

            <div class="span3">
                <input type="text"  id="course.author.lastName" name="course.author.lastName" />
            </div>

        </div>

    </div>

    <!--Category & Email-->

    <div class="row-fluid span12">

        <div class="container">

            <div class="span6">

                <label for="type"> <@spring.message "courses.category"/> </label>
            <@spring.bind "categories" />
            <@spring.formSingleSelect "course.type", "categories", " " />
            </div>

            <div class="span6">

                <label for="course.author.email"> <@spring.message "courses.email"/> </label>
                <input type="text" id="course.author.email" name="course.author.email" />

            </div>

        </div>
    </div>

    <!--startDate & Tags-->
    <div class="row-fluid span12">
        <div class="container">

            <div class="span6">

                <label for="startDate"><@spring.message "courses.startDate"/> </label>
            <@datepicker "startDate" "startDate" />

            </div>

            <div class="span6">

                <label for="course.tags"> <@spring.message "courses.tags"/> </label>
                <input type="text" id="course.tags" name="course.tags" />

            </div>
        </div>
    </div>

    <!--endDate-->
    <div class="row-fluid span12">

        <div class="container">

            <div class="span6">

                <label for="endDate"><@spring.message "courses.endDate"/> </label>
            <@datepicker "endDate" "endDate" />

            </div>

        </div>

    </div>
    <input type="submit" value="Create" /> or <label for="cancel"></label> <input type="cancel" value="Cancel" />
</form>
</body>
</html>