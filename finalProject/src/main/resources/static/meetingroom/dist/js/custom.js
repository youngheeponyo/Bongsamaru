$( document ).ready(function() {

    $(window).scroll(function() {
		if ($(this).scrollTop() > 50){  
			$('header').addClass("sticky");
		}
		else{
			$('header').removeClass("sticky");
		}
	});
    
    $('.frequenly-question-slider .owl-carousel').owlCarousel({
        loop:true,
        center: true,
        items:3,
        margin:30,
        nav:false,
        dots: false,
        responsive:{
            0:{
                items:1,
                margin:10
            },
            768:{
                items:2,
                margin:20
            },
            1000:{
                items:2,
            },
            1400:{
                items:3,
            },
            1600:{
                items:4,
                margin:30
            }
        }
    })

    $('.logo-brand-slider .owl-carousel').owlCarousel({
        loop:true,
        items:4,
        autoplay:true,
        autoplayTimeout:5000,
        autoplayHoverPause:true,
        nav:false,
        dots: false,
        responsive:{
            0:{
                items:1,
            },
            768:{
                items:2,
            },
            1000:{
                items:3,
            },
            1400:{
                items:4,
            }
        }
    })


});