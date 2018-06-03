<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 关注自动回复</span>
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class="tab_menu">
					        <ul>
					            <li class="selected">关注自动回复</li>
					            <li>消息自动回复</li>
					        </ul>
					    </div>
						<!-- 消息回复框 -->
							<div class="menu_content_container" >
								<div class="menu_content send jsMain" id="edit" style="display: block;">
									<div class="msg_sender" id="editDiv">
										<div class="msg_tab">
											<div class="tab_navs_panel">
												<span class="tab_navs_switch_wrp switch_prev js_switch_prev"> <span class="tab_navs_switch"></span>
												</span> <span class="tab_navs_switch_wrp switch_next js_switch_next"> <span class="tab_navs_switch"></span>
												</span>
												<div class="tab_navs_wrp">
													<ul class="tab_navs js_tab_navs" style="padding-left: 0;margin-bottom:0;">
														<li class="tab_nav tab_text width5 selected" data-type="1" data-tab=".js_textArea" data-tooltip="文字"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">文字</span></a></li>
														<li class="tab_nav tab_img width5" data-type="2" data-tab=".js_imgArea" data-tooltip="图片"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图片</span></a></li>
													</ul>
												</div>
											</div>
											<!-- 输入部分 -->
											<div class="tab_panel">
												<!-- 文字 -->
												<div class="tab_content" style="display: block;">
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
														<div class="faceDiv" style="width: 390px; display: none;">
															<section class="emoji_container"></section>
															<section class="emoji_tab"></section>
														</div>
													</div>
												</div>
												<!-- 图片 -->
												<div class="tab_content" style="display: none;">
													<div class="js_imgArea inner ">
														<div class="tab_cont_cover jsMsgSendTab" data-index="2" data-type="2">
															<div class="media_cover">
																<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="2" data-index="2"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong></a></span>
															</div>
															<div class="media_cover" style="margin-left: 5.5%;">
																<span class="create_access"> <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:;"> <i class="icon36_common add_gray"></i> <strong>上传图片</strong></a></span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="menu_content send jsMain" id="edit" style="display: none;">
									<div class="msg_sender" id="editDiv">
										<div class="msg_tab">
											<div class="tab_navs_panel">
												<span class="tab_navs_switch_wrp switch_prev js_switch_prev"> <span class="tab_navs_switch"></span>
												</span> <span class="tab_navs_switch_wrp switch_next js_switch_next"> <span class="tab_navs_switch"></span>
												</span>
												<div class="tab_navs_wrp">
													<ul class="tab_navs js_tab_navs" style="padding-left: 0;margin-bottom:0;">
														<li class="tab_nav tab_text width5 selected" data-type="1" data-tab=".js_textArea" data-tooltip="文字"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">文字</span></a></li>
														<li class="tab_nav tab_img width5" data-type="2" data-tab=".js_imgArea" data-tooltip="图片"><a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图片</span></a></li>
													</ul>
												</div>
											</div>
											<!-- 输入部分 -->
											<div class="tab_panel">
												<!-- 文字 -->
												<div class="tab_content" style="display: block;">
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
														<div class="faceDiv" style="width: 390px; display: none;">
															<section class="emoji_container"></section>
															<section class="emoji_tab"></section>
														</div>
													</div>
												</div>
												<!-- 图片 -->
												<div class="tab_content" style="display: none;">
													<div class="js_imgArea inner ">
														<div class="tab_cont_cover jsMsgSendTab" data-index="2" data-type="2">
															<div class="media_cover">
																<span class="create_access"> <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="2" data-index="2"> <i class="icon36_common add_gray"></i> <strong>从素材库中选择</strong></a></span>
															</div>
															<div class="media_cover" style="margin-left: 5.5%;">
																<span class="create_access"> <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:;"> <i class="icon36_common add_gray"></i> <strong>上传图片</strong></a></span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>	
							<div class="row">
								<div class="col-md-7" style="margin-top: 10px;">
									<button class="btn btn-primary" type="button" >
										<i class="ace-icon fa fa-check bigger-110"></i>
										保存
									</button>
									&nbsp; &nbsp;
									<button class="btn" type="button">
										<i class="ace-icon fa fa-trash-o bigger-110"></i>
										删除回复
									</button>
								</div>
							</div>		
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	
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
		    var tab_label = $('.tab_menu ul li');
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
	<script src="./js/myemojiPl.js"></script>
	<script>
		var emojiconfig = {
			    tieba: {
			        name: "贴吧表情",
			        path: "./js/arclist/",
			        maxNum: 69,
			        file: ".gif",
			        placeholder: ":{alias}:",
			        alias: {
			            1: "hehe",
			            2: "haha",
			            3: "tushe",
			            4: "a",
			            5: "ku",
			            6: "lu",
			            7: "kaixin",
			            8: "han",
			            9: "lei",
			            10: "heixian",
			            11: "bishi",
			            12: "bugaoxing",
			            13: "zhenbang",
			            14: "qian",
			            15: "yiwen",
			            16: "yinxian",
			            17: "tu",
			            18: "yi",
			            19: "weiqu",
			            20: "huaxin",
			            21: "hu",
			            22: "xiaonian",
			            23: "neng",
			            24: "taikaixin",
			            25: "huaji",
			            26: "mianqiang",
			            27: "kuanghan",
			            28: "guai",
			            29: "shuijiao",
			            30: "jinku",
			            31: "shengqi",
			            32: "jinya",
			            33: "pen",
			            34: "aixin",
			            35: "xinsui",
			            36: "meigui",
			            37: "liwu",
			            38: "caihong",
			            39: "xxyl",
			            40: "taiyang",
			            41: "qianbi",
			            42: "dnegpao",
			            43: "chabei",
			            44: "dangao",
			            45: "yinyue",
			            46: "haha2",
			            47: "shenli",
			            48: "damuzhi",
			            49: "ruo",
			            50: "OK",
			            51: "shengqi",
			            52: "jinya",
			            53: "pen",
			            54: "aixin",
			            55: "xinsui",
			            56: "meigui",
			            57: "liwu",
			            58: "caihong",
			            59: "xxyl",
			            60: "taiyang",
			            61: "qianbi",
			            62: "dnegpao",
			            63: "chabei",
			            64: "dangao",
			            65: "yinyue",
			            66: "haha2",
			            67: "shenli",
			            68: "damuzhi",
			            69: "ruo",
			            
			        },
			        title: {
			            1: "呵呵",
			            2: "哈哈",
			            3: "吐舌",
			            4: "啊",
			            5: "酷",
			            6: "怒",
			            7: "开心",
			            8: "汗",
			            9: "泪",
			            10: "黑线",
			            11: "鄙视",
			            12: "不高兴",
			            13: "真棒",
			            14: "钱",
			            15: "疑问",
			            16: "阴脸",
			            17: "吐",
			            18: "咦",
			            19: "委屈",
			            20: "花心",
			            21: "呼~",
			            22: "笑脸",
			            23: "冷",
			            24: "太开心",
			            25: "滑稽",
			            26: "勉强",
			            27: "狂汗",
			            28: "乖",
			            29: "睡觉",
			            30: "惊哭",
			            31: "生气",
			            32: "惊讶",
			            33: "喷",
			            34: "爱心",
			            35: "心碎",
			            36: "玫瑰",
			            37: "礼物",
			            38: "彩虹",
			            39: "星星月亮",
			            40: "太阳",
			            41: "钱币",
			            42: "灯泡",
			            43: "茶杯",
			            44: "蛋糕",
			            45: "音乐",
			            46: "haha",
			            47: "胜利",
			            48: "大拇指",
			            49: "弱",
			            50: "OK",
			            51: "生气",
			            52: "惊讶",
			            53: "喷",
			            54: "爱心",
			            55: "心碎",
			            56: "玫瑰",
			            57: "礼物",
			            58: "彩虹",
			            59: "星星月亮",
			            60: "太阳",
			            61: "钱币",
			            62: "灯泡",
			            63: "茶杯",
			            64: "蛋糕",
			            65: "音乐",
			            66: "haha",
			            67: "胜利",
			            68: "大拇指",
			            69: "弱",
			           
			        }
			      },
			
			  };
			$('.Main').myEmoji({emojiconfig : emojiconfig});
		</script>