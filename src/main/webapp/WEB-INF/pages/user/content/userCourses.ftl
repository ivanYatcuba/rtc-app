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

                                <img data-src="<profile.jpg>" alt="...">

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
