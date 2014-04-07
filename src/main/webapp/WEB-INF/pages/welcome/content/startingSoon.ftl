<div class="col-md-4">
    <h3>Starting soon</h3>
    <p>
        <#list soonCourses as course>
            ${course.name}&nbsp; &nbsp;${course.startDate?date} <br/>
        </#list>
    </p>
</div>