<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="blog-page area-padding">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4">
				<div class="page-head-blog">
					<!-- left nav -->
					<%@ include file="../includes/nav_left.jsp"%>
				</div>
			</div>
			<!-- End left sidebar -->
			<div class="col-md-8 col-sm-8 col-xs-12">
				<article class="blog-post-wrapper">
					<div class="detail_btn">
						<div class="update_delete">
						<c:if test="${principal.user.role=='ADMIN' || principal.user.nickname==board.writer }">
							<button type="button"
								onclick="location.href = '/class/update?bno=${board.bno}&&course=${course }&&role=${role }'">수정</button>
							<button type="button"
								onclick="location.href = '/class/delete?course=${course }&&role=${role }&&bno=${board.bno }'">삭제</button>
						</c:if>	
							<button class="list_btn" type="button"
								onclick="location.href = '/class/list?course=${course }&&role=${role }'">목록</button>
						</div>
					</div>
					<div class="text-center"></div>
					<div class="form-group mt-3">
						<h1 style="height: 70px;" name="title" id="title">${board.title }</h1>
					</div>
					<div class="post-information">
						<div style="font-size: larger;" class="entry-meta">
							<span class="author-meta"><i class="bi bi-person"></i> ${board.writer }</span> <span><i class="bi bi-clock"></i> <fmt:formatDate pattern="yy.MM.dd" value="${board.regdate }"/></span> 
								<span><i class="bi bi-chat"></i> <a href="#">${replycount }</a></span>
						</div>
						
						<div style = "display : flex; padding: 10px 0;">
						<label style = "padding: 0 5px;">파일</label>
						<ul style = "padding: 0 10px;">
				      		<c:forEach items="${list }" var="fileInfo">
				      			<li style="list-style:none; padding-bottom: 3px;">
				      			<img src="/resources/upload/icon_file_89266.png" style = "height:20px;" >
				      				<%-- <c:choose>
				      					<c:when test="${fileInfo.filetype=='image' }">
				      						<img src="${fileInfo.savefolder }/${fileInfo.savefile }" height="20" >
				      					</c:when>
				      					<c:otherwise>
 				      						<img src="upload/icon_file_89266.png" height="20" >
				      					</c:otherwise>
				      				</c:choose> --%>
				      				${fileInfo.originfile }<a class="cs_filedown" href="/class/download/${fileInfo.fno }" > <i class="bi bi-download"></i></a>
				      			</li>
				      		</c:forEach>
				      	</ul>
				      	</div>
				      	<hr>
						<div style="font-size: larger;" class="entry-content">${board.content }
						</div>
					</div>
				</article>
				<div class="clear"></div>
				<div class="single-post-comments">
					<div class="comments-area">
						<div class="comments-heading">
							<h3> <div id="replyCount" ></div>${replycount } comments</h3>
						</div>
						<div id="comments-list">
						</div>
					</div>
					<div class="comment-respond">
						<h3 class="comment-reply-title">댓글을 남겨주세요</h3>
						<!-- <span class="email-notes">Required fields are marked *</span> -->
							<div class="row">
								<div class="col-lg-4 col-md-4"></div>
								<div class="col-lg-4 col-md-4"></div>
								<div class="col-lg-4 col-md-4"></div>
								<div class="col-lg-12 col-md-12 col-sm-12 comment-form-comment">
									<textarea id="message-box" cols="30" rows="10"></textarea>
									<input type="button" id="replyBtn" value="댓글 달기" />
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var init=function() {
		$.ajax({
			type:"get",
			url:"/replies/getList/${board.bno}",
			dataType:"JSON",
			contentType:"application/json;charset=utf-8",
		}).done(function(resp) {
			
			var str="";
			$.each(resp,function(key,val){
				if("${board.writer}"==val.replyer) {
						str+="<ul><li class='threaded-comments'><div class='comments-details'><div class='comments-list-img'><img src='/img/blog/b02.jpg' alt='post-author'></div><div class='comments-content-wrap'><span><b><a style='font-size: large;' href='#'>"+val.replyer+"</a></b>"
						str+="<span style='font-size: medium;' class='post-time'>"+val.replydate+"</span>"
				} else {
					str+="<ul><li><div class='comments-details'><div class='comments-list-img'><img src='/img/blog/b02.jpg' alt='post-author'></div><div class='comments-content-wrap'><span><b><a style='font-size: large;' href='#'>"+val.replyer+"</a></b>"
					str+="<span style='font-size: medium;' class='post-time'>"+val.replydate+"</span>"
				}
				if ("${principal.user.nickname}" == val.replyer || "${principal.user.role}" == "ADMIN") {
				str+="<a style='font-size:medium;' href='javascript:rdel("+val.rno+")'>삭제</a></span>"
				}
				str+="<p style='font-size: x-large;'>"+val.reply+"</p>"
				str+="</div></div></li></ul>"
				
			})
			
			$("#comments-list").html(str);
		})
	}
	
	init();
	
	function rdel(rno) {
		$.ajax({
			type:"delete",
			url:"/replies/"+rno,
		}).done(function(resp) {
			init();
		}).fail(function() {
			alert("으 설마")
		})
	}
	
	$("#replyBtn").click(function() {
		if(${empty principal.user}) {
			alert("로그인하세요");
			location.href="/member/login";
			return;
		}
		
		var data={
			"bno":${board.bno},
			"reply":$("#message-box").val(),
			"replyer":"${principal.user.nickname}"
		}
		
		$.ajax({
			type:"post",
			url:"/replies/new",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data)
		}).done(function(resp) {
			alert("댓글 추가 성공");
			init();
		}).fail(function() {
			alert("댓글 추가 실패");
		});
	});
	

</script>

<%@ include file="../includes/footer.jsp"%>	

