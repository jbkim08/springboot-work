<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>부산IT센터 - 메인</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="/img/favicon.png" rel="icon">
  <link href="/img/apple-touch-icon.png" rel="apple-touch-icon">
  
  
  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="/css/style.css" rel="stylesheet">
  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <!-- =======================================================
  * Template Name: eBusiness - v4.7.0
  * Template URL: https://bootstrapmade.com/ebusiness-bootstrap-corporate-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<!-- security에 따른 권한 설정 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<body>

<!-- login header start -->
  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex justify-content-between">

      <div class="logo">
        <h1><a href="/home"><span>부산</span>IT센터</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="/img/logo.png" alt="" class="img-fluid"></a>-->
      </div>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="/home/#">Home</a></li>
          <li><a class="nav-link scrollto" href="/home/#notice">공지사항</a></li>
          <li class="dropdown"><a href="#"><span>강좌</span></a>
            <ul>
              <!-- <li><a href="#">Drop Down 1</a></li> -->
              <li class="dropdown"><a href="/class/main_class?course=bigdata"><span>BIGDATA</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="/class/list?course=bigdata&&role=csource">수업자료실</a></li>
                  <li><a href="/class/list?course=bigdata&&role=fshare">파일나눔터</a></li>
                  <li><a href="/class/list?course=bigdata&&role=tout">과제제출실</a></li>
                </ul>
              </li>
              <li class="dropdown"><a href="/class/main_class?course=frontend"><span>FRONTEND</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="/class/list?course=frontend&&role=csource">수업자료실</a></li>
                  <li><a href="/class/list?course=frontend&&role=fshare">파일나눔터</a></li>
                  <li><a href="/class/list?course=frontend&&role=tout">과제제출실</a></li>
                </ul>
              </li>
              <li class="dropdown"><a href="/class/main_class?course=backend"><span>BACKEND</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="/class/list?course=backend&&role=csource">수업자료실</a></li>
                  <li><a href="/class/list?course=backend&&role=fshare">파일나눔터</a></li>
                  <li><a href="/class/list?course=backend&&role=tout">과제제출실</a></li>
                </ul>
              </li>
            </ul>
          </li>          
          <sec:authorize access="isAnonymous()">
          	<li class="dropdown"><a href="#"><span>Contact</span></a>
          	<ul>
          		<li class="dropdown"><a href="/member/login"><span>Login</span></a></li>
				<li class="dropdown"><a href="/member/join"><span>Join</span></a></li>
          	</ul>
          </sec:authorize>
          <sec:authorize access="isAuthenticated()">
          	<li class="dropdown"><a href="#"><span>${sessionScope.suser.nickname }</span></a>
          	<ul>
          	<li class="dropdown"><a href="/logout"><span>Logout</span></a></li>
          	<c:choose>
          		<c:when test="${principal.user.role == 'ADMIN' }">
        			<li class="dropdown"><a href="/member/detail?username=${principal.user.username }"><span>정보수정</span></a></li>
        			<li class="dropdown"><a href="/member/userInfo"><span>회원목록</span></a></li>
        		</c:when>
        		<c:otherwise>
       				<li class="dropdown"><a href="/member/detail?username=${principal.user.username }"><span>정보수정</span></a></li>		
       			</c:otherwise>
          	</c:choose>
          	</ul>
          </sec:authorize>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header>

  <!-- other header start -->
  <c:set var = "courseTitle" value = "${fn:split(pageContext.request.requestURI,'/') }" />
  <c:set var = "fileName" value = "${fn:split(courseTitle[fn:length(courseTitle)-1], '.')}" />
  <c:choose>
  <c:when test="${fileName[0]=='home'}">  
  <!-- main header start -->
    <!-- ======= hero Section ======= -->
  <section id="hero">
    <div class="hero-container">
      <div id="heroCarousel" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="5000">

        <ol id="hero-carousel-indicators" class="carousel-indicators"></ol>

        <div class="carousel-inner" role="listbox">

          <div class="carousel-item active" style="background-image: url(/img/hero-carousel/1.jpg)">
            <div class="carousel-container">
              <div class="container">
                <h2 class="animate__animated animate__fadeInDown">강좌1 </h2>
                <p class="animate__animated animate__fadeInUp">빅데이터</p>
                <a href="/class/main_class?course=bigdata" class="btn-get-started scrollto animate__animated animate__fadeInUp">신청하기</a>
              </div>
            </div>
          </div>

          <div class="carousel-item" style="background-image: url(/img/hero-carousel/2.jpg)">
            <div class="carousel-container">
              <div class="container">
                <h2 class="animate__animated animate__fadeInDown">강좌 2</h2>
                <p class="animate__animated animate__fadeInUp">프론트엔드</p>
                <a href="/class/main_class?course=frontend" class="btn-get-started scrollto animate__animated animate__fadeInUp">신청하기</a>
              </div>
            </div>
          </div>

          <div class="carousel-item" style="background-image: url(/img/hero-carousel/3.jpg)">
            <div class="carousel-container">
              <div class="container">
                <h2 class="animate__animated animate__fadeInDown">강좌3</h2>
                <p class="animate__animated animate__fadeInUp">백엔드</p>
                <a href="/class/main_class?course=backend" class="btn-get-started scrollto animate__animated animate__fadeInUp">신청하기</a>
              </div>
            </div>
          </div>

        </div>

        <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
          <span class="carousel-control-prev-icon bi bi-chevron-left" aria-hidden="true"></span>
        </a>

        <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
          <span class="carousel-control-next-icon bi bi-chevron-right" aria-hidden="true"></span>
        </a>

      </div>
    </div>
  </section><!-- End Hero Section -->

<!-- main header end -->
  </c:when>
  
  <c:otherwise>
  <!-- other header start -->
  <div class="header-bg page-area">
      <div class="container position-relative">
        <div class="row">
          <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="slider-content text-center">
              <div class="header-bottom">
                <div class="layer2">
                  <h1 class="title2">${fn:toUpperCase(course) }</h1>
                </div>
                <div class="layer3">
<%--                   <h2 class="title3">${fn:toUpperCase(role==null?"":role) }</h2> --%>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Blog Header -->
    <!-- other header end -->
    </c:otherwise>
</c:choose>
    <!-- End Blog Header -->
    <!-- other header end -->

