<div class="row">
    <div class="col-md-6">
    <@spring.formItem "course.name" "class=\"required\""/>
        <div>
            <label for="type"><@spring.message "course.category" /></label>
        <@spring.bind "categories" />
        <@spring.formSingleSelect "course.type", categories, "class=\"required\""/>
        </div>
    <@spring.formItem "course.capacity", 'onkeydown="return isNumber(event);" class="input-small required" size="2" maxlength="2" ' />
    </div>

    <div class="col-md-6">
    <@spring.formItem "course.startDate" 'class="input-medium required"' "datepiker"/>
            <@spring.formItem "course.endDate" 'class="input-medium required"' "datepiker"/>
            <@spring.formItem "course.tags" "" "tag"/>
    </div>

</div>
<hr>
<div class="row">
    <div class="col-md-6">
    <@spring.formItem "course.author.lastName"  "class=\"required\""/>
        <@spring.formItem "course.author.firstName"  "class=\"required\""/>  </div>
    <div class="col-md-6">
    <@spring.formItem "course.author.email"  "class=\"required\""/>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-11">
        <label for="description"><@spring.message "course.description"/> </label>
    <@spring.formTextarea "course.description" 'style="width:79%;" rows="3" maxlength="255" id=\"description\" class=\"required\"'/>
    </div>
</div>
<hr>
<div class="row">
    <label><@spring.message "course.status"/></label>&nbsp;${course.status}
</div>
<script>
    function isNumber(event) {
        if (event) {
            var charCode = (event.which) ? event.which : event.keyCode;
            if (charCode != 190 && charCode > 31 &&
                    (charCode < 48 || charCode > 57) &&
                    (charCode < 96 || charCode > 105) &&
                    (charCode < 37 || charCode > 40) &&
                    charCode != 110 && charCode != 8 && charCode != 46)
                return false;
        }
        return true;
    }
</script>
<@spring.formValidation formName="course" jsonRules="${validationRules}"/>




