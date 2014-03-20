<html>
<#import "/spring.ftl" as spring/>
<script src="<@spring.url'/resources/css/js/jquery.js'/>"></script>
<link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-combined.no-icons.min.css'/>" rel="stylesheet"/>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<link href="runnable.css" rel="stylesheet" />

<link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<@spring.url'/resources/css/js/JavaScript.js'/>"> </script>
<link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-modal.js'/>"></script>
<script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>

<head>

</head>
<body>

<form name="course" id = "course" action="<@spring.url "/admin/courses/save" />" method="post">
    <h2><@spring.message "create.message"/></h2><br>
    <hr>

    <!--Course name & Author-->
    <div class="row-fluid span12">
        <div class="container">
            <div class = "span5">
                <label for="name">
                    <@spring.message "courses.name"/>&nbsp;
                    <@spring.formInput "course.name" />
                    <@spring.showErrors "<br>" />
                </label>
            </div>

            <div class="span7">
                <label for="course.author.firstName">
                    <@spring.message "courses.author"/>&nbsp;
                    <@spring.formInput "course.author.firstName" /> <@spring.formInput "course.author.lastName" />
                </label>
            </div>

        </div>

    </div>

    <!--Category & Email-->

    <div class="row-fluid span12">
        <div class="container">
            <div class="span5">

                <label for="type"> <@spring.message "courses.category"/>
                <@spring.bind "categories" />
                <@spring.formSingleSelect "course.type", categories, " " />
                </label>
            </div>

            <div class="span7">

                <label for="course.author.email">
                    <@spring.message "courses.email"/>&nbsp;
                    <@spring.formInput "course.author.email" />
                    <@spring.showErrors "<br>" />
                </label>

            </div>
        </div>
    </div>

    <!--startDate & Tags-->
    <div class="row-fluid span12">
        <div class="container">
            <div class="span5">

                <label for="startDate"><@spring.message "courses.startDate"/>
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

                <label for="endDate"><@spring.message "courses.endDate"/>
                    <@spring.formInput "course.endDate" />
                    <@spring.showErrors "<br>" />
                </label>

            </div>
        </div>
    </div>

        <div class="row-fluid span12">
            <div class="container">
                <div class="span5">
                </div>

                <div class="span7">
                    <input type="submit" value="Create" /> or <a name="">Cancel</a>
                </div>
            </div>
        </div>
</form>
</body>
</html>