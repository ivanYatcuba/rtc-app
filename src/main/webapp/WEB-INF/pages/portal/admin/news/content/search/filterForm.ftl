<#import "../../../../../fieldMacro.ftl" as formMacro />
<h3><@spring.message "news.filter.form.header"/></h3>
<div class="form-horizontal">
<div class="row">
    <div class="col-md-6">
    <@formMacro.rtcFormTextInput "filterNews.title" "filterNews.title"/>
    </div>
    <div class="col-md-6">
    <@formMacro.formDateSearch "filterNews.dateMoreLessEq", "filterNews.createDate"/>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label class="control-label col-md-3" for="autoSelectInput">
                <@spring.message "filterNews.newsAuthor"/>
            </label>
            <div class="col-md-5">
                <input type="text" class="form-control" id="autoSelectInput"/>
            </div>
        </div>
        <@spring.formHiddenInput "filterNews.authorId" />
    </div>

    <div class="col-md-6">
    <@formMacro.rtcFormSingleSelect "filterNews.status", "filterNews.status", statuses, "", "", "", {"" : "All"}/>
    </div>
</div>
</div>
<hr/>
<div class="row" style="text-align: right">
    <div class="col-md-5">
    </div>
    <div class="col-md-5" style="text-align: right"> <input type="submit" id="searchButton" class="btn btn-primary" value="Search"/>
        <a class="btn btn-default" href="<@spring.url "/admin/news" />">Reset</a>
    </div>
</div>
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
            }
        });
        $("#autoSelectInput").on("autocompleteselect", function(event,ui){
            var selectedValue = (ui.item.label);
            var authorId = mapAdminDataId[selectedValue];
            $("#authorId").attr("value",authorId);
        })
        $("#autoSelectInput").on("change", function(){
                $("#authorId").attr("value",null);
        })
    });
</script>
