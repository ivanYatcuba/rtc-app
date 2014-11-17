<#import "../../../../fieldMacro.ftl" as formMacro />
<h4><strong><@spring.message "news.filter.form.header"/></strong></h4>
<div class="form-horizontal">
    <div class="row">
        <div class="col-md-6">
        <@formMacro.rtcFormTextInput "newsFilter.title" "newsFilter.title"/>
        </div>
        <div class="col-md-6">
        <@formMacro.formDateSearch "newsFilter.dateMoreLessEq", "newsFilter.createDate"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label class="control-label col-md-3" for="autoSelectInput">
                <@spring.message "newsFilter.newsAuthor"/>
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" id="autoSelectInput"/>
                </div>
            </div>
        <@spring.formHiddenInput "newsFilter.authorId" />
        </div>

        <div class="col-md-6">
        <@formMacro.rtcFormSingleSelect "newsFilter.status", "newsFilter.status", newsStatuses, "", "", "news.status.", {"" : "All"}/>
        </div>
    </div>
</div>
<hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
<#--<@formMacro.rtcSearchButtons "/admin/news"/>-->
<br>

<script>
    $(function() {
        var mapAdminDataId;
        var autoCompleteAuthors;
        $.ajax({
            type: "POST",
            url: "<@spring.url "/admin/user/getAdmins"/>",
            success: function(response){
                mapAdminDataId = response;
                autoCompleteAuthors = Object.keys(mapAdminDataId);
                $("#autoSelectInput").autocomplete({source: autoCompleteAuthors});

                $("#autoSelectInput").on("autocompleteselect", function(event,ui){
                    var selectedValue = (ui.item.label);
                    var authorId = mapAdminDataId[selectedValue];
                    $("#authorId").attr("value",authorId);
                })
                $("#autoSelectInput").on("keyup", function(){
                    if(!mapAdminDataId.hasOwnProperty($("#expertCode").value)){
                        $("#authorId").attr("value",null);
                    }
                })
            }
        });

    });
</script>
