import * as ld from "./load.js";
import * as fc from "./functions.js";
import * as bd from "./board.js";
import * as rs from "./rooms.js";

var cUrl = document.getElementById('clientUrl').getAttribute('data-url');
var gUrl = document.getElementById('gameUrl').getAttribute('data-url');
var bUrl = document.getElementById('boardUrl').getAttribute('data-url');
var lUrl = document.getElementById('logUrl').getAttribute('data-url');
var jsUrl = document.getElementById('jsUrl').getAttribute('data-url');
var rootUrl = document.getElementById('rootUrl').getAttribute('data-url');
var resUrl = document.getElementById('resUrl').getAttribute('data-url');
const musicPlay03 = new Audio(resUrl+'3.mp3');
let music = true;
var lorr = jQ('#sessionNew').val();
var curName = "";
var cssUrl = document.getElementById('cssUrl').getAttribute('data-url');
const err = document.getElementById('error');
var tempReady = {};
var tempRRR = 0;
var tempTTT = 0;
var tempName ="";
let tempQQQ = {
    "l_room": jQ('#sessionLoc').val(),
    "b_type": jQ('#sessionTyp').val()
}
var uI;

var hUrl = jsUrl + "hair.json";
var fUrl = jsUrl + "face.json";


const l_url = fUrl;
let facejson;
const l_url2 = hUrl;
let hairjson;
let OtherFace, OtherHair, ThisFace, ThisHair;
jQ(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay03.loop=true;
	musicPlay03.play()
	}
})
ld.jsonData((data1) => {
    facejson = data1;
    
    ld.jsonData((data2) => {
        hairjson = data2;
        bringFaceHair(jQ("#sessionId").val(), lorr);
    }, l_url2)
}, l_url)

jQ(document).ready(function () {
    ld.loadWell(jQ('#globalLoad'));
    if (ld.checkLoged(rootUrl, jQ('#sessionId'), err, jQ('#error_button'))) {
        console.log(jQ('#sessionNew').val());
        uI = setInterval(updateReady, 800);
        curName = jQ("#sessionId").val();
    }//last
})//lastss


jQ(document).on("click", '.btn_back', function () {
    let tempSource = {
        "l_room": jQ('#sessionLoc').val(),
        "p_name": jQ("#sessionId").val(),
        "b_type": jQ('#sessionTyp').val(),
    };
        
    function leaveRRSP(callback) {
        ld.leaveRoom(callback, gUrl, tempSource);
    }
    leaveRRSP(data => {
        window.location.href = cUrl + `index`;
    });
});

jQ(document).on("click", '#rsp_btn_ready', function () {
    if (tempRRR == 0) {
        tempRRR = 1;
    } else {
        tempRRR = 0;
    }
    console.log(tempRRR);
    tempReady["l_room"] = parseInt(jQ('#sessionLoc').val());
    tempReady["p_name"] = jQ("#sessionId").val().toString();
    tempReady["b_type"] = parseInt(jQ('#sessionTyp').val());
    tempReady["ro_lr"] = lorr.toString();
    tempReady["ro_re"] = parseInt(tempRRR);

    readySituation(data => {
        let tempq = 0;
        lorr == "L" ? tempq = 0 : tempq = 1;
        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_0')
        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_1')

    });
});

function readySituation(callback) {
    rs.readySituation(callback, tempReady, lUrl);
}
function updateReadyAJAX(callback) {
    rs.updateReady(callback, tempQQQ, lUrl)
}


function updateReady() {
    if (jQ('#rsp_chars_ready_0').hasClass('rsp_chars_ready_1') && jQ('#rsp_chars_ready_1').hasClass('rsp_chars_ready_1')) {
        clearInterval(uI);
        jQ('#rsp_btn_ready').fadeOut(300);
    } else {
        updateReadyAJAX(data => {
            if (data.length > 0) {
                for (let index = 0; index < data.length; index++) {
                    let item = data[index];
                    let tempq = 0;
                    let tempp = 0;
                    item.ro_lr == "L" ? tempq = 0 : tempq = 1;
                    if (item.ro_re != tempTTT && item.ro_lr != lorr) {
                        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_0')
                        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_1')
                        tempTTT = item.ro_re;
                    }

                }
                
                let tempArray = [];                
                for (let index = 0; index < data.length; index++) {
                	let tempList = {};	
                    let item = data[index];
                    tempList["p_name"] = item.p_name;                    
                    tempList["ro_lr"] = item.ro_lr;                    
                    tempArray.push(tempList)
                }
                
                if(tempArray.length == 1){
                tempName ="";
                	let tempq = 0;
                	lorr == "L" ? tempq = 1 : tempq = 0;
                	jQ(`#rsp_chars_hair_${tempq}`).css({					
					width: `calc(100% * (38 / 57))`,
                    aspectRatio: `38 / 22}`,
                    top: `calc(100% * (14 / 50))`,
                    left: `calc(100% * (9 / 57))`,
                    backgroundImage: `url('${cssUrl}hair/0000.png')`
				});
				jQ(`#rsp_chars_face_${tempq}`).css({
					aspectRatio: `26 / 16`,
                    backgroundImage: `url('${cssUrl}face/0000.png')`
				})
                }else if(tempArray.length >1){
                tempArray.forEach(item=>{
                	if(item.p_name != tempName && item.ro_lr != lorr){
                		tempName = item.p_name;
                		bringFaceHair(item.p_name, item.ro_lr);
                	}
                })
                }
                
            }

        })
    }
}

function bringFaceHair(names,lor){
	jQ.ajax({
		url : cUrl + "getUserInfo",
		type:'POST',
		data : {p_name : names},
		success:function(data){
				let tempq = 0;
				lor == "L" ? tempq = 0 : tempq = 1;
				
				OtherFace = facejson.filter(element => element.face_original == data[0].p_face);
				OtherHair = hairjson.filter(element => element.hair_original == data[0].p_hair);
				
				jQ(`#rsp_chars_hair_${tempq}`).css({					
					width: `calc(100% * (${OtherHair[0].hair_w} / 57))`,
                    aspectRatio: `${OtherHair[0].hair_w} / ${OtherHair[0].hair_h}`,
                    top: `calc(100% * (${OtherHair[0].hair_y} / 50))`,
                    left: `calc(100% * (${OtherHair[0].hair_x} / 57))`,
                    backgroundImage: `url('${cssUrl}hair/${OtherHair[0].hair_number}.png')`
				});
				jQ(`#rsp_chars_face_${tempq}`).css({
					aspectRatio: `${OtherFace[0].face_x} / ${OtherFace[0].face_y}`,
                    backgroundImage: `url('${cssUrl}face/${OtherFace[0].face_number}.png')`
				})
		},
		error: function (errorThrown) {
		}
		
		
	})
}