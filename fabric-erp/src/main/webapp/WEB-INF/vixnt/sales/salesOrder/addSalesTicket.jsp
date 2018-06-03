<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesTicketForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdateSalesTicket.action" method="post" enctype="multipart/form-data">
	<input id="salesTicketId" name="salesTicket.id" value="${salesTicket.id }" type="hidden"/>
	<input id="salesOrderId" name="salesTicket.salesOrder.id" value="${salesTicket.salesOrder.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开票日期:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="planTicketDate" name="salesTicket.planTicketDate" value="${salesTicket.planTicketDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planTicketDate'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="title" name="salesTicket.title" value="${salesTicket.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">纳税人识别号:</label>
			<div class="col-md-4">
				<input id="taxpayerPlayer" name="salesTicket.taxpayerPlayer" value="${salesTicket.taxpayerPlayer}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label">开户行及帐号:</label>
			<div class="col-md-4">
				<input id="bank" name="salesTicket.bank" value="${salesTicket.bank}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">税率:</label>
			<div class="col-md-4">
				<input id="bankAccount" name="salesTicket.bankAccount" value="${salesTicket.bankAccount}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label">税额:</label>
			<div class="col-md-4">
				<input id="taxRate" name="salesTicket.taxRate" value="${salesTicket.taxRate}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发票金额:</label>
			<div class="col-md-4">
				<input id="ticketAmount" name="salesTicket.ticketAmount" value="${salesTicket.ticketAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开票数量:</label>
			<div class="col-md-4">
				<input id="ticketCount" name="salesTicket.ticketCount" value="${salesTicket.ticketCount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">描述:</label>
			<div class="col-md-4">
				<input id="memo" name="salesTicket.memo" value="${salesTicket.memo}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label">开票类型:</label>
			<div class="col-md-4">
				<input id="ticketType" name="salesTicket.ticketType" value="${salesTicket.ticketType}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">开票内容:</label>
			<div class="col-md-4">
				<input id="content" name="salesTicket.content" value="${salesTicket.content}" class="form-control" type="text"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否冻结:</label>
			<div class="col-md-3" data-toggle="buttons" class="btn-group">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="salesTicket.isFreeze == 1">active</s:if>">
						<input type="radio" value="1" id="isFreeze" name="salesTicket.isFreeze" <s:if test="salesTicket.isFreeze == 1">checked="checked"</s:if> class="validate[required]">是
					</label>
					<label class="btn btn-default <s:if test="salesTicket.isFreeze == 0">active</s:if>">
						<input type="radio" value="0" id="isFreeze" name="salesTicket.isFreeze" <s:if test="salesTicket.isFreeze == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>开票货品明细</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div style="margin:5px;">
							<div class="form-group" style="margin: 0 0px;">
								<div class="col-md-3">
									<input type="text" value="" placeholder="货品名称" class="form-control" id="searchSalesItemName">
								</div>
								<button onclick="salesTicketDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchSalesItemName').val('');salesTicketDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
								<div class="listMenu-float-right" >
									<button onclick="addSalesTicketDetail();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
									</button>
								</div>
							</div>
						</div>
					    <table id="salesTicketDetail" class="table table-striped table-bordered table-hover" width="100%">
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
	$("#salesTicketForm").validationEngine();
	
	var salesTicketDetailColumns = [
			{"orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"name":"saleOrderItem","data" : function(data) {return data.saleOrderItem == null ? '': data.saleOrderItem.itemName;}},
			{"name":"ticketItemCount","data" : function(data) {return data.ticketItemCount;}},
			{"orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateSalesTicketDetail('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
		 	}}
		];
	              	             	                         	             	
   	var salesTicketDetailTable = initDataTable("salesTicketDetail","${nvix}/nvixnt/nvixSalesOrderAction!getSalesTicketDetailJson.action",salesTicketDetailColumns,function(page,pageSize,orderField,orderBy){
   			var name = $('#searchSalesItemName').val();
   			name = Url.encode(name);
   			var id = $("#salesTicketId").val();
   			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','1');
   	
   	/** 检查主题是否保存的通用方法*/
	function addSalesTicketDetail(){
		var id = $("#salesTicketId").val();
		if(id == ''){
			if($('#salesTicketForm').validationEngine('validate')){
				$("#salesOrderId").val($("#id").val());
				$("#salesTicketForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							$("#salesTicketId").val($.trim(r[0]));
							saveOrUpdateSalesTicketDetail('0');
						}
					}
				});
			}
		}else {
			saveOrUpdateSalesTicketDetail('0');
		}
   	}
   	
   	/** 添加发票明细*/
   	function saveOrUpdateSalesTicketDetail(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixSalesOrderAction!addSalesTicketDetail.action?id=' + id,'salesTicketDetailForm','添加发票明细',720,240,salesTicketDetailTable,function(){
			$("#saleTicketId").val($("#salesTicketId").val());
		},function(){
			salesTicketDetailTable.ajax.reload();
		});
   	}
   	
  //删除发票明细
	function deleteDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesOrderAction!deleteSalesTicketDetailById.action?id='+id,'是否删除此条明细?',salesTicketDetailTable);
	}
	
</script>