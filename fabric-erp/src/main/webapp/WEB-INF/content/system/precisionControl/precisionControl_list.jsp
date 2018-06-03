<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#baseCoderForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate(selectId) {
		if ($("#selectId").val() != null && $("#selectId").val() != '') {
			if ($('#baseCoderForm').validationEngine('validate')) {
				$.post('${vix}/system/precisionControlAction!saveOrUpdate.action', {
				'precisionControl.id' : $("#id").val(),
				'organizationId' : $("#selectId").val(),
				'precisionControl.stockSizes' : $("#stockSizes").val(),
				'precisionControl.stockPrice' : $("#stockPrice").val(),
				'precisionControl.noteTheUnitPrice' : $("#noteTheUnitPrice").val(),
				'precisionControl.amount' : $("#amount").val(),
				'precisionControl.conversiorate' : $("#conversiorate").val(),
				'precisionControl.stockWeightDecimal' : $("#stockWeightDecimal").val(),
				'precisionControl.stockDimensionDecimal' : $("#stockDimensionDecimal").val(),
				'precisionControl.amountDecimal' : $("#amountDecimal").val(),
				'precisionControl.taxRate' : $("#taxRate").val()
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
					$.ajax({
					url : '${vix}/system/precisionControlAction!goSaveOrUpdate.action?organizationId=' + selectId,
					cache : false,
					success : function(html) {
						$("#right").html(html);
					}
					});
				});
			} else {
				return false;
			}
		} else {
			asyncbox.success("请选择公司!", "提示信息");
		}
	};
	function reset() {
		$("#stockSizes").val("");
		$("#stockPrice").val("");
		$("#noteTheUnitPrice").val("");
		$("#amount").val("");
		$("#conversiorate").val("");
		$("#stockWeightDecimal").val("");
		$("#stockDimensionDecimal").val("");
		$("#amountDecimal").val("");
		$("#taxRate").val("");
	};
	function loadRoot() {
		pager("start", "${vix}/system/precisionControlAction!goSingleList.action?1=1", 'baseCoder');
	}

	$(document).ready(function() {
		$.ajax({
		url : '${vix}/system/precisionControlAction!goSaveOrUpdate.action',
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
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">数据精度管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<!-- c_head -->
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<!-- <b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div> -->
		</div>
		<div></div>
	</div>
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
						$("#right").html(html);
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
</div>