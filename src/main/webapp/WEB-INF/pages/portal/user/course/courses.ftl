<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<input type="hidden" id="withArchive" value="false">
<div class="col-md-offset-3 col-md-6" style="margin-bottom: 10px">
    <div class="form-group"><h4 class="control-label col-md-4" for="types" ><strong><@spring.message "courses.catalog"/></strong></h4>
        <div class="col-md-5" style="margin-left: -5%;">

            <select id='types' class="form-control">
                <option value=""><@spring.message "courses.types.All"/></option>
                <#list courseTypes as type>
                    <option value="${type}"><@spring.message "courses.types.${type}"/></option>
                </#list>
            </select>
        </div>
    </div>
</div>

<div id="coursesContent">

</div>

</@layout.layout>

<script>
    $(function() {
        search("", 1);
    });

    $("#types").change(function (event) {
        event.preventDefault();
        var type = $("#types").val();
        search(type, 1);
    });

    $("#coursesContent").on("click", ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        search($("#types").val(), page);
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