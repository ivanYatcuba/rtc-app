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
                <div class="span4">
                <a href="#">${course.name} </a>
                <br>
                <div style="width:250px; height:200px; word-wrap: break-word ">${course.description}
                <br>
                    <div class="btn btn-info"> Apply </div>
                </div>
                </div>
            </#list>

        </div>



</div>
