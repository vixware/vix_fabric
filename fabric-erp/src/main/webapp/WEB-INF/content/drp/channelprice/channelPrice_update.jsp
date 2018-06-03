<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function chooseAreaChannelDistributor(part) {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择分销商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorAreaId" + part).val(result[0]);
						$("#channelDistributorAreaName" + part).val(result[1]);
					} else {
						$("#channelDistributorAreaId").val("");
						$("#channelDistributorAreaName").val("");
						asyncbox.success("请选择分销商信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	/** 选择产品 */
	function chooseItemForchannelPriceCondition() {
		$.ajax({
		url : '${vix}/drp/channelPriceAction!goChooseItem.action?parentId=' + $('#selectId').val(),
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 540,
			title : "选择${vv:varView('vix_mdm_item')}",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						var i = returnValue.split(",");
						$("#itemId").val(i[0]);
						$("#itemName").val(i[1]);
					} else {
						asyncbox.success("请选择${vv:varView('vix_mdm_item')}!", "<s:text name='vix_message'/>");
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function loadPriceConditionDetail(priceType) {
		var url = '';
		if (priceType == 'price') {
			url = '${vix}/drp/channelPriceConditionPriceAreaAction!goListContent.action?id=' + $("#channelPriceConditionId").val();
		} else {
			url = '${vix}/drp/channelPriceConditionCountAreaAction!goListContent.action?id=' + $("#channelPriceConditionId").val();
		}
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			if (priceType == 'price') {
				$("#priceConditionPriceArea").html(html);
			} else {
				$("#priceConditionCountArea").html(html);
			}
		}
		});
	};
	loadPriceConditionDetail('count');
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function savePriceCondition(tag) {
		if ($('#priceConditionForm').validationEngine('validate')) {
			$.post('${vix}/drp/channelPriceAction!saveOrUpdate.action', {
			'channelPriceCondition.id' : $("#channelPriceConditionId").val(),
			'channelPriceCondition.item.id' : $("#itemId").val(),
			'channelPriceCondition.code' : $("#code").val(),
			'channelPriceCondition.name' : $("#name").val(),
			'channelPriceCondition.defaultTax' : $("#defaultTax").val(),
			'channelPriceCondition.channelDistributor.id' : $("#channelDistributorId").val(),
			'channelPriceCondition.currencyType.id' : $("#currencyTypeId").val(),
			'channelPriceCondition.startEffectiveTime' : $("#startEffectiveTime").val(),
			'updateField' : updateField,
			'channelPriceCondition.endEffectiveTime' : $("#endEffectiveTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				if (tag == '0') {
					loadContent('${vix}/drp/channelPriceAction!goList.action');
				} else {
					$.ajax({
					url : '${vix}/drp/channelPriceAction!goSaveOrUpdate.action?id=' + id,
					cache : false,
					success : function(html) {
						$("#mainContent").html(html);
					}
					});
				}
			});
		}
	}
	function saveOrUpdatePriceConditionDetail(id, priceType) {
		var url = '';
		var title = '';
		if (priceType == 'price') {
			url = '${vix}/drp/channelPriceConditionPriceAreaAction!goSaveOrUpdate.action?id=' + id;
			title = '定价价格区域';
		} else {
			url = '${vix}/drp/channelPriceConditionCountAreaAction!goSaveOrUpdate.action?id=' + id;
			title = '定价数量区域';
		}
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 880,
			height : 440,
			title : title,
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#priceConditionAreaForm').validationEngine('validate')) {
						if (priceType == 'price') {
							$.post('${vix}/drp/channelPriceConditionPriceAreaAction!saveOrUpdate.action', {
							'channelPriceConditionPriceArea.id' : $("#pcpaId").val(),
							'channelPriceConditionPriceArea.channelPriceCondition.id' : $("#channelPriceConditionId").val(),
							/* 'channelPriceConditionPriceArea.item.id' : $("#itemIdCd").val(), */
							'channelPriceConditionPriceArea.startTotalAmount' : $("#startTotalAmount").val(),
							'channelPriceConditionPriceArea.endTotalAmount' : $("#endTotalAmount").val(),
							'channelPriceConditionPriceArea.refund' : $("#refund").val(),
							'channelPriceConditionPriceArea.discount' : $("#discount").val(),
							'updateField' : updateField,
							'channelPriceConditionPriceArea.memo' : $("#memo").val()
							}, function(result) {
								asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
									loadPriceConditionDetail('price');
								});
							});
						} else {
							$.post('${vix}/drp/channelPriceConditionCountAreaAction!saveOrUpdate.action', {
							'channelPriceConditionCountArea.id' : $("#pccaId").val(),
							'channelPriceConditionCountArea.channelPriceCondition.id' : $("#channelPriceConditionId").val(),
							/* 'channelPriceConditionCountArea.item.id' : $("#itemIdCd").val(), */
							'channelPriceConditionCountArea.startCount' : $("#startCount").val(),
							'channelPriceConditionCountArea.endCount' : $("#endCount").val(),
							'channelPriceConditionCountArea.unit' : $("#unit").val(),
							'channelPriceConditionCountArea.price' : $("#price").val(),
							'channelPriceConditionCountArea.discount' : $("#discount").val(),
							'updateField' : updateField,
							'channelPriceConditionCountArea.memo' : $("#memo").val()
							}, function(result) {
								asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
									loadPriceConditionDetail('count');
								});
							});
						}
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function deletePriceConditionEntity(id, priceType) {
		var url = '';
		if (priceType == 'price') {
			url = '${vix}/drp/channelPriceConditionPriceAreaAction!deleteById.action?id=' + id;
		} else {
			url = '${vix}/drp/channelPriceConditionCountAreaAction!deleteById.action?id=' + id;
		}
		asyncbox.confirm('确定要删除该定价条件么?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : url,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						loadPriceConditionDetail(priceType);
					});
				}
				});
			}
		});
	}
	$("#priceConditionForm").validationEngine();
