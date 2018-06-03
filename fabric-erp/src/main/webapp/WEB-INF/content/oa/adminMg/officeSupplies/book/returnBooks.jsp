<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">

    function loadBookNumber(){
    	bookNumber = $('#oa_bookNumber').val();
    	bookNumber = Url.encode(bookNumber);
    }
    
    function loadRecipientsWho(){
    	recipientsWho = $('#oa_recipientsWho').val();
    	recipientsWho = Url.encode(recipientsWho);
    }
    
    function loadCode(){
    	code = $('#oa_code').val();
    	code = Url.encode(code);
    }
    /*搜索*/
    function bookQuery(i){
    	loadRecipientsWho();
    	loadCode();
    	if(i == 0){
    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?bookNumber="+$('#oa_bookNumber').val(),'newtab1');
    	}
    	else if(i == 1){
    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?recipientsWho="+Url.encode(recipientsWho),'newtab1');
    	}
    	else if(i == 2){
    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?code="+Url.encode(code),'newtab1');
    	}
    }
    
    
    function saveOrUpdateReturn(id){
    	$.ajax({
    		  url:'${vix}/oa/bookLibrarianAction!goSaveOrUpdateReturn.action?id='+id,
    		  cache: false,
    		  success: function(html){
    			  asyncbox.open({
    				 	modal:true,
    					width : 680,
    					height : 240,
    					title:"归还图书",
    					html:html,
    					callback : function(action){
    						if(action == 'ok'){
    							if($('#bookBorrowForm').validationEngine('validate')){
    								$.post('${vix}/oa/bookLibrarianAction!saveOrUpdateReturn.action',
    									 {'bookBorrow.id':$("#bookEntryId").val(),
    									  'bookBorrow.code':$("#code").val(),
    									  'bookBorrow.bookName':$("#bookName").val(),
    									  'bookBorrow.startTime':$("#startTime").val(),
    									  'bookBorrow.borrowNumber':$("#borrowNumber").val(),
    									  'bookBorrow.returnNumber':$("#returnNumber").val()
    									},
    									function(result){
    										asyncbox.success(result,"<s:text name='归还图书'/>",function(action){
												
    									    	if($('#oa_bookNumber').val() != ''){
    									    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?bookNumber="+$('#oa_bookNumber').val(),'newtab1');
    									    	}
    									    	else if($('#oa_recipientsWho').val() != ''){
    									    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?recipientsWho="+Url.encode($('#oa_recipientsWho').val()),'newtab1');
    									    	}
    									    	else if($('#oa_code').val() != ''){
    									    		pager("start","${vix}/oa/bookLibrarianAction!goSingleList.action?code="+Url.encode($('#oa_code').val()),'newtab1');
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
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');"><img src="img/oa_office_Supplies.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">图书管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">借还书管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">书籍归还</a></li>
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
					<strong><b><s:text name="书籍归还" /> </b><i></i> </strong>
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
											<th>借书证号：</th>
											<td><input id="oa_bookNumber" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(0);"><span>查询</span></a></td>
											<th>借书人：</th>
											<td><input id="oa_recipientsWho" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(1);"><span>查询</span></a></td>
										</tr>
										<tr>
											<th>图书编码：</th>
											<td><input id="oa_code" name="" type="text" class="int" /> <a href="###" class="abtn" onclick="bookQuery(2);"><span>查询</span></a></td>
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
