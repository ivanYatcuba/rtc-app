<#import "../../../../fieldMacro.ftl" as formMacro />

<style>
    label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 10px;
    }
</style>

<div style="width: 85%">
    <div>
        <h4 class="page-header"><@spring.message "course.details"/></h4>
    </div>
    <br>

    <div class="row">
        <div class="col-md-12">
              <@formMacro.rtcFormLabelOut "course.name" "${course.name}"/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.category" course.types/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.capacity" "${course.capacity}"/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.startDate" "${course.startDate?date?string('dd-MMM-yyyy')}"/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.endDate" "${course.endDate?date?string('dd-MMM-yyyy')}"/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.tags" course.tags/>
        </div>
    </div>

    &NonBreakingSpace;
    <hr>
    <div class="row">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.experts" course.experts/>
        </div>
    </div>

    &NonBreakingSpace;
    <hr>
    <div class="row">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.description" "${course.description}"/>
        </div>
    </div>

    &NonBreakingSpace;
    <hr>
    <div class="row">
        <div class="col-md-12">
            <label><@spring.message  "course.status"/>&nbsp</label>
            <#if "${course.status}" == "DRAFT"> <@formMacro.rtcColorLabel "${course.status}" "label-warning" "course.status."/></#if>
            <#if "${course.status}" == "PUBLISHED"><@formMacro.rtcColorLabel "${course.status}" "label-success" "course.status."/> </#if>
            <#if "${course.status}" == "ARCHIVED"> <@formMacro.rtcColorLabel "${course.status}" "label-default" "course.status."/> </#if>
            <#--<@formMacro.rtcFormLabelOut "course.status" "${course.status}"/>-->
        </div>


        <div class="col-md-12">
            <#if course.publishDate??>
                <@formMacro.rtcFormLabelOut "course.publishDate" "${course.publishDate?date?string('dd-MMM-yyyy')}"/>
            <#else>
                <@formMacro.rtcFormLabelOut "course.publishDate" " "/>
            </#if>
        </div>
    </div>

    &NonBreakingSpace;
    <hr>
    <div class="row">
            <@formMacro.rtcSubmitDoOrCancel "coursesPage.action.edit" "/admin/course/${course.getCode()}/update"
                "coursesPage.action.cancel" "/admin/course"/>
    </div>
</div>