<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style>
	@keyframes gradientAnimation {
  0% {background: linear-gradient(90deg, rgba(255, 255, 0, 1) 0%, rgba(37, 255, 12, 1) 55%, rgba(255, 0, 0, 1) 100%);}

1% {background: linear-gradient(90deg, rgba(248, 255, 0, 1) 0%, rgba(43, 247, 11, 1) 55%, rgba(255, 7, 0, 1) 100%);}

2% {background: linear-gradient(90deg, rgba(241, 255, 0, 1) 0%, rgba(50, 239, 11, 1) 55%, rgba(255, 15, 0, 1) 100%);}

3% {background: linear-gradient(90deg, rgba(235, 255, 1, 1) 0%, rgba(56, 231, 10, 1) 55%, rgba(255, 23, 0, 1) 100%);}

4% {background: linear-gradient(90deg, rgba(228, 255, 1, 1) 0%, rgba(63, 224, 10, 1) 55%, rgba(255, 30, 0, 1) 100%);}

5% {background: linear-gradient(90deg, rgba(221, 255, 1, 1) 0%, rgba(70, 216, 10, 1) 55%, rgba(255, 38, 0, 1) 100%);}

6% {background: linear-gradient(90deg, rgba(215, 255, 2, 1) 0%, rgba(76, 208, 9, 1) 55%, rgba(255, 46, 0, 1) 100%);}

7% {background: linear-gradient(90deg, rgba(208, 255, 2, 1) 0%, rgba(83, 200, 9, 1) 55%, rgba(255, 54, 0, 1) 100%);}

8% {background: linear-gradient(90deg, rgba(202, 255, 2, 1) 0%, rgba(89, 193, 9, 1) 55%, rgba(255, 61, 0, 1) 100%);}

9% {background: linear-gradient(90deg, rgba(195, 255, 3, 1) 0%, rgba(96, 185, 8, 1) 55%, rgba(255, 69, 0, 1) 100%);}

10% {background: linear-gradient(90deg, rgba(188, 255, 3, 1) 0%, rgba(103, 177, 8, 1) 55%, rgba(255, 77, 0, 1) 100%);}

11% {background: linear-gradient(90deg, rgba(182, 255, 4, 1) 0%, rgba(109, 170, 8, 1) 55%, rgba(255, 85, 0, 1) 100%);}

12% {background: linear-gradient(90deg, rgba(175, 255, 4, 1) 0%, rgba(116, 162, 7, 1) 55%, rgba(255, 92, 0, 1) 100%);}

13% {background: linear-gradient(90deg, rgba(169, 255, 4, 1) 0%, rgba(122, 154, 7, 1) 55%, rgba(255, 100, 0, 1) 100%);}

14% {background: linear-gradient(90deg, rgba(162, 255, 5, 1) 0%, rgba(129, 146, 6, 1) 55%, rgba(255, 108, 0, 1) 100%);}

15% {background: linear-gradient(90deg, rgba(155, 255, 5, 1) 0%, rgba(136, 139, 6, 1) 55%, rgba(255, 115, 0, 1) 100%);}

16% {background: linear-gradient(90deg, rgba(149, 255, 5, 1) 0%, rgba(142, 131, 6, 1) 55%, rgba(255, 123, 0, 1) 100%);}

17% {background: linear-gradient(90deg, rgba(142, 255, 6, 1) 0%, rgba(149, 123, 5, 1) 55%, rgba(255, 131, 0, 1) 100%);}

18% {background: linear-gradient(90deg, rgba(136, 255, 6, 1) 0%, rgba(155, 115, 5, 1) 55%, rgba(255, 139, 0, 1) 100%);}

19% {background: linear-gradient(90deg, rgba(129, 255, 6, 1) 0%, rgba(162, 108, 5, 1) 55%, rgba(255, 146, 0, 1) 100%);}

20% {background: linear-gradient(90deg, rgba(122, 255, 7, 1) 0%, rgba(169, 100, 4, 1) 55%, rgba(255, 154, 0, 1) 100%);}

21% {background: linear-gradient(90deg, rgba(116, 255, 7, 1) 0%, rgba(175, 92, 4, 1) 55%, rgba(255, 162, 0, 1) 100%);}

22% {background: linear-gradient(90deg, rgba(109, 255, 8, 1) 0%, rgba(182, 85, 4, 1) 55%, rgba(255, 170, 0, 1) 100%);}

23% {background: linear-gradient(90deg, rgba(103, 255, 8, 1) 0%, rgba(188, 77, 3, 1) 55%, rgba(255, 177, 0, 1) 100%);}

24% {background: linear-gradient(90deg, rgba(96, 255, 8, 1) 0%, rgba(195, 69, 3, 1) 55%, rgba(255, 185, 0, 1) 100%);}

25% {background: linear-gradient(90deg, rgba(89, 255, 9, 1) 0%, rgba(202, 61, 2, 1) 55%, rgba(255, 193, 0, 1) 100%);}

26% {background: linear-gradient(90deg, rgba(83, 255, 9, 1) 0%, rgba(208, 54, 2, 1) 55%, rgba(255, 200, 0, 1) 100%);}

27% {background: linear-gradient(90deg, rgba(76, 255, 9, 1) 0%, rgba(215, 46, 2, 1) 55%, rgba(255, 208, 0, 1) 100%);}

28% {background: linear-gradient(90deg, rgba(70, 255, 10, 1) 0%, rgba(221, 38, 1, 1) 55%, rgba(255, 216, 0, 1) 100%);}

29% {background: linear-gradient(90deg, rgba(63, 255, 10, 1) 0%, rgba(228, 30, 1, 1) 55%, rgba(255, 224, 0, 1) 100%);}

30% {background: linear-gradient(90deg, rgba(56, 255, 10, 1) 0%, rgba(235, 23, 1, 1) 55%, rgba(255, 231, 0, 1) 100%);}

31% {background: linear-gradient(90deg, rgba(50, 255, 11, 1) 0%, rgba(241, 15, 0, 1) 55%, rgba(255, 239, 0, 1) 100%);}

32% {background: linear-gradient(90deg, rgba(43, 255, 11, 1) 0%, rgba(248, 7, 0, 1) 55%, rgba(255, 247, 0, 1) 100%);}

33% {background: linear-gradient(90deg, rgba(37, 255, 12, 1) 0%, rgba(255, 0, 0, 1) 55%, rgba(255, 255, 0, 1) 100%);}

34% {background: linear-gradient(90deg, rgba(37, 255, 12, 1) 0%, rgba(255, 0, 0, 1) 55%, rgba(255, 255, 0, 1) 100%);}

35% {background: linear-gradient(90deg, rgba(43, 247, 11, 1) 0%, rgba(255, 7, 0, 1) 55%, rgba(248, 255, 0, 1) 100%);}

36% {background: linear-gradient(90deg, rgba(50, 239, 11, 1) 0%, rgba(255, 15, 0, 1) 55%, rgba(241, 255, 0, 1) 100%);}

37% {background: linear-gradient(90deg, rgba(56, 231, 10, 1) 0%, rgba(255, 23, 0, 1) 55%, rgba(235, 255, 1, 1) 100%);}

38% {background: linear-gradient(90deg, rgba(63, 224, 10, 1) 0%, rgba(255, 30, 0, 1) 55%, rgba(228, 255, 1, 1) 100%);}

39% {background: linear-gradient(90deg, rgba(70, 216, 10, 1) 0%, rgba(255, 38, 0, 1) 55%, rgba(221, 255, 1, 1) 100%);}

40% {background: linear-gradient(90deg, rgba(76, 208, 9, 1) 0%, rgba(255, 46, 0, 1) 55%, rgba(215, 255, 2, 1) 100%);}

41% {background: linear-gradient(90deg, rgba(83, 200, 9, 1) 0%, rgba(255, 54, 0, 1) 55%, rgba(208, 255, 2, 1) 100%);}

42% {background: linear-gradient(90deg, rgba(89, 193, 9, 1) 0%, rgba(255, 61, 0, 1) 55%, rgba(202, 255, 2, 1) 100%);}

43% {background: linear-gradient(90deg, rgba(96, 185, 8, 1) 0%, rgba(255, 69, 0, 1) 55%, rgba(195, 255, 3, 1) 100%);}

44% {background: linear-gradient(90deg, rgba(103, 177, 8, 1) 0%, rgba(255, 77, 0, 1) 55%, rgba(188, 255, 3, 1) 100%);}

45% {background: linear-gradient(90deg, rgba(109, 170, 8, 1) 0%, rgba(255, 85, 0, 1) 55%, rgba(182, 255, 4, 1) 100%);}

46% {background: linear-gradient(90deg, rgba(116, 162, 7, 1) 0%, rgba(255, 92, 0, 1) 55%, rgba(175, 255, 4, 1) 100%);}

47% {background: linear-gradient(90deg, rgba(122, 154, 7, 1) 0%, rgba(255, 100, 0, 1) 55%, rgba(169, 255, 4, 1) 100%);}

48% {background: linear-gradient(90deg, rgba(129, 146, 6, 1) 0%, rgba(255, 108, 0, 1) 55%, rgba(162, 255, 5, 1) 100%);}

49% {background: linear-gradient(90deg, rgba(136, 139, 6, 1) 0%, rgba(255, 115, 0, 1) 55%, rgba(155, 255, 5, 1) 100%);}

50% {background: linear-gradient(90deg, rgba(142, 131, 6, 1) 0%, rgba(255, 123, 0, 1) 55%, rgba(149, 255, 5, 1) 100%);}

51% {background: linear-gradient(90deg, rgba(149, 123, 5, 1) 0%, rgba(255, 131, 0, 1) 55%, rgba(142, 255, 6, 1) 100%);}

52% {background: linear-gradient(90deg, rgba(155, 115, 5, 1) 0%, rgba(255, 139, 0, 1) 55%, rgba(136, 255, 6, 1) 100%);}

53% {background: linear-gradient(90deg, rgba(162, 108, 5, 1) 0%, rgba(255, 146, 0, 1) 55%, rgba(129, 255, 6, 1) 100%);}

54% {background: linear-gradient(90deg, rgba(169, 100, 4, 1) 0%, rgba(255, 154, 0, 1) 55%, rgba(122, 255, 7, 1) 100%);}

55% {background: linear-gradient(90deg, rgba(175, 92, 4, 1) 0%, rgba(255, 162, 0, 1) 55%, rgba(116, 255, 7, 1) 100%);}

56% {background: linear-gradient(90deg, rgba(182, 85, 4, 1) 0%, rgba(255, 170, 0, 1) 55%, rgba(109, 255, 8, 1) 100%);}

57% {background: linear-gradient(90deg, rgba(188, 77, 3, 1) 0%, rgba(255, 177, 0, 1) 55%, rgba(103, 255, 8, 1) 100%);}

58% {background: linear-gradient(90deg, rgba(195, 69, 3, 1) 0%, rgba(255, 185, 0, 1) 55%, rgba(96, 255, 8, 1) 100%);}

59% {background: linear-gradient(90deg, rgba(202, 61, 2, 1) 0%, rgba(255, 193, 0, 1) 55%, rgba(89, 255, 9, 1) 100%);}

60% {background: linear-gradient(90deg, rgba(208, 54, 2, 1) 0%, rgba(255, 200, 0, 1) 55%, rgba(83, 255, 9, 1) 100%);}

61% {background: linear-gradient(90deg, rgba(215, 46, 2, 1) 0%, rgba(255, 208, 0, 1) 55%, rgba(76, 255, 9, 1) 100%);}

62% {background: linear-gradient(90deg, rgba(221, 38, 1, 1) 0%, rgba(255, 216, 0, 1) 55%, rgba(70, 255, 10, 1) 100%);}

63% {background: linear-gradient(90deg, rgba(228, 30, 1, 1) 0%, rgba(255, 224, 0, 1) 55%, rgba(63, 255, 10, 1) 100%);}

64% {background: linear-gradient(90deg, rgba(235, 23, 1, 1) 0%, rgba(255, 231, 0, 1) 55%, rgba(56, 255, 10, 1) 100%);}

65% {background: linear-gradient(90deg, rgba(241, 15, 0, 1) 0%, rgba(255, 239, 0, 1) 55%, rgba(50, 255, 11, 1) 100%);}

66% {background: linear-gradient(90deg, rgba(248, 7, 0, 1) 0%, rgba(255, 247, 0, 1) 55%, rgba(43, 255, 11, 1) 100%);}

67% {background: linear-gradient(90deg, rgba(255, 0, 0, 1) 0%, rgba(255, 255, 0, 1) 55%, rgba(37, 255, 12, 1) 100%);}

68% {background: linear-gradient(90deg, rgba(255, 7, 0, 1) 0%, rgba(248, 255, 0, 1) 55%, rgba(43, 247, 11, 1) 100%);}

69% {background: linear-gradient(90deg, rgba(255, 15, 0, 1) 0%, rgba(241, 255, 0, 1) 55%, rgba(50, 239, 11, 1) 100%);}

70% {background: linear-gradient(90deg, rgba(255, 23, 0, 1) 0%, rgba(235, 255, 1, 1) 55%, rgba(56, 231, 10, 1) 100%);}

71% {background: linear-gradient(90deg, rgba(255, 30, 0, 1) 0%, rgba(228, 255, 1, 1) 55%, rgba(63, 224, 10, 1) 100%);}

72% {background: linear-gradient(90deg, rgba(255, 38, 0, 1) 0%, rgba(221, 255, 1, 1) 55%, rgba(70, 216, 10, 1) 100%);}

73% {background: linear-gradient(90deg, rgba(255, 46, 0, 1) 0%, rgba(215, 255, 2, 1) 55%, rgba(76, 208, 9, 1) 100%);}

74% {background: linear-gradient(90deg, rgba(255, 54, 0, 1) 0%, rgba(208, 255, 2, 1) 55%, rgba(83, 200, 9, 1) 100%);}

75% {background: linear-gradient(90deg, rgba(255, 61, 0, 1) 0%, rgba(202, 255, 2, 1) 55%, rgba(89, 193, 9, 1) 100%);}

76% {background: linear-gradient(90deg, rgba(255, 69, 0, 1) 0%, rgba(195, 255, 3, 1) 55%, rgba(96, 185, 8, 1) 100%);}

77% {background: linear-gradient(90deg, rgba(255, 77, 0, 1) 0%, rgba(188, 255, 3, 1) 55%, rgba(103, 177, 8, 1) 100%);}

78% {background: linear-gradient(90deg, rgba(255, 85, 0, 1) 0%, rgba(182, 255, 4, 1) 55%, rgba(109, 170, 8, 1) 100%);}

79% {background: linear-gradient(90deg, rgba(255, 92, 0, 1) 0%, rgba(175, 255, 4, 1) 55%, rgba(116, 162, 7, 1) 100%);}

80% {background: linear-gradient(90deg, rgba(255, 100, 0, 1) 0%, rgba(169, 255, 4, 1) 55%, rgba(122, 154, 7, 1) 100%);}

81% {background: linear-gradient(90deg, rgba(255, 108, 0, 1) 0%, rgba(162, 255, 5, 1) 55%, rgba(129, 146, 6, 1) 100%);}

82% {background: linear-gradient(90deg, rgba(255, 115, 0, 1) 0%, rgba(155, 255, 5, 1) 55%, rgba(136, 139, 6, 1) 100%);}

83% {background: linear-gradient(90deg, rgba(255, 123, 0, 1) 0%, rgba(149, 255, 5, 1) 55%, rgba(142, 131, 6, 1) 100%);}

84% {background: linear-gradient(90deg, rgba(255, 131, 0, 1) 0%, rgba(142, 255, 6, 1) 55%, rgba(149, 123, 5, 1) 100%);}

85% {background: linear-gradient(90deg, rgba(255, 139, 0, 1) 0%, rgba(136, 255, 6, 1) 55%, rgba(155, 115, 5, 1) 100%);}

86% {background: linear-gradient(90deg, rgba(255, 146, 0, 1) 0%, rgba(129, 255, 6, 1) 55%, rgba(162, 108, 5, 1) 100%);}

87% {background: linear-gradient(90deg, rgba(255, 154, 0, 1) 0%, rgba(122, 255, 7, 1) 55%, rgba(169, 100, 4, 1) 100%);}

88% {background: linear-gradient(90deg, rgba(255, 162, 0, 1) 0%, rgba(116, 255, 7, 1) 55%, rgba(175, 92, 4, 1) 100%);}

89% {background: linear-gradient(90deg, rgba(255, 170, 0, 1) 0%, rgba(109, 255, 8, 1) 55%, rgba(182, 85, 4, 1) 100%);}

90% {background: linear-gradient(90deg, rgba(255, 177, 0, 1) 0%, rgba(103, 255, 8, 1) 55%, rgba(188, 77, 3, 1) 100%);}

91% {background: linear-gradient(90deg, rgba(255, 185, 0, 1) 0%, rgba(96, 255, 8, 1) 55%, rgba(195, 69, 3, 1) 100%);}

92% {background: linear-gradient(90deg, rgba(255, 193, 0, 1) 0%, rgba(89, 255, 9, 1) 55%, rgba(202, 61, 2, 1) 100%);}

93% {background: linear-gradient(90deg, rgba(255, 200, 0, 1) 0%, rgba(83, 255, 9, 1) 55%, rgba(208, 54, 2, 1) 100%);}

94% {background: linear-gradient(90deg, rgba(255, 208, 0, 1) 0%, rgba(76, 255, 9, 1) 55%, rgba(215, 46, 2, 1) 100%);}

95% {background: linear-gradient(90deg, rgba(255, 216, 0, 1) 0%, rgba(70, 255, 10, 1) 55%, rgba(221, 38, 1, 1) 100%);}

96% {background: linear-gradient(90deg, rgba(255, 224, 0, 1) 0%, rgba(63, 255, 10, 1) 55%, rgba(228, 30, 1, 1) 100%);}

97% {background: linear-gradient(90deg, rgba(255, 231, 0, 1) 0%, rgba(56, 255, 10, 1) 55%, rgba(235, 23, 1, 1) 100%);}

98% {background: linear-gradient(90deg, rgba(255, 239, 0, 1) 0%, rgba(50, 255, 11, 1) 55%, rgba(241, 15, 0, 1) 100%);}

99% {background: linear-gradient(90deg, rgba(255, 247, 0, 1) 0%, rgba(43, 255, 11, 1) 55%, rgba(248, 7, 0, 1) 100%);}

100% {background: linear-gradient(90deg, rgba(255, 255, 0, 1) 0%, rgba(37, 255, 12, 1) 55%, rgba(255, 0, 0, 1) 100%);}
}
	#border{
		margin:0;
		padding:0;
		border:none;
		background: rgb(255,255,0);
		background: linear-gradient(90deg, rgba(255,255,0,1) 0%, rgba(37,255,12,1) 55%, rgba(255,0,0,1) 100%);
