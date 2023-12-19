<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RSP</title>
                <c:choose>
                <c:when test="${ fileEnabled}">
                    <link href="<c:url value='/pagefull/css/rsp.css'/>" rel="stylesheet" type="text/css" />
                    <link href="<c:url value='/pagefull/css/main.css'/>" rel="stylesheet" type="text/css" />
                </c:when>
                <c:otherwise>
                    <link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" type="text/css" />
    				<link href="<c:url value='/resources/css/rsp.css'/>" rel="stylesheet" type="text/css" />
                </c:otherwise>
            </c:choose>
            <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/css/cont/pavicon.png">
</head>

<body>
<jsp:include page="./insert/all.jsp"></jsp:include>
<jsp:include page="./insert/player.jsp"></jsp:include>                         
 <c:choose>
                <c:when test="${ fileEnabled}">
                    <div id="cssUrl" data-url="<c:url value='/pagefull/css/'/>"></div>
                </c:when>
                <c:otherwise>
                    <div id="cssUrl" data-url="${pageContext.request.contextPath}/resources/css/"></div>
                </c:otherwise>
            </c:choose>

<input id="sessionId" value="${sessionScope.joinedName}" type="hidden"/>
<div id="globalLoad">
        <div id="globalLoad_context"></div>
    </div>
 <div id="error">
        <div id="error_button"></div>
    </div>
    <div id="ir_back">
        <div class="ir_back_front"></div>
        <div class="ir_back_front"></div>
        <div class="ir_back_front"></div>
        <div class="ir_back_front"></div>
        <div id="ir_b_tit">
            <div class="cr_b_tit_in cr_b_tit_i_l"></div>
            <div class="cr_b_tit_in cr_b_tit_i_r"></div>
        </div>
    </div>
    <div id="ir_m">
        <div id="ir_party_main">
            <div id="ir_party_lv">
                <span id="ir_party_lv_t">Lv</span>
                <span id="ir_party_lv_v">1</span>
            </div>
            <div id="ir_party_data">
                <span id="ir_party_data_r">0</span>
                <span id="ir_party_data_w">0</span>
                <span id="ir_party_data_l">0</span>
                <span id="ir_party_data_d">0</span>
            </div>
            <div id="ir_status_bar">
                <div id="ir_status_bar_hp_b"></div>
                <div id="ir_status_bar_hp"></div>
                <div id="ir_status_bar_exp_b"></div>
                <div id="ir_status_bar_exp">
                    
                </div>
            </div>
            <div id="ir_party_newButton"></div>
            <div id="ir_party_done">

            </div>
            <div id="ir_party_room_all">
               
            </div>
        </div>
    <div id="ir_party_board">
            <div id="ir_party_board_ch0"></div>
            <div id="ir_party_board_ch1"></div>
            <div id="ir_party_board_ch2"></div>

            <input type="text" autocomplete="off" maxlength="17" id="ir_party_board_input" name="ir_party_board_input">
            <div id="ir_party_board_de" class="ir_party_board_bt"></div>
            <div id="ir_party_board_in" class="ir_party_board_bt"></div>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
            <script>
                $.noConflict();
                var jQ = jQuery;
            </script>

            <script type="module" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
            <script type="module" src="${pageContext.request.contextPath}/resources/js/rsp.js"></script>
            <script type="module" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
</body>

</html>