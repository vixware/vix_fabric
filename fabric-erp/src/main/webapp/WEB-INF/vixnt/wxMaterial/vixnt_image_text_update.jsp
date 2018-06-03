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
						<div class="row box-br">
							<div class="col-xs-3 col-sm-3">
								<div class="mybox">
									<div class="appmsg_container_hd">
										<h4 class="appmsg_container_title">图文列表</h4>
									</div>
									<div class="appmsg_container_bd">
										<div class="appmsg multi has_first_cover editing">
											<div id="js_appmsg_preview" class="appmsg_content">
												<div id="appmsgItem" data-fileid="" data-id="" data-msgindex="0" class="js_appmsg_item appmsg_item_wrp current">
													<div class="first_appmsg_item" title="第一篇图文">
														<div class="cover_appmsg_item">
															<div class="appmsg_title">
																<a class="js_appmsg_title" href="javascript:void(0);" onclick="return false;">标44444题</a>
															</div>
															<div class="appmsg_thumb_wrp js_appmsg_thumb" style="background-image: url('');">
	
																<div class="appmsg_thumb default">
																	<i class="icon_appmsg_thumb">封面图片</i>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div id="appmsgItem" data-fileid="" data-id="" data-msgindex="2" class="js_appmsg_item appmsg_item_wrp  current">
													<div class="appmsg_item has_cover" title="第二篇图文">
														<div class="appmsg_thumb_wrp js_appmsg_thumb" style="background-image: url('');">
															<div class="appmsg_thumb default">
																<i class="icon_appmsg_thumb_small">缩略图</i>
															</div>
														</div>
														<p class="appmsg_title js_appmsg_title">标题</p>
														<div class="appmsg_edit_mask js_readonly" style="height: 44px; width: 100%; position: absolute; left: 0; bottom: 0; background: rgba(0, 0, 0, 0.5) !important;">
															<a onclick="return false;" class="icon20_common del_media_white js_del" data-id="" href="javascript:;" title="删除" style="background: url(../assets/images/base_z36bc7e.png) 0 -4876px no-repeat; margin-top: 13px; margin-left: 10px;">删除</a>
														</div>
													</div>
													<div class="appmsg_item has_cover" title="第二篇图文">
														<div class="appmsg_thumb_wrp js_appmsg_thumb" style="background-image: url('');">
															<div class="appmsg_thumb default">
																<i class="icon_appmsg_thumb_small">缩略图</i>
															</div>
														</div>
														<p class="appmsg_title js_appmsg_title">标题</p>
														<div class="appmsg_edit_mask js_readonly" style="height: 44px; width: 100%; position: absolute; left: 0; bottom: 0; background: rgba(0, 0, 0, 0.5) !important;">
															<a onclick="return false;" class="icon20_common del_media_white js_del" data-id="" href="javascript:;" title="删除" style="background: url(../assets/images/base_z36bc7e.png) 0 -4876px no-repeat; margin-top: 13px; margin-left: 10px;">删除</a>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="js_add_appmsg" title="添加一篇图文" class="create_access_primary appmsg_add js_readonly">
											<i class="icon35_common add_gray">增加一条</i>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-7 col-sm-7 bor-lr artical-main">
								
								
							</div>
							<div class="col-xs-2 col-sm-2">
								<div class="mybox">
									<div class="appmsg_container_hd">
										<h4 class="appmsg_container_title">多媒体</h4>
									</div>
									<div class="appmsg_container_bd">
										<ul id="js_media_list" class="tpl_list" style="padding-left:0;">
											<li class="tpl_item img" id="js_editor_insertimage" style=""><i class="icon_media_choose"></i>图片</li>
											<li class="tpl_item video" id="js_editor_insertvideo" style=""><i class="icon_media_choose"></i>视频</li>
											<li id="audio_music_plugin_btn" class="tpl_item audio" style=""><i class="icon_media_choose"></i>音频</li>
											<li class="tpl_item vote" id="js_editor_insertvote" style=""><i class="icon_media_choose"></i>投票</li>
											<li class="tpl_item cardticket" id="js_editor_insertcard" style="display: none;"><i class="icon_media_choose"></i>卡券</li>
											<li class="tpl_item weapp" id="js_editor_insertweapp" style=""><i class="icon_media_choose"></i>小程序</li>
										</ul>
										<div id="media_list_mask" style="display: none;" class="share_appmsg_disabled_mask"></div>
									</div>
								</div>
							</div>
					</div>
					<div id="bottom_main" class="tool_area_wrp">                                  
						<div class="js_bot_bar tool_area">                    
							<div class="tool_bar">                                                                       
								<span id="js_send" class="btn  btn-default">
								保存并群发
								</span>                        
								<span id="js_preview" class="btn  btn-default no_extra">
								预览
								</span>                        
								<span id="js_submit" class="btn  btn-primary">
								保存
								</span>                    
							</div>                
						</div>            
					</div>
						
					</div>
				</div>
			</div>
		</div>
	<script>
	function showDiv(){
		$(".dialog_appointment").show();
	};
	function hideDiv(){
		$(".dialog_appointment").hide();
	}
	</script>