
<html>

<head>

    <title>Edit User</title>

</head>

<body>
<h3>Edit User</h3>


<!--Email & Password-->
<div class="row-fluid span12">

    <div class="container">

        <div class = "span4">

            *Email:
            <input type="text" id="inputEmail" placeholder="ivanov@xyz.net" style="margin-top: 5px;">

        </div>

        <div class="span4">

            *Password:
            <input type="password" id="inputPassword" placeholder="123456" style="margin-top: 5px;">

        </div>
    </div>
</div>

<!--Role & show password-->

<div class="row-fluid span12">

    <div class="container">

        <div class="span4">

            *Role:
            <select style="margin-top: 5px;">
                <option>User</option>
                <option>User 1</option>
                <option>User 2</option>
            </select>

        </div>

        <div class="span4">

            <label class="checkbox inline">
                <input type="checkbox" id="inlineCheckbox1" value=""> show password
            </label>

        </div>
    </div>
</div>

<hr align="center" width="800" size="1" />

<!--Gender-->

<div class="row-fluid span4">

    <div class="span1" style="margin-top: 9px;">
        Gender:
    </div>

    <div class="span1">

        <label class="radio">
            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
            Male
        </label>
    </div>

    <div class="span1">
        <label class="radio">
            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" checked>
            Female
        </label>
    </div>

</div>



</body>




</html>