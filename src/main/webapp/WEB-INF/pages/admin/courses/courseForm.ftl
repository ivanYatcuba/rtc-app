
    <!--Course name & Author-->
    <div class="row-fluid span12">
        <div class="container">
            <div class="span5">
            <@spring.formItem "course.name" />
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
            <@spring.formItem "course.author.email" />
            </div>
        </div>
    </div>

    <!--startDate & Tags-->
    <div class="row-fluid span12">
        <div class="container">
            <div class="span5">
            <@spring.formItem "course.startDate" "datepiker"/>
            </div>

            <div class="span7">
            <@spring.formItem "course.tags" "tags"/>
            </div>
        </div>
    </div>

    <!--endDate-->
    <div class="row-fluid span12">
        <div class="container">
            <div class="span5">
            <@spring.formItem "course.endDate" "datepiker"/>
            </div>
        </div>
    </div>