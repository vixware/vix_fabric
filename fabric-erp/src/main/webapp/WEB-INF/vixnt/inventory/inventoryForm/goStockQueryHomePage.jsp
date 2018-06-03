<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style type="text/css">
	.tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
	.tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
	.myHoverLine:hover{  /* 指定a标签鼠标移动上去加下划线 */
    border-bottom: 1px solid #000000;
    	color: #fff;
    text-decoration: none;
	}
	.myHoverLine {/* 指定a标签鼠标移出时，字体颜色为白色 */
	    color: #fff;
	}
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 库存仪表盘 </span>
			</h1>
		</div>
	</div>
	
			<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row" style="padding-top:20px;">
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#EF9178;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">现存物料SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">${existingGoodsSKUblock }</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月过期物料量</strong><br>      
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">${overdueThisMonthblock }</strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row"> 
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存不足物料SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">${insufficientGoodsSKUblock }</strong></a>    
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7074B9;">
										<div class="row">  
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存积压物料SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">${backlogGoodsSKUblock }</strong></a>    
											</div>
										</div>   
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5" id="todayInNum">0.00</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日入库物料总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日入库 <strong class="txt-color-pink" id="ytdayInNum">0.00</strong> 环比 <strong class="txt-color-pink" id="iclasspertodayInNum"><i class="fa fa-arrow-up tmyColorB "></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5" id="todayOutNum">0.00</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日出库物料总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日出库 <strong class="txt-color-pink" id="ytdayOutNum">0.00</strong> 环比 <strong class="txt-color-pink" id="iclasspertodayOutNum"><i class="fa fa-arrow-up tmyColorB "></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">30319</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日领料物料总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日领料<strong class="txt-color-pink"> 0</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">3位</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日领料人</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日领料<strong class="txt-color-pink"> 0位</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
						</div>
					</div>
				</div>
				
	<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日物料入库趋势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadStockHomePageInView"> <!-- 慢加载:最近30日物料入库趋势图 -->
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>			
		
		<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日物料出库趋势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadStockHomePageOutView">   <!-- 慢加载:最近30日物料出库趋势图 -->
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>		
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>最近30日入库单据</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							创建人: <input type="text" value="" class="form-control" id="personIn" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="listGoodsInSqoqmTable.ajax.reload();">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#personIn').val('');listGoodsInSqoqmTable.ajax.reload();">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsInSqoqmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>最近30日出库单据</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							创建人: <input type="text" value="" class="form-control" id="personOut" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="listGoodsOutTable.ajax.reload();">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#personOut').val('');listGoodsOutTable.ajax.reload();">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsOutTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>本月过期物料列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							物料名称: <input type="text" value="" class="form-control" id="mynameOverdue" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="listGoodsOverdueTable.ajax.reload();"  >
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#mynameOverdue').val('');listGoodsOverdueTable.ajax.reload();">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsOverdueTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>库存不足物料列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							物料名称: <input type="text" value="" class="form-control" id="mynameLess" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="goodsLessTable.ajax.reload();" >
							<i class="glyphicon glyphicon-search"></i> 检索 
						</button>
						<button type="button" class="btn btn-default" onclick="$('#mynameLess').val('');goodsLessTable.ajax.reload();">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="goodsLessTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>库存积压物料列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							物料: <input type="text" value="" class="form-control" id="mynameMore" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="goodsMoreTable.ajax.reload();" >
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#mynameMore').val('');goodsMoreTable.ajax.reload();">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="goodsMoreTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日物料入库趋势图   **/
function slowLoadStockHomePageInView(){
	$.ajax({  
		url: "${nvix}/nvixnt/vixntStockQueryHomePageAction!slowLoadStockHomePageInView.action", 
		type: "POST",
		success : function(html) {
			$("#slowLoadStockHomePageInView").html(html); 
		}
	});
}
/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日物料出库趋势图   **/
function slowLoadStockHomePageOutView(){
	$.ajax({  
		url: "${nvix}/nvixnt/vixntStockQueryHomePageAction!slowLoadStockHomePageOutView.action", 
		type: "POST",
		success : function(html) {
			$("#slowLoadStockHomePageOutView").html(html); 
		}
	});
}
$(document).ready(function() {
	pageSetUp();
	slowLoadStockHomePageInView();
	slowLoadStockHomePageOutView();
})
</script>

<script type="text/javascript">
	pageSetUp();
	var listGoodsInSqoqmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "单据编码",
	"data" : function(data) {
		return (data.code != null ? data.code : "" );
	}
	},{
	"title" : "主题",   
	"data" : function(data) {
		return (data.name != null ? data.name : "" );
	}
	},{
	"title" : "仓库名称",
	"data" : function(data) {
		return (data.warehouseName != null ? data.warehouseName : "" );
	}
	},{
	"title" : "创建人",
	"data" : function(data) {
		return (data.creator != null ? data.creator : "" );
	}
	},{
	"title" : "入库时间",
	"data" : function(data) {
		return (data.createTimeTimeStr != null ? data.createTimeTimeStr : "" );
	}
	} ]; 																								
	var listGoodsInSqoqmTable = initDataTable("listGoodsInSqoqmTableId", "${nvix}/nvixnt/vixntStockQueryHomePageAction!storageInTable.action", listGoodsInSqoqmColumns, function(page, pageSize, orderField, orderBy) {
		var personIn = $("#personIn").val();
		personIn = Url.encode(personIn);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"personIn" : personIn,
		};
	});
