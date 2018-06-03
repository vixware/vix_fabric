<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
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
$("#objectExpandFieldForm").validationEngine();
</script>
<form id="objectExpandFieldForm">
	<div style="padding: 5px;">
		<s:hidden id="objectExpandFieldId" name="objectExpandField.id" value="%{objectExpandField.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">显示名称:&nbsp;</td>
					<td><input id="displayName" name="objectExpandField.displayName" value="${objectExpandField.displayName}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<s:if test="objectExpandField == null">
					<tr height="30">
						<td align="right">列名:&nbsp;</td>
						<td><input id="fieldName" name="objectExpandField.fieldName" value="${objectExpandField.fieldName}" class="validate[custom[onlyLetterNumber]] text-input" /><span style="color: red;">*</span></td>
					</tr>
				</s:if>
				<s:else>
					<input id="fieldName" name="objectExpandField.fieldName" value="${objectExpandField.fieldName}" type="hidden" />
				</s:else>
				<s:if test="objectExpandField == null">
					<tr height="30">
						<td align="right">列类型:&nbsp;</td>
						<td><select id="fieldType" onchange="fieldTypeChange(this);" class="validate[required]">
								<option value="">----请选择----</option>
								<s:iterator value="columnTypeConstantMap.entrySet().iterator()" var="key">
									<s:if test="objectExpandField.fieldType == key">
										<option value="<s:property value='key'/>" selected="selected"><s:text name="getText(value)" /></option>
									</s:if>
									<s:else>
										<option value="<s:property value='key'/>"><s:text name="getText(value)" /></option>
									</s:else>
								</s:iterator>
						</select></td>
					</tr>
					<tr id="accuracyShow" style="display: none;" height="30">
						<td align="right">精度:</td>
						<td><input id="accuracy" name="objectExpandField.accuracy" value="${objectExpandField.accuracy}" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span></td>
					</tr>
					<tr id="refShow" style="display: none;" height="30">
						<td align="right">引用:</td>
						<td><input type="hidden" id="refTag" name="objectExpandField.refTag" value="%{objectExpandField.refTag}" /> <span id="refEnumeration"></span> <span class="btn"><a href="#" onclick="chooseEnumeration();">引用</a></span></td>
					</tr>
				</s:if>
				<s:else>
					<input id="fieldType" name="objectExpandField.fieldType" value="${objectExpandField.fieldType}" type="hidden" />
					<input id="accuracy" name="objectExpandField.accuracy" value="${objectExpandField.accuracy}" type="hidden" />
					<input id="refTag" name="objectExpandField.refTag" value="%{objectExpandField.refTag}" type="hidden" />
				</s:else>
				<s:if test="objectExpandField == null">
					<tr id="lengthShow" style="display:;" height="30">
						<td align="right">长度:</td>
						<td><input id="length" name="objectExpandField.length" value="${objectExpandField.length}" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span></td>
					</tr>
					<tr height="30">
						<td align="right">默认值:&nbsp;</td>
						<td><s:textfield id="defaultValue" name="objectExpandField.defaultValue" value="%{objectExpandField.defaultValue}" theme="simple" /></td>
					</tr>
					<tr height="30">
						<td align="right">是否必填:</td>
						<td><s:if test="objectExpandField.isRequired == 0">
								<input type="radio" id="isRequired1" name="isRequired" value="1" />是
								<input type="radio" id="isRequired2" name="isRequired" value="0" checked="checked" />否
							</s:if> <s:elseif test="objectExpandField.isRequired == 1">
								<input type="radio" id="isRequired1" name="isRequired" value="1" checked="checked" />是
								<input type="radio" id="isRequired2" name="isRequired" value="0" />否
							</s:elseif> <s:else>
								<input type="radio" id="isRequired1" name="isRequired" value="1" />是
								<input type="radio" id="isRequired2" name="isRequired" value="0" />否
							</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<input id="length" name="objectExpandField.length" value="${objectExpandField.length}" type="hidden" />
					<input id="defaultValue" name="objectExpandField.defaultValue" value="${objectExpandField.defaultValue}" type="hidden" />
					<input id="isRequired" name="objectExpandField.isRequired" value="${objectExpandField.isRequired}" type="hidden" />
				</s:else>
				<tr height="30">
					<td align="right">是否显示:&nbsp;</td>
					<td><s:if test="objectExpandField.isShow == 0">
							<input type="radio" id="isShow1" name="isShow" value="1" />是
							<input type="radio" id="isShow2" name="isShow" value="0" checked="checked" />否
						</s:if> <s:elseif test="objectExpandField.isShow == 1">
							<input type="radio" id="isShow1" name="isShow" value="1" checked="checked" />是
							<input type="radio" id="isShow2" name="isShow" value="0" />否
						</s:elseif> <s:else>
							<input type="radio" id="isShow1" name="isShow" value="1" />是
							<input type="radio" id="isShow2" name="isShow" value="0" />否
						</s:else></td>
				</tr>
				<tr height="30">
					<td align="right">顺序:&nbsp;</td>
					<td><input id="orderNumber" name="objectExpandField.orderNumber" value="${objectExpandField.orderNumber}" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span>
				</tr>
				<tr height="30">
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="objectExpandField.memo" value="${objectExpandField.memo}" /></td>
				</tr>
			</table>
		</div>
	</div>
</form>
