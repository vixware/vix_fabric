<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<div style="padding-left: 4px;">
			<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#">根目录</a>
		</div>
		<div id="chooseItemCatalogZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		<!--
		var chooseItemCatalogZtree;			
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				radioType: "level"
			},
			async: {
				enable: true,
				url:"${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
				autoParam:["id", "name=n", "level=lv"]
			},
			callback: {
				onCheck: onCheck
			}
		};
		setting.check.chkboxType = { "Y" : "", "N" : "" };
		chooseItemCatalogZtree = $.fn.zTree.init($("#chooseItemCatalogZtree"), setting);
		function onCheck(e, treeId, treeNode) {
			var ids = "";
			var names = "";
			var nodes = chooseItemCatalogZtree.getCheckedNodes(true);
			for (var i=0; i<nodes.length; i++){
				var node = nodes[i];
				ids += node.id + ",";
				names += node.name + ",";
			}
			$.returnValue = ids+":"+names;
		}
		//-->
		</script>
</div>
