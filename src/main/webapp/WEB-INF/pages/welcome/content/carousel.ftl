<div id="myCarousel" class="carousel slide">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="item active">
            <img id="imag" src="<@spring.url'/resources/images/carousel/cat1.jpg'/>" alt="">
        </div>
        <div class="item">
            <img src="<@spring.url'/resources/images/carousel/cat2.jpg'/>" alt="">
        </div>
        <div class="item">
            <img src="<@spring.url'/resources/images/carousel/cat3.jpg'/>" alt="">
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div><!-- /.carousel -->
<script>
    !function ($) {
        $(function(){
            // carousel demo
            $('#myCarousel').carousel()
        })
    }(window.jQuery)
</script>
