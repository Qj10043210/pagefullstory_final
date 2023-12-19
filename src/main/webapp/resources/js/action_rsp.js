import * as ld from "./load.js";
import * as fc from "./functions.js";

var cUrl = document.getElementById('clientUrl').getAttribute('data-url');
var gUrl = document.getElementById('gameUrl').getAttribute('data-url');
var bUrl = document.getElementById('boardUrl').getAttribute('data-url');
var jsUrl = document.getElementById('jsUrl').getAttribute('data-url');
var lUrl = document.getElementById('logUrl').getAttribute('data-url');
var rootUrl = document.getElementById('rootUrl').getAttribute('data-url');

var cssUrl = document.getElementById('cssUrl').getAttribute('data-url');
const err = document.getElementById('error');
var lorr = "", loc, typ;
var curName = "";
let tempLR = "";
var expirationTime = 10 * 1000;
var tempExpTime = 9;
var tempOrder = 0;
var whatIchoose = "";
var randomRSP = ["R", "S", "P"]
var tempData = {};
var thisPlayer, otherPlayer;

var hUrl = jsUrl + "hair.json";
var fUrl = jsUrl + "face.json";


const l_url = fUrl;
let facejson;
const l_url2 = hUrl;
let hairjson;
let OtherFace, OtherHair, ThisFace, ThisHair;
ld.jsonData((data1) => {
    facejson = data1;

    ld.jsonData((data2) => {
        hairjson = data2;

    }, l_url2)
}, l_url)
/////////////////////////////////////////
var uI, uI2;
/////////////////////////////////////////
jQ(document).ready(function () {
    setTimeout(() => {

        lorr = jQ('#sessionNew').val();
        curName = jQ("#sessionId").val();
        loc = jQ('#sessionLoc').val();
        typ = jQ('#sessionTyp').val();


        getUserInfo((data) => {
            lorr == "L" ? tempLR = "l" : tempLR = "r";
            let hps = 0;
            thisPlayer = data;
            if (data[0].p_curHp != 0) {
                getHp(data[0].p_curHp, data[0].p_maxHp, jQ(`#rsp_${tempLR}_bar_hp`), data[0].p_name)
            }else{
            	youDie(data[0].p_name);
            }
        }, curName);




        uI = setInterval(checkReady, 800);
    }, 800)
})

