var resUrl = document.getElementById('resUrl').getAttribute('data-url');
const musicPlay01 = new Audio(resUrl+'1.mp3');


jQ(document).on('click','body',function(){
musicPlay01.loop=false;
musicPlay01.muted=false;
musicPlay01.play()
            	jQ('#main_load').fadeIn();
            	setTimeout(()=>{
            		jQ('#main_load').addClass("done")
            		jQ('#main_load').css("display","flex");
            		jQ('#main_load_msg').fadeIn();	
            	},3050)
            })
           	jQ('#main_load_msg').on('click',function(){
           		window.location.href = jQ('#rootUrl').data('url')+"index";
           	})
