<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../includes/header.jsp" %>    
  <main id="main">
    <!-- ======= Blog Page ======= -->
	<div class="blog-page area-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4">
					<div class="page-head-blog">
						<div class="single-blog-page">
							<!-- search option start -->
							<form action="/class/list2" method="get">
								<div class="search-option">
									<input type="hidden" placeholder="Search..." name="course" id="course" value="${course }" >
									<input type="hidden" placeholder="Search..." name="role" id="role" value="${role }" >
									<input type="text" placeholder="Search..." name="word" id="word" value="${word }"><!-- * -->
									<button class="button" type="submit" onclick="location.href='/class/list2?course=${course}&&role=${role }&&word=${word }'">
										<i class="bi bi-search"></i>
									</button>
								</div>
							</form>
							<!-- search option end -->
						</div>
						<!-- left nav -->
						<%@ include file="../includes/nav_left.jsp"%>
					</div>
				</div>
				<!-- End left sidebar -->
          <div class="col-md-8 col-sm-8 col-xs-12">
            <div class="list_botton">
              <div class = "btnPost_p">                              
                <h3><c:if test="${role=='fshare' }">파일나눔터</c:if></h3>
                <c:if test="${not empty principal.user && principal.user.role!='STUDENT' }">
                <button class = "btnPost" type="button" onclick="location.href = '/class/register?course=${course }&&role=${role }'">파일추가</button>
                </c:if>
              </div>
            </div>
            <table class = "table table-hover mt-3">
                <c:forEach begin="${count+1-(p.currentPage-1)*(p.pageSize) }" end="${count+1-(p.currentPage-1)*(p.pageSize) }" var="i">
                  <tr>
                    <th>번호</th>
                    <th>파일</th>
                    <th>글쓴이</th>
                    <th>등록일</th>
	               <th></th>
	               </tr>
		                <c:forEach items="${list}" var="file" >
		                	<tr>
		                		<td>${i=i-1 }</td>
		                		<td class = 'list_title'>${file.originfile } <a href="/class/download/${file.fno }" ><i class="bi bi-download"></i></a></td>
		                		<td>${file.writer }</td>
		                		<td><fmt:formatDate pattern="yy.MM.dd" value="${file.regdate }" /></td>
		                		<td><c:if test="${principal.user.nickname==file.writer || principal.user.role == 'ADMIN'  }"><input class = "btnFileDelete" type="button" value="x" onclick="location.href='/class/fileDelete?fno=${file.fno}&&course=${course }&&role=${role }'"></c:if></td>
		                	</tr>
		                </c:forEach>
                </c:forEach>
                </table>
              <div class="csource-pagination-div">
                <ul class="csource-pagination-ul">
					<c:if test="${word != null}">
					<!-- 이전 -->
                	<c:if test="${p.startPage > p.blockSize }">
						<li class="page-item"><a class="page-link" href="/class/list2?pageNum=${p.endPage-p.blockSize }&&course=${course}&&role=${role}&&word=${word}">&lt;</a></li>			
					</c:if>
					<!--페이지 리스트-->
					<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i" >
						<li class="page-item"><a class="page-link" href="/class/list2?pageNum=${i }&&course=${course}&&role=${role}&&word=${word}" >${i }</a></li>
					</c:forEach>
					<!-- 다음 -->
					<c:if test="${p.endPage < p.totPage }">
						<li class="page-item"><a class="page-link" href="/class/list2?pageNum=${p.endPage+1 }&&course=${course}&&role=${role}&&word=${word}">&gt;</a></li>				
					</c:if>
                </c:if>
                <c:if test="${word == null }">
                	<!-- 이전 -->
					<c:if test="${p.startPage > p.blockSize }">
						<li class="page-item"><a class="page-link" href="/class/list?pageNum=${p.endPage-p.blockSize+1 }&&course=${course}&&role=${role}&&word=${word}">&lt;</a></li>
					</c:if>
					<!--페이지 리스트-->
					<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i" >
					<li class="page-item"><a class="page-link" href="/class/list?pageNum=${i }&&course=${course}&&role=${role}&&word=${word}" >${i }</a></li>
					</c:forEach>
					<!-- 다음 -->
					<c:if test="${p.endPage < p.totPage }">
						<li class="page-item"><a class="page-link" href="/class/list?pageNum=${p.endPage+1 }&&course=${course}&&role=${role}&&word=${word}">&gt;</a></li>				
					</c:if>
                </c:if>
					</ul>
              </div>
              <div class="clear"></div>
				<div class="single-post-comments">
					<div class="comments-area">
						<div class="comments-heading">
							<h3> <div id="replyCount" ></div>community</h3>
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
    <!-- End Blog Page -->

  </main>
  <!-- End #main -->
  
  <script type="text/javascript">

	var init=function() {
		$.ajax({
			type:"get",
			url:"/replies/getList/0",
			dataType:"JSON",
			contentType:"application/json;charset=utf-8",
		}).done(function(resp) {
			
			var str="";
			$.each(resp,function(key,val){
				if("${principal.user.nickname}"==val.replyer) {
					str += "<div class='fshare-comments-list'><ul><li class='fshare-threaded-comments'><div class='comments-details'><div class='fshare-comments-content-wrap'><span class='fshare-comments-nickname'><b><a style='font-size: large;' href='#'>"+val.replyer+"</a></b></span><div class='fshare-comments-contents-self'>"
					if ( "${principal.user.nickname}" ==val.replyer || "${principal.user.role}"=="ADMIN") {
					str += "<a style='font-size: medium;' href='javascript:rdel("+val.rno+")'>&nbsp;x&nbsp;</a>"
					}
					str += "<span class='fshare-post-time'><span class='post-time'>"+val.replydate+"</span></span><p style='font-size: large;'>"+val.reply+"</p></div></div></div></li></ul></div>"
				} else {
					str += "<ul><li><div class='comments-details'><div class='fshare-comments-content-wrap'><span><b><a style='font-size: large;' href='#'>"+val.replyer+"</a></b></span>"
					str += "<div class = 'fshare-comments-contents'><p style='font-size: large;'>"+val.reply+"</p><span class='fshare-post-time'><span class='post-time'>"+val.replydate+"</span></span>"
					if ("${principal.user.role}"=="ADMIN") {
					str += "<a style='font-size: large;' href='javascript:rdel("+val.rno+")'>&nbsp;x&nbsp;</a>"
					}
					str += "</div></div></div></li></ul>"
				}
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
			"bno":"${board.bno}",
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
  
  <%@ include file="../includes/footer.jsp" %>