<div class="row">
    <div class="col-md-6">
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
    <@formMacro.rtcFormTextInput  "user.surname" "user.surname" "required" />
    <@formMacro.rtcFormTextInput  "user.name" "user.name" "required" />
    <@formMacro.rtcFormTextInput  "user.middleName" "user.middleName" />
    <@formMacro.rtcFormDateField  "user.birthDate" "user.birthDate" "input-medium required" />
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
                    <input type="file" accept="image/*"  onchange="showMyImage(this)"
                           name="uploadPhoto" id="uploadPhoto" class="upload"><br/>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>

<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput  "user.email" "user.email" />
        <@formMacro.rtcFormSingleSelect "user.role" "user.authorities" roles "required" "" "user.role."/>
    </div>
    <div class="col-md-6">
        <@formMacro.rtcFormPasswordInputWithCheckbox   "user.password" "user.password" "required"/>
    </div>
</div>
<hr/>

<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput  "user.city" "user.city" />
    </div>
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput  "user.phone" "user.phone" "required" />
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

<div class="row">
    <div class="col-md-12">
        <@formMacro.rtcFormTextarea  "user.note" "user.note" "required field input-xlarge field col-lg-12" "rows=\"3\"
        maxlength=\"255\"
        width='100%'"/>
    </div>
</div>

<hr/>

<div class="row">
<div class="col-md-6">
        <input id="ifActive" name="ifActive" type="checkbox" <#if user.isActive()> checked = "checked" </#if>>
            <@spring.message "user.active"/>
</div>
</div>

<hr/>
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

        if ((ext != "jpg")) {fileInput.empty; return;}

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

