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
					<div class="list_btn_p">
						<button class="list_btn" type="button"
							onclick="location.href = '/class/list?course=${course }&&role=${role }'">목록</button>
					</div>
					<form action="update" method="post" role="form" class="insert-form" enctype="multipart/form-data">
						<input type = "hidden" name = "bno" id = "bno" value = "${board.bno }" />
						<input type = "hidden" name = "role" id = "role" value = "${board.role }" />
						<input type = "hidden" name = "course" id = "course" value = "${board.course }" />
						<input type = "hidden" name = "writer" id = "writer" value = "${board.writer }" />
						<div style="height: 50px; line-height: 60px; font-size: larger;"
							class="entry-meta">
							<span class="author-meta"><i class="bi bi-person"></i> ${board.writer }</span>
						</div>
						<div class="form-group mt-3 col-xs-12">
							<input style="height: 70px; font-size: larger;" type="text"
								name="title" class="form-control" id="title"
								placeholder="제목을 입력하세요" value = "${board.title }" required>
						</div>
						<div class="entry-content form-floating form-group mt-3 col-xs-12">
							<textarea class="form-control" id="floatingTextarea2" name="content" >${board.content }</textarea>
							<label for="floatingTextarea" name="content">글 내용</label>
						</div>
						<div class="text-center">
							<button class="list_btn" type="submit">수정</button>
						</div>
					</form>
				</article>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>
