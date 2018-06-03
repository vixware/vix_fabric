<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
</script>
<div class="content" style="margin-top: 15px; overflow: hidden;">
	<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
		<div style="padding: 8px;">
			<table id="dlSuppliers" class="easyui-datagrid" style="height: 450px; width: 700px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlSuppliersTb',url: '${vix}/purchase/invitingTenderAction!getSuppliersJson.action?ptID=${ptID}',onClickRow: onDlClickRow">
				<thead>
					<tr>
						<th data-options="field:'code',width:280,align:'center',editor:'text'">编码</th>
						<th data-options="field:'name',width:400,align:'center',editor:'text'">名称</th>
					</tr>
				</thead>
			</table>
			<script type="text/javascript">
				$('#dlSuppliers').datagrid();
				function onDlClickRow(){
					var rows = $('#dlSuppliers').datagrid('getSelected');
					if(null == rows){
						alert("请选择一条数据！");
						return;
					}
					$.ajax({
						  url:'${vix}/srm/managementBusinessPartnerAction!goSaveOrUpdate.action?id='+rows.id,
						  cache: false,
						  success: function(html){
							  $("#mainContent").html(html);
						  }
					});
				}
			</script>
		</div>
	</div>
</div>