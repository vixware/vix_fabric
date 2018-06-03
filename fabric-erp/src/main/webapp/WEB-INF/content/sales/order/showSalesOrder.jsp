<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//打印
function goPrintSalesOrder(id) {
	$.ajax({
	url : '${vix}/sales/order/salesOrderAction!goPrintSalesOrder.action?id=' + id,
	cache : false,
	success : function(html) {
		LODOP = getLodop();
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
		// LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
		LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
		/* LODOP.PRINT(); */
		LODOP.PREVIEW();
	}
	});
};
</script>
<s:if test="tag != 'window'">
	<div class="sub_menu">
		<h2>
			<i> <a href="#"><img alt="" src="img/icon_14.gif">
				<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
				<s:text name="help" /></a>
			</i>
			<div id="breadCrumb" class="breadCrumb module">
				<ul>
					<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" alt="" />
						<s:text name="cmn_home" /></a></li>
					<li><a href="#">供应链</a></li>
					<li><a href="#">销售管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action');">销售订单</a></li>
				</ul>
			</div>
		</h2>
	</div>
</s:if>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<%-- <s:if test="tag != 'window'">
					<span class="no_line">
						<s:if test="taskId == null">
							<a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png"/></a>
						</s:if>
						<s:else>
							<a onclick="goAudit('${taskId}');" href="###"><img width="22" height="22" alt="提交审批" src="${vix}/common/img/dt_aprroval.png"/></a>
							<a href="#" onclick="loadContent('${vix}/common/model/jobTodoAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png"/></a>
						</s:else>
					</span>
				</s:if> --%>
				<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
						width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfter('${salesOrder.id }','before');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png">
				</a> <a href="#" onclick="goShowBeforeAndAfter('${salesOrder.id }','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintSalesOrder('${salesOrder.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
					<div class="ms" style="float: none; display: inline;">
						<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
						<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
							<li><a href="#" onclick="goPrintSalesQuotation('${salesOrder.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
						</ul>
					</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b> <s:if test="salesOrder.title != null ">
							${salesOrder.title}
						</s:if>
				</b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher" style="margin: 0;">
						<dl>
							<dt>
								<b></b> <strong>基本信息</strong>
							</dt>
							<dd style="display: block;">
								<table class="addtable_c">
									<tr>
										<td align="right" width="15%">编码:</td>
										<td width="35%">${salesOrder.code}</td>
										<td align="right" width="10%">主题:</td>
										<td width="40%">${salesOrder.title}</td>
									</tr>
									<tr>
										<td align="right">单据日期:</td>
										<td><s:property value='formatDateToString(salesOrder.billDate)' /></td>
										<s:if test="tag == 'fromQuote'">
											<td align="right">来源单据:</td>
											<td><span>销售报价单</span></td>
										</s:if>
										<s:else>
											<td align="right">来源单据:</td>
											<td>无</td>
										</s:else>
									</tr>
									<tr>
										<td align="right" width="10%">客户名称:</td>
										<td width="40%">${salesOrder.customerAccount.name}</td>
										<td align="right">联系人:</td>
										<td>${salesOrder.contactPerson.name}</td>
									</tr>
									<tr>
										<td align="right">项目:</td>
										<td>${salesOrder.crmProject.subject}</td>
										<td align="right">业务类型:</td>
										<td>${salesOrder.bizType}</td>
									</tr>
									<tr>
										<td align="right">币种:</td>
										<td>${salesOrder.currencyType.name}</td>
										<td align="right">地域:</td>
										<td>${salesOrder.regional.name}</td>
									</tr>
									<tr>
										<td align="right">销售组织:</td>
										<td>${salesOrder.saleOrg.fullName}</td>
										<td align="right">计划发运日期:</td>
										<td><s:property value='formatDateToTimeString(salesOrder.deliveryTimeInPlan)' /></td>
									</tr>
									<tr>
										<td align="right">承诺日期:</td>
										<td><s:property value='formatDateToTimeString(salesOrder.promiseTime)' /></td>
										<td align="right">过账日期:</td>
										<td><s:property value='formatDateToTimeString(salesOrder.postingTime)' /></td>
									</tr>
									<tr>
										<td align="right">状态:</td>
										<td><s:if test="salesOrder.status == 0">禁用</s:if> <s:elseif test="salesOrder.status == 1">激活</s:elseif></td>
										<td align="right">业务员:</td>
										<td>${salesOrder.salePerson.name}</td>
									</tr>
									<tr>
										<td align="right">开始日期:</td>
										<td><s:property value='formatDateToTimeString(salesOrder.startTime)' /></td>
										<td align="right">结束日期:</td>
										<td><s:property value='formatDateToTimeString(salesOrder.endTime)' /></td>
									</tr>
									<tr>
										<td align="right">备注</td>
										<td colspan="3">${salesOrder.memo}</td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
			<div class="clearfix" style="background: #FFF; position: relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(4,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						<li><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发运计划</a></li>
						<li><a onclick="javascript:$('#a3').attr('style','');tab(4,3,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发票</a></li>
						<li><a onclick="javascript:$('#a4').attr('style','');tab(4,4,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
					</ul>
				</div>
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<div style="padding: 8px;">
						<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px; width: 740px;" data-options="fitColumns:true,singleSelect: true,url: '${vix}/sales/order/salesOrderAction!getSaleOrderItemJson.action?id=${salesOrder.id}'">
							<thead>
								<tr>
									<th data-options="field:'item.id',align:'center',width:60"></th>
									<th data-options="field:'id',align:'center',width:60">编号</th>
									<th data-options="field:'barCode',width:80,align:'center'">条形码</th>
									<th data-options="field:'skuCode',width:80,align:'center'">SKU</th>
									<th data-options="field:'itemCode',width:80,align:'center'">编码</th>
									<th data-options="field:'itemName',width:120,align:'center'">名称</th>
									<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
									<th data-options="field:'measureUnit',width:80,align:'center'">主计量单位</th>
									<th data-options="field:'amount',width:60,align:'center'">数量</th>
									<th data-options="field:'taxPrice',width:60,align:'center'">含税单价</th>
									<th data-options="field:'price',width:60,align:'center'">金额</th>
									<th data-options="field:'netPrice',width:60,align:'center'">无税单价</th>
									<th data-options="field:'netTotal',width:60,align:'center'">无税金额</th>
									<th data-options="field:'tax',width:60,align:'center'">税率</th>
									<th data-options="field:'discount',width:60,align:'center'">折扣率</th>
									<th data-options="field:'requireDate',width:80,align:'center'">预发货日期</th>
									<th data-options="field:'memo',width:120,align:'center'">备注</th>
								</tr>
							</thead>
						</table>
						<script type="text/javascript">
							$('#item').datagrid();
							$('#item').datagrid('hideColumn','item.id');
					</script>
					</div>
				</div>
				<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<div style="padding: 8px;">
						<table id="deliveryPlan" class="easyui-datagrid" style="height: 380px; margin: 6px; width: 740px;" data-options="iconCls: 'icon-edit',singleSelect: true,url: '${vix}/sales/order/salesOrderAction!getSalesDeliveryPlanJson.action?id=${salesOrder.id}'">
							<thead>
								<tr>
									<th data-options="field:'id',width:60">编号</th>
									<th data-options="field:'salesOrder.code',width:120,align:'center',editor:'text'">订单编号</th>
									<th data-options="field:'deliveryTime',width:80,align:'center',editor:'datebox'">发运时间</th>
									<th data-options="field:'country',width:90,align:'center',editor:'text'">国家</th>
									<th data-options="field:'province',width:90,align:'center',editor:'text'">省</th>
									<th data-options="field:'city',width:100,align:'center',editor:'text'">城市</th>
									<th data-options="field:'target',width:200,align:'center',editor:'text'">目的地</th>
									<th data-options="field:'carrier',width:100,align:'center',editor:'text'">承运人</th>
									<th data-options="field:'reciever',width:100,align:'center',editor:'text'">收货人</th>
								</tr>
							</thead>
						</table>
						<script type="text/javascript">
							$('#deliveryPlan').datagrid();
						</script>
					</div>
				</div>
				<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<div style="padding: 8px;">
						<table id="salesTicket" class="easyui-datagrid" style="height: 380px; margin: 6px; width: 740px;" data-options="iconCls: 'icon-edit',singleSelect: true,url: '${vix}/sales/order/salesOrderAction!getSalesTicketJson.action?id=${salesOrder.id}'">
							<thead>
								<tr>
									<th data-options="field:'id',align:'center',width:60">编号</th>
									<th data-options="field:'title',width:120,align:'center',editor:'text'">名称</th>
									<th data-options="field:'taxpayerPlayer',width:100,align:'center',editor:'text'">纳税人识别号</th>
									<th data-options="field:'ticketAmount',width:100,align:'center',editor:'text'">发票金额</th>
									<th data-options="field:'ticketCount',width:220,align:'center',editor:'text'">发票数量</th>
								</tr>
							</thead>
						</table>

						<script type="text/javascript">
							$('#salesTicket').datagrid();
						</script>
					</div>
				</div>
				<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<div style="padding: 8px;">
						<table id="soAttach" class="easyui-datagrid" style="height: 380px; margin: 6px; width: 740px;" data-options="iconCls: 'icon-edit',singleSelect: true,url: '${vix}/sales/order/salesOrderAction!getAttachFileJson.action?id=${salesOrder.id}'">
							<thead>
								<tr>
									<th data-options="field:'id',align:'center',width:60">编号</th>
									<th data-options="field:'name',width:260,align:'center',editor:'text'">名称</th>
									<th data-options="field:'type',width:60,align:'center',editor:'text'">类型</th>
									<th data-options="field:'memo',width:320,align:'center',editor:'text'">备注</th>
								</tr>
							</thead>
						</table>
						<script type="text/javascript">
							$('#soAttach').datagrid();
						</script>
					</div>
				</div>
			</div>
			<dd class="clearfix">
				<div class="order_table">
					<table class="addtable_c">
						<tr>
							<td width="15%" align="right">小计:</td>
							<td width="35%"><input id="amount" name="amount" value="${salesOrder.amount}" type="text" size="30" readonly="readonly" /></td>
							<td width="10%" align="right">运费:</td>
							<td width="40%"><input id="freight" name="freight" type="text" value="${salesOrder.freight}" size="30" onblur="calculateAmount();" /></td>
						</tr>
						<tr>
							<td align="right">税率:</td>
							<td><input id="taxRate" name="taxRate" value="0.17" type="text" size="30" /></td>
							<td align="right">税额:</td>
							<td><input id="tax" name="tax" value="${salesOrder.tax}" type="text" size="30" /></td>
						</tr>
						<tr>
							<td align="right"></td>
							<td></td>
							<td align="right">总金额:</td>
							<td><input id="amountTotal" name="amountTotal" value="${salesOrder.amountTotal}" type="text" readonly="readonly" size="30" /></td>
						</tr>
					</table>
				</div>
			</dd>
		</dl>
	</div>
	<!--oder-->
</div>
<!-- content -->
<script type="text/javascript">
	$(function(){
		$.fn.fix = function(options){
			var defaults = {
				'advance' : 0,
				'top' : 0
			}
			options = $.extend(defaults, options);
			return this.each(function(){
				var $_this = $(this);
				$_this.wrap('<div></div>');
				var wp = $_this.parent('div');
				wp.height(wp.height());
				var ofst = wp.offset();
				
				if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}
				$(window).scroll(function(){
					if(!$_this.is(':visible')){
						return ;
					}
					
					if($(window).scrollTop() + options.advance > wp.offset().top){
						$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
					}else{
						$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
					}
				});
			});
		}
		$('#a1 .right_btn,#a3 .right_btn').fix({'advance':38,'top':38});
	});
	
	function calculateAmount(){
		var amount = $("#amount").val();
		var freight = $("#freight").val();
		if(amount != ''){
			if(freight == ''){
				$("#amountTotal").val(Number(amount));
				$("#tax").val(Number(amount)*0.17);
			}else{
				$("#amountTotal").val(Number(amount)+Number(freight));
				$("#tax").val(Number(amount)*0.17);
			}
		}
	}
	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
 
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});
 
</script>