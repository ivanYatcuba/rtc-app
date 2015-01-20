<#import "../../../../datatables.ftl" as datatables/>
<#import "../../../../fieldMacro.ftl" as formMacro/>
<h4><strong><@spring.message "course.search.result.page.search"/></strong></h4>
<div id="data">
    <table width="100%" class="table" style="margin-bottom: 5px" id="CourseTable">
        <thead>
        <tr>
            <th><@spring.message "course.search.result.table.name"/></th>
            <th><@spring.message "course.search.result.table.expert"/></th>
            <th><@spring.message "course.search.result.table.term"/></th>
            <th><@spring.message "course.search.result.table.status"/></th>
            <th></th>
        </tr>
        </thead>
<#if courses?has_content>
        <#list courses as course>
            <tr style="vertical-align: middle">
                <td style="vertical-align: middle; width: 25%">
                    <a href="<@spring.url "/admin/course/view/${course.code}" />">${course.name}</a>
                    <p style="font-family: 'Times New Roman', Times, serif; font-style: italic">${course.types?join(", ")}</p>
                </td>
                <td style="vertical-align: middle;">
                    <#list course.experts as expert>
                        <p>&nbsp${expert.name}&nbsp${expert.surname}
                            &nbsp${expert.email}</p>
                    </#list>
                </td>
                <td style="vertical-align: middle; width: 25%">${course.startDate?string('dd-MMM-yyyy')}&nbsp;-&nbsp;${course.endDate?string('dd-MMM-yyyy')}</td>
                <td style="vertical-align: middle; width: 15%">
                        <#if "${course.status}" == "DRAFT"> <@formMacro.rtcColorLabel "${course.status}" "label-warning" "course.status."/></#if>
                        <#if "${course.status}" == "PUBLISHED"><@formMacro.rtcColorLabel "${course.status}" "label-success" "course.status."/> </#if>
                        <#if "${course.status}" == "ARCHIVED"> <@formMacro.rtcColorLabel "${course.status}" "label-default" "course.status."/> </#if>
                </td>

                <td style="width: 15%;vertical-align: middle">
                    <#if (course.status??) && (course.status == "DRAFT") >
                        <div class="btn-group">
                            <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenu1"
                                    data-toggle="dropdown">
                                Action</button>
                            <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                <li id="publicationLi" role="presentation"><a role="menuitem" tabindex="-1"onclick="javascript:PopUpShow('${course.code}')">Publish</a></li>
                            <#--<li id="deleteLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url "/admin/course/delete/${course.code}"/>">Remove</a></li>-->
                            </ul>
                        </div>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
</div>
<hr style="height: 1px; margin-top: 5px; border-top: 1px solid #ddd;">
<div class="row">
    <div class="col-md-6">
        <a  href="<@spring.url "/admin/course/create" />">
            <button class="btn btn-primary">Create New</button>
        </a>
    </div>
    <div class="col-md-6" style="text-align: right">
        <@datatables.addPagination/>
    </div>
</div>
<#else>
    <td>
        There are no courses. <a  href="<@spring.url "/admin/course/create"/>">Click here</a> to add.
    </td>
</#if>

<!-- Modal -->
<div class="modal" style="top: 15%; left: 1%" id="publishCourseModal" tabindex="-1" role="dialog" aria-labelledby="publishCourseModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cancel</span></button>
                <h4 class="modal-title" id="removeUserModalLabel">Publish Course</h4>
            </div>
            <div class="modal-body">
                The course will be published and visible for all users.
            </div>
            <div class="modal-footer">
                <form id="publishCourseForm" name="publishCourseForm" method="post">
                    <input type="hidden" id="courseCode" name="courseCode"/>
                    <button type="button" class="btn btn-default"  data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-default"  onClick="return ActionDeterminator(true);">Publish and Create news</button>
                    <button type="button" class="btn btn-primary" onClick="return ActionDeterminator(false);
                    ">Publish</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function PopUpShow(courseCode) {
        $("#courseCode").val(courseCode);
        $('#publishCourseModal').modal('show')
    }
    function PopUpHide() {
        $('#publishCourseModal').modal('hide');
    }
    function ActionDeterminator(createNews) {
        $.ajax({
            type: "POST",
            url: "<@spring.url"/admin/course/publish/"/>" + $("#courseCode").val() + "?ifCreateNews="+createNews,
            success: function () {
                searchPage.doSearch(${currentPage});
            }
        });
        PopUpHide();
        return true;
    }
</script>
