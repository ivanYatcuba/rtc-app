<ul class="nav nav-sidebar">
    <li id="newsMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search?formToShow=news"/>' >News</a></li>
    <li id="courseMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search"/>'>Courses</a></li>
    <li id="userMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search"/>'>Users</a></li>
    <li id="reportMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search"/>'>Reports</a></li>
</ul>

<script type="text/javascript">
    $(function() {
        var pathname = window.location.pathname;
        if(pathname.indexOf("course") > -1){
            $("#courseMenuItem" ).addClass( "active" );
        }
        if(pathname.indexOf("user") > -1){
            $( "#userMenuItem" ).addClass( "active" );
        }
        if(pathname.indexOf("export") > -1){
            $( "#reportMenuItem" ).addClass( "active" );
        }
        if(pathname.indexOf("news") > -1){
            $( "#newsMenuItem" ).addClass( "active" );
        }
    });
</script>