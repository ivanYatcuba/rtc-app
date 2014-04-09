<div class="col-md-4" style="padding-left: 0">
    <h3>Courses starting soon</h3>
    <p>
        <#list soonCourses as course>
            ${course.name}&nbsp; &nbsp;${course.startDate?date} <br/>
        </#list>
    </p>
</div>