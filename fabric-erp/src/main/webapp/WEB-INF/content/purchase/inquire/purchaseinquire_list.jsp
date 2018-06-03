<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
	_pad_page_refresh_main_content = true;

	$(function() {
		//载入tab数据
		_load_tab_page_content();

		loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
	});

	function saveOrUpdate(id, parentId, purchaseType) {
		var url = '${vix}/purchase/purchaseInquireAction!goSaveOrUpdate.action';
		var data = {};
		if (id != null) {
			data.id = id;
		}
		if (parentId != null) {
			data.parentId = parentId;
		}

		if (purchaseType && purchaseType != '') {
			var copyUrl = '';
			if (purchaseType == 'apply') {
				copyUrl = '${vix}/purchase/purchaseOrderAction!goChoosePurchaseApply.action';
			} else if (purchaseType == 'inquire') {
				copyUrl = '${vix}/purchase/purchaseOrderAction!goChoosePurchaseInquire.action';
			} else if (purchaseType == 'order') {
				copyUrl = '${vix}/purchase/purchaseArrivalAction!goChoosePurchaseOrders.action';
			}
			$.ajax({
			url : copyUrl,
			cache : false,
			success : function(html) {
				asyncbox.open({
				modal : true,
				width : 1024,
				height : 550,
				title : "选择复制单据",
				html : html,
				callback : function(action, returnValue) {
					if (action == 'ok') {
						if (returnValue != '') {
							data.purchaseType = purchaseType;
							data.purchaseId = returnValue;

							var itemdetails = '';
							$('#item_detail_' + returnValue + ' .item_check:checked').each(function() {
								var itemId = $(this).val();
								var amount = $('#amount_' + itemId).val();
								if (amount && !isNaN(amount) && amount > 0)
									itemdetails = itemdetails + ',' + itemId + '_' + amount;
							});

							if (itemdetails != '') {
								itemdetails = itemdetails.substring(1);
								data.itemDetails = itemdetails;
							}

							_pad_page_view_push(url, data);
						} else {
							asyncbox.success("请选择订单!", "<s:text name='vix_message'/>");
							return false;
						}
					}
				},
				btnsbar : $.btn.OKCANCEL
				});
			}
			});
		} else {
			_pad_page_view_push(url, data);
		}
	}

	function showPurchaseInquire(id) {
		$.ajax({
		url : '${vix}/purchase/purchaseInquireAction!goShowPurchaseInquire.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}

	function goPrintPurchaseInquire(id) {
		$.ajax({
		url : '${vix}/purchase/purchaseInquireAction!goPrintPurchaseInquire.action?id=' + id,
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

	function goSearch() {
		$
				.ajax({
				url : '${vix}/purchase/purchaseInquireAction!goSearch.action',
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 650,
							height : 300,
							title : "查询条件",
							html : html,
							callback : function(action) {
								if (action == 'ok') {
									pager("start", "${vix}/purchase/purchaseInquireAction!goSingleList.action?code=" + $('#code').val() + "&name=" + $('#name').val() + "&purchasePerson=" + $('#purchasePerson').val() + "&appDate=" + $('#appDate')
											.val(), 'purchase_inquire_grid');
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
		<i> <a href="#" class="nav_print_btn"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#" class="nav_help_btn"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
	<div class="drop">
		<p>
		<ul>
			<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span>新增采购询价</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val());">普通采购询价</a></li>
					<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val(),'apply');">来源自采购申请单</a></li>
					<li><a href="#" onclick="saveOrUpdate(0,$('#selectId').val(),'order');">来源自采购订单</a></li>
				</ul></li>
		</ul>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<ul>
			<li><a href="#" id="numBtn" class="num_btn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="code" class="int more" placeholder="编码"></label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>

	<div id="number" class="quick_index">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="indexEntityList" var="index">
				<li><a href="#" onclick="saveOrUpdate('${index.id}');"><span style="display: none;">${index.chineseCharacter}</span>${index.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->

	<div class="box">
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/purchase/purchaseInquireAction!goSingleList.action"> <img src="img/mail.png" alt="" /> 供应询价单
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
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
