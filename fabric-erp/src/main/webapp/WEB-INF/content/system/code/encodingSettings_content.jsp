<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function checkedradio() {
		if (document.getElementById("codeType").value == "A") {
			$("#codetypeA").attr("checked", true);
		} else if (document.getElementById("codeType").value == "B") {
			$("#codetypeB").attr("checked", true);
		} else if (document.getElementById("codeType").value == "C") {
			$("#codetypeC").attr("checked", true);
		}
		if (document.getElementById("l1").value == "1") {
			$("#islevel1").attr("checked", true);
		}
		if (document.getElementById("l2").value == "1") {
			$("#islevel2").attr("checked", true);
		}
		if (document.getElementById("l3").value == "1") {
			$("#islevel3").attr("checked", true);
		}
		if (document.getElementById("l4").value == "1") {
			$("#islevel4").attr("checked", true);
		}
		if (document.getElementById("l5").value == "1") {
			$("#islevel5").attr("checked", true);
		}
		if (document.getElementById("l6").value == "1") {
			$("#islevel6").attr("checked", true);
		}
		if (document.getElementById("l7").value == "1") {
			$("#islevel7").attr("checked", true);
		}
		if (document.getElementById("l8").value == "1") {
			$("#islevel8").attr("checked", true);
		}
		if (document.getElementById("l9").value == "1") {
			$("#islevel9").attr("checked", true);
		}
		if (document.getElementById("l10").value == "1") {
			$("#islevel10").attr("checked", true);
		}
		if (document.getElementById("l11").value == "1") {
			$("#islevel11").attr("checked", true);
		}
		if (document.getElementById("l12").value == "1") {
			$("#islevel12").attr("checked", true);
		}
	}

	$(function() {
		$("#level1value").val('${encodingRulesTableInTheMiddle.level1value}');
		$("#level2value").val('${encodingRulesTableInTheMiddle.level2value}');
		$("#level3value").val('${encodingRulesTableInTheMiddle.level3value}');
		$("#level4value").val('${encodingRulesTableInTheMiddle.level4value}');
		$("#level5value").val('${encodingRulesTableInTheMiddle.level5value}');
		$("#level6value").val('${encodingRulesTableInTheMiddle.level6value}');
		$("#level7value").val('${encodingRulesTableInTheMiddle.level7value}');
		$("#level8value").val('${encodingRulesTableInTheMiddle.level8value}');
		$("#level9value").val('${encodingRulesTableInTheMiddle.level9value}');
		$("#level10value").val('${encodingRulesTableInTheMiddle.level10value}');
		$("#level11value").val('${encodingRulesTableInTheMiddle.level11value}');
		$("#level12value").val('${encodingRulesTableInTheMiddle.level12value}');
	});
</script>

<style>
.test {
	padding: 10px;
	border-bottom: 1px solid #ccc;
	line-height: 30px;
}

.nt1 {
	padding: 20px 10px 10px 10px;
	border: #CCC solid 1px;
	line-height: 20px;
	margin: 10px 0 30px;
}

.nt_title1 {
	float: left;
	font-size: 14px;
	font-weight: bold;
	display: inline;
	margin: -30px 0 0;
	background: #FAFAFA;
	padding: 0 5px;
}

.nt_line1 table {
	table-layout: fixed;
}

.nt_line1 table td {
	padding: 5px 5px 5px;
	vertical-align: middle;
	line-height: 24px;
}

.nt_line1 table td input, .nt_line table td select {
	vertical-align: middle;
	margin-right: 5px;
}

