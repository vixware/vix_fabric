<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> <a>会员管理</a><span>> 会员管理 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','1');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;办理会员
				</button>
				<button class="btn btn-warning" type="button" onclick="pay('','新增');">
					<i class='fa fa-yen'></i>&nbsp;会员充值
				</button>
				<div class="btn-group">
					<button class="btn btn-info dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-gear"></i>&nbsp;其他&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="exportExcel();">导出</a></li>
						<li><a href="javascript:void(0);" onclick="$('#fileToUpload').click();">导入</a></li>
						<li><a href="javascript:void(0);" onclick="downloadTemplate();">模板下载</a></li>
					</ul>
				</div>	
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="position: relative; padding: 0;">
			<article style=" zoom: 1;">
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
										姓名: <input type="text" value="" class="form-control" id="selectName">
										手机号: <input type="text" value="" class="form-control" id="phone">
										会员卡号: <input type="text" value="" class="form-control" id="carNumber">
									</div>
									<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
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
	"width" : "5%",
	"defaultContent" : ''
	}, {
	"title" : "会员卡号",
	"width" : "7%",
	"data" : function(data) {
		return data.clipNumber;
	}
	}, {
	"title" : "会员姓名",
	"width" : "7%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"show('" + data.id + "','2');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "手机",
	"width" : "7%",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}
	, {
	"title" : "门店",
	"width" : "12%",
	"data" : function(data) {
		return data.channelDistributorName;
	}
	}, {
	"title" : "卡类型",
	"width" : "6%",
	"data" : function(data) {
		if(data.customerClipType == '1'){
			return "<span class='label label-warning'>余额卡</span>";
		}else if(data.customerClipType == '2'){
			return "<span class='label label-info'>次&nbsp;&nbsp;&nbsp;&nbsp;卡</span>";
		}		
		return "";
	}
	}, {
	"title" : "余额",
	"width" : "7%",
	"data" : function(data) {
		return data.moneyStr;
	}
	}, {
	"title" : "积分",
	"width" : "7%",
	"data" : function(data) {
		return data.point;
	}
	}, {
	"title" : "最近消费日期",
	"width" : "15%",
	"data" : function(data) {
		return data.updateTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "7%",
	"data" : function(data) {
		if(data.isUse == "Y"){
			return "<span class='label label-success'>启用</span>";
		}else if(data.isUse == "N"){
			return "<span class='label label-warning'>禁用</span>";
		}
		return '';
	}
	}, {
	"title" : "卡状态",
	"width" : "7%",
	"data" : function(data) {
		if(data.isReport == "Y"){
			return "<span class='label label-info'>挂失</span>";
		}else{
			var d = data.expiryDate;
			var b = new Date().getTime();
			if(b >= d){
				return "<span class='label label-warning'>会员卡到期</span>";
			}else{
				return "<span class='label label-success'>正常</span>";
			}
		}
		return '';
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		//var applayCard = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"applayCard('" + data.id + "','办卡');\" title='办卡'><span class='txt-color-blue pull-right'><i class='fa fa-barcode'></i></span></a>";
		//var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		//var report = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"report('" + data.id + "');\" title='挂失'><span class='txt-color-blue pull-right'><i class='fa fa-question-circle'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		//var pay = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"pay('" + data.id + "');\" title='充值'><span class='txt-color-blue pull-right'><i class='fa fa-yen'></i></span></a>";
		var start = "<div class=\"btn-group\"><button class=\"btn btn-success btn-xs dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-gear\"></i> 操作  <span class=\"caret\"></span></button><ul class=\"dropdown-menu pull-right\">";
		var applayCard = "<li><a href='javascript:void(0);' onclick=\"applayCard('" + data.id + "','办卡');\" title='办卡'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>办卡</a></li>";
		var update = "<li><a href='javascript:void(0);' onclick=\"goSaveOrUpdate('" + data.id + "','2');\" title='修改'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>修改</a></li>";
		var checkUse = "<li><a href='javascript:void(0);' onclick=\"checkUse('" + data.id + "','启用');\" title='启用'><span class='txt-color-blue pull-right'><i class='fa  fa-ban'></i></span>启用</a></li>";
		var checkNotUse = "<li><a href='javascript:void(0);' onclick=\"checkNotUse('" + data.id + "','禁用');\" title='禁用'><span class='txt-color-red pull-right'><i class='fa  fa-ban'></i></span>禁用</a></li>";
		var goOn = "<li><a href='javascript:void(0);' onclick=\"goOn('" + data.id + "','续卡');\" title='续卡'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span>续卡</a></li>";
		var pay = "<li><a href='javascript:void(0);' onclick=\"pay('" + data.id + "','充值');\" title='充值'><span class='txt-color-blue pull-right'><i class='fa fa-yen'></i></span>充值</a></li>";
		var del = "<li><a href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span>删除</a></li>";
		var report = "<li><a href='javascript:void(0);' onclick=\"report('" + data.id + "');\" title='挂失'><span class='txt-color-blue pull-right'><i class='fa fa-question-circle'></i></span>挂失</a></li>";
		var swivel = "<li><a href='javascript:void(0);' onclick=\"swivel('" + data.id + "');\" title='转卡'><span class='txt-color-blue pull-right'><i class='fa fa-sign-out'></i></span>转卡</a></li>";
		var integralChange = "<li><a href='javascript:void(0);' onclick=\"integralChange('" + data.id + "');\" title='积分兑换'><span class='txt-color-blue pull-right'><i class='fa fa-sign-out'></i></span>积分兑换</a></li>";
		var makeTag = "<li><a href='javascript:void(0);' onclick=\"makeTag('" + data.id + "');\" title='打标签'><span class='txt-color-blue pull-right'><i class='fa fa-tags'></i></span>打标签</a></li>";
		var end = "</ul></div>";
		var d = data.expiryDate;
		var b = new Date().getTime();
		if(b >= d){
			return show+"&nbsp;&nbsp;" +start+goOn+ update +end;
		}
		if(data.isReport == "Y"){
			return show+"&nbsp;&nbsp;" +start+applayCard+ update +end;
		}
		if(data.isUse == "N"){
			return show+"&nbsp;&nbsp;"+start+checkUse+ update +end;
		}
		if(data.status == "2"){
			return show+"&nbsp;&nbsp;" +start+update +pay +integralChange+ checkNotUse + report + makeTag + end;
		}else{
			return show+"&nbsp;&nbsp;" +start+update +end;
		}
		
	}
	} ];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var selectName = $("#selectName").val();
		var phone = $("#phone").val();
		var carNumber = $('#carNumber').val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"treeType" : treeType,
		"phone" : phone,
		"carNumber" : carNumber,
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
		if(type=="1"){
			$.ajax({
				url : '${nvix}/nvixnt/nvixCustomerAccountAction!goSaveOrUpdate.action?id=' + id ,
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
		}
		if(type=="2"){
			$.ajax({
				url : '${nvix}/nvixnt/nvixCustomerAccountAction!goSaveOrUpdateCustomer.action?id=' + id ,
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
		}
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
	function integralChange(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountAction!integralChange.action?id='+ id, "integralRulesForm", "积分兑换", 750, 450, customerAccountTable);
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
	function applayCard(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdate.action?customerAccountId=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function goOn(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateGoOnClip.action?customerAccountId=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function swivel(id,title){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!swivel.action?customerAccountId=' + id, "memberTagForm", title, 750, 450, customerAccountTable);
	}
	function makeTag(id){
		openWindowForShow('${nvix}/nvixnt/nvixCustomerAccountAction!makeTag.action?id=' + id,"打标签", 750, 550,function(){
			customerAccountTable.ajax.reload();
		});
	}
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/nvixCustomerAccountAction!exportCustomerAccountExcel.action";
		form.submit();
	};
</script>