<h3><@spring.message "news.filter.form.header"/></h3>
<div class="row">
    <div class="col-md-6 form-group">
    <@rtcmacros.formItem "filterNews.title"/>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <label class="control-label col-md-2"><@spring.message "news.filterNews.message.created"/></label>
            <@rtcmacros.formSingleSelect "filterNews.dateMoreLessEq",["=", "<", ">"]/>
            <@rtcmacros.formDatepicker "filterNews.createDate"/>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6 form-group">
        <div class="form-group">
            <label class="control-label col-md-2"><@spring.message "filterNews.newsAuthor"/></label>
            <div class="col-md-4">
                <input type="text" id="autoSelectInput"/>
            </div>
        </div>
            <@spring.formHiddenInput "filterNews.authorId" />
   </div>

    <div class="col-md-6">
        <@rtcmacros.formItem "filterNews.status" 'class="input-medium"' "singleSelect" statuses "NewsStatus."/>
    </div>
</div>
<hr/>
<div class="row">
    <div class="col-md-5 col-md-offset-6" style="text-align: right">
        <input type="submit" class="btn btn-primary" id="searchButton" value="Search"/> or <a
            href="<@spring.url "/admin/news" />">Reset</a>
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
