<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#publicCabinetForm").validationEngine();
	function chooseKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/oa/publicCabinetAction!goPrivateCabinetCategory.action',
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
						$("#publicCabinetCategoryId").val(result[0]);
						$("#publicCabinetCategoryName").val(result[1]);
					} else {
						$("#publicCabinetCategoryId").val("");
						$("#publicCabinetCategoryName").val("");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	
	//页面首次加载
	$(function(){
		$("#type").val('${publicCabinet.type}');
	});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="publicCabinetForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="publicCabinetId" name="publicCabinet.id" value="%{publicCabinet.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="publicCabinetCategoryId" name="publicCabinet.publicCabinetCategory.id" value="%{publicCabinet.publicCabinetCategory.id}" theme="simple" /> <input type="text" id="publicCabinetCategoryName" name="publicCabinet.publicCabinetCategory.name" value="${publicCabinet.publicCabinetCategory.name}" readonly="readonly" /> <input
						class="btn" type="button" value="选择" onclick="chooseKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="publicCabinet.code" class="validate[required] text-input" value="${publicCabinet.code}" /> <span style="color: red;">*</span></td>
					<td align="right">文件名称:&nbsp;</td>
					<td><input type="text" id="fileName" name="publicCabinet.fileName" class="validate[required] text-input" value="${publicCabinet.fileName}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">姓名:&nbsp;</td>
					<td><input type="text" id="keyword" name="publicCabinet.keyword" value="${publicCabinet.keyword}" /></td>
					<td align="right">类型：</td>
					<td><select id="type" name="publicCabinet.type" style="width: 50" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="1">技术类</option>
							<option value="2">资料类</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">日期：</td>
					<td><input id="createTime" name="publicCabinet.createTime" value="${publicCabinet.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="publicCabinet.memo" size="45" class="validate[required] text-input" value="${publicCabinet.memo}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th>上传文件：</th>
					<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>