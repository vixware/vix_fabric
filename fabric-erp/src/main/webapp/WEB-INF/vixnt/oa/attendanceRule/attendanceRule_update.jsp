<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div>
	<h6>
		考勤规则<small>（<span class="text-danger">*</span>表示必填）
		</small>
	</h6>
	<form id="basicRuleForm" class="form-horizontal" action="${nvix}/nvixnt/attendanceRuleSetAction!saveOrUpdate.action" method="post" enctype="multipart/form-data">
		<input type="hidden" id="id" name="aaRocord.id" value="${aaRocord.id}" />
		<div class="form-actions" style="margin-top: 15px;">
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp; 保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('oa_attendanceRuleSet', '${nvix}/nvixnt/attendanceRuleSetAction!goList.action')">返回</button>
				</div>
			</div>
		</div>
		<br />
		<fieldset>
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
				<div class="col-md-3">
					<input id="code" name="aaRocord.code" value="${aaRocord.code}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
				<div class="col-md-3">
					<input id="name" name="aaRocord.name" value="${aaRocord.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
				<div class="col-md-3">
					<div data-toggle="buttons" class="btn-group">
						<label class="btn btn-default <s:if test="aaRocord.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="aaRocord.isEnable" <s:if test="aaRocord.isEnable == 1">checked="checked"</s:if> class="validate[required]">启用
						</label> <label class="btn btn-default <s:if test="aaRocord.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="aaRocord.isEnable" <s:if test="aaRocord.isEnable == 0">checked="checked"</s:if>>禁用
						</label>
					</div>
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>考勤类型:</label>
				<div class="col-md-3">
					<select id="attRuleId" name="aaRocord.attRule.id" data-prompt-position="topLeft" class="form-control">
						<option value="">------请选择------</option>
						<c:forEach items="${attendanceRuleList}" var="entity">
							<option value="${entity.id}" <c:if test="${aaRocord.attRule.id == entity.id}">selected="selected"</c:if>>${entity.ruleType}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">创建人:</label>
				<div class="col-md-3">
					<input id="indexWord" name="aaRocord.indexWord" value="${aaRocord.indexWord}" class="form-control" type="text" />
				</div>
				<label class="col-md-2 control-label">创建时间:</label>
				<div class="col-md-3">
					<input id="trademark" name="aaRocord.trademark" value="${aaRocord.trademark}" class="form-control" type="text" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>考勤范围:</label>
				<div class="col-md-3">
					<input id="accountType" name="aaRocord.accountType" value="${aaRocord.accountType}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
				</div>
				<label class="col-md-2 control-label">备注:</label>
				<div class="col-md-3">
					<textarea rows="3" id="memo" name="aaRocord.memo" value="${aaRocord.memo}" class="form-control">${customerAccount.memo}</textarea>
				</div>
			</div>
			<br />
			<div class="form-group">
				<label class="col-md-1 control-label"></label>
				<div class="col-md-10">
					<div>
						<header style="height: 1px; border-color: #ddd !important"></header>
						<div id="salesOrderRightContent" class="widget-body no-padding">
							<div class="jarviswidget" id="salesOrderDetailTabs" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
								<header>
									<ul class="nav nav-tabs pull-left in">
										<li class="active"><a data-toggle="tab" href="#orderDetailTab" onclick="orderDetailTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">基本规则</span>
										</a></li>
										<li><a data-toggle="tab" href="#orderDeliveryPlanTab" onclick="orderDeliveryPlanTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">加班规则</span>
										</a></li>
										<li><a data-toggle="tab" href="#orderInvoiceTab" onclick="orderInvoiceTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">工作日设定</span>
										</a></li>
										<li><a data-toggle="tab" href="#accessories1Tab" onclick="accessories1Table.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">自动套班</span>
										</a></li>
										<li><a data-toggle="tab" href="#accessories2Tab" onclick="accessories2Table.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">计算方式</span>
										</a></li>
										<li><a data-toggle="tab" href="#accessories3Tab" onclick="accessories3Table.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">其他规则</span>
										</a></li>
									</ul>
								</header>
								<div class="widget-body" style="padding: 0;">
									<div class="tab-content">
										<div class="tab-pane no-padding active" id="orderDetailTab">
											<div id="orderDetailSearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="orderDetailSearchName" placeholder="" class="form-control" />
													</div>
													<button onclick="orderDetailTable.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('orderDetailSearchName',orderDetailTable);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('items');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="orderDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
										<div class="tab-pane no-padding" id="orderDeliveryPlanTab">
											<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="orderDeliveryPlanSearchName" placeholder="收货人" class="form-control" />
													</div>
													<button onclick="orderDeliveryPlanTable.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('orderDeliveryPlanSearchName',orderDeliveryPlanTable);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('plan');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="orderDeliveryPlan" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
										<div class="tab-pane no-padding" id="orderInvoiceTab">
											<div id="orderInvoiceSearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="orderInvoiceSearchName" placeholder="发票抬头" class="form-control" />
													</div>
													<button onclick="orderInvoiceTable.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('orderInvoiceSearchName',orderInvoiceTable);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('invoice');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="orderInvoice" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
										<div class="tab-pane no-padding" id="accessories1Tab">
											<div id="accessories1SearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="accessories1SearchName" placeholder="附件名称" class="form-control" />
													</div>
													<button onclick="accessories1Table.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('accessories1SearchName',accessories1Table);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('accessories1');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="accessories1" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
										<div class="tab-pane no-padding" id="accessories2Tab">
											<div id="accessories2SearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="accessories2SearchName" placeholder="附件名称" class="form-control" />
													</div>
													<button onclick="accessories2Table.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('accessories2SearchName',accessories2Table);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('accessories2');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="accessories2" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
										<div class="tab-pane no-padding" id="accessories3Tab">
											<div id="accessoriesSearchForm" style="margin: 5px;">
												<div class="form-group" style="margin: 0 5px;">
													<div class="col-md-3">
														<input type="text" value="" id="accessories3SearchName" placeholder="附件名称" class="form-control" />
													</div>
													<button onclick="accessories3Table.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="clearSearchCondition('accessories3SearchName',accessories3Table);" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
													<div class=" listMenu-float-right">
														<button onclick="checkSalesOrderisSave('accessories3');" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-plus"></i>&nbsp;
															<s:text name="add" />
														</button>
													</div>
												</div>
											</div>
											<table id="accessories3" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="form-actions" style="margin-top: 15px;">
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp; 保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('oa_attendanceRule','${nvix}/nvixnt/attendanceRuleSetAction!goList.action')">返回</button>
				</div>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#attSettingForm").validationEngine();
	
	var orderDetailColumns = [
			{"title":"上班提前时间","width":"13%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.barCode;}},
			{"title":"下班推迟时间","width":"13%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.skuCode;}},
			{"title":"刷卡间隔时间","width":"13%","name":"item.code","data" : function(data) {return data.item == null ? '' : data.item.code;}},
			{"title":"迟到允许时间","width":"13%","name":"item.name","data" : function(data) {return data.item == null ? '' : data.item.name;}},
			{"title":"早退允许时间","width":"13%","name":"specification","data" : function(data) {return data.specification;}},
			{"title":"迟到无效时间","width":"13%","data" : function(data) {return data.measureUnit == null ? '' : data.measureUnit;}},
			{"title":"早退无效时间","width":"13%","name":"amount","data" : function(data) {return data.amount;}},
			{"title":"操作","width":"9%","orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id + "','items');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "','items');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
   			}}
		];
	             	
   	var orderDetailTable = initDataTable("orderDetail","${nvix}/nvixnt/nvixSalesOrderAction!getSaleOrderItemJson.action",orderDetailColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderDetailSearchName').val();
   			name = Url.encode(name);
   	 		var id = $("#id").val();
   	 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','0');
	             	
	var orderDeliveryPlanColumns = [
			{"title":"工时加班倍率","width":"15%","orderable":false,"data" : function(data) {return '&nbsp;'}},
   			{"title":"提前上班时间","width":"15%","name":"salesOrder","data" : function(data) {return data.salesOrder == null ? '' : data.salesOrder.code;}},
   			{"title":"推迟下班时间","width":"15%","name":"deliveryTime","data" : function(data) {return data.deliveryTime;}},
   			{"title":"节假日允许加班时长","width":"15%","name":"country","data" : function(data) {return data.country;}},
   			{"title":"日常加班允许时长","width":"15%","name":"province","data" : function(data) {return data.province;}},
   			//{"title":"城市","width":"10%","name":"city","data" : function(data) {return data.city;}},
   			//{"title":"目的地","width":"15%","name":"target","data" : function(data) {return data.target;}},
   			{"title":"操作","width":"15%","orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id +"','plan');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id +"','plan');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
   			}}
		];
	             	             	
	var orderDeliveryPlanTable = initDataTable("orderDeliveryPlan","${nvix}/nvixnt/nvixSalesOrderAction!getSalesDeliveryPlanJson.action",orderDeliveryPlanColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderDeliveryPlanSearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	                	
	var orderInvoiceColumns = [
			{"title":"星期日","width":"8%","orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"title":"星期一","width":"8%","name":"title","data" : function(data) {return data.title;}},
			{"title":"星期二","width":"8%","name":"taxpayerPlayer","data" : function(data) {return data.taxpayerPlayer;}},
			{"title":"星期三","width":"8%","name":"ticketAmount","data" : function(data) {return data.ticketAmount;}},
			{"title":"星期四","width":"8%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"星期五","width":"8%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"星期六","width":"8%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"休息日","width":"8%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"工作日标准工作时长","width":"14%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"工作日最小工作时长","width":"14%","name":"ticketCount","data" : function(data) {return data.ticketCount;}},
			{"title":"操作","width":"8%","orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id + "','invoice');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('"+data.id +"','invoice');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
   			}}
		];
	                        	             	
	var orderInvoiceTable = initDataTable("orderInvoice","${nvix}/nvixnt/nvixSalesOrderAction!getSalesTicketJson.action",orderInvoiceColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderInvoiceSearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	             	
	var accessories1Columns = [
			{"title":"自动套班","width":"15%","orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"title":"计算请假与缺勤","width":"15%","name":"name","data" : function(data) {return "<a href='###' onclick=\"obShowInvoiceBill('" + data.id + "');\">"+data.code+"</a>";}},
			{"title":"回溯最佳刷卡记录","width":"15%","name":"type","data" : function(data) {return data.type;}},
			{"title":"匹配最佳班次","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"排班错误使用自动套班","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"自动匹配所有班次","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"操作","width":"10%","orderable":false,"data" : function(data) {
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
   			}}
		];
	             	                         	             	
	var accessories1Table = initDataTable("accessories1","${nvix}/nvixnt/nvixSalesOrderAction!getAttachFileJson.action",accessories1Columns,function(page,pageSize,orderField,orderBy){
			var name = $('#accessories1SearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	
	var accessories2Columns = [
		{"title":"工时计算取整","width":"15%","orderable":false,"data" : function(data) {return '&nbsp;'}},
		{"title":"加班计算取整","width":"15%","name":"name","data" : function(data) {return "<a href='###' onclick=\"obShowInvoiceBill('" + data.id + "');\">"+data.code+"</a>";}},
		{"title":"迟到早退取整","width":"15%","name":"type","data" : function(data) {return data.type;}},
		{"title":"请假计算取整","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
		{"title":"计算保留小数","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
		{"title":"时段工时有效时长","width":"15%","name":"memo","data" : function(data) {return data.memo;}},
		{"title":"操作","width":"10%","orderable":false,"data" : function(data) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	              	             	                         	             	
	var accessories2Table = initDataTable("accessories2","${nvix}/nvixnt/nvixSalesOrderAction!getAttachFileJson.action",accessories2Columns,function(page,pageSize,orderField,orderBy){
		var name = $('#accessories2SearchName').val();
		name = Url.encode(name);
		var id = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
	},10,'1','1');
	              	
	var accessories3Columns = [
			{"title":"外出起算时间","width":"5%","orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"title":"请假起算时间","width":"30%","name":"name","data" : function(data) {return "<a href='###' onclick=\"obShowInvoiceBill('" + data.id + "');\">"+data.code+"</a>";}},
			{"title":"中班计算时间","width":"20%","name":"type","data" : function(data) {return data.type;}},
			{"title":"夜班计算时间","width":"30%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"允许中班夜班重复计数","width":"30%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"白班时间","width":"30%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"中班时间","width":"30%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"夜班时间","width":"30%","name":"memo","data" : function(data) {return data.memo;}},
			{"title":"操作","width":"15%","orderable":false,"data" : function(data) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateTables('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	              	            	             	                         	             	
	var accessories3Table = initDataTable("accessories3","${nvix}/nvixnt/nvixSalesOrderAction!getAttachFileJson.action",accessories3Columns,function(page,pageSize,orderField,orderBy){
		var name = $('#accessories3SearchName').val();
		name = Url.encode(name);
		var id = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
	},10,'1','1');
	              	            	
	
	function saveOrUpdate(id){
		//表单验证
		if($("#basicRuleForm").validationEngine('validate')){
			$("#basicRuleForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/attendanceRuleSetAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					var r = result.split(":");
					showMessage(r[1]);
					loadContent('oa_attendanceRuleSet','${nvix}/nvixnt/attendanceRuleSetAction!goList.action');
				}
			});
		}
	}
	
	/** 检查主题是否保存的通用方法*/
	function checkSalesOrderisSave(types){
		var id = $("#id").val();
		if(id == ''){
			if($('#attSettingForm').validationEngine('validate')){
				$("#attSettingForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							$("#id").val($.trim(r[0]));
							id = $("#id").val();
							if(types == 'items'){
								addRuleDetail('0');
							}else if(types == 'plan'){
								addOverTime('0');
							}else if(types == 'invoice'){
								addWorkDay('0');
							}else if(types == 'accessories1'){
								addAutomatic('0');
							}else if(types == 'accessories2'){
								addCalculation('0');
							}else if(types == 'accessories3'){
								addOther('0');
							}
						}
					}
				});
			}
		}else {
			if(types == 'items'){
				addRuleDetail('0');
			}else if(types == 'plan'){
				addOverTime('0');
			}else if(types == 'invoice'){
				addWorkDay('0');
			}else if(types == 'accessories1'){
				addAutomatic('0');
			}else if(types == 'accessories2'){
				addCalculation('0');
			}else if(types == 'accessories3'){
				addOther('0');
			}
		}
	}
	
	/** 基本规则*/
	function addRuleDetail(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addRuleDetail.action?id=' + id,'ruleDetailForm','基本规则',1000,820,orderDetailTable,null,function(){
			orderDetailTable.ajax.reload();
		});
	}
	
	/** 加班规则*/
	/* function addOverTime(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addOverTime.action?id=' + id,'overTimeForm','加班规则',1000,700,orderDeliveryPlanTable,null,function(){
			orderDeliveryPlanTable.ajax.reload();
		});
	} */
	
	/** 工作日设定 */
	function addWorkDay(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addWorkDay.action?id=' + id,'workDayForm','工作日设定',1000,720,orderInvoiceTable,null,function(){
			orderInvoiceTable.ajax.reload();
		});
	}
	
	/** 自动套班*/
	function addAutomatic(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addAutomatic.action?id=' + id,'automaticForm','自动套班',750,415,accessories1Table,null,function(){
			accessories1Table.ajax.reload();
		});
	}
	
	/** 计算方式*/
	function addCalculation(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addCalculation.action?id=' + id,'calculationForm','计算方式',880,710,accessories2Table,null,function(){
			accessories2Table.ajax.reload();
		});
	}
	
	/** 其他设定*/
	function addOther(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleAction!addOther.action?id=' + id,'otherForm','其他设定',950,710,accessories3Table,null,function(){
			accessories3Table.ajax.reload();
		});
	}

	//更新
	function saveOrUpdateTables(id,types){
		if(types == 'items'){
			addSalesOrderItem(id);
		}else if(types == 'plan'){
			addDeliveryPlan(id);
		}else if(types == 'invoice'){
			addSalesTicket(id);
		}else if(types == 'accessories'){
			addSalesAttachFile(id);
		} 
	}
	
	//删除
	function deleteById(id,types){
		if(types == 'items'){
			deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleAction!deleteSalesOrderItemById.action?id='+id,'是否删除此条明细?',orderDetailTable);
		}else if(types == 'plan'){
			deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleAction!deleteSalesOrderDeliveryPlanById.action?id='+id,'是否删除发运计划?',orderDeliveryPlanTable);
		}else if(types == 'invoice'){
			deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleAction!deleteSalesTicketById.action?id='+id,'是否删除发票信息?',orderInvoiceTable);
		}else if(types == 'accessories'){
			deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleAction!deleteSalesAttachFileById.action?id='+id,'是否删除此附件?',accessoriesTable);
		}
	}
	
	// 修改留痕
	var updateField = "";
	function salesOrderFieldChanged(input){
		updateField+= $(input).attr("id")+",";
	}
</script>