<#import "../../../../fieldMacro.ftl" as FormMacro />

<style>
    label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 10px;
    }
</style>

<h4 class="page-header"><@spring.message "user.details"/></h4>
<div style="width: 85%">
<div class="row">
    <div class="col-md-6">

    <div class="col-md-12">
    <@formMacro.rtcFormRadioButtons "user.gender" "user.gender", ["Male", "Female"] "required",  "", "${user.gender}"  "disabled"/>
    </div>

    <div class="col-md-12">
       <@formMacro.rtcFormLabelOut "user.surname" "${user.surname}" "" "col-md-5"/>
    </div>

    <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.name" "${user.name}" "" "col-md-5"/>
    </div>

        <div class="col-md-12">
       <@formMacro.rtcFormLabelOut "user.middleName" "${user.middleName}" "" "col-md-5"/>
            </div>

        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.birthDate" "${user.birthDate?string('dd-MMM-yyyy')}" "" "col-md-5"/>
        </div>


    </div>
    <div>

        <div>
        <#if user.photo??>
            <img id="Img" src="/PathToPhotos/${user.photo}"  class="img-circle"/>
        <#else>
            <img src = "<@spring.url '/resources/images/errorCat.jpg'/>"  class="img-circle">
        </#if>
        </div>
    </div>
  </div>

<hr>

<div class="row">

    <div class="col-md-6">
     <@FormMacro.rtcFormLabelOut "user.email" "${user.email}" "" "col-md-5"/>
    </div>

    <div class="col-md-6">
        <@FormMacro.rtcFormLabelOut "user.role" user.authorities "user.role" "col-md-5"/>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <@FormMacro.rtcFormLabelOut "user.city" "${user.city}" "" "col-md-5"/>
    </div>

    <div class="col-md-6">
        <@FormMacro.rtcFormLabelOut "user.phone" "${user.phone}" "" "col-md-5"/>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-md-6">

        <div class="col-md-12">
        <@FormMacro.rtcFormLabelOut "user.university" "${user.university}" "" "col-md-5"/>
        </div>


        <div class="col-md-12">
        <@FormMacro.rtcFormLabelOut "user.faculty" "${user.faculty}" "" "col-md-5"/>
         </div>

   </div>

    <div class="col-md-6">
    <@FormMacro.rtcFormLabelOut "user.speciality" "${user.speciality}" "" "col-md-5"/>
    </div>
</div>

<hr>

<div class="row">

    <div class="col-md-6">
            <@FormMacro.rtcFormLabelOut "user.programmingLanguages" user.programmingLanguages  "" "col-md-5"/>
    </div>

    <div class="col-md-6">
      <@FormMacro.rtcFormLabelOut "user.english" "${user.english}" "" "col-md-5"/>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-md-6">
    <@FormMacro.rtcFormLabelOut "user.note" "${user.note}" "" "col-md-5"/>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label class="col-md-5"><@spring.message "user.status"/>:&nbsp</label>
            <div>
                <p class="form-control-static">
                <#if user.status=="ACTIVE">
                    <@formMacro.rtcColorLabel "${user.status}" "label-success" "user.status."/>
                <#elseif user.status=="FOR_REMOVAL">
                    <@formMacro.rtcColorLabel "${user.status}" "label-danger" "user.status."/>
                <#elseif user.status=="INACTIVE">
                    <@formMacro.rtcColorLabel "${user.status}" "label-warning" "user.status."/>
                </#if>
                </p>
            </div>
        </div>
             <@FormMacro.rtcFormLabelOut "user.registerDate" "${user.registerDate? string['dd-MMM-yyyy']}" "" "col-md-5" />
    </div>

</div>

<hr>
<#if user.status == "FOR_REMOVAL">
    <@formMacro.rtcSubmitDoOrCancel "coursesPage.action.edit" "/admin/user/${user.code}/edit" "Cancel" "/admin/user" "disabled"/>
<#else>
    <@formMacro.rtcSubmitDoOrCancel "action.edit" "/admin/user/userPage/editPage/${user.code}" "Cancel" "/admin/user/viewAll"/>
</#if>
</div>
