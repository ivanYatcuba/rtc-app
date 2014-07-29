<form name="export" id="export" action="<@spring.url "/admin/export/downloadCourseExport" />" method="post">
    <script src="<@spring.url'/resources/css/js/jquery.fileDownload.js'/>"></script>
    <h3><@spring.message "export.courses"/></h3>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span5">
            <label for="export.status"><@spring.message "export.status"/>: </label>
            <select id="selectedRole" name="selectedRole">
            <#list stats as stat>
                <option value="${stat}">${stat}</option>
            </#list>
            </select>
        </div>
    </div>
    <br/>
    <br/>
    <hr>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>

        <div class="span2" style="text-align: right">
            <a  class="btn fileDownloadSimpleRichExperience"  href="<@spring.url "/admin/export/downloadCourseExport" />">Export</a> or <a
                href="<@spring.url "/" />">Cancel</a>
        </div>
    </div>
</form>

<script>
    $(document).on("click", "a.fileDownloadSimpleRichExperience", function () {
        $.fileDownload($(this).prop('href'), {
            preparingMessageHtml: "We are preparing your report, please wait...",
            failMessageHtml: "There was a problem generating your report, please try again."
        });
        return false;
    });
</script>