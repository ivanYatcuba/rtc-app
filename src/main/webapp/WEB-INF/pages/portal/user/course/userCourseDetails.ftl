<#import "../../../fieldMacro.ftl" as formMacro />
<#import "../layout/layout.ftl" as layout/>

<@layout.layout>
<h4><strong><@spring.message "course.details"/></strong></h4>
<div class="form-horizontal">
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormLabelOut "course.name" "${course.name}"/>
        </div>
        <div class="col-md-6">
            <@formMacro.rtcFormLabelOut "course.startDate" "${course.startDate?date?string('dd-MMM-yyyy')}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormLabelOut "course.category" course.types "courses.types."/>
        </div>
        <div class="col-md-6">
            <@formMacro.rtcFormLabelOut "course.endDate" "${course.endDate?date?string('dd-MMM-yyyy')}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormLabelOut "course.capacity" "${course.capacity}"/>
        </div>
        <div class="col-md-6">
            <#if course.tags?? && course.tags?size-1 != -1>
            <@formMacro.rtcFormLabelOut "course.tags" course.tags/>
        <#else>
                <@formMacro.rtcFormLabelOut "course.tags" "This course doesn't have any tags!"/>
            </#if>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <div class="row">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.experts" course.experts/>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <div class="row">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "course.description" "${course.description}"/>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <div class="row">
        <div class="col-md-6">
            <div>
                <label style="float: left;width: 13em;text-align: right;margin-bottom: 10px; margin-right: 15px">
                    <@spring.message "course.status"/>&nbsp</label>
            </div>
            <#if "${course.status}" == "DRAFT"> <@formMacro.rtcColorLabel "${course.status}" "label-warning" "course.status."/></#if>
            <#if "${course.status}" == "PUBLISHED"><@formMacro.rtcColorLabel "${course.status}" "label-success" "course.status."/> </#if>
            <#if "${course.status}" == "ARCHIVED"> <@formMacro.rtcColorLabel "${course.status}" "label-default" "course.status."/> </#if>
        </div>
        <div class="col-md-6">
            <div class="col-md-3"></div>
                <@formMacro.capacityIndicator course.acceptedOrders  course.capacity/>
                &nbsp;<span data-toggle="tooltip" data-placement="bottom" title="<@spring.message 'course.appliedCapacity'/>">${course.acceptedOrders}</span>/
                <span data-toggle="tooltip" data-placement="bottom" title="<@spring.message 'course.totalCapacity'/>">${course.capacity}</span><br/>&nbsp;
            </div>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <#if "${course.status}" == "ARCHIVED">
        <@formMacro.rtcCancelButton "/user/courses"/>
    <#else>
        <div class="span2" style="text-align: right">
            <#if !course.isCurrentUserAssigned()>
                <input onclick="popUpShow()" class="btn btn-primary" value="Apply order"/>
            </#if>
            <a class="btn btn-default" href="/user/courses">Cancel</a>
        </div>
    </#if>
</div>

<div class="modal" style="top: 15%; left: 1%" id="selectRoleModal" tabindex="-1" role="dialog" aria-labelledby="selectRoleModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cancel</span></button>
                <h4 class="modal-title">Apply order</h4>
            </div>
            <div class="modal-body">
                <@formMacro.rtcFormRadioButtons "course.selectRole" "course.types" courseTypes />
            </div>
            <div class="modal-footer">
                <form id="publishCourseForm" name="publishCourseForm" method="post">
                    <input type="hidden" id="courseCode" name="courseCode" value="${course.code}"/>
                    <button type="button" class="btn btn-primary" id="applyBtn">Apply</button>
                    <button type="button" class="btn btn-default" id="cancelBtn">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function popUpShow() {
        $('#selectRoleModal').modal('show')
    }
    function popUpHide() {
        $('#selectRoleModal').modal('hide');
    }

    $("#applyBtn").on("click", function(event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "<@spring.url"/user/courses/sendOrder/"/>",
            data: {courseCode: $("#courseCode").val(), position: $("[name='types']:checked").val()},

        })
        popUpHide();
    })

    $("#cancelBtn").on("click",function(event) {
        event.preventDefault();
        popUpHide();
    })
</script>

</@layout.layout>





