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
	<form id="salesOrderForm">
		<input type="hidden" id="id" name="salesOrder.id" value="${salesOrder.id}"/>
		<input type="hidden" id="isDeleted" name="salesOrder.isDeleted" value="${salesOrder.isDeleted}"/>
		<input type="hidden" id="groupCode" name="salesOrder.groupCode" value="${salesOrder.groupCode}"/>
		<section class="newMeet newCla new">
			<div class="newMeet_list2">
			<br/>
				<dl>
					<dt>
						<span>编码</span>
						<strong>
							<input type="text" id="code" name="salesOrder.code" value="${salesOrder.code}">
						</strong>
					</dt>
					<dt>
						<span>主体</span>
						<strong>
							<input type="text" id="title" name="salesOrder.title" value="${salesOrder.title}">
						</strong>
					</dt>
					<dt>
						<span>单据日期</span>
						<strong>
							<input type="text" id="billDate" name="salesOrder.billDate" value="${salesOrder.billDateStr}" placeholder="请输客户姓名" readonly="readonly">
						</strong>
					</dt>
					<s:if test="tag == 'fromQuote'">
						<dt>
							<span>单据来源</span>
							<strong>
								<input type="text" id="" name="" value="" placeholder="销售报价单" readonly="readonly">
							</strong>
						</dt>
					</s:if>
					<s:else>
						<dt>
							<span>单据来源</span>
							<strong>
								<input type="text" id="" name="" value="" placeholder="新增销售单" readonly="readonly">
							</strong>
						</dt>
					</s:else>
					<dt>
						<span>客户名称</span>
						<strong>
							<s:select id="customerAccountId" headerKey="" headerValue="--请选择--" list="%{customerList}" listValue="name" listKey="id" name="salesOrder.customerAccount.id" value="%{salesOrder.customerAccount.id}" multiple="false" theme="simple" onclick="loadContentPerson();"></s:select>
						</strong>
					</dt>
					<dt>
						<span>联系人</span>
						<strong id="contactPersonSelect">
							<s:select id="contactPersonId" headerKey="-1" headerValue="--请选择--" list="%{contactPersonList}" listValue="name" listKey="id" name="salesOrder.contactPerson.id" value="%{salesOrder.contactPerson.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>业务类型</span> 
						<strong> 
							<s:select id="bizType" headerKey="" headerValue="--请选择--" list="%{billsTypeList}" listValue="name" listKey="id" name="salesOrder.bizType.id" value="%{salesOrder.bizType.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>币种</span> 
						<strong> 
							<s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" name="salesOrder.currencyType.id" value="%{salesOrder.currencyType.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>地域</span> 
						<strong> 
							<s:select id="regionalId" headerKey="" headerValue="--请选择--" list="%{regionalList}" listValue="name" listKey="id" name="salesOrder.regional.id" value="%{salesOrder.regional.id}" multiple="false" theme="simple"></s:select>
						</strong>
					</dt>
					<dt>
						<span>业务员</span> 
						<strong>
							<input id="salePersonId" name="salesOrder.salePerson.id" value="${salesOrder.salePerson.id}" type="hidden"/>
							<input id="salePersonName" name="" value="${salesOrder.salePerson.name}" type="text" readonly="readonly"/>
						</strong>
					</dt>
					<dt>
						<span>备注</span>
						<strong>
							<input type="text" id="memo" name="salesOrder.memo" value="${salesOrder.memo}">
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
	
	function loadContentPerson(){
		var customerAccountId = $("#customerAccountId").val();
		if(null != customerAccountId && customerAccountId != ""){
			$.ajax({
				url:"${vix}/wechat/webChatSalesAction!loadContentPerson.action?customerAccountId=" + customerAccountId,
				  cache: false,
				  success: function(html){
					  $("#contactPersonSelect").html(html);
				  }
			});
		}
	}
	
	function saveOrUpdate() {
		var code = $("#code").val();
		var title = $("#title").val();
		var customerAccountId = $("#customerAccountId").val();
		// 验证
		if (!title || $.trim(title).length <= 0) {
			alert("主题不能为空，请输入销售主题！");
			return;
		}
		if (!code || $.trim(code).length <= 0) {
			alert("请输入销售单编码");
			return;
		}
		if (!customerAccountId || $.trim(customerAccountId).length <= 0) {
			alert("请选择客户！");
			return;
		}
		
		$("#salesOrderForm").ajaxSubmit({
			type : "post",
			url : "${vix}/wechat/webChatSalesAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			dataType : "text",
			success : function() {
				window.location.href = "${vix}/wechat/webChatSalesAction!goSalesList.action";
			}
		});
	};
</script>