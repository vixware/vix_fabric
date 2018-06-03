<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 自定义菜单</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="row">
						<div class="col-xs-4 col-sm-4">
							<div class="menu_preview_area">
								<div class="mobile_menu_preview">
									<div class="mobile_hd tc">中盛盈创</div>
									<div class="mobile_bd">
										<ul class="pre_menu_list grid_line ui-sortable ui-sortable-disabled" id="menuList">
											<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3" id="menu_0"><a href="javascript:void(0);" class="pre_menu_link" draggable="false"> <i class="icon_menu_dot js_icon_menu_dot dn"></i> <i class="icon20_common sort_gray"></i> <span class="js_l1Title">菜单名称</span>
											</a>
												<div class="sub_pre_menu_box js_l2TitleBox" style="display: block;">
													<ul class="sub_pre_menu_list">

														<li id="subMenu_menu_1_0" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li id="subMenu_menu_1_1" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li class="js_addMenuBox"><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>
													</ul>
													<i class="arrow arrow_out"></i> <i class="arrow arrow_in"></i>
												</div></li>
											<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3" id="menu_1"><a href="javascript:void(0);" class="pre_menu_link" draggable="false"> <i class="icon_menu_dot js_icon_menu_dot dn"></i> <i class="icon20_common sort_gray"></i> <span class="js_l1Title">菜单名称</span>
											</a>
												<div class="sub_pre_menu_box js_l2TitleBox" style="display: none;">
													<ul class="sub_pre_menu_list">

														<li id="subMenu_menu_1_0" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li id="subMenu_menu_1_1" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li class="js_addMenuBox"><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>
													</ul>
													<i class="arrow arrow_out"></i> <i class="arrow arrow_in"></i>
												</div></li>
											<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3" id="menu_2"><a href="javascript:void(0);" class="pre_menu_link" draggable="false"> <i class="icon_menu_dot js_icon_menu_dot dn"></i> <i class="icon20_common sort_gray"></i> <span class="js_l1Title">菜单名称</span>
											</a>
												<div class="sub_pre_menu_box js_l2TitleBox" style="display: none;">
													<ul class="sub_pre_menu_list">

														<li id="subMenu_menu_1_0" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li id="subMenu_menu_1_1" class="jslevel2"><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span></span></a></li>

														<li class="js_addMenuBox"><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>
													</ul>
													<i class="arrow arrow_out"></i> <i class="arrow arrow_in"></i>
												</div></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-8 col-sm-8">
							<div class="menu_form_area">
								<div id="js_none" class="menu_initial_tips tips_global" style="display: none;">点击左侧菜单进行编辑操作</div>
								<div id="js_rightBox" class="portable_editor to_left" style="display: block;">
									<div class="editor_inner">
										<div class="global_mod float_layout menu_form_hd js_second_title_bar">
											<div class="global_info">菜单名称</div>
											<div class="global_extra">
												<a href="javascript:void(0);" id="jsDelBt">删除菜单</a>
											</div>
										</div>
										<div class="menu_form_bd" id="view">
											<div id="js_innerNone" style="display: block;" class="msg_sender_tips tips_global">已为“菜单名称”添加了5个子菜单，无法设置其他内容。</div>
											<div class="frm_control_group js_setNameBox">
												<label for="" class="frm_label"> <strong class="title js_menuTitle">菜单名称</strong>
												</label>
												<div class="frm_controls">
													<span class="frm_input_box with_counter counter_in append"> <input type="text" class="frm_input js_menu_name">
													</span>
													<!-- <p class="frm_msg fail js_titleEorTips dn">字数超过上限</p> -->
													<!-- <p class="frm_msg fail js_titlenoTips dn" style="display: none;">请输入菜单名称</p> -->
													<p class="frm_tips js_titleNolTips">字数不超过4个汉字或8个字母</p>
												</div>
											</div>
											<div class="frm_control_group" style="display: none;">
												<label for="" class="frm_label"> <strong class="title js_menuContent">菜单内容</strong>
												</label>
												<div class="frm_controls frm_vertical_pt">
													<label class="frm_radio_label js_radio_sendMsg selected" data-editing="0"> <i class="icon_radio"></i> <span class="lbl_content">发送消息</span> <input type="radio" name="hello" class="frm_radio">
													</label> <label class="frm_radio_label js_radio_url" data-editing="0"> <i class="icon_radio"></i> <span class="lbl_content">跳转网页</span> <input type="radio" name="hello" class="frm_radio">
													</label> <label class="frm_radio_label js_radio_weapp" data-editing="0"> <i class="icon_radio"></i> <span class="lbl_content">跳转小程序</span> <input type="radio" name="hello" class="frm_radio">
													</label>
												</div>
											</div>
											<div class="menu_content_container" style="display: none;">
												<div class="menu_content send jsMain" id="edit" style="display: block;">
													<div class="msg_sender" id="editDiv">
														<div class="msg_tab">
															<div class="tab_navs_panel">
																<span class="tab_navs_switch_wrp switch_prev js_switch_prev"> <span class="tab_navs_switch"></span>
																</span> <span class="tab_navs_switch_wrp switch_next js_switch_next"> <span class="tab_navs_switch"></span>
																</span>
																<div class="tab_navs_wrp">
																	<ul class="tab_navs js_tab_navs" style="margin-left: 0;">

																		<li class="tab_nav tab_appmsg width5 selected" data-type="10" data-tab=".js_appmsgArea" data-tooltip="图文消息"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图文消息</span></a></li>

																		<li class="tab_nav tab_text width5" data-type="1" data-tab=".js_textArea" data-tooltip="文字"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">文字</span></a></li>

																		<li class="tab_nav tab_img width5" data-type="2" data-tab=".js_imgArea" data-tooltip="图片"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图片</span></a></li>

																		<li class="tab_nav tab_audio width5" data-type="3" data-tab=".js_audioArea" data-tooltip="语音"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">语音</span></a></li>

																		<li class="tab_nav tab_video width5 no_extra" data-type="15" data-tab=".js_videoArea" data-tooltip="视频"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">视频</span></a></li>

																	</ul>
																</div>
															</div>
															<!--  -->
															<div class="tab_panel">
																<div class="tab_content">
																	<div class="js_appmsgArea inner ">
																		<!--type 10图文 2图片  3语音 15视频 11商品消息-->

																		<div class="tab_cont_cover jsMsgSendTab" data-index="0">
																			<div class="media_cover">
																				<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="10" data-index="0"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong>
																				</a>
																				</span>
																			</div>
																			<div class="media_cover" style="margin-left: 5.5%;">
																				<span class="create_access"> <a target="_blank" class="add_gray_wrp create_new_appmsg" href="javascript:;"> <i class="icon36_common add_gray"></i> <strong>新建图文消息</strong>
																				</a>
																				</span>
																			</div>
																		</div>

																	</div>
																</div>
																<!-- 文字 -->
																<div class="tab_content" style="display: none;">
																	<div class="js_textArea inner no_extra Main">
																		<div class="emotion_editor Input_Box">

																			<div class="edit_area js_editorArea Input_text" contenteditable="true" style="overflow-y: auto; overflow-x: hidden;" onkeyup="keypress2()" id="myarea"></div>
																			<div class="editor_toolbar Input_Foot">
																				<a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch imgBtn">表情</a>
																				<div class="editor_tip js_editorTip" id="pinglun">
																					还可以输入<em>300</em>字
																				</div>
																			</div>

																		</div>
																		<div class="faceDiv" style="width: 390px; display: block;">
																			<section class="emoji_container"></section>
																			<section class="emoji_tab"></section>
																		</div>
																	</div>
																</div>
																<!-- 图片 -->
																<div class="tab_content" style="display: none;">
																	<div class="js_imgArea inner ">
																		<!--type 10图文 2图片  3语音 15视频 11商品消息-->
																		<div class="tab_cont_cover jsMsgSendTab" data-index="2" data-type="2">
																			<div class="media_cover">
																				<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="2" data-index="2"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong>
																				</a>
																				</span>
																			</div>
																			<div class="media_cover" style="margin-left: 5.5%;">
																				<span class="create_access"> <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:;"> <i class="icon36_common add_gray"></i> <strong>上传图片</strong>
																				</a>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
																<!-- 语音 -->
																<div class="tab_content" style="display: none;">
																	<div class="js_audioArea inner ">
																		<!--type 10图文 2图片  3语音 15视频 11商品消息-->
																		<div class="tab_cont_cover jsMsgSendTab" data-index="3" data-type="3">
																			<div class="media_cover">
																				<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="3" data-index="3"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong>
																				</a>
																				</span>
																			</div>
																			<div class="media_cover" style="margin-left: 5.5%;">
																				<span class="create_access"> <a class="add_gray_wrp " id="msgSendAudioUploadBt" href="javascript:;"> <i class="icon36_common add_gray"></i> <strong>新建语音</strong>
																				</a>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
																<!-- 视频 -->
																<div class="tab_content" style="display: none;">
																	<div class="js_videoArea inner ">
																		<!--type 10图文 2图片  3语音 15视频 11商品消息-->
																		<div class="tab_cont_cover jsMsgSendTab" data-index="4">
																			<div class="media_cover">
																				<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="15" data-index="4"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong>
																				</a>
																				</span>
																			</div>
																			<div class="media_cover" style="margin-left: 5.5%;">
																				<span class="create_access"> <a target="_blank" class="add_gray_wrp" href="/cgi-bin/appmsg?t=media/videomsg_edit&amp;action=video_edit&amp;type=15&amp;lang=zh_CN&amp;token=792962420"> <i class="icon36_common add_gray"></i> <strong>新建视频</strong>
																				</a>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
												<!-- 跳转网页 -->
												<div class="menu_content url jsMain" id="url" style="display: none;">
													<form action="" id="urlForm" onsubmit="return false;">
														<p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>
														<div class="frm_control_group">
															<label for="" class="frm_label">页面地址</label>
															<div class="frm_controls">
																<span class="frm_input_box"> <input type="text" class="frm_input" id="urlText" name="urlText">
																</span>
																<p class="frm_tips" id="js_urlTitle" style="display: none;">
																	来自 <span class="js_name"></span> <span style="display: none;"> -《<span class="js_title"></span>》
																	</span>
																</p>
															</div>
														</div>
														<div class="frm_control_group btn_appmsg_wrap">
															<div class="frm_controls">
																<p class="frm_msg fail dn" id="urlUnSelect" style="display: none;">
																	<span for="urlText" class="frm_msg_content" style="display: inline;">请选择一篇文章</span>
																</p>
																<a href="javascript:;" id="js_appmsgPop">从公众号图文消息中选择</a> <a href="javascript:void(0);" class="dn btn btn-primary" id="js_reChangeAppmsg">重新选择</a>
															</div>
														</div>
													</form>
												</div>
												<!-- 跳转小程序 -->
												<div class="menu_content weapp " id="weapp" style="display: none;">
													<div class="weapp_empty_box js_weapp_no_binded_hint">
														<p class="desc">自定义菜单可跳转已绑定的小程序，本公众号尚未绑定小程序。</p>
														<a href="https://mp.weixin.qq.com/cgi-bin/wxopen?action=list&amp;token=792962420&amp;lang=zh_CN" class="btn btn-primary">前往绑定</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							<div id="bottom_main" class="tool_area_wrp">                                  
								                  
									<div style="margin-top:20px;text-align:center;">                                                                       
										<a href="javascript:void(0);" class="btn btn-primary">保存</a>
										<a href="javascript:void(0);" class="btn btn-success">提交到微信</a>
									</div>                
								          
							</div>
						</div>
					</div>
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
	
	<script>
			/* 底部按钮的切换 */
			$(".pre_menu_list .jsMenu").click(function(){
				$(this).addClass("current").siblings(".jsMenu").removeClass("current");
				//console.log($(this).siblings(".jsMenu"))
				$(this).children(".sub_pre_menu_box").show();
				$(this).siblings(".jsMenu").children(".sub_pre_menu_box").hide();
			});
			/* 弹出框的切换 */
			$(".sub_pre_menu_list .jslevel2").click(function(e){
				e.stopPropagation()
				$(this).addClass("current").siblings(".jslevel2").removeClass("current");
				$(this).parents(".jsMenu").removeClass("current");
				//console.log($(this).parents(".jsMenu"))
				$(".frm_control_group").show();
				$(".menu_content_container").show();
			});
			
		</script>

	<script>
			$(function(){
			    var tab_li = $('.tab_navs_wrp .tab_navs li');
			    tab_li.click(function(){
			        var index = $(this).index()+1;
			        $(this).addClass('selected').siblings().removeClass('selected');
			        var tab_li_index = tab_li.index(this);
			        $('.tab_panel .tab_content').eq(tab_li_index).show().siblings().hide();
			    })
			});
			/* 菜单内容的切换 */
			$(function(){
			    var tab_label = $('.frm_controls .frm_radio_label');
			    tab_label.click(function(){
			        var index = $(this).index()+1;
			        $(this).addClass('selected').siblings().removeClass('selected');
			        var tab_label_index = tab_label.index(this);
			        $('.menu_content_container .menu_content').eq(tab_label_index).show().siblings().hide();
			    })
			});
		</script>
	<script>
		function keypress2() //textarea输入长度处理   
		{   
		var text1=document.getElementById("myarea").innerHTML;   
		var len;//记录剩余字符串的长度   
		if(text1.length>=300)//textarea控件不能用maxlength属性，就通过这样显示输入字符数了   
		{   
		document.getElementById("myarea").innerHTML=text1.substr(0,300);   
		len=0;   
		}   
		else   
		{   
		len=300-text1.length;   
		}   
		var show="你还可以输入"+len+"个字";   
		document.getElementById("pinglun").innerText=show;   
		}   
		</script>
	