<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> <a>会员管理</a><span>> 会员卡管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
		</div>
	</div>
	<section id="" class="">
		<div style="position: relative; padding: 0;">
			<article style="zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>会员卡列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="selectName">
										手机号: <input type="text" value="" class="form-control" id="phone">
										卡号: <input type="text" value="" class="form-control" id="number">
									</div>
									<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');$('#phone').val('');$('#number').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-dedault">
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
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "卡号",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"show('" + data.id + "','查看');\" title='查看'>"+data.name+"</a>";
	}
	}, {
	"title" : "持卡人",
	"data" : function(data) {
		return data.customerName;
	}
	}, {
	"title" : "电话",
	"data" : function(data) {
		return data.customerPhone;
	}
	}, {
	"title" : "会员卡类型",
	"data" : function(data) {
		if(data.cardType== "1"){
			return "<span class='label label-warning'>余额卡</span>";
		}else if(data.cardType == "2"){
			return "<span class='label label-info'>次&nbsp;&nbsp;&nbsp;&nbsp;卡</span>";
		}
		return "";
	}
	}, {
	"title" : "积分",
	"data" : function(data) {
		return data.pointValue;
	}
	}, {
	"title" : "余额",
	"data" : function(data) {
		return data.moneyStr;
	}
	}, {
	"title" : "会员卡状态",
	"data" : function(data) {
		if(data.isReport=="Y"){
			return "<span class='label label-info'>已挂失</span>";
		}else{
			var d = data.expiryDate;
			var b = new Date().getTime();
			if(b >= d){
				return "<span class='label label-warning'>会员卡到期</span>";
			}else{
				return "<span class='label label-success'>正常</span>";
			}
		}
	}
	}, {
	"title" : "操作",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		//var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		//var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		//var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		//var swivel = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"swivel('" + data.id + "');\" title='转卡'><span class='txt-color-blue pull-right'><i class='fa fa-openid'></i></span></a>";
		//var report = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"report('" + data.id + "');\" title='挂失'><span class='txt-color-blue pull-right'><i class='fa fa-wrench'></i></span></a>";
		//var pay = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"pay('" + data.id + "','充值');\" title='充值'><span class='txt-color-blue pull-right'><i class='fa fa-yen'></i></span></a>";
		var applayCard = "<li><a href='javascript:void(0);' onclick=\"applayCard('" + data.id + "','办卡');\" title='办卡'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>办卡</a></li>";
		var start = "<div class=\"btn-group\"><button class=\"btn btn-success btn-xs dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-gear\"></i> 操作  <span class=\"caret\"></span></button><ul class=\"dropdown-menu pull-right\">";
		var show = "<li><a href='javascript:void(0);' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span>查看</a></li>";
		var goOn = "<li><a href='javascript:void(0);' onclick=\"goOn('" + data.id + "','续卡');\" title='续卡'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span>续卡</a></li>";
		var pay = "<li><a href='javascript:void(0);' onclick=\"pay('" + data.id + "','充值');\" title='充值'><span class='txt-color-blue pull-right'><i class='fa fa-yen'></i></span>充值</a></li>";
		var report = "<li><a href='javascript:void(0);' onclick=\"report('" + data.id + "');\" title='挂失'><span class='txt-color-blue pull-right'><i class='fa fa-question-circle'></i></span>挂失</a></li>";
		var integralChange = "<li><a href='javascript:void(0);' onclick=\"integralChange('" + data.id + "');\" title='积分兑换'><span class='txt-color-blue pull-right'><i class='fa fa-sign-out'></i></span>积分兑换</a></li>";
		var swivel = "<li><a href='javascript:void(0);' onclick=\"swivel('" + data.id + "');\" title='转卡'><span class='txt-color-blue pull-right'><i class='fa fa-sign-out'></i></span>转卡</a></li>";
		var end = "</ul></div>";
		var d = data.expiryDate;
		var b = new Date().getTime();
		if(b >= d){
			return start+ show + goOn +end;
		}
		if(data.isReport == 'Y'){
			return start+ show + applayCard +end;
		}else{
			return start + show + swivel+integralChange + report + pay + end;	
		}
	}
	} ];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerAccountClipAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var number = $("#number").val();
		var phone = $("#phone").val();
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"number" : number,
		"phone" : phone,
		"selectName" : selectName
		};
	}, 10, '0');
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
	function show(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!show.action?id=' + id,
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
	function swivel(id,title){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!swivel.action?customerAccountClipId=' + id, "memberTagForm", title, 750, 450, customerAccountTable);
	}
	function pay(id){
		//openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountId=' + id, "memberTagForm", title, 750, 450, customerAccountTable);
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountClipId=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function report(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAccountClipAction!report.action?customerAccountClipId=' + id, '是否挂失?', customerAccountTable);
	};
	//积分兑换
	function integralChange(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!integralChange.action?id='+ id, "integralRulesForm", "积分兑换", 750, 450, customerAccountTable);
		/* openWindowForShow('${nvix}/nvixnt/nvixCustomerAccountAction!integralChange.action?id='+ id,'积分兑换',750,450,function(){
			customerAccountTable.ajax.reload();
		}); */
	}
	function applayCard(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdate.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
	function applayCard(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!goSaveOrUpdateGoOnClip.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	}
</script>