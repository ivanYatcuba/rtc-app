<#include "/spring.ftl"/>

<#macro datatable filterForm searchTable searchUrl>
<div id="filterForm">
    <#include "${filterForm}"/>
</div>

<div id="searchTable">
    <#include "${searchTable}"/>
</div>

<script>
    $("#searchTable").on('click', ".navButton", function(event) {
        event.preventDefault();
        var page = this.getAttribute("page")
        switchPage(page)
    })

    function switchPage(page) {
        $.ajax({
            type: "POST",
            url: "<@spring.url "${searchUrl}" />",
            data: $("#searchTable :input").serialize()+"&page="+page,//{page: page},
            success: function (result) {
                /*var str = result;
                var live_str = $('<div>', {html: str});
                var nav = live_str.find('#navigation').html();
                var data = live_str.find('#data').html();
                $('#navigation').html(nav);
                $('#data').html(data);*/
                $("#searchTable").html(result)
            }, error: function (xhr, status, error) {
                alert("error")
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    }
</script>
</#macro>


<#macro addPagination>
<div id="navigation">
    <#if startPage??>
        <div class="row">
            <ul class="pagination">
                <li><a href="#" class="navButton" page="${startPage}">&laquo;&nbsp</a></li>

                <#if currentPage &gt; startPage+1>
                <#-- -2 -->
                    <#if currentPage < lastPage-1 && currentPage &gt; 0>
                    <#-- -2 +2 -->
                        <#list currentPage-2..currentPage+2 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" class="navButton" page="${i}">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    <#else>
                        <#if currentPage < lastPage <#-- currentPage &gt; 0 --> >
                        <#-- -3 +1 -->
                            <#list currentPage-3..currentPage+1 as i>
                                <#if currentPage == i>
                                    <li class="active"><a href="#">${i}</a></li>
                                <#else>
                                    <#if i<=lastPage && i &gt; startPage || i==startPage>
                                        <li><a href="#" class="navButton" page="${i}">${i}</a></li>
                                    </#if>
                                </#if>
                            </#list>
                        <#else>
                        <#-- -4 0 -->
                            <#list currentPage-4..currentPage as i>
                                <#if currentPage == i>
                                    <li class="active"><a href="#">${i}</a></li>
                                <#else>
                                    <#if i<=lastPage && i &gt; startPage || i==startPage>
                                        <li><a href="#" class="navButton" page="${i}">${i}</a></li>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
                    </#if>
                <#else>
                    <#if currentPage &gt; startPage>
                    <#-- -1 +3 -->
                        <#list currentPage-1..currentPage+3 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" class="navButton" page="${i}">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    <#else>
                    <#-- 0 +4-->
                        <#list currentPage..currentPage+4 as i>
                            <#if currentPage == i>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <#if i<=lastPage && i &gt; startPage || i==startPage>
                                    <li><a href="#" class="navButton" page="${i}">${i}</a></li>
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                </#if>
                <li><a href="#" class="navButton" page="${lastPage}">&nbsp&raquo;</a></li>
            </ul>
        </div>
    </#if>
</div>
</#macro>