width:120%;
height:1%;
position:fixed;
left:-10%;
bottom:0%;
animation: gradientAnimation 2s linear infinite;
animation-fill-mode: forwards;
overflow:hidden;
 transition : height 0.7s;
 display:flex;
 justify-content:center;
 align-items:center;
	}
	#hello{
	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
	color:white;
	font-size:3em;
	display:none;
	text-shadow:4px 4px 7px black;
	}
	
	</style>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/css/cont/pavicon.png">
</head>
<body  id="html">
<jsp:include page="./insert/all.jsp"></jsp:include>	
<c:choose>
                <c:when test="${ fileEnabled}">                    
                    <link href="<c:url value='/pagefull/css/main.css'/>" rel="stylesheet" type="text/css" />
                </c:when>
                <c:otherwise>
                    <link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" type="text/css" />    				
                </c:otherwise>
            </c:choose>

<div id="main_load">
	<div id="main_load_msg">게임 시작하기</div>
</div>
<div id="border">
	<div id="hello">PRESS CTRL AND ALT TOGETHER!!! </div>
</div>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
            <script>
                $.noConflict();
                var jQ = jQuery;
            </script>
            <script type="module" src="${pageContext.request.contextPath}/resources/js/home.js"></script>
            <script>
            let htmls = document.getElementById("html");
            let targetDiv = document.getElementById("border");
            let targetDiv2 = document.getElementById("hello");
            var rootUrl = document.getElementById('rootUrl').getAttribute('data-url');
            let isCtrlPressed = false;

            htmls.addEventListener("keydown", (e) => {
                if (e.key === "Control") {
                    isCtrlPressed = true;
                }

                if (isCtrlPressed && e.key === "Alt") {
                    jQ.ajax({
                    	url : rootUrl + "def",
                    	type:'POST',
                    	success: function (data) {
                         
                        },
                        error: function (errorThrown) {
                            
                        }
                    })
                }
            });

            htmls.addEventListener("keyup", (e) => {
                if (e.key === "Control") {
                    isCtrlPressed = false;
                }
            });
            targetDiv.addEventListener("mouseenter", () => {
                if (isCtrlPressed) {
                    targetDiv.style.height = "10%";
                    targetDiv2.style.display = "block";
                }
            });
            targetDiv.addEventListener("mouseleave", () => {
                
                    targetDiv.style.height = "1%"; 
                    targetDiv2.style.display = "none";
                
            });
            
            </script>
</body>
</html>
