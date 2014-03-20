<html>
<#import "/spring.ftl" as spring/>

<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
</head>

<body>

<form name="course" id = "course" action="<@spring.url "/admin/courses/update" />" method="post">

    <div class="container">
    <@spring.formHiddenInput "course.id" />
    <@spring.formHiddenInput "course.code" />
        <h3><@spring.message "update.message"/></h3>
        <hr width="100%">
        <!--Course name & Author-->
        <div class="row-fluid span12">
            <div class="container">
                <div class = "span5">

                    <label for="name" style="text-align: right">
                    <@spring.message "courses.name"/>&nbsp;
                    <@spring.formInput "course.name" />
                    <@spring.showErrors "<br>" />
                    </label>

                </div>

                <div class="span7">

                    <label for="course.author.firstName" >
                    <@spring.message "courses.author"/>
                    <@spring.formInput "course.author.firstName" />
                    <@spring.formInput "course.author.lastName" />
                    <@spring.showErrors "<br>" />
                    </label>

                </div>
            </div>
        </div>

        <!--Category & Email-->

        <div class="row-fluid span12">
            <div class="container">
                <div class="span5">

                    <label for="type" style="text-align: right">
                    <@spring.message "courses.category"/>
                    <@spring.bind "categories" />
                    <@spring.formSingleSelect "course.type", categories, " " />
                    </label>

                </div>

                <div class="span7">

                    <label for="course.author.email">
                    <@spring.message "courses.email"/>&nbsp;
                    <@spring.formInput "course.author.email"/>
                    <@spring.showErrors "<br>" />
                    </label>

                </div>
            </div>
        </div>

        <!--startDate & Tags-->
        <div class="row-fluid span12">
            <div class="container">
                <div class="span5">

                    <label for="startDate" style="text-align: right">
                    <@spring.message "courses.startDate" />
                    <@spring.formInput "course.startDate" />
                    <@spring.showErrors "<br>" />
                    </label>

                </div>

                <div class="span7">

                    <label for="course.tags">
                    <@spring.message "courses.tags"/> &nbsp;
                    <@spring.formInput "course.tags" />
                    <@spring.showErrors "<br>" />
                    </label>

                </div>
            </div>
        </div>

        <!--endDate-->
        <div class="row-fluid span12">
            <div class="container">
                <div class="span5">

                    <label for="endDate" style="text-align: right">
                    <@spring.message "courses.endDate"/>
                    <@spring.formInput "course.endDate" />
                    <@spring.showErrors "<br>" />
                    </label>

                </div>
            </div>
        </div>

        <!--Create & Cancel-->
        <div class="row-fluid span12">
            <div class="container">
                <div class="span5">
                </div>

                <div class="span6"  style="text-align: right">

                    <input type="submit" value="Update" /> or <a href="<@spring.url "/admin/courses" />">Cancel</a>

                </div>
            </div>
        </div>

    </div>
</form>
</body>
</html>


