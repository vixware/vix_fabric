<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script type="text/javascript">
function chooseKnowledgeCategory(){
	$.ajax({
		  url:'${vix}/mdm/item/knowledgeCategoryAction!goChooseKnowledgeCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择知识库分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#knowledgeCategoryId").val(result[0]);
								$("#knowledgeCategoryName").val(result[1]);
							}else{
								$("#knowledgeCategoryId").val("");
								$("#knowledgeCategoryName").val("");
								asyncbox.success("<s:text name='pleaseChooseKnowledgeCategory'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$('#knowledgeForm').validationEngine();
var editor = KindEditor.create('textarea[name="kmAnswer"]',
	{basePath:'${vix}/plugin/KindEditor/',
		width:550,
		height:250,
		cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
		uploadJson : '${vix}/common/kindEditorAction!uploadFile.action',
		fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
		allowFileManager : true 
	}
);
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="knowledgeForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="knowledge.id" value="%{knowledge.id}" theme="simple" />
				<s:hidden id="knowledgeCategoryId" name="knowledgeCategoryId" value="%{knowledge.knowledgeCategory.id}" theme="simple" />
				<tr height="30">
					<td align="right">主题:&nbsp;</td>
					<td><input id="subject" name="knowledge.subject" value="${knowledge.subject}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">发布时间:&nbsp;</td>
					<td><input id="publishTime" name="knowledge.publishTime" value="<s:property value='formatDateToString(knowledge.publishTime)'/>" class="validate[required] text-input" /> <img onclick="showTime('publishTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
					</td>
				</tr>
				<tr height="30">
					<td align="right">知识点编码 :&nbsp;</td>
					<td><input id="kmCode" name="knowledge.kmCode" value="${knowledge.kmCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">知识点:&nbsp;</td>
					<td><input id="kmQuestion" name="knowledge.kmQuestion" value="${knowledge.kmQuestion}" class="validate[required] text-input" /><span style="color: red;">*</span></td>

				</tr>
				<tr height="30">
					<td align="right">类型:&nbsp;</td>
					<td><input id="kmType" name="knowledge.kmType" value="${knowledge.kmType}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">大小:&nbsp;</td>
					<td><input id="keSize" name="knowledge.keSize" value="${knowledge.keSize}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">授权给:&nbsp;</td>
					<td><input id="assignee" name="knowledge.assignee" value="${knowledge.assignee}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">分类:&nbsp;</td>
					<td><span id=""><s:property value="" /></span> <input id="knowledgeCategoryName" name="knowledgeCategoryName" value="${knowledge.knowledgeCategory.name}" type="text" readonly="readonly" /> <span class="abtn"><span onclick="chooseKnowledgeCategory();">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">解答:&nbsp;</td>
					<td align="left" colspan="3"><textarea id="kmAnswer" name="kmAnswer">${knowledge.kmAnswer}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>