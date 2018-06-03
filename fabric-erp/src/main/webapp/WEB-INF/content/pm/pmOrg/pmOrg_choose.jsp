<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();">根节点</a></div> --%>
		<div id="chooseCategoryZtree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		<!--
		var chooseDepzTree;			
		var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "level"
			},
			async: {
				enable: true,
				url:"${vix}/pm/org/pmOrgAction!findOrgAndUnitTreeToJson.action",
				autoParam:["treeId","treeType"]
			},
			callback: {
				onCheck: onCheck
			}/* ,
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			} */
		};
		var type = $("#level").attr("checked")? "level":"all";
		setting.check.radioType = type;
		
		chooseDepzTree = $.fn.zTree.init($("#chooseCategoryZtree"), setting);
		function onCheck(e, treeId, treeNode) {
			$.returnValue = treeNode.treeId+','+treeNode.name+','+treeNode.treeType;
		}
		
		/* $(document).ready(function(){
			vixAjaxSendHtml("${vix}/pm/org/pmOrgAction!findOrgAndUnitTreeToJson.action","a=1",function(data){
				var treeData = $.parseJSON(data);
				chooseDepzTree = $.fn.zTree.init($("#chooseCategoryZtree"), setting,treeData);
			});
			
		}); 
		
		function onCheck(e, treeId, treeNode) {
			$.returnValue = treeNode.id+','+treeNode.name+','+treeNode.treeType;
		} */
		//-->
		</script>
</div>
