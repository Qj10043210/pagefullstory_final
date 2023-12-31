<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SDOKU</title>
    <c:choose>
                <c:when test="${ fileEnabled}">
                    <link href="<c:url value='/pagefull/css/sdoku1.css'/>" rel="stylesheet" type="text/css" />
                    <link href="<c:url value='/pagefull/css/sdoku2.css'/>" rel="stylesheet" type="text/css" />
                    <link href="<c:url value='/pagefull/css/main.css'/>" rel="stylesheet" type="text/css" />
                </c:when>
                <c:otherwise>
                    <link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" type="text/css" />
    				<link href="<c:url value='/resources/css/sdoku1.css'/>" rel="stylesheet" type="text/css" />
    				<link href="<c:url value='/resources/css/sdoku2.css'/>" rel="stylesheet" type="text/css" />
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


    <div id="title"></div>
    <div  id="table">
        <div id="leftNav">
        
            <div id="optionbox">
                <p><input type="radio" name="options" id="opnone" checked value="0"><label for="opnone">none</label></p>
                <p><input type="radio" name="options" id="ophori" value="1"><label for="ophori">horizne</label></p>
                <p><input type="radio" name="options" id="opvert" value="2"><label for="opvert">vertical</label></p>
                <p><input type="radio" name="options" id="opbox" value="3"><label for="opbox">box</label></p>
                <p><input type="radio" name="options" id="opcros" value="4"><label for="opcros">cross</label></p>
        
            </div>
            <div id="buttonBox">
                <div id="test">NEW BOARD</div>
                <div id="try">MAKE BLANK</div>
                <div id="resetplease">SHOW ME ANSWER</div>
                <div id="retry">DEFAULT THE BOARD</div>
                <div id="showmeanswer">CHECK</div>
                <div id="wechecknum">here nums</div>
                <div id="save">SAVE The Board</div>
                <div id="showbox">▽▽save complete▽▽</div>
            </div>
            <div id="saveBox">
                <ul id="saveBoxUl">
                    
                </ul>
            </div>
        </div>
        <table>
           <tbody>
           
                <tr class="line">
                    <td class="A0 A1 A3 T1">
                        
                    </td>
                    <td class="A0 B1 A3 T2"></td>
                    <td class="A0 C1 A3 T3"></td>
                    <td class="A0 D1 B3 T4"></td>
                    <td class="A0 E1 B3 T5"></td>
                    <td class="A0 F1 B3 T6"></td>
                    <td class="A0 G1 C3 T7"></td>
                    <td class="A0 H1 C3 T8"></td>
                    <td class="A0 I1 C3 T9"></td>
                </tr>
                <tr class="line">
                    <td class="B0 A1 A3 T10"></td>
                    <td class="B0 B1 A3 T11"></td>
                    <td class="B0 C1 A3 T12"></td>
                    <td class="B0 D1 B3 T13"></td>
                    <td class="B0 E1 B3 T14"></td>
                    <td class="B0 F1 B3 T15"></td>
                    <td class="B0 G1 C3 T16"></td>
                    <td class="B0 H1 C3 T17"></td>
                    <td class="B0 I1 C3 T18"></td>
                </tr>
                <tr class="line">
                    <td class="C0 A1 A3 T19"></td>
                    <td class="C0 B1 A3 T20"></td>
                    <td class="C0 C1 A3 T21"></td>
                    <td class="C0 D1 B3 T22"></td>
                    <td class="C0 E1 B3 T23"></td>
                    <td class="C0 F1 B3 T24"></td>
                    <td class="C0 G1 C3 T25"></td>
                    <td class="C0 H1 C3 T26"></td>
                    <td class="C0 I1 C3 T27"></td>
                </tr>
                <tr class="line">
                    <td class="D0 A1 D3 T28"></td>
                    <td class="D0 B1 D3 T29"></td>
                    <td class="D0 C1 D3 T30"></td>
                    <td class="D0 D1 E3 T31"></td>
                    <td class="D0 E1 E3 T32"></td>
                    <td class="D0 F1 E3 T33"></td>
                    <td class="D0 G1 F3 T34"></td>
                    <td class="D0 H1 F3 T35"></td>
                    <td class="D0 I1 F3 T36"></td>
                </tr>
                <tr class="line">
                    <td class="E0 A1 D3 T37"></td>
                    <td class="E0 B1 D3 T38"></td>
                    <td class="E0 C1 D3 T39"></td>
                    <td class="E0 D1 E3 T40"></td>
                    <td class="E0 E1 E3 T41"></td>
                    <td class="E0 F1 E3 T42"></td>
                    <td class="E0 G1 F3 T43"></td>
                    <td class="E0 H1 F3 T44"></td>
                    <td class="E0 I1 F3 T45"></td>
                </tr>
                <tr class="line">
                    <td class="F0 A1 D3 T46"></td>
                    <td class="F0 B1 D3 T47"></td>
                    <td class="F0 C1 D3 T48"></td>
                    <td class="F0 D1 E3 T49"></td>
                    <td class="F0 E1 E3 T50"></td>
                    <td class="F0 F1 E3 T51"></td>
                    <td class="F0 G1 F3 T52"></td>
                    <td class="F0 H1 F3 T53"></td>
                    <td class="F0 I1 F3 T54"></td>
                </tr>
                <tr class="line">
                    <td class="G0 A1 G3 T55"></td>
                    <td class="G0 B1 G3 T56"></td>
                    <td class="G0 C1 G3 T57"></td>
                    <td class="G0 D1 H3 T58"></td>
                    <td class="G0 E1 H3 T59"></td>
                    <td class="G0 F1 H3 T60"></td>
                    <td class="G0 G1 I3 T61"></td>
                    <td class="G0 H1 I3 T62"></td>
                    <td class="G0 I1 I3 T63"></td>
                </tr>
                <tr class="line">
                    <td class="H0 A1 G3 T64"></td>
                    <td class="H0 B1 G3 T65"></td>
                    <td class="H0 C1 G3 T66"></td>
                    <td class="H0 D1 H3 T67"></td>
                    <td class="H0 E1 H3 T68"></td>
                    <td class="H0 F1 H3 T69"></td>
                    <td class="H0 G1 I3 T70"></td>
                    <td class="H0 H1 I3 T71"></td>
                    <td class="H0 I1 I3 T72"></td>
                </tr>
                <tr class="line">
                    <td class="I0 A1 G3 T73"></td>
                    <td class="I0 B1 G3 T74"></td>
                    <td class="I0 C1 G3 T75"></td>
                    <td class="I0 D1 H3 T76"></td>
                    <td class="I0 E1 H3 T77"></td>
                    <td class="I0 F1 H3 T78"></td>
                    <td class="I0 G1 I3 T79"></td>
                    <td class="I0 H1 I3 T80"></td>
                    <td class="I0 I1 I3 T81"></td>
                </tr>
           </tbody>
        </table>
        <div id="messageHere">
            <div id="realhere"></div>
            <div id="bottomhere">
                <div id="bottomL"></div>
                <div id="bottomR"></div>
            </div>
        </div>
    </div>
    <div id="hidingg">
        Made by Jin
        Lateset Version:2023-09-12
    </div>
    
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/resources/js/sdoku.js"></script>
    <script>
        const wSize = $('body').width();
        if (wSize<720){
            let a = $('table')
            $('table').remove();
            $('#table').prepend(a)
            

        }
        else{
            let optbox = $('#optionbox').width();
        $('#optionbox').css({
            width : optbox
        })
        optbox = optbox
        $('#leftNav').css({
            left : -optbox - 30
        })
            $('table').css({
                width:'900px',
                height:'900px'
            })
            $('#leftNav').css({
                top:'0px',
                marginLeft: '0px'
            })
            $('#mainbox').css({
                width:'832px',
                
            })
        }

        

    </script>

</body>
</html>