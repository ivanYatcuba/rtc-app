<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top" role="navigation" style="background-color: #c0c0c0;">
    <div class="container">
        <div class="navbar-collapse collapse" style="margin-left: -1.36em">
            <ul class="nav navbar-nav">
                <li><a href="#">How it works</a></li>
                <li><a href="#">Courses</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="<@spring.url "/user/register" />">Register</a></li>
            </ul>

            <#if "${content}" != "/user/login">
                <ul class="nav navbar-nav navbar-right" style="margin-right: 1.50em">
                    <#if currentUser??>
                    <#if currentUser != "anonymousUser">
                        <li style="color: #ac2925"><a href="<@spring.url'/login_attempt'/>">Logged as: ${currentUser}</a></li>
                        <li><a href="<@spring.url'/logout'/>">Log Out</a></li>
                    <#else>
                        <li><a href="<@spring.url'/login'/>" class="loginButton">Sign In</a></li>
                    </#if>
                    </#if>
                </ul>
            </#if>
        </div>
    </div>
</div>