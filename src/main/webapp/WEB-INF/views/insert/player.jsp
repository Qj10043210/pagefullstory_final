<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input id="sessionId" value="${sessionScope.joinedName}" type="hidden"/>
 <div id="player_icon"></div>
<div id="player_box">
	<div id="box_str" class="box_stat">
        <span>　힘</span>
        <span id="box_str_v"></span>
        <div id="box_str_p">+</div>
    </div>
    <div id="box_dex" class="box_stat">
        <span>민첩</span>
        <span id="box_dex_v"></span>
        <div id="box_dex_p">+</div>
    </div>
    <div id="box_int" class="box_stat">
        <span>지력</span>
        <span id="box_int_v"></span>
        <div id="box_int_p">+</div>
    </div>
    <div id="box_luk" class="box_stat">
        <span>행운</span>
        <span id="box_luk_v"></span>
        <div id="box_luk_p">+</div>
    </div>
    <div id="box_point">
        <span>스텟포인트</span>
        <span id="box_point_v"></span>
        <div id="box_point_p">+</div>
    </div>
    <div id="box_bar">
        <div id="box_status_bar_exp_b"></div>
        <div id="box_status_bar_exp"></div>
    </div>
    <div id="box_level">
        <span>레벨</span>
        <span id="box_level_v"></span>
    </div>
</div>