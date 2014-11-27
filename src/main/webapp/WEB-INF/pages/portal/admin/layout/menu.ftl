<ul class="nav nav-sidebar">
    <li id="newsMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/news"/>' >News</a></li>
    <li id="courseMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/course"/>'>Courses</a></li>
    <li id="userMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/user"/>'>Users</a></li>
    <li id="reportMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/report"/>'>Reports</a></li>
</ul>

<script type="text/javascript">
    var mainMenuMap = { "news": "#newsMenuItem", "course": "#courseMenuItem",
        "user": "#userMenuItem", "report": "#reportMenuItem", "": "#newsMenuItem"}

    $(document).ready(function() {
        $(mainMenuMap['${menuItem!""}']).addClass("active")
    });
</script>
