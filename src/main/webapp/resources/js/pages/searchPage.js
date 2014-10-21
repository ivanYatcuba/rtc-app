function SearchPage(settings) {
    var self = this;

    self.urlMap = settings.urlMap;
    self.menuMap = settings.menuMap;

    self.doSearch = function (page) {
        var activeForm = $(".activeForm");
        var activeFormId=activeForm.attr('id');
        var gourl = self.urlMap[activeFormId];
//        alert(gourl);
        if(!activeForm || activeForm.size() > 1 ) {
            return;
        }
//
        page = page || 1;
        $.ajax({
            type: "POST",
//            url: "<@spring.url "+self.urlMap[activeFormId]+"/>",
            url: gourl,
            data: $("#activeFormId :input").serialize()+"&page="+page,//{page: page}, //Id -??
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
                alert(self.urlMap[activeFormId]);
                alert("error");
//                var err = eval("(" + xhr.responseText + ")");
//                alert(err.Message);
            }
        });
    };

    self.doReset = function () {
        self.emptyTable();

        $(".activeForm input, textarea").val("");

        $(".activeForm ul#tagsTag li.tagit-choice").remove();

        $(".activeForm select option:selected").removeAttr("selected")

    };

    self.showFilterForm = function (menuId) {
//        alert(menuId+"   "+self.menuMap[menuId]);
        self.emptyTable();
        $("#searchButtons").show();
        $(".filterForm").hide();
        $(".navMenuItem").removeClass("active");
        $("#"+menuId).addClass("active");
        $(".activeForm").removeClass("activeForm").hide();
        $(self.menuMap[menuId]).addClass("activeForm").show();
       };

    self.emptyTable = function () {
        $("#searchTable").empty();
    };
}