<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#policiesForm").validationEngine();
	function chooseCategory() {
		$.ajax({
		url : '${vix}/hr/policiesAction!goPoliciesCategory.action',
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
						$("#polSysManageCategoryId").val(result[0]);
						$("#polSysManageCategoryName").val(result[1]);
					} else {
						$("#polSysManageCategoryId").val("");
						$("#polSysManageCategoryName").val("");
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
	$("#states").val('${polSysManage.states}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="policiesForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="polSysManageId" name="polSysManage.id" value="%{polSysManage.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="polSysManageCategoryId" name="polSysManage.polSysManageCategory.id" value="%{polSysManage.polSysManageCategory.id}" theme="simple" /> <input type="text" id="polSysManageCategoryName" name="polSysManage.polSysManageCategory.name" value="${polSysManage.polSysManageCategory.name}" readonly="readonly" /> <input class="btn"
						type="button" value="选择" onclick="chooseCategory();" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="polSysManage.code" class="validate[required] text-input" value="${polSysManage.code}" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="fileName" name="polSysManage.fileName" class="validate[required] text-input" value="${polSysManage.fileName}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">姓名:&nbsp;</td>
					<td><input type="text" id="polName" name="polSysManage.polName" value="${polSysManage.polName}" /></td>
					<td align="right">类型：</td>
					<td><select id="states" name="polSysManage.states" style="width: 50" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="1">办公用品管理制度</option>
							<option value="2">会议管理制度</option>
							<option value="3">档案管理制度</option>
							<option value="4">保密制度</option>
							<option value="5">电脑使用管理规定</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">日期：</td>
					<td><input id="createTime" name="polSysManage.createTime" value="${polSysManage.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
				</tr>
				<tr height="40">
					<td align="right">内容描述:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="polSysManage.memo" size="45" value="${polSysManage.memo}" /></td>
				</tr>
				<tr height="40">
					<th>上传文件：</th>
					<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>