<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#baseCoderForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#baseCoderForm').validationEngine('validate')) {
			$.post('${vix}/system/codeAction!saveOrUpdate.action', {
			'encodingRulesTableInTheMiddle.id' : $("#id").val(),
			'encodingRulesTableInTheMiddle.codeType' : $(":radio[name=codetype][checked]").val(),
			'encodingRulesTableInTheMiddle.billType' : $("#billType").val(),
			'encodingRulesTableInTheMiddle.codeLength' : $("#codeLength").val(),
			'encodingRulesTableInTheMiddle.level1value' : $("#level1value").val(),
			'encodingRulesTableInTheMiddle.level2value' : $("#level2value").val(),
			'encodingRulesTableInTheMiddle.level3value' : $("#level3value").val(),
			'encodingRulesTableInTheMiddle.level4value' : $("#level4value").val(),
			'encodingRulesTableInTheMiddle.level5value' : $("#level5value").val(),
			'encodingRulesTableInTheMiddle.level6value' : $("#level6value").val(),
			'encodingRulesTableInTheMiddle.level7value' : $("#level7value").val(),
			'encodingRulesTableInTheMiddle.level8value' : $("#level8value").val(),
			'encodingRulesTableInTheMiddle.level9value' : $("#level9value").val(),
			'encodingRulesTableInTheMiddle.level10value' : $("#level10value").val(),
			'encodingRulesTableInTheMiddle.level11value' : $("#level11value").val(),
			'encodingRulesTableInTheMiddle.level12value' : $("#level12value").val(),
			'encodingRulesTableInTheMiddle.serialNumberEnd' : $("#serialNumberEnd").val(),
			'encodingRulesTableInTheMiddle.serialNumberStep' : $("#serialNumberStep").val(),
			'encodingRulesTableInTheMiddle.serialNumberBegin' : $("#serialNumberBegin").val(),
			'encodingRulesTableInTheMiddle.islevel1' : $(":checkbox[name=islevel1][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel2' : $(":checkbox[name=islevel2][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel3' : $(":checkbox[name=islevel3][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel4' : $(":checkbox[name=islevel4][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel5' : $(":checkbox[name=islevel5][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel6' : $(":checkbox[name=islevel6][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel7' : $(":checkbox[name=islevel7][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel8' : $(":checkbox[name=islevel8][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel9' : $(":checkbox[name=islevel9][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel10' : $(":checkbox[name=islevel10][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel11' : $(":checkbox[name=islevel11][checked]").val(),
			'encodingRulesTableInTheMiddle.islevel12' : $(":checkbox[name=islevel12][checked]").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$.ajax({
				url : '${vix}/system/codeAction!goSaveOrUpdate.action?billTypeCode=' + $("#billTypeCode").val(),
				cache : false,
				success : function(html) {
					$("#right").html(html);
					checkedradio();
				}
				});
			});
		}
	};
	/* 载入内容区 */
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/system/codeAction!goSaveOrUpdate.action',
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
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> <s:text name="system_control" /> </a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">编码管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<input type="hidden" id="billTypeCode" name="billTypeCode" value="${billTypeCode}" />
<div class="content">
	<form id="baseCoderForm">
		<!-- c_head -->
		<div class="box">
			<div id="left" class="ui-resizable" style="height: 750px;">
				<div id="switch_box" class="switch_btn"></div>
				<div class="left_content" style="height: 750px; overflow: auto;">
					<div id="tree_root" class="ztree" style="padding: 0; padding-top: 10px;"></div>
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
						url : '${vix}/system/codeAction!goSaveOrUpdate.action?billTypeCode=' + treeNode.code,
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
			<!-- right -->
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
	</form>
	<!-- c_foot -->
</div>