<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateMediaPlan() {
		if ($('#mediaPlanForm').validationEngine('validate')) {
			$.post('${vix}/drp/mediaDeliveryPlanAction!saveOrUpdate.action', {
			'mediaPlan.id' : $("#id").val(),
			'mediaPlan.code' : $("#code").val(),
			'mediaPlan.name' : $("#name").val(),
			'mediaPlan.cost' : $("#cost").val(),
			'mediaPlan.startTime' : $("#startTime").val(),
			'mediaPlan.endTime' : $("#endTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/mediaDeliveryPlanAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#mediaPlanForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/drp/mediaDeliveryPlanAction!goList.action');">媒体组合投放计划</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${mediaPlan.id}" />
<div class="content">
	<form id="mediaPlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateMediaPlan()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/mediaDeliveryPlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>媒体信息 </b></strong>
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
											<td align="right">计划编码：</td>
											<td><input id="code" name="code" value="${mediaPlan.code }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${mediaPlan.name }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">计划费用：</td>
											<td><input id="cost" name="cost" value="${mediaPlan.cost }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">开始时间：</td>
											<td><input id="startTime" name="startTime" value="<s:date format="yyyy-MM-dd" name="%{mediaPlan.startTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">结束时间：</td>
											<td><input id="endTime" name="endTime" value="<s:date format="yyyy-MM-dd" name="%{mediaPlan.endTime}" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />电视</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(5,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />报纸</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(5,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />电台</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveTelevisionMedia(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '电视',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#televisionMediaForm').validationEngine('validate')) {
												$.post('${vix}/drp/mediaDeliveryPlanAction!saveOrUpdateTelevisionMedia.action', {
												'id' : $("#id").val(),
												'televisionMedia.id' : $("#televisionMediaId").val(),
												'televisionMedia.channelName' : $("#channelName").val(),
												'televisionMedia.mediaColumn' : $("#mediaColumn").val(),
												'televisionMedia.columnCoverageRate' : $("#columnCoverageRate").val(),
												'televisionMedia.columnStartTime' : $("#columnStartTime").val(),
												'televisionMedia.playTimes' : $("#playTimes").val(),
												'televisionMedia.advertisementTimes' : $("#advertisementTimes").val(),
												'televisionMedia.advertisementPrice' : $("#advertisementPrice").val(),
												'televisionMedia.cameTime' : $("#cameTime").val(),
												'televisionMedia.audience' : $("#audience").val(),
												'televisionMedia.advertisementFileName' : $("#advertisementFileName").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#televisionMedia').datagrid("reload");
												});
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
							$('#televisionMedia').datagrid({
							url : '${vix}/drp/mediaDeliveryPlanAction!getTelevisionMediaJson.action?id=${mediaPlan.id}',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'channelName',
							title : '频道名称',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'mediaColumn',
							title : '媒体栏目',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'columnCoverageRate',
							title : '栏目覆盖率',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'columnStartTime',
							title : '栏目开播时间',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'playTimes',
							title : '播放时长',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'advertisementTimes',
							title : '广告时长',
							width : 100,
							align : 'center'
							}, {
							field : 'advertisementPrice',
							title : '广告价格',
							width : 100,
							align : 'center'
							}, {
							field : 'cameTime',
							title : '投放时间',
							width : 100,
							align : 'center'
							}, {
							field : 'audience',
							title : '受众人数',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'advertisementFileName',
							title : '广告制作文件名称',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveTelevisionMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateTelevisionMedia.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#televisionMedia').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveTelevisionMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateTelevisionMedia.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#televisionMedia').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="televisionMedia"></table>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveNewsPaperMedia(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '报纸',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#newsPaperMediaForm').validationEngine('validate')) {
												$.post('${vix}/drp/mediaDeliveryPlanAction!saveOrUpdateNewsPaperMedia.action', {
												'id' : $("#id").val(),
												'newsPaperMedia.id' : $("#newsPaperMediaId").val(),
												'newsPaperMedia.columnName' : $("#columnName").val(),
												'newsPaperMedia.columnCoverageRate' : $("#columnCoverageRate").val(),
												'newsPaperMedia.advertisementPage' : $("#advertisementPage").val(),
												'newsPaperMedia.advertisementPrice' : $("#advertisementPrice").val(),
												'newsPaperMedia.cameTime' : $("#cameTime").val(),
												'newsPaperMedia.audience' : $("#audience").val(),
												'newsPaperMedia.advertisementFileName' : $("#advertisementFileName").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#newsPaperMedia').datagrid("reload");
												});
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
							$('#newsPaperMedia').datagrid({
							url : '${vix}/drp/mediaDeliveryPlanAction!getNewsPaperMediaJson.action?id=${mediaPlan.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'columnName',
							title : '栏目名称',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'columnCoverageRate',
							title : '栏目覆盖率',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'advertisementPage',
							title : '广告版面',
							width : 100,
							align : 'center'
							}, {
							field : 'advertisementPrice',
							title : '广告价格',
							width : 100,
							align : 'center'
							}, {
							field : 'cameTime',
							title : '投放时间',
							width : 100,
							align : 'center'
							}, {
							field : 'audience',
							title : '受众人数',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'advertisementFileName',
							title : '广告制作文件名称',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveNewsPaperMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateNewsPaperMedia.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#newsPaperMedia').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveNewsPaperMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateNewsPaperMedia.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#newsPaperMedia').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
								}
							}
							} ]
							});
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						</script>
						<div style="padding: 8px;">
							<table id="newsPaperMedia"></table>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveRadioMedia(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '电台',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#radioMediaForm').validationEngine('validate')) {
												$.post('${vix}/drp/mediaDeliveryPlanAction!saveOrUpdateRadioMedia.action', {
												'id' : $("#id").val(),
												'radioMedia.id' : $("#radioMediaId").val(),
												'radioMedia.channelName' : $("#channelName").val(),
												'radioMedia.mediaColumn' : $("#mediaColumn").val(),
												'radioMedia.columnCoverageRate' : $("#columnCoverageRate").val(),
												'radioMedia.columnStartTime' : $("#columnStartTime").val(),
												'radioMedia.playTimes' : $("#playTimes").val(),
												'radioMedia.advertisementTimes' : $("#advertisementTimes").val(),
												'radioMedia.advertisementPrice' : $("#advertisementPrice").val(),
												'radioMedia.cameTime' : $("#cameTime").val(),
												'radioMedia.audience' : $("#audience").val(),
												'radioMedia.advertisementFileName' : $("#advertisementFileName").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#radioMedia').datagrid("reload");
												});
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
							$('#radioMedia').datagrid({
							url : '${vix}/drp/mediaDeliveryPlanAction!getRadioMediaJson.action?id=${mediaPlan.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'channelName',
							title : '频道名称',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'mediaColumn',
							title : '媒体栏目',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'columnCoverageRate',
							title : '栏目覆盖率',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'columnStartTime',
							title : '栏目开播时间',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'playTimes',
							title : '播放时长',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'advertisementTimes',
							title : '广告时长',
							width : 100,
							align : 'center'
							}, {
							field : 'advertisementPrice',
							title : '广告价格',
							width : 100,
							align : 'center'
							}, {
							field : 'cameTime',
							title : '投放时间',
							width : 100,
							align : 'center'
							}, {
							field : 'audience',
							title : '受众人数',
							width : 100,
							align : 'right',
							required : 'true'
							}, {
							field : 'advertisementFileName',
							title : '广告制作文件名称',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveRadioMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateRadioMedia.action');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#radioMedia').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveRadioMedia('${vix}/drp/mediaDeliveryPlanAction!goSaveOrUpdateRadioMedia.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#radioMedia').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
								}
							}
							} ]
							});
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						</script>
						<div style="padding: 8px;">
							<table id="radioMedia"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>