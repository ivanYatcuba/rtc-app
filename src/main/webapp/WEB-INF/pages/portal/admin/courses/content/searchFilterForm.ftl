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
    <div class="row" style="text-align: right">
        <div class="col-md-11" style="text-align: right">
            <input id="searchButton" type="submit" class="btn btn-primary" value="Search"/>
            <a style="margin: 10px" href="<@spring.url "/admin/course"/>" class="btn btn-default">Reset</a>
        </div>
    </div>
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
