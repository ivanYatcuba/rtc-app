<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<script src="<@spring.url'/resources/js/pages/searchPage.js'/>"></script>
<div id="messageFilter" class="filterForm" style="width: 100%; float: left; ">
    <#include "messageFilter.ftl"/>
</div>

<div id="searchButtons" class="row" style="text-align: right;">
    <button id="search" type="button" class="btn btn-primary">Search</button>
    <button id="reset" type="button" class="btn btn-default">Reset</button>
</div>

<div id="searchTable"></div>

<script type="text/javascript">

    var serializedFilter = "";

    $(document).ready(function() {
        search(1);
    });

    function search(page) {
        $.ajax({
            type: "GET",
            url: '<@spring.url "/user/expert/message/messageTable"/>',
            data: serializedFilter + "&page=" + page,
            success: function (result) {
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
            }
        });
    }

    $("#searchTable").on("click", ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        search(page);
    });

    $("#search").on('click', function (event) {
        event.preventDefault();
        serializedFilter = $("#messageFilter :input").serialize();
        search(1);
    });

    $("#reset").on("click", function () {
            $(".form-horizontal input, textarea").val("");
            $(".form-horizontal ul#tagsTag li.tagit-choice").remove();
            $(".form-horizontal select option:selected").removeAttr("selected");
            search(1);
        }
    );

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
