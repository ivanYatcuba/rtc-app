<div class="col-sm-3 col-md-2 sidebar" >
    <ul class="nav nav-sidebar">
            <li class="active"><a href="<@spring.url'/user/view/'/>">Profile</a></li>
        <#if user?? && user.id??>
            <li class="active"><a href="<@spring.url'/user/${user.id}/userCourses'/>">Courses</a></li>
        <#else >
            <li class="active"><a href="<@spring.url'/user/userCourses'/>">Courses</a></li>
        </#if>
    </ul>
</div>
