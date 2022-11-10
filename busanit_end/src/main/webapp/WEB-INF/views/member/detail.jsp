<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>


  <main id="main" >
    <!-- ======= Contact Section ======= -->
    <div id="contact" class="contact-area" >
      <div class="contact-inner area-padding">
        <div class="contact-overly"></div>
        <div class="container ">
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="section-headline text-center">
                <h2>MY ACCOUNT</h2>
              </div>
            </div>
          </div>
          <div class="row">
            <!-- Start contact icon column -->
            <!-- Start contact icon column -->
            <!-- Start contact icon column -->
            
          </div>
          <div class="row" >

            <!-- Start Google Map -->
           
            <!-- End Google Map -->

            <!-- Start  contact -->
            <div class="col-md-6" >
              <div class="form contact-form" >
                <form action="join/update" method="post" role="form" class="join-form">
                  <div class="form-group" >
                    <input type="text" name="username" class="form-control" id="username" value="${list.username }" readonly>
                  </div>
                  <div class="form-group mt-3">
                    <input type="password" class="form-control" name="password" id="password"  value="${list.password }" >
                  </div>
                  <div class="form-group mt-3">
                  </div>
                  <div class="form-group mt-3">
                    <input type="text" class="form-control" name="nickname" id="nickname" value="${list.nickname }" >
                  </div>
                  <div class="form-group mt-3">
                    <input type="text" class="form-control" name="joindate" id="joindate" value="${list.joindate }"  readonly>
                  </div>
                  <!-- <div class="form-group mt-3">
                    <textarea class="form-control" name="message" rows="5" placeholder="Message" required></textarea>
                  </div> -->
                  <!-- <div class="my-3">
                    <div class="loading">Loading</div>
                    <div class="error-message"></div>
                    <div class="sent-message">Your message has been sent. Thank you!</div>
                  </div> -->
                  <div class="text-center"><button type="button" id="btnUserUpdate">수정하기</button>
                  <c:choose>
            			<c:when test="${principal.user.role == 'ADMIN' }">
                  <button type="button" id="btnUserOut" onclick = "location.href='/member/adminDelete?username=${list.username}'">삭제하기</button>
                  		</c:when>
                  		<c:otherwise>
                  <button type="button" id="btnJoinOut">탈퇴하기</button>
                  		</c:otherwise>
                  </c:choose>
                  </div>
                </form>
              </div>
            </div>
            <!-- End Left contact -->
          </div>
        </div>
      </div>
    </div><!-- End Contact Section -->
    
  </main><!-- End #main -->
<script type="text/javascript" src="../js/user.js"></script>
<script type="text/javascript">

$("#btnJoinOut").click(function(){
	if(confirm("정말 탈퇴하시겠습니까?")){
		alert("회원님의 정보는 안전하게 삭제 되었습니다.");
		location.href="/member/delete?username=${list.username}"
	}
});
// $("#btnUserOut").click(function(){
// 	if(confirm("정말 삭제하시겠습니까?")){
// 		alert("${list.username} 회원은 안전하게 삭제 되었습니다.");
// 		location.href="/member/adminDelete?username=${list.username}"
// 	}
// });

</script>
<%@ include file="../includes/footer.jsp" %>