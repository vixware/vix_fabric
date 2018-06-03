<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#privateCabinetForm").validationEngine();
	function chooseKnowledgeBaseCategory() {
		$.ajax({
		url : '${vix}/oa/privateCabinetAction!goPrivateCabinetCategory.action',
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
						$("#privateCabinetCategoryId").val(result[0]);
						$("#privateCabinetCategoryName").val(result[1]);
					} else {
						$("#privateCabinetCategoryId").val("");
						$("#privateCabinetCategoryName").val("");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	
	$(function() {
    //加载更新日期(新增)
    if (document.getElementById("createTime").value == "") {
	    var myDate = new Date();
	    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
    }
});
	
//页面首次加载
$(function(){
	$("#type").val('${privateCabinet.type}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="privateCabinetForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="privateCabinetId" name="privateCabinet.id" value="%{privateCabinet.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="privateCabinetCategoryId" name="privateCabinet.privateCabinetCategory.id" value="%{privateCabinet.privateCabinetCategory.id}" theme="simple" /> <input type="text" id="privateCabinetCategoryName" name="privateCabinet.privateCabinetCategory.name" value="${privateCabinet.privateCabinetCategory.name}" readonly="readonly" />
						<input class="btn" type="button" value="选择" onclick="chooseKnowledgeBaseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">排序号:&nbsp;</td>
					<td><input type="text" id="code" name="privateCabinet.code" class="validate[required] text-input" value="${privateCabinet.code}" /> <span style="color: red;">*</span></td>
					<td align="right">文件名称:&nbsp;</td>
					<td><input type="text" id="fileName" name="privateCabinet.fileName" class="validate[required] text-input" value="${privateCabinet.fileName}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">姓名:&nbsp;</td>
					<td><input type="text" id="keyword" name="privateCabinet.keyword" value="${privateCabinet.keyword}" /></td>
					<td align="right">类型：</td>
					<td><select id="type" name="privateCabinet.type" style="width: 50" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="1">工作文件柜</option>
							<option value="2">私人文件柜</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">日期：</td>
					<td><input id="createTime" name="privateCabinet.createTime" value="${privateCabinet.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="privateCabinet.memo" size="45" value="${privateCabinet.memo}" /></td>
				</tr>
				<tr height="40">
					<th>上传文件：</th>
					<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>