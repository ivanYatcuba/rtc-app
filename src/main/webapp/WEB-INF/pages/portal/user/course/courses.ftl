<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<input type="hidden" id="withArchive" value="false">
<div class="col-md-offset-3 col-md-6" style="margin-bottom: 10px">
    <div class="form-group"><h2 class="control-label col-md-7" for="types" ><@spring.message "courses.catalog"/></h2>
    <div class="col-md-5" style="margin-left: -10%;">

        <ul style="padding-top: 10%" id="typeMenu" class="nav nav-pills" role="tablist" style="padding-bottom: 10%">
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

<div id="coursesContent">

</div>

<!--Deprecated-->
<div class="popup" id="window-popup" style="display: none">
    <div class="popup-content">
        <h2><@spring.message "userCourses"/></h2>
        <strong>
            <@spring.message "userCourses.IHave"/> <@spring.message "userCourses.IHave2"/>
        </strong>
        <form name="modal" id="modal" action="<@spring.url'/user/courses/sendOrder/'/>" method="post">
            <@spring.formHiddenInput "order.courseCode"/>
            <div id="positions">
            </div>
            <strong><@spring.message "userCourses.because"/></strong><br/>
            <@spring.bind "order.reason"/>
            <textarea class="input-block-level" name="reason" id="redex"
                      style="width: 100%; height: 100%"></textarea><br>
            <style type="text/css">#redex {
                resize: none;
            }</style>
            <br/>
            <input type="submit" class="btn" value="OK">
            <button class="btn" type="button" onClick="javascript:PopUpHide()">Cancel</button>
        </form>
    </div>
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
            data: "types="+ type +"&page="+ page + "&withArchived=" + withArchive,
            url: "<@spring.url "/user/courses/courseTable"/>",
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


    //Deprecated

    function setCode(courseCode) {
        document.getElementById("courseCode").value = courseCode;
        $.ajax({
            url:"<@spring.url'/user/courses/position/'/>"+courseCode,
            success:function(result){

                var res = "";
                result.forEach(function(element, index, array){
                    res += "<div id='position.name'> "+
                    "<input type='radio' name='position' value='"+element+"'/>&nbsp;"+
                    element+
                    "<br/></div>"
                });
                $("#positions").html(res);
            }
        });
    }

    function PopUpShow(types) {
        types.split(",").forEach(function (type) {
            $("#" + type).show()
        })
        $("#window-popup").show();
    }
    function PopUpHide() {
        $("#window-popup").hide();
    }
</script>