<#import "../../../../datatables.ftl" as datatables/>
<script src="<@spring.url'/resources/Bootstrap/js/bootstrap-dropdown.js'/>"></script>

<style>
    hr {
        border-top:2px solid #e1dede
    }
</style>
<@datatables.datatable "/portal/admin/news/content/search/filterForm.ftl" "/portal/admin/news/content/search/searchTable.ftl" "/admin/news/search"/>