.nt_line1 table td.tr {
	padding-right: 5px;
	text-align: right;
}
</style>
<input type="hidden" id="id" name="id" value="${encodingRulesTableInTheMiddle.id}" />
<input type="hidden" id="l1" name="l1" value="${encodingRulesTableInTheMiddle.islevel1}" />
<input type="hidden" id="l2" name="l2" value="${encodingRulesTableInTheMiddle.islevel2}" />
<input type="hidden" id="l3" name="l3" value="${encodingRulesTableInTheMiddle.islevel3}" />
<input type="hidden" id="l4" name="l4" value="${encodingRulesTableInTheMiddle.islevel4}" />
<input type="hidden" id="l5" name="l5" value="${encodingRulesTableInTheMiddle.islevel5}" />
<input type="hidden" id="l6" name="l6" value="${encodingRulesTableInTheMiddle.islevel6}" />
<input type="hidden" id="l7" name="l7" value="${encodingRulesTableInTheMiddle.islevel7}" />
<input type="hidden" id="l8" name="l8" value="${encodingRulesTableInTheMiddle.islevel8}" />
<input type="hidden" id="l9" name="l9" value="${encodingRulesTableInTheMiddle.islevel9}" />
<input type="hidden" id="l10" name="l10" value="${encodingRulesTableInTheMiddle.islevel10}" />
<input type="hidden" id="l11" name="l11" value="${encodingRulesTableInTheMiddle.islevel11}" />
<input type="hidden" id="l12" name="l12" value="${encodingRulesTableInTheMiddle.islevel12}" />
<input type="hidden" id="codeType" name="codeType" value="${encodingRulesTableInTheMiddle.codeType}" />
<input type="hidden" id="billType" name="billType" value="${encodingRulesTableInTheMiddle.billType}" />
<input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
<input type="hidden" id="billTypeCode" name="billTypeCode" value="${billTypeCode}" />
<input type="hidden" id="billTypeId" name="billTypeId" value="${billTypeId}" />
<div class="npcontent clearfix">
	<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
		<div class="np_left_title">
			<h2>编码设置</h2>
		</div>
		<div class="np_clist">
			<div class="nt1">
				<div class="nt_title1">
					<label><input type="radio" name="codetype" id="codetypeA" value="A" />&nbsp;&nbsp;手工录入</label>
				</div>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户手动输入单据编码</label>
			</div>
			<div class="nt1">
				<div class="nt_title1">
					<label><input type="radio" name="codetype" id="codetypeB" value="B" />&nbsp;&nbsp;系统生成</label>
				</div>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统随机生成单据编码</label>
			</div>
			<div class="nt1">
				<div class="nt_title1">
					<label><input type="radio" name="codetype" id="codetypeC" value="C" />&nbsp;&nbsp;编码规则</label>
				</div>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据用户设置的编码规则生成相应的单据编码</label>
				<div class="nt_line1">
					<div class="test">
						<table>
							<tr>
								<td><label><input type="checkbox" name="islevel1" id="islevel1" value="1" /></label>一&nbsp;&nbsp;&nbsp;级:</td>
								<td style="padding-right: 30px;"><label><select id="level1value" name="level1value" style="width: 150px; height: 25px;">
											<option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select> </label></td>
								<td><label><input type="checkbox" name="islevel2" id="islevel2" value="1" /></label>二&nbsp;&nbsp;&nbsp;级:</td>
								<td><label><select id="level2value" name="level2value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
							<tr>
								<td><label><input type="checkbox" name="islevel3" id="islevel3" value="1" /></label>三&nbsp;&nbsp;&nbsp;级:</td>
								<td style="padding-right: 30px;"><label><select id="level3value" name="level3value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
								<td><label><input type="checkbox" name="islevel4" id="islevel4" value="1" /></label>四&nbsp;&nbsp;&nbsp;级:</td>
								<td><label><select id="level4value" name="level4value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
							<tr>
								<td><label><input type="checkbox" name="islevel5" id="islevel5" value="1" /></label>五&nbsp;&nbsp;&nbsp;级:</td>
								<td style="padding-right: 30px;"><label><select id="level5value" name="level5value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
								<td><label><input type="checkbox" name="islevel6" id="islevel6" value="1" /></label>六&nbsp;&nbsp;&nbsp;级:</td>
								<td><label><select id="level6value" name="level6value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
							<tr>
								<td><label><input type="checkbox" name="islevel7" id="islevel7" value="1" /></label>七&nbsp;&nbsp;&nbsp;级:</td>
								<td style="padding-right: 30px;"><label><select id="level7value" name="level7value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
								<td><label><input type="checkbox" name="islevel8" id="islevel8" value="1" /></label>八&nbsp;&nbsp;&nbsp;级:</td>
								<td><label><select id="level8value" name="level8value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
							<tr>
								<td><label><input type="checkbox" name="islevel9" id="islevel9" value="1" /></label>九&nbsp;&nbsp;&nbsp;级:</td>
								<td style="padding-right: 30px;"><label><select id="level9value" name="level9value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
								<td><label><input type="checkbox" name="islevel10" id="islevel10" value="1" /></label>十&nbsp;&nbsp;&nbsp;级:</td>
								<td><label><select id="level10value" name="level10value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
							<tr>
								<td><label><input type="checkbox" name="islevel11" id="islevel11" value="1" /></label>十一级:</td>
								<td style="padding-right: 30px;"><label><select id="level11value" name="level11value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
								<td><label><input type="checkbox" name="islevel12" id="islevel12" value="1" /></label>十二级:</td>
								<td><label><select id="level12value" name="level12value" style="width: 150px; height: 25px;"><option value="">请选择</option>
											<option value="${billTypeCode}">单据类型</option>
											<option value="YYYYMMDD">年月日</option>
											<option value="YYYYMM">年月</option>
											<option value="YYYY">年</option>
									</select></label></td>
							</tr>
						</table>
					</div>
					<table>
						<tr>
							<td align="right">编&nbsp;码&nbsp;长&nbsp;度:</td>
							<td style="padding-right: 30px;"><input type="text" name="codeLength" id="codeLength" class="ipt" value="${encodingRulesTableInTheMiddle.codeLength }" /><span style="color: red;">*</span></td>
							<td align="right">起始:</td>
							<td style="padding-right: 30px;"><input type="text" name="serialNumberBegin" id="serialNumberBegin" class="ipt" value="${encodingRulesTableInTheMiddle.serialNumberBegin }" /><span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td align="right">序号最大值:</td>
							<td><input type="text" name="serialNumberEnd" id="serialNumberEnd" class="ipt" value="${encodingRulesTableInTheMiddle.serialNumberEnd }" /><span style="color: red;">*</span></td>
							<td align="right">步长:</td>
							<td><input type="text" name="serialNumberStep" id="serialNumberStep" class="ipt" value="${encodingRulesTableInTheMiddle.serialNumberStep }" /><span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td align="right">编&nbsp;码&nbsp;预&nbsp;览:</td>
							<td align="left" colspan="3"><label><input type="text" class="ipt w88per underline" name="codingPreview" value="${encodingRulesTableInTheMiddle.codingPreview }" style="width: 400px;" /></label></td>
						</tr>
					</table>
				</div>
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
				<tr>
					<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate($('#selectId').val());" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="取消" class="btn" /></td>
				</tr>
			</table>
		</div>
	</div>