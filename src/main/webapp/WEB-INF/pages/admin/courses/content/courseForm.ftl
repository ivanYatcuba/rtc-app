    <!--Course name & Author-->
    <div class="row-fluid span12" >

            <div class="span6" >
                <@spring.formItem "course.name"/>
                <p><label for="type">
                <@spring.message "courses.category"/>   </label>
                <@spring.bind "categories" />
                <@spring.formSingleSelect "course.type", categories, " "/></p>
                <p><@spring.formItem "course.startDate" "datepiker" 'class="input-medium"'/></p>
                <p><@spring.formItem "course.endDate" "datepiker" 'class="input-medium"'/></p>
            </div>

            <div class="span6">
                <p><label for="course.author.firstName">
                <@spring.message "courses.author"/> </label>
                <@spring.formInput "course.author.firstName" 'class="input-medium"'/>
                <@spring.formInput "course.author.lastName" 'class="input-medium"'/>
                <@spring.showErrors "<br>" /></p>
                <p><@spring.formItem "course.author.email"/></p>
                <p> <@spring.formItem "course.tags" "tags" 'class="input-medium"'/></p>
            </div>

    </div>


