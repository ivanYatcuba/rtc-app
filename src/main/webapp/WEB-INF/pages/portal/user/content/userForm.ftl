<@spring.formHiddenInput "user.code" />
<@spring.formHiddenInput "user.registerDate" />
<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormUserEmailInput  "user.email" "user.email" "required"/>
    </div>

    <div class="col-md-6">
        <@formMacro.rtcFormPasswordInputWithCheckbox   "user.password" "user.password" "required"/>
    </div>
</div>
<hr/>
<div class="row">
    <div class="col-md-6">
    <@formMacro.rtcFormCustomInput   "user.gender" "user.gender">
        <div style="padding-top: 5px">
            <@spring.bind "user.gender"/>
            <input type="radio" name="gender" id="optionsRadios1"
                   value="Male" checked
                   style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
            <input type="radio" name="gender" id="optionsRadios2"
                   value="Female"
                   style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>
        </div>
     </@formMacro.rtcFormCustomInput >

    <@formMacro.rtcFormTextInput  "user.surname" "user.surname" "required" />
    <@formMacro.rtcFormTextInput  "user.middleName" "user.middleName" />
    </div>


    <div class="col-md-6">
        <div class="form-group">
            <div class="col-md-6" style="padding-top: 25px">
            </div>
        </div>
        <div class="row"></div>
    <@formMacro.rtcFormTextInput  "user.name" "user.name" "required" />
    <@formMacro.rtcFormDateField  "user.birthDate" "input-medium required" />
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
    <@formMacro.rtcFormTextInput  "user.city" "user.city"/>
    </div>
    <div class="col-md-6">
    <@formMacro.rtcFormTextInput  "user.phone" "user.phone" "required"/>
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
    <@formMacro.rtcFormTextInput  "user.university" "user.university"/>
    <@formMacro.rtcFormTextInput  "user.faculty" "user.faculty"/>
    </div>

    <div class="col-md-6">
    <@formMacro.rtcFormTextInput  "user.speciality" "user.speciality"/>
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
    <@formMacro.rtcFormTextInput  "user.programmingLanguages" "user.programmingLanguages"/>
    </div>

    <div class="col-md-6">
    <@formMacro.rtcFormCustomInput   "user.english" "user.english">
        <div class="col-md-4">
            <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;" class=\"required\"'/>
        </div>
    </@formMacro.rtcFormCustomInput >
    </div>
</div>

<hr/>

<!--Note-->
<div class="row">
    <div class="col-md-12">
    <@formMacro.rtcFormTextarea  "user.note" "user.note" "required" "style=\"width:60%;\" rows=\"3\"
    maxlength=\"255\""/>
    </div>
</div>
