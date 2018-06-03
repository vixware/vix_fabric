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
		url : "${vix}/drp/distributerManagementAction!findOrgAndUnitTreeToJson.action",
		autoParam : [ "id", "treeType" ]
		},
		callback : {
		onClick : onClick,
		onCheck : onCheck
		}
		};
		function onClick(event, treeId, treeNode, clickFlag) {
			pager("start", "${vix}/drp/distributerManagementAction!goSingleList.action?id=" + treeNode.id + "&treeType=" + treeNode.treeType, "channelDistributor");
		}
		var type = $("#level").attr("checked") ? "level" : "all";
		setting.check.radioType = type;
		zTree = $.fn.zTree.init($("#chooseCategoryZtree"), setting);
		function onCheck(e, treeId, treeNode) {
			$.returnValue = treeNode.id + ',' + treeNode.name;
		}
	</script>
</div>
