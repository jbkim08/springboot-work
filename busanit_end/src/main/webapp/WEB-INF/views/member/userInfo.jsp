<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>    
  <main id="main">
    <!-- ======= Blog Page ======= -->
	<div class="blog-page area-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4">
					<div class="page-head-blog">
						<div class="single-blog-page">
							<!-- search option start --><!--  -->
							<form action="#">
								<div class="search-option">
									<input type="text" placeholder="Search..." name="word" value="${word }">
									<button class="button" type="submit" onclick="location.href='/member/userInfo?pageNum=${pageNum}&&word=${word }'">
										<i class="bi bi-search"></i>
									</button>
								</div>
							</form>
							<!-- search option end -->
						</div>
						<!-- left nav -->
<%-- 						<%@ include file="../includes/nav_left.jsp"%> --%>
					</div>
				</div>
				<!-- End left sidebar -->
          <div class="col-md-8 col-sm-8 col-xs-12">
            <div class="list_botton">
              <div class = "btnPost_p">                              
                <h3>회원목록</h3>
              </div>
            </div>
            <table class="table table-hover mt-3">
              <tr>
                <th>아이디</th>
                <th>닉네임</th>
                <th>회원유형</th>
                <th>가입날짜</th>
                <th>정보수정</th>
              </tr>
              <c:forEach items="${list }" var="uList">
              	<tr>
	                <td><a href="/member/detail?username=${uList.username }"> ${uList.username }</a></td>
	                <td> ${uList.nickname } <!-- <span style="font-size: medium;"><i class="bi bi-chat"></i> 12</span> --> </td>
	                <td>${uList.role }</td>
	                <td><fmt:formatDate value="${uList.joindate }" pattern="yyyy-MM-dd"/> </td>
	                <td><button class = "btnDeleteUser" type="button" id="btnUserDelete" onclick = "location.href='/member/adminDelete?username=${uList.username}'">삭제</button></td>
              </tr>
              </c:forEach>
            </table>
            <div class="source-pagination">
              <ul class="pagination">
               	<!-- 이전 -->
					<c:if test="${p.startPage > p.blockSize }">
						<li class="page-item"><a class="page-link" href="/member/userInfo?pageNum=${p.startPage -1 }&&word=${word}">&lt;</a></li>
					</c:if>
					<!--페이지 리스트-->
					<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i" >
					<li class="page-item"><a class="page-link" href="/member/userInfo?pageNum=${i }&&word=${word}" >${i }</a></li>
					</c:forEach>
					<!-- 다음 -->
					<c:if test="${p.endPage < p.totPage }">
						<li class="page-item"><a class="page-link" href="/member/userInfo?pageNum=${p.endPage+1 }&&word=${word}">&gt;</a></li>				
					</c:if>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Blog Page -->

  </main>
<script type="text/javascript">
// $("#btnUserDelete").click(function(){
// 	if(confirm("정말 삭제하시겠습니까?")){
// 		alert("${uList.username} 회원은 안전하게 삭제 되었습니다.");
// 		location.href="/member/adminDelete?username=${uList.username}"
// 	}
// });

</script>
  <!-- End #main -->
  <%@ include file="../includes/footer.jsp" %>