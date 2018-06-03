<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="wxpRoleForm" class="form-horizontal" style="padding: 20px;" action="">
	<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="selectBizType" name="selectBizType" />
	<fieldset>
		<div class="form-group" style="margin-bottom: 5px;">
			<label class="col-md-2 control-label">权限类别:</label>
			<div class="col-md-3">
				<div class="input-group">
					<select id="authorityBizType" class="form-control" onchange="initAuthorityTree();">
						<s:iterator value="bizTypeMap">
							<option value="${key}">${value}</option>
						</s:iterator>
					</select>
				</div>
			</div>
		</div>
		<div class="jarviswidget jarviswidget-color-white">
			<header style="height: 0;"></header>
			<div class="widget-body">
				<ul id="tree_root" class="ztree"></ul>
				<input type="hidden" id="addAuthorityIds" name="addAuthorityIds" value="" /> <input type="hidden" id="deleteAuthorityIds" name="deleteAuthorityIds" value="" />
				<div id="categoryFun" class="table" style="width: 100%; display: none">
					<table id="dgAuthFun"></table>
				</div>
			</div>
		</div>
	</fieldset>
</form>


<script type="text/javascript">
	var zTreeAuthority;

	function initAuthorityTree() {
		var bizTypeTmp = $("#authorityBizType").val();
		var treeNodeTmp = [];
		$.ajax({
		url : "${nvix}/nvixnt/nvixntRoleAction!findAuthorityTreeData.action",
		data : {
		bizType : bizTypeTmp,
		roleId : '${id}'
		},
		cache : false,
		async : false,
		dataType : "text",
		success : function(data) {
			if (data != "") {
				treeNodeTmp = $.parseJSON(data).authdata;
			}
		}
		});
		var setting = {
		view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom
		},
		check : {
		enable : true,
		checkType : {
		"Y" : "ps",
		"N" : "ps"
		}
		},
		callback : {
			onClick : onClick
		}
		};
		function addHoverDom(treeId, treeNode) {
			var aObj = $("#" + treeNode.tId + "_a");
			if ($("#auTreeDiv_" + treeNode.id).length > 0)
				return;
			var editStr = "<span id='auTreeDiv_space_" +treeNode.id+ "' title='授权所有子节点' style='margin:20px;'>授权</span>" + "<span id='auTreeDiv_" + treeNode.id + "' title='取消所有子节点' onfocus='this.blur();' style='margin:10px;'>取消</button>";
			aObj.append(editStr);

			//授权
			var btn = $("#auTreeDiv_space_" + treeNode.id);
			if (btn) {
				btn.bind("click", function() {
					updateEntityByConfirm('${nvix}/nvixnt/nvixntRoleAction!saveForAuthorityByAuthId.action?roleId=${id}' + '&bizType=' + bizTypeTmp + '&authId=' + treeNode.id, '确定要授权吗?', wxpRoleTable, "", function() {
						zTreeAuthority.checkNode(treeNode, true, true, false);
						var node = treeNode.getParentNode();
						if (node != null) {
							getParentNodes(node, bizTypeTmp);
						}
					});

				});
			}

			//取消授权
			var btn = $("#auTreeDiv_" + treeNode.id);
			if (btn) {
				btn.bind("click", function() {
					updateEntityByConfirm('${nvix}/nvixnt/nvixntRoleAction!deleteForAuthorityByAuthId.action?roleId=${id}' + '&bizType=' + bizTypeTmp + '&authId=' + treeNode.id, '确定要取消授权吗?', wxpRoleTable, "", function() {
						zTreeAuthority.checkNode(treeNode, false, true, false);
					});
				});
			}
		}
		;
		function removeHoverDom(treeId, treeNode) {
			$("#auTreeDiv_" + treeNode.id).unbind().remove();
			$("#auTreeDiv_space_" + treeNode.id).unbind().remove();
		}
		;

		function onClick(event, treeId, treeNode, clickFlag) {
			$("#selectId").val(treeNode.id);
			//loadAuthorityFunGrid(treeNode.id);
		}
		;
		zTreeAuthority = $.fn.zTree.init($("#tree_root"), setting, treeNodeTmp);
		//loadAuthorityFunGrid(-1);
	};

	function getParentNodes(node, bizTypeTmp) {
		if (node != null) {
			$.ajax({
			url : '${nvix}/nvixnt/nvixntRoleAction!saveForAuthorityByAuthIdOnly.action?roleId=${id}' + '&bizType=' + bizTypeTmp + '&authId=' + node.id,
			cache : false,
			success : function() {
			}
			});
			if (node.getParentNode() != null) {
				getParentNodes(node.getParentNode(), bizTypeTmp);
			}
		}
	}
	var initDgCheckedRow = null;
	//右侧的列表
	function loadAuthorityFunGrid(selectId) {
		initDgCheckedRow = new Array();
		$('#dgAuthFun').datagrid({
		width : 800,
		height : 250,
		singleSelect : false,
		nowrap : true,
		autoRowHeight : false,
		striped : true,
		collapsible : true,
		url : '${nvix}/nvixnt/nvixntRoleAction!findFunGrid.action',
		queryParams : {
		roleId : '${id}',
		authId : selectId,
		bizType : $("#authorityBizType").val()
		},
		remoteSort : false,
		idField : 'id',
		frozenColumns : [ [ {
		field : 'ck',
		checkbox : true
		}, {
		field : 'name',
		title : '名称 ',
		width : 300,
		editor : 'text'
		}, {
		field : 'memo',
		title : '说明',
		width : 400,
		editor : 'text'
		} ] ],
		pagination : false,
		rownumbers : true,
		onLoadSuccess : function(data) {
			var rowData = data.rows;
			$.each(rowData, function(index, value) {
				if (value.isCheck == "Y") {
					$("#dgAuthFun").datagrid("checkRow", index);
					initDgCheckedRow.push(value.id);
				}
			});
		}
		});
	};

	setTimeout(function() {
		initAuthorityTree();
	}, 0);
</script>