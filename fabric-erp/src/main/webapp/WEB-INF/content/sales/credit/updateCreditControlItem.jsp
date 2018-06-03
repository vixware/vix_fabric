<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
$("#creditControlItemForm").validationEngine();
function controlTypeChange(){
	//$.ajax({
	//	  url:"${vix}/sales/credit/creditDictionaryAction/loadValue.action?id="+$("#id").val()+"&type="+$("#controlTypeId").val(),
	//	  cache: false,
	//	  success: function(html){
	//		  $("#value").html(html);
	//	  }
	//});
}
//-->
</script>
<input type="hidden" id="cciId" name="cciId" value="${creditControlItem.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="creditControlItemForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td width="15%" align="right">控制主体:</td>
					<td width="35%"><select id="ccSubject" class="validate[required]">
							<option value="">------请选择------</option>
							<c:forEach items="${controlSubjectMap}" var="entity">
								<option value="${entity.key}" <c:if test="${creditControlItem.ccSubject == entity.key}">selected="selected"</c:if>>${entity.value}</option>
							</c:forEach>
					</select></td>
					<td width="10%" align="right">控制类型:</td>
					<td width="40%"><select id="ccType" onchange="controlTypeChange();" class="validate[required]">
							<option value="">------请选择------</option>
							<c:forEach items="${controlTypeMap}" var="entity">
								<option value="${entity.key}" <c:if test="${creditControlItem.ccType == entity.key}">selected="selected"</c:if>>${entity.value}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr height="30">
					<td align="right">风险等级:</td>
					<td><input id="creditLevel" name="creditControlItem.creditLevel" value="${creditControlItem.creditLevel}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">控制值:</td>
					<td><input id="creditValue" name="creditControlItem.creditValue" value="${creditControlItem.creditValue}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">控制方法:</td>
					<td><input id="creditMethod" name="creditControlItem.creditMethod" value="${creditControlItem.creditMethod}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">是否生效:</td>
					<td><s:if test="creditControlItem.isValid == 'N'">
							<input type="radio" id="isValid1" name="isValid" value="Y" />是
							<input type="radio" id="isValid2" name="isValid" value="N" checked="checked" />否
						</s:if> <s:elseif test="creditControlItem.isValid == 'Y'">
							<input type="radio" id="isValid1" name="isValid" value="Y" checked="checked" />是
							<input type="radio" id="isValid2" name="isValid" value="N" />否
						</s:elseif> <s:else>
							<input type="radio" id="isValid1" name="isValid" value="Y" />是
							<input type="radio" id="isValid2" name="isValid" value="N" checked="checked" />否
						</s:else></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
