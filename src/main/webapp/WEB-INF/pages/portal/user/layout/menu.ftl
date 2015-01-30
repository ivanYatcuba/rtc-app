<#--if you change this menu, you have to change MenuItems.class!!!-->
<ul class="nav nav-sidebar">
    <li id="profileMenuItem" class="navMenuItem"><a href='<@spring.url"/user/profile/"/>'>Profile</a></li>
    <li id="courseMenuItem" class="navMenuItem"><a href='<@spring.url"/user/courses"/>'>Courses</a></li>
</ul>

<script type="text/javascript">
    var mainMenuMap = { "profile": "#profileMenuItem",  "course": "#courseMenuItem"}

    $(document).ready(function() {
    <#--if you change this menu, you have to change MenuItems.class!!!-->
        $(mainMenuMap['${menuItem!""}']).addClass("active")
    });

    $(document).click(function() {
        console.log($("#menu").val());
    });

</script>