</script>
<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" />供应链</a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelPriceAction!goList.action');">渠道价格</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="savePriceCondition('0');" href="#" title="保存"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a onclick="savePriceCondition('1');" href="#" title="保存并新增"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
					onclick="loadContent('${vix}/drp/channelPriceAction!goList.action');" title="返回"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
				</span> <strong> <b> 新增价格条件 </b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b class="downup"></b> <strong>基本信息</strong>
							</dt>
							<dd style="display: block;">
								<form id="priceConditionForm">
									<table style="border: none">
										<s:hidden id="channelPriceConditionId" name="channelPriceConditionId" value="%{channelPriceCondition.id}" theme="simple" />
										<tr height="30">
											<td align="right">分销商:&nbsp;</td>
											<td><input type="hidden" id="channelDistributorId" value="${channelPriceCondition.channelDistributor.id}" onchange="fieldChanged(this);" /> <input id="channelDistributorName" name="channelPriceCondition.channelDistributor.name" value="${channelPriceCondition.channelDistributor.name}" type="text"
												class="validate[required] text-input" size="30" /> <span style="color: red;">*</span><a class="abtn" href="#" onclick="chooseAreaChannelDistributor();"><span>选择</span></a></td>
											<td align="right">商品:&nbsp;</td>
											<td><input type="hidden" id="itemId" value="${channelPriceCondition.item.id}" onchange="fieldChanged(this);" /> <input id="itemName" name="itemName" value="${channelPriceCondition.item.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <a class="abtn" href="#"
												onclick="chooseItemForchannelPriceCondition();"><span>选择</span></a></td>
										</tr>
										<tr height="30">
											<td align="right">编码:&nbsp;</td>
											<td><input id="code" name="channelPriceCondition.code" value="${channelPriceCondition.code}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">主题:&nbsp;</td>
											<td><input id="name" name="channelPriceCondition.name" value="${channelPriceCondition.name}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr height="30">
											<td align="right">税率:&nbsp;</td>
											<td><input id="defaultTax" name="channelPriceCondition.defaultTax" value="${channelPriceCondition.defaultTax}" class="validate[required] text-input" onchange="fieldChanged(this);" />% 范围(1-100)<span style="color: red;">*</span></td>
											<td align="right">币种:&nbsp;</td>
											<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{channelPriceCondition.currencyType.id}" multiple="false" theme="simple" onchange="fieldChanged(this);"></s:select><span style="color: red;">*</span></td>
										</tr>
										<tr height="30">
											<td align="right">开始时间:&nbsp;</td>
											<td><input type="text" id="startEffectiveTime" name="channelPriceCondition.startEffectiveTime" onchange="fieldChanged(this);" value="<s:property value='formatDateToTimeString(channelPriceCondition.startEffectiveTime)'/>" onfocus="showTime('startEffectiveTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /><span
												style="color: red;">*</span> <img onclick="showTime('startEffectiveTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">结束时间:&nbsp;</td>
											<td><input type="text" id="endEffectiveTime" name="channelPriceCondition.endEffectiveTime" onchange="fieldChanged(this);" value="<s:property value='formatDateToTimeString(channelPriceCondition.endEffectiveTime)'/>" onfocus="showTime('endEffectiveTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /><span
												style="color: red;">*</span> <img onclick="showTime('endEffectiveTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</form>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
			<div class="clearfix" style="background: #FFF; position: relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadPriceConditionDetail('count');"><img alt="" src="img/mail.png">数量区间</a></li>
						<li class=""><a onclick="javascript:tab(2,2,'a',event);loadPriceConditionDetail('price');"><img alt="" src="img/mail.png">价格区间</a></li>
					</ul>
				</div>
				<div id="a1" class="right_content" style="display: block;">
					<div class="list_table" style="margin: 0; width: 100%">
						<p>
							<a class="abtn" href="###" onclick="saveOrUpdatePriceConditionDetail(0,'count');"><span style="width: 50px;">添加明细</span></a>
						</p>
					</div>
					<div id="priceConditionCountArea" class="list_table" style="margin: 0; width: 100%"></div>
				</div>
				<div id="a2" class="right_content" style="display: none;">
					<div class="list_table" style="margin: 0; width: 100%">
						<p>
							<a class="abtn" href="###" onclick="saveOrUpdatePriceConditionDetail(0,'price');"><span style="width: 50px;">添加明细</span></a>
						</p>
					</div>
					<div id="priceConditionPriceArea" class="list_table" style="margin: 0; width: 100%"></div>
				</div>
			</div>
		</dl>
	</div>
</div>