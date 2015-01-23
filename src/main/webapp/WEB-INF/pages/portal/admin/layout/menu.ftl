<style>
    .outside {
        position: absolute;
        left: 60px;
        top: 650px;
    }
</style>

<ul class="nav nav-sidebar">
    <li id="activityMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/activity"/>'>Activity</a></li >
    <li id="newsMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/news"/>'>News</a></li>
    <li id="courseMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/course"/>'>Courses</a></li>
    <li id="userMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/user"/>'>Users</a></li>
    <li id="reportMenuItem" class="navMenuItem"><a href='<@spring.url"/admin/search/report"/>'>Reports</a></li>
</ul>

<div class="outside">
    <a href='<@spring.url"/admin/logs/list"/>'>Server Log</a>
</div>

<script type="text/javascript">
    var mainMenuMap = { "activity": "#activityMenuItem", "news": "#newsMenuItem", "course": "#courseMenuItem",
        "user": "#userMenuItem", "report": "#reportMenuItem", "": "#activityMenuItem"}

    $(document).ready(function() {
        $(mainMenuMap['${menuItem!""}']).addClass("active")
    });
</script>
