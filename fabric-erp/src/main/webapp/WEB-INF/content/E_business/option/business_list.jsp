<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//加载列表显示
	pager("start", "${vix}/business/onlineStoreSetAction!goStorePage.action?1=1", 'store');
	//保存平台类型
	function saveOrUpdateStoreType() {
		if ($('#storeTypeForm').validationEngine('validate')) {
			$.post('${vix}/business/onlineStoreSetAction!saveOrUpdateStoreType.action', {
			'storeType.id' : $("#storeTypeId").val(),
			'storeType.code' : $("#storeTypeCode").val(),
			'storeType.type' : $("#type").val(),
			'storeType.name' : $("#storeTypeName").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$("#storeTypeId").val("");
				$("#storeTypeCode").val("");
				$("#storeTypeName").val("");
				pager("start", "${vix}/business/onlineStoreSetAction!goStoreTypePage.action?pageNo=${pageNo}", 'storeType');
			});
		} else {
			return false;
		}
	};
	//保存网店信息
	function saveOrUpdateStore() {
		if ($('#storeForm').validationEngine('validate')) {
			$.post('${vix}/business/onlineStoreSetAction!saveOrUpdateChannelDistributor.action', {
			'channelDistributor.id' : $("#channelDistributorId").val(),
			'channelDistributor.storeType.id' : $("#storeId").val(),
			'channelDistributor.name' : $("#name").val(),
			'channelDistributor.appKey' : $("#appKey").val(),
			'channelDistributor.appSecret' : $("#appSecret").val(),
			'channelDistributor.session' : $("#session").val(),
			'channelDistributor.mallType' : $("#mallType").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$("#channelDistributorId").val("");
				$("#storeId").val("");
				$("#name").val("");
				$("#nick").val("");
				$("#appKey").val("");
				$("#appSecret").val("");
				$("#session").val("");
				pager("start", "${vix}/business/onlineStoreSetAction!goStorePage.action?pageNo=${pageNo}", 'store');
			});
		} else {
			return false;
		}
	};
	//保存物流公司信息
	function saveOrUpdateLogistics() {
		if ($('#logisticsForm').validationEngine('validate')) {
			$.post('${vix}/business/onlineStoreSetAction!saveOrUpdateLogistics.action', {
			'logistics.id' : $("#logisticsId").val(),
			'logistics.code' : $("#logisticsCode").val(),
			'logistics.name' : $("#logisticsName").val(),
			'logistics.isDefault' : $("#isDefault").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$("#logisticsId").val("");
				$("#logisticsCode").val("");
				$("#logisticsName").val("");
				$("#isDefault").val("");
				pager("start", "${vix}/business/onlineStoreSetAction!goLogisticsPage.action?pageNo=${pageNo}", 'logistics');
			});
		} else {
			return false;
		}
	};
	function goAuthorize(id) {
		window.location.href = $('#storeurl').val();
		/* $.ajax({
		url : '${vix}/business/onlineStoreSetAction!goJDAuthorize.action?id=' + id,
		cache : false,
		success : function(html) {
		}
		}); */
	};
	$("#storeForm").validationEngine();
	$("#storeTypeForm").validationEngine();
	$("#logisticsForm").validationEngine();
	//修改物流公司信息
	function chooseLogistics(id, code, name, isDefault) {
		$("#logisticsId").val(id);
		$("#logisticsCode").val(code);
		$("#logisticsName").val(name);
		$("#isDefault").val(isDefault);
	}
	//修改平台类型
	function chooseStoreType(id, code, name, type) {
		$("#storeTypeId").val(id);
		$("#storeTypeCode").val(code);
		$("#storeTypeName").val(name);
		$("#type").val(type);
	}
	function chooseStore(id, storeTypeId, name, appKey, appSecret, session, nick, mallType) {
		$("#channelDistributorId").val(id);
		$("#storeId").val(storeTypeId);
		$("#name").val(name);
		$("#nick").val(nick);
		$("#appKey").val(appKey);
		$("#appSecret").val(appSecret);
		$("#session").val(session);
		$("#mallType").val(mallType);
	}

	function tab(num, befor, id, e, entity) {
		var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
		var pa = el.parentNode.getElementsByTagName("li");
		for (var i = 0; i < pa.length; i++) {
			pa[i].className = "";
		}
		el.className = "current";
		for (i = 1; i <= num; i++) {
			try {
				if (i == befor) {
					document.getElementById(id + i).style.display = "block";
				} else {
					document.getElementById(id + i).style.display = "none";
				}
			} catch (e) {
			}
		}
		if (entity != undefined) {
			categoryPager('start', entity);
		}
	}
	function categoryPager(tag, entity) {

		if (entity == 'logistics') {
			pager(tag, "${vix}/business/onlineStoreSetAction!goLogisticsPage.action?1=1", entity);
		}
		if (entity == 'storeType') {
			pager(tag, "${vix}/business/onlineStoreSetAction!goStoreTypePage.action?1=1", entity);
		}
		if (entity == 'store') {
			pager(tag, "${vix}/business/onlineStoreSetAction!goStorePage.action?1=1", entity);
		}
	}

	function deleteStoreById(id) {
		asyncbox.confirm('确定要删除该网店吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/business/onlineStoreSetAction!deleteStoreById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						pager("start", "${vix}/business/onlineStoreSetAction!goStorePage.action?1=1", 'store');
					});
				}
				});
			}
		});
	}
	function deleteStoreTypeById(id) {
		asyncbox.confirm('确定要删除该电商平台吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/business/onlineStoreSetAction!deleteStoreTypeById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						pager("start", "${vix}/business/onlineStoreSetAction!goStoreTypePage.action?1=1", 'storeType');
					});
				}
				});
			}
		});
	}
	function deleteLogisticsById(id) {
		asyncbox.confirm('确定要删除该物流公司吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/business/onlineStoreSetAction!deleteLogisticsById.action?id=' + id,
				cache : false,
				success : function(html) {
					asyncbox.success(html, "提示信息", function(action) {
						pager("start", "${vix}/business/onlineStoreSetAction!goLogisticsPage.action?1=1", 'logistics');
					});
				}
				});
			}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/inv_productDisassembly.png" alt="" /> <s:text name="cmn_supplyChain" /> </a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">网店设置</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>网店设置</h2>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a onclick="tab(5,1,'np',event,'store')">店铺管理</a></li>
					<li><a onclick="tab(5,2,'np',event,'logistics')">物流设置</a></li>
					<li><a onclick="tab(5,3,'np',event,'storeType')">平台设置</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form id="storeForm">
						<div class="nt">
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<input type="hidden" id="channelDistributorId" name="channelDistributorId" />
										<input type="hidden" id="storeurl" name="storeurl" value="${storeurl }" />
										<tr class="alt">
											<td align="right"><span style="color: red;">*</span>店铺名称:&nbsp;</td>
											<td><input type="text" id="name" name="name" value="${channelDistributor.name}" class="ipt w88per underline" size="50" /></td>
											<td align="right"><span style="color: red;">*</span>平台类型：</td>
											<td><s:select id="storeId" headerKey="-1" headerValue="--请选择--" list="%{storeTypeList}" listValue="name" listKey="id" value="%{channelDistributor.storeType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr class="alt">
											<td align="right"><span style="color: red;">*</span>appKey(电商店铺编码):&nbsp;</td>
											<td><input type="text" id="appKey" name="appKey" class="ipt w88per underline" value="${channelDistributor.appKey}" size="50" /></td>
											<td align="right">appSecret:&nbsp;</td>
											<td><input type="text" id="appSecret" name="appSecret" class="ipt w88per underline" value="${channelDistributor.appSecret}" size="50" /></td>
										</tr>
										<tr class="alt">
											<td align="right">session:&nbsp;</td>
											<td colspan="3"><input type="text" id="session" name="session" value="${channelDistributor.session}" class="ipt w88per underline" size="70" /></td>
										</tr>
										<tr class="alt">
											<td align="right">模式(京东):&nbsp;</td>
											<td><select id="mallType" name="mallType" class="ipt w88per underline">
													<option value="">--请选择--</option>
													<option value="5">LBP</option>
													<option value="6">SOP</option>
													<option value="7">SOPL</option>
											</select></td>
										</tr>
									</tbody>
								</table>
							</div>
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
								<tr>
									<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdateStore();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</table>
							<div class="c_box">
								<div id="store" class="box_table"></div>
								<div class="pagelist">
									<div>
										<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="storeInfo"></b>到 <b class="storeTotalCount"></b>)
										</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist" id="np2" style="display: none;">
					<form id="logisticsForm">
						<div class="nt">
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<input type="hidden" id="logisticsId" name="logisticsId" />
										<tr class="alt">
											<td align="right">编码&nbsp;</td>
											<td><input type="text" id="logisticsCode" name="logisticsCode" class="ipt w88per underline" /></td>
											<td align="right">名称&nbsp;</td>
											<td><input type="text" id="logisticsName" name="logisticsName" class="ipt w88per underline" /></td>
										</tr>
										<tr class="alt">
											<td align="right">是否默认&nbsp;</td>
											<td><select id="isDefault" name="isDefault" class="ipt w88per underline">
													<option value="">请选择</option>
													<option value="0">否</option>
													<option value="1">是</option>
											</select><span style="color: red;">*</span></td>
										</tr>
									</tbody>
								</table>
							</div>
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
								<tr>
									<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdateLogistics();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</table>
							<div class="c_box">
								<div id="logistics" class="box_table"></div>
								<div class="pagelist">
									<div>
										<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="logisticsInfo"></b>到 <b class="logisticsTotalCount"></b>)
										</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist lineh30" id="np3" style="display: none;">
					<form id="storeTypeForm">
						<div class="nt">
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<input type="hidden" id="storeTypeId" name="storeTypeId" />
											<td align="right" width="20%">平台编码&nbsp;</td>
											<td width="30%"><input type="text" id="storeTypeCode" name="storeTypeCode" class="ipt w88per underline" /></td>
											<td align="right" width="20%">平台名称&nbsp;</td>
											<td width="30%"><input type="text" id="storeTypeName" name="storeTypeName" class="ipt w88per underline" /></td>
										</tr>
										<tr class="alt">
											<td align="right" width="20%">平台类型&nbsp;</td>
											<td width="30%"><select id="type" name="type" class="ipt w88per underline">
													<option value="">--请选择--</option>
													<option value="1">淘宝</option>
													<option value="3">京东</option>
													<option value="6">一号店</option>
											</select></td>
										</tr>
									</tbody>
								</table>
							</div>
							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
								<tr>
									<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdateStoreType();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
								</tr>
							</table>
							<div class="c_box">
								<div id="storeType" class="box_table"></div>
								<div class="pagelist">
									<div>
										<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="storeTypeInfo"></b>到 <b class="storeTypeTotalCount"></b>)
										</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>