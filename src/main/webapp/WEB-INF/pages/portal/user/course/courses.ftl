<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>

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
       search();
    });

    $('#types').on('change', function (e) {
        search();
    });

    function search() {
        $.ajax({
            type: "POST",
            url: "<@spring.url "/user/courses/courseTable"/>",
            data: $("#types").serialize(),
            success: function (result) {
                $("#coursesContent").html(result)
            }, error: function (xhr, status, error) {
                alert("err")
            }
        });
    }
</script>