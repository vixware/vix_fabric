<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdatePrintMedia() {
		if ($('#printMediaForm').validationEngine('validate')) {
			$.post('${vix}/drp/printMediaManagementAction!saveOrUpdate.action', {
			'printMedia.id' : $("#id").val(),
			'printMedia.name' : $("#name").val(),
			'printMedia.mediaColumn' : $("#mediaColumn").val(),
			'printMedia.section' : $("#section").val(),
			'printMedia.price' : $("#price").val(),
			'printMedia.introductions' : $("#introductions").val(),
			'printMedia.contactPerson' : $("#contactPerson").val(),
			'printMedia.mobile' : $("#mobile").val(),
			'printMedia.email' : $("#email").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/printMediaManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#printMediaForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/drp/printMediaManagementAction!goList.action');">平面媒体管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="printMedia.id" value="${printMedia.id}" />
<div class="content">
	<form id="printMediaForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePrintMedia()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/printMediaManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>媒体信息 </b></strong>
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
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${printMedia.name }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">栏目：</td>
											<td><input id="mediaColumn" name="mediaColumn" value="${printMedia.mediaColumn }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">版面：</td>
											<td><input id="section" name="section" value="${printMedia.section }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">价格：</td>
											<td><input id="price" name="price" value="${printMedia.price }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">说明：</td>
											<td><input id="introductions" name="introductions" value="${printMedia.introductions }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">广告联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${printMedia.contactPerson }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">手机：</td>
											<td><input id="mobile" name="mobile" value="${printMedia.mobile }" type="text" size="35" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">邮件：</td>
											<td><input id="email" name="email" value="${printMedia.email }" type="text" size="30" /></td>
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