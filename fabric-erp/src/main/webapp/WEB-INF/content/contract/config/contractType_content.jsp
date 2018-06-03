<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function checkedradio (){
		if (document.getElementById ("codeType").value == "A"){
			$ ("#codetypeA").attr ("checked" , true);
		}else if (document.getElementById ("codeType").value == "B"){
			$ ("#codetypeB").attr ("checked" , true);
		}else if (document.getElementById ("codeType").value == "C"){
			$ ("#codetypeC").attr ("checked" , true);
		}
		if (document.getElementById ("isTime").value == "1"){
			$ ("#isOpenTime").attr ("checked" , true);
		}
		if (document.getElementById ("l1").value == "1"){
			$ ("#islevel1").attr ("checked" , true);
		}
		if (document.getElementById ("l2").value == "1"){
			$ ("#islevel2").attr ("checked" , true);
		}
		if (document.getElementById ("l3").value == "1"){
			$ ("#islevel3").attr ("checked" , true);
		}
		if (document.getElementById ("l4").value == "1"){
			$ ("#islevel4").attr ("checked" , true);
		}
		if (document.getElementById ("l5").value == "1"){
			$ ("#islevel5").attr ("checked" , true);
		}
		if (document.getElementById ("l6").value == "1"){
			$ ("#islevel6").attr ("checked" , true);
		}
		if (document.getElementById ("l7").value == "1"){
			$ ("#islevel7").attr ("checked" , true);
		}
		if (document.getElementById ("l8").value == "1"){
			$ ("#islevel8").attr ("checked" , true);
		}
		if (document.getElementById ("l9").value == "1"){
			$ ("#islevel9").attr ("checked" , true);
		}
		if (document.getElementById ("l10").value == "1"){
			$ ("#islevel10").attr ("checked" , true);
		}
		if (document.getElementById ("l11").value == "1"){
			$ ("#islevel11").attr ("checked" , true);
		}
		if (document.getElementById ("l12").value == "1"){
			$ ("#islevel12").attr ("checked" , true);
		}
	}
