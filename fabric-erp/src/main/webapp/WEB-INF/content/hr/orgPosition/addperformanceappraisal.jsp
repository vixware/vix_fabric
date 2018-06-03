<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>



<script type="text/javascript">
$(function(){
	//设置选中
	$("#perScore").val('${performanceAppraisal.perScore}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="waForm">
		<s:hidden id="daId" name="performanceAppraisal.id" value="%{performanceAppraisal.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="perCode" name="performanceAppraisal.perCode" value="${performanceAppraisal.perCode}" type="text" /></td>
					<th align="right">指标名称:&nbsp;</th>
					<td><input id="perName" name="performanceAppraisal.perName" value="${performanceAppraisal.perName}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">指标分类:&nbsp;</th>
					<td><input id="perType" name="performanceAppraisal.perType" value="${performanceAppraisal.perType}" type="text" /></td>
					<th align="right">考核标准:&nbsp;</th>
					<td><input id="perContent" name="performanceAppraisal.perContent" value="${performanceAppraisal.perContent}" type="text" /></td>
				</tr>
				<%-- <tr height="40">
					<th align="right">考核开始日期:&nbsp;</th>
					<td><input id="perStartDatePer" name="perStartDatePer"
						value="<fmt:formatDate value='${performanceAppraisal.perStartDatePer}' type='both' pattern='yyyy-MM-dd' />"
						readonly="readonly" type="text" /> <img
						onclick="showTime('perStartDatePer','yyyy-MM-dd')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle">
					</td>
					<th align="right">考核结束日期:&nbsp;</th>
					<td><input id="perEndDatePer" name="perEndDatePer"
						value="<fmt:formatDate value='${performanceAppraisal.perEndDatePer}' type='both' pattern='yyyy-MM-dd' />"
						readonly="readonly" type="text" /> <img
						onclick="showTime('perEndDatePer','yyyy-MM-dd')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle">
					</td>
				</tr> --%>
				<tr height="40">
					<th align="right">考核周期:&nbsp;</th>
					<td><select id="perScore" name="perScore" style="width: 50">
							<option value="">请选择</option>
							<option value="1">年</option>
							<option value="2">季</option>
							<option value="3">月</option>
					</select></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="performanceAppraisal.remarks" value="${performanceAppraisal.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#waForm").validationEngine();
</script>