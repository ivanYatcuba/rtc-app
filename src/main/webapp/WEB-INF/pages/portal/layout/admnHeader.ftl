<div class="container-fluid" style="background-color: #000000">
    <div class="navbar-header">
        <a class="navbar-brand" href="#" style="color: #ffffff;">Admin
            Portal</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
    <#if currentUser??>
        <li style="color: #ac2925">Logged as: ${currentUser}</li>
        <li><a href="<@spring.url'/logout'/>">Log Out</a></li>
    </#if>
    </ul>
</div>
