<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){$(this).addClass("btnhover");},function(){$(this).removeClass("btnhover");});
function saveOrUpdateDetail(id){
	var height = 0;
	if(id > 0){
		height = 290;
	}else{
		height = 420;
	}
	$.ajax({
		  url:'${vix}/system/objectExpandFieldAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 360,
					height : height,
					title:"扩展对象类型明细",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#objectExpandFieldForm').validationEngine('validate')){
								var isRequired = "";
								if($("#id").val() != ''){
									isRequired = $("#isRequired").val();
								}else{
									isRequired = $(":radio[name=isRequired][checked]").val();
								}
								$.post('${vix}/system/objectExpandFieldAction!saveOrUpdate.action',
									{'objectExpandField.id':$("#objectExpandFieldId").val(),
									  'objectExpandField.displayName':$("#displayName").val(),
									  'objectExpandField.fieldName':$("#fieldName").val(),
									  'objectExpandField.fieldType':$("#fieldType").val(),
									  'objectExpandField.length':$("#length").val(),
									  'objectExpandField.accuracy':$("#accuracy").val(),
									  'objectExpandField.refTag':$("#refTag").val(),
									  'objectExpandField.memo':$("#memo").val(),
									  'objectExpandField.defaultValue':$("#defaultValue").val(),
									  'objectExpandField.isRequired':isRequired,
									  'objectExpandField.isShow':$(":radio[name=isShow][checked]").val(),
									  'objectExpandField.orderNumber':$("#orderNumber").val(),
									  'objectExpandField.extendedObjectCode':$("#extendedObjectCode").val(),
									  'objectExpandField.isGenerateTable':$("#isGenerateTable").val(),
									  'objectExpandField.objectExpand.id':$("#id").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("start","${vix}/system/objectExpandFieldAction!goSingleList.action?id="+$("#id").val(),'objectExpandField');
										});
									}
								 );
							}else{
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteExpandField(row,id){
	asyncbox.confirm('确定要删除该扩展类型明细么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$(row).parent().parent().remove();
			$.ajax({
				  url:'${vix}/system/objectExpandFieldAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					  asyncbox.success(html,"<s:text name='vix_message'/>");
				  }
			});
		}
	});
}
pager("start","${vix}/system/objectExpandFieldAction!goSingleList.action?id="+$("#id").val(),'objectExpandField');
$("#objectExpandForm").validationEngine();
if($("#id").val() != ""){
	$("#objectExpandSave").attr('style','display:none');
	$("#objectExpandItem").attr('style','display:inline;color:black;line-height: 18px;');
}
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="objectExpandForm">
		<div id="updateObjectExpand">
			<input type="hidden" id="id" name="objectExpand.id" value="${objectExpand.id}" /> <input type="hidden" id="isGenerateTable" name="objectExpand.isGenerateTable" value="${objectExpand.isGenerateTable}" /> <input type="hidden" id="specTableIsGenerate" name="objectExpand.specTableIsGenerate" value="${objectExpand.specTableIsGenerate}" /> <input
				type="hidden" id="code" name="objectExpand.code" value="${objectExpand.code}" /> <input type="hidden" id="isReference" name="objectExpand.isReference" value="${objectExpand.isReference}" /> <input type="hidden" id="foreignKey" name="objectExpand.foreignKey" value="${objectExpand.foreignKey}" />
			<div class="box order_table" style="line-height: normal;">
				<table>
					<tr height="30">
						<td align="right">名称:&nbsp;</td>
						<td><input id="name" name="objectExpand.name" value="${objectExpand.name}" /></td>
						<td align="right">扩展对象:&nbsp;</td>
						<td><select id="extendedObjectCode">
								<option value="-1">------请选择------</option>
								<s:iterator value="expandTypeConstantMap.entrySet().iterator()" var="key">
									<s:if test="objectExpand.extendedObjectCode == key">
										<option value="<s:property value='key'/>" selected="selected"><s:text name="getText(value)" /></option>
									</s:if>
									<s:else>
										<option value="<s:property value='key'/>"><s:text name="getText(value)" /></option>
									</s:else>
								</s:iterator>
						</select></td>
					</tr>
					<tr height="30">
						<td align="right">状态:&nbsp;</td>
						<td><s:if test="objectExpand.status == 0">
								<input type="radio" id="status1" name="status" value="1" />激活
								<input type="radio" id="status2" name="status" value="0" checked="checked" />禁用
							</s:if> <s:elseif test="objectExpand.status == 1">
								<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
								<input type="radio" id="status2" name="status" value="0" />禁用
							</s:elseif> <s:else>
								<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
								<input type="radio" id="status2" name="status" value="0" />禁用
							</s:else></td>
						<td align="right">备注:&nbsp;</td>
						<td><s:textfield id="memo" name="objectExpand.memo" value="%{objectExpand.memo}" theme="simple" /></td>
					</tr>
				</table>
				<div class="table" style="margin: 5px 0;">
					<div class="pagelist drop" style="height: 25px; margin: 5px 0;">
						<span style="color: black; vertical-align: middle;">明细列表</span> <input id="objectExpandItem" style="display: none; line-height: 18px;" type="button" class="btn" value="添加" onclick="saveOrUpdateDetail(0);">
					</div>
					<div id="objectExpandField" class="table" style="overflow-x: auto; overflow-y: hidden; width: 100%; padding-bottom: 5px;"></div>
				</div>
			</div>
		</div>
	</form>
</div>