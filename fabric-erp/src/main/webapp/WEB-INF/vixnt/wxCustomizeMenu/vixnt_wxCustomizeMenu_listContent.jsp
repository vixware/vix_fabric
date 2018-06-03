<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script type="text/javascript" src="${nvix}/common/js/myemojiPl.js"></script>
<script type="text/javascript" src="${nvix}/common/js/emoji.js"></script>
<style>
.frm_input_box{line-height: 0px;}
</style>
<script type="text/javascript">
$(function(){
	$('#menu_form_area .Main').myEmoji({emojiconfig : emojiconfig});
	onload();
	/* 发送消息的内容切换 */
	var tab_li = $('.tab_navs_wrp .tab_navs li');
	tab_li.click(function(){
		var index = $(this).index()+1;
		$(this).addClass('selected').siblings().removeClass('selected');
		var tab_li_index = tab_li.index(this);
		$('.tab_panel .tab_content').eq(tab_li_index).show().siblings().hide();
	})
	if($("#wxpMenuType").val() == ''){
		$("#wxpMenuType").val("click")
	}
	/* 菜单内容的切换 */
	var tab_label = $('.frm_controls .frm_radio_label');
    tab_label.click(function(){
        var index = $(this).index()+1;
        $(this).addClass('selected').siblings().removeClass('selected');
        //var tab_label_index = tab_label.index(this);
        //$('.menu_content_container .menu_content').eq(tab_label_index).show().siblings().hide();
        $("#wxpMenuType").val($(this).attr('data_type'));
        if($(this).attr('data_type') == 'view'){
        	$("#editDiv").hide();
        	$("#url").show();
        }else{
        	$("#editDiv").show();
        	$("#url").hide();
        }
    });
    $(".appmsg_item").mouseover(function(){
		$(this).children(".edit_mask").show();
	});
	$(".appmsg_item").mouseout(function(){
		$(this).children(".edit_mask").hide();
	});
});
function onload(){
	var msgType = $("#msgType").val();
	if(msgType != ''){
		var index = $('.tab_navs .'+msgType).index() + 1;
		$('.tab_navs .'+msgType).addClass('selected').siblings().removeClass('selected');
		var tab_li_index = $('.tab_navs_wrp .tab_navs li').index($('.tab_navs .'+msgType));
		$('.tab_panel .tab_content').eq(tab_li_index).show().siblings()
				.hide();
		if(msgType == 'news'){
			wxpMaterialNews('${wxpReply.wxpMaterial.id}');
		}else if(msgType == 'text'){
			//用于处理表情显示图片替换
			var textContent = $('#menu_form_area #textContent').val();
			$.each(emojititle,function(i,val){
				var url = "https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/"+i+".gif";
				var title = val;
				var emotionHtml = '<img title="'+title+'" src="'+url+'"/>'
				var replaceStr = '\\['+title+'\\]';
				textContent = textContent.replace(new RegExp(replaceStr,"gm"), emotionHtml);
	        });
			$('#menu_form_area #myarea').html(textContent);
		}
		changePinglun(600);
	}
}
function changePinglun(total){ 
	if(total == ''){
		total = 600;
	}
	var text=$("#myarea").text();  
	var len;
	var txtlen = text.length;
	$("#myarea").find("img").each(function(){
		var title = $(this).attr("title");
		txtlen = txtlen + title.length;
	});
	if(txtlen >= total){   
		$("#myarea").text(text.substr(0,total));
		len = 0;   
	}else {   
		len = total-txtlen;   
	}
	var show="你还可以输入"+len+"个字";   
	$("#pinglun").text(show);  
}
function wxpMenuSaveOrUpdate(){
	var menuName = $('#menu_form_area #menuName').val();
	var type = $("#type").val();
	if(!menuName){
		layer.alert("请输入菜单名称");
		return ;
	}
	if(!menuName.replace(/[\d]/g,'')){
		layer.alert("菜单名称只能输入字母和汉字");
		return ;
	}
	re = /[\u4E00-\u9FA5]/g; //测试中文字符的正则
	if(type == 'first' && (menuName.length > 8 || menuName.match(re).length > 4)){
		layer.alert("菜单名称字数不超过4个汉字或8个字母");
		return ;
	}else if(menuName.length > 16 || menuName.match(re).length > 8){
		layer.alert("菜单名称字数不超过8个汉字或16个字母");
		return ;
	}
	if(type == 'first' && $('#subMenu').val() == 1){
		$("#wxpMenuType").val('');
	}else{
		if($("#wxpMenuType").val() != '' && $("#wxpMenuType").val() == 'view'){
			if(!$('#menu_form_area #urlText').val()){
				layer.alert("请输入菜单跳转网页");
				return ;
			}
		}else if($("#wxpMenuType").val() != '' && $("#wxpMenuType").val() == 'click'){
			var msgType = $('#menu_form_area .tab_nav.selected:first').attr('data-type');
			$('#menu_form_area #msgType').val(msgType);
			if(msgType=='text'){
				var editorHtml =$('#menu_form_area #myarea').html();
				$('#menu_form_area #textContent').val(editorHtml);
				var textContent = $('#menu_form_area #textContent').val();
				var tempHtml = $('<div></div>');
				tempHtml.html(textContent);
				tempHtml.find("img[title]").each(function(){
					emTitle = $(this).attr('title');
					emTitle = $('<span>[' + emTitle + ']</span>');
					$(this).replaceWith(emTitle);
				});
				$('#menu_form_area #textContent').val(tempHtml.text());
				if(!$('#menu_form_area #textContent').val()){
					layer.alert("请输入文本信息");
					return ;
				}
			}else if(msgType=='image'){
				var imageId = $('#menu_form_area #wxpMaterialId').val();
				if(imageId == ""){
		     	  layer.alert("请选择图片");
					return ;
				}
			} 
		}
	}
	$("#wxpMenuEditForm").ajaxSubmit({
		 url: '${nvix}/nvixnt/wx/wxCustomizeMenuAction!saveOrUpdate.action',
	     type: "post",
	     dataType: "text",
	     success: function(result){
			var r = result.split(":");
 			if(r[0] != '0'){
 				loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxCustomizeMenuAction!goList.action');
 			}else{
 				layer.alert(r[1]);
 				return ;
 			}
	     }
	 });  
}
function wxpMenuDelete(id){
	if(id == ''){
		loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxCustomizeMenuAction!goList.action');
	}else{
		layer.confirm('是否删除该菜单?', {title:'删除菜单'}, function(index){
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$.ajax({
				url : '${nvix}/nvixnt/wx/wxCustomizeMenuAction!wxpMenuDelete.action?id=' + id,
				cache: false,
				success: function(result){
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					var r = result.split(":")
					if(r[0] != '0'){
						loadContent('sys_wxpWeixinSite','${nvix}/nvixnt/wx/wxCustomizeMenuAction!goList.action');
					}else{
						layer.alert(r[1]);
		 				return ;
					}
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					layer.alert("系统错误，请联系管理员");
				}
			});
		    layer.close(index);
		});
	}
}
function showWxpMenuContent(obj,value){
	$(obj).addClass('selected').siblings().removeClass('selected');
	$("#subMenu").val(value);
	if(value == 0){
		$("#wxMenuContent").show();
		$(".menu_content_container").show();
    }else{
    	$("#wxMenuContent").hide();
    	$(".menu_content_container").hide();
    }
}
</script>
<div class="menu_form_area" id="menu_form_area">                
	<!-- <div id="js_none" class="menu_initial_tips tips_global" style="display: none;">点击左侧菜单进行编辑操作</div> -->
	<form id="wxpMenuEditForm"  method="post" >
	<input type="hidden" id="wxpMenuId" name="wxpMenu.id" value="${wxpMenu.id}"/>
	<input type="hidden" id="wxpMenuType" name="wxpMenu.menuType" value="${wxpMenu.menuType}"/>
	<input type="hidden" id="wxpMenuParentId" name="wxpMenu.parent.id" value="${parentId}"/>
	<input type="hidden" id="replyId" name="wxpReply.id"/>
	<input type="hidden" id="type" name="type" value="${type}"/>
	<input type="hidden" id="subMenu" <s:if test="wxpMenu != null && (wxpMenu.menuType == null || wxpMenu.menuType == '')">value="1"</s:if><s:else>value="0"</s:else>/>
	<input type="hidden" id="textContent" name="wxpReply.textContent"/>
	<input type="hidden" id="msgType" name="wxpReply.msgType" value="${wxpReply.msgType}"/>
	<input type="hidden" id="replyMark" name="wxpReply.replyMark" value="menu"/>
	<input type="hidden" id="wxpMaterialId" name="wxpReply.wxpMaterial.id" value="${wxpReply.wxpMaterial.id}"/>
	<input type="hidden" id="wxpMaterialMediaIdId" name="wxpReply.mediaId" value="${wxpReply.mediaId}"/>
	<div id="js_rightBox" class="portable_editor to_left" style="display: block;">                    
		<div class="editor_inner">                        
			<div class="global_mod float_layout menu_form_hd js_second_title_bar">                            
				<div class="global_info">菜单名称</div>                            
				<div class="global_extra">                                
					<a href="javascript:wxpMenuDelete('${wxpMenu.id}');" id="jsDelBt">删除菜单</a>
				</div>                        
			</div>                        
			<div class="menu_form_bd" id="view">                            
				<!-- <div id="js_innerNone" style="display: block;" class="msg_sender_tips tips_global">已为“菜单名称”添加了5个子菜单，无法设置其他内容。</div>  -->
				<div class="frm_control_group js_setNameBox">                                
					<label for="" class="frm_label">                                    
						<strong class="title js_menuTitle">菜单名称</strong>                                
					</label>                                
					<div class="frm_controls">                                    
						<span class="frm_input_box with_counter counter_in append">  
							<input type="text" id="menuName" placeholder="请输入菜单名称" class="validate[required] frm_input js_menu_name" name="wxpMenu.title" value="${wxpMenu.title}">   
						</span>                                    
						<!-- <p class="frm_msg fail js_titleEorTips dn">字数超过上限</p> -->
						<!-- <p class="frm_msg fail js_titlenoTips dn" style="display: none;">请输入菜单名称</p> --> 
						<s:if test="type == 'first'"><p class="frm_tips js_titleNolTips">字数不超过4个汉字或8个字母</p></s:if>
						<s:else><p class="frm_tips js_titleNolTips">字数不超过8个汉字或16个字母</p></s:else>
					</div>                            
				</div>
				<div class="zi_control_group" <c:if test="${type != 'first'}">style="display:none;"</c:if>>                                
					<label for="" class="frm_label">                                    
						<strong class="title js_menuContent">有子菜单</strong>                                
					</label>                                
					<div class="frm_controls frm_vertical_pt">                                                                        
						<label <s:if test="wxpMenu != null && (wxpMenu.menuType == null || wxpMenu.menuType == '')">class="zi_radio_label selected"</s:if><s:else>class="zi_radio_label"</s:else>
						 onclick="showWxpMenuContent(this,1)">                                         
							<i class="icon_radio"></i>                                        
							<span class="lbl_content">有</span>                                        
							<input type="radio" class="frm_radio" name="menuType" value="1">                                    
						</label>                                                                     
						<label <s:if test="wxpMenu != null && (wxpMenu.menuType == null || wxpMenu.menuType == '')">class="zi_radio_label"</s:if><s:else>class="zi_radio_label selected"</s:else>
						 onclick="showWxpMenuContent(this,0)">
						    <i class="icon_radio"></i>                                        
						    <span class="lbl_content">没有</span>                                        
						    <input type="radio" class="frm_radio" name="menuType" value="0">                                    
						</label>                                                                        
					</div>                            
				</div> 
				<div class="frm_control_group frm_control_grou" id="wxMenuContent" <s:if test="type == 'first'"><s:if test="wxpMenu != null && (wxpMenu.menuType == null || wxpMenu.menuType == '')">style="display:none;"</s:if></s:if>>                                
					<label for="" class="frm_label">                                    
						<strong class="title js_menuContent">菜单内容</strong>                                
					</label>                               
					<div class="frm_controls frm_vertical_pt">                                                                        
						<label class="frm_radio_label js_radio_sendMsg <c:if test="${wxpMenu.menuType != '' && wxpMenu.menuType == 'click'}">selected</c:if>"
							data-editing="0" data_type = "click">                                        
							<i class="icon_radio"></i>                                        
							<span class="lbl_content">发送消息</span>                                        
							<input type="radio" class="frm_radio" name="wmenuType" value="click">                                    
						</label>                                                                        
						<label class="frm_radio_label js_radio_sendMsg <c:if test="${wxpMenu.menuType != '' && wxpMenu.menuType == 'view'}">selected</c:if>"
							data-editing="0" data_type = "view">
						    <i class="icon_radio"></i>                                        
						    <span class="lbl_content">跳转网页</span>                                        
						    <input type="radio" class="frm_radio" name="wmenuType" value="view">                                    
						</label>                                                                        
					</div>                            
				</div>   
				<div class="menu_content_container" <c:if test="${wxpMenu != null && (wxpMenu.menuType == null || wxpMenu.menuType == '')}">style="display:none;"</c:if>>   
				     <div class="menu_content send jsMain" id="edit" >
						<div class="msg_sender" id="editDiv" <c:if test="${wxpMenu.menuType != '' && wxpMenu.menuType == 'view'}">style="display:none;"</c:if>>
				          	<div class="msg_tab">
							    <div class="tab_navs_panel">
							        <span class="tab_navs_switch_wrp switch_prev js_switch_prev">
							            <span class="tab_navs_switch"></span>
							        </span>
							        <div class="tab_navs_wrp">
							            <ul class="tab_navs js_tab_navs" style="margin-left:0;">
							                <li class="tab_nav news tab_appmsg width5 selected" data-type="news" data-tooltip="图文消息">
							                    <a href="javascript:void(0);">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图文消息</span></a>
							                </li>
							                <li class="tab_nav text tab_text width5" data-type="text"  data-tooltip="文字">
							                    <a href="javascript:void(0);">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">文字</span></a>
							                </li>
							                <li class="tab_nav image tab_img width5" data-type="image" data-tooltip="图片">
							                    <a href="javascript:void(0);">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">图片</span></a>
							                </li>
							            </ul>
							        </div>
							    </div>
								<div class="tab_panel">
								    <div class="tab_content">
								    	<div class="js_appmsgArea inner " id="wx_mes_reply_news_add" <s:if test="wxpReply.msgType == 'news'">style="display: none;"</s:if>>
										    <div class="tab_cont_cover jsMsgSendTab" data-index="0" >
							                    <div class="media_cover">
										            <span class="create_access">
										                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:selectMaterialNewsList();" data-type="10" data-index="0">
							                                <i class="icon36_common add_gray"></i>
							                                <strong>从素材库中选择</strong>
							                            </a>
										            </span>
							                    </div>
										        <div class="media_cover" style="margin-left:5.5%;">
										            <span class="create_access" >
							                            <a target="_blank" class="add_gray_wrp create_new_appmsg" href="javascript:;">
										                    <i class="icon36_common add_gray"></i>
										                    <strong>新建图文消息</strong>
										                </a>
										            </span>
										        </div>
								    		</div>
								    	</div>
								    	<div class="inner tab_cont_cover" id="wx_mes_reply_news" <s:if test="wxpReply == null || wxpReply.msgType != 'news'">style="display: none;"</s:if>>
											<div class="js_appmsgitem media_cover" id="wx_mes_reply_news_inner">
											</div>
											<div class="media_cover" style="margin-left: 5%;">
												<span onclick="wxpMaterialNewsDelete()">删除</span>
											</div>
								    	</div>
								    </div>
									<!-- 文字 -->
								    <div class="tab_content" style="display: none;">
								    	<div class="js_textArea inner no_extra Main">
								    		<div class="emotion_editor Input_Box" >
							    				<div class="edit_area js_editorArea Input_text" onkeyup="changePinglun(600)" contenteditable="true" style="overflow-y: auto; overflow-x: hidden;"  id="myarea">${wxpReply.textContent}</div>
							    				<div class="editor_toolbar Input_Foot">
							        				<a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch imgBtn">表情</a>
							        				<div class="editor_tip js_editorTip" id="pinglun">还可以输入<em id="len">600</em>字</div>
							    				</div>
											</div>
											<div class="faceDiv" style="width: 560px; display: none;">
												<section class="emoji_container" len = "600" em_width="560"></section>
											</div>
										</div>
								    </div>
									<!-- 图片 -->
									<div class="tab_content" style="display: none;">
								    	<div class="js_imgArea inner ">
								    		<!--type 10图文 2图片  3语音 15视频 11商品消息-->
							                <div class="tab_cont_cover jsMsgSendTab" data-index="2" data-type="2">
							                	<div id="wx_mes_reply_image_add" <s:if test="wxpReply.msgType == 'image'">style="display: none;"</s:if>>
								                    <div class="media_cover">
											            <span class="create_access">
											                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:selectMaterialImageList();" data-type="2" data-index="2">
								                                <i class="icon36_common add_gray"></i>
								                                <strong>从素材库中选择</strong>
								                            </a>
											            </span>
								                    </div>
								                    <div class="media_cover" style="margin-left:5.5%;">
											            <span class="create_access">
											                <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:wxpMaterialImageEdit();">
								                                <i class="icon36_common add_gray"></i>
								                                <strong>上传图片</strong>
								                            </a>
											            </span>
								                    </div>
								                 </div>
								                 <div class="shangchuanpic row" <s:if test="wxpReply == null || wxpReply.msgType != 'image'">style="display: none;"</s:if> id="wx_mes_reply_image">
							                        <div class="appmsg_item has_cover col-xs-3">
										                <div class="appmsg_thumb_wrp" >
										                	<img src="${wxpReply.wxpMaterial.imageUrl }" width="100%">
										                </div>
										                <a href="javascript:wxpMaterialImageDelete()" class="edit_mask preview_mask js_preview" data-msgid="402689313" data-idx="1">
										                    <div class="edit_mask_content">
										                         <i class="ace-icon fa fa-trash-o bigger-300"></i>
										                    </div>
										                    <span class="vm_box"></span>
										                </a>
										            </div>
							                    </div>
							                </div>
								    	</div>
								    </div>
								</div>
						   </div>
						</div>                                    
					</div>
					<!-- 跳转网页 -->                                                                
					<div class="menu_content url jsMain" id="url" <c:if test="${wxpMenu.menuType != '' && wxpMenu.menuType == 'click'}">style="display:none;"</c:if>>
			           <p class="menu_content_tips tips_global">关注者点击该子菜单会跳到以下链接</p>
			           <div class="frm_control_group">                                            
			           		<label for="" class="frm_label">页面地址</label>
			           		<div class="frm_controls">                                                
			           			<span class="frm_input_box"> 
			           			     <input type="text" class="frm_input" id="urlText" name="wxpMenu.menuUrl" value="${wxpMenu.menuUrl}">
			           			</span>                                                
                  			</div>                                        
                  		</div>                                        
                      </div>   
                  </div>                        
              </div>                    
      	</div>                      
 	</div>           
</div>
<div id="bottom_main" class="tool_area_wrp">                                  
	<div style="margin-top:20px;text-align:center;">                                                                       
		<a href="javascript:void(0);" class="btn btn-primary" onclick="wxpMenuSaveOrUpdate()">保存</a>
		<a href="javascript:void(0);" class="btn btn-success">提交到微信</a>
	</div>                
</div>