<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="includes/header.jsp"%>

<main id="main">

	<!-- ======= About Section ======= -->
	<div id="notice" class="about-area area-padding">
		<div class="container ">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="section-headline text-center">
						<h2>공지사항</h2>
					</div>
					<div class="list_botton">
						<sec:authorize access="isAuthenticated()">
							<c:if test="${principal.user.role == 'ADMIN' }">
								<div class="btnPost_p">
									<button class="btnPost" type="button" onclick = "location.href = '/register'">글쓰기</button>
								</div>
							</c:if>
						</sec:authorize>
					</div>
				</div>
			</div>
			
				<div id="homeplus" ></div>
                <div id = "more_btn_div" align = "center">
                <hr>
                	<a id = "home_btn" href = "javascript:moreContent('more_list', 2);">더보기 (More)</a>
                <hr>
                </div>
                
		</div>
	</div>
	<!-- End About Section -->
<script type="text/javascript">

	var pageNum=0;
	
	var init = function(){
	$.ajax({
		type:"get",
		url:"/list/"+pageNum,
		dataType:"JSON",
		contentType:"application/json;charset=utf-8",
	}).done(function(res){
		var str="<table class = 'table table-hover mt-3'><tr><th>번호</th><th>제목</th><th>글쓴이</th><th>등록일</th><th>조회수</th></tr>";
		var num = ${count}
		$.each(res, function(key, val){
			num=num-1
			str+="<tr><td>"+num+"</td>";
			str+="<td class = 'list_title'><a href='/detail/"+val.bno+"' >"+val.title+"</a></td>";
    		str+="<td>"+val.writer+"</td>";
    		str+="<td>"+val.regdate+"</td>";
    		str+="<td>"+val.hitcount+"</td></tr>";
		})
		str+="</table>";
		
		$("#homeplus").html(str);
		
		})

	}
	init();
	
	$("#home_btn").click(function() {
		if (pageNum + 4 >= "${count}") {
			pageNum = 0;
		} else {
			pageNum = pageNum + 4;	
		}
		init();
	})

</script>
	<%@ include file="includes/footer.jsp"%>