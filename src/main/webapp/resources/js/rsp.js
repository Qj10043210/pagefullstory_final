import * as ld from "./load.js";
import * as fc from "./functions.js";
import * as bd from "./board.js";

var cUrl = document.getElementById('clientUrl').getAttribute('data-url');
var gUrl = document.getElementById('gameUrl').getAttribute('data-url');
var bUrl = document.getElementById('boardUrl').getAttribute('data-url');
var jsUrl = document.getElementById('jsUrl').getAttribute('data-url');
var lUrl = document.getElementById('logUrl').getAttribute('data-url');
var rootUrl = document.getElementById('rootUrl').getAttribute('data-url');
var resUrl = document.getElementById('resUrl').getAttribute('data-url');
const musicPlay04 = new Audio(resUrl+'4.mp3');
let music = true;
var cssUrl = document.getElementById('cssUrl').getAttribute('data-url');
const err = document.getElementById('error');
const rsp_m = document.getElementById('ir_m');
const rsp_main = document.getElementById('ir_party_main');
const rsp_lv_t = document.getElementById('ir_party_lv_t');
const rsp_lv_v = document.getElementById('ir_party_lv_v');
const rsp_data_r = document.getElementById('ir_party_data_r');
const rsp_data_w = document.getElementById('ir_party_data_w');
const rsp_data_l = document.getElementById('ir_party_data_l');
const rsp_data_d = document.getElementById('ir_party_data_d');
const rsp_done = document.getElementById('ir_party_done');

var textMax = 7, textMaded = 0, tempData, tempUpdate = {};

/////////////////////////temp////////////////////
//
//
var expLimit = 1000;
const pageInfo = {
    "box-name": "ir_party_room",
    "box-class": "cr_party_room",
    "title-class": "cr_party_room_title",
    "index-class": "cr_party_room_index",
    "max": textMax
}
let uI;
//
//
//////////////////////////////////////////////////
jQ(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay04.loop=true;
	musicPlay04.play()
	}
})
jQ(document).ready(function () {
    ld.loadWell(jQ('#globalLoad'))
    if (ld.checkLoged(rootUrl, jQ('#sessionId'), err, jQ('#error_button'))) {
    	clearAll();
        uI = setInterval(allDo, 700);
    }//last
});//lastss


function allDo(){
    jQ.ajax({
        url: gUrl + "getScoreRSP",
        type: 'GET',
        success: function (data) {
            tempData = data;            
            updateAll(data);
            loadBRSP(data => {
                let tmp = data;
                jQ('#ir_party_room_all').empty();
                loadReadyPlayer(data=>{  
                textMaded = bd.makeBoard(jQ('#ir_party_room_all'), pageInfo, tmp);
                tempUpdate["p_name"] = jQ("#sessionId").val();
                tempUpdate["b_type"] = 0;
                })
            })
        },
        error: function (errorThrown) {

        }
    });//fAjax
}
jQ(document).on("click", "#ir_party_newButton", function () {
    jQ('#ir_party_board').fadeIn(100);
});
jQ(document).on("click", "#ir_party_done", function () {
    window.location.href = cUrl + `index`;
});
jQ(document).on("click", "#ir_party_board_de", function () {
    jQ('#ir_party_board_input').val("")
    jQ('#ir_party_board_input').attr("placeholder", "");
    jQ('#ir_party_board').fadeOut(100);
});
jQ(document).on("click", "#ir_party_board_in", function () {
    if (jQ('#ir_party_board_input').val() == null || jQ('#ir_party_board_input').val() == "") {
        jQ('#ir_party_board_input').attr("placeholder", "빈 글은 등록할 수 없습니다!");
    } else {
        jQ('#ir_party_board_input').attr("placeholder", "");
        tempUpdate["b_title"] = jQ('#ir_party_board_input').val();
        tempUpdate["l_room"] = parseInt(jQ('.cr_party_room_title').length) + 1;
        console.log(parseInt(jQ('.cr_party_room_title').length) + 1)
        updateBRSP(data => {
            loadBRSP(data => {
            	let tmp = data;
            	loadReadyPlayer(data=>{            	
                jQ('#ir_party_room_all').empty();
                textMaded = bd.makeBoard(jQ('#ir_party_room_all'), pageInfo, tmp);
                tempUpdate["p_name"] = jQ("#sessionId").val();
                clearInterval(uI);
                roomMove(textMaded)
                })
            })
        })
    }

});
jQ(document).on('click', ".cr_party_room", function () {
    let nums = jQ(this).data("index")
    roomMove(nums);
})


