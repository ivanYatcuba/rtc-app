<html>
<#import "/spring.ftl" as spring/>

<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
</head>

<body>
<style type="text/css">
    html, body {
        height: 100%;
        width: 100%;
        margin: 0px;
        padding: 0px;
    }

    .container-fluid {
        min-height: 100%;
        height: auto !important;
        height: 100%;
    }

    .container {
        padding-bottom: 100px;
    }

    .footer {
        height: 100px;
        margin-top: -100px;
        text-align: center;
        background: #999;
    }
</style>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">

            <div class="jumbotron">
                <h1>
                    there was a menu
                </h1>
            </div>
        </div>
        <p>

        <form name="course" id="course" action="<@spring.url "/admin/courses/save" />" method="post">

            <div class="container">
                <h3><@spring.message "create.message"/></h3>
                <hr width="100%">
                <!--Course name & Author-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">

                            <label for="name" style="text-align: right">
                            <@spring.message "courses.name"/>&nbsp; </label>
                        <@spring.formInput "course.name" />
                        <@spring.showErrors "<br>" />


                        </div>

                        <div class="span7">

                            <label for="course.author.firstName">
                            <@spring.message "courses.author"/> </label>
                        <@spring.formInput "course.author.firstName" />
                        <@spring.formInput "course.author.lastName" />
                        <@spring.showErrors "<br>" />


                        </div>
                    </div>
                </div>

                <!--Category & Email-->

                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">

                            <label for="type" style="text-align: right">
                            <@spring.message "courses.category"/>   </label>
                        <@spring.bind "categories" />
                        <@spring.formSingleSelect "course.type", categories, " " />


                        </div>

                        <div class="span7">

                            <label for="course.author.email">
                            <@spring.message "courses.email"/>&nbsp; </label>
                        <@spring.formInput "course.author.email"/>
                        <@spring.showErrors "<br>" />


                        </div>
                    </div>
                </div>

                <!--startDate & Tags-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">

                            <label for="startDate" style="text-align: right">
                            <@spring.message "courses.startDate" /> </label>
                        <@spring.formInput "course.startDate" />
                        <@spring.showErrors "<br>" />


                        </div>

                        <div class="span7">

                            <label for="course.tags">
                            <@spring.message "courses.tags"/> &nbsp; </label>
                        <@spring.formInput "course.tags" />
                        <@spring.showErrors "<br>" />


                        </div>
                    </div>
                </div>

                <!--endDate-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">

                            <label for="endDate" style="text-align: right">
                            <@spring.message "courses.endDate"/> </label>
                        <@spring.formInput "course.endDate" />
                        <@spring.showErrors "<br>" />


                        </div>
                    </div>
                </div>

                <!--Create & Cancel-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">
                        </div>

                        <div class="span6" style="text-align: right">

                            <input type="submit" value="Create"/> or <a
                                href="<@spring.url "/admin/courses" />">Cancel</a>

                        </div>
                    </div>
                </div>

            </div>
        </form>
        </p>
    </div>
</div>

<div class="footer">
    Copyright 2014 Exigen Services, Ltd. All rights reserved E-mail: ukraine@exigenservices.com
</div>
</body>
</html>
