<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#orderItemForm").validationEngine();
//选择分类
function chooseSupplierCategory(){
	$.ajax({
		  url:'${vix}/hr/polSysManageAction!goChooseSupplierCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择所属分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#newScId").val(result[0]);
									$("#newScName").val(result[1]);
							}else{
								asyncbox.success("请选择所属分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="polSysManage.id" value="%{polSysManage.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">上级分类:&nbsp;</th>
					<td colspan="3"><input id="newScName" name="polSysManage.polSysManage.name" value="${polSysManage.polSysManage.name}" type="text" readonly="readonly" style="background-color: #C0C0C0;" /> <input type="hidden" id="newScId" name="polSysManage.polSysManage.id" value="${polSysManage.polSysManage.id}" /> <input class="btn" type="button"
						value="选择" onclick="chooseSupplierCategory();" /></td>
				</tr>
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="newCode" name="polSysManage.code" value="${polSysManage.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="newName" name="polSysManage.name" value="${polSysManage.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">主题:&nbsp;</th>
					<td><input id="newCode" name="polSysManage.code" value="${polSysManage.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">内容概述:&nbsp;</th>
					<td><input id="newName" name="polSysManage.name" value="${polSysManage.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">分类:&nbsp;</th>
					<td><input id="newCode" name="polSysManage.code" value="${polSysManage.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">所属部门:&nbsp;</th>
					<td><input id="newName" name="polSysManage.name" value="${polSysManage.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
			<div class="clearfix" style="background: #FFF; position: relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
					</ul>
				</div>
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/hr/probationAction!getHrAttachmentsJson.action?id=${hrBecomeRegular.id}',
						title: '附件',
						width: 'auto',
						height: '450',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/hr/probationAction!addHrAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
											 	width : 364,
												height : 160,
												title:"添加明细",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/hr/probationAction!uploadHrAttachments.action?id=${hrBecomeRegular.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
								//循环所选的行
								for(var i =0;i<rows.length;i++){
									var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
									$.ajax({url:'${vix}/hr/probationAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
					<div style="padding: 8px;">
						<table id="soAttach"></table>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>