<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>

<div id="newsFilter" class="filterForm" style="width: 100%; float: left;">
<#include "filters/newsSearchFilter.ftl"/>
</div>

<div id="courseFilter" class="filterForm" style="display: none; width: 100%; float: left;">
<#include "filters/courseSearchFilter.ftl"/>
</div>

<div id="userFilter" class="filterForm" style="display: none;width: 100%; float: left; ">
<#include "filters/userSearchFilter.ftl"/>
</div>

<div id="reportFilter" class="filterForm" style="display: none;width: 100%; float: left; ">
<#include "filters/reportSearchFilter.ftl"/>
</div>

<div id="searchButtons" class="row" style="text-align: right;">
    <button id="search" type="button" class="btn btn-primary">Search</button>
    <button id="reset" type="button" class="btn btn-default">Reset</button>
</div>

<div id="searchTable"></div>

<script type="text/javascript">
    var menuMap = { "newsMenuItem": "#newsFilter", "courseMenuItem": "#courseFilter",
                    "userMenuItem": "#userFilter", "reportMenuItem": "#reportFilter"}
    var urlMap = {  "newsFilter": "newsTable", "courseFilter": "courseTable",
                    "userFilter": "userTable", "reportFilter": "reportTable"}

    var settings = {
        "menuMap": menuMap,
        "urlMap": urlMap
    };
    var searchPage = new SearchPage(settings);

    $(".navMenuItem").on("click", function (event) {
        event.preventDefault();
        searchPage.showFilterForm(this.id);
        searchPage.doReset();
        searchPage.doSearch(1);

    });
    $("#reset").on("click", function () {
                searchPage.doReset();
                searchPage.doSearch();
            }
    );

    $("#searchTable").on("click", ".navButton", function (event) {
                event.preventDefault();
                var page = this.getAttribute("page");
                searchPage.doSearch(page);
            }
    );

    $("#search").on('click', function (event) {
        event.preventDefault();
        searchPage.doSearch();
    });


    $( document ).ready(function() {
        $('.active').each(function(i, obj) {
                obj.click();
        });
    });


</script>
</@layout.layout>

