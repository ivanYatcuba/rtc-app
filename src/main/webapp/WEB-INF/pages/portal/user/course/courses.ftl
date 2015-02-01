<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<input type="hidden" id="withArchive" value="false">
<div class="col-md-offset-3 col-md-6">
    <div class="form-group"><label class="control-label col-md-4" for="types" style="padding-top: 2%"><@spring.message "courses.catalog"/></label>
    <div class="col-md-8" style="margin-left: -5%;">

        <ul id="typeMenu" class="nav nav-pills" role="tablist" style="padding-bottom: 10%">
            <li role="presentation" class="dropdown">
                <a id="drop6" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
                    <span id="currentType" type=""><@spring.message "courses.types.All"/></span>
                    <span class="caret"></span>
                </a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop6">
                    <li role="presentation"><a class="typeItem" role="menuitem" tabindex="-1" href="#"  type=""><@spring.message "courses.types.All"/></a></li>
                    <#list courseTypes as type>
                        <li role="presentation"><a class="typeItem" role="menuitem" tabindex="-1" href="#" type="${type}"> <@spring.message "courses.types.${type}"/></a></li>
                    </#list>
                </ul>
            </li>
        </ul>

    </div>
    </div>
</div>
<br/><br/>

<div id="coursesContent">

</div>

</@layout.layout>

<script>
    $(function() {
       search("", 1);
    });


    $("#typeMenu").on("click", ".typeItem", function (event) {
        var type = this.getAttribute("type");
        $("#currentType").attr("type", type);
        $("#currentType").text(this.text);
        search(type, 1);
    });

    $("#coursesContent").on("click", ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        search($("#currentType").attr("type"), page);
    });

    function search(type, page) {
        var withArchive = $("#withArchive").val();
        $.ajax({
            type: "POST",
            url: "<@spring.url "/user/courses/courseTable"/>",
            data: "types="+ type +"&page="+ page + "&withArchived=" + withArchive,
            success: function (result) {
                $("#coursesContent").html(result)
            }, error: function (xhr, status, error) {
            }
        });
    }

    function shorten(text, maxLength) {
        var ret = text;
        if (ret.length > maxLength) {
            ret = ret.substr(0,maxLength-3) + "...";
        }
        return ret;
    }
</script>