<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#knowledgeBaseForm").validationEngine();
	function chooseKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/drp/knowledgeBasemanagementAction!goKnowledgeBaseCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择分类",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#knowledgeBaseCategoryId").val(result[0]);
						$("#knowledgeBaseCategoryName").val(result[1]);
					} else {
						$("#knowledgeBaseCategoryId").val("");
						$("#knowledgeBaseCategoryName").val("");
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
	<form id="knowledgeBaseForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="knowledgeBaseId" name="knowledgeBase.id" value="%{knowledgeBase.id}" theme="simple" />
		<s:hidden id="updateField" name="updateField" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="knowledgeBaseCategoryId" name="knowledgeBase.knowledgeBaseCategory.id" value="%{knowledgeBase.knowledgeBaseCategory.id}" theme="simple" onchange="fieldChanged(this);" /><input type="text" id="knowledgeBaseCategoryName" name="knowledgeBase.knowledgeBaseCategory.name" value="${knowledgeBase.knowledgeBaseCategory.name}"
						readonly="readonly" /><input class="btn" type="button" value="选择" onclick="chooseKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">编号:&nbsp;</td>
					<td><input type="text" id="code" name="knowledgeBase.code" class="validate[required] text-input" value="${knowledgeBase.code}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="name" name="knowledgeBase.name" class="validate[required] text-input" value="${knowledgeBase.name}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">关键字:&nbsp;</td>
					<td><input type="text" id="keyword" name="knowledgeBase.keyword" value="${knowledgeBase.keyword}" onchange="fieldChanged(this);" /></td>
					<td align="right">类型：</td>
					<td><select id="type" name="knowledgeBase.type" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
							<option value="">请选择</option>
							<option value="1" selected="selected">技术</option>
							<option value="2">文学</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">发布时间：</td>
					<td><input id="createTime" name="knowledgeBase.createTime" value="${knowledgeBase.createTime}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="knowledgeBase.memo" size="45" value="${knowledgeBase.memo}" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<th>上传文件：</th>
					<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>