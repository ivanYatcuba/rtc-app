<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<style>
    label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 10px;
    }
</style>
<h3 class="page-header"><@spring.message "user.details"/></h3><br/>

<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.email" "${user.email}" "" />
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.gender" "${user.gender}" "" />
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.surname" "${user.surname}" "" />
        </div>
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.middleName" "${user.middleName}" "" />
        </div>
    </div>
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.name" "${user.name}" "" />
        </div>
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.birthDate" "${user.birthDate}" "" />
        </div>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.city" "${user.city}" "" />
        </div>
    </div>
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.phone" "${user.phone}" "" />
        </div>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.university" "${user.university}" "" />
        </div>
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.faculty" "${user.faculty}" "" />
        </div>
    </div>
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.speciality" "${user.speciality}" "" />
        </div>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.programmingLanguages" user.programmingLanguages "" />
        </div>
    </div>
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.english" "${user.english}" "" />
        </div>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "user.english" "${user.note}" "" />
        </div>
    </div>
</div>

<hr>
<div class="row">
<@formMacro.rtcSubmitDoOrCancel "action.edit" "/user/profile/edit" "Cancel" "/user/search"/>
</div>
</@layout.layout>