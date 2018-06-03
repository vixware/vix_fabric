<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		if (name != undefined) {
			name = Url.encode(name);
			name = Url.encode(name);
		}
	}
	$("#baseCoderForm").validationEngine();
	/** 保存数据 */
	function saveOrUpdate(selectId) {
		if ($('#baseCoderForm').validationEngine('validate')) {
			$.post('${vix}/system/operatingParametersAction!saveOrUpdate.action', {
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
				loadContent('${vix}/system/operatingParametersAction!goList.action');
			});
		}
	};
	loadName();
	function loadRoot() {
		$('#name').val("");
		pager("start", "${vix}/system/operatingParametersAction!goSingleList.action?name=" + name, 'baseCoder');
	}
	bindSearch();
	bindSwitch();
	function changeDisplay() {
		var divText = $("#lb_search_advanced").text();
		if (divText == "高级搜索") {
			$("#nameS").attr({
				disabled : 'true'
			});
		} else {
			$("#nameS").removeAttr("disabled");
		}
	}
	function resetForContent(tag) {
		if (tag == 0) {
			$("#nameS").val("");
		} else {
			$("#outcodes").val("");
			$("#departmentcodes").val("");
		}
	}

	$(document).ready(function() {
		$.ajax({
		url : '${vix}/system/operatingParametersAction!goSingleList.action',
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
				<li><a href="#">运行参数</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="baseCoderForm">
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
					url : "${vix}/system/operatingParametersAction!findTreeToJson.action",
					autoParam : [ "id", "treeType" ]
					},
					callback : {
						onClick : onClick
					}
					};
					function onClick(event, treeId, treeNode, clickFlag) {
						$("#selectId").val(treeNode.id);
						$.ajax({
						url : '${vix}/system/operatingParametersAction!goSaveOrUpdate.action?organizationId=' + treeNode.id,
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
	</form>
</div>