function SearchPage(settings) {
    var self = this;

    self.urlMap = settings.urlMap;
    self.menuMap = settings.menuMap;

    self.doSearch = function (page) {
        var activeForm;
        alert($(".filterForm :visible").getAttribute("id"));
//       if ($("#userFilter").is(":visible"))
//       $(".filterForm").is(":visible", function(event){
//                   event.preventDefault();
//
//                   searchPage.showFilterForm(this.id)
//               });
//           })
        page = page || 1;
        $.ajax({
            type: "POST",
            url: "<@spring.url "+self.urlMap[activeForm]+"/>",
            data: $("#filterForm :input").serialize()+"&page="+page,//{page: page},
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
                alert("error")
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });;
    };

    self.doReset = function () {
        $(":input").val('');
    };

    self.showFilterForm = function (menuId) {
//        alert(menuId+"   "+self.menuMap[menuId]);
        $("#searchButtons").show();
        $(".filterForm").hide();
        $(".navMenuItem").removeClass("active");
        $("#"+menuId).addClass("active");
        $(self.menuMap[menuId]).show();
       };

    self.emptyTable = function () {
        $("#searchTable").empty();
    };
}