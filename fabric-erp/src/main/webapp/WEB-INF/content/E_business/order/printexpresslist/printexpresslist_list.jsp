<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function goPrintList(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!goPrintList.action',
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/business/printExpressListAction!goSingleList.action?name="+name,'orderBatch');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/business/printExpressListAction!goSingleList.action?name="+name,'orderBatch');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/business/printExpressListAction!goSingleList.action?orderBatchId="+$("#orderBatchId").val(),'orderBatch');
//排序 
function orderBy(orderField){
	var orderBy = $("#orderBatchOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/printExpressListAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'orderBatch');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/business/printExpressListAction!goSingleList.action?name="+name,'orderBatch');
}
var orderid = "";
function chkChange1 (chk , id){
	if (chk.checked){
		orderid = orderid + "," + id;
	}
}
function printExpressList(id,pageNo){
	$.ajax({
		  url:'${vix}/business/printExpressListAction!goPrint.action?businussOrderId='+orderid,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};

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
		  url:'${vix}/business/printExpressListAction!goUpdateLogisticsNumbers.action',
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
							bahtPrintExpressList($("#outSid").val(),$("#logisticsId").val());
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
		  url:'${vix}/business/printExpressListAction!goUpdateLogisticsNumbers.action',
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
								bahtPreviewPrintExpressList($("#outSid").val(),$("#logisticsId").val());
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
				 printExpressList("聚会美","聚会美","北京朝阳区",orderlist[i].receiverName,"",orderlist[i].receiverAddress,orderlist[i].itemTitle,orderlist[i].amount,"13588887676",orderlist[i].receiverPhone);
				  //回写物流单号 
			      updateOutSid(orderlist[i].id);
				}
		       } 
		       //更改订单批次状态
		       updateOrderBatchState($("#orderBatchId").val());
			}
		});
      };
	function bahtPreviewPrintExpressList(outSid,logisticsId){
	   	  $.ajax({
	   	    url:'${vix}/business/printExpressListAction!goPrintExpressList.action?ids='+orderid+"&outSid="+outSid+"&logisticsId="+logisticsId,
			cache: false,
			success: function(json){ 
			var obj = eval('('+json+')'); 
			var orderlist = obj.rows;
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
	             updateOutSid(orderlist[i].id);
                 } 
                 //更改订单批次状态
                  updateOrderBatchState($("#orderBatchId").val());
			  }
		});
      };
      //回写物流单号 
      function updateOutSid(id){
    	  $.ajax({
 		     url:'${vix}/business/printExpressListAction!goUpdateOutSid.action?id='+id,
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
  		    	/*  loadContent('${vix}/business/printExpressListAction!goList.action'); */
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
		LODOP = getLodop();
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
		LODOP.PRINT(); 
		/* LODOP.PREVIEW(); */
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
</script>
<input type="hidden" id="orderBatchId" name="orderBatchId" value="${orderBatchId}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">订单管理 </a></li>
				<li><a href="#">打印快递单 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<!-- <a href="#" onclick="setupExpressList();"><span>打印维护</span> </a> -->
		<a href="#" onclick="updatePreviewLogisticsNumbers();"><span>打印</span> </a>
		<!-- <a href="#" onclick="updateLogisticsNumbers();"><span>打印</span> </a> -->
		<a href="#" onclick="loadContent('${vix}/business/outBoundProcessAction!goList.action');"><span>返回</span> </a>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""> </label> <label> <input type="button"
				value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="search_advanced">
			<label><s:text name="drp_order_on1" /><input type="text" class="int" name=""> </label><label><s:text name="drp_order_date1" /><input type="text" class="int" name=""> </label><label><s:text name="drp_customer_name1" /><input type="text" class="int" name=""> </label><label><s:text name="drp_department1" /><input
				type="text" class="int" name=""> </label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesOrderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderBatchInfo"></b> <s:text name='cmn_to' /> <b class="orderBatchTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="orderBatch" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderBatchInfo"></b> <s:text name='cmn_to' /> <b class="orderBatchTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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