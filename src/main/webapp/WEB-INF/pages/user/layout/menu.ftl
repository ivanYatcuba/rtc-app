<div class="col-sm-3 col-md-2 sidebar" >
    <ul class="nav nav-sidebar">
        <#if user?? && user.id??>
            <li class="active"><a href="<@spring.url'/user/view/${user.id}'/>">Profile</a></li>

            <#else>
                <li class="active"><a href="<@spring.url'/user/view/'/>">Profile</a></li>
        </#if>
        <#if user?? && user.id??>
            <li class="active"><a href="<@spring.url'/user/${user.id}/userCourses'/>">Courses</a></li>
        <#else >
            <li class="active"><a href="<@spring.url'/user/userCourses'/>">Courses</a></li>
        </#if>
    </ul>
</div>
