<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	$("#integralRulesForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#integralRulesForm').validationEngine('validate')) {
			$.post('${vix}/drp/integralRulesSetAction!saveOrUpdate.action', {
			'integralRules.id' : $("#id").val(),
			'storeId' : $("#storeId").val(),
			'integralRules.isZc' : $(":checkbox[name=isZc][checked]").val(),
			'integralRules.presentZc' : $("#presentZc").val(),
			'integralRules.presentType' : $(":radio[name=presentType][checked]").val(),
			'integralRules.conversiorate' : $("#conversiorate").val(),
			'integralRules.isXszc' : $(":checkbox[name=isXszc][checked]").val(),
			'integralRules.xsConversiorate' : $("#xsConversiorate").val(),
			'integralRules.xsStartDate' : $("#xsStartDate").val(),
			'integralRules.xsEndDate' : $("#xsEndDate").val(),
			'integralRules.isJfyxq' : $(":checkbox[name=isJfyxq][checked]").val(),
			'integralRules.yxType' : $(":radio[name=yxType][checked]").val(),
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/integralRulesSetAction!goList.action');
			});
		}
	};
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/drp/integralRulesSetAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#right").html(html);
		}
		});
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" />供应链</a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">积分管理</a></li>
				<li><a href="#">积分规则设置</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="integralRulesForm">
		<!-- c_head -->
		<div class="box">
			<div id="left" class="ui-resizable" style="height: 500px;">
				<div id="switch_box" class="switch_btn"></div>
				<div class="left_content" style="height: 500px;">
					<div id="tree_root" class="ztree" style="padding: 0;"></div>
				</div>
				<script type="text/javascript">
					var zTree;
					var setting = {
					async : {
					enable : true,
					url : "${vix}/drp/integralRulesSetAction!findOrgAndUnitTreeToJson.action",
					autoParam : [ "id", "treeType" ]
					},
					callback : {
						onClick : onClick
					}
					};
					function onClick(event, treeId, treeNode, clickFlag) {
						$.ajax({
						url : '${vix}/drp/integralRulesSetAction!goSaveOrUpdate.action?storeId=' + treeNode.id,
						cache : false,
						success : function(html) {
							$("#right").html(html);
							checkedradio();
						}
						});
					}
					zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
				<div class="ui-resizable-handle ui-resizable-e"></div>
			</div>
			<div id="right"></div>
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
	</form>
</div>