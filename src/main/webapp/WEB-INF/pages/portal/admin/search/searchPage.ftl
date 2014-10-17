<#import "../../../rtcmacroses.ftl" as rtcmacros/>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>

<div id="newsFilter" class="filterForm" style="width: 100%; float: left; display: none">
    <#include "filters/newsSearchFilter.ftl"/>
</div>

<div id="courseFilter" class="filterForm" style="display: none">
<#include "filters/courseSearchFilter.ftl"/>
</div>

<div id="userFilter" class="filterForm" style="width: 100%; float: left; display: none">
    <#include "filters/userSearchFilter.ftl"/>
</div>

<div id="reportFilter" class="filterForm" style="display: none">
    <#include "filters/reportSearchFilter.ftl"/>
</div>

<div id="searchButtons" class="row" style="text-align: right; display: none">
    <a id="search" class="btn btn-primary" href="#"/>Search</a>
    or
    <a id="reset" class="btn-default" href="#">Reset</a>
</div>

<div id="searchTable">

</div>

<script type="text/javascript">
    var menuMap = {"news": "#newsFilter", "course":"#courseFilter", "user": "#userFilter", "report":"#reportFilter"}
    var urlMap = {"newsFilter": "/admin/developed/newsTable","courseFilter": "/admin/developed/courseTable" ,"userFilter": "fwfw","reportFilter": "fwf"}

    var settings = { "menuMap" : menuMap,
                     "urlMap" : urlMap
    };

    var searchPage = new SearchPage(settings);
    $(function () {
        $(".navMenuItem").on("click", function(event){
            event.preventDefault();

            searchPage.showFilterForm(this.id)
        });
    })

    $(function () {
        $("#reset").on("click", function() {
                    searchPage.doReset();
                }
        );  })

    $("#searchTable").on('click', ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        switchPage(page)
    });

    $("#search").on('click', function(event) {
        event.preventDefault();
        searchPage.doSearch(1);
    });
//    $(function () {
//        $("#reset").click(function () {
//            alert("ffooo");
//            searchPage.doReset(); });
//    })
   // searchPage.doSearch(2);
    //searchButtin onclick
    //resetButton onclick - OK
    //menuButtin onclick - ok
    //navigateionButton onclick
</script>

