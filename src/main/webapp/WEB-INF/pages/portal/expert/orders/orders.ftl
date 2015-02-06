<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>
<div id="reportFilter" class="filterForm" style="display: none;width: 100%; float: left; ">
    <#include "orderFilter.ftl"/>
</div>

<div id="searchButtons" class="row" style="text-align: right;">
    <button id="search" type="button" class="btn btn-primary">Search</button>
    <button id="reset" type="button" class="btn btn-default">Reset</button>
</div>

<div id="searchTable"></div>

<script type="text/javascript">


    $(".navMenuItem").on("click", function (event) {
        event.preventDefault();
        searchPage.showFilterForm(this.id);
        searchPage.doReset();
        searchPage.doSearch();

    });
    $("#reset").on("click", function () {
                searchPage.doReset();
                searchPage.doSearch();
            }
    );
    $("#searchTable").on("click", ".navButton", function (event) {
                event.preventDefault();
                var page = this.getAttribute("page");
                searchPage.doChangePage(page);
            }
    );
    $("#search").on('click', function (event) {
        event.preventDefault();
        searchPage.doSearch();
    });
    function search() {
        $.ajax({
            type: "POST",
            url: <@spring.url "/user/expert/order/orderTable"/>,
            data: currSerializedFilter+"&page="+page,
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
            }
        });
    }
    $( document ).ready(function() {

    });
    function ajaxSessionTimeout() {
        window.location.replace("<@spring.url'/login'/>");
    }
    !function( $ ) {
        $.ajaxSetup({
            statusCode: {
                901: ajaxSessionTimeout
            }
        });
    }(window.jQuery);

</script>
</@layout.layout>
