<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-dollar"></i> <a>会员消费管理</a><span>> 会员退货 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>会员列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input type="text" value="" class="form-control" id="selectName">
										手机号: <input type="text" value="" class="form-control" id="phone">
									</div>
									<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAccountColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"defaultContent" : ''
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "会员姓名",
	"data" : function(data) {
		return data.customerName;
	}
	}, {
	"title" : "手机",
	"data" : function(data) {
		return data.customerPhone;
	}
	}, {
	"title" : "退款金额",
	"data" : function(data) {
		return data.amount;
	}
	}];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerSalesReturnAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		var phone = $("#phone").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"phone" : phone,
		"selectName" : selectName
		};
	}, 10, '0');
	function format(d){
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">:</td>' + '<td>' + d.batchcode + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">供应商:</td>' + '<td>' + d.supplierName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">到期日:</td>' + '<td>' + d.massunitEndTimeStr + '</td>' + '</tr>' + '<tr>' + '<td>单位:</td>' + '<td>' + d.unit + '</td>' + '</tr>' + '<tr>' + '<td>操作:</td>' + '<td><button class="btn btn-xs btn-danger pull-right" style="margin-left:5px" onclick="deleteItemById(\'' + d.id + '\');">删除</button> </td>' + '</tr>' + '</table>';
	}
	$("#customerAccountTableId tbody").on('click','td.details-control',function(){
		var tr = $(this).closest('tr');
		var row = customerAccountTable.row(tr);
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			row.child(format(row.data())).show();
			tr.addClass('shown');
		}
	});
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountAction!deleteById.action?id=' + id, '是否删除?', customerAccountTable);
	};
	function report(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountClipAction!report.action?customerAccountId=' + id, '是否挂失?', customerAccountTable);
	};
	function checkNotUse(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountAction!checkNotUse.action?id=' + id, '是否禁用?', customerAccountTable);
	};
	function checkUse(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountAction!checkUse.action?id=' + id, '是否启用?', customerAccountTable);
	};
	function goSaveOrUpdate(id,type) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!goSaveOrUpdate.action?id=' + id ,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
	function show(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountAction!show.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function pay(id){
		//openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountId=' + id, "memberTagForm", title, 750, 450, customerAccountTable);
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountId=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!importFile.action',// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(data, status) {// 服务器成功响应处理函数
			customerAccountTable.ajax.reload();
			showMessage("导入成功!", 'success');
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			showMessage("上传错误!", 'error');
		}
		});
	};
	function downloadTemplate() {
		var url = "${nvix}/nvixnt/nvixCustomerAccountAction!downloadTemplate.action";
		window.open(url);
	};
	function applayCard(id,title){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdate.action?customerAccountId=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function swivel(id,title){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!swivel.action?customerAccountId=' + id, "memberTagForm", title, 750, 450, customerAccountTable);
	}
</script>