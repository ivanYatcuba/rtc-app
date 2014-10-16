<#import "../../../rtcmacroses.ftl" as rtcmacros/>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>

<div id="newsFilter" class="filterForm" style="width: 100%; float: left; display: ">
    <#include "filters/newsSearchFilter.ftl"/>
</div>

<div id="courseFilter" class="filterForm" style="display: none">
<#include "filters/courseSearchFilter.ftl"/>
</div>

<div id="userFilter" class="filterForm" style="width: 100%; float: left; display: none">
    <#include "filters/userSearchFilter.ftl"/>
</div>

<div id="reportFilter" class="filterForm" style="display: ">
    <#include "filters/reportSearchFilter.ftl"/>
</div>

<div class="row" style="text-align: right; display: ">
    <a class="btn btn-primary" href="#" onclick="javascript:searchPage.doSearch(page)"/>Search</a>
    or
    <a id="reset" class="btn-default" href="#" onclick="javascript:searchPage.doReset()/>">Reset</a>
</div>

<div id="searchTable">

</div>

<script type="text/javascript">
    var settings = {};
    var searchPage = new SearchPage(settings);
//    $("#reset").click(searchPage.doReset());
   // searchPage.doSearch(2);
    //searchButtin onclick
    //resetButton onclick
    //menuButtin onclick
    //navigateionButton onclick
</script>

