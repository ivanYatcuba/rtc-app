<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
            <div><label for="user.email">
            * <@spring.message "user.email"/>: </label>
            <@spring.formInput "user.email" 'style="background-color: #FFFACD;"' />
            <@spring.showErrors "<br>"/></div>
    </div>

    <div class="span6">
                <div><label for="user.password">
                * <@spring.message "user.password"/>: </label>
                <input id="pass" class="st-input" type="password" style="background-color: #FFFACD;" /> <br>
                <input onchange="if ($('#pass').get(0).type=='password') $('#pass').get(0).type='text'; else $('#pass').get(0).type='password';" name="fff" type="checkbox" value="false" style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
                </div>
    </div>
</div>
<div class="row-fluid span12" style="margin-left: 1px;">
    <hr width="72%">
</div>

<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
        <div style="margin-bottom: 10px;"><label for="user.gender">
        <@spring.message "user.gender"/>:
            <input type="radio" path="user.gender" name="user.gender" value="male" style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
            <input type="radio" path="user.gender" name="user.gender" value="female" style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>
        </label>
        </div>
    </div>
</div>

<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
        <div><label for="user.surname">
        * <@spring.message "user.surname"/>: </label>
        <@spring.formInput "user.surname" 'style="background-color: #FFFACD;"'/>
        <@spring.showErrors "<br>"/></div>
        <@spring.formItem "user.middleName"/>
    </div>

    <div class="span6">

        <div><label for="user.name">
        * <@spring.message "user.name"/>: </label>
        <@spring.formInput "user.name" 'style="background-color: #FFFACD;"'/>
        <@spring.showErrors "<br>"/>
        </div>
        <div><label for="user.birthDate">
        * <@spring.message "user.birthDate"/>: </label>
        <@spring.formDatepicker "user.birthDate" 'style="background-color: #FFFACD;"'/>
        <@spring.showErrors "<br>"/>
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

    <div class="span6">
        <div><label for="user.phone">
        * <@spring.message "user.phone"/>: </label>
        <@spring.formDatepicker "user.phone" 'style="background-color: #FFFACD;"'/>
        <@spring.showErrors "<br>"/>
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

    <div class="span6">
        <@spring.formItem "user.speciality"/>
    </div>
</div>

<div class="row-fluid span12" style="margin-left: 1px;">
    <hr width="72%">
</div>

<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
    <@spring.formItem "user.progLanguages"/>
    </div>

    <div class="span6">
        <div><label for="user.english">
            * <@spring.message "user.english"/>: </label>
            <div class="controls">
                <select style="background-color: #FFFACD;">
                    <option>Basic</option>
                    <option>Pre-Intermediate</option>
                    <option>Intermediate</option>
                    <option>Upper Intermediate</option>
                    <option>Advanced</option>
                </select>
            </div>
        </div>
    </div>
</div>

    <div class="row-fluid span12" style="margin-left: 1px;">
        <hr width="72%"">
    </div>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span12">

            <div><label for="user.note">
            * <@spring.message "user.note"/>: </label>
            <@spring.formTextarea "user.note" 'style="width:700px; background-color: #FFFACD;"' />
            <@spring.showErrors "<br>"/>
            </div>
        </div>
    </div>
