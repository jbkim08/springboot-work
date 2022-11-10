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
                <h2>Join us</h2>
              </div>
            </div>
          </div>
          <div class="row">
          </div>
          <div class="row" >
            <div class="col-md-6" >
              <div class="form contact-form" >
                <form action="joinProc" method="post" role="form" class="join-form">
                  <div style = "display: flex;"class="form-group">
                    <input type="text" name="username" class="form-control" id="username" placeholder="아이디를 입력하세요" required>                    
                    <button type="button" name="idDupCheck" id="btnIdCheck">중복 확인</button>                     
                  </div>                  
                  <div class="form-group mt-3">
                    <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요" required>
                  </div>
                  <div class="form-group mt-3">
                    <input type="password" class="form-control" name="pass_check" id="pass_check" placeholder="똑같은 비밀번호를 한번더 입력해주세요!" required>
                  </div>
                  <div class="form-group mt-3">
                    <input type="text" class="form-control" name="nickname" id="nickname" placeholder="별명을 입력하세요" required>
                  </div>
                  <div class="form-group mt-3">
                    <label style="width: 100%;">
                    	<select class = "form-control" id="role" name="role">
                        <option value = "" selected>▽ 회원 유형을 골라주세요 ▽</option>
                    		<option value="STUDENT" >STUDENT</option>
                    		<option value="MANAGER">MANAGER</option>
                    		<option value="ADMIN">ADMIN</option>
                    	</select>
                    </label>
                  </div>
                  <div class="text-center"><button type="button" id="btnJoin">가입하기</button></div>
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
<%@ include file="../includes/footer.jsp" %>