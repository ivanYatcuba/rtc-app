<@spring.formHiddenInput "user.code" />
<@spring.formHiddenInput "user.registerDate" />
<@spring.formHiddenInput "user.photo" />
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
    <@formMacro.rtcFormRadioButtons "user.gender" "user.gender", ["Male", "Female"] "required", "", "${user.gender}" />
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
    <@formMacro.rtcFormDateField  "user.birthDate" "user.birthDate" "input-normal required" "style='width: 60%'"/>
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
    <@formMacro.rtcFormSingleSelect "user.english" "user.english",  ["Basic", "Intermediate", "Advanced"] "required" "" />
    </div>
</div>

<hr/>

<!--Note-->
<div class="row">
    <div class="row">
        <div class="col-md-6">
        <@formMacro.rtcFormTextarea  "user.note" "user.note" "required" "rows='3'
        maxlength='255'
        style='width: 370%'"/>
        </div>
    </div>
</div>
