<#list courses as course>
<#list users as user>
<div class="row">
    <div class="span12"><a>${course.name}:</a></div>
</div>
<div class="row">
    <div class="span9">
        ${user.name} <@spring.message "expert.note" /> ${user.speciality}
    </div>
    <div class="span3"><a style="color: #008000"><@spring.message "expert.accept" /></a> |
        <a style="color: #ff0000"><@spring.message "expert.decline" /></a></div>
</div>
<div class="row">
    <div class="span12">
        "${user.reason}"
    </div>
</div>
</#list>
</#list>