
<div class="container">
    
<!-- Кнопка по нажатию на которую будет показано окно: -->



<!-- Содержимое модального окна: -->

<div id="modalWindow" class="modal hide fade" tabindex="-1" role="dialog">

  <div class="modal-header">

    <button type="button" class="close" data-dismiss="modal">?</button>

    <h1><@spring.message "enter"/> </h1>
  </div>

  <div class="modal-body">
<center>
    <#include "in.ftl">
<p>
</p>
</center>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal"><@spring.message "btn.close"/></button>
      </div>
</div>

   
