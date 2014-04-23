<div class="row-fluid 12">

    <div class="span2">

        <label>My courses</label>

    </div>

</div>

<hr size="2">
<div class="">

    <div class="span12">

        <label>Available courses</label>

    </div>

        <div class="container"style="margin-left: -20px;padding-right: 0px">

            <#list courses as course>

                <div class="span4" style="word-wrap: break-word; border: solid 1px #008000;text-align: center;margin-top:10px; margin-left: 5px;">

                     <a href="#">${course.name} </a>
                <br>

                        <#--<div class="span3">-->
                            <div class="thumbnail">

                                <img src="<@spring.url'/resources/images/profile.jpg'/>" alt="..." style="width: 200px;height: 120px">

                            </div>
                        <#--</div>-->

                    <br>

                        <div class="userCourses"> ${course.description}</div>
                    <br>
                    <div class="btn btn-success" style="margin-bottom: 5px"> Apply </div>

                </div>

            </#list>

        </div>

</div>
<!--Lisitsa-->
<a href="javascript:PopUpShow()">Показать попап</a>

<div class="popup" id="window-popup">
 <div class="popup-content">
<center>
<h2><@spring.message "userCourses"/>
</h2></center>
<strong>
<@spring.message "userCourses.IHave"/>
</strong>
 <form name="modal" action="modal" method="post">
<label class="radio inline">
<input type="radio" name="userCourses" id="optionsRadios1" value="<@spring.message "userCourses.developer"/>" checked><@spring.message "userCourses.developer"/></label>
<br>
<label class="radio inline">
<input type="radio" name="userCourses" id="optionsRadios2" value="<@spring.message "userCourses.tester"/>"><@spring.message "userCourses.tester"/></label>
<br>
<label class="radio inline">
<input type="radio" name="userCourses" id="optionsRadios2" value="<@spring.message "userCourses.Business_Analyst"/>"><@spring.message "userCourses.Business_Analyst"/></label>
<br>
<strong>
<@spring.message "userCourses.because"/>
</strong>
<br>
    <textarea class="input-block-level" name="userTextArea" id="redex" rows="10"></textarea>
    <style type="text/css">
#redex { resize: none; }
</style>
<br>
<!--<a href="javascript:PopUpHide()">Скрыть попап</a>-->
<center>

 <input type="submit" class="btn btn-primary" value="Ok"/>
<button class="btn" type="button" onClick="javascript:PopUpHide()">Cencel</button>
</center>
</form>

</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
PopUpHide();
});
function PopUpShow(){
$("#window-popup").show();
}
function PopUpHide(){
$("#window-popup").hide();
}
</script>
