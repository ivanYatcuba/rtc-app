<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>

<ul class="nav nav-pills" role="tablist">
    <li role="presentation" class="dropdown">
        <a id="drop6" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
            Dropdown
            <span class="caret"></span>
        </a>
        <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop6">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
        </ul>
    </li>
</ul>



<div class="col-md-offset-3 col-md-6">
    <div class="form-group"><label class="control-label col-md-4" for="types"><@spring.message "courses.catalog"/></label>
    <div class="col-md-8">
        <select id="types" name="types" class = "form-control">
            <option value=""><@spring.message "courses.types.All"/></option>
            <#list courseTypes as type>
            <option value="${type}">
                <@spring.message "courses.types.${type}"/>
            </option>
            </#list>
        </select>
    </div>
    </div>
</div>
<br/><br/>

<div id="coursesContent">

</div>

</@layout.layout>

<script>
    $(function() {
       search(1);
    });

    $('#types').on('change', function (e) {
        search(1);
    });

    $("#coursesContent").on("click", ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        search(page);
    });

    function search(page) {
        $.ajax({
            type: "POST",
            url: "<@spring.url "/user/courses/courseTable"/>",
            data: $("#types").serialize()+"&page="+page,
            success: function (result) {
                $("#coursesContent").html(result)
            }, error: function (xhr, status, error) {
                alert("err")
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