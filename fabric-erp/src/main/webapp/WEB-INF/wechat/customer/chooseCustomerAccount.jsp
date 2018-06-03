<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
		<title>客户列表</title>
	</head>
	<body>
		<section>
			<div class="search">
				<p>
					<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索">
				</p>
			</div>
			<div class="subJob">
				<s:if test="customerAccountList.size > 0">
					<s:iterator value="customerAccountList" var="entity" status="s">
						<dl>
							<dt>
								<span style="height: 28px;"></span>
								<strong>
									<a href="#">${entity.name}</a>
								</strong>
							</dt>
						</dl>
					</s:iterator>
				</s:if>
				<h3>
					<a href="#" class="btn"><img src="${vix}/wechatcommon/images/subJob_detIcon.png" />确定</a>
				</h3>
			</div>
		</section>
	</body>
</html>