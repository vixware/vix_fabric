<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link href="css/demo_style.css" rel="stylesheet" type="text/css">
<link href="${vix}/common/css/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<script src="${vix}/common/js/jquery.smartWizard-2.0.min.js" type="text/javascript"></script>
<!-- head -->
<script type="text/javascript">
	function saveOrUpdateAccountInformation() {
		if ($('#accountInformationForm').validationEngine('validate')) {
			$.post('${vix}/system/initSetAction!saveOrUpdateAccountInformation.action', {
			'accountInformation.id' : $("#accountInformationId").val(),
			'accountInformation.code' : $("#accountInformationCode").val(),
			'accountInformation.name' : $("#accountInformationName").val(),
			'accountInformation.startTime' : $("#accountInformationStartTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
			});
		}
	}
	function saveOrUpdateOrganization() {
		if ($('#organizationForm').validationEngine('validate')) {
			$.post('${vix}/system/initSetAction!saveOrUpdateOrganization.action', {
			'organization.id' : $("#organizationId").val(),
			'organization.code' : $("#organizationCode").val(),
			'organization.orgName' : $("#orgName").val(),
			'organization.address' : $("#address").val(),
			'organization.corporateRepresentative' : $("#corporateRepresentative").val(),
			'organization.telephone' : $("#telephone").val(),
			'organization.fax' : $("#fax").val(),
			'organization.memo' : $("#memo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
			});
		}
	}
	function saveOrUpdateCostingType() {
		if ($('#costingTypeForm').validationEngine('validate')) {
			$.post('${vix}/system/initSetAction!saveOrUpdateCostingType.action', {
			'costingType.id' : $("#costingTypeId").val(),
			'costingType.code' : $("#costingTypeCode").val(),
			'costingType.name' : $("#costingTypeName").val(),
			'costingType.companyType' : $("#companyType").val(),
			'costingType.businessNature' : $("#businessNature").val(),
			'costingType.presetLanguageSubjects' : $("#presetLanguageSubjects").val(),
			'costingType.accountDirector' : $("#accountDirector").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
			});
		}
	}
	$("#accountInformationForm").validationEngine();
	$("#organizationForm").validationEngine();
	$("#costingTypeForm").validationEngine();
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/system/codeAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#codeRight").html(html);
		}
		});
	});

	$(document).ready(function() {
		$.ajax({
		url : '${vix}/system/precisionControlAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#precisionControlRight").html(html);
		}
		});
	});

	$("#baseCoderForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate(selectId) {
		if ($('#baseCoderForm').validationEngine('validate')) {
			$.post('${vix}/system/precisionControlAction!saveOrUpdate.action', {
			'precisionControl.id' : $("#id").val(),
			'organizationId' : selectId,
			'precisionControl.stockSizes' : $("#stockSizes").val(),
			'precisionControl.stockPrice' : $("#stockPrice").val(),
			'precisionControl.noteTheUnitPrice' : $("#noteTheUnitPrice").val(),
			'precisionControl.amount' : $("#amount").val(),
			'precisionControl.conversiorate' : $("#conversiorate").val(),
			'precisionControl.taxRate' : $("#taxRate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/system/precisionControlAction!goList.action');
			});
		}
	};
</script>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="wizard" class="swMain">
			<ul>
				<li><a href="#step-1" onclick="saveOrUpdateAccountInformation();"> <label class="stepNumber">1</label> <span class="stepDesc"> 企业信息<br />
					</span>
				</a></li>
				<li><a href="#step-2" onclick="saveOrUpdateCostingType();"> <label class="stepNumber">2</label> <span class="stepDesc"> 核算类型<br />
					</span>
				</a></li>
				<li><a href="#step-3"> <label class="stepNumber">3</label> <span class="stepDesc">基础信息<br />
					</span>
				</a></li>
				<li><a href="#step-4"> <label class="stepNumber">4</label> <span class="stepDesc"> 编码方案<br />
					</span>
				</a></li>
				<li><a href="#step-5"> <label class="stepNumber">5</label> <span class="stepDesc"> 数据精度<br />
					</span>
				</a></li>
				<li><a href="#step-6"> <label class="stepNumber">6</label> <span class="stepDesc"> 用户管理<br />
					</span>
				</a></li>
			</ul>
			<div id="step-1">
				<form id="organizationForm">
					<input type="hidden" id="organizationId" name="organizationId" value="${organization.id}" />
					<h2 class="StepTitle">企业信息</h2>
					<table width="100%" class="lineh30">
						<tr>
							<td>账套编码</td>
							<td><input type="text" id="accountInformationCode" name="accountInformationCode" value="${accountInformation.code}" class="ipt  wb80"></td>
							<td>账套名称</td>
							<td><input type="text" id="accountInformationName" name="accountInformationName" value="${accountInformation.name}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>启用会计期</td>
							<td><input id="accountInformationStartTime" name="accountInformationStartTime" class="ipt  wb50" value="${accountInformation.startTime}" type="text" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" align="absmiddle"></td>
						</tr>
						<tr>
							<td>编码</td>
							<td><input type="text" id="organizationCode" name="organizationCode" value="${organization.code}" class="ipt  wb80"></td>
							<td>名称</td>
							<td><input type="text" id="orgName" name="orgName" value="${organization.orgName}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>单位地址</td>
							<td><input type="text" id="address" name="address" value="${organization.address}" class="ipt  wb80"></td>
							<td>法人代表</td>
							<td><input type="text" id="corporateRepresentative" name="corporateRepresentative" value="${organization.corporateRepresentative}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><input type="text" id="telephone" name="telephone" value="${organization.telephone}" class="ipt  wb80"></td>
							<td>传真</td>
							<td><input type="text" id="fax" name="fax" value="${organization.fax}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>备注</td>
							<td><input type="text" id="memo" name="memo" value="${organization.memo}" class="ipt  wb80"></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="step-2">
				<form id="costingTypeForm">
					<input type="hidden" id="costingTypeId" name="costingTypeId" value="${costingType.id}" />
					<h2 class="StepTitle">核算类型</h2>
					<table width="100%" class="lineh30" style="margin-bottom: 10px;">
						<tr>
							<td>本币代码</td>
							<td><input type="text" id="costingTypeCode" name="costingTypeCode" value="${costingType.code}" class="ipt  wb80"></td>
							<td>本币名称</td>
							<td><input type="text" id="costingTypeName" name="costingTypeName" value="${costingType.name}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>企业类型</td>
							<td><input type="text" id="companyType" name="companyType" value="${costingType.companyType}" class="ipt  wb80"></td>
							<td>行业性质</td>
							<td><input type="text" id="businessNature" name="businessNature" value="${costingType.businessNature}" class="ipt  wb80"></td>
						</tr>
						<tr>
							<td>科目预置语言</td>
							<td><input type="text" id="presetLanguageSubjects" name="presetLanguageSubjects" value="${costingType.presetLanguageSubjects}" class="ipt  wb80"></td>
							<td>账套主管</td>
							<td><input type="text" id="accountDirector" name="accountDirector" value="${costingType.accountDirector}" class="ipt  wb80"></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="step-3">
				<h2 class="StepTitle">基础信息</h2>
			</div>
			<div id="step-4">
				<h2 class="StepTitle">编码方案</h2>
				<form id="baseCoderForm">
					<!-- c_head -->
					<div class="box">
						<div id="left" class="ui-resizable" style="height: 500px;">
							<div id="switch_box" class="switch_btn"></div>
							<div class="left_content" style="height: 500px;">
								<div id="codeRoot" class="ztree" style="padding: 0;"></div>
							</div>
							<script type="text/javascript">
								var zTree;
								var setting = {
								async : {
								enable : true,
								url : "${vix}/system/billTypeManagementAction!findOrgAndUnitTreeToJson.action",
								autoParam : [ "id", "treeType" ]
								},
								callback : {
									onClick : onClick
								}
								};
								function onClick(event, treeId, treeNode, clickFlag) {
									$.ajax({
									url : '${vix}/system/codeAction!goSaveOrUpdate.action?billTypeCode=' + treeNode.code + "&billTypeId=" + treeNode.id,
									cache : false,
									success : function(html) {
										$("#codeRight").html(html);
										checkedradio();
									}
									});
								}
								zTree = $.fn.zTree.init($("#codeRoot"), setting);
							</script>
							<div class="ui-resizable-handle ui-resizable-e"></div>
						</div>
						<div id="codeRight"></div>
					</div>
				</form>
				<div class="tg_w_auto"></div>
			</div>
			<div id="step-5">
				<h2 class="StepTitle">数据精度</h2>
				<div class="box">
					<div id="left" class="ui-resizable" style="height: 500px;">
						<div id="switch_box" class="switch_btn"></div>
						<div class="left_content" style="height: 500px;">
							<div id="precisionControl" class="ztree" style="padding: 0;"></div>
						</div>
						<script type="text/javascript">
							var zTree;
							var setting = {
							async : {
							enable : true,
							url : "${vix}/system/precisionControlAction!findTreeToJson.action",
							autoParam : [ "id", "treeType" ]
							},
							callback : {
								onClick : onClick
							}
							};
							function onClick(event, treeId, treeNode, clickFlag) {
								$("#selectId").val(treeNode.id);
								$.ajax({
								url : '${vix}/system/precisionControlAction!goSaveOrUpdate.action?organizationId=' + treeNode.id,
								cache : false,
								success : function(html) {
									$("#precisionControlRight").html(html);
								}
								});
							}
							zTree = $.fn.zTree.init($("#precisionControl"), setting);
						</script>
						<div class="ui-resizable-handle ui-resizable-e"></div>
					</div>
					<div id="precisionControlRight"></div>
				</div>
			</div>
			<div id="step-6">
				<h2 class="StepTitle">用户管理</h2>
				<%-- <table>
					<div class="pagelist drop">
						<ul>
							<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
								<ul>
									<li><a href="#"><s:text name='cmn_delete' /></a></li>
									<li><a href="#"><s:text name='cmn_mail' /></a></li>
									<li><a href="#"><s:text name="merger" /></a></li>
									<li><a href="#"><s:text name="export" /></a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
						<div>
							<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b
								class="roleInfo"></b> <s:text name='cmn_to' /> <b class="roleTotalCount"></b>)
							</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
						</div>
					</div>
					<div id="role" class="table"></div>
					<div class="pagelist drop">
						<ul>
							<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
								<ul>
									<li><a href="#"><s:text name='cmn_delete' /></a></li>
									<li><a href="#"><s:text name='cmn_mail' /></a></li>
									<li><a href="#"><s:text name="merger" /></a></li>
									<li><a href="#"><s:text name="export" /></a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
						<div>
							<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b
								class="roleInfo"></b> <s:text name='cmn_to' /> <b class="roleTotalCount"></b>)
							</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
						</div>
					</div>
				</table> --%>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
	<script type="text/javascript">
		window.onload = function() {
			setTimeout(function() {
				// 不延迟 grid不能初始化
				// 测试FF、chrome延迟500毫秒
				// IE下 datagrid延迟2秒，treegrid延迟4秒
				// 此延迟取决于easyui grid初始化执行完成时间
				$("#step-3,#step-4").removeAttr('style');
				$('#wizard').smartWizard({
					transitionEffect : 'slide'
				});
			}, 3000);
		}
	</script>
</div>