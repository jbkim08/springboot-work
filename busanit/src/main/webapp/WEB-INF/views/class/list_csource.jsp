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
                <h3><c:if test="${role=='csource' }">수업자료실</c:if></h3>
                <c:if test="${not empty principal.user && principal.user.role!='STUDENT' }">
                <button class = "btnPost" type="button" id="list_csource>btn" onclick="location.href = '/class/register?course=${course }&&role=${role }'">글쓰기</button>
                </c:if>
              </div>
            </div>
            	<table class = "table table-hover mt-3">
                <c:forEach begin="${count+1-(p.currentPage-1)*(p.pageSize) }" end="${count+1-(p.currentPage-1)*(p.pageSize) }" var="i">
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>등록일</th>
                    <th>조회수</th>                    
                  </tr>
                <c:forEach items="${list}" var="board" >
                	<tr>
                		<td>${i=i-1 }</td>
                		<td class = 'list_title'><a href="/class/detail?course=${course }&&role=${role }&&bno=${board.bno}" >${board.title }</a> <span><a style = "font-size: medium;" href="#"> <i class="bi bi-chat" ></i> ${board.replycount }</a></span></td>
                		<td>${board.writer }</td>
                		<td><fmt:formatDate pattern="yy.MM.dd" value="${board.regdate }" /></td>
                		<td>${board.hitcount }</td>
                	</tr>
                </c:forEach>
                </c:forEach>
                </table>
              <div class="csource-pagination-div">
                <ul class="csource-pagination-ul">
					<c:if test="${word != null}">
					<!-- 이전 -->
                	<c:if test="${p.startPage > p.blockSize }">
						<li class="page-item"><a class="page-link" href="/class/list2?pageNum=${p.startPage -1 }&&course=${course}&&role=${role}&&word=${word}">&lt;</a></li>			
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
						<li class="page-item"><a class="page-link" href="/class/list?pageNum=${p.startPage -1 }&&course=${course}&&role=${role}&&word=${word}">&lt;</a></li>
					</c:if>
					<!--페이지 리스트-->
					<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i" >
						<c:if test="${i==p.currentPage}"><li class="page-item active"><a class="page-link" href="/class/list?pageNum=${i }&&course=${course}&&role=${role}&&word=${word}" >${i }</a></li></c:if>
						<c:if test="${i!=p.currentPage}"><li class="page-item"><a class="page-link" href="/class/list?pageNum=${i }&&course=${course}&&role=${role}&&word=${word}" >${i }</a></li></c:if>
					</c:forEach>
					<!-- 다음 -->
					<c:if test="${p.endPage < p.totPage }">
						<li class="page-item"><a class="page-link" href="/class/list?pageNum=${p.endPage+1 }&&course=${course}&&role=${role}&&word=${word}">&gt;</a></li>				
					</c:if>
                </c:if>
				</ul>
              </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Blog Page -->

  </main>
  <!-- End #main -->
  <%@ include file="../includes/footer.jsp" %>