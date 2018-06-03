<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
function loadEncoding(){
	encoding = $('#oa_encoding').val();
	encoding = Url.encode(encoding);
}

function loadRecipientsWho(){
	recipientsWho = $('#oa_recipientsWho').val();
	recipientsWho = Url.encode(recipientsWho);
}

function loadModel(){
	model = $('#oa_model').val();
	model = Url.encode(model);
}

/*搜索*/
function bookQuery(i){
	loadRecipientsWho();
	loadModel();
	if(i == 0){
		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?encoding="+$('#oa_encoding').val(),'newtab1');
	}
	else if(i == 1){
		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?recipientsWho="+Url.encode(recipientsWho),'newtab1');
	}
	else if(i == 2){
		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?model="+Url.encode(model),'newtab1');
	}
}


function saveOrUpdateBorrowList(id){
	$.ajax({
		  url:'${vix}/oa/officeSuppliesRegisterAction!goSuppliesReturnList.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 680,
					height : 240,
					title:"归还办公用品",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#officeSuppliesBorrowForm').validationEngine('validate')){
								$.post('${vix}/oa/officeSuppliesRegisterAction!saveOrUpdateBorrowList.action',
									 {'officeSuppliesBorrow.id':$("#officeSuppliesRegisterId").val(),
									  'officeSuppliesBorrow.model':$("#model").val(),
									  'officeSuppliesBorrow.officeName':$("#officeName").val(),
									  'officeSuppliesBorrow.startTime':$("#startTime").val(),
									  'officeSuppliesBorrow.borrowNumber':$("#borrowNumber").val(),
									  'officeSuppliesBorrow.returnNumber':$("#returnNumber").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='归还办公用品'/>",function(action){
											
											if($('#oa_encoding').val() != ''){
									    		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?encoding="+$('#oa_encoding').val(),'newtab1');
									    	}
									    	else if($('#oa_recipientsWho').val() != ''){
									    		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?recipientsWho="+Url.encode($('#oa_recipientsWho').val()),'newtab1');
									    	}
									    	else if($('#oa_model').val() != ''){
									    		pager("start","${vix}/oa/officeSuppliesRegisterAction!goBorrowList.action?model="+Url.encode($('#oa_model').val()),'newtab1');
									    	}
										});
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action?pageNo=${pageNo}');"><img src="img/oa_office_Supplies.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action?pageNo=${pageNo}');">办公用品管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action?pageNo=${pageNo}');">办公用品登记管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action?pageNo=${pageNo}');">办公用归还</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${bookRegister.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<strong><b><s:text name="办公用品归还" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>借用人工号：</th>
											<td><input id="oa_encoding" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(0);"><span>查询</span></a></td>
											<th>借用人：</th>
											<td><input id="oa_recipientsWho" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(1);"><span>查询</span></a></td>
										</tr>
										<tr>
											<th>办公用品编码：</th>
											<td><input id="oa_model" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(2);"><span>查询</span></a></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;" id="selectOptionId1">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />归还明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="newtab1" style="width: 100%"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
