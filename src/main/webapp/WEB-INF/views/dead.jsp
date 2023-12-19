<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DEAD</title>
     <c:choose>
                <c:when test="${ fileEnabled}">
                    <link href="<c:url value='/pagefull/css/dead.css'/>" rel="stylesheet" type="text/css" />
                    <link href="<c:url value='/pagefull/css/main.css'/>" rel="stylesheet" type="text/css" />
                </c:when>
                <c:otherwise>
                    <link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" type="text/css" />
    				<link href="<c:url value='/resources/css/dead.css'/>" rel="stylesheet" type="text/css" />
                </c:otherwise>
            </c:choose>
            <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/css/cont/pavicon.png">
</head>
<body>
<div id="clientUrl" data-url="<c:url value='/client/' />"></div>
<div id="rootUrl" data-url="${pageContext.request.contextPath}/"></div>
 <c:choose>
                <c:when test="${ fileEnabled}">
                    <div id="cssUrl" data-url="<c:url value='/pagefull/css/'/>"></div>
                </c:when>
                <c:otherwise>
                    <div id="cssUrl" data-url="${pageContext.request.contextPath}/resources/css/"></div>
                </c:otherwise>
            </c:choose>
            
            <div id="tomb_main">
            <div id="tomb_msg"></div>
        <div id="tomb_shadow"></div>
        <div id="tomb">
        	<div id="tomb_head">
        		<div id="tomb_hair"></div>
        		<div id="tomb_face"></div>
        	</div>
        </div>
        
   </div>  
            
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
            <script>
                $.noConflict();
                var jQ = jQuery;
            </script>
           <script type="module" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
           <script>
           	setTimeout(()=>{
           		jQ('#tomb_msg').fadeIn(2300);
           		jQ('#tomb_head').fadeIn(2300);
           		
           	},2000)
           	jQ('#tomb_msg').on('click',function(){
           		window.location.href = jQ('#rootUrl').data('url')+"home";
           	})
           </script>
                 </body>

</html>