<div class="row-fluid span12" style="margin-left: 1px">

    <div class="span6" >
            <div><label for="user.email">
            <@spring.message "user.email"/> </label>
            <@spring.formInput "user.email" 'style="background-color: #FFFACD;"'/></div>
    </div>

    <div class="span5">
                <div><label for="user.password">
                <@spring.message "user.password"/> </label>
                <@spring.formInput "user.password" 'style="background-color: #FFFACD;"'/></div>

                <label class="checkbox" style="margin-top: 0px;">
                    <input type="checkbox"> <@spring.message "user.showPassword"/>
                </label>
    </div>
</div>

    <div class="row-fluid span12" style="margin-left: 1px;">
            <hr width="72%">
    </div>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
            <div><label for="user.gender">
             <@spring.message "user.gender"/>
             <#--<input type="radio" path="user.gender" value="Male">
             <input type="radio" path="user.gender" value="Female">-->
             </label>  </div>

            <div><label for="user.surname">
            <@spring.message "user.surname"/> </label>
            <@spring.formInput "user.surname" 'style="background-color: #FFFACD;"'/></div>
            <@spring.formItem "user.middleName"/>
        </div>

        <div class="span5">

            <div><label for="user.name">
            <@spring.message "user.name"/> </label>
            <@spring.formInput "user.name" 'style="background-color: #FFFACD;"'/>
            </div>
            <div><label for="user.birthDate">
            <@spring.message "user.birthDate"/> </label>
            <@spring.formDatepicker "user.birthDate" 'style="background-color: #FFFACD;"'/>
            </div>
        </div>
    </div>

    <div class="row-fluid span12" style="margin-left: 1px;">
        <hr width="72%" >
    </div>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
            <@spring.formItem "user.city"/>
        </div>

        <div class="span5">
            <div><label for="user.phone">
            <@spring.message "user.phone"/> </label>
            <@spring.formDatepicker "user.phone" 'style="background-color: #FFFACD;"'/>
            </div>
        </div>
    </div>

    <div class="row-fluid span12" style="margin-left: 1px;">
        <hr width="72%">
    </div>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
            <@spring.formItem "user.university"/>
            <@spring.formItem "user.faculty"/>
        </div>

        <div class="span5">
            <@spring.formItem "user.speciality"/>
        </div>
    </div>

    <div class="row-fluid span12" style="margin-left: 1px;">
        <hr width="72%"">
    </div>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span11">

            <div><label for="user.note">
            <@spring.message "user.note"/> </label>
            <@spring.formInput "user.note" 'style="width:700px;"' />
            </div>
        </div>
    </div>
