<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
					<s:if test=" fromPage=='storeStockPageSvy' ">  
						<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店库存报表</span>
					</s:if>
					<input type="hidden" value="${changeSQL }" id="changeSQL"  />  
					<input type="hidden" value="${fromPage }" id="fromPage"  />
			</h1>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			  <s:if test="fromPage=='storeStockPageSvy'">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action');"> 
					<i class="fa fa-rotate-left"></i> 返回             
				</button>
			  </s:if>
			</div>
		</div>
	</div>
	
		<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品名称: <input type="text" value="" class="form-control" id="mytitleXC" style="width: 130px;" placeholder="请输入商品名称">
						</div>
						<div class="form-group">
							商品编码: <input type="text" value="" class="form-control" id="mynumXC" style="width: 130px;" placeholder="请输入商品编码">
						</div>
						<button onclick="listGoodsExistScodmTable.ajax.reload();"  type="button" class="btn btn-info" id="searchButtonD">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitleXC').val('');$('#mynumXC').val('');listGoodsExistScodmTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsExistScodmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div> 
	
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var listGoodsExistScodmColumns =  [ {
		"title" : "编号",
		"width" : "8%",
		"data" : function(data) {
			return "";
			}
		}, {
		"title" : "商品编码",
		"data" : function(data) {
			return data.itemcode;
		}
		}, {
		"title" : "SKU编码",
		"data" : function(data) {
			return data.skuCode;
		}
		}, {
		"title" : "商品名称",
		"data" : function(data) {
			return data.itemname;
		}
		}, {
		"title" : "数量",
		"data" : function(data) {
			return data.avaquantity;
		}
		}, {
		"title" : "价格",
		"data" : function(data) {
			return data.price;
		}
		}, {
		"title" : "仓库",
		"data" : function(data) {
			return data.warehouse;
		}
		}, {
		"title" : "货位",
		"data" : function(data) {  
			return data.invShelfName;
		}
		}, {
		"title" : "批次号",
		"data" : function(data) {  
			return data.batchcode;
		}
		}, {
		"title" : "到期日",
		"data" : function(data) {  
			return data.massunitEndTimeStr;
		}
		} ];  
	var listGoodsExistScodmTable = initDataTable("listGoodsExistScodmTableId", "${nvix}/nvixnt/vixntMemberManageDataAction!goGoodsDataAbgj.action", listGoodsExistScodmColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitleXC").val();//Exist 存在的
		mytitle = Url.encode(mytitle);
		var mynum = $("#mynumXC").val();
		mynum = Url.encode(mynum);
		var changeSQL = $("#changeSQL").val();  
		changeSQL = Url.encode(changeSQL);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"changeSQL" : changeSQL,
		"mynum" : mynum
		};
	});
</script>