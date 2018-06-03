<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 微信支付配置</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="tab_con">
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>微信支付配置</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox"></div>
									<div class="widget-body no-padding">
										<div class="dt-toolbar">
											<div class="col-xs-12 col-sm-6">
												<div id="dt_basic_filter" class="dataTables_filter">
													<label>
														<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span> 
														<input type="search" class="form-control" placeholder="" aria-controls="dt_basic">
													</label>
												</div>
											</div>
											<div class="col-sm-6 col-xs-12 hidden-xs">
												<div class="dataTables_length" id="dt_basic_length">
													<label><select name="dt_basic_length" aria-controls="dt_basic" class="form-control">
													<option value="10">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
													</select></label>
												</div>
											</div>
										</div>
										<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th >订单名称</th>
													<th >商户号</th>
													<th >支付秘钥</th>
													<th >店铺服务器</th>
													<th >所属公众号</th>
													<th >创建时间</th>
													<th >操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>北京中盛盈创</td>
													<td>12369845982</td>
													<td>e3a1915f5a1a090cc03959611d4f284c</td>
													<td>112.124.5.161</td>
													<td>北京中盛盈创</td>
													<td>2017-11-07 12:20</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<button class="btn btn-xs btn-primary" onclick="showDiv()">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<div class="dt-toolbar-footer">
											<div class="col-sm-6 col-xs-12 hidden-xs">
												<div class="dataTables_info" id="dt_basic_info" role="status" aria-live="polite">
													共3条记录
												</div>
											</div>
											<div class="col-xs-12 col-sm-6">
												<div class="dataTables_paginate paging_simple_numbers" id="dt_basic_paginate">
													<ul class="pagination pagination-sm">
														<li class="paginate_button previous disabled" aria-controls="dt_basic" tabindex="0" id="dt_basic_previous"><a href="#">上一页</a></li>
														<li class="paginate_button active" aria-controls="dt_basic" tabindex="0"><a href="#">1</a></li>
														<li class="paginate_button next" aria-controls="dt_basic" tabindex="0" id="dt_basic_next"><a href="#">下一页</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 弹框 -->
	<div class="dialog_appointment">
		<div class="dialog_appointment_middle_m">
			<div class="dialog_appointment_con_m">
				<div class="dialog_appointment_head">
					<div class="row">
						<div class="col-xs-6 txt-color-white" style="line-height: 35px;">微信支付配置</div>
						<div class="col-xs-6" style="line-height: 35px;text-align:right;">
							<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv()">关闭</button>
						</div>
					</div>
				</div>
				<div class="dialog_appointment_main">
					<div class="row">
						 <div class="col-xs-12">
								<form id="wxPayEditForm" method="post" class="form-horizontal" style="margin-bottom:0px;">
									<input type="hidden" name="wxPayConfig.id" value="a9fe3cad-5e5c-1ceb-815e-5f4df5bf0029" id="wxPayConfigId">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>所属公众号:</label>
										<div class="controls col-sm-9">
											<input type="text" placeholder="北京中盛盈创" readonly="readonly" class="col-xs-10 col-sm-10">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>商户号:</label>
										<div class="controls col-sm-9">
											<input type="text" name="wxPayConfig.mchId" placeholder="请填写商户号"  class="validate[required] col-xs-10 col-sm-10" data-prompt-position="centerRight">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>支付秘钥:</label>
										<div class="controls col-sm-9">
											<input type="text" name="wxPayConfig.privateKey" placeholder="请填写支付秘钥" class="validate[required]" data-prompt-position="centerRight"  >
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>订单名称:</label>
										<div class="controls col-sm-9">
											<input type="text" name="wxPayConfig.payBody" placeholder="请填写订单名称" class="validate[required] col-xs-10 col-sm-10" data-prompt-position="centerRight">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>店铺服务器:</label>
										<div class="controls col-sm-9">
											<input type="text" name="wxPayConfig.spbillCreateIp" placeholder="请填写店铺服务器" class="validate[required]" data-prompt-position="centerRight">
										</div>
									</div>
								</form>
						</div>
					</div>
				</div>
				<div class="dialog_appointment_footer">
					<button class="btn btn-sm btn-primary ">保存</button>
					<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	    $(function(){
	        var tab_menu_li = $('.tab_menu ul li');
	        $('.tab_con .common:gt(0)').hide();
	        tab_menu_li.click(function(){
	            $(this).addClass('selected').siblings().removeClass('selected');
	            var tab_menu_li_index = tab_menu_li.index(this);
	            $('.tab_con .common').eq(tab_menu_li_index).show().siblings().hide();
	        })
	    });
	</script>
	<script>
		function showDiv(){
			$(".dialog_appointment").show();
		};
		function hideDiv(){
			$(".dialog_appointment").hide();
		}
	</script>