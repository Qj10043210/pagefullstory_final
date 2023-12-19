window.addEventListener("wheel", (e) => e.preventDefault(), { passive: false });
window.addEventListener('mousedown', (e) => {
    if (e.button === 1) {
        e.preventDefault();
    }
});
var expLimit=1000;
var cUrl = document.getElementById('clientUrl').getAttribute('data-url');
jQ(document).on("click",'#player_icon',function(){
	let p_name = jQ('#sessionId').val();
	if(p_name != null && p_name != ""){
		jQ.ajax({
			url:cUrl+"getUserInfo",
			type:'post',
			data:{p_name : p_name},
			success:function(data){
				jQ('#box_str_v').text(data[0].p_str);
				jQ('#box_dex_v').text(data[0].p_dex);
				jQ('#box_int_v').text(data[0].p_int);
				jQ('#box_luk_v').text(data[0].p_luk);
				jQ('#box_point_v').text(data[0].p_point);
				jQ('#box_level_v').text(data[0].p_level);
				let exps = (data[0].p_exp / expLimit) * 100;
				jQ('#box_status_bar_exp_b').css({
        		clipPath: `polygon(0% 0%, ${exps}% 0%, ${exps}% 100%, 0% 100%)`
    			})
    			jQ('#player_icon').css('display','none');
    			jQ('#player_box').css('display','flex');
			},
			error: function (errorThrown) {

        	}
		})
	
	}
})

jQ(document).on("mouseleave",'#player_box',function(){
	jQ('#player_icon').css('display','block');
	jQ('#player_box').css('display','none');
});
