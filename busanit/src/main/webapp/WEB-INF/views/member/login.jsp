<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
    
    <!-- ======= Contact Section ======= -->
    <div id="contact" class="contact-area" >
      <div class="contact-inner area-padding">
        <div class="contact-overly"></div>
        <div class="container ">
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="section-headline text-center">
                <h2>Login</h2>
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
                <form action="/loginProc" method="post" role="form" class="join-form">
                  <div class="form-group" >
                    <input type="text" name="username" class="form-control" id="username" placeholder="아이디를 입력하세요" required>
                  </div>
                  <div class="form-group mt-3">
                    <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요" required>
                  </div>
                  <span>
                  	<c:if test="${error != null}">
                  		<p id="valid" class="alert alert-danger">${exception }</p>
                  	</c:if>
                  </span>
                  <div class="text-center"><button type="submit" id="btnLogin">로그인</button></div>
                </form>
              </div>
            </div>
            <!-- End Left contact -->
          </div>
        </div>
      </div>
    </div><!-- End Contact Section -->
    
  
<%@ include file="../includes/footer.jsp" %>