<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
					<s:if test=" fromPage=='PurchasePageUnu' ">  
						<i class="fa-fw fa fa-home"></i> 供应商管理 <span>> 供应商统计报表 </span>
					</s:if>
					
					<input type="hidden" value="${changeSQL }" id="changeSQL"  />  
					<input type="hidden" value="${fromPage }" id="fromPage"  />
					<input type="hidden" value="${setSupplierID }" id="setSupplierIDApage"  />
			</h1>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			  <s:if test="fromPage=='PurchasePageUnu'">
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierViewDataAction!goSupplierView.action');"> 
					<i class="fa fa-rotate-left"></i> 返回    
				</button>
			  </s:if>
			</div>
		</div>
	</div>
	
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>供应商列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								名称: <input type="text" value="" class="form-control" id="selectName" style="width: 130px;">
								<input type="hidden" value="" class="form-control" id="phone" style="width: 130px;">
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-primary" id="">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectName').val('');$('#phone').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
	
	</div>
</div>
<script type="text/javascript">
//开始统计列表
pageSetUp();
var customerAccountColumns =[ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "联系人",
	"data" : function(data) {
		return data.contacts;
	}
	}, {
	"title" : "电话",
	"data" : function(data) {
		return data.cellphone;
	}
	}, {
	"title" : "类型",
	"data" : function(data) {
		if (data.type == 'GENERAL') {
			return "<span class='label label-info'>普通供应商</span>";
		} else if (data.type == 'AGREEMENT') {
			return "<span class='label label-primary'>协议供应商</span>";
		} else if (data.type == 'OUTSOURCING') {
			return "<span class='label label-success'>委外供应商</span>";
		}
		return "";
	}
	}, {
	"title" : "状态",
	"data" : function(data) {
		if (data.status == '1') {
			return "<span class='label label-info'>待建档</span>";
		} else if (data.status == '2') {
			return "<span class='label label-primary'>待评估</span>";
		} else if (data.status == '3') {
			return "<span class='label label-success'>正式</span>";
		} else if (data.status == '4') {
			return "<span class='label label-success'>无效</span>";
		}
		return "";
	}
	} ];

var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntSupplierViewDataAction!queryDataToList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
	var phone = $("#phone").val();
	phone = Url.encode(phone);
	var selectName = $("#selectName").val();
	selectName = Url.encode(selectName);
	var changeSQL = $("#changeSQL").val();  
	changeSQL = Url.encode(changeSQL);
	var setSupplierID = $("#setSupplierIDApage").val();
	setSupplierID = Url.encode(setSupplierID);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"phone" : phone,
	"changeSQL" : changeSQL,
	"setSupplierID" : setSupplierID,
	"selectName" : selectName
	};
}, 10, '0');

</script>