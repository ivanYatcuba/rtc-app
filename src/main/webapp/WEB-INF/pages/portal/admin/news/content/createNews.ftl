<form class="form-horizontal" id="news" name="news"
    action="<@spring.url '/admin/news/save'/>" method="post">
    <div>
        <h3 class="page-header"><@spring.message "news.message"/></h3><br/>
    </div>
    <div class="row">
        <div class="col-md-3">
            <@spring.formItem "news.title" 'id="title" class="required"' />
            </div>
        <div class="col-md-3">
            <@spring.formItem "news.tags" 'id="tags"' />
            </div>
        </div>
    <div class="row">
        <div class="col-md-10" >
            <div class="form-group">
          <label class="control-label col-md-2"
                for="description"><@spring.message "news.description"/></label>
            <@spring.formTextarea "news.description"
            'style="width:100%;" rows="3" maxlength="255" class=\"required field col-md-5\"'/>
            <div class="controls">
            <input id="publish" type="checkbox" />
            <label for="publish">Publish</label>
            </div>
          </div>
         </div>
        </div>
        <hr/>
    <div class="row">
        <div class="col-md-12" style="text-align: right">
            <input type="submit" class="btn" value="Create" />
            <a href="<@spring.url "/admin/news/list" />">Cancel</a>
        </div>
    </div>
</form>
