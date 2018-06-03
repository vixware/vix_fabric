<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>
</head>
<body>
	<form id="customerAccountForm">
		<input type="hidden" id="customerAccountId" name="customerAccount.id" value="${customerAccount.id }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>客户分类</span> 
						<strong> 
							<s:select id="customerAccountCategoryId" headerKey="-1" headerValue="--请选择--" list="%{customerCategoryList}" listValue="name" listKey="id" name="customerAccount.customerAccountCategory.id" value="%{customerAccount.customerAccountCategory.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>类型</span> 
						<strong> 
							<select id="customerAccountType" name="customerAccount.type" value="${customerAccount.type }">
								<option value="">请选择类型</option>
								<option value="enterPrise" <c:if test='${customerAccount.type eq "enterPrise"}'>selected="selected"</c:if>>企业客户</option>
								<option value="personal" <c:if test='${customerAccount.type eq "personal"}'>selected="selected"</c:if>>个人客户</option>
							</select>
						</strong>
					</dt>
					<dt>
						<span>客户编码</span>
						<strong>
							<input type="text" id="code" name="customerAccount.code" value="${customerAccount.code}" placeholder="请输客户姓名">
						</strong>
					</dt>
					<dt>
						<span>客户名称</span>
						<strong>
							<input type="text" id="name" name="customerAccount.name" value="${customerAccount.name}" placeholder="请输客户姓名">
						</strong>
					</dt>
					<dt>
						<span>英文名称</span>
						<strong>
							<input id="englishName" name="customerAccount.englishName" value="${customerAccount.englishName}" type="text" placeholder="请输入客户英文名称"/>
						</strong>
					</dt>
					<s:if test="contactPersonList.size > 0">
						<dt>
							<span>联系人</span>
							<strong>
								<s:select id="contactPersonId" headerKey="-1" headerValue="--请选择--" list="%{contactPersonList}" listValue="name" listKey="id" name="customerAccount.contactPerson.id" value="%{customerAccount.contactPerson.id}" multiple="false" theme="simple"></s:select>
							</strong>
						</dt>
					</s:if>
					<dt>
						<span>所属行业</span> 
						<strong> 
							<s:select id="industryId" headerKey="-1" headerValue="--请选择--" list="%{industryList}" listValue="name" listKey="id" name="customerAccount.industry.id" value="%{customerAccount.industry.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>客户来源</span> 
						<strong> 
							<s:select id="customerSourceId" headerKey="-1" headerValue="--请选择--" list="%{customerSourceList}" listValue="name" listKey="id" name="customerAccount.customerSource.id" value="%{customerAccount.customerSource.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
					<span>公司简介</span> 
					<strong>
						<input id="companyBrief" name="customerAccount.companyBrief" value="${customerAccount.companyBrief}" type="text" placeholder="公司简介"/>
					</strong>
					</dt>
				</dl>
			</div>
			<div style="height: 70px; clear: both;"></div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="editTripRecord();">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate();">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script type="text/javascript">
	
	function editTripRecord(){
		alert("功能未开放！");
	}
	function saveOrUpdate() {
		var name = $("#name").val();
		var code = $("#code").val();
		// 验证
		if (!name || $.trim(name).length <= 0) {
			alert("请输入客户名称");
			return;
		}
		if (!code || $.trim(code).length <= 0) {
			alert("请输入请客户编码");
			return;
		}

		$("#customerAccountForm").ajaxSubmit({
			type : "post",
			url : "${vix}/wechat/webChatCustomerAction!saveOrUpdateCustomerAccont.action",
			dataType : "text",
			success : function() {
				window.location.href = "${vix}/wechat/webChatCustomerAction!goCustomerAccountList.action";
			}
		});
	};
</script>