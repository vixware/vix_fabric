<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 关键词自动回复</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<!-- 新增按钮 -->
						<div class="clearfix " style="margin-bottom:5px;">
							<div class="pull-right tableTools-container">
								<a href="#" class="btn btn-primary btn-sm" id="gritter-sticky" onclick="saveOrUpdate();"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
							</div>
						</div>
						<!--  -->
						<div class="jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
							
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>关键词自动回复</h2>
							</header>
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
												<th>规则名称</th>
												<th> 关键词</th>
												<th> 回复内容</th>
												<th>状态</th>
												<th> 操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>健身</td>
												<td><span class="label label-sm label-success">健身运动</span></td>
												<td>1条</td>
												<td>启用</td>
												<td>
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-info" onclick="openpage()">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button> 
													</div>
													<div class="hidden-sm hidden-xs btn-group">	
														<button class="btn btn-xs btn-danger" onclick="cutDiv()">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button> 
													</div>
												</td>
											</tr>
											<tr>
												<td>健身</td>
												<td><span class="label label-sm label-success">健身运动</span></td>
												<td>1条</td>
												<td>启用</td>
												<td>
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-info" onclick="openpage()">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button> 
													</div>
													<div class="hidden-sm hidden-xs btn-group">	
														<button class="btn btn-xs btn-danger" onclick="cutDiv()">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button> 
													</div>
												</td>
											</tr>
											<tr>
												<td>健身</td>
												<td><span class="label label-sm label-success">健身运动</span></td>
												<td>1条</td>
												<td>启用</td>
												<td>
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-info" onclick="openpage()">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button> 
													</div>
													<div class="hidden-sm hidden-xs btn-group">	
														<button class="btn btn-xs btn-danger" onclick="cutDiv()">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
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
<script type="text/javascript">
function cutDiv(){
	$(".dialog_appointment1").show();
};
function hideDiv1(){
	$(".dialog_appointment1").hide();
};

function openpage(){
	window.location.replace("./新增信息.html");
}
function saveOrUpdate(){
	$.ajax({
		url : '${nvix}/nvixnt/wx/wxMessageAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
}
</script>