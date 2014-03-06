
<div class="container">
    
<!-- Кнопка по нажатию на которую будет показано окно: -->



<!-- Содержимое модального окна: -->

<div id="mayoOkno" class="modal hide fade" tabindex="-1" role="dialog">
  <div class="modal-header">

    <button type="button" class="close" data-dismiss="modal">?</button>

    <h1><@spring.message "enter"/> </h1>
  </div>

  <div class="modal-body">
<center>
    <#include "in.ftl">
<p>
<#include "down.ftl">
</p>
</center>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal">Закрыть</button>
      </div>
</div>

   
