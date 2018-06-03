<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateNewMedia() {
		if ($('#newMediaForm').validationEngine('validate')) {
			$.post('${vix}/drp/newMediaManagementAction!saveOrUpdate.action', {
			'newMedia.id' : $("#id").val(),
			'newMedia.mediaName' : $("#mediaName").val(),
			'newMedia.programName' : $("#programName").val(),
			'newMedia.targetAudienceRate' : $("#targetAudienceRate").val(),
			'newMedia.advertisingTime' : $("#advertisingTime").val(),
			'newMedia.advertisingPrice' : $("#advertisingPrice").val(),
			'newMedia.percentageCoverage' : $("#percentageCoverage").val(),
			'newMedia.programSectionPercentage' : $("#programSectionPercentage").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/newMediaManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#newMediaForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">市场管理 </a></li>
				<li><a href="#">媒体活动 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/newMediaManagementAction!goList.action');">新媒体管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${newMedia.id}" />
<div class="content">
	<form id="newMediaForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateNewMedia()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/newMediaManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新媒体信息 </b></strong>
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
											<th>媒体名称：</th>
											<td><input id="mediaName" name="mediaName" value="${newMedia.mediaName }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>节目名称：</th>
											<td><input id="programName" name="programName" value="${newMedia.programName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>目标受众率：</th>
											<td><input id="targetAudienceRate" name="targetAudienceRate" value="${newMedia.targetAudienceRate}" type="text" size="30" /></td>
											<th>广告时间：</th>
											<td><input id="advertisingTime" name="advertisingTime" value="${tvMedia.advertisingTime}" type="text" class="validate[required] text-input" /><img onclick="showTime('advertisingTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>广告价格：</th>
											<td><input id="advertisingPrice" name="advertisingPrice" value="${newMedia.advertisingPrice }" type="text" size="30"></td>
											<th>覆盖率百分比：</th>
											<td><input id="percentageCoverage" name="percentageCoverage" value="${newMedia.percentageCoverage}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>节目收视率百分比：</th>
											<td><input id="programSectionPercentage" name="programSectionPercentage" value="${newMedia.programSectionPercentage}" type="text" size="30" /></td>
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