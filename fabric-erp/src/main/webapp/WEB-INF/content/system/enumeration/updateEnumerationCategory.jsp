<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	<!--
	var setting = {
		view: {
			dblClickExpand: false
		},
		async: {
			enable: true,
			url:"${vix}/system/enumerationCategoryAction!findTreeToJson.action",
			autoParam:["id", "name=n", "level=lv"]
		},
		callback: {
			onClick: onClick
		}
	};
	
	function onClick(e, treeId, treeNode) {
		$("#parentEnumCategoryId").attr("value", treeNode.id);
		$("#parentEnumCategory").attr("value", treeNode.name);
		hideMenu();
	}

	function showMenu() {
		$("#treeMenuContent").css({left:80 + "px", top:31 + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#treeMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "treeMenuContent" || $(event.target).parents("#treeMenuContent").length>0)) {
			hideMenu();
		}
	}

	$.fn.zTree.init($("#parentEnumCategoryTree"), setting);
	
	$("#enumerationCategoryForm").validationEngine();
	//-->
</script>
<div class="content" style="margin: 0; margin-top: 5px; overflow: hidden">
	<form id="enumerationCategoryForm">
		<div class="box order_table" style="line-height: normal; padding: 3px;">
			<table sytle="width:100%;">
				<s:hidden id="id" name="enumerationCategory.id" value="%{enumerationCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td><input id="parentEnumCategoryId" type="hidden" value="${enumerationCategory.parentEnumerationCategory.id}" /> <input id="parentEnumCategory" type="text" readonly="readonly" onfocus="showMenu(); return false;" value="${enumerationCategory.parentEnumerationCategory.name}" />
						<div id="treeMenuContent" class="treeMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
							<ul id="parentEnumCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
						</div></td>
				</tr>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="enumerationCategory.name" value="${enumerationCategory.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="enumerationCategory.memo" value="${enumerationCategory.memo}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>