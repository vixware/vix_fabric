<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		if ($('#membershipSubdivisionForm').validationEngine('validate')) {
			$.post('${vix}/crm/business/membershipSubdivisionAction!saveOrUpdate.action', {
			'membershipSubdivision.id' : $("#membershipSubdivisionId").val(),
			'membershipSubdivision.channelDistributorId' : $("#channelDistributorId").val(),
			'membershipSubdivision.name' : $("#name").val(),
			'membershipSubdivision.description' : $("#description").val(),
			'membershipSubdivision.memberTagName' : $("#memberTagName").val(),
			'membershipSubdivision.startTime' : $("#startTime").val(),
			'memberTagId' : $("#memberTagId").val(),
			'updateField' : updateField,
			'membershipSubdivision.endTime' : $("#endTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#membershipSubdivisionForm").validationEngine();
	$(function() {
		$("#channelDistributorId").val('${membershipSubdivision.channelDistributorId}');
	});

	function chooseMemberTag() {
		$.ajax({
		url : '${vix}/crm/business/membershipSubdivisionAction!goChooseMemberTag.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 350,
			title : "选择标签",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						var selectIds = "";
						var selectNames = "";
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								selectIds += "," + v[0];
								selectNames += "," + v[1];
							}
						}
						if (selectIds != '') {
							$("#memberTagId").val(selectIds.substring(1));
							if ($("#memberTagName").val() != '') {
								$("#memberTagName").val($("#memberTagName").val() + selectNames);
							} else {
								$("#memberTagName").val(selectNames.substring(1));
							}
						}

					} else {
						asyncbox.success("请选择标签!", "<s:text name='vix_message'/>");
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">营销中心 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">会员细分管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="membershipSubdivisionId" name="membershipSubdivisionId" value="${membershipSubdivision.id}" />
<div class="content">
	<form id="membershipSubdivisionForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>客户细分 </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>主题：</th>
											<td><input id="name" name="name" value="${membershipSubdivision.name }" type="text" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>门店：</th>
											<td><s:select id="channelDistributorId" headerKey="" headerValue="总部" list="%{channelDistributorList}" listValue="name" listKey="id" value="" multiple="false" theme="simple" onchange="fieldChanged(this);">
												</s:select></td>
										</tr>
										<tr>
											<th>开始时间：</th>
											<td><input id="startTime" name="startTime" value="<s:date name="%{membershipSubdivision.startTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="endTime" value="<s:date name="%{membershipSubdivision.endTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>标签：</th>
											<td colspan="3"><input type="hidden" id="memberTagId" name="memberTagId" value="${memberTagId}" /> <textarea id="memberTagName" name="memberTagName" cols="3" rows="2" style="height: 75px; width: 850px; margin-top: 10px;" onchange="fieldChanged(this);">${membershipSubdivision.memberTagName } </textarea><input class="btn"
												type="button" value="选择标签" onclick="chooseMemberTag();" /></td>
										</tr>
										<tr>
											<th>描述：</th>
											<td colspan="3"><input id="description" name="description" value="${membershipSubdivision.description }" type="text" size="75" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;" id="selectOptionId1">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />客户细分条件</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/crm/business/membershipSubdivisionAction!goSaveOrUpdateMembershipSubdivision.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 750,
									height : 255,
									title : "客户细分条件",
									html : html,
									callback : function(action) {
										if (action == 'ok') {
											if ($('#membershipSubdivisionDetailForm').validationEngine('validate')) {
												$.post('${vix}/crm/business/membershipSubdivisionAction!saveOrUpdateMembershipSubdivisionDetail.action', {
												'membershipSubdivisionDetail.membershipSubdivision.id' : $("#membershipSubdivisionId").val(),
												'membershipSubdivisionDetail.id' : $("#membershipSubdivisionDetailId").val(),
												'membershipSubdivisionDetail.standardCategory' : $("#standardCategory").val(),
												'membershipSubdivisionDetail.operationalCharacter' : $("#operationalCharacter").val(),
												'membershipSubdivisionDetail.categoryValue' : $("#categoryValue").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#membershipSubdivisionDetail').datagrid("reload");
												});
											} else {
												return false;
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							};
							$('#membershipSubdivisionDetail').datagrid({
							url : '${vix}/crm/business/membershipSubdivisionAction!getMembershipSubdivisionDetailJson.action?id=${membershipSubdivision.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'name',
							title : '条件名称',
							width : 200,
							align : 'center'
							}, {
							field : 'operationalCharacterName',
							title : '运算符名称',
							width : 200,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'categoryValue',
							title : '值',
							width : 300,
							align : 'right',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress(0);
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#membershipSubdivisionDetail').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress(row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#membershipSubdivisionDetail').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#membershipSubdivisionDetail').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#membershipSubdivisionDetail').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/crm/business/membershipSubdivisionAction!deleteMembershipSubdivisionDetail.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="membershipSubdivisionDetail"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>