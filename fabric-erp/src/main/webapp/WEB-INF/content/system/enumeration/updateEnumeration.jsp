<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){$(this).addClass("btnhover");},function(){$(this).removeClass("btnhover");});
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function addEnumDetail(){
	var enumValueCount = $("#enumValueCount").val();
	var netCount = Number(enumValueCount)+1;
	$("#enumValueCount").val(netCount);
	var html = "<tr>";
	html = html + "<td><input id='enumerationId_"+ netCount +"' value='' type='hidden'/><input id='enumerationRadio_"+ netCount +"' type='radio' value='0' name='enumerationRadio'/></td>";
	html = html + "<td><input id='enumerationCheckbox_"+ netCount +"' type='checkbox' value='0' /></td>";
	html = html + "<td><input id='enumerationDisplayName_"+ netCount +"' type='text' style='height:20px;width:95%;' onblur='displayNameOnBlur("+ netCount +");'/></td>";
	html = html + "<td><input id='enumerationValue_"+ netCount +"' type='text' style='height:20px;width:95%;'/></td>";
	html = html + "<td><a href='###' onclick='deleteRow(this,0)'>删除</a></td>";
	html += "</tr>";
	$("#tableUpdate>tbody:last").append(html);
	$("#tableUpdate tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
	$("#tableUpdate tr:even").addClass("alt");
}
var enumSetting = {
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
	$("#enumCategoryId").attr("value", treeNode.id);
	$("#enumCategory").attr("value", treeNode.name);
	hideMenu();
}
function showMenu() {
	$("#enumMenuContent").css({left:50 + "px", top:32 + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#enumMenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "enumMenuContent" || $(event.target).parents("#enumMenuContent").length>0)) {
		hideMenu();
	}
}
$.fn.zTree.init($("#enumCategoryTree"), enumSetting);
$("#enumerationForm").validationEngine();
function deleteRow(td,id){
	var enumValueCount = $("#enumValueCount").val();
	var netCount = Number(enumValueCount)-1;
	$("#enumValueCount").val(netCount);
	$(td).parent().parent().remove();
	if(id != '' && id != undefined){
		$.ajax({
			  url:'${vix}/system/enumerationAction!deleteEnumValueById.action?id='+id,cache: false
		});
	}
}
function displayNameOnBlur(count){
	if($("#enumerationDisplayName_"+count).val() != ''){
		$("#enumerationCheckbox_"+count).attr('checked','checked');
	}else{
		$("#enumerationCheckbox_"+count).attr('checked',false);
	}
}
</script>
<div class="content" style="margin: 0; margin-top: 5px; overflow: hidden">
	<form id="enumerationForm">
		<div class="box order_table" style="line-height: normal; padding: 3px;">
			<table style="width: 100%;">
				<s:hidden id="id" name="enumeration.id" value="%{enumeration.id}" theme="simple" />
				<s:hidden id="code" name="enumeration.code" value="%{enumeration.code}" theme="simple" />
				<tr height="30">
					<td align="right">分类:&nbsp;</td>
					<td><input id="enumCategoryId" type="hidden" value="${enumeration.enumerationCategory.id}" /> <input id="enumerationCode" type="hidden" value="${enumeration.enumerationCode}" /> <input id="enumCategory" type="text" readonly="readonly" onfocus="showMenu(); return false;" value="${enumeration.enumerationCategory.name}" />
						<div id="enumMenuContent" class="enumMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
							<ul id="enumCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
						</div></td>
					<td align="right">编码:&nbsp;</td>
					<td><span style="cursor: pointer; font-weight: bold;">${enumeration.enumerationCode}</span></td>
				</tr>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="enumeration.name" value="${enumeration.name}" type="text" /></td>
					<td align="right">显示名称:&nbsp;</td>
					<td><input id="displayName" name="enumeration.displayName" value="${enumeration.displayName}" type="text" /></td>
				</tr>
			</table>
			<div class="table" style="margin: 5px 0;">
				<p id="table_top" style="height: 25px;">
					<span style="color: black;">明细列表</span> <input id="add" style="line-height: 18px;" type="button" class="btn" value="添加" onclick="addEnumDetail(0);">
				</p>
				<input id="enumValueCount" name="enumValueCount" type="hidden" value="${enumeration.valueSize}" />
				<table id="tableUpdate" class="list">
					<tr>
						<th width="10%">缺省&nbsp;</th>
						<th width="10%">启用&nbsp;</th>
						<th width="40%">显示名称</th>
						<th width="30%">值</th>
						<th width="10%" style="text-align: left;">操作</th>
					</tr>
					<s:iterator value="enumeration.enumValues" var="entity" status="s">
						<tr>
							<td><input id="enumerationId_${s.count}" value="${entity.id}" type="hidden" /> <s:if test="#entity.isDefault == 1">
									<input id="enumerationRadio_${s.count}" type="radio" value="1" name="enumerationRadio" checked="checked" />
								</s:if> <s:else>
									<input id="enumerationRadio_${s.count}" type="radio" value="0" name="enumerationRadio" />
								</s:else></td>
							<td><s:if test="#entity.enable == 1">
									<input id="enumerationCheckbox_${s.count}" type="checkbox" value="1" checked="checked" />
								</s:if> <s:else>
									<input id="enumerationCheckbox_${s.count}" type="checkbox" value="0" />
								</s:else></td>
							<input id="enumValueCode_${s.count }" type="hidden" value="${entity.enumValueCode}" />
							<td><input id="enumerationDisplayName_${s.count}" type="text" style="height: 20px; width: 95%;" value="${entity.displayName }" /></td>
							<td><input id="enumerationValue_${s.count}" type="text" style="height: 20px; width: 95%;" value="${entity.value }" onblur="directoryBlur('enumeration',${s.count});" /></td>
							<td><a href="###" onclick="deleteRow(this,${entity.id})">删除</a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</form>
</div>
