<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">


<script>
    $(function() {
        $("#searcher").accordion({ header: "h3", collapsible: true, active: false });
    });
</script>


<div id="searcher" height = "100%">
    <h3>Search</h3>
    <div class="panel panel-default">
        <div class="panel-body">
            <#include "../searchFilterForm.ftl">
         </div>
    </div>
</div>