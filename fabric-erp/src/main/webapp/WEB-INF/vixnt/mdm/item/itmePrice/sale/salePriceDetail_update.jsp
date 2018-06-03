<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="objectExpandDetailForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/system/nvixObjectExpandAction!saveOrUpdateDetail.action">
	<input type="hidden" id="id" name="objectExpandField.id" value="${objectExpandField.id}" /> 
	<input type="hidden" id="parentId" name="objectExpandField.objectExpand.id" value="${parentId}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>显示名称:</label>
			<div class="col-md-4">
				<input id="displayName" name="objectExpandField.displayName" value="${objectExpandField.displayName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>列名:</label>
			<div class="col-md-4">
				<input id="fieldName" name="objectExpandField.fieldName" value="${objectExpandField.fieldName}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>列类型:</label>
			<div class="col-md-4">
				<select id="fieldType" name="objectExpandField.fieldType" onchange="fieldTypeChange(this);" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<s:iterator value="columnTypeConstantMap">
						<option value="${key}" <s:if test="objectExpandField.fieldType == key">selected='selected'</s:if>><s:text name="getText(value)"/></option>
					</s:iterator>
				</select>
			</div>
			<label class="col-md-2 control-label">默认值:</label>
			<div class="col-md-4">
				<input id="defaultValue" name="objectExpandField.defaultValue" value="${objectExpandField.defaultValue}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<div id="accuracyShow" style="display: none;">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>精度:</label>
				<div class="col-md-4">
					<input id="accuracy" name="objectExpandField.accuracy" value="${objectExpandField.accuracy}" class="form-control validate[required,custom[integer]]" type="text" />
				</div>
			</div>
			<div id="lengthShow" style="display:;">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>长度:</label>
				<div class="col-md-4">
					<input id="length" name="objectExpandField.length" value="${objectExpandField.length}" class="form-control validate[required,custom[integer]]" type="text" />
				</div>
			</div>
			<div id="refShow" style="display: none;">
				<input type="hidden" id="refTag" name="objectExpandField.refTag" value="%{objectExpandField.refTag}"/>
				<label class="col-md-2 control-label">引用:</label>
				<div class="col-md-4">
					<input type="file" id="docToUpload" name="docToUpload" class="validate[required]">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否必填:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="objectExpandField.isRequired == 1">active</s:if>">
						<input type="radio" value="1" id="isRequired" name="objectExpandField.isRequired" <s:if test="objectExpandField.isRequired == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="objectExpandField.isRequired == 0">active</s:if>">
						<input type="radio" value="0" id="isRequired" name="objectExpandField.isRequired" <s:if test="objectExpandField.isRequired == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">是否显示:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="objectExpandField.isShow == 1">active</s:if>">
						<input type="radio" value="1" id="isShow" name="objectExpandField.isShow" <s:if test="objectExpandField.isShow == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="objectExpandField.isShow == 0">active</s:if>">
						<input type="radio" value="0" id="isShow" name="objectExpandField.isShow" <s:if test="objectExpandField.isShow == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>顺序:</label>
			<div class="col-md-4">
				<input id="orderNumber" name="objectExpandField.orderNumber" value="${objectExpandField.orderNumber}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea rows="3" id="memo" name="objectExpandField.memo" value="${objectExpandField.memo}" class="form-control">${objectExpand.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
function fieldTypeChange(select){
	var sel = $(select).val();
	if(sel == 'number'){
		$("#accuracyShow").css({'display':''});
		$("#refShow").css({'display':'none'});
		return;
	}
	if(sel =='radio' || sel =='select' || sel == 'checkbox'){
		$("#refShow").css({'display':''});
		$("#accuracyShow").css({'display':'none'});
		return;
	}
	if(sel == 'date'){
		$("#lengthShow").css({'display':'none'});
		$("#refShow").css({'display':'none'});
		$("#accuracyShow").css({'display':'none'});
	}else{
		$("#lengthShow").css({'display':''});
	}
}

function chooseEnumeration(){
	$.ajax({
		  url:'${vix}/system/enumerationAction!goChooseEnumeration.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 440,
					title:"选择字典",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ""){
								var result = returnValue.split(":");
								$("#refTag").val(result[0]);
								$("#refEnumeration").html(result[1]);
							}else{
								$("#refTag").val("");
								$("#refEnumeration").html("");
								asyncbox.success("请选择字典信息!","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>