<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
function loadRfmData(){
	loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action?year='+$("#rfmDate").val());
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
loadTopDynamicMenuContent('${vix}/crm/lead/saleLeadAction!goTopDynamicMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/saleLead.png" alt="" />供应链</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#">智能分析</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action');">RFM模型</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class=" drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<%-- <strong></strong>&nbsp;&nbsp;&nbsp;选择时间：
        	<input id="rfmDate" value="${year}" type="text" onfocus="showTime('rfmDate','yyyy')" onchange="loadRfmData();"/>
        	<img onclick="showTime('rfmDate','yyyy')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> --%>
			&nbsp;&nbsp;&nbsp;开始时间： <input id="rfmDateStart" value="" type="text" onfocus="showTime('rfmDateStart','yyyy-MM-dd')" /> <img onclick="showTime('rfmDateStart','yyyy')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> &nbsp;&nbsp;&nbsp;结束时间： <input id="rfmDateEnd" value="" type="text"
				onfocus="showTime('rfmDateEnd','yyyy-MM-dd')" /> <img onclick="showTime('rfmDateEnd','yyyy')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <label><input type="checkbox" value="" name="" style="">时间(R)</label> <label><input type="checkbox" value="" name="" style="">频率(F)</label> <label><input
				type="checkbox" value="" name="" style="">金额(M)</label>
			<!--  &nbsp;&nbsp;&nbsp;店铺选择
            <select><option val="">请选择</option></select> -->
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="table">
			<table class="list list_center">
				<tbody>
					<tr class="alt">
						<th></th>
						<th>F=1</th>
						<th>F=2</th>
						<th>F=3</th>
						<th>F=4</th>
						<th>F=5</th>
						<th>行合计</th>
					</tr>
					<s:iterator value="rfmList" var="rfmRowList" status="sr">
						<tr class="">
							<s:if test="#sr.count == 1">
								<th>0&lt;R≤30</th>
							</s:if>
							<s:elseif test="#sr.count == 2">
								<th>30&lt;R≤90</th>
							</s:elseif>
							<s:elseif test="#sr.count == 3">
								<th>90&lt;R≤180</th>
							</s:elseif>
							<s:elseif test="#sr.count == 4">
								<th>180&lt;R≤360</th>
							</s:elseif>
							<s:elseif test="#sr.count == 5">
								<th>R&gt;360</th>
							</s:elseif>
							<s:else>
								<th>列合计</th>
							</s:else>
							<s:iterator value="rfmRowList" var="rfm" status="s">
								<s:if test="#s.count == 1">
									<td <s:if test="#sr.count != 6">style="background:#f98971;"</s:if>><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</td>
								</s:if>
								<s:elseif test="#s.count == 2">
									<td <s:if test="#sr.count != 6">style="background:#fcbf7c;"</s:if>><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</td>
								</s:elseif>
								<s:elseif test="#s.count == 3">
									<td <s:if test="#sr.count != 6">style="background:#f4e884;"</s:if>><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</td>
								</s:elseif>
								<s:elseif test="#s.count == 4">
									<td <s:if test="#sr.count != 6">style="background:#a4d180;"</s:if>><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</td>
								</s:elseif>
								<s:elseif test="#s.count == 5">
									<td <s:if test="#sr.count != 6">style="background:#bed87d;"</s:if>><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</td>
								</s:elseif>
								<s:else>
									<th><strong>${rfm.customerAccountCount}人</strong><br>占比：${rfm.customerAccountPersentStr}%</th>
								</s:else>
							</s:iterator>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>