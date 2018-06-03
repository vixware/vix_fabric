<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="col-sm-3 profile-pic">
	<img src="${e.headImgUrl }" alt="demo user">
	<div class="padding-10">
		<h4 class="font-md">
			<small>${e.position}</small> <br> <strong>职位</strong>
		</h4>
	</div>
</div>
<div class="col-sm-5">
	<h1>
		<span>${e.name}</span>
	</h1>
	<ul class="list-unstyled">
		<li>
			<p class="text-muted">
				<i class="fa fa-phone"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${e.mobile}&nbsp;&nbsp;</span>
			</p>
		</li>
		<li>
			<p class="text-muted">
				<i class="fa fa-envelope"></i>&nbsp;&nbsp; <a href="mailto:simmons@smartadmin"> ${e.email}&nbsp;&nbsp; </a>
			</p>
		</li>
	</ul>
	<p class="font-md">
		<i>个人简介</i>
	</p>
	<p>${e.memo}</p>
</div>