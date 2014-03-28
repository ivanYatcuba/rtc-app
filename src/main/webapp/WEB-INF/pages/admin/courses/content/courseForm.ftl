    <div class="row-fluid span12" style="margin-left: 1px">

            <div class="span6" >
                <@spring.formItem "course.name"/>
                <div><label for="type">
                <@spring.message "courses.category"/>   </label>
                <@spring.bind "categories" />
                <@spring.formSingleSelect "course.type", categories, " "/></div>
                <@spring.formItem "course.startDate" "datepiker" 'class="input-medium"'/>
                <@spring.formItem "course.endDate" "datepiker" 'class="input-medium"'/>
            </div>

            <div class="span5">
                <div><label for="course.author.firstName">
                <@spring.message "courses.author"/> </label>
                <@spring.formInput "course.author.firstName" 'class="input-medium"'/>
                <@spring.formInput "course.author.lastName" 'class="input-medium"'/>
                <@spring.showErrors "<br>" /></div>
                <@spring.formItem "course.author.email"/>
                <@spring.formItem "course.tags" "tags"/>
            </div>

    </div>


