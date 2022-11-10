<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<div class="single-blog-page">
	<div class="left-blog">
		<h4>
			<a href="/class/main_class?course=${course }">${fn:toUpperCase(course) }</a>
		</h4>
		<ul>
			<li>
			<a href="/class/list?course=${course }&&role=csource">수업자료실</a>
			</li>
			<li>
			<a href="/class/list?course=${course }&&role=fshare">파일나눔터</a>
			</li>
			<li>
			<a href="/class/list?course=${course }&&role=tout">과제제출실</a>
			</li>
		</ul>
	</div>
</div>
