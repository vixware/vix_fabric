<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 图文素材</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<!-- 新增按钮 -->
						<div class="clearfix " style="margin-bottom:5px;">
							<div class="pull-right tableTools-container">
								<a href="#" class="btn btn-primary btn-sm" onclick="saveOrUpdate();"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
								<a href="javascript:;" class="btn btn-primary btn-sm" onclick="showDiv1()"><i class="fa fa-cloud"></i>&nbsp;&nbsp;同步公众号已创建的标签</a>
							</div>
						</div>
						<div class="jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false">
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>微信图文素材信息</h2>
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
											<div class="dataTables_length sub_title_bar" id="dt_basic_length">
												<a href="javascript:;" class="btn_card_rank current" id="js_cardview" alt="卡片式">卡片式</a> 
												<a href="javascript:;" class="btn_list_rank" id="js_listview" alt="列表式">列表式</a>
											</div>
										</div>
									</div>
									
									<div class="page-content" style="padding-top: 50px;">
										<!-- 图文消息 -->
										<div class="main_bd tuwen">
											<!-- 图文消息1 -->
											<div class="appmsg_list row" id="js_card" style="">
												<div class="appmsg_col tj_item col-xs-3 col-sm-3">
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
					
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
					
																	<div class="appmsg_item has_cover">
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																		</h4>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
																<div class="appmsg_item has_cover">
																	<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																	<h4 class="appmsg_title js_title">
																		<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																	</h4>
																	<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																		<div class="edit_mask_content">
																			<p class="">预览文章</p>
																		</div> <span class="vm_box"></span>
																	</a>
																</div>
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>

												</div>
					
												<div class="appmsg_col tj_item col-xs-3 col-sm-3">
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
					
																	<div class="appmsg_item has_cover">
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																		</h4>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
					
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
					
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
												</div>
					
												<div class="appmsg_col tj_item col-xs-3 col-sm-3">
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
					
																	<div class="appmsg_item has_cover">
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																		</h4>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
																<div class="appmsg_item has_cover">
																	<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																	<h4 class="appmsg_title js_title">
																		<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																	</h4>
																	<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																		<div class="edit_mask_content">
																			<p class="">预览文章</p>
																		</div> <span class="vm_box"></span>
																	</a>
																</div>
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
					
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
												</div>
												
												<div class="appmsg_col tj_item col-xs-3 col-sm-3">
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
					
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="inner col-xs-12 col-sm-12" id="js_col1">
														<div id="appmsg402689313" class="js_appmsgitem">
															<div class="appmsg multi has_first_cover" data-id="402689313" data-fileid="402689303" data-completed="1">
																<div class="appmsg_content">
																	<div class="appmsg_info">
																		<em class="appmsg_date">更新于 2016年01月06日</em>
																	</div>
																	<div class="cover_appmsg_item">
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="0"> 素材2</a>
																		</h4>
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg')"></div>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="0">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
					
																	<div class="appmsg_item has_cover">
																		<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																		<h4 class="appmsg_title js_title">
																			<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																		</h4>
																		<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																			<div class="edit_mask_content">
																				<p class="">预览文章</p>
																			</div> <span class="vm_box"></span>
																		</a>
																	</div>
																</div>
																<div class="appmsg_item has_cover">
																	<div class="appmsg_thumb_wrp" style="background-image: url('https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg');"></div>
																	<h4 class="appmsg_title js_title">
																		<a href="" target="_blank" data-msgid="402689313" data-idx="1">素材</a>
																	</h4>
																	<a href="" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
																		<div class="edit_mask_content">
																			<p class="">预览文章</p>
																		</div> <span class="vm_box"></span>
																	</a>
																</div>
																<div class="appmsg_opr">
																	<ul>
																		<li class="appmsg_opr_item grid_item size1of2"><a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;isMul=1" data-tooltip="编辑">&nbsp;<i
																				class="icon18_common edit_gray">编辑</i></a></li>
																		<li class="appmsg_opr_item grid_item size1of2 no_extra"><a class="js_del no_extra js_tooltip" data-id="402689313" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- 图文消息2 -->
											<div class="appmsg_list_v" id="js_list" style="display: none;">
												<ul class="inner_list_v">
													<li class="appmsg_item_v js_appmsgitem">
														<div class="inner">
															<div class="content">
																<img src="https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg" class="content_cover">
																<div class="content_abstract">
																	<p class="js_title" data-msgid="402689313" data-idx="0">
																		<a target="_blank" href="">1. 素材2</a>
																	</p>
																	<p class="js_title" data-msgid="402689313" data-idx="1">
																		<a target="_blank" href="">2. 素材</a>
																	</p>
																</div>
															</div>
															<div class="opr">
																<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;fromview=list">编辑</a> <a href="javascript:;" class="js_del" data-id="402689313">删除</a>
																<div class="popover " style="position: relative; display: none">
																	<div class="popover_inner">
																		<div class="popover_content">确定删除此素材吗？</div>
																		<div class="popover_bar">
																			<a href="javascript:;" class="btn btn_primary">确定</a> <a href="javascript:;" class="btn btn_default">取消 </a>
																		</div>
																	</div>
																	<i class="popover_arrow popover_arrow_out"> </i> <i class="popover_arrow popover_arrow_in"> </i>
																</div>
															</div>
															<div class="date">
																<p>2016年01月06日</p>
															</div>
														</div>
													</li>
													<li class="appmsg_item_v js_appmsgitem">
														<div class="inner">
															<div class="content">
																<img src="https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg" class="content_cover">
																<div class="content_abstract">
																	<p class="js_title" data-msgid="402689313" data-idx="0">
																		<a target="_blank" href="">1. 素材2</a>
																	</p>
																	<p class="js_title" data-msgid="402689313" data-idx="1">
																		<a target="_blank" href="">2. 素材</a>
																	</p>
																</div>
															</div>
															<div class="opr">
																<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;fromview=list">编辑</a> <a href="javascript:;" class="js_del" data-id="402689313">删除</a>
																<div class="popover " style="position: relative; display: none">
																	<div class="popover_inner">
																		<div class="popover_content">确定删除此素材吗？</div>
																		<div class="popover_bar">
																			<a href="javascript:;" class="btn btn_primary">确定</a> <a href="javascript:;" class="btn btn_default">取消 </a>
																		</div>
																	</div>
																	<i class="popover_arrow popover_arrow_out"> </i> <i class="popover_arrow popover_arrow_in"> </i>
																</div>
															</div>
															<div class="date">
																<p>2016年01月06日</p>
															</div>
														</div>
													</li>
													<li class="appmsg_item_v js_appmsgitem">
														<div class="inner">
															<div class="content">
																<img src="https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg" class="content_cover">
																<div class="content_abstract">
																	<p class="js_title" data-msgid="402689313" data-idx="0">
																		<a target="_blank" href="">1. 素材2</a>
																	</p>
																	<p class="js_title" data-msgid="402689313" data-idx="1">
																		<a target="_blank" href="">2. 素材</a>
																	</p>
																</div>
															</div>
															<div class="opr">
																<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;fromview=list">编辑</a> <a href="javascript:;" class="js_del" data-id="402689313">删除</a>
																<div class="popover " style="position: relative; display: none">
																	<div class="popover_inner">
																		<div class="popover_content">确定删除此素材吗？</div>
																		<div class="popover_bar">
																			<a href="javascript:;" class="btn btn_primary">确定</a> <a href="javascript:;" class="btn btn_default">取消 </a>
																		</div>
																	</div>
																	<i class="popover_arrow popover_arrow_out"> </i> <i class="popover_arrow popover_arrow_in"> </i>
																</div>
															</div>
															<div class="date">
																<p>2016年01月06日</p>
															</div>
														</div>
													</li>
													<li class="appmsg_item_v js_appmsgitem">
														<div class="inner">
															<div class="content">
																<img src="https://mmbiz.qlogo.cn/mmbiz/6BkhKLlLibibmRxbuYAULibDAJWuhgMOYx5TTowYQob31zoLHecGicGgF8CvXayoA27MqJ1q3rYWb9koZ30qO7teXA/0?wx_fmt=jpeg" class="content_cover">
																<div class="content_abstract">
																	<p class="js_title" data-msgid="402689313" data-idx="0">
																		<a target="_blank" href="">1. 素材2</a>
																	</p>
																	<p class="js_title" data-msgid="402689313" data-idx="1">
																		<a target="_blank" href="">2. 素材</a>
																	</p>
																</div>
															</div>
															<div class="opr">
																<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&amp;action=edit&amp;lang=zh_CN&amp;token=792962420&amp;type=10&amp;appmsgid=402689313&amp;fromview=list">编辑</a> <a href="javascript:;" class="js_del" data-id="402689313">删除</a>
																<div class="popover " style="position: relative; display: none">
																	<div class="popover_inner">
																		<div class="popover_content">确定删除此素材吗？</div>
																		<div class="popover_bar">
																			<a href="javascript:;" class="btn btn_primary">确定</a> <a href="javascript:;" class="btn btn_default">取消 </a>
																		</div>
																	</div>
																	<i class="popover_arrow popover_arrow_out"> </i> <i class="popover_arrow popover_arrow_in"> </i>
																</div>
															</div>
															<div class="date">
																<p>2016年01月06日</p>
															</div>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>
									<!-- 底部页码 -->
									<div class="dt-toolbar-footer">
										<div class="col-sm-6 col-xs-12 hidden-xs">
											<div class="dataTables_info" id="dt_basic_info" role="status" aria-live="polite">
												共1条记录
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
		<!-- 弹出框 -->
	<div class="dialog_appointment1">
		<div class="dialog_appointment1_middle">
			<div class="dialog_appointment1_con">
				<div class="dialog_appointment1_main">此前如果已经执行过同步操作，一般不需要再次执行，是否确认执行？</div>
				<div class="dialog_appointment1_footer">
					<button class="btn btn-sm btn-primary">保存</button>
					<button class="btn btn-sm bg-color-blueDark txt-color-white" onclick="hideDiv1()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".sub_title_bar a").click(function(){
			$(this).addClass("current").siblings().removeClass("current");
		});
		$(".sub_title_bar .btn_card_rank").click(function(){
			$(".appmsg_list").show();
			$(".appmsg_list_v").hide();
		});
		$(".sub_title_bar .btn_list_rank").click(function(){
			$(".appmsg_list").hide();
			$(".appmsg_list_v").show();
		});
		
		function showDiv1(){
			$(".dialog_appointment1").show();
		};
		function hideDiv1(){
			$(".dialog_appointment1").hide();
		}
		function saveOrUpdate(){
			$.ajax({
				url : '${nvix}/nvixnt/wx/wxMaterialAction!goSaveOrUpdate.action',
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
		}
	</script>