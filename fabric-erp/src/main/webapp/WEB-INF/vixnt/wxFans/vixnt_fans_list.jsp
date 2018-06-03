<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 微信粉丝</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="tab_menu">
					        <ul>
					            <li class="selected">关注粉丝</li>
					            <li >取消关注粉丝</li>
					            <li >全部粉丝</li>
					        </ul>
					    </div>
						<div class="tab_con">
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>关注粉丝</h2>
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
													<th>头像</th>
													<th>昵称</th>
													<th>性别</th>
													<th>地址</th>
													<th>关注时间</th>
													<th>创建时间</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
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
						
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>取消关注粉丝</h2>
								</header>
								<!-- widget div-->
								<div>
									<!-- widget edit box -->
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
													<th data-hide="phone">头像</th>
													<th data-class="expand">昵称</th>
													<th data-hide="phone">性别</th>
													<th>地址</th>
													<th data-hide="phone,tablet">关注时间</th>
													<th data-hide="phone,tablet">创建时间</th>
													<th data-hide="phone,tablet">操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
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
						
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>全部粉丝</h2>
								</header>
								<!-- widget div-->
								<div>
									<!-- widget edit box -->
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
													<th data-hide="phone">头像</th>
													<th data-class="expand">昵称</th>
													<th data-hide="phone">性别</th>
													<th>地址</th>
													<th data-hide="phone,tablet">关注时间</th>
													<th data-hide="phone,tablet">创建时间</th>
													<th data-hide="phone,tablet">操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
															</button>
														</div>	
													</td>
												</tr>
												<tr>
													<td>
														<img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh1RHTXgDyIhzJYCt4VDPuQdAyyqrmFPpibpzhcZqaovH3NS1goVKpRcF2rvrNUlCiav51xTpMtDDPI/46" style="height:46px;">
													</td>
													<td>减肥的歌</td>
													<td><span class="label label-sm label-success">男</span></td>
													<td>中国&nbsp;&nbsp;北京&nbsp;&nbsp;海淀</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														2017-11-07 15:00:23
													</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group" title="修改备注">
															<button class="btn btn-xs btn-info" onclick="showDiv();">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="打印">
															<button class="btn btn-xs btn-info" onclick="">
																 <i class="fa fa-gavel"></i>
															</button>
														</div>
														<div class="hidden-sm hidden-xs btn-group" title="刷新">
															<button class="btn btn-xs btn-info" onclick="showDiv1()">
																 <i class="fa fa-refresh"></i>
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
						<div class="col-xs-6 txt-color-white" style="line-height: 35px;">设置粉丝备注名</div>
						<div class="col-xs-6" style="line-height: 35px;text-align:right;">
							<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv()">关闭</button>
						</div>
					</div>
				</div>
				<div class="dialog_appointment_main">
					<div class="row">
						 <div class="col-xs-12">
								<form id="wxpuserEditForm" method="post" class="form-horizontal" style="margin-bottom:0px;">
									<input type="hidden" name="openId" value="oBZ5_t-1038AKoppHpLLmzW_QxEI">
									<div class="form-group">
										<label class="col-sm-3 control-label" style="padding-right:0;" for="form-field-1"><span class="txt-color-red"><strong>*</strong></span>备注名称:</label>
										<div class="controls col-sm-9">
											<input type="text" name="remarkName" placeholder="请填写名称" value="" class="validate[required,maxSize[30]] col-xs-10 col-sm-10" data-prompt-position="centerRight">
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
	
	<!-- 弹出框 -->
	<div class="dialog_appointment1">
		<div class="dialog_appointment1_middle">
			<div class="dialog_appointment1_con">
				<div class="dialog_appointment1_main">是否重新加载该粉丝信息？</div>
				<div class="dialog_appointment1_footer">
					<button class="btn btn-sm btn-primary">保存</button>
					<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv1()">关闭</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
	    var tab_menu_li = $('.tab_menu ul li');
	    $('.tab_con .common:gt(0)').hide();
	    tab_menu_li.click(function(){
	        $(this).addClass('selected').siblings().removeClass('selected');
	        var tab_menu_li_index = tab_menu_li.index(this);
	        $('.tab_con .common').eq(tab_menu_li_index).show().siblings().hide();
	    })
	});
	function showDiv(){
		$(".dialog_appointment").show();
	};
	function hideDiv(){
		$(".dialog_appointment").hide();
	}
	function showDiv1(){
		$(".dialog_appointment1").show();
	};
	function hideDiv1(){
		$(".dialog_appointment1").hide();
	}
</script>