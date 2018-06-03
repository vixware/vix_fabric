<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt;报价单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdateSalesQuotation('');">
					<i class="fa fa-save"></i> &nbsp; 保存
				</button>
				<s:if test="isAllowAudit == 1">
					<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
						<i class="fa fa-save"></i> &nbsp; 提交审批
					</button>
				</s:if>
				<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesQuotationAction!goList.action');">
					<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>报价单</h2>
		</header>
		<div class="widget-body">
			<form id="salesQuotationForm" class="form-horizontal" action="${nvix}/nvixnt/nvixSalesQuotationAction!saveOrUpdate.action">
				<input type="hidden" id="id" name="salesQuotation.id" value="${salesQuotation.id}"/>
				<input type="hidden" id="isDeleted" name="salesQuotation.isDeleted" value="${salesQuotation.isDeleted}"/>
				<input type="hidden" id="billGroupCodeId" name="billGroupCode.id" value="${billGroupCode.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价单编码:</label>
						<div class="col-md-3">
							<input id="code" name="salesQuotation.code" value="${salesQuotation.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价单主题:</label>
						<div class="col-md-3">
							<input id="quoteSubject" name="salesQuotation.quoteSubject" value="${salesQuotation.quoteSubject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="salesQuotation.customerAccount.id" value="${salesQuotation.customerAccount.id}"/>
								<input id="customerName" name="salesQuotation.customerAccount.name" value="${salesQuotation.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">销售机会:</label>
						<div class="col-md-3">
							<select id="saleChanceId" name="salesQuotation.saleChance.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.saleChance.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="salesQuotation.currencyType.id" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">报价单类型:</label>
						<div class="col-md-3">
							<select id="typeId" name="salesQuotation.type.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${billsTypeList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesQuotation.type.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgDeptId" name="salesQuotation.dept.id" value="${salesQuotation.dept.id}"/>
								<input id="chooseSaleOrgDeptName" type="text" onfocus="saleOrgDeptShowMenu(); return false;" value="${salesQuotation.dept.fullName}" class="form-control validate[required]" readonly="readonly"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#chooseSaleOrgDeptId').val('');$('#chooseSaleOrgDeptName').val('');">
										清空
									</button>
								</div>
								<div id="saleOrgDeptMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
									<ul id="chooseSaleOrgDeptZtree" class="ztree"></ul>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						<div class="col-md-3">
							<div id="saleOrgTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgId" name="salesQuotation.salesOrg.id" value="${salesQuotation.salesOrg.id}"/>
								<input id="chooseSaleOrgName" type="text" onfocus="saleOrgShowMenu(); return false;" value="${salesQuotation.salesOrg.fullName}" class="form-control validate[required]" readonly="readonly"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#chooseSaleOrgId').val('');$('#chooseSaleOrgName').val('');">
										清空
									</button>
								</div>
								<div id="saleOrgMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
									<ul id="chooseSaleOrgZtree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>业务类型:</label>
						<div class="col-md-3">
							<input id="bizType" name="salesQuotation.bizType" value="${salesQuotation.bizType}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>地域:</label>
						<div class="col-md-3">
							<select id="regionalId" name="salesQuotation.regional.id" data-prompt-position="topLeft" class="form-control validate[required]" >
								<option value="">------请选择------</option>
								<c:forEach items="${regionalList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesQuotation.regional.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="billDate" name="salesQuotation.billDate" value="${salesQuotation.billDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">税率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="tax" name="salesQuotation.tax" value="${salesQuotation.tax}" class="form-control validate[custom[number],min[1],max[100]]" type="text"/>
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">接收人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="salesQuotation.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">报价人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="salesQuotation.salesMan.id" value="${salesQuotation.salesMan.id}"/>
								<input id="employeeName" name="salesQuotation.salesMan.name" value="${salesQuotation.salesMan.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">报价人联系方式:</label>
						<div class="col-md-8">
							<input id="phone" name="salesQuotation.phone" value="${salesQuotation.phone}" class="form-control validate[custom[phone]]" type="text"/>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label">交货日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dilveryDate" name="salesQuotation.dilveryDate" value="${salesQuotation.dilveryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dilveryDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价有效期从:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="validQuotationFrom" name="salesQuotation.validQuotationFrom" value="${salesQuotation.validQuotationFromStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'validQuotationFrom'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价有效期至:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="validQuotationTo" name="salesQuotation.validQuotationTo" value="${salesQuotation.validQuotationToStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'validQuotationTo'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label">交付说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="deliveryMemo" name="salesQuotation.deliveryMemo" class="form-control">${salesQuotation.deliveryMemo}</textarea> 
						</div>
						<label class="col-md-2 control-label">付款说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="paymentMemo" name="salesQuotation.paymentMemo" class="form-control">${salesQuotation.paymentMemo}</textarea> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">包装运输说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="packMemo" name="salesQuotation.packMemo" class="form-control">${salesQuotation.packMemo}</textarea> 
						</div>
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<textarea rows="3" id="memo" name="salesQuotation.memo" class="form-control">${salesQuotation.memo}</textarea> 
						</div>
					</div>
					<br/>
					<div class="form-group">
						<div class="col-md-12">
							<div id="salesQuotationDetailDiv" class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>报价单明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="货品名称" class="form-control" id="searchSalesItemName">
												</div>
												<button onclick="salesQuotationDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchSalesItemName').val('');salesQuotationDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right" >
													<button onclick="checkSalesQuotationisSave();" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="添加明细"/>
													</button>
													<button onclick="scanSalesQuotationItem('scan');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="扫描添加"/>
													</button>
												</div>
											</div>
										</div>
									   	<table id="salesQuotationDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
								 	</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label">报价人:</label>
						<div class="col-md-2">
							<div class="input-group">
								<input type="hidden" id="chooseSalesManId" name="salesQuotation.salesMan.id" value="${salesQuotation.salesMan.id}"/>
								<input id="chooseSalesManName" name="salesQuotation.salesMan.name" value="${salesQuotation.salesMan.name}" onfocus="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalesMan');" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalesMan');;">选择</span>
							</div>
						</div> --%>
						<label class="col-md-2 control-label">金额:</label>
						<div class="col-md-3">
							<input id="amount" name="salesQuotation.amount" value="${salesQuotation.amount}" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdateSalesQuotation('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<s:if test="isAllowAudit == 1">
								<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
									<i class="fa fa-save"></i> &nbsp; 提交审批
								</button>
							</s:if>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesQuotationAction!goList.action');">
								返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#salesQuotationForm").validationEngine();
	
	var salesQuotationDetailColumns = [
			{"title":"条形码","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.barCode;}},
			{"title":"SKU","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.skuCode;}},
			{"title":"编码","width":"10%","orderable":false,"data" : function(data) {return data.itemCode;}},
			{"title":"名称","width":"20%","orderable":false,"data" : function(data) {return data.itemName;}},
			{"title":"规格型号","width":"6%","orderable":false,"data" : function(data) {return data.specification;}},
			{"title":"主计量单位","width":"6%","orderable":false,"data" : function(data) {return data.measureUnit == null ? '' : data.measureUnit;}},
			{"title":"数量","width":"6%","orderable":false,"data" : function(data) {return data.amount;}},
			{"title":"金额","width":"6%","orderable":false,"data" : function(data) {return data.price == null ? "￥ 0" : ("￥" + data.price);}},
			{"title":"税率","width":"4%","orderable":false,"data" : function(data) {return data.tax == null ? "0&nbsp;%" : (data.tax + "&nbsp;%");}},
			{"title":"折扣率","width":"4%","orderable":false,"data" : function(data) {return data.discount == null ? "0&nbsp;%" : (data.discount + "&nbsp;%");}},
			{"title":"操作","width":"6%","orderable":false,"data" : function(data) {
				var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"addSalesQuotationItem('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
   			}}
		];
	             	
   	var salesQuotationDetailTable = initDataTable("salesQuotationDetail","${nvix}/nvixnt/nvixSalesQuotationAction!getSalesQuotationItemJson.action",salesQuotationDetailColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#searchSalesItemName').val();
   			name = Url.encode(name);
   	 		var id = $("#id").val();
   	 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','0');
	
	 /** 初始化销售组织下拉列表树 */
	var saleOrgShowMenu = initDropListTree("saleOrgTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgId","chooseSaleOrgName","chooseSaleOrgZtree","saleOrgMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgId").val("");
			$("#chooseSaleOrgName").val("");
		}
	});
	 /** 初始化部门下拉列表树 */
	var saleOrgDeptShowMenu = initDropListTree("saleOrgDeptTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgDeptId","chooseSaleOrgDeptName","chooseSaleOrgDeptZtree","saleOrgDeptMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgDeptId").val("");
			$("#chooseSaleOrgDeptName").val("");
		}
	});
	
	function saveOrUpdateSalesQuotation(id){
		//表单验证
		if($("#salesQuotationForm").validationEngine('validate')){
			/* var startTime = $("#validQuotationFrom").val() + " 00:00:01";;
			var endTime = $("#validQuotationTo").val() + " 00:00:01";;
			var tag = dateTimeRange(startTime,endTime);
			if(!tag){
				layer.alert("有效期结束时间不能小于开始时间!");
				return tag;
			}else{
				$("#salesQuotationForm").ajaxSubmit({
					type: "post",
					url:"${nvix}/nvixnt/nvixSalesQuotationAction!saveOrUpdate.action?id=" + $("#id").val(),
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						showMessage(r[1]);
						loadContent('','${nvix}/nvixnt/nvixSalesQuotationAction!goList.action');
					}
				});
			} */
			$("#salesQuotationForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/nvixSalesQuotationAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					var r = result.split(":");
					showMessage(r[1]);
					loadContent('','${nvix}/nvixnt/nvixSalesQuotationAction!goList.action');
				}
			});
		}
	}
	
	/** 检查主题是否保存的通用方法*/
	function checkSalesQuotationisSave(){
		var id = $("#id").val();
		if(id == ''){
			if($('#salesQuotationForm').validationEngine('validate')){
				/* var startTime = $("#validQuotationFrom").val() + " 00:00:01";;
				var endTime = $("#validQuotationTo").val() + " 00:00:01";;
				var tag = dateTimeRange(startTime,endTime);
				if(!tag){
					layer.alert("有效期结束时间不能小于开始时间!");
					return tag;
				}else{
					$("#salesQuotationForm").ajaxSubmit({
						type: "post",
			    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
						success : function(result) {
							var r = result.split(":");
							if(r[0] != '0'){
								$("#id").val($.trim(r[0]));
								id = $("#id").val();
								addSalesQuotationItem('0');
							}
						}
					});
				} */
				$("#salesQuotationForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							$("#id").val($.trim(r[0]));
							id = $("#id").val();
							addSalesQuotationItem('0');
						}
					}
				});
			}
		}else {
			addSalesQuotationItem('0');
		}
	}
	
	/** 添加明细*/
	function addSalesQuotationItem(id){
		openWindowForShow('${nvix}/nvixnt/nvixSalesQuotationAction!goSaveOrUpdateSalesQuotationItem.action?id=' + id,'添加明细',900,560,function(){
			salesQuotationDetailTable.ajax.reload();
			$.ajax({
				url : '${nvix}/nvixnt/nvixSalesQuotationAction!getSalesQuotationItemTotal.action?id=' + $("#id").val(),
				cache : false,
				success : function(json) {
					$("#amount").val(json);
				}
			});
		});
	}
	
	/** 扫描添加明细*/
	function scanSalesQuotationItem(types){
		openWindowForShow('${nvix}/nvixnt/nvixSalesQuotationAction!goSaveOrUpdateSalesQuotationItem.action?types=' + types,'添加明细(扫描)',420,240,function(){
			salesQuotationDetailTable.ajax.reload();
		});
	}
	
	/** 销售定价 */
	function showItemPrice(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixSalesOrderAction!goSaveOrUpdate.action?id=' + id,'itemPriceForm','销售定价',720,665,orderDetailTable,null,function(){
			orderDetailTable.ajax.reload();
		});
	}
	
	//删除
	function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesQuotationAction!deleteSalesQuotationItemById.action?id='+id,'是否删除此条明细?',salesQuotationDetailTable,"删除明细",function(){
			$.ajax({
				url : '${nvix}/nvixnt/nvixSalesQuotationAction!getSalesQuotationItemTotal.action?id=' + $("#id").val(),
				cache : false,
				success : function(json) {
					$("#amount").val(json);
				}
			});
		});
	}
	
	/** 加载机会*/
	function loadSaleChance() {
		if ($("#customerId").val() != '') {
			$.ajax({
				url : '${nvix}/nvixnt/nvixCompetitorAction!loadSaleChance.action?id=' + $("#customerId").val(),
				cache : false,
				success : function(html) {
					var saleChanceId = $("#saleChanceId").val();
					$("#saleChanceId").html(html);
					/* if (saleChanceId != '') {
						$("#saleChanceId").val(saleChanceId);
					} */
				}
			});
		}
	}
	
	/** 加载联系人*/
	function loadContactPerson() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixContactPersonAction!loadContactPersonOption.action?parentId=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var contactPersonId = $("#contactPersonId").val();
				$("#contactPersonId").html(html);
				/* if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				} */
			}
			});
		}
	}
	
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadSaleChance();
			loadContactPerson();
		});
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
	
	pageSetUp();
</script>