function rspStart() {
    switch (tempOrder) {
        case 0:
            jQ(`#rsp_sc_l`).css({
                aspectRatio: 103 / 89,
                backgroundImage: `url('${cssUrl}rsp/shaking.gif')`
            })
            jQ(`#rsp_sc_r`).css({
                aspectRatio: 103 / 89,
                backgroundImage: `url('${cssUrl}rsp/shaking.gif')`
            })
            uI2 = setInterval(updateTimer, 800);
            break;
        case 1:
            if (whatIchoose == "") {
                fc.shuffleArray(randomRSP)
                tempData["g_hand"] = randomRSP[0];
            } else {
                tempData["g_hand"] = whatIchoose;
            }
            tempData["p_name"] = curName;
            tempData["ro_lr"] = lorr;
            tempData["b_type"] = typ;
            tempData["g_num"] = loc;
            insertHand((data) => {

                setTimeout(() => {
                    updateHand((data) => {
                        console.log(data)
                        jQ(`#rsp_sc_l`).css({
                            aspectRatio: 87 / 77,
                            backgroundImage: `url('${cssUrl}rsp/${data.leftHand}.png')`
                        })
                        jQ(`#rsp_sc_r`).css({
                            aspectRatio: 87 / 77,
                            backgroundImage: `url('${cssUrl}rsp/${data.rightHand}.png')`
                        })
                    }, tempData)
                }, 100)

            }, tempData)

            setTimeout(()=>{
            uI2 = setInterval(updateTimer, 800);},1000);
            break;
        case 2:
            if (whatIchoose == "") {
                fc.shuffleArray(randomRSP);
                tempData["g_hand"] = randomRSP[0];

            } else {
                tempData["g_hand"] = whatIchoose;

            }
            tempData["p_name"] = curName;
            tempData["ro_lr"] = lorr;
            tempData["b_type"] = typ;
            tempData["g_num"] = loc;
            insertHand((data) => {

                setTimeout(() => {
                    updateHand((data) => {

                        jQ(`#rsp_sc_l`).css({
                            aspectRatio: 87 / 77,
                            backgroundImage: `url('${cssUrl}rsp/${data.leftHand}.png')`
                        })
                        jQ(`#rsp_sc_r`).css({
                            aspectRatio: 87 / 77,
                            backgroundImage: `url('${cssUrl}rsp/${data.rightHand}.png')`
                        })
                        switch (data.result) {
                            case "lw":
                                giveDamage(data.leftMan, data.rightMan, "L");
                                break;
                            case "rw":
                                giveDamage(data.rightMan, data.leftMan, "R");
                                break;
                            case "draw":
                            case "fail":
                                drawGame(data.leftMan, data.rightMan);
                                break;
                        }
                    }, tempData)
                }, 100)


            }, tempData)

            break;
    }

}
function updateTimer() {
    if (expirationTime <= 0) {
        clearInterval(uI2);
        tempOrder++;
        expirationTime = 10 * 1000;
        tempExpTime = 9;
        rspStart()
    } else {
        if (!jQ('#rsp_chars_ready_0').hasClass('rsp_chars_ready_1') || !jQ('#rsp_chars_ready_1').hasClass('rsp_chars_ready_1')) {
            clearInterval(uI2);
            tempOrder = 0;
            expirationTime = 10 * 1000;
            tempExpTime = 9;
            uI = setInterval(checkReady, 800);
        }
        expirationTime -= 1000;
        if(tempOrder ==0){
        jQ('#rsp_msg_time').css({
            backgroundImage: `url('${cssUrl}effect/020${tempExpTime}.png')`
        });
        
        }else{
        jQ('#rsp_msg_time').css({
            backgroundImage: `url('${cssUrl}effect/010${tempExpTime}.png')`
        });
        
        }
        tempExpTime--;
    }
}
function getUserInfo(callback, p_name) {
    jQ.ajax({
        url: cUrl + "getUserInfo",
        type: 'POST',
        data: { p_name: p_name },
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}
function checkReady() {
    if (jQ('#rsp_chars_ready_0').hasClass('rsp_chars_ready_1') && jQ('#rsp_chars_ready_1').hasClass('rsp_chars_ready_1')) {
        let tempSources = {
            "l_room": parseInt(jQ('#sessionLoc').val()),
            "b_type": parseInt(jQ('#sessionTyp').val()),
        }
        getConnectedUser((data) => {
            console.log(data)
            let tempName = data[0].p_name;
            let tempName2 = data[1].p_name;
            let tempName3 = "";
            if (tempName == curName) {
                tempName3 = tempName2;
            } else {
                tempName3 = tempName;
            }

            getUserInfo((data) => {
                lorr == "L" ? tempLR = "l" : tempLR = "r";
                let hps = 0;
                thisPlayer = data;
                if (data[0].p_curHp != 0) {
                    getHp(data[0].p_curHp, data[0].p_maxHp, jQ(`#rsp_${tempLR}_bar_hp`), data[0].p_name)
                }
            }, curName);



            getUserInfo((data) => {
                lorr == "L" ? tempLR = "r" : tempLR = "l";
                let hps = 0;
                otherPlayer = data;
                if (data[0].p_curHp != 0) {
                    getHp(data[0].p_curHp, data[0].p_maxHp, jQ(`#rsp_${tempLR}_bar_hp`), data[0].p_name)
                }
            }, tempName3);
        }, tempSources)
        clearInterval(uI);
        rspStart();
    }
}

function youDie(names) {
    if (names == curName) {

        let tempSource = {
            "l_room": jQ('#sessionLoc').val(),
            "p_name": jQ("#sessionId").val(),
            "b_type": jQ('#sessionTyp').val(),
        };
        jQ.ajax({
            url: cUrl + "removePlayer",
            type: 'POST',
            data: { p_name: names },
            success: function (datas) {
                function leaveRRSP(callback) {
                    ld.leaveRoom(callback, gUrl, tempSource);
                }
                leaveRRSP(data => {
                    window.location.href = cUrl + `dead`;
                });
            },
            error: function (errorThrown) {

            }
        })

    }
}
jQ(document).on("click", '.rsp_selects', function () {
    jQ('#rsp_select_r').addClass('noSelect');
    jQ('#rsp_select_s').addClass('noSelect');
    jQ('#rsp_select_p').addClass('noSelect');
    jQ('#rsp_select_r').removeClass('Select');
    jQ('#rsp_select_s').removeClass('Select');
    jQ('#rsp_select_p').removeClass('Select');
    jQ(this).removeClass('noSelect');
    jQ(this).addClass('Select');
    chooseWhat(jQ(this).data("rsp"));
})

function chooseWhat(things) {
    whatIchoose = things;

}
function insertHand(callback, sources) {
    jQ.ajax({
        url: gUrl + "insertHand",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sources),
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}

function updateHand(callback, sources) {
    jQ.ajax({
        url: gUrl + "giveHand",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sources),
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}
function giveDamage(winner, loser, direction) {
    let newInfo = ""

    if (direction == lorr) {
        newInfo = loser;

    } else { newInfo = winner; }
    getUserInfo((data) => {
        getDamage((data) => {
            takeDamage((data) => {
                getUserInfo((data) => {
                    let tempDir = "";
                    direction == "L" ? tempDir = "r" : tempDir = "l";
                    
                    ThisFace = facejson.filter(element => element.face_original == thisPlayer[0].p_face);
                    ThisHair = hairjson.filter(element => element.hair_original == thisPlayer[0].p_hair);
                    OtherFace = facejson.filter(element => element.face_original == data[0].p_face);
                    OtherHair = hairjson.filter(element => element.hair_original == data[0].p_hair);
                    let tempNums, tempNums2;
                    lorr == "L" ? tempNums = 0 : tempNums = 1;
                    lorr == "L" ? tempNums2 = 1 : tempNums2 = 0;
                    if (direction == lorr) {
                        jQ(`#rsp_chars_face_${tempNums2}`).css({
                            aspectRatio: `${OtherFace[4].face_x} / ${OtherFace[4].face_y}`,
                            backgroundImage: `url('${cssUrl}face/${OtherFace[4].face_number}.png')`
                        })
                        jQ(`#rsp_chars_face_${tempNums}`).css({
                            aspectRatio: `${ThisFace[3].face_x} / ${ThisFace[3].face_y}`,
                            backgroundImage: `url('${cssUrl}face/${ThisFace[3].face_number}.png')`
                        })
                    } else {
                        jQ(`#rsp_chars_face_${tempNums2}`).css({
                            aspectRatio: `${OtherFace[3].face_x} / ${OtherFace[3].face_y}`,
                            backgroundImage: `url('${cssUrl}face/${OtherFace[3].face_number}.png')`
                        })
                        jQ(`#rsp_chars_face_${tempNums}`).css({
                            aspectRatio: `${ThisFace[4].face_x} / ${ThisFace[4].face_y}`,
                            backgroundImage: `url('${cssUrl}face/${ThisFace[4].face_number}.png')`
                        })
                    }
                    
                    increaeScore((data) => {
                        increaeScore((data) => {

                        }, loser, "LOSE");
                    }, winner, "WIN");
                    getUserInfo((data)=>{getHp(data[0].p_curHp, data[0].p_MaxHp, jQ(`#rsp_${tempDir}_bar_hp`), loser)},loser);
                }, newInfo)
            }, loser, data)
        }, data)
    }, winner)
    setTimeout(() => {
        let tempNums, tempNums2;
        lorr == "L" ? tempNums = 0 : tempNums = 1;
        lorr == "L" ? tempNums2 = 1 : tempNums2 = 0;
        jQ(`#rsp_chars_face_${tempNums2}`).css({
            aspectRatio: `${OtherFace[0].face_x} / ${OtherFace[0].face_y}`,
            backgroundImage: `url('${cssUrl}face/${OtherFace[0].face_number}.png')`
        })
        jQ(`#rsp_chars_face_${tempNums}`).css({
            aspectRatio: `${ThisFace[0].face_x} / ${ThisFace[0].face_y}`,
            backgroundImage: `url('${cssUrl}face/${ThisFace[0].face_number}.png')`
        })
    }, 3000)

}
function drawGame(playerA, playerB) {
    increaeScore((data) => {
        increaeScore((data) => { }, playerB, "DRAW")
    }, playerA, "DRAW")
}
function getHp(curHp, maxHp, target, name) {
    let hps = 0;
    hps = (curHp / maxHp) * 100;
    if (hps <= 0) {
        youDie(name);
        target.css({
            clipPath: `polygon(0% 0%, 0% 0%, 0% 100%, 0% 100%)`
        })
    } else {

        target.css({
            clipPath: `polygon(0% 0%, ${hps}% 0%, ${hps}% 100%, 0% 100%)`
        })
    }

}

function getDamage(callback, data) {
    jQ.ajax({
        url: gUrl + "returnValueDamage",
        type: 'POST',
        data: { p_dex: data[0].p_dex },
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}
function takeDamage(callback, name, data) {
    jQ.ajax({
        url: gUrl + "returnValueCurHp",
        type: 'POST',
        data: { p_name: name, damage: data },
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}

function getConnectedUser(callback, sources) {
    jQ.ajax({
        url: bUrl + "getCoonectedUser",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(sources),
        success: function (data) {
            callback(data)
        },
        error: function (errorThrown) {

        }
    })
}

function increaeScore(callback, p_name, direction) {
    switch (direction) {
        case "LOSE":
            jQ.ajax({
                url: cUrl + "youLose",
                type: 'POST',
                data: { p_name: p_name },
                success: function (data) {
                    callback(data)
                },
                error: function (errorThrown) {

                }
            })
            break;
        case "WIN":
            jQ.ajax({
                url: cUrl + "youWin",
                type: 'POST',
                data: { p_name: p_name },
                success: function (data) {
                    callback(data)
                },
                error: function (errorThrown) {

                }
            })
            break;
        case "DRAW":
            jQ.ajax({
                url: cUrl + "youDraw",
                type: 'POST',
                data: { p_name: p_name },
                success: function (data) {
                    callback(data)
                },
                error: function (errorThrown) {

                }
            })
            break;
    }
}