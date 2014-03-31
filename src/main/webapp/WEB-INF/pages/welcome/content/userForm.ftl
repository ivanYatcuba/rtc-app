<div class="row-fluid span12" style="margin-left: 1px">

    <div class="span6" >
        <@spring.formItem "reg.email"/>

            <hr width="100%">
        <div><label for="user.gender">
        <@spring.message "user.gender"/> </label>
        <@spring.formRadioButtons "user.gender" /></div>
        <@spring.formItem "reg.lastName"/>
        <@spring.formItem "middleName"/>

            <hr width="100%">
        <@spring.formItem "user.city"/>

            <hr width="100%">
        <@spring.formItem "user.university"/>
        <@spring.formItem "user.faculty"/>

            <hr width="100%">
        <@spring.formItem "progLanguage"/>

    </div>

    <div class="span5">

        <@spring.formItem "reg.password"/>
        <!-- Show password -->
        <div><label for="user.showPassword">
        <@spring.message "user.showPassword"/> </label>
        <@spring.formCheckboxes "user.showPassword" /></div>

            <hr width="100%">
        <@spring.formItem "reg.firstName"/>
        <@spring.formItem "user.birthdate" "datepiker" 'class="input-medium"'/>

            <hr width="100%">
        <@spring.formItem "user.phone"/>

            <hr width="100%">
        <@spring.formItem "user.speciality"/>

            <hr width="100%">
        <@spring.formItem "user.english"/>

    </div>

</div>

<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span11">

        <div>
            <label for="reg.reason">
            <@spring.message "reg.reason"/> </label>
            <@spring.formInput "reg.reason" />
        </div>

    </div>
</div>