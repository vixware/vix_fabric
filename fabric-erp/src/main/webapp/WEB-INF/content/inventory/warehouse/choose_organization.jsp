<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<div id="chooseCategoryZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		var zTree;
		var setting = {
		check : {
		enable : true,
		chkStyle : "radio",
		radioType : "level"
		},
		async : {
		enable : true,
		url : "${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
		autoParam : [ "id", "treeType" ]
		},
		callback : {
			onCheck : onCheck
		}
		};
		var type = $("#level").attr("checked") ? "level" : "all";
		setting.check.radioType = type;
		zTree = $.fn.zTree.init($("#chooseCategoryZtree"), setting);
		function onCheck(e, treeId, treeNode) {
			$.returnValue = treeNode.id + ',' + treeNode.name;
		}
	</script>
</div>
