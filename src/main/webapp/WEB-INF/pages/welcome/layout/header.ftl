<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">How it works</a></li>
                <li><a href="#about">Courses</a></li>
                <li><a href="#contact">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><a href="#contact">Register</a></li>
            </ul>
            <#if "${content}" != "/user/login">
                <ul class="nav navbar-nav navbar-right"">
                    <li><a href="<@spring.url'/login'/>">Sign In</a></li>
                </ul>
            </#if>
        </div>
    </div>
</div>