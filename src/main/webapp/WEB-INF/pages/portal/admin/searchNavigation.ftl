<div id="navigation">
<#if startPage??>
    <div class="row">
        <div class="col-md-12" align="right">
            Pages:
            <#if startPage != currentPage>
                <a href="#" onclick="switchPage(${startPage})">>&laquo;</a>
            </#if>
            <#if prevPage??>
                <a href="#" onclick="switchPage(${prevPage})">>>${prevPage}</a>
            </#if>
        ${currentPage}
            <#if nextPage??>
                <a href="#" onclick="switchPage(${nextPage})">${nextPage}</a>
            </#if>
            <#if lastPage != currentPage>
                <a href="#" onclick="switchPage(${lastPage})">>>&raquo;</a>
            </#if>
        </div>
    </div>
</#if>
</div>


<script>
    function switchPage(page) {
        $.ajax({
            type: "POST",
            url: "/admin/course/" + page + "/",
            success: function (result) {
                var str = result;
                var live_str = $('<div>', {html: str});
                var nav = live_str.find('#navigation').html();
                var data = live_str.find('#data').html();
                $('#navigation').html(nav);
                $('#data').html(data);
            }
        });
    }
    $(function () {
        switchPage(1);
    });
</script>