</script>
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
<input type="hidden" id="isTime" name="isTime" value="${encodingRulesTableInTheMiddle.isOpenTime}" />
<input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
<!-- left -->
<div class="npcontent clearfix">
	<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
		<div id="lnp1">
			<ul class="np_tab clearfix">
				<li class="current"><a href="javascript:;" onclick="javascript:tab(3,1,'np',event)">编码设置</a></li>
			</ul>
			<div class="np_clist" id="np1">
				<input type="hidden" id="id" name="id" value="${encodingRulesTableInTheMiddle.id}" /> <input type="hidden" id="codeLength" name="codeLength" value="${encodingRulesTableInTheMiddle.codeLength}" /> <input type="hidden" id="enableSeries" name="enableSeries" value="${encodingRulesTableInTheMiddle.enableSeries}" /> <input type="hidden"
					id="level1Length" name="level1Length" value="${encodingRulesTableInTheMiddle.level1Length}" /> <input type="hidden" id="level2Length" name="level2Length" value="${encodingRulesTableInTheMiddle.level2Length}" /> <input type="hidden" id="level3Length" name="level3Length" value="${encodingRulesTableInTheMiddle.level3Length}" /> <input
					type="hidden" id="level4Length" name="level4Length" value="${encodingRulesTableInTheMiddle.level4Length}" /> <input type="hidden" id="level5Length" name="level5Length" value="${encodingRulesTableInTheMiddle.level5Length}" /> <input type="hidden" id="level6Length" name="level6Length" value="${encodingRulesTableInTheMiddle.level6Length}" /> <input
					type="hidden" id="level7Length" name="level7Length" value="${encodingRulesTableInTheMiddle.level7Length}" /> <input type="hidden" id="level8Length" name="level8Length" value="${encodingRulesTableInTheMiddle.level8Length}" /> <input type="hidden" id="level9Length" name="level9Length" value="${encodingRulesTableInTheMiddle.level9Length}" /> <input
					type="hidden" id="level10Length" name="level10Length" value="${encodingRulesTableInTheMiddle.level10Length}" /> <input type="hidden" id="level11Length" name="level11Length" value="${encodingRulesTableInTheMiddle.level11Length}" /> <input type="hidden" id="level12Length" name="level12Length"
					value="${encodingRulesTableInTheMiddle.level12Length}" />
				<div class="nt">
					<div class="nt_line">
						<label><input type="radio" name="codetype" id="codetypeA" value="A" /> </label><label>手工录入――用户手动输入单据编码</label>
					</div>
				</div>
				<div class="nt">
					<div class="nt_line">
						<label><input type="radio" name="codetype" id="codetypeB" value="B" /> </label><label>系统生成――系统随机生成单据编码</label>
					</div>
				</div>
				<div class="nt">
					<div class="nt_line">
						<label><input type="radio" name="codetype" id="codetypeC" value="C" /> </label><label>编码规则――根据用户设置的编码规则生成相应的单据编码</label>
						<div class="nt">
							<div class="nt_line">
								<table>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel1" id="islevel1" value="1" /></label>一级</td>
										<td><select name="level1type" id="level1type">
												<option value="">单据类型</option>
												<option value="销售类型">销售类型</option>
												<option value="采购类型">采购类型</option>
										</select></td>
										<td><label><input type="text" class="ipt" name="level1value" id="level1value" value="${encodingRulesTableInTheMiddle.billsType.typeCode }" /></label></td>
										<td><label><input type="checkbox" name="islevel2" id="islevel2" value="1" /></label>二级</td>
										<td><select name="level2type" id="level2type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level2value" id="level2value" value="${encodingRulesTableInTheMiddle.level2value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel3" id="islevel3" value="1" /></label>三级</td>
										<td><select name="level3type" id="level3type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level3value" id="level3value" value="${encodingRulesTableInTheMiddle.level3value }" size="20" /></label></td>
										<td><label><input type="checkbox" name="islevel4" id="islevel4" value="1" /></label>四级</td>
										<td><select name="level4type" id="level4type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level4value" id="level4value" value="${encodingRulesTableInTheMiddle.level4value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel5" id="islevel5" value="1" /></label>五级</td>
										<td><select name="level5type" id="level5type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level5value" id="level5value" value="${encodingRulesTableInTheMiddle.level5value }" size="20" /></label></td>
										<td><label><input type="checkbox" name="islevel6" id="islevel6" value="1" /></label>六级</td>
										<td><select name="level6type" id="level6type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level6value" id="level6value" value="${encodingRulesTableInTheMiddle.level6value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel7" id="islevel7" value="1" /></label>七级</td>
										<td><select name="level7type" id="level7type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level7value" id="level7value" value="${encodingRulesTableInTheMiddle.level7value }" size="20" /></label></td>
										<td><label><input type="checkbox" name="islevel8" id="islevel8" value="1" /></label>八级</td>
										<td><select name="level8type" id="level8type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level8value" id="level8value" value="${encodingRulesTableInTheMiddle.level8value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel9" id="islevel9" value="1" /></label>九级</td>
										<td><select name="level9type" id="level9type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level9value" id="level9value" value="${encodingRulesTableInTheMiddle.level9value }" size="20" /></label></td>
										<td><label><input type="checkbox" name="islevel10" id="islevel10" value="1" /></label>十级</td>
										<td><select name="level10type" id="level10type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level10value" id="level10value" value="${encodingRulesTableInTheMiddle.level10value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name="islevel11" id="islevel11" value="1" /></label>十一级</td>
										<td><select name="level11type" id="level11type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level11value" id="level11value" value="${encodingRulesTableInTheMiddle.level11value }" size="20" /></label></td>
										<td><label><input type="checkbox" name="islevel12" id="islevel12" value="1" /></label>十二级</td>
										<td><select name="level12type" id="level12type"><option value="0">请选择</option></select></td>
										<td><label><input type="text" class="ipt" name="level12value" id="level12value" value="${encodingRulesTableInTheMiddle.level12value }" size="20" /></label></td>
									</tr>
									<tr class="alt">
										<td><label><input type="checkbox" name=isOpenTime id="isOpenTime" value="1" /></label><label>时间</label></td>
										<td><select name="timeType" id="timeType"><option value="单据日期">单据日期</option></select></td>
										<td><select name="timeFormat" id="timeFormat"><option value="yyyymmdd">yyyymmdd</option>
												<option value="mmdd">mmdd</option></select></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">
								<label>序列号</label>
							</div>
							<div class="nt_line">
								<table>
									<tr class="alt">
										<td>起始</td>
										<td><input type="text" class="ipt" size="10" name="serialNumberBegin" id="serialNumberBegin" value="${encodingRulesTableInTheMiddle.serialNumberBegin }" /></td>
										<td>结束</td>
										<td><input type="text" class="ipt" size="10" name="serialNumberEnd" id="serialNumberEnd" value="${encodingRulesTableInTheMiddle.serialNumberEnd }" /></td>
										<td>步长</td>
										<td><input type="text" class="ipt" size="10" name="serialNumberStep" id="serialNumberStep" value="${encodingRulesTableInTheMiddle.serialNumberStep }" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="nt">
					<div class="nt_title">
						<label>编号预览</label>
					</div>
					<div class="nt_line">
						<table>
							<tr class="alt">
								<td>单据编号&nbsp;&nbsp;=</td>
								<td><input type="text" class="ipt" size="10" name="level1value" value="${encodingRulesTableInTheMiddle.level1value }" />+<input type="text" class="ipt" size="10" name="level2value" value="${encodingRulesTableInTheMiddle.level2value }" />+<input type="text" class="ipt" size="10" name="level3value"
									value="${encodingRulesTableInTheMiddle.level3value }" />+<input type="text" class="ipt" size="10" name="level4value" value="${encodingRulesTableInTheMiddle.level4value }" /></td>
							</tr>
							<tr class="alt">
								<td>效果</td>
								<td><label><input type="text" class="ipt" size="50" name="codingPreview" value="${encodingRulesTableInTheMiddle.level1value }${encodingRulesTableInTheMiddle.level2value }${encodingRulesTableInTheMiddle.level3value }${encodingRulesTableInTheMiddle.serialNumberEnd }" /></label></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
			<tr>
				<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate($('#selectId').val());" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="取消" class="btn" /></td>
			</tr>
		</table>
	</div>
</div>