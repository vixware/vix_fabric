<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 模板消息</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="tab_menu">
					        <ul>
					            <li class="selected" id="wxpPlugin">我的模板</li>
					            <li>模板消息记录</li>
					        </ul>
					    </div>
						<!-- 内容部分 -->
						<div class="tab_con">
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>我的模板</h2>
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
													<th>编号</th>
													<th>id（腾讯）</th>
													<th> 标题</th>
													<th>行业</th>
													<th> 例子</th>
													<th> 例子</th>
													<th> 操作</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>OPENTM408249170</td>
													<td>70caVjsU_PeuHhQjuEizYSHiDaZnhF3rEb5zqd9qG1c</td>
													<td>签到成功通知</td>
													<td>文体娱乐-体育</td>
													<td>
														{{first.DATA}}<br>签到类型：{{keyword1.DATA}}<br>签到时间：{{keyword2.DATA}}<br>截止时间：{{keyword3.DATA}}<br>{{remark.DATA}}
													</td>
													<td>
														签到成功通知！<br>签到类型：游泳季卡<br>签到时间：2016-11-23 14:20<br>截止时间：2016-11-30<br>如有疑问请联系客服：400-888-888!
													</td>
													<td></td>
												</tr>
												<tr>
													<td>OPENTM408249170</td>
													<td>70caVjsU_PeuHhQjuEizYSHiDaZnhF3rEb5zqd9qG1c</td>
													<td>签到成功通知</td>
													<td>文体娱乐-体育</td>
													<td>
														{{first.DATA}}<br>签到类型：{{keyword1.DATA}}<br>签到时间：{{keyword2.DATA}}<br>截止时间：{{keyword3.DATA}}<br>{{remark.DATA}}
													</td>
													<td>
														签到成功通知！<br>签到类型：游泳季卡<br>签到时间：2016-11-23 14:20<br>截止时间：2016-11-30<br>如有疑问请联系客服：400-888-888!
													</td>
													<td></td>
												</tr>
												<tr>
													<td>OPENTM408249170</td>
													<td>70caVjsU_PeuHhQjuEizYSHiDaZnhF3rEb5zqd9qG1c</td>
													<td>签到成功通知</td>
													<td>文体娱乐-体育</td>
													<td>
														{{first.DATA}}<br>签到类型：{{keyword1.DATA}}<br>签到时间：{{keyword2.DATA}}<br>截止时间：{{keyword3.DATA}}<br>{{remark.DATA}}
													</td>
													<td>
														签到成功通知！<br>签到类型：游泳季卡<br>签到时间：2016-11-23 14:20<br>截止时间：2016-11-30<br>如有疑问请联系客服：400-888-888!
													</td>
													<td></td>
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
						
							<div class="common jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false" style="">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>模板消息记录</h2>
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
													<th data-hide="phone">模板名称</th>
													<th data-class="expand">消息内容</th>
													<th data-hide="phone">会员/员工/教练/会籍顾问</th>
													<th>场馆</th>
													<th data-hide="phone,tablet">创建时间</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>绑定成功通知</td>
													<td>
														恭喜！您已成功绑定线下员工，详情：<br>用户名：林水瑶(15124326531)<br>用户类型：会籍顾问<br>绑定日期：2017-11-07 09:37<br>感谢您的支持，如有疑问，请与客服人员联系。
													</td>
													<td>林水姚（会籍顾问）</td>
													<td>荆棘鸟健身（知春路店）</td>
													<td>
														2017-11-07 14:50
													</td>
												</tr>
												<tr>
													<td>绑定成功通知</td>
													<td>
														恭喜！您已成功绑定线下员工，详情：<br>用户名：林水瑶(15124326531)<br>用户类型：会籍顾问<br>绑定日期：2017-11-07 09:37<br>感谢您的支持，如有疑问，请与客服人员联系。
													</td>
													<td>林水姚（会籍顾问）</td>
													<td>荆棘鸟健身（知春路店）</td>
													<td>
														2017-11-07 14:50
													</td>
												</tr>
												<tr>
													<td>绑定成功通知</td>
													<td>
														恭喜！您已成功绑定线下员工，详情：<br>用户名：林水瑶(15124326531)<br>用户类型：会籍顾问<br>绑定日期：2017-11-07 09:37<br>感谢您的支持，如有疑问，请与客服人员联系。
													</td>
													<td>林水姚（会籍顾问）</td>
													<td>荆棘鸟健身（知春路店）</td>
													<td>
														2017-11-07 14:50
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
	</div>
	<div class="page-footer">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<span class="txt-color-white">SmartAdmin 1.5 <span class="hidden-xs"> - Web Application Framework</span> © 2014-2015
				</span>
			</div>

			<div class="col-xs-6 col-sm-6 text-right hidden-xs">
				<div class="txt-color-white inline-block">
					<i class="txt-color-blueLight hidden-mobile">Last account activity <i class="fa fa-clock-o"></i> <strong>52 mins ago &nbsp;</strong>
					</i>
					<div class="btn-group dropup">
						<button class="btn btn-xs dropdown-toggle bg-color-blue txt-color-white" data-toggle="dropdown">
							<i class="fa fa-link"></i> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right text-left">
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">Download Progress</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-success" style="width: 50%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">Server Load</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-success" style="width: 20%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<p class="txt-color-darken font-sm no-margin">
										Memory Load <span class="text-danger">*critical*</span>
									</p>
									<div class="progress progress-micro no-margin">
										<div class="progress-bar progress-bar-danger" style="width: 70%;"></div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="padding-5">
									<button class="btn btn-block btn-default">refresh</button>
								</div>
							</li>
						</ul>
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