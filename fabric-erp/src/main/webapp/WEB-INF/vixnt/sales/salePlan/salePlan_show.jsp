<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt;销售计划</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
					<i class="fa fa-chevron-left"></i> 上一条
				</button>
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
					<i class="fa fa-chevron-right"></i> 下一条
				</button>
				<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixntSalePlanAction!goSourceList.action?id='+$('#salePlanId').val());">
					<i class="fa fa-retweet"></i> 追溯
				</button>
				<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="printSalePlan();">
					<i class="fa fa-print"></i> 打印
				</button>
				<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
					<i class="fa fa-envelope-o"></i> 发送邮件
				</button>
				<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSalePlanAction!goList.action');">
					<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售计划</h2>
		</header>
		<div class="widget-body">
			<form id="salePlanForm" class="form-horizontal" action="${nvix}/nvixnt/nvixntSalePlanAction!saveOrUpdate.action">
				<input type="hidden" id="salePlanId" name="salePlan.id" value="${salePlan.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label">编码:</label>
						<div class="col-md-3">
							<input id="code" name="salePlan.code" value="${salePlan.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">名称:</label>
						<div class="col-md-3">
							<input id="name" name="salePlan.name" value="${salePlan.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input id="chooseSaleOrgDeptName" type="text" return false;" value="${salePlan.departmet.fullName}" class="form-control validate[required]" readonly="readonly"/>
							</div>
						</div>
						<label class="col-md-2 control-label">销售人员:</label>
						<div class="col-md-3">
							<input id="employeeName" name="salePlan.salesMan.name" value="${salePlan.salesMan.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">计划数量:</label>
						<div class="col-md-3">
							<input id="amount" name="salePlan.amount" value="${salePlan.amount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">计划金额:</label>
						<div class="col-md-3">
							<input id="acount" name="salePlan.acount" value="${salePlan.acount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planDate" name="salePlan.planDate" value="${salePlan.planDate}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>计划明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
										</div>
										<button onclick="salePlanDetailsTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');salePlanDetailsTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="salePlanDetailsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>审批信息</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="flowApprovalOpinionTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
								<i class="fa fa-chevron-left"></i> 上一条
							</button>
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
								<i class="fa fa-chevron-right"></i> 下一条
							</button>
							<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixntSalePlanAction!goSourceList.action?id='+$('#salePlanId').val());">
								<i class="fa fa-retweet"></i> 追溯
							</button>
							<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="printSalePlan();">
								<i class="fa fa-print"></i> 打印
							</button>
							<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
								<i class="fa fa-envelope-o"></i> 发送邮件
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSalePlanAction!goList.action');">
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
	var LODOP;
	function printSalePlan(){
		$.ajax({
			url : '${nvix}/nvixnt/nvixntSalePlanAction!printSalePlan.action?id='+$("#salePlanId").val(),
			cache : false,
			success : function(html) {
				LODOP = getLodop();
				LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
				LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
				LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
				LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
				LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
				/* LODOP.PRINT(); */
				LODOP.PREVIEW();
			}
			});
	}
	function goBeforeOrAfter(tag){
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!goShowBeforeAndAfter.action?str='+tag+'&id='+$('#salePlanId').val());
	}
	var flowApprovalOpinionColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "审批意见",
		"data" : function(data) {
			return data.opinion;
		}
		}, {
		"title" : "审批人",
		"data" : function(data) {
			return data.approvalPersonName;
		}
		}, {
		"title" : "审批时间",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		} ];
		var flowApprovalOpinionTable = initDataTable("flowApprovalOpinionTableId", "${nvix}/nvixnt/vixntInboundWarehouseAction!getFlowApprovalOpinion.action", flowApprovalOpinionColumns, function(page, pageSize, orderField, orderBy) {
			var salePlanId = $("#salePlanId").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"id" : salePlanId
			};
		});
		var salePlanDetailsColumns = [ {
			"title" : "编号",
			"width" : "5%",
			"orderable" : false,
			"data" : function(data) {
				return "&nbsp;";
			}
			}, {
			"title" : "商品编码",
			"data" : function(data) {
				return data.item == null ? "":data.item.code;
			}
			}, {
			"title" : "商品名称",
			"data" : function(data) {
				return data.item == null ? "":data.item.name;
			}
			}, /* {
			"title" : "规格型号",
			"name" : "specification",
			"data" : function(data) {
				return data.specification;
			}
			}, {
			"title" : "SKU编码",
			"name" : "skuCode",
			"data" : function(data) {
				return data.skuCode;
			}
			}, */{
			"title" : "单价",
			"name" : "price",
			"data" : function(data) {
				return data.item == null ? "": "￥"+data.item.price;
			}
			},{
			"title" : "01月",
			"data" : function(data) {
				return data.jan;
			}
			}, {
			"title" : "02月",
			"data" : function(data) {
				return data.feb;
			}
			},{
			"title" : "03月",
			"data" : function(data) {
				return data.mar;
			}
			}, {
			"title" : "04月",
			"data" : function(data) {
				return data.apr;
			}
			},{
			"title" : "05月",
			"data" : function(data) {
				return data.may;
			}
			}, {
			"title" : "06月",
			"data" : function(data) {
				return data.jun;
			}
			},{
			"title" : "07月",
			"data" : function(data) {
				return data.jul;
			}
			}, {
			"title" : "08月",
			"data" : function(data) {
				return data.aug;
			}
			},{
			"title" : "09月",
			"data" : function(data) {
				return data.sep;
			}
			}, {
			"title" : "10月",
			"data" : function(data) {
				return data.oct;
			}
			},{
			"title" : "11月",
			"data" : function(data) {
				return data.nov;
			}
			}, {
			"title" : "12月",
			"data" : function(data) {
				return data.december;
			}
			}, {
			"title" : "总计",
			"data" : function(data) {
				return data.amount;
			}
			}];
			var salePlanDetailsTable = initDataTable("salePlanDetailsTableId", "${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSingleList.action", salePlanDetailsColumns, function(page, pageSize, orderField, orderBy) {
				var id = $("#salePlanId").val();
				var searchName = $("#searchName").val();
				searchName = Url.encode(searchName);
				return {
				"page" : page,
				"pageSize" : pageSize,
				"orderField" : orderField,
				"orderBy" : orderBy,
				"itemName" : searchName,
				"id" : id
				};
			});
			
			function goSaveOrUpdate(id,title){
				var salePlanId = $("#salePlanId").val();
				if(salePlanId){
					openSaveOrUpdateWindow("${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSaveOrUpdate.action?id="+id,"salePlanDetailsForm",title,750,500,salePlanDetailsTable);
				}else{
					if($("#salePlanForm").validationEngine('validate')){
						$("#salePlanForm").ajaxSubmit({
							type: "post",
							url:"${nvix}/nvixnt/nvixntSalePlanAction!saveOrUpdate.action",
							contentType: "application/x-www-form-urlencoded; charset=utf-8", 
							success : function(result) {
								var r = result.split(":");
								if(r[0] == "1"){
									$("#salePlanId").val(r[2]);
									showMessage(r[1],"success");
									openSaveOrUpdateWindow("${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSaveOrUpdate.action?id="+id,"salePlanDetailsForm",title,750,500,salePlanDetailsTable);
								}else{
									showMessage(r[1],"success");
								}
							}
						});
					}
				}
			}
	pageSetUp();
</script>