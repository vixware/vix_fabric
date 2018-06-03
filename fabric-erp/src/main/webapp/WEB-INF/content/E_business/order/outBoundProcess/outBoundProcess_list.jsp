<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
/* 内容 */
var orderCode = "";
function loadOrderCode(){
	orderCode = $('#orderCode').val();
	orderCode=Url.encode(orderCode);
	orderCode=Url.encode(orderCode);
}
var buyerNick = "";
function loadBuyerNick(){
	buyerNick = $('#buyerNick').val();
	buyerNick=Url.encode(buyerNick);
	buyerNick=Url.encode(buyerNick);
}
var payTypeCn = "";
function loadPayTypeCn(){
	payTypeCn = $('#payTypeCn').val();
	payTypeCn=Url.encode(payTypeCn);
	payTypeCn=Url.encode(payTypeCn);
}
var receiverMobile = "";
function loadReceiverMobile(){
	receiverMobile = $('#receiverMobile').val();
	receiverMobile=Url.encode(receiverMobile);
	receiverMobile=Url.encode(receiverMobile);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
	  url:'${vix}/business/printPickingListAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});};
function goOrderPicking(){
	$.ajax({
	  url:'${vix}/business/outBoundProcessAction!goOrderPicking.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
function goPrintTemp(){
	$.ajax({
	  url:'${vix}/business/outBoundProcessAction!goPrintTemp.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
/* function goPrintPickingList(){
	$.ajax({
	  url:'${vix}/business/printPickingListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
}; */
/* 发货单 */
function goPrintDeliveryList(){
	$.ajax({
	  url:'${vix}/business/printDeliveryListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};


/* 快递单 */
function goPrintExpressList(){
	$.ajax({
	  url:'${vix}/business/printExpressListAction!goList.action',
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};
function downloadOrder(){
	$.ajax({
	  url:'${vix}/business/outBoundProcessAction!goDownloadOrder.action',
	  cache: false,
	  success: function(){
	    setTimeout("", 1000);
	    loadContent('${vix}/business/outBoundProcessAction!goList.action');
	  }
});
};

//载入分页列表数据
pager("start","${vix}/business/outBoundProcessAction!goSingleList1.action?1=1",'order1');
//排序 
function orderBy(orderField){
	var orderBy = $("#order1OrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/outBoundProcessAction!goSingleList1.action?orderField="+orderField+"&orderBy="+orderBy,'order1');
}

function currentPager(tag){
	pager(tag,"${vix}/business/outBoundProcessAction!goSingleList1.action?1=1",'order1');
}
var businussOrderId = "";
function chkChange (chk , id){
	if (chk.checked){
		businussOrderId = businussOrderId + "," + id;
	}
}

function goCheckOrder(){
	$.ajax({
	  url:'${vix}/inventory/outBoundAction!goBusinessOrder.action?businussOrderId='+businussOrderId,
	  cache: false,
	  success: function(html){
	  	$("#mainContent").html(html);
	  }
});
};


function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
function categoryPager(tag,entity){
	if(entity == 'order1'){
		pager(tag,"${vix}/business/outBoundProcessAction!goSingleList1.action?1=1",entity);
	}
	if(entity == 'order2'){
		pager(tag,"${vix}/business/outBoundProcessAction!goSingleList2.action?1=1",entity);
	}
	if(entity == 'order3'){
		pager(tag,"${vix}/business/outBoundProcessAction!goSingleList3.action?1=1",entity);
	}
	if(entity == 'order4'){
		pager(tag,"${vix}/business/outBoundProcessAction!goSingleList4.action?1=1",entity);
	}
	if(entity == 'order5'){
		pager(tag,"${vix}/business/outBoundProcessAction!goSingleList5.action?1=1",entity);
	}
}

function searchOrder(){
	$.ajax({
		  url:'${vix}/business/outBoundProcessAction!goSearchOrder.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 300,
					title:"高级查询",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							pager("start","${vix}/business/outBoundProcessAction!searchOrder.action?1=1",'order1');
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function saveOrUpdatePrintDelivery(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printDeliveryListAction!goList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function saveOrUpdatePrintExpress(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!goList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function saveOrUpdatePrintPicking(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printPickingListAction!goList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
//配货
function saveOrUpdatePickingOut(id,pageNo){
	$.ajax({
		  url:'${vix}/business/pickingOutAction!goList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function saveOrUpdateSendOut(id,pageNo){
	$.ajax({
		  url:'${vix}/business/sendOutAction!goList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function sendOut(id){
	$.ajax({
		 url:'${vix}/business/sendOutAction!sendOutOrder.action?orderBatchId='+id,
		  cache: false,
		  success: function(result){
		    showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/business/outBoundProcessAction!goList.action');
		  }
	});
};
function sendOrderToBusiness(id){
	$.ajax({
		 url:'${vix}/business/sendOutAction!sendOrderToBusiness.action?orderBatchId='+id,
		  cache: false,
		  success: function(result){
		    showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/business/outBoundProcessAction!goList.action');
		  }
	});
};
function chooseEmployees() {
	$.ajax({
	url : '${vix}/common/select/commonSelectEmpAction!goList.action',
	data : {
		chkStyle : "checkbox"
	},
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 800,
		height : 600,
		title : "选择人员",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != undefined) {
					var selectIds = "";
					var selectNames = "";
					var result = returnValue.split(",");
					for (var i = 0; i < result.length; i++) {
						if (result[i].length > 1) {
							var v = result[i].split(":");
							selectIds += "," + v[0];
							selectNames = v[1];
							$.ajax({
								  url:'${vix}/business/orderProcessAction!dealOrder.action?employeesId='+v[0]+"&orderIds="+businussOrderId,
								  cache: false,
								  success: function(html){
									  loadContent('${vix}/business/sendOutAction!goList.action');
								  }
							});
						}
					}
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}
function saveOrUpdateThreeSorting(id){
	$.ajax({
		  url:'${vix}/business/orderProcessAction!goThreeSorting.action?id='+id+"&orderId="+businussOrderId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"分拣条件",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#logisticsForm').validationEngine('validate')){
							$.post('${vix}/business/orderProcessAction!saveOrUpdateThreeSorting.action',
									 {
							     	 'orderId':$("#orderId").val(),
								     'logisticsId':$("#logisticsId").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/outBoundProcessAction!goList.action');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function goPrintDelivery(id,pageNo){
	$.ajax({
		  url:'${vix}/business/outBoundProcessAction!goPrintPickingList.action?id='+id,
		  cache: false,
		  success: function(html){
			    LODOP=getLodop();  
				LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
				LODOP.ADD_PRINT_HTM(0,0,"100%","100%",html);	
				LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);//打印后自动关闭预览窗口
				LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",1);
				LODOP.SET_PREVIEW_WINDOW(1,2,0,1024,550,"");	
				
				/* LODOP.PRINT(); */
				LODOP.PREVIEW(); 
		  }
	});
};
function goPrintPickingList(id){
	$.ajax({
		  url:'${vix}/business/printPickingListAction!goPrintPickingList.action?id='+id,
		  cache: false,
		  success: function(html){
			LODOP=getLodop();  
			LODOP.ADD_PRINT_HTM(0,0,"100%","100%",html);	
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",1);
			//LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.SET_PREVIEW_WINDOW(1,2,0,1024,550,"");	
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		  }
	});
};
function pophtml(id){
	$.ajax({
		  url:'${vix}/business/outBoundProcessAction!goSaveOrUpdateOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"订单信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							var dlData = $("#dlAddress").datagrid("getRows");
							var orderDetailJson = JSON.stringify(dlData);
							if ($('#order').validationEngine('validate')) {
								$.post('${vix}/business/outBoundProcessAction!updateOrderDetail.action', {
								'order.id' : $("#orderId").val(),
								'orderDetailJson' : orderDetailJson
								}, function(result) {
									showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/business/pickingOutAction!goList.action?orderBatchId='+$("#orderBatchId").val());
								});
							} else {
								return false;
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
function showOrders(id){
	$.ajax({
		  url:'${vix}/business/outBoundProcessAction!goOrderBatch.action?orderBatchId='+id,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
//称重
function saveOrUpdateWeighing(id,pageNo){
	$.ajax({
		  url:'${vix}/business/pickingOutAction!goWeighingList.action?orderBatchId='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};

function searchForContent(){
	loadBuyerNick();
	loadPayTypeCn();
	loadOrderCode();
	loadReceiverMobile();
    pager("start","${vix}/business/outBoundProcessAction!goSearchList.action?orderCode="+orderCode+"&buyerNick="+buyerNick+"&receiverMobile="+receiverMobile+"&payTypeCn="+payTypeCn,'order1');
}

function resetForContent(){
	$("#orderCode").val("");
	$("#buyerNick").val("");
	$("#receiverMobile").val("");
	$("#payTypeCn").val("");
}
//声明为全局变量
var LODOP; 
//打印维护
/* function setupExpressList() {
	createZtoPage();
	LODOP.PRINT_SETUP();
}; */
//打印设计
function setupExpressList() {
	createZtoPage();
	LODOP.PRINT_DESIGN();
};

function updateLogisticsNumbers(id){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!goUpdateLogisticsNumbers.action?orderBatchId='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 200,
					title:"填写快递单号",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#logisticsForm').validationEngine('validate')){
								updateShippingOutSid($("#outSid").val(),$("#logisticsId").val(),$("#orderBatchId").val());
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		    }
	   });
};
function updatePreviewLogisticsNumbers(id){
$.ajax({
	  url:'${vix}/business/printExpressListAction!goUpdateLogisticsNumbers.action?orderBatchId='+id,
	  cache: false,
	  success: function(html){
		  asyncbox.open({
			 	modal:true,
				width : 650,
				height : 200,
				title:"填写快递单号",
				html:html,
				callback : function(action){
					if(action == 'ok'){
						if($('#logisticsForm').validationEngine('validate')){
							updateShippingOutSid($("#outSid").val(),$("#logisticsId").val(),$("#orderBatchId").val());
						}else {
							return false;
						}
					}
					},
				btnsbar : $.btn.OKCANCEL
			});
	    }
   });
};
//回填物流单号
function updateShippingOutSid(outSid,logisticsId,orderBatchId){
 	  $.ajax({
 	    url:'${vix}/business/printExpressListAction!goUpdateShippingOutSid.action?orderBatchId='+orderBatchId+"&outSid="+outSid+"&logisticsId="+logisticsId,
		cache: false,
		success: function(result){
			showMessage(result);
			setTimeout("", 1000);
		  }
	});
};
function bahtPrintExpressList(outSid,logisticsId){
   	  $.ajax({
   	    url:'${vix}/business/printExpressListAction!goPrintExpressList.action?ids='+orderid+"&outSid="+outSid+"&logisticsId="+logisticsId,
		cache: false,
		success: function(json){ 
		var obj = eval('('+json+')'); 
		var orderlist = obj.rows;
		 for(var i=0;i<orderlist.length;i++) {
			//根据物流公司编码判断快递单模板
			if(orderlist[i].logisticsCode=="STO"){
			 printExpressList(orderlist[i].fromCompany,orderlist[i].contactPerson,orderlist[i].fromAddress,orderlist[i].receiverName,"",orderlist[i].receiverAddress,orderlist[i].itemTitle,orderlist[i].amount,orderlist[i].fromPhone,orderlist[i].receiverPhone);
			  //回写物流单号 
		      updateOutSid(orderlist[i].id);
			}
	       } 
	       //更改订单批次状态
	       updateOrderBatchState($("#orderBatchId1").val());
		}
	});
  };
function bahtPreviewPrintExpressList(orderBatchId){
   	  $.ajax({
   	    url:'${vix}/business/printExpressListAction!goPrintExpressList.action?orderBatchId='+orderBatchId,
		cache: false,
		success: function(json){ 
		var obj = eval('('+json+')'); 
		var orderlist = obj.rows;
		 for(var i=0;i<orderlist.length;i++) {
			 LODOP = getLodop();
			 //根据物流公司编码判断快递单模板
			 if(orderlist[i].logisticCode=="STO"){
				 createStoPage(orderlist[i].sellerNick,orderlist[i].sellerNick,orderlist[i].receiverName,orderlist[i].receiverCity,orderlist[i].sellerNick,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="ZTO"){
				 createZtoPage(orderlist[i].fromCompany,orderlist[i].contactPerson,orderlist[i].receiverName,orderlist[i].receiverCity,orderlist[i].sellerNick,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="POST"){
				 createPostPage(orderlist[i].receiverName,orderlist[i].receiverPhone,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverCity,orderlist[i].itemTitle,orderlist[i].fromCompany,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="EMS"){
				 createEmsPage(orderlist[i].receiverName,orderlist[i].receiverPhone,orderlist[i].receiverAddress,orderlist[i].fromCompany,orderlist[i].receiverCity,orderlist[i].itemTitle,orderlist[i].receiverCity,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }
			 if(i==0){
				 LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);//打印后自动关闭预览窗口
				 LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",1);
				 LODOP.PREVIEW();
			 }else {
				 LODOP.PRINT();  
			 }
             //回写物流单号 
            // updateOutSid(orderlist[i].id);
             //填写打印顺序
			 updatePrintIndex(orderlist[i].id,i)
             } 
             //更改订单批次状态
             // updateOrderBatchState($("#orderBatchId1").val());
		  }
	});
  };
/* function bahtPreviewPrintExpressList(outSid,logisticsId,orderBatchId){
   	  $.ajax({
   	    url:'${vix}/business/printExpressListAction!goPrintExpressList.action?outSid='+outSid+"&logisticsId="+logisticsId+"&orderBatchId="+orderBatchId,
		cache: false,
		success: function(json){ 
		var obj = eval('('+json+')'); 
		var orderlist = obj.rows;
		LODOP = getLodop();
		LODOP.PRINT_INIT("");
		LODOP.PREVIEW(); 
		 for(var i=0;i<orderlist.length;i++) {
			 //根据物流公司编码判断快递单模板
			 if(orderlist[i].logisticCode=="STO"){
				 createStoPage(orderlist[i].sellerNick,orderlist[i].sellerNick,orderlist[i].receiverName,orderlist[i].receiverCity,orderlist[i].sellerNick,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="ZTO"){
				 createZtoPage(orderlist[i].sellerNick,orderlist[i].sellerNick,orderlist[i].receiverName,orderlist[i].receiverCity,orderlist[i].sellerNick,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="POST"){
				 createPostPage(orderlist[i].receiverName,orderlist[i].receiverPhone,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverCity,orderlist[i].itemTitle,orderlist[i].sellerNick,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }else if (orderlist[i].logisticCode=="EMS"){
				 createEmsPage(orderlist[i].receiverName,orderlist[i].receiverPhone,orderlist[i].receiverAddress,orderlist[i].sellerNick,orderlist[i].receiverCity,orderlist[i].itemTitle,orderlist[i].receiverCity,orderlist[i].receiverPhone,orderlist[i].itemTitle,orderlist[i].receiverCity);
			 }
             //回写物流单号 
            // updateOutSid(orderlist[i].id);
             //填写打印顺序
			 updatePrintIndex(orderlist[i].id,i)
             } 
             //更改订单批次状态
              updateOrderBatchState($("#orderBatchId1").val());
		  }
	});
  }; */
  //回写物流单号 
  function updateOutSid(id){
	  $.ajax({
		     url:'${vix}/business/printExpressListAction!goUpdateOutSid.action?id='+id,
		     cache: false,
		     success: function(html){
		  }
	      }); 
  }
  //填写打印顺序
  function updatePrintIndex(id,printIndex){
	  $.ajax({
		     url:'${vix}/business/printExpressListAction!goUpdateShippingPrintIndex.action?id='+id+"&printIndex="+printIndex,
		     cache: false,
		     success: function(html){
		  }
	      }); 
  }
  //更改订单批次状态
  function updateOrderBatchState(id){
   $.ajax({
	     url:'${vix}/business/printExpressListAction!goUpdateOrderBatchState.action?id='+id,
	     cache: false,
	     success: function(html){
	  }
      }); 
  }
//post测试通过
function createPostPage(name1,name2,name3,name4,name5,name6,name7,name8,name9,name10) {
	LODOP = getLodop();
	LODOP.PRINT_INIT("");
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/post.jpg'>");
	LODOP.SET_PRINT_PAGESIZE(0,"230mm","127mm","");
	LODOP.SET_SHOW_MODE("BKIMG_WIDTH",870);
	LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",480);
	LODOP.ADD_PRINT_TEXTA("name1","43.1mm","16.4mm","26.5mm","5.3mm",name1);
	LODOP.ADD_PRINT_TEXTA("name2","42.3mm","55.8mm","26.5mm","5.3mm",name2);
	LODOP.ADD_PRINT_TEXTA("name3","54.8mm","21.2mm","63mm","12.2mm",name3);
	LODOP.ADD_PRINT_TEXTA("name4","69.6mm","15.9mm","26.5mm","5.3mm",name4);
	LODOP.ADD_PRINT_TEXTA("name6","42.6mm","88.9mm","44.4mm","39.7mm",name6);
	LODOP.PRINT();  
	/* LODOP.PREVIEW(); */
};
//测试通过Ems  订单编码生成规则还没有
function createEmsPage(name1,name2,name3,name4,name5,name6,name7,name8,name9,name10) {
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/ems.jpg'>");
	LODOP.SET_PRINT_PAGESIZE(0,"230mm","127mm","");
	LODOP.SET_SHOW_MODE("BKIMG_WIDTH",870);
	LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",480);
	LODOP.ADD_PRINT_TEXTA("name1","52.1mm","16.1mm","26.5mm","5.3mm",name1);
	LODOP.ADD_PRINT_TEXTA("name2","52.1mm","51.3mm","26.5mm","5.3mm",name2);
	LODOP.ADD_PRINT_TEXTA("name3","65.9mm","16.1mm","85.2mm","12.2mm",name3);
	LODOP.ADD_PRINT_TEXTA("name4","21.4mm","14mm","26.5mm","5.3mm",name4);
	LODOP.ADD_PRINT_TEXTA("name6","99.7mm","19.3mm","94.7mm","6.6mm",name6);
	LODOP.ADD_PRINT_TEXTA("name5","81mm","16.7mm","32mm","6.6mm",name5);
	/* LODOP.PRINT(); */ 
};
function createSfPage(name1,name2,name3,name4,name5,name6,name7,name8,name9,name10) {
	LODOP = getLodop();
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/sf.jpg'>");
	/* LODOP.PREVIEW(); */
};
//申通快递单打印测试通过
function createStoPage(name1,name2,name3,name4,name5,name6,name7,name8,name9,name10) {
	LODOP = getLodop();
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/sto.jpg'>");
	LODOP.SET_PRINT_PAGESIZE(0,"230mm","127mm","");
	LODOP.ADD_PRINT_TEXTA("name3","23mm","108mm","31.2mm","7.4mm",name3);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",18);
	LODOP.ADD_PRINT_TEXTA("name5","23mm","17.5mm","32mm","5.3mm",name5);
	LODOP.ADD_PRINT_TEXTA("name6","50.3mm","102.1mm","83.9mm","5.6mm",name6);
	LODOP.ADD_PRINT_TEXTA("name8","60.3mm","117.5mm","51.1mm","6.6mm",name8);
	LODOP.ADD_PRINT_TEXTA("name9","67.2mm","19.6mm","81.8mm","5.3mm",name9);
	LODOP.ADD_PRINT_TEXTA("name10","24.9mm","155.8mm","35.5mm","6.9mm",name10);
	LODOP.ADD_PRINT_TEXTA("name4","88.4mm","143.4mm","43.4mm","16.4mm",name4);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",30);
	LODOP.PRINT();
	/* LODOP.PREVIEW(); */  
};
//中通快递单打印测试通过
function createZtoPage(name1,name2,name3,name4,name5,name6,name7,name8,name9,name10) {
	LODOP = getLodop();
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/zto.jpg'>");
	LODOP.SET_PRINT_PAGESIZE(0,"230mm","127mm","");
	LODOP.SET_SHOW_MODE("BKIMG_WIDTH",870);
	LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",480);
	LODOP.ADD_PRINT_TEXTA("name2","27.8mm","18.5mm","30.2mm","7.4mm",name2);
	LODOP.ADD_PRINT_TEXTA("name3","27.3mm","101.9mm","32.5mm","5.8mm",name3);
	LODOP.ADD_PRINT_TEXTA("name4","94.2mm","125.1mm","49.2mm","14.6mm",name4);
	LODOP.SET_PRINT_STYLEA(0,"FontSize",30);
	LODOP.ADD_PRINT_TEXTA("name6","38.1mm","101.9mm","70.9mm","13.8mm",name6);
	LODOP.ADD_PRINT_TEXTA("name8","58.5mm","101.6mm","55.3mm","6.6mm",name8);
	LODOP.ADD_PRINT_TEXTA("name9","93.4mm","11.6mm","61.6mm","9.3mm",name9);
	LODOP.ADD_PRINT_TEXTA("name10","26.7mm","152.1mm","24.1mm","6.6mm",name10);
    LODOP.PRINT();  
    /* LODOP.PREVIEW(); */  
};

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/business/outBoundProcessAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				 pager("start","${vix}/business/outBoundProcessAction!goSearchList.action?orderCode="+$('#orderCode').val()+"&buyerNick="+$('#buyerNick').val()+"&receiverMobile="+$("#receiverMobile").val()+"&payTypeCn="+$('#payTypeCn').val(),'salesOrder');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">订单管理 </a></li>
				<li><a href="#">订单处理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<!-- <a href="#" onclick="chooseEmployees();"><span>分单</span> </a> -->
		<a href="#" onclick="saveOrUpdateThreeSorting(0);"><span>分拣 </span> </a>
	</div>
</div>
<input type="hidden" id="orderBatchId1" name="orderBatchId1" value="${orderBatchId}" />
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>订单编码:<input type="text" class="int" id="orderCode"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label> <label>
				<input type="button" value="高级搜索" class="btn" onclick="goSearch();" />
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="salesOrderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(5,1,'a',event,'order1')"><img src="img/holidaysam.png" alt="" />待分拣</a></li>
					<li><a onclick="categoryTab(5,2,'a',event,'order2')"><img src="img/adjust_view_day.png" alt="" />待打单</a></li>
					<!-- <li><a onclick="categoryTab(5,3,'a',event,'order3')"><img src="img/adjust_view_day.png" alt="" />待配货</a></li>
					<li><a onclick="categoryTab(5,4,'a',event,'order4')"><img src="img/adjust_view_day.png" alt="" />待发货</a></li> -->
					<li><a onclick="categoryTab(5,5,'a',event,'order5')"><img src="img/adjust_view_day.png" alt="" />已发货</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="order1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order1');"></a></span> <span><a class="end" onclick="categoryPager('end','order1');"></a></span>
					</div>
				</div>
				<div id="order1" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order1');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order1');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="order1TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order1');"></a></span> <span><a class="end" onclick="categoryPager('end','order1');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a2" style="display: none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order2');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order2');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="order2TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order2');"></a></span> <span><a class="end" onclick="categoryPager('end','order2');"></a></span>
					</div>
				</div>
				<div id="order2" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order2');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order2');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="order2TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order2');"></a></span> <span><a class="end" onclick="categoryPager('end','order2');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a3" style="display: none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order3');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order3');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="order3TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order3');"></a></span> <span><a class="end" onclick="categoryPager('end','order3');"></a></span>
					</div>
				</div>
				<div id="order3" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order3');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order3');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="order3TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order3');"></a></span> <span><a class="end" onclick="categoryPager('end','order3');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a4" style="display: none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order4');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order4');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="order4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order4');"></a></span> <span><a class="end" onclick="categoryPager('end','order4');"></a></span>
					</div>
				</div>
				<div id="order4" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order4');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order4');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="order4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order4');"></a></span> <span><a class="end" onclick="categoryPager('end','order4');"></a></span>
					</div>
				</div>
			</div>
			<div class="right_content" id="a5" style="display: none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order5');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order5');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="order4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order5');"></a></span> <span><a class="end" onclick="categoryPager('end','order5');"></a></span>
					</div>
				</div>
				<div id="order5" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete" /></a></li>
								<li><a href="#"><s:text name="cmn_email" /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','order5');"></a></span> <span><a class="previous" onclick="categoryPager('previous','order5');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="order4TotalCount">${pager.totalCount}</b>)
						</em> <span><a class="next" onclick="categoryPager('next','order5');"></a></span> <span><a class="end" onclick="categoryPager('end','order5');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>

<script type="text/javascript">
$(function(){
	if($('.fixed_header').length > 0 && $('#c_head').length > 0){
		var fixed_t = $('.content #c_head').offset().top -40;
		$(window).scroll(function(){
			if($(window).scrollTop() > fixed_t){
				$('.fixed_header').show();
			}else{
				$('.fixed_header').hide();
				}
		})
	}
});
</script>