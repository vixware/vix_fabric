<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');

	/* 载入内容区 */
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/inventory/reorderPointAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#right").html(html);
		}
		});
	});

	$("#reorderPointForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#reorderPointForm').validationEngine('validate')) {
			$.post('${vix}/inventory/reorderPointAction!saveOrUpdate.action', {
			'reorderPoint.id' : $("#reorderPointId").val(),
			'reorderPoint.item.id' : $("#itemId").val(),
			'reorderPoint.normalDeliveryDays' : $("#normalDeliveryDays").val(),
			'reorderPoint.daySales' : $("#daySales").val(),
			'reorderPoint.safetyStock' : $("#safetyStock").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$.ajax({
				url : '${vix}/inventory/reorderPointAction!goSaveOrUpdate.action?itemId=' + $("#itemId").val(),
				cache : false,
				success : function(html) {
					$("#right").html(html);
				}
				});
			});
		}
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_rop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">ROP再订货点</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="reorderPointForm">
		<div class="drop" id="c_head">
			<span class="left_bg"></span> <span class="right_bg"></span>
			<div class="untitled">
				<b><img alt="" src="img/icon_11.png"> </b>
				<div class="popup">
					<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
					</strong>
					<p>ROP再订货点</p>
				</div>
			</div>
		</div>
		<!-- c_head -->
		<div class="box">
			<div id="left" class="ui-resizable" style="height: 500px;">
				<div id="switch_box" class="switch_btn"></div>
				<div class="left_content" style="height: 500px; overflow: auto;">
					<div id="tree_root" class="ztree" style="padding: 0;"></div>
				</div>
				<script type="text/javascript">
					var zTree;
					var setting = {
					async : {
					enable : true,
					url : "${vix}/inventory/batchAction!findItemCatalogAndItemTreeToJson.action",
					autoParam : [ "id", "treeType" ]
					},
					callback : {
						onClick : onClick
					}
					};
					function onClick(event, treeId, treeNode, clickFlag) {
						$.ajax({
						url : '${vix}/inventory/reorderPointAction!goSaveOrUpdate.action?itemId=' + treeNode.id + "&treeType=" + treeNode.treeType,
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
			<!-- left -->
			<div id="right"></div>
			<!-- right -->
		</div>
	</form>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>