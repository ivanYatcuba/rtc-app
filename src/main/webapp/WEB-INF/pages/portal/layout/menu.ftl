<ul class="nav nav-sidebar">
    <li class="active"><a href="<@spring.url'/user/view/'/>">Profile</a></li>
<#if user?? && user.authorities?seq_contains("ROLE_EXPERT")>
    <li class="active"><a href="<@spring.url'/expert/requests'/>">Courses</a>
    </li>
</#if>
<#if user?? && user.authorities?seq_contains("ROLE_USER")>
    <li class="active"><a href="<@spring.url'/user/userCourses'/>">Courses</a>
    </li>
</#if>
</ul>
