<div class="row-fluid 12">

    <div class="span2">

        <label>My courses</label>

    </div>

</div>

<hr>
<div class="row-fluid 12">

    <div class="span12">

        <label>Available courses</label>

    </div>

        <div class="span12">

            <#list courses as course>

                <div class="span4" style="word-wrap: break-word; border: solid 1px #008000;text-align: center">

                     <a href="#">${course.name} </a>
                <br>

                        <div style="width:300px; height:200px; word-wrap: break-word "> ${course.description}</div>

                    <div class="btn btn-success"> Apply </div>

                </div>

            </#list>


        </div>



</div>
