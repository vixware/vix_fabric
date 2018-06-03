<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesDeliveryPlanForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdateDeliveryPlan.action" method="post" enctype="multipart/form-data">
	<input id="deliveryPlanId" name="salesDeliveryPlan.id" value="${salesDeliveryPlan.id }" type="hidden"/>
	<input id="salesOrderId" name="salesDeliveryPlan.salesOrder.id" value="${salesDeliveryPlan.salesOrder.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>收货人:</label>
			<div class="col-md-4">
				<input id="reciever" name="salesDeliveryPlan.reciever" value="${salesDeliveryPlan.reciever}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
			<label class="col-md-2 control-label">承运人:</label>
			<div class="col-md-4">
				<input id="carrier" name="salesDeliveryPlan.carrier" value="${salesDeliveryPlan.carrier}" data-prompt-position="topLeft" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">国家:</label>
			<div class="col-md-4">
				<input id="country" name="salesDeliveryPlan.country" value="${salesDeliveryPlan.country}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label">省:</label>
			<div class="col-md-4">
				<input id="province" name="salesDeliveryPlan.province" value="${salesDeliveryPlan.province}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">城市:</label>
			<div class="col-md-4">
				<input id="city" name="salesDeliveryPlan.city" value="${salesDeliveryPlan.city}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>地址:</label>
			<div class="col-md-4">
				<input id="target" name="salesDeliveryPlan.target" value="${salesDeliveryPlan.target}" class="form-control validate[required]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发运时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="deliveryTime" name="salesDeliveryPlan.deliveryTime" value="${salesDeliveryPlan.deliveryTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'deliveryTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>发运计划明细</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div style="margin:5px;">
							<div class="form-group" style="margin: 0 0px;">
								<div class="col-md-3">
									<input type="text" value="" placeholder="货品名称" class="form-control" id="searchSalesItemName">
								</div>
								<button onclick="salesDeliveryPlanDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchSalesItemName').val('');salesDeliveryPlanDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
								<div class="listMenu-float-right" >
									<button onclick="addSalesDeliveryPlanDetail();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
									</button>
								</div>
							</div>
						</div>
					    <table id="salesDeliveryPlanDetail" class="table table-striped table-bordered table-hover" width="100%">
					   		<thead>			                
								<tr>
									<th width="10%">编号</th>
									<th width="50%">订单子项(货品名称)</th>
									<th width="20%">数量</th>
									<th width="20%">操作</th>
								</tr>
							</thead>
					    </table>
				 	</div>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#salesDeliveryPlanForm").validationEngine();
	
	var salesDeliveryPlanDetailColumns = [
			{"orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"name":"saleOrderItem","data" : function(data) {return data.saleOrderItem == null ? '': data.saleOrderItem.itemName;}},
			{"name":"deliveryPlanItemCount","data" : function(data) {return data.deliveryPlanItemCount;}},
			{"orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateSalesDeliveryPlanDetail('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
		 			}}
		];
	              	             	                         	             	
   	var salesDeliveryPlanDetailTable = initDataTable("salesDeliveryPlanDetail","${nvix}/nvixnt/nvixSalesOrderAction!getSalesDeliveryPlanDetailJson.action",salesDeliveryPlanDetailColumns,function(page,pageSize,orderField,orderBy){
   			var name = $('#searchSalesItemName').val();
   			name = Url.encode(name);
   			var id = $("#deliveryPlanId").val();
   			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','1');
   	
   	/** 检查主题是否保存的通用方法*/
	function addSalesDeliveryPlanDetail(){
		var id = $("#deliveryPlanId").val();
		if(id == ''){
			if($('#salesDeliveryPlanForm').validationEngine('validate')){
				$("#salesOrderId").val($("#id").val());
				$("#salesDeliveryPlanForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							$("#deliveryPlanId").val($.trim(r[0]));
							saveOrUpdateSalesDeliveryPlanDetail('0');
						}
					}
				});
			}
		}else {
			saveOrUpdateSalesDeliveryPlanDetail('0');
		}
   	}
   	
   	/** 添加发运计划明细*/
   	function saveOrUpdateSalesDeliveryPlanDetail(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixSalesOrderAction!addSalesDeliveryPlanDetail.action?id=' + id,'salesDeliveryPlanDetailForm','发运计划明细',720,240,salesDeliveryPlanDetailTable,function(){
			$("#salesDeliveryPlanId").val($("#deliveryPlanId").val());
		},function(){
			salesDeliveryPlanDetailTable.ajax.reload();
		});
   	}
   	
  //删除
	function deleteDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesOrderAction!deleteSalesOrderDeliveryPlanDetailById.action?id='+id,'是否删除此条明细?',salesDeliveryPlanDetailTable);
	}
	
</script>