<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateTvMedia() {
		if ($('#tvMediaForm').validationEngine('validate')) {
			$.post('${vix}/drp/tvMediaManagementAction!saveOrUpdate.action', {
			'tvMedia.id' : $("#id").val(),
			'tvMedia.tvProgram' : $("#tvProgram").val(),
			'tvMedia.channelName' : $("#channelName").val(),
			'tvMedia.percentageCoverage' : $("#percentageCoverage").val(),
			'tvMedia.columnName' : $("#columnName").val(),
			'tvMedia.ratingsSectionPercentage' : $("#ratingsSectionPercentage").val(),
			'tvMedia.targetAudienceRate' : $("#targetAudienceRate").val(),
			'tvMedia.advertisingTime' : $("#advertisingTime").val(),
			'tvMedia.advertisingPrice' : $("#advertisingPrice").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/tvMediaManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#tvMediaForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#">媒体活动</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/tvMediaManagementAction!goList.action');">电视媒体管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="tvMedia.id" value="${tvMedia.id}" />
<div class="content">
	<form id="tvMediaForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateTvMedia()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/tvMediaManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>电视栏目信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">电视栏目：</td>
											<td><input id="tvProgram" name="tvProgram" value="${tvMedia.tvProgram }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">频道名称：</td>
											<td><input id="channelName" name="channelName" value="${tvMedia.channelName }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">覆盖率百分比：</td>
											<td><input id="percentageCoverage" name="percentageCoverage" value="${tvMedia.percentageCoverage }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">栏目名称：</td>
											<td><input id="columnName" name="columnName" value="${tvMedia.columnName }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">栏目收视率百分比：</td>
											<td><input id="ratingsSectionPercentage" name="ratingsSectionPercentage" value="${tvMedia.ratingsSectionPercentage }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">目标受众率：</td>
											<td><input id="targetAudienceRate" name="targetAudienceRate" value="${tvMedia.targetAudienceRate }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">广告时间：</td>
											<td><input id="advertisingTime" name="advertisingTime" value="${tvMedia.advertisingTime}" type="text" class="validate[required] text-input" /><img onclick="showTime('advertisingTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<td align="right">广告价格：</td>
											<td><input id="advertisingPrice" name="advertisingPrice" value="${tvMedia.advertisingPrice }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>