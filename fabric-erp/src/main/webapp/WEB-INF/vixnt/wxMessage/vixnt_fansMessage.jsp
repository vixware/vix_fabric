<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 粉丝消息</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						
						<!-- 内容部分 -->
						<div class="jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
							
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>粉丝消息</h2>
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
												<th >头像</th>
												<th >昵称</th>
												<th >性别</th>
												<th>内容</th>
												<th >创建时间</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh8OnZzo33AM61FrgASHIS3OYx4tR0IHD1tA0c7DwT6Y3aGuWRQgFibtpDxM4dXHiaFRC4UqVgE66ibO/46" style="height:46px;"></td>
												<td>减肥的歌</td>
												<td><span class="label label-sm label-danger">女</span></td>
												<td>哈哈哈哈哈哈</td>
												<td>
													2017-11-07 15:00:23
												</td>
											</tr>
											<tr>
												<td><img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh8OnZzo33AM61FrgASHIS3OYx4tR0IHD1tA0c7DwT6Y3aGuWRQgFibtpDxM4dXHiaFRC4UqVgE66ibO/46" style="height:46px;"></td>
												<td>减肥的歌</td>
												<td><span class="label label-sm label-danger">女</span></td>
												<td>哈哈哈哈哈哈</td>
												<td>
													2017-11-07 15:00:23
												</td>
											</tr>
											<tr>
												<td><img src="http://wx.qlogo.cn/mmopen/olpHM1eiaLSoiaFmZzC6CRh8OnZzo33AM61FrgASHIS3OYx4tR0IHD1tA0c7DwT6Y3aGuWRQgFibtpDxM4dXHiaFRC4UqVgE66ibO/46" style="height:46px;"></td>
												<td>减肥的歌</td>
												<td><span class="label label-sm label-danger">女</span></td>
												<td>哈哈哈哈哈哈</td>
												<td>
													2017-11-07 15:00:23
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