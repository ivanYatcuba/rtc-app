<form class="form-horizontal" name="courseFilter" role="form"
      action="<@spring.url "/admin/course/filter"/>" method="get">
    <div class="row">
        <div class="col-md-6">
        <@rtcmacros.formItem "filterCourse.name"/>

        <@spring.bind "filterCourse.types"/>
            <div class="form-group">
                <label class="control-label col-md-2"
                       for="types"><@spring.message "filterCourse.types"/></label>

                <div class="col-md-4">
                    <@rtcmacros.formMultiSelect "filterCourse.types", categories/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2"
                       for="addExpertH"><@spring.message "filterCourse.experts"/></label>

                <div class="col-md-8">
                    <div id="experts"></div>
                    <a id="addExpertH" href="#" onclick="addExpert()">Add
                        Expert</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
        <#--<@rtcmacros.formItem "filterCourse.startDate" 'class="input-small"' "datepiker" />-->
            <label class="control-label col-md-2"><@spring.message "filterCourse.startDate"/></label>
            <@rtcmacros.formSingleSelect "filterCourse.dateMoreLessEq", ["=", "<", ">"], 'style="background-color: #FFFACD;"'/>
            <@rtcmacros.formDatepicker "filterCourse.startDate"/>
           </div>
        <@rtcmacros.formItem "filterCourse.status" 'class="input-medium"' "singleSelect" statuses/>
        <@rtcmacros.formItem "filterCourse.tags" "" "tag" />
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Search"/> or <a
                href="<@spring.url "/admin/course" />">Reset</a>
        </div>
    </div>
</form>

<script src="<@spring.url'/resources/js/pages/courseForm.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
<script>
    $(function () {
        $("#courseFilter").validate();
        prepareCourseFormPage("<@spring.url "/admin/user/expertUsers"/>");
    <#if filterCourse.experts??>
        <#assign i = 0>
        <#list  filterCourse.experts as f>
            addExpert();
            setFieldSelection(${i}, "${f.name}" + " " + "${f.surname}" + " " + "${f.email}");
            <#assign i = i+1>
        </#list>
    </#if>
    });
</script>
