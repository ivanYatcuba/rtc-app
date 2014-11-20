<#import "../../../../fieldMacro.ftl" as formMacro/>
<#import "../../../../datatables.ftl" as datatables/>
<h4><strong><@spring.message "user.search.result.page.header.search"/></strong></h4>
<div id="data">
    <table width="100%" class="table" style="margin-bottom: 5px" id="UserTable">
        <thead>
        <tr>
            <th><@spring.message "user.search.result.header.user"/></th>
            <th><@spring.message "user.search.result.header.email"/></th>
            <th><@spring.message "user.search.result.header.created.date"/></th>
            <th><@spring.message "user.search.result.header.status"/></th>
            <th></th>
        </tr>
        </thead>
    <#list users as user>
        <tr style="vertical-align: middle">
            <#if (user.name??) && (user.surname??) >
                <td style="width: 30%; vertical-align: middle">
                    <div class="row">
                        <div class="col-md-2">
                         <#if user.photo??>
                            <img id="Img" src="/PathToPhotos/${user.photo}"  class="avatar"/>
                        <#else>
                            <img id="Img" src = "<@spring.url '/resources/images/errorCat.jpg'/>"  class="avatar">
                        </#if>
                        </div>
                        <div class="col-md-10" style="padding-left: 22px; vertical-align: middle ">
                            <a href="<@spring.url"/admin/user/userPage/${user.code}"/>">  ${user.surname + " " + user.name } </a>
                            <br><font face="Aerial"><em>
                            <#list user.authorities as role>
                        ${role.name.roleViewName}
                        </#list>
                        </em></font>
                        </div>
                    </div>
                </td>
            <#else>
                <td style="vertical-align: middle">None</td>
            </#if>

            <#if (user.email)??>
                <div class="row">
                    <div class="col-md-6">
                        <td style="vertical-align: middle">${user.email}</td>
                    </div>
                </div>
            <#else>
                <td style="vertical-align: middle">None</td>
            </#if>

            <#if (user.registerDate)??>
                <td style="vertical-align: middle">${user.registerDate?string('dd-MMM-yyyy')}</td>
            <#else>
                <td style="vertical-align: middle">None</td>
            </#if>

            <#if (user.status)??>
                <td style="vertical-align: middle">
                    <#if (user.status)=="ACTIVE">
                        <@formMacro.rtcColorLabel "${user.status}" "label-success"  "user.status."/>
                    <#else>
                        <#if (user.status)=="FOR_REMOVAL">
                           <@formMacro.rtcColorLabel "${user.status}" "label-danger"  "user.status."/>
                        <#else>
                            <@formMacro.rtcColorLabel "${user.status}" "label-default"  "user.status."/>
                        </#if>
                    </#if>
                </td>
            <#else>
                <td style="vertical-align: middle">
                    <@rtcColorLabel "None" "label-default"/>
                </td>
            </#if>
            <td style="width: 15%; vertical-align: middle">
                <#if user.isForRemoval()>
                    <div class="btn-group">
                        <button class="btn btn-default" style="width: 100px" type="button" data-toggle="dropdown">
                            Action
                        </button>
                        <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" style="width: 112px" role="menu">
                            <li>
                                <a href="<@spring.url"/admin/user/restore/${user.code}"/>">Restore</a>
                            </li>
                        </ul>
                    </div>

                <#else>
                    <div class="btn-group">
                        <button class="btn btn-default" style="width: 100px" type="button" data-toggle="dropdown">
                            Action
                        </button>
                        <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>

                        <ul class="dropdown-menu" style="width: 112px role="menu">
                        <li>
                            <a href="#" onclick="javascript:PopUpShow('${user.code}')">Remove</a>
                        </li>
                        <li>
                            <#if user.isActive()>
                                <a href="<@spring.url"/admin/user/inactivate/${user.code}"/>">Inactivate</a>
                            <#else>
                                <a href="<@spring.url"/admin/user/activate/${user.code}"/>">Activate</a>
                            </#if>
                        </li>
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
        <form  class="inline-box" style="margin: 0px"  name="createUser" action="<@spring.url"/admin/user/createUser"/>"method="get">
            <button  class="btn btn-primary" type="submit">Create New</button>
        </form>
    </div>
    <div class="col-md-6" style="text-align: right">
    <@datatables.addPagination/>
    </div>
</div>

<!-- Modal -->
<div class="modal" style="top: 15%; left: 1%" id="removeUserModal" tabindex="-1" role="dialog" aria-labelledby="removeUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="removeUserModalLabel">Remove User</h4>
            </div>
            <div class="modal-body">
                Are you sure you want to remove User?
            </div>
            <div class="modal-footer">
                <form name="deleteUser" action="<@spring.url"/admin/user/remove/"/>" method="post">
                    <input type="hidden" id="userCode" name="userCode"/>
                    <button type="button" class="btn btn-default"  data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary" onClick="javascript:PopUpHide()">Remove</button>
                </form>
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">
    function PopUpShow(userCode) {
        $("#userCode").val(userCode);
        $('#removeUserModal').modal('show')
    }
    function PopUpHide() {
        $('#removeUserModal').modal('hide');
    }
</script>
