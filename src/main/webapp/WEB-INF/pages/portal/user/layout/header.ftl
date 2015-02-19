<div class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <div class="navbar-header">
    </div>
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right" style="">

            <li style="height: 45px">
                <a style="padding-right: 0px" href='<@spring.url"/user/profile/"/>'><@formMacro.userImage "${(profileHeader.imageId)!}" "" "profileImg" /></a>
            </li>
            <li style="margin-right: 50px;height: 45px">
                <a href='<@spring.url"/user/profile/"/>'>${(profileHeader.name)!}</a>
            </li>
            <#if profileHeader.unreadMessageCount!=0>
                <li style="margin-right: 50px;height: 45px">
                    <a>(${(profileHeader.unreadMessageCount)!})</a>
                </li>
            </#if>
            <li style="margin-right: 20px;height: 45px">
                <a href='<@spring.url"/logout"/>'><img src="<@spring.url'/resources/images/exit.png'/>"></a>
            </li>

        </ul>
    </div>
</div>
