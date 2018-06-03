<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdatePutInEffect() {
		if ($('#putInEffectForm').validationEngine('validate')) {
			$.post('${vix}/drp/putInEffectAction!saveOrUpdate.action', {
			'putInEffect.id' : $("#id").val(),
			'putInEffect.columnName' : $("#columnName").val(),
			'putInEffect.viewership' : $("#viewership").val(),
			'putInEffect.sectionPercentage' : $("#sectionPercentage").val(),
			'putInEffect.audience' : $("#audience").val(),
			'putInEffect.targetAudienceRate' : $("#targetAudienceRate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/putInEffectAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#putInEffectForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/drp/putInEffectAction!goList.action');">投放效果跟踪</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${putInEffect.id}" />
<div class="content">
	<form id="putInEffectForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePutInEffect()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/putInEffectAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>媒体信息</b></strong>
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
											<td align="right">栏目：</td>
											<td><input id="columnName" name="columnName" value="${putInEffect.columnName }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">收视人数：</td>
											<td><input id="viewership" name="viewership" value="${putInEffect.viewership }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">收视率百分比：</td>
											<td><input id="sectionPercentage" name="sectionPercentage" value="${putInEffect.sectionPercentage }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">受众人数：</td>
											<td><input id="audience" name="audience" value="${putInEffect.audience }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">目标受众率百分比：</td>
											<td><input id="targetAudienceRate" name="targetAudienceRate" value="${putInEffect.targetAudienceRate }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>