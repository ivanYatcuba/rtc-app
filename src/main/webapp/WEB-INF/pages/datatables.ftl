<#macro datatable filterForm searchTable searchUrl>
<div id="filterForm">
    <#include "${filterForm}"/>
</div>

<div id="searchTable">
    <#include "${searchTable}"/>
</div>

<script>
    $("#searchTable").on('click', ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        switchPage(page)
    });

    $("#searchButton").on('click', function(event) {
        event.preventDefault();
        switchPage();
    });

    function switchPage(page) {
        page = page || 1;
        $.ajax({
            type: "POST",
            url: "<@spring.url "${searchUrl}" />",
            data: $("#filterForm :input ").not("#autoSelectInput").serialize()+"&page="+page,//{page: page},
            success: function (result) {
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
    <#if currentPage?? && lastPage &gt; 1>
        <div class="row">
            <ul class="pagination" style="margin: 0px">
                     <#if currentPage &gt; 1>
                         <li><a href="#" class="navButton" page="1">&laquo;&nbsp</a></li>
                     </#if>

                     <#if currentPage-2 &gt; 0>
                         <li><a href="#" class="navButton" page="${currentPage-2}")">${currentPage-2}</a></li>
                     </#if>
                     <#if currentPage-1 &gt; 0>
                         <li><a href="#" class="navButton" page="${currentPage-1}")">${currentPage-1}</a></li>
                     </#if>

                     <li class="active"><a href="#" class="navButton" page="${currentPage}")">${currentPage}</a></li>

                     <#if currentPage+1 <= lastPage>
                         <li><a href="#" class="navButton" page="${currentPage+1}")">${currentPage+1}</a></li>
                     </#if>
                     <#if currentPage+2 <= lastPage>
                         <li><a href="#" class="navButton" page="${currentPage+2}")">${currentPage+2}</a></li>
                     </#if>

                     <#if lastPage != currentPage>
                         <li><a href="#" class="navButton" page="${lastPage}">&nbsp&raquo;</a></li>
                    </#if>
            </ul>
        </div>
    </#if>
</div>
</#macro>
