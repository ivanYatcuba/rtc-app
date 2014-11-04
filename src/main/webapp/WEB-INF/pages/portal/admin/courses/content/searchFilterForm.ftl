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
            <div class="form-group">
                <label class="control-label col-md-3" for="autoSelectInput">
                <@spring.message"filterCourse.experts"/>
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" id="autoSelectInput"/>
                </div>
            </div>
        <@spring.formHiddenInput "filterCourse.expertCode" />
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
    <@formMacro.rtcSearchButtons "/admin/course"/>
</div>

<script>
    $(function() {
        var mapAdminDataId;
        var autoCompleteAuthors;
        $.ajax({
            type: "POST",
            url: "<@spring.url "/admin/user/getExperts"/>",
            success: function(response){
                mapAdminDataId = response;
                autoCompleteAuthors = Object.keys(mapAdminDataId);
                $("#autoSelectInput").autocomplete({source: autoCompleteAuthors});

                $("#autoSelectInput").on("autocompleteselect", function(event,ui){
                    var selectedValue = (ui.item.label);
                    var authorId = mapAdminDataId[selectedValue];
                    $("#expertCode").attr("value",authorId);
                });
                $("#autoSelectInput").on("keyup", function(){
                    if(!mapAdminDataId.hasOwnProperty($("#expertCode").value)){
                        $("#expertCode").attr("value",null);
                    }
                });
            }
        });
    });
</script>
