<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<main id="main">
	<!-- ======= Blog Page ======= -->
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
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="section-headline text-center">
                  <h2>${course }</h2>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="single-blog">
                  <div class="single-blog-img">
                    <a href="blog-details.html">
                      <img src="/img/blog/1.jpg" alt="">
                    </a>
                  </div>
                  <div class="blog-meta">
                    <span class="comments-type">
                      <!-- <i class="bi bi-chat"></i>
                      <a href="#">11 comments</a> -->
                    </span>
                    <span class="date-type">
                      <!-- <i class="bi bi-calendar"></i>2016-03-05 / 09:10:16 -->
                    </span>
                  </div>
                  <div class="blog-text">
                    <h4>
                      <a href="#">BIGDATA는 이러이러한 저러저러한 것입니다</a>
                    </h4>
                    <p>
                      우리가 만나는 빅데이터는 Lorem ipsum dolor sit amet consectetur, adipisicing elit. Esse, magni officiis. Quaerat a voluptatibus cupiditate quam neque necessitatibus, mollitia nisi dignissimos similique fuga laborum enim quod consequatur praesentium, fugit nam?이란 것에서 가져오는 것이며 Lorem ipsum dolor sit amet consectetur adipisicing elit. Vero laudantium voluptas repellat quis corrupti vel? Ullam vel quo molestiae illo delectus, aspernatur doloremque! Fugit enim accusantium delectus molestiae ipsam unde.은 창업자로서 알려져 있습니다.
                    </p>
                  </div>
                  <span class = "classmain_atag">
                    <a href="/class/detail?course=${course }&&role=csource" class="ready-btn">수업자료실</a>
                    <a href="/class/detail?course=${course }&&role=fshare" class="ready-btn">파일나눔터</a>
                    <a href="/class/detail?course=${course }&&role=tout" class="ready-btn">과제제출실</a>
                  </span>
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
<%@ include file="../includes/footer.jsp"%>