</script>
<script type="text/javascript">
	pageSetUp();
	var listGoodsOutColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "单据编码",
	"data" : function(data) {
		return (data.code != null ? data.code : "" );
	}
	},{
	"title" : "主题",   
	"data" : function(data) {
		return (data.name != null ? data.name : "" );
	}
	},{
	"title" : "仓库名称",
	"data" : function(data) {
		return (data.warehouseName != null ? data.warehouseName : "" );
	}
	},{
	"title" : "创建人",
	"data" : function(data) {
		return (data.creator != null ? data.creator : "" );
	}
	},{
	"title" : "入库时间",
	"data" : function(data) {
		return (data.createTimeTimeStr != null ? data.createTimeTimeStr : "" );
	}
	} ]; 																										
	var listGoodsOutTable = initDataTable("listGoodsOutTableId", "${nvix}/nvixnt/vixntStockQueryHomePageAction!storageOutTable.action", listGoodsOutColumns, function(page, pageSize, orderField, orderBy) {
		var personOut = $("#personOut").val();
		personOut = Url.encode(personOut);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"personOut" : personOut,
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var listGoodsOverdueColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "物料编码",
	"data" : function(data) {
		return (data.itemcode != null ? data.itemcode : "" );
	}
	},{
	"title" : "SKU编码",   
	"data" : function(data) {
		return (data.skuCode != null ? data.skuCode : "" );
	}
	},{
	"title" : "物料名称",
	"data" : function(data) {
		return (data.itemname != null ? data.itemname : "" );
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return (data.avaquantity != null ? Number(data.avaquantity).toFixed(2) : "" ); 
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return (data.price != null ? Number(data.price).toFixed(2) : "" ); 
	}
	},{
	"title" : "仓库",
	"data" : function(data) {
		return (data.warehouse != null ? data.warehouse : "" );
	}
	},{
	"title" : "到期日",
	"data" : function(data) {
		return (data.massunitEndTimeStr != null ? data.massunitEndTimeStr : "" );
	}
	} ]; 																								
	var listGoodsOverdueTable = initDataTable("listGoodsOverdueTableId", "${nvix}/nvixnt/vixntStockQueryHomePageAction!storageOverdueTable.action", listGoodsOverdueColumns, function(page, pageSize, orderField, orderBy) {
		var mynameOverdue = $("#mynameOverdue").val();
		mynameOverdue = Url.encode(mynameOverdue);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mynameOverdue" : mynameOverdue
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var goodsLessColumns = [ {
		"title" : "编号",
		"width" : "8%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "物料编码",
		"data" : function(data) {
			return (data.itemcode != null ? data.itemcode : "" );
		}
		},{
		"title" : "SKU编码",   
		"data" : function(data) {
			return (data.skuCode != null ? data.skuCode : "" );
		}
		},{
		"title" : "物料名称",
		"data" : function(data) {
			return (data.itemname != null ? data.itemname : "" );
		}
		},{
		"title" : "数量",
		"data" : function(data) {
			return (data.avaquantity != null ? Number(data.avaquantity).toFixed(2) : "" ); 
		}
		},{
		"title" : "单价",
		"data" : function(data) {
			return (data.price != null ? Number(data.price).toFixed(2) : "" ); 
		}
		},{
		"title" : "仓库",
		"data" : function(data) {
			return (data.warehouse != null ? data.warehouse : "" );
		}
		},{
		"title" : "到期日",
		"data" : function(data) {
			return (data.massunitEndTimeStr != null ? data.massunitEndTimeStr : "" );
		}
		},{
		"title" : "预警数量",
		"data" : function(data) {
			return "20.00";
		}
		} ]; 																										
	var goodsLessTable = initDataTable("goodsLessTableId", "${nvix}/nvixnt/vixntStockQueryHomePageAction!storageLessTable.action", goodsLessColumns, function(page, pageSize, orderField, orderBy) {
		var mynameLess = $("#mynameLess").val();
		mynameLess = Url.encode(mynameLess);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mynameLess" : mynameLess
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var goodsMoreColumns = [ {
		"title" : "编号",
		"width" : "8%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "物料编码",
		"data" : function(data) {
			return (data.itemcode != null ? data.itemcode : "" );
		}
		},{
		"title" : "SKU编码",   
		"data" : function(data) {
			return (data.skuCode != null ? data.skuCode : "" );
		}
		},{
		"title" : "物料名称",
		"data" : function(data) {
			return (data.itemname != null ? data.itemname : "" );
		}
		},{
		"title" : "数量",
		"data" : function(data) {
			return (data.avaquantity != null ? Number(data.avaquantity).toFixed(2) : "" ); 
		}
		},{
		"title" : "单价",
		"data" : function(data) {
			return (data.price != null ? Number(data.price).toFixed(2) : "" ); 
		}
		},{
		"title" : "仓库",
		"data" : function(data) {
			return (data.warehouse != null ? data.warehouse : "" );
		}
		},{
		"title" : "到期日",
		"data" : function(data) {
			return (data.massunitEndTimeStr != null ? data.massunitEndTimeStr : "" );
		}
		},{
		"title" : "预警数量",
		"data" : function(data) {
			return "200.00";
		}
		} ];																										
	var goodsMoreTable = initDataTable("goodsMoreTableId", "${nvix}/nvixnt/vixntStockQueryHomePageAction!storageMoreTable.action", goodsMoreColumns, function(page, pageSize, orderField, orderBy) {
		var mynameMore = $("#mynameMore").val();
		mynameMore = Url.encode(mynameMore);
		return {  
		"page" : page,
		"pageSize" : pageSize,
		"mynameMore" : mynameMore
		};
	});
</script>