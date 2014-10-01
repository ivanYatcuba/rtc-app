<#import "../../../../datatables.ftl" as datatables/>

<style>
    hr {
        border-top:2px solid #e1dede
    }
</style>


<@datatables.datatable "/portal/admin/news/content/search/filterForm.ftl" "/portal/admin/news/content/search/searchTable.ftl" "/admin/news/search"/>
<div class="row">
    <div class="col-md-6" >
        <form  class="inline-box" style="margin:20" name="createNews" action="<@spring.url"/admin/news/create"/>"method="get">
            <button  class="btn btn-primary" type="submit">Create New</button>
        </form>
    </div>
</div>

