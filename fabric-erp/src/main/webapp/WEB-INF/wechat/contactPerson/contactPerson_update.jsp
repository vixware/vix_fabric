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
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
	</head>
	<body>
		<form id="contactPersonForm">
			<input type="hidden" id="contactPersonId" name="contactPerson.id" value="${contactPerson.id }" />
			<input type="hidden" id="sync" value="${sync }" />
			<section class="newMeet newCla new">
				<div class="newMeet_list2">
					<dl class="conorder_dlO">
						<s:if test="contactPerson.customerAccount.id != null">
							<dt>
								<span>隶属客户:</span> 
								<strong>
									<input id="customerAccountId" name="contactPerson.customerAccount.id" value="${contactPerson.customerAccount.id}" type="hidden"/> 
									<input id="customerAccountName" value="${contactPerson.customerAccount.name}" type="text" readonly="readonly"/> 
								</strong>
							</dt>
						</s:if>
						<s:else>
							<dt>
								<span>隶属客户:</span> 
								<strong>
									<s:select id="customerAccountId" headerKey="" headerValue="--请选择--" list="%{customerAccountList}" listValue="name" listKey="id" name="contactPerson.customerAccount.id" value="%{contactPerson.customerAccount.id}" multiple="false" theme="simple"></s:select>
								</strong>
							</dt>
						</s:else>
						<dt>
							<span>联系人姓名:</span>
							<strong>
								<input id="name" name="contactPerson.lastName" value="${contactPerson.lastName}" type="text" placeholder="请输联系人姓名"/>
							</strong>
						</dt>
				        <dt>
				        	<span>性别:</span>
				        	<strong> 
				        		<select id="sex" headerKey="" headerValue="--请选择--" listValue="name" listKey="id" name="contactPerson.sex" value="%{contactPerson.sex}" multiple="false" theme="simple">
								  <option value="1" <c:if test='${contactPerson.sex eq "1"}'>selected="selected"</c:if>>男</option>
								  <option value="0" <c:if test='${contactPerson.sex eq "0"}'>selected="selected"</c:if>>女</option>
								</select>
							</strong>
				        </dt>
				        <dt>
				        	<span>联系人类型:</span>
				        	<strong> 
				        		<strong>
									<s:select id="crmContactTypeId" headerKey="-1" headerValue="--请选择--" list="%{crmContactTypeList}" listValue="name" listKey="id" name="contactPerson.crmContactType.id" value="%{contactPerson.crmContactType.id}" multiple="false" theme="simple"></s:select>
								</strong>
							</strong>
				        </dt>
						<dt>
							<span>负责业务:</span>
							<strong>
								<input id="presideBusiness" name="contactPerson.presideBusiness" value="${contactPerson.presideBusiness}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>称谓:</span>
							<strong>
								<input id="callTitle" name="contactPerson.callTitle" value="${contactPerson.callTitle}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>公司:</span>
							<strong>
								<input id="company" name="contactPerson.company" value="${contactPerson.company}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>部门:</span>
							<strong>
								<input id="department" name="contactPerson.department" value="${contactPerson.department}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>职务:</span>
							<strong>
								<input id="position" name="contactPerson.position" value="${contactPerson.position}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>手机:</span>
							<strong>
								<input id="mobile" name="contactPerson.mobile" value="${contactPerson.mobile}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>工作电话:</span>
							<strong>
								<input id="directPhone" name="contactPerson.directPhone" value="${contactPerson.directPhone}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>邮件:</span>
							<strong>
								<input id="email" name="contactPerson.email" value="${contactPerson.email}" type="text"/>
							</strong>
						</dt>
						<dt>
							<span>微信:</span>
							<strong>
								<input id="msnAccount" name="contactPerson.msnAccount" value="${contactPerson.msnAccount}" type="text"/>
							</strong>
						</dt>
					</dl>
				</div>
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
	function chooseCustomer(){
	}
	
	function saveOrUpdate() {
		var sync = $("#sync").val();
		var customerAccountId = $("#customerAccountId").val();
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		if (!customerAccountId || $.trim(customerAccountId).length <= 0) {
			alert("请选择所属客户!");
			return;
		}
		if (!name || $.trim(name).length <= 0) {
			alert("请输入联系人名称!");
			return;
		}
		if (!mobile || $.trim(mobile).length <= 0) {
			alert("请输入联系人电话!");
			return;
		}
		
		if(!sync || $.trim(sync).length <= 0){
			$("#contactPersonForm").ajaxSubmit({
				type : "post",
				url : "${vix}/wechat/webChatCustomerAction!saveOrUpdateContactPerson.action",
				dataType : "text",
				success : function() {
					window.location.href = "${vix}/wechat/webChatCustomerAction!goContactPersonList.action";
				}
			});
		}else {
			saveOrUpdateContactPerson();
		}
		
	};
	
	function saveOrUpdateContactPerson(){
		$("#contactPersonForm").ajaxSubmit({
			type : "post",
			url : "${vix}/wechat/webChatCustomerAction!saveOrUpdateContactPerson.action",
			dataType : "text",
			success : function() {
				window.location.href = "${vix}/wechat/webChatCustomerAction!goCustomerAccountList.action";
			}
		});
	}
</script>