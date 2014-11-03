<#import "../../../../fieldMacro.ftl" as formMacro/>
<h4><strong><@spring.message "course.search.result.page.criteria"/></strong></h4>
<div class="form-horizontal">
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormTextInput "filterCourse.name" "filterCourse.name"/>
            <@formMacro.rtcFormMultiSelect "filterCourse.types" "filterCourse.types" categories "" "height: 65;"/>
        </div>
        <div class="col-md-6">
        <@formMacro.formDateSearch  "filterCourse.dateMoreLessEq" "filterCourse.startDate"/>
            <@formMacro.rtcFormSingleSelect "filterCourse.status" "filterCourse.status" statuses, "", "", "course.status.",  {"" : "All"}/>
            <@formMacro.rtcFormTextInput "filterCourse.experts" "filterCourse.experts"/>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <@formMacro.rtcSearchButtons "/admin/course"/>
</div>

<script>
    $(function() {
        var autoCompleteExperts;
        $.ajax({
            type: "POST",
            url: "<@spring.url "/admin/user/expertUsers"/>",
            success: function(response){
                autoCompleteExperts = response;
                $("#experts").autocomplete({source: autoCompleteExperts});
            }
        });
    });
</script>
