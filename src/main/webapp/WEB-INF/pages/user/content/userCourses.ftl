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

        <div class="span3">

            <#list courses as course>
            <a href="#">${course.name} </a>
            <br>
            <label>${course.description}</label>

        </div>

        </#list>

</div>
