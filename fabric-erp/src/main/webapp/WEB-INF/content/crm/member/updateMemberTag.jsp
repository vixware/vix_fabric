<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	$("#memberTagDetailForm").validationEngine();
</script>
<input type="hidden" id="memberTagId" name="memberTagId" value="${memberTag.id}" />
<div class="content">
	<form id="memberTagForm">
		<div class="order">
			<dl>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>名称：</th>
											<td><input id="name" name="name" value="${memberTag.name }" type="text"></td>
											<th>备注：</th>
											<td><input id="memo" name="memo" value="${memberTag.memo }" type="text"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<script type="text/javascript">
						function saveDeliveryAddress(id) {
							$.ajax({
							url : '${vix}/crm/member/memberTagAction!goSaveOrUpdateMemberTagDetail.action?id=' + id,
							cache : false,
							success : function(html) {
								asyncbox.open({
								modal : true,
								width : 750,
								height : 255,
								title : "条件",
								html : html,
								callback : function(action) {
									if (action == 'ok') {
										if ($('#memberTagDetailForm').validationEngine('validate')) {
											$.post('${vix}/crm/member/memberTagAction!saveOrUpdateMemberTagDetail.action', {
											'memberTagDetail.memberTag.id' : $("#memberTagId").val(),
											'memberTagDetail.id' : $("#memberTagDetailId").val(),
											'memberTagDetail.standardCategory' : $("#standardCategory").val(),
											'memberTagDetail.operationalCharacter' : $("#operationalCharacter").val(),
											'memberTagDetail.categoryValue' : $("#categoryValue").val()
											}, function(result) {
												showMessage(result);
												setTimeout("", 1000);
												$('#memberTagDetail').datagrid("reload");
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
						$('#memberTagDetail').datagrid({
						url : '${vix}/crm/member/memberTagAction!getMemberTagDetailJson.action?id=${memberTag.id}',
						width : 700,
						height : 250,
						rownumbers : true,
						sortOrder : 'desc',
						fitColumns : true,
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
						width : 200,
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
							var row = $('#memberTagDetail').datagrid("getSelected"); //获取你选择的所有行
							if (row) {
								saveDeliveryAddress(row.id);
							}
						}
						}, '-', {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							var rows = $('#memberTagDetail').datagrid("getSelections"); //获取你选择的所有行	
							//循环所选的行
							for (var i = 0; i < rows.length; i++) {
								var index = $('#memberTagDetail').datagrid('getRowIndex', rows[i]);//获取某行的行号
								$('#memberTagDetail').datagrid('deleteRow', index); //通过行号移除该行
								$.ajax({
								url : '${vix}/crm/member/memberTagAction!deleteMemberTagDetail.action?id=' + rows[i].id,
								cache : false
								});
							}
						}
						} ]
						});
					</script>
					<div style="padding: 8px;">
						<table id="memberTagDetail"></table>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>