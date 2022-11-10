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
							<span class="author-meta"><i class="bi bi-person"></i> <a
								href="#"> ${board.writer }</a></span> <span><i class="bi bi-clock"></i> <fmt:formatDate pattern="yy.MM.dd" value="${board.regdate }"/></span> 
								<span><i class="bi bi-chat"></i> <a href="#">${fileCount }</a></span>
						</div>
						<div style="font-size: larger; height : 100px;" class="entry-content">${board.content }
						</div>
					</div>
					<hr>
				</article>
				<div class="clear"></div>
				<div class="single-post-comments">
					<div class="comments-area">
						<div class="comments-heading">
							<h3> <div id="replyCount" ></div>${fileCount } comments</h3>
						</div>
						<div id="comments-list">
						</div>
					</div>
					<div class="comment-respond">
						<h3 class="comment-reply-title">과제를 올려주세요</h3>
						<!-- <span class="email-notes">Required fields are marked *</span> -->
							<div style="height: 50px; line-height: 60px; font-size: larger;"
							class="entry-meta">
							<span class="author-meta" style = "font-size: x-large; line-height:24px;"><i class="bi bi-person"></i> ${principal.user.nickname }</span>
					</div>
					<form id="uploadForm" enctype="multipart/form-data">
						<div class="form-group mt-3 col-xs-12">
						<input class = "form-control" style="height: 40px; font-size: large;" type = "file" name = "uploads[]" id = "uploads" multiple />
						</div>
							<input type="hidden" name="writer" value="${principal.user.nickname }">
							<input type="hidden" name="role" value="${role }">
							<input type="hidden" name="course" value="${course }">
							<input type="hidden" name="bno" value="${bno }">
						<div class="entry-content form-floating form-group mt-3 col-xs-12">
							<textarea class="form-control" placeholder="Leave a comment here"
								id="floatingTextarea2" name="content" style="height: 100px"></textarea>
							<label for="floatingTextarea">글 내용</label>
						</div>
						<div class="text-center">
							<button class = "list_btn" type="button" id="tout_btn">전송</button>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	var init = function() {
		
		$.ajax({
			type:"get",
			url:"/class/getFileList/${bno}/${role}",
			dataType:"JSON",
			contentType:"application/json;charset=utf-8",
		}).done(function(res) {
			var str="";
			$.each(res, function(key,val){
				if(${principal.user.role!='ADMIN'}) {
					if("${principal.user.nickname}"==val.writer) {
						str += "<div style = 'display : flex;'>"
						str+="<p><i class='bi bi-person'></i> "+val.writer+"</p>";
						str+="<p class = 'tout_download'><i class='bi bi-folder'></i> "+val.originfile+" <a href='/class/download/"+val.fno+"'><i class='bi bi-download'></i></a></p>";
						str += "</div>"
						str+="<p>"+val.content+"</p>";
						str += "<hr>"
					} else {
						str+="<p><i class='bi bi-person'></i> "+val.writer+"</p>";
						str+="<p>비공개 댓글입니다.</p>";
						str += "<hr>"
					}
				} else {
					str += "<div style = 'display : flex;'>"
					str+="<p style = 'padding : 0 5px;'><i class='bi bi-person'></i> "+val.writer+"</p>";
					str+="<p style = 'padding : 0 5px;' class = 'tout_download'><i class='bi bi-folder'></i> "+val.originfile+ " <a href='/class/download/"+val.fno+"'><i class='bi bi-download'></i></a></p>";
					str += "</div>"
					str+="<p>"+val.content+"</p>";
					str += "<hr>"
				}
			})
			
			$("#comments-list").html(str);
		})
	}
	
	init();
	
	var filesArry=[];
	
	function addFile(files) {
		fileTempArry=[];
		
		var fileArry = Array.prototype.slice.call(files);
		
		for(var i=0;i<fileArry.length; i++)
			fileTempArry.push(fileArry[i]);
	}

	
	$("#tout_btn").click(function() {
		if(${empty principal.user}) {
			alert("로그인하세요");
			location.href="/member/login";
			return;
		}
		
		var data = new FormData($("#uploadForm")[0]);
		
		data.append("writer", $('#writer'));
		data.append("role", $('#role'));
		data.append("content", $('#content'));
		data.append("bno", $('#bno'));
		
		for(var i=0; i<fileTempArry.length; i++)
			data.append("files", fileTempArry[i]);
		
		$.ajax({
			type:"post",
			url:"/class/replyFile",
			processData:false,
			contentType:false,
			data:data,
		}).done(function(resp) {
			alert("댓글 추가 성공");
			init();
		}).fail(function() {
			alert("댓글 추가 실패");
		});
	});
	
	
	$(document).ready(function() {
		$("#uploads").on("change", function() {
			addFile(this.files);
		})
	})
	

</script>

<%@ include file="../includes/footer.jsp"%>