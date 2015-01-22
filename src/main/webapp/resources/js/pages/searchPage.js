function SearchPage(settings) {
    var self = this;
    var currSerializedFilter;

    self.urlMap = settings.urlMap;
    self.menuMap = settings.menuMap;

    function doUpdateTable(useCurrentFilter, page){
        var activeForm = $(".activeForm");
        var activeFormId = activeForm.attr('id');
        var gourl = self.urlMap[activeFormId];
        if(!activeForm || activeForm.size() > 1 ) {
            return;
        }

        if(useCurrentFilter)
            currSerializedFilter = $("#" + activeFormId + " input" + ", #" + activeFormId + " select").serialize();

        page = page || 1;
        $.ajax({
            type: "POST",
            url: gourl,
            data: currSerializedFilter+"&page="+page,
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
                //alert(self.urlMap[activeFormId]);
                //alert("error");
//                var err = eval("(" + xhr.responseText + ")");
//                alert(err.Message);
            }
        });

    }

    // use current filter
    self.doSearch = function () {
        doUpdateTable(true);
    };

    // use old filter
    self.doChangePage = function (page) {
        doUpdateTable(false, page);
    };

    self.doReset = function () {
        /*self.emptyTable();*/

        $(".activeForm input, textarea").val("");

        $(".activeForm ul#tagsTag li.tagit-choice").remove();

        $(".activeForm select option:selected").removeAttr("selected");

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

var menuMap = { "activityMenuItem": "#activityFilter", "newsMenuItem": "#newsFilter", "courseMenuItem": "#courseFilter",
    "userMenuItem": "#userFilter", "reportMenuItem": "#reportFilter"}
var urlMap = {  "activityFilter": "activityTable", "newsFilter": "newsTable", "courseFilter": "courseTable",
    "userFilter": "userTable", "reportFilter": "reportTable"}

var settings = {
    "menuMap": menuMap,
    "urlMap": urlMap
};
var searchPage = new SearchPage(settings);