function updateBRSP(callback) {
    ld.updateBoard(callback, bUrl, tempUpdate)
}
function loadReadyPlayer(callback) {
    bd.loadReadyPlayer(callback, lUrl)
}
function loadBRSP(callback) {
    ld.loadBoard(callback, bUrl, "0");
}

function clearAll() {
    jQ('#ir_party_board_input').val("")
    jQ('#ir_party_board_input').attr("placeholder", "");
    jQ('#ir_party_board').css('display', 'none');
    rsp_lv_v.textContent = "";
    rsp_data_r.textContent = "";
    rsp_data_w.textContent = "";
    rsp_data_l.textContent = "";
    rsp_data_d.textContent = "";
}
function updateAll(data) {
    rsp_lv_v.textContent = data[0].p_level;
    rsp_data_w.textContent = data[0].r_win;
    rsp_data_l.textContent = data[0].r_lose;
    rsp_data_d.textContent = data[0].r_draw;
    let rate = 0, hps = 0, exps = 0;
    if ((data[0].r_win + data[0].r_draw + data[0].r_lose) != 0) {
        rate = ((data[0].r_win + data[0].r_draw) / (data[0].r_win + data[0].r_draw + data[0].r_lose)) * 100;
    }
    rate = rate.toFixed(2)
    rsp_data_r.textContent = rate;
    if (data[0].p_curHp != 0) {
        hps = (data[0].p_curHp / data[0].p_maxHp) * 100;
    }
    exps = (data[0].p_exp / expLimit) * 100;
    jQ('#ir_status_bar_hp_b').css({
        clipPath: `polygon(0% 0%, ${hps}% 0%, ${hps}% 100%, 0% 100%)`
    });
    jQ('#ir_status_bar_exp_b').css({
        clipPath: `polygon(0% 0%, ${exps}% 0%, ${exps}% 100%, 0% 100%)`
    })

}
function roomMove(nums){
    
    jQ("#globalLoad").css({
        display: 'block',
        backgroundImage: 'none',
        // backgroundImage : `url('${cssUrl}loadBack.png')`
    });
    let tempSource = {
        "l_room": nums,
        "b_type": 0,
        "p_name": jQ("#sessionId").val()
    };
    function tryRRSP(callback) {
        ld.tryRoom(callback, gUrl, tempSource)
    }
    function enterRRSP(callback) {
        ld.enterRoom(callback, gUrl, tempSource)
    }


    tryRRSP(data => {
        console.log(data)
        switch (data) {
            case "Y":
                enterRRSP(data => {
                    console.log(data)
                    if (data == "w") {
                        window.location.href = cUrl + `room?num=` + tempSource.l_room;
                    };
                    jQ("#globalLoad").css({
                        display: 'none',
                        backgroundImage: `url('${cssUrl}loadBack.png')`
                    });
                })
                break;
            case "S":
                enterRRSP(data => {
                    console.log(data)
                    if (data == "w") {
                        window.location.href = cUrl + `room?num=` + tempSource.l_room;
                    };
                    jQ("#globalLoad").css({
                        display: 'none',
                        backgroundImage: `url('${cssUrl}loadBack.png')`
                    });
                })
                break;
            case "E":
            default:
                jQ("#globalLoad").css({
                    display: 'none',
                    backgroundImage: `url('${cssUrl}loadBack.png')`
                });
                break;
        }
    })

}
