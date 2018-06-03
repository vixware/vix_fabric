<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	$('#parentId').val($('#selectId').val());
	var parentName = $('#selectName').val();
	if(parentName=='')
		parentName = '根节点';
	$('#parentNodeName').text(parentName);
});

function saveEqCategory(){
	$("#eqCategoryForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/accountManageAction!saveEqCategory.action",
	     dataType: "text",
	     success: function(result){
	    	var dataId = result;
	    	if(dataId>0){
		    	$('#categoryId').val(dataId);
		    	showMessage('设结构信息保存完毕');
		    	
		    	_tab_close_current_and_reload_opener_grid();

		    	_pad_refreshTreeSelectedNoteDelay('tree_root');
		    	//setTimeout(_pad_refreshTreeSelectedNote,1000);
	    	}
	     }
	 });
}
</script>
</head>
<body>
	<s:form id="eqCategoryForm" name="eqCategoryForm" action="" method="post" theme="simple">
		<input type="hidden" id="parentId" name="eqStructure.parentId" />
		<input type="hidden" id="categoryId" name="eqStructure.id" />
		<div class="order_table">
			<table>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<th width="100">父节点：</th>
					<td><span id="parentNodeName"></span></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>设备编号：</th>
					<td><input id="eqCode" name="eqStructure.eqCode" type="text" /></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>设备名称：</th>
					<td><input id="eqName" name="eqStructure.eqName" type="text" /></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>备注说明：</th>
					<td colspan="3"><input id="memo" name="eqStructure.memo" type="text" /></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<th></th>
				<td><input name="" id="" onclick="javascript:return saveEqCategory()" type="button" class="btn" value="保存" /></td>
				<th></th>
				<td></td>
			</table>
		</div>
	</s:form>
</body>
</html>