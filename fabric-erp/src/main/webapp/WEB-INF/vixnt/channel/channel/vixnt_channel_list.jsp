<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-chain"></i> 渠道管理 <span>> 渠道列表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goChooseArea($('#selectId').val(),4,$('#selectTreeType').val());">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增渠道
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>渠道</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/channel/vixntChannelAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							channelTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>渠道列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input type="text" value="" class="form-control" id="selectName">
									</div>
									<button onclick="channelTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');channelTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="channelTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var channelColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"width" : "25%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "联系人",
	"name" : "contacts",
	"width" : "10%",
	"data" : function(data) {
		return data.contacts;
	}
	}, {
	"title" : "电话",
	"name" : "telephone",
	"width" : "10%",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-primary'>启用</span>";
		} else if (data.status == 1) {
			return "<span class='label label-info'>禁用</span>";
		}
		return "";
	}
	}, {
	"title" : "创建时间",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var employee = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateEmployee('" + data.id + "');\" title='创建管理员'><span class='txt-color-blue pull-right'><i class='fa fa-user'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red  pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + employee + "&nbsp;&nbsp;" + del;
	}
	} ];

	var channelTable = initDataTable("channelTableId", "${nvix}/nvixnt/channel/vixntChannelAction!goSingleList.action", channelColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"selectName" : selectName,
		"treeType" : treeType
		};
	}, 10, '0');
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/channel/vixntChannelAction!deleteById.action?id=' + id, '是否删除?', channelTable);
	};

	function goChooseArea(parentId, type, treeType) {
		if (treeType != '' && treeType != null && treeType == 'C') {
			openSaveOrUpdateWindow('${nvix}/nvixnt/channel/vixntChannelAction!goChooseArea.action?parentId=' + parentId + "&type=" + type + "&treeType=" + treeType, "vixntDistributionSystemForm", "选择区域", 500, 325, null, null, function(id) {
				var r = id.split(":");
				goSaveOrUpdate(r[0]);
			});
		} else {
			layer.alert("请选择上级公司!");
		}
	};

	function goSaveOrUpdate(id, parentId, type, treeType) {
		if (id != '' && id != null) {
			saveOrUpdate('${nvix}/nvixnt/channel/vixntChannelAction!goSaveOrUpdate.action?id=' + id);
		} else {
			if (treeType != '' && treeType != null && treeType == 'C') {
				saveOrUpdate('${nvix}/nvixnt/channel/vixntChannelAction!goSaveOrUpdate.action?id=' + id + "&parentId=" + parentId + "&type=" + type + "&treeType=" + treeType);
			} else {
				layer.alert("请选择上级公司!");
			}
		}
	};
	function saveOrUpdate(url) {
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goSaveOrUpdateEmployee(id) {
		$.ajax({
		url : '${nvix}/nvixnt/channel/vixntChannelAction!goSaveOrUpdateEmployee.action?parentId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>