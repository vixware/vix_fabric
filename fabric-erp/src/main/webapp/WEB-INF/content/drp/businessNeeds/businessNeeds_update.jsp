<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/businessNeedsAction!saveOrUpdate.action', {
				'channelDistributor.id' : $("#id").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/businessNeedsAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">门店管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/businessNeedsAction!goList.action');">文商需求 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/businessNeedsAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
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
											<td align="right">编码：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="35" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="industry" name="industry" class="validate[required] text-input">
													<option value="">请选择</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">创建时间：</td>
											<td><input id="createTime" name="createTime" value="${wimStockrecords.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="">*</span></td>
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