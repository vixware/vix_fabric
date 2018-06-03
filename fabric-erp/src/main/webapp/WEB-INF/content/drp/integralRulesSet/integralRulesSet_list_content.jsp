<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="id" name="id" value="${integralRules.id}" />
<input type="hidden" id="storeId" name="storeId" value="${integralRules.channelDistributor.id}" />
<input type="hidden" id="presenttype" name="presenttype" value="${integralRules.presentType}" />
<input type="hidden" id="iszc" name="iszc" value="${integralRules.isZc}" />
<input type="hidden" id="isxszc" name="isxszc" value="${integralRules.isXszc}" />
<input type="hidden" id="isjfyxq" name="isjfyxq" value="${integralRules.isJfyxq}" />
<input type="hidden" id="yxtype" name="yxtype" value="${integralRules.yxType}" />
<script type="text/javascript">
	function checkedradio() {
		if (document.getElementById("presenttype").value == "1") {
			$("#presentTypeA").attr("checked", true);
		} else if (document.getElementById("presenttype").value == "2") {
			$("#presentTypeB").attr("checked", true);
		}
		if (document.getElementById("iszc").value == "1") {
			$("#isZc").attr("checked", true);
		}
		if (document.getElementById("isxszc").value == "1") {
			$("#isXszc").attr("checked", true);
		}
		if (document.getElementById("isjfyxq").value == "1") {
			$("#isJfyxq").attr("checked", true);
		}
		if (document.getElementById("yxtype").value == "1") {
			$("#yxTypeA").attr("checked", true);
		} else if (document.getElementById("yxtype").value == "2") {
			$("#yxTypeB").attr("checked", true);
		}
	}
</script>
<!-- left -->
<div class="npcontent clearfix">
	<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
		<div id="lnp1">
			<ul class="np_tab clearfix">
				<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">积分规则设置</a></li>
			</ul>
			<div class="np_clist" id="np1">
				<div class="nt_line">
					<div class="nt">
						<div class="nt_title">
							<label><input type="checkbox" name="isZc" id="isZc" value="1" /> </label><label>注册送积分</label>
						</div>
						<div class="nt_line">
							<table>
								<tr>
									<td>积分值:</td>
									<td><input type="text" class="ipt" name="presentZc" id="presentZc" value="${integralRules.presentZc }" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">
							<label><input type=radio name="presentType" id="presentTypeA" value="1" /> </label><label>根据消费额按照比率进行积分</label>
						</div>
						<div class="nt_line">
							<table>
								<tr>
									<td>积分系数:</td>
									<td><input type="text" class="ipt" size="20" name="conversiorate" id="conversiorate" value="${integralRules.conversiorate }" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">
							<label><input type="checkbox" name="isXszc" id="isXszc" value="1" /> </label><label>限时积分赠送</label>
						</div>
						<div class="nt_line">
							<table>
								<tr>
									<td>积分系数:</td>
									<td><input type="text" class="ipt" name="xsConversiorate" id="xsConversiorate" value="${integralRules.xsConversiorate }" /></td>
								</tr>
								<tr>
									<td align="right">开始日期：</td>
									<td><input class="ipt" id="xsStartDate" name="xsStartDate" value="<s:date format="yyyy-MM-dd" name="%{integralRules.xsStartDate}" />" type="text" readonly="readonly" /><img onclick="showTime('xsStartDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									<td align="right">结束日期：</td>
									<td><input class="ipt" id="xsEndDate" name="xsEndDate" value="<s:date format="yyyy-MM-dd" name="%{integralRules.xsEndDate}" />" type="text" readonly="readonly" /><img onclick="showTime('xsEndDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">
							<label><input type="checkbox" name="isJfyxq" id="isJfyxq" value="1" /> </label><label>积分清零设置</label>
						</div>
						<div class="nt_line">
							<table>
								<tr>
									<td align="right"><input type="radio" name="yxType" id="yxTypeA" value="1" />月末清零</td>
									<td align="right"><input type="radio" name="yxType" id="yxTypeB" value="2" />年末清零</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
					<tr>
						<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="取消" class="btn" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>