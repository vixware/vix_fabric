<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#encodingRulesTableInTheMiddleForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="encodingRulesTableInTheMiddleForm">
		<s:hidden id="encodingRulesTableInTheMiddleId" name="encodingRulesTableInTheMiddleId" value="%{encodingRulesTableInTheMiddle.id}" theme="simple" />
		<input type="hidden" id="billType" name="billType" value="${encodingRulesTableInTheMiddle.billType}" /> <input type="hidden" id="level1type" name="level1type" value="${encodingRulesTableInTheMiddle.level1type}" /> <input type="hidden" id="level1value" name="level1value" value="${encodingRulesTableInTheMiddle.level1value}" /> <input
			type="hidden" id="level2type" name="level2type" value="${encodingRulesTableInTheMiddle.level2type}" /> <input type="hidden" id="level2value" name="level2value" value="${encodingRulesTableInTheMiddle.level2value}" /> <input type="hidden" id="level3type" name="level3type" value="${encodingRulesTableInTheMiddle.level3type}" /> <input
			type="hidden" id="level3value" name="level3value" value="${encodingRulesTableInTheMiddle.level3value}" /> <input type="hidden" id="level4type" name="level4type" value="${encodingRulesTableInTheMiddle.level4type}" /> <input type="hidden" id="level4value" name="level4value" value="${encodingRulesTableInTheMiddle.level4value}" /> <input
			type="hidden" id="level5type" name="level5type" value="${encodingRulesTableInTheMiddle.level5type}" /> <input type="hidden" id="level5value" name="level5value" value="${encodingRulesTableInTheMiddle.level5value}" /> <input type="hidden" id="level6type" name="level6type" value="${encodingRulesTableInTheMiddle.level6type}" /> <input
			type="hidden" id="level6value" name="level6value" value="${encodingRulesTableInTheMiddle.level6value}" /> <input type="hidden" id="level7type" name="level7type" value="${encodingRulesTableInTheMiddle.level7type}" /> <input type="hidden" id="level7value" name="level7value" value="${encodingRulesTableInTheMiddle.level7value}" /> <input
			type="hidden" id="level8type" name="level8type" value="${encodingRulesTableInTheMiddle.level8type}" /> <input type="hidden" id="level8value" name="level8value" value="${encodingRulesTableInTheMiddle.level8value}" /> <input type="hidden" id="level9type" name="level9type" value="${encodingRulesTableInTheMiddle.level9type}" /> <input
			type="hidden" id="level9value" name="level9value" value="${encodingRulesTableInTheMiddle.level9value}" /> <input type="hidden" id="level10type" name="level10type" value="${encodingRulesTableInTheMiddle.level10type}" /> <input type="hidden" id="level10value" name="level10value" value="${encodingRulesTableInTheMiddle.level10value}" /> <input
			type="hidden" id="level11type" name="level11type" value="${encodingRulesTableInTheMiddle.level11type}" /> <input type="hidden" id="level11value" name="level11value" value="${encodingRulesTableInTheMiddle.level11value}" /> <input type="hidden" id="level12type" name="level12type" value="${encodingRulesTableInTheMiddle.level12type}" /> <input
			type="hidden" id="level12value" name="level12value" value="${encodingRulesTableInTheMiddle.level12value}" /> <input type="hidden" id="timeType" name="timeType" value="${encodingRulesTableInTheMiddle.timeType}" /> <input type="hidden" id="timeFormat" name="timeFormat" value="${encodingRulesTableInTheMiddle.timeFormat}" /> <input type="hidden"
			id="serialNumberBegin" name="serialNumberBegin" value="${encodingRulesTableInTheMiddle.serialNumberBegin}" /> <input type="hidden" id="serialNumberEnd" name="serialNumberEnd" value="${encodingRulesTableInTheMiddle.serialNumberEnd}" /> <input type="hidden" id="serialNumberStep" name="serialNumberStep"
			value="${encodingRulesTableInTheMiddle.serialNumberStep}" /> <input type="hidden" id="isOpenTime" name="isOpenTime" value="${encodingRulesTableInTheMiddle.isOpenTime}" /> <input type="hidden" id="codeType" name="codeType" value="${encodingRulesTableInTheMiddle.codeType}" /> <input type="hidden" id="islevel1" name="islevel1"
			value="${encodingRulesTableInTheMiddle.islevel1}" /> <input type="hidden" id="islevel2" name="islevel2" value="${encodingRulesTableInTheMiddle.islevel2}" /> <input type="hidden" id="islevel3" name="islevel3" value="${encodingRulesTableInTheMiddle.islevel3}" /> <input type="hidden" id="islevel4" name="islevel4"
			value="${encodingRulesTableInTheMiddle.islevel4}" /> <input type="hidden" id="islevel5" name="islevel5" value="${encodingRulesTableInTheMiddle.islevel5}" /> <input type="hidden" id="islevel6" name="islevel6" value="${encodingRulesTableInTheMiddle.islevel6}" /> <input type="hidden" id="islevel7" name="islevel7"
			value="${encodingRulesTableInTheMiddle.islevel7}" /> <input type="hidden" id="islevel8" name="islevel8" value="${encodingRulesTableInTheMiddle.islevel8}" /> <input type="hidden" id="islevel9" name="islevel9" value="${encodingRulesTableInTheMiddle.islevel9}" /> <input type="hidden" id="islevel10" name="islevel10"
			value="${encodingRulesTableInTheMiddle.islevel10}" /> <input type="hidden" id="islevel11" name="islevel11" value="${encodingRulesTableInTheMiddle.islevel11}" /> <input type="hidden" id="islevel12" name="islevel12" value="${encodingRulesTableInTheMiddle.islevel12}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="35">
					<td align="right">单据类型:</td>
					<td><input type="text" id="billType" name="billType" value="${encodingRulesTableInTheMiddle.billType}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="35">
					<td align="right">编码长度:</td>
					<td><input type="text" id="codeLength" name="codeLength" class="validate[required] text-input" value="${encodingRulesTableInTheMiddle.codeLength}" /><span style="color: red;">*</span></td>
					<td align="right">启用级数:</td>
					<td><input type="text" id="enableSeries" name="enableSeries" class="validate[required] text-input" value="${encodingRulesTableInTheMiddle.enableSeries}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="35">
					<td align="right">一级长度:</td>
					<td><input type="text" id="level1Length" name="level1Length" value="${encodingRulesTableInTheMiddle.level1Length}" /></td>
					<td align="right">二级长度:</td>
					<td><input type="text" id="level2Length" name="level2Length" value="${encodingRulesTableInTheMiddle.level2Length}" /></td>
				</tr>
				<tr height="35">
					<td align="right">三级长度:</td>
					<td><input type="text" id="level3Length" name="level3Length" value="${encodingRulesTableInTheMiddle.level3Length}" /></td>
					<td align="right">四级长度:</td>
					<td><input type="text" id="level4Length" name="level4Length" value="${encodingRulesTableInTheMiddle.level4Length}" /></td>
				</tr>
				<tr height="35">
					<td align="right">五级长度:</td>
					<td><input type="text" id="level5Length" name="level5Length" value="${encodingRulesTableInTheMiddle.level5Length}" /></td>
					<td align="right">六级长度:</td>
					<td><input type="text" id="level6Length" name="level6Length" value="${encodingRulesTableInTheMiddle.level6Length}" /></td>
				</tr>
				<tr height="35">
					<td align="right">七级长度:</td>
					<td><input type="text" id="level7Length" name="level7Length" value="${encodingRulesTableInTheMiddle.level7Length}" /></td>
					<td align="right">八级长度:</td>
					<td><input type="text" id="level8Length" name="level8Length" value="${encodingRulesTableInTheMiddle.level8Length}" /></td>
				</tr>
				<tr height="35">
					<td align="right">九级长度:</td>
					<td><input type="text" id="level9Length" name="level9Length" value="${encodingRulesTableInTheMiddle.level9Length}" /></td>
					<td align="right">十级长度:</td>
					<td><input type="text" id="level10Length" name="level10Length" value="${encodingRulesTableInTheMiddle.level10Length}" /></td>
				</tr>
				<tr height="35">
					<td align="right">十一级长度:</td>
					<td><input type="text" id="level11Length" name="level11Length" value="${encodingRulesTableInTheMiddle.level11Length}" /></td>
					<td align="right">十二级长度:</td>
					<td><input type="text" id="level12Length" name="level12Length" value="${encodingRulesTableInTheMiddle.level12Length}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>