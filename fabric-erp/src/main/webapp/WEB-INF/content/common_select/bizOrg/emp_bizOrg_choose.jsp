<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" >根节点</a></div> --%>
		<div id="chooseEmpBizOrgZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		<!--
		var choosEmpBizOrgTree;			
		var empBizOrgTreeSetting = {
			check: {
				enable: true,
				chkStyle: "${chkStyle}",
				radioType: "level",
				chkboxType: { "Y": "", "N": "" }
			},
			async: {
				enable: true,
				url:"${vix}/common/select/commonSelectEmpByBizOrgAction!findEmpBizOrgTreeToJson.action?bizViewIdStr=${bizViewIdStr}",
				autoParam:["treeId"]
			},
			callback: {
				onCheck: onCheckEmpBizOrg
			}
		};
		var type = $("#level").attr("checked")? "level":"all";
		empBizOrgTreeSetting.check.radioType = type;
		choosEmpBizOrgTree = $.fn.zTree.init($("#chooseEmpBizOrgZtree"), empBizOrgTreeSetting);
		
		function onCheckEmpBizOrg(e, treeId, treeNode) {
			//$.returnValue = treeNode.treeId+','+treeNode.name+','+treeNode.treeType;
			//var treeObj = $.fn.zTree.getZTreeObj("chooseEmpBizOrgZtree");
			var nodes = choosEmpBizOrgTree.getCheckedNodes(true);
			$.returnValue = JSON.stringify(nodes);
			/* var resVal = "";
			if(nodes.length>0){
				var resArray = new Array();
				for(var i=0;i<nodes.length;i++){
					var node = nodes[i];
					resArray.put( node.treeId+','+node.name+','+node.treeType);
				}
				resArra.
			} */
			
		}
		//-->
		</script>
</div>
