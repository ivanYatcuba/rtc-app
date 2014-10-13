<@spring.formHiddenInput "user.code" />
<@spring.formHiddenInput "user.registerDate" />
<div class="row" xmlns="http://www.w3.org/1999/html">

    <div class="col-md-6">Email
        <!--Email-->
    <@rtcmacros.formItem  "user.email" "class=required"/>
    </div>


    <div class="col-md-6">
        <!--Password-->
    <@rtcmacros.formItem "user.password" "id=password class=required" "password"/>
        <div class="controls">
            <label for="check"></label>
            <input id="check"
                   onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
                   name="fff" type="checkbox" value="false"
                   style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
        </div>
    </div>
</div>
<hr/>
<div class="row">
    <div class="col-md-6">
        <!--Gender-->
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="user.gender"><@spring.message "user.gender"/></label>

            <div class="col-md-4" style="padding-top: 5px">
            <@spring.bind "user.gender"/>
                <input type="radio" name="gender" id="optionsRadios1"
                       value="Male" checked
                       style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
                <input type="radio" name="gender" id="optionsRadios2"
                       value="Female"
                       style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>
            </div>
        </div>
        <!--Last Name-->
    <@rtcmacros.formItem  "user.surname" "class=\"required\""/>
        <!--Middle Name -->
    <@rtcmacros.formItem  "user.middleName" />
    </div>


    <div class="col-md-6">
        <div class="form-group">
            <div class="col-md-6" style="padding-top: 25px">
            </div>
        </div>
        <!--First Name-->
        <div class="row"></div>
    <@rtcmacros.formItem  "user.name" "class=\"required\""/>
        <!--Birth Date-->
    <@rtcmacros.formItem "user.birthDate"  'class="input-medium required"' "datepiker"/>
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--City-->
    <@rtcmacros.formItem  "user.city" />
    </div>
    <div class="col-md-6">
        <!--Phone-->
    <@rtcmacros.formItem  "user.phone" "class=\"required\""/>
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--University-->
    <@rtcmacros.formItem  "user.university" />
        <!--Speciality-->
    <@rtcmacros.formItem  "user.faculty" />

    </div>

    <div class="col-md-6">
        <!--Faculty-->
    <@rtcmacros.formItem  "user.speciality" />
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--Languages-->
    <@rtcmacros.formItem "user.programmingLanguages" />
    </div>

    <div class="col-md-6">
        <!--English-->
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="english"><@spring.message "user.english"/></label>

            <div class="col-md-4">
            <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;" class=\"required\"'/>
            </div>
        </div>
    </div>
</div>

<hr/>

<!--Note-->
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="note"><@spring.message "user.note"/></label>

            <div class="col-md-9">    <@spring.formTextarea "user.note"
            'style="width:100%;" rows="3" maxlength="255" id=\"note\" class=\"required\"' /> </div>
        </div>
    </div>
</div>

<@rtcmacros.formValidation formName="user" jsonRules="${validationRules}"/>
<script type="text/javascript" charset="utf8"
        src="<@spring.url'/resources/js/pages/userMailValidation.js'/>"></script>
<script>
    $(function () {
        addMailValidation("<@spring.url "/mailExist/" />", "${user.email!""}")
    });
</script>

