<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" /> <input type="hidden" id="itemIds" name="itemIds" /> <input type="hidden" id="itemNames" name="itemNames" />
	<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 销售管理<span>>销售计划 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增
				</button>
				<button class="btn btn-primary" type="button" onclick="collectSalePlan();">
					<i class="glyphicon glyphicon-plus"></i>&nbsp; 汇总
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<!-- <article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>组织架构</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/nvixntCommissionResultAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							salePlanTable.ajax.reload();
							salePlansTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article> -->
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<ul class="nav nav-tabs pull-left in" id="">
							<li class="active"><a data-toggle="tab" href="#hr1"><span class="hidden-mobile hidden-tablet">待汇总</span></a></li>
							<!-- <li><a data-toggle="tab" href="#hr2"><span class="hidden-mobile hidden-tablet">已汇总</span></a></li> -->
						</ul>
						<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8 text-align-right" style="float:right;padding-right:0">
						<div class="col-md-3" style="float:right;padding-right:0">
							<div class="input-group">
								<input id="planDate" value="" class="form-control" onClick="WdatePicker({dateFmt:'yyyy'});" onchange="salePlanTable.ajax.reload();" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						</div>
					</header>
					<div class="tab-content">
						<div class="tab-pane active" id="hr1">
							<div class="widget-body no-padding">
								<div id="">
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											主题: <input type="text" value="" class="form-control" id="overselectname">
										</div>
										<button onclick="salePlanTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#overselectname').val('');$('#planDate').val('');salePlanTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="salePlanTableId" class="table table-striped table-bordered table-hover" width="100%">
								</table>
							</div>
						</div>
						<div class="tab-pane" id="hr2">
							<div class="widget-body no-padding">
								<div id="">
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											主题: <input type="text" value="" class="form-control" id="selectname">
										</div>
										<button onclick="salePlansTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#selectname').val('');salePlansTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="salePlansTableId" class="table table-striped table-bordered table-hover" width="100%">
								</table>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	$(function(){
		var myDate = new Date();
		$("#planDate").val(myDate.getFullYear());
	})
	var salePlansColumns = [ {
	"orderable" : false,
	"title":"编号",
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title":"物料名称",
	"width" : "14%",
	"data" : function(data) {
		return data.item == null ? "" : data.item.name;
	}
	}, {
	"title":"计划周期",
	"width" : "4%",
	"data" : function(data) {
		return data.cycle;
	}
	}, {
	"title":"01月",
	"width" : "4%",
	"data" : function(data) {
		return data.jan;
	}
	}, {
	"title":"02月",
	"width" : "4%",
	"data" : function(data) {
		return data.feb;
	}
	}, {
	"title":"03月",
	"width" : "4%",
	"data" : function(data) {
		return data.mar;
	}
	}, {
		"title":"04月",
	"width" : "4%",
	"data" : function(data) {
		return data.apr;
	}
	}, {
	"title":"05月",
	"width" : "4%",
	"data" : function(data) {
		return data.may;
	}
	}, {
	"title":"06月",
	"width" : "4%",
	"data" : function(data) {
		return data.jun;
	}
	}, {
	"title":"07月",
	"width" : "4%",
	"data" : function(data) {
		return data.jul;
	}
	}, {
	"title":"08月",
	"width" : "4%",
	"data" : function(data) {
		return data.aug;
	}
	}, {
	"title":"09月",
	"width" : "4%",
	"data" : function(data) {
		return data.sep;
	}
	}, {
	"title":"10月",
	"width" : "4%",
	"data" : function(data) {
		return data.oct;
	}
	}, {
	"title":"11月",
	"width" : "4%",
	"data" : function(data) {
		return data.nov;
	}
	}, {
	"title":"12月",
	"width" : "4%",
	"data" : function(data) {
		return data.december;
	}
	}, {
	"orderable" : false,
	"title":"操作",
	"width" : "11%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;" + show + "&nbsp;" + del;
		}
		return '';
	}
	} ];

	var salePlansTable = initDataTable("salePlansTableId", "${nvix}/nvixnt/nvixntSalePlanAction!goSingleListCollect.action", salePlansColumns, function(page, pageSize, orderField, orderBy) {
		/* var treeId = $("#selectId").val();
		treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType); */
		return {
		"page" : page,
		"pageSize" : pageSize,
		/* "parentId" : treeId,
		"treeType" : treeType */
		};
	}, 10, "2", "1");
	var salePlanColumns = [ {
	"orderable" : false,
	"title":"编号",
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title":"主题",
	"width" : "10%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title":"计划人",
	"width" : "10%",
	"data" : function(data) {
		return data.salesMan == null ? "" :data.salesMan.name;
	}
	}, {
	"title":"计划数量",
	"width" : "10%",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title":"计划金额",
	"width" : "10%",
	"data" : function(data) {
		return "￥"+data.acount;
	}
	}, {
		"title":"计划周期",
	"width" : "10%",
	"data" : function(data) {
		return data.planDate;
	}
	}, {
	"title":"状态",
	"width" : "10%",
	"data" : function(data) {
		 if (data.isCollect == '0') {
			return "<span class='label label-info'>待汇总</span>";
		} else if (data.isCollect == '1') {
			return "<span class='label label-primary'>已汇总</span>";
		} 
		return "";
	}
	}, {
	"orderable" : false,
	"title":"操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if (data.isCollect == "1") {
			return show + "&nbsp;" + del;
		}else{
			return update + "&nbsp;" + show + "&nbsp;" + del;
		}
	}
	} ];

	var salePlanTable = initDataTable("salePlanTableId", "${nvix}/nvixnt/nvixntSalePlanAction!goSingleList.action", salePlanColumns, function(page, pageSize, orderField, orderBy) {
		/* var treeId = $("#selectId").val(); */
		var planDate = $("#planDate").val();
		/* treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType); */
		return {
		"page" : page,
		"pageSize" : pageSize,
		/* "parentId" : treeId, */
		"planDate" : planDate,
		/* "treeType" : treeType */
		};
	}, 10, "2", "1");
	function saveOrUpdate(id) {
			var treeType = $("#selectTreeType").val();
			if(treeType == "C"){
				layer.alert("请选择部门!");
				return;
			}
			goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!goSaveOrUpdate.action?id=' + id+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val());
	}
	function show(id) {
			goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!show.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSalePlanAction!deleteById.action?id=' + id, '是否删除该计划?', salePlanTable);
	};
	
	//汇总计划
	function collectSalePlan(){
		$.ajax({
			url : "${nvix}/nvixnt/nvixntSalePlanAction!collectSalePlan.action?planDate=" + $("#planDate").val()+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val(),
			cache : false,
			success : function(result) {
				showMessage(result);
				salePlanTable.ajax.reload();
				salePlansTable.ajax.reload();
			}
			});
	}
</script>