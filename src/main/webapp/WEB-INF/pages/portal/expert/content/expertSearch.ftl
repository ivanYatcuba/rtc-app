<h3 style="text-align: center"><@spring.message "expert.search" /></h3>

<form class="form-horizontal" name="expert" id="expert"
      action="<@spring.url '/expert/search'/>" method="post">
    <div class="row">
        <div class="span6">
        <@spring.formItem "expert.name"/>
            <@spring.formItem "expert.category"/>
            <@spring.formItem "expert.author"/>
        </div>
        <div class="span6">
        <@spring.formItem "expert.startDate" "datepiker" 'class="input-medium"'/>
            <@spring.formItem "expert.tags" "tag"/>
        </div>
    </div>
</form>
