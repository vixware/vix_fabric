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
						<div class="row">
						 	<div class="col-sm-offset-1 col-sm-10">
								<form id="replyRuleEditForm" method="post" class="form-horizontal smart-form" style="margin-bottom:0px;">
									<div class="tab-content profile-edit-tab-content">
										<header> 规则信息</header>
										<fieldset>
											<div class="form-group rule">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="reds"><strong>*</strong></span>规则名称:</label>
												<div class="controls col-sm-9">
													<input type="text" name="wxpReplyRule.title" placeholder="请填写规则名称" value="" class="validate[required] col-xs-10 col-sm-10" data-prompt-position="centerRight">
												</div>
											</div>
											<div class="form-group rule">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="reds"><strong>*</strong></span>使用状态:</label>
												<div class="controls col-sm-9">
													<select name="wxpwxpReplyRule.isActive" class=" col-xs-10 col-sm-10">
														<option value="1">启用</option>
														<option value="0">停用</option>
														
															<option value="-1" selected="selected">草稿</option>
														
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right" for="form-field-1"><span class="reds"><strong>*</strong></span>&nbsp;是否回复全部:</label>
												<div class="controls col-sm-9" style="line-height:30px;">
													<input type="checkbox" class="ace ace-switch ace-switch-5" id="isReplyAll" checked="">
													<span class="lbl middle"></span>
												</div>
											</div>
										</fieldset>
										<header> 
											<span>关键字</span>
											<span style="float: right;" onclick="showDiv()"><a href="javascript:;">添加关键字</a></span>
										</header>
										<fieldset>
											<table id="wxp_reply_match_table" class="table  table-bordered table-hover">
												<thead>
													<tr>
														<th>关键字</th>
														<th>是否完全匹配 </th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													
													<tr><td colspan="4"><span class="nodata">暂无数据</span></td></tr>
												</tbody>
											</table>
										</fieldset>
										<header> 
											<span>回复</span>
											<div class="msg_sender" style="float: right;border: 0px;">
												<ul class="tab_navs js_tab_navs" style="margin-left: 0;background-color: rgba(255, 255, 255, 0);">
													<li class="tab_nav tab_text width5" data-type="text" data-tooltip="文字">
														<a href="javascript:;" onclick="showDiv1();">&nbsp;<i class="icon_msg_sender"></i></a>
													</li>
													<li class="tab_nav tab_img width5" data-type="image" data-tooltip="图片">
														<a href="javascript:;" onclick="showDiv2();">&nbsp;<i class="icon_msg_sender"></i></a>
													</li>
													<li class="tab_nav tab_appmsg width5" data-type="news" data-tooltip="图文消息">
									                    <a href="javascript:;" onclick="showDiv3();">&nbsp;<i class="icon_msg_sender"></i></a>
									                </li>
												</ul>
											</div>
										</header>
										<fieldset style="width:98%;">
											<table id="wxp_reply_table" class="table  table-bordered table-hover">
												<thead>
													<tr>
														<th>内容</th>
														<th>创建时间</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<tr><td colspan="3"><span>暂无数据</span></td></tr>
												</tbody>
											</table>
										</fieldset>
										<div class="operation-btn">
											<a href="javascript:void(0);" class="btn btn-primary">
												<i class="fa fa-check"></i>&nbsp;&nbsp;保存
											</a>
											<a href="javascript:void(0);" class="btn bg-color-blueLight txt-color-white">
												<i class="fa fa-undo"></i>&nbsp;&nbsp;取消
											</a>
										</div>
									</div>
								</form>
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
</script>