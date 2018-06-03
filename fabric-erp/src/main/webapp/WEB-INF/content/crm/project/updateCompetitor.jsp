<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/competitors.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">基础资料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/competitorAction!goList.action');">竞争对手</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 5px;">
	<form id="competitorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="goBeforeOrder(${order.id });"><img width="22" height="22" title="上一条" src="${vix}/common/img/m_previous.gif"> </a> <a onclick="saveOrUpdateCompetitor();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22"
							height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <s:if test="isAllowAudit == 1">
							<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if> <a href="#" onclick="goAfterOrder(${order.id });"> <img width="22" height="22" title="下一条" src="${vix}/common/img/m_next.gif">
					</a> <a href="#" onclick="loadContent('${vix}/crm/project/competitorAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="competitor.id > 0">
								${competitor.companyName}
							</s:if> <s:else>
								新增竞争对手
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<s:hidden id="id" name="competitor.id" value="%{competitor.id}" theme="simple" />
									<s:hidden id="customerAccountId" name="competitor.customerAccount.id" value="%{competitor.customerAccount.id}" theme="simple" />
									<table class="addtable_c">
										<tr height="30">
											<td align="right" width="15%">公司名称:</td>
											<td width="35%"><input type="text" id="companyName" name="competitor.companyName" value="${competitor.companyName}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr height="30">
											<td align="right" width="10%">竞争产品:</td>
											<td width="40%"><input type="text" id="competeProduct" name="competitor.competeProduct" value="${competitor.competeProduct}" size="30" /></td>
											<td align="right" width="15%">价格:</td>
											<td width="35%"><input type="text" id="price" name="competitor.price" value="${competitor.price}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<td align="right" width="10%">联系人:</td>
										<td width="40%"><input type="text" id="contactPerson" name="competitor.contactPerson" value="${competitor.contactPerson}" size="30" /></td>
										<td align="right" width="15%">联系人电话:</td>
										<td width="35%"><input type="text" id="cpPhone" name="competitor.cpPhone" value="${competitor.cpPhone}" size="30" /></td>
										</tr>
										<td align="right" width="10%">公司地址:</td>
										<td width="40%"><input type="text" id="address" name="competitor.address" value="${competitor.address}" size="30" /></td>
										<td align="right" width="15%">公司网站:</td>
										<td width="35%"><input type="text" id="website" name="competitor.website" value="${competitor.website}" size="30" /></td>
										</tr>
									</table>
								</dd>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr height="30">
										</tr>
										<tr height="30">
											<td align="right" colspan="2">产品描述:</td>
											<td align="left" colspan="1"><textarea id="description" name="description">${competitor.description}</textarea></td>
										</tr>
										<tr height="30">
											<td></td>
										</tr>
										<tr height="30">
											<td align="right">优势:</td>
											<td align="left"><textarea id="superiority" name="superiority">${competitor.superiority}</textarea></td>
											<td align="right">劣势:</td>
											<td align="left"><textarea id="inferior" name="inferior">${competitor.inferior}</textarea></td>
										</tr>
										<tr height="30">
											<td></td>
										</tr>
										<tr height="30">
											<td align="right">应用策略:</td>
											<td align="left"><textarea id="copeStrategy" name="copeStrategy">${competitor.copeStrategy}</textarea></td>
											<td align="right">备注:</td>
											<td align="left"><textarea id="memo" rows="12" cols="58" style="font-size: 12px;">${competitor.memo}</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js" />
<script type="text/javascript">

var editorSuperiority = KindEditor.create('textarea[name="superiority"]', {
	basePath : '${vix}/plugin/KindEditor/',
	width : 375,
	height : 200,
	cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
	fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	items : ['fontname', 'fontsize', '|', 'forecolor', 
	         'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 
			'justifyright', 'insertorderedlist','insertunorderedlist'],
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {
			self.sync();
			document.forms['competitorForm'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {
			self.sync();
			document.forms['competitorForm'].submit();
		});
	}
});

var editorInferior = KindEditor.create('textarea[name="inferior"]', {
	basePath : '${vix}/plugin/KindEditor/',
	width : 375,
	height : 200,
	cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
	fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	items : ['fontname', 'fontsize', '|', 'forecolor', 
	         'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 
			'justifyright', 'insertorderedlist','insertunorderedlist'],
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
	}
});

var editorCopeStrategy = KindEditor.create('textarea[name="copeStrategy"]', {
	basePath : '${vix}/plugin/KindEditor/',
	width : 375,
	height : 200,
	cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
	fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	items : ['fontname', 'fontsize', '|', 'forecolor', 
	         'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 
			'justifyright', 'insertorderedlist','insertunorderedlist'],
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
	}
});

var editorDescription = KindEditor.create('textarea[name="description"]', {
	basePath : '${vix}/plugin/KindEditor/',
	width : 375,
	height : 200,
	cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
	fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	items : ['fontname', 'fontsize', '|', 'forecolor', 
	         'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 
			'justifyright', 'insertorderedlist','insertunorderedlist'],
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {self.sync();
			document.forms['competitorForm'].submit();
		});
	}
});


//上一页，下一页
function goNextPager(id) {
	$.ajax({
	url : '${vix}/business/orderProcessAction!goBeforeOrder.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#ordercontent").html(html);
	}
	});
};
function goAfterOrder(id) {
	$.ajax({
	url : '${vix}/business/orderProcessAction!goAfterOrder.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#ordercontent").html(html);
	}
	});
};

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
	
$("#competitorForm").validationEngine();
function chooseCustomer(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerAccountName").val(result[1]);
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								$("#customerCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}else{
							$("#customerAccountId").val("");
							$("#customerAccountName").val("");
							asyncbox.success("请选择客户!","<s:text name='vix_message'/>");
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
	
function saveOrUpdateCompetitor(){

	if($('#competitorForm').validationEngine('validate')){
		$.post('${vix}/crm/project/competitorAction!saveOrUpdate.action',
			{'competitor.id':$("#id").val(),
			  'competitor.customerAccount.id':$("#customerAccountId").val(),
			  'competitor.price':$("#price").val(),
			  'competitor.companyName':$("#companyName").val(),
			  'competitor.competeProduct':$("#competeProduct").val(),
			  'competitor.superiority':editorSuperiority.html(),
			  'competitor.inferior':editorInferior.html(),
			  'competitor.copeStrategy':editorCopeStrategy.html(),
			  'competitor.memo':$("#memo").val(),
			  'competitor.cpPhone':$("#cpPhone").val(),
			  'competitor.contactPerson':$("#contactPerson").val(),
			  'competitor.website':$("#website").val(),
			  'competitor.address':$("#address").val(),
			  'competitor.description':editorDescription.html()
			},
			function(result){
				asyncbox.success(result,"<s:text name='vix_message'/>",function(){
					loadContent('${vix}/crm/project/competitorAction!goList.action');
				});
			}
		);
	}
}
</script>