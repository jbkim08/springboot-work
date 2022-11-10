<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%@ include file="../includes/header.jsp" %>

<div class="hero-container">

	<main id="main">

    <!-- ======= About Section ======= -->
    <div id="about" class="about-area area-padding">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="section-headline text-center">
              <h2>${course }</h2>
            </div>
          </div>
        </div>
        <div class="row">
          <!-- single-well start-->
          <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="well-left">
              <div class="single-well">
                <a href="#">
                  <img src="/img/about/1.jpg" alt="">
                </a>
              </div>
            </div>
          </div>
          <!-- single-well end-->
          <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="well-middle">
              <div class="single-well">
                <a href="#">
                  <h4 class="sec-head">공지사항</h4>
                </a>
                <table style="border:1px solid black;">
                	<tr>
                		<th>title</th>
                		<th>writer</th>
                		<th>content</th>
                	</tr>
	                <c:forEach items="${list}" var="board" >
	                	<tr>
	                		<td>${board.title }</td>
	                		<td>${board.writer }</td>
	                		<td>${board.content }</td>
	                	</tr>
	                </c:forEach>
                </table>
              </div>
            </div>
          </div>
          <!-- End col-->
        </div>
      </div>
    </div><!-- End About Section -->

</div>

<%@ include file="../includes/footer.jsp" %>