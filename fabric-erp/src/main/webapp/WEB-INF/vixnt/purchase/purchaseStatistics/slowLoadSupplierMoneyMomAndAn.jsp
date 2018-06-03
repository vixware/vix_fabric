<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js" type="text/javascript"></script>
<style>
	.txt-color-mygreen { /* 圆环显示‘环比’‘同比’的颜色style  */
		color: #00FF00 !important
	}
</style>
<div class="row">
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
														<h2>供应商采购订单总金额环比<i class="${moneyNumMomfa }" style="color:${moneyNumMomColor};"></i></h2>
													</header>
													<div>
														<div class="widget-body no-padding"  >
														<center>
															<div style="height: 15px;"></div>
															<div class="easy-pie-chart txt-color-${moneyNumMomClass} easyPieChart" data-percent="${moneyNumMom}" data-pie-size="180">
																<span class="percent percent-sign txt-color-${moneyNumMomClass} font-xl semi-bold">${moneyNumMom}</span>
															</div> 
															<div style="height: 15px;"></div>
														</center>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>供应商采购订单总金额同比<i class="${moneyNumAnfa }" style="color:${moneyNumAnColor};"></i></h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<center>
															<div style="height: 15px;"></div>
															<div class="easy-pie-chart txt-color-${moneyNumAnClass } easyPieChart" data-percent="${moneyNumAn}" data-pie-size="180">
																<span class="percent percent-sign txt-color-${moneyNumAnClass } font-xl semi-bold">${moneyNumAn}</span>
															</div> 
															<div style="height: 15px;"></div>
															</center>
														</div>
													</div>
												</div>
											</div>
										</div>
<script type="text/javascript">
$(document).ready(function() {
	 pageSetUp(); 
});
 </script>