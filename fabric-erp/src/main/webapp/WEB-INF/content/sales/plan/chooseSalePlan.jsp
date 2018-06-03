<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<div style="padding-left: 4px;">
			<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#">根目录</a>
		</div>
		<div id="chooseSalePlanZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		<!--
		var chooseSalePlanZtree;			
		var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "level"
			},
			async: {
				enable: true,
				url:"${vix}/sales/plan/salePlanAction!findTreeToJson.action",
				autoParam:["id", "name=n", "level=lv"]
			},
			callback: {
				onCheck: onCheck
			}
		};
		var type = $("#level").attr("checked")? "level":"all";
		setting.check.radioType = type;
		chooseSalePlanZtree = $.fn.zTree.init($("#chooseSalePlanZtree"), setting);
		function onCheck(e, treeId, treeNode) {
			$.returnValue = treeNode.id+','+treeNode.name;
		}
		//-->
		</script>
</div>
