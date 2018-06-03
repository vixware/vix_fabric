<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<div id="chooseCategoryZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		var chooseDepzTree;
		var setting = {
		check : {
		enable : true,
		chkStyle : "checkbox",
		chkboxType : {
		"Y" : "",
		"N" : ""
		}
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
		chooseDepzTree = $.fn.zTree.init($("#chooseCategoryZtree"), setting);

		function onCheck(e, treeId, treeNode) {
			$.returnValue = "[{" + '\"treeid\"' + ':\"' + treeNode.id + '\",' + '\"treename\"' + ':\"' + treeNode.name + '\",' + '\"treetype\"' + ':\"' + treeNode.treeType + "\"}]";
		}
	</script>
</div>
