<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>
<div id="orderFilter" class="filterForm" style="width: 100%; float: left; ">
    <#include "orderFilter.ftl"/>
</div>

<div id="searchButtons" class="row" style="text-align: right;">
    <button id="search" type="button" class="btn btn-primary">Search</button>
    <button id="reset" type="button" class="btn btn-default">Reset</button>
</div>

<div id="searchTable"></div>

<script type="text/javascript">

    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: '<@spring.url "/user/expert/order/orderTable"/>',
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
            }
        });
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
