<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">How it works</a></li>
                <li><a href="#about">Courses</a></li>
                <li><a href="#contact">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><a href="<@spring.url "/user/register" />">Register</a></li>
            </ul>

            <#if "${content}" != "/user/login">
                <ul class="nav navbar-nav navbar-right"">
                    <#if currentUser??>
                    <#if currentUser != "anonymousUser">
                        <li style="color: #ac2925"><a href="<@spring.url'/login_attempt'/>">Logged as: ${currentUser}</a></li>
                        <li><a href="<@spring.url'/logout'/>">Log Out</a></li>
                    <#else>
                        <li><a href="<@spring.url'/login'/>">Sign In</a></li>
                    </#if>
                    </#if>
                </ul>
            </#if>
        </div>
    </div>
</div>