<div class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation"">
    <div class="navbar-header">
        <a class="navbar-brand" href="#" >Admin
            Portal</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <#if currentUser??>
        <li><a href="<@spring.url"/admin/user/userPage/${currentUser.code}"/>"> <u>Profile</u></a></li>
        </#if>
    </ul>

    <form class="navbar-form navbar-right">
        <input  type="text"
                placeholder="Search...">
    </form>
</div>
