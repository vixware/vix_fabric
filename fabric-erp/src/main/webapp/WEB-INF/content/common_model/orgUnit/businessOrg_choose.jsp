<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/page/taglibs.jsp"%>
<div style="padding: 10px;">
	<div id="tree">
		<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/></div> --%>
		<div id="orgUnitBoTree" class="ztree" style="padding: 0;"></div>
	</div>
	<script type="text/javascript">
		<!--
		$(document).ready(function(){
			
			
			var chooseBusinessOrgTree;			
			var setting = {
				check: {
					enable: true,
					chkboxType: {"Y":"", "N":""}
				},
				view: {
					dblClickExpand: false
				},
				callback: {
					//beforeClick: beforeClick,
					onCheck: onCheck
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: 0
					}
				}
			};
			
			function onCheck(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("orgUnitBoTree");
				var nodes = zTree.getCheckedNodes(true);
				var objNames = "";
				var objIds ="";
				for (var i=0, l=nodes.length; i<l; i++) {
					objNames += nodes[i].name + ",";
					objIds += nodes[i].id + ",";
				}
				if (objNames.length > 0 ) objNames = objNames.substring(0, objNames.length-1);
				if (objIds.length > 0 ) objIds = objIds.substring(0, objIds.length-1);
				
				//$("#entityBusinessOrgId").attr("value", objIds);
				//$("#entityBusinessOrgName").attr("value", objNames);
				$.returnValue = objIds+';'+objNames;
			}
			
			
			 vixAjaxSendHtml("${vix}/common/org/organizationUnitAction!goSaveOrUpdateJson.action","id=${id}",function(data){
				//debugger;
				var treeData = $.parseJSON(data);
				 $.fn.zTree.init($("#orgUnitBoTree"), setting,treeData);
			}); 
			/* var treeData = [
			                {"id":12,"pId":0,"name":"a2","isParent":true,"checked":false,
			                	"children":[{"id":13,"pId":12,"name":"vvvvvvvvvvv","isParent":false,"checked":false}]},
			                {"id":1,"pId":0,"name":"测试根节点1","isParent":false,"checked":false}
			               ]; */
			// $.fn.zTree.init($("#orgUnitBoTree"), setting,treeData);
			//
			//alert($("#entityBusinessOrgJsonStr").val());
			//var zNodes = $.parseJSON($("#entityBusinessOrgJsonStr").val());
		}); 
		
		//-->
		</script>
</div>
