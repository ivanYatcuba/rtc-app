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
    <@formMacro.rtcFormTextInput  "user.surname" "user.surname" "required" />
    <@formMacro.rtcFormTextInput  "user.middleName" "user.middleName" "required" />
    <@formMacro.rtcFormTextInput  "user.name" "user.name" "required" />
    <@formMacro.rtcFormTextInput  "user.birthDate" "user.birthDate" "input-medium required" />
    </div>

    <div class="col-md-6">
        <div>
        <#if user.photo??>
            <img id="photo" src="/PathToPhotos/${user.photo}"  alt="image" class="round-image"/>
        <#else>
            <img id="photo"  src="<@spring.url '/resources/images/errorCat.jpg'/>" alt="image" class="round-image"/>
        </#if>
        </div>


        <div class="row">
            <div class="col-md-6">
                <div style="margin-left: 10px" class="fileUpload btn-link">
                    <span><u>Upload</u></span>
                    <input type="file" accept="image/*"  onchange="showMyImage(this)" name="uploadPhoto" id="uploadPhoto" class="upload" accept="image/*"  onchange="showMyImage(this)"  ><br/>
                </div>
            </div>
        </div>

    </div>

</div>

<hr/>

<div class="row">

    <div class="col-md-6">
        <!--Email-->
    <@formMacro.rtcFormTextInput  "user.email" "user.email" "required" />

        <!--Authorities-->
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="selectedRole"><@spring.message "user.role"/></label>

            <div class="col-md-4">
                <select id="selectedRole" name="selectedRole" class="required">
                <#list roles as role>
                    <option value="${role}">${role.roleViewName}</option>
                </#list>
                </select>
            </div>
        </div>
    </div>


    <div class="col-md-6">
        <@formMacro.rtcFormPasswordInputWithCheckbox   "user.password" "user.password" "required"/>
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

            <div class="col-md-10">
            <@spring.formTextarea
            "user.note" 'rows="3" maxlength="255"
            id=\"note\"
            class=\"required field col-md-10\"' />
            </div>
        </div>
    </div>
</div>

<hr/>

<div class="controls">
    <label for="check"></label>
    <input id="check"
           name="fff" type="checkbox" value="false"
           style="margin: 0px 0px 0px;">  <@spring.message "user.active"/>
</div>

<hr/>

<@rtcmacros.formValidation formName="userForm" jsonRules="${validationRules}"/>

<script type="text/javascript" charset="utf8"
        src="<@spring.url'/resources/js/pages/userMailValidation.js'/>"></script>
<script>
    $(function () {
        addMailValidation("<@spring.url "/mailExist/" />", "${user.email!""}")
    });
</script>

<script type="text/javascript">

    function showMyImage(fileInput) {
        var files = fileInput.files;

        var url = fileInput.value;
        var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();

        if ((ext != "jpg")) {return;}

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /image.*/;
            if (!file.type.match(imageType)) {
                continue;
            }
            var img=document.getElementById("photo");
            img.file = file;
            var reader = new FileReader();
            reader.onload = (function(aImg) {
                return function(e) {
                    aImg.src = e.target.result;
                };
            })(img);
            reader.readAsDataURL(file);
        }
    }
</script>

