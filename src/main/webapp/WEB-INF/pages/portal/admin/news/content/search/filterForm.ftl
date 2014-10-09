<h3><@spring.message "news.filter.form.header"/></h3>
<div class="row">
    <div class="col-md-6 form-group">
    <@spring.formItem "filterNews.title"/>
    </div>
    <div class="col-md-6">

    </div>
</div>

<div class="row">
    <div class="col-md-6">
    <@spring.formItem "filterNews.author"/>
    </div>
    <div class="col-md-6">
    <@spring.formItem "filterNews.status" 'class="input-medium"' "singleSelect" statuses/>
    </div>
</div>

<hr/>
<div class="row">
    <div class="col-md-6">
    </div>
    <div class="col-md-5" style="text-align: right">
        <input type="submit" class="btn btn-primary" value="Search"/> or <a
            href="<@spring.url "/admin/course" />">Reset</a>
    </div>
</div>
