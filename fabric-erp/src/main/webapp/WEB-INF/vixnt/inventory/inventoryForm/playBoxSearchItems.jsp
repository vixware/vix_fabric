<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-12" style="text-align: left; padding: 5px 5px;">
		<div id="" class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i>
				</span>
				<h2>物料列表</h2>
				<h2>已选中物料:<span style="color:#FF0033" id="boxqueryInvWarehousename">当前无选中物料,请选择物料!</span></h2>
				<input type="hidden" value="" id="boxqueryInvWarehouseid">
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								<input type="text" value="" placeholder="名称" class="form-control" id="searchProductName">
							</div>
							<div class="form-group">
								<input type="text" value="" placeholder="编号" class="form-control" id="searchProductCoder"> 
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
<button type="button" class="btn btn-default" onclick="$('#searchProductName').val('');$('#searchProductCoder').val('');$('#boxqueryInvWarehousename').html('当前无选中物料,请选择物料!');$('#boxqueryInvWarehouseid').val('');chooseEcProductTable.ajax.reload();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<button type="button" class="btn btn-success" onclick="mergeStockInDetail();">
											<i class="fa fa-save"></i> &nbsp; 确认
							</button>
						</form>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="40%">物料编码</th>
								<th width="60%">物料名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript">
	var chooseEcProductColumns = [ {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	} ];
	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntStockQueryStatisticsAction!goDetailSingleList.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchProductName").val();  
		name = Url.encode(name);
		var itemcode = $("#searchProductCoder").val();  
		itemcode = Url.encode(itemcode);  
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemcode" : itemcode,
		"itemname" : name
		};
	}, 10, '1', '0');
	var qa = "";var qb = "";var qc = "";var qd = "";var qe = "";
	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$('#boxqueryInvWarehousename').html(data.name);
			$('#boxqueryInvWarehouseid').val(data.code);
			qa=data.code;
			qb=data.name;	
			qc=data.measureUnit.name;	
			qd=data.specification;
		} 
	});
	function mergeStockInDetail() {
		if(goFixedPrice()){
			$("#itemCodeLook").html(( ( qa.length > 0 ) ? qa:'---' ));  
			$("#itemNameLook").html(( ( qb.length > 0 ) ? qb:'---' ));
			$("#measureUnitnameLook").html(( ( qc.length > 0 ) ? qc:'---' ));
			$("#specificationLook").html(( ( qd.length > 0 ) ? qd:'---' ));
			qCondition.supplierID=$('#boxqueryInvWarehouseid').val();
			$('#querySupplier').val(qCondition.supplierID);
			var loadIndex = layer.load(2);/* 打开遮盖层 */ 
			arrivalListTable.ajax.reload();
			lookAlreadySelectTime();
			layer.close(loadIndex);/* 关闭遮盖层 */
			parent.layer.closeAll();/* 关闭所有弹框 */
		}
	};
	/** 判断是否已经选中物料 **/
	function goFixedPrice() {
		var querySpplier = $('#boxqueryInvWarehouseid').val();
		if(!querySpplier){
			layer.alert("请点击选择物料!");
			return false;
		}else {
			return true;
		}
	};
</script>