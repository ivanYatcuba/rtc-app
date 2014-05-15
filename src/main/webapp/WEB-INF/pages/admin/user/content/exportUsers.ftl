<form name="export" id="export" action="<@spring.url "/admin/export/users" />" method="post">
    <h3><@spring.message "export.users"/></h3>
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span5">
            <label for="export.status"><@spring.message "export.role"/>: </label>
            <@spring.formSingleSelect "user.role", ["All", "Admin", "Expert", "User"] />
        </div>
    </div>

    <hr>

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>

        <div class="span2" style="text-align: right">
            <input type="submit" class="btn" value="Export"/> or <a
                href="<@spring.url "/" />">Cancel</a>
        </div>
    </div>
</form>