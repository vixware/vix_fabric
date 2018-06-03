<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="col-sm-3 profile-pic">
	<img src="${employee.headImgUrl}">
	<div class="padding-10">
		<h4 class="font-md">
			<small>${employee.organizationUnitName}</small> <br> <strong>部门</strong>
		</h4>
	</div>
</div>
<div class="col-sm-5">
	<h1>
		<span>${employee.name}</span> <span> <s:if test="%{employee.wabtype == 0}">男</s:if> <s:elseif test="%{employee.wabtype == 1}">女</s:elseif>
		</span>
	</h1>
	<ul class="list-unstyled">
		<li>
			<p class="text-muted">
				<i class="fa fa-phone"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.mobile}&nbsp;&nbsp;</span>
			</p>
		</li>
		<li>
			<p class="text-muted">
				<i class="fa fa-envelope"></i>&nbsp;&nbsp; <a href=""> ${employee.email}&nbsp;&nbsp;</a>
			</p>
		</li>
		<li>
			<p class="text-muted">
				<i class="fa fa-qq"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.qq}&nbsp;&nbsp;</span>
			</p>
		</li>
		<li>
			<p class="text-muted">
				<i class="fa fa-weixin"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.weixinid}&nbsp;&nbsp; </span>
			</p>
		</li>
		<li>
			<p class="text-muted">
				<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="txt-color-darken"> <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment"> <fmt:formatDate value="${employee.startTime}" type="both" pattern="yyyy-MM-dd" />
				</a>
				</span>
			</p>
		</li>
	</ul>
	<p class="font-md">
		<i>个人简介</i>
	</p>
	<p>${employee.memo}</p>
</div>