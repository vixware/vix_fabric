<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
</script>
<s:if test="objectExpandFieldList.size > 0">
	<div class="voucher newvoucher">
		<dl>
			<dt>
				<b></b> <strong>扩展信息</strong>
			</dt>
			<dd style="display: block;">
				<input id="append_ID" name="append_ID" value="<s:property value="loadObjectExpandFieldValue('ID')"/>" type="hidden" />
				<table style="border: none;">
					<s:iterator var="ef" value="objectExpandFieldList" status="s">
						<s:if test="#s.count%2 == 1 ">
							<tr height="30">
						</s:if>
						<td align="right">${ef.displayName}:&nbsp;</td>
						<td><s:if test="#ef.fieldType == 'date'">
								<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="<s:property value="loadObjectExpandFieldValue(#ef.fieldName)"/>" type="text" size="30" />
								<img onclick="showTime('append_${ef.fieldName}','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
							</s:if> <s:if test="#ef.fieldType == 'select'">
								<select id="append_${ef.fieldName}" name="${ef.fieldName}">
									<option value="0">----请选择----</option>
									<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
										<s:if test="loadObjectExpandFieldSelectValue(#ef.fieldName) == #ev.value">
											<option value="${ev.value}" selected="selected">${ev.displayName}</option>
										</s:if>
										<s:else>
											<option value="${ev.value}">${ev.displayName}</option>
										</s:else>
									</s:iterator>
								</select>
							</s:if> <s:if test="#ef.fieldType == 'checkbox'">
								<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
									<s:if test="loadObjectExpandFieldCheckboxValue(#ef.fieldName+','+#ev.value)">
										<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="checkbox" checked="checked" />${ev.displayName}
									</s:if>
									<s:else>
										<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="checkbox" />${ev.displayName}
									</s:else>
								</s:iterator>
							</s:if> <s:if test="#ef.fieldType == 'radio'">
								<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
									<s:if test="loadObjectExpandFieldRadioValue(#ef.fieldName+','+#ev.value)">
										<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="radio" checked="checked" />${ev.displayName}
									</s:if>
									<s:else>
										<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="radio" />${ev.displayName}
									</s:else>
								</s:iterator>
							</s:if> <s:if test="#ef.fieldType == 'text' || #ef.fieldType == 'number'">
								<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="<s:property value="loadObjectExpandFieldValue(#ef.fieldName)"/>" type="text" />
							</s:if></td>
						<s:if test="#s.count%2 == 0 ">
							</tr>
						</s:if>
					</s:iterator>
				</table>
			</dd>
		</dl>
	</div>
</s:if>
