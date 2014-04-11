<div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6" >
            <@spring.formItem "course.name"/>
            <div><label for="type">
            <@spring.message "course.category"/>   </label>
            <@spring.bind "categories" />
            <@spring.formSingleSelect "course.type", categories, " "/></div>
            <label><@spring.message "course.capacity"/> </label>
            <@spring.formInput "course.capacity", 'onkeydown="return isNumber(event);" class="input-small" size="2" maxlength="2" ' />
        </div>

        <div class="span5">
            <@spring.formItem "course.startDate" "datepiker" 'class="input-medium"'/>
            <@spring.formItem "course.endDate" "datepiker" 'class="input-medium"'/>
            <@spring.formItem "course.tag" "tag"/>
        </div>

</div>
&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px; ">
    <div class="span6" >
        <div>
        <@spring.formItem "course.author.lastName" />
        <@spring.formItem "course.author.firstName" />  </div>

        <@spring.showErrors "<br>" />
    </div>
    <div class="span5">
        <@spring.formItem "course.author.email"/>
    </div>
</div>
&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <label><@spring.message "course.description"/> </label>
    <@spring.formTextarea "course.description" 'style="width:100%;" rows="3" maxlength="255"'/>
</div>
&NonBreakingSpace;
<hr>
<div class="row-fluid span12" style="margin-left: 1px">
    <@spring.message "course.status"/>&nbsp;${course.status}
</div>
<script>
    function isNumber(event) {
        if (event) {
            var charCode = (event.which) ? event.which : event.keyCode;
            if (charCode != 190 && charCode > 31 &&
                    (charCode < 48 || charCode > 57) &&
                    (charCode < 96 || charCode > 105) &&
                    (charCode < 37 || charCode > 40) &&
                    charCode != 110 && charCode != 8 && charCode != 46 )
                return false;
        }
        return true;
    }
</script>


