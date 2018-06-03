<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="chooseTree">
		<div id="chooseProductCategoryZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<input type="hidden" id="orgPositionId" name="" value="" /> 
	<input type="hidden" id="employeeId" name="" value="${id }" />
	<script type="text/javascript">
		function onCheck(e, treeId, treeNode) {
			$("#orgPositionId").val(treeNode.id);
		}
		var setting = {
		check : {
		enable : true,
		chkStyle : "radio",
		radioType : "all"
		},
		async : {
		enable : true,
		url : "${nvix}/nvixnt/wxpEmployeeAction!findOrgPositionViewTreeToJson.action",
		autoParam : [ "id", "name=n", "level=lv" ]
		},
		callback : {
			onCheck : onCheck
		}
		};
		var chooseProductCategoryZtree = $.fn.zTree.init($("#chooseProductCategoryZtree"), setting);
	</script>
</div>
