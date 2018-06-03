<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<article style="width: 230px; float: left; position: absolute; height: 515px; overflow-x: hidden; overflow-y: auto;">
		<ul id="tree" class="ztree"></ul>
		<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" value="" />
		<script type="text/javascript">
			<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action",
						autoParam:["idStr", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.idStr);
					$("#selectIdTreeId").val(treeNode.tId);
					departmentTable.ajax.reload();
				}
				zTree = $.fn.zTree.init($("#tree"), setting);
			//-->
		</script>
	</article>
</div>
