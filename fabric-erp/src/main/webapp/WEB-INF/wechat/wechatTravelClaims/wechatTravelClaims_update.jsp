<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建报销</title>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>

<input type="hidden" id="nonceStr" value="${nonceStr }" />
<input type="hidden" id="timestamp" value="${timestamp}" />
<input type="hidden" id="signature" value="${signature }" />
<input type="hidden" id="qiyeCorpId" value="${qiyeCorpId }" />
<script type="text/javascript">
	var qiyeCorpId = $('#qiyeCorpId').val();
	var timestamp = $('#timestamp').val();
	var nonceStr = $('#nonceStr').val();
	var signature = $('#signature').val();

	wx.config({
	debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	appId : qiyeCorpId, // 必填，企业号的唯一标识，此处填写企业号corpid
	timestamp : timestamp, // 必填，生成签名的时间戳
	nonceStr : nonceStr, // 必填，生成签名的随机串
	signature : signature,// 必填，签名，见附录1
	jsApiList : [ 'chooseImage' ]
	// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function() {
		wx.chooseImage({
		count : 9, // 默认9
		sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
		sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
		success : function(res) {
			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		}
		});
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});
	wx.error(function(res) {
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	function chooseImage() {
		var images = {
		localId : [],
		serverId : []
		};
		wx.chooseImage({
			success : function(res) {
				images.localId = res.localIds;
				// alert('已选择 ' + res.localIds.length + ' 张图片');  

				if (images.localId.length == 0) {
					alert('请先使用 chooseImage 接口选择图片');
					return;
				}
				var i = 0, length = images.localId.length;
				images.serverId = [];
				function upload() {
					wx.uploadImage({
					localId : images.localId[i],
					success : function(res) {
						i++;
						// alert('已上传：' + i + '/' + length);  
						images.serverId.push(res.serverId);
						alert(res.serverId);
						if (i < length) {
							upload();
						}
					},
					fail : function(res) {
						alert(JSON.stringify(res));
					}
					});
				}
				upload();
			}
		});
	}
	$(function() {
		$('#beginTime').date({
			theme : "datetime"
		});
		$(".newCla_list1 h1 a")
				.click((function() {
					$(".detBox")
							.append("" + "<div class='con'>" + "<div class='newMeet_list2'>" + "<dl>" + "<dt class='reiDet'><span>报销明细</span><strong><a href='#'>+复制</a><a href='#' class='red_txt del'>-删除</a></strong></dt>" + "<dt><span>所属项目</span> <strong> <select> <option value='A'>请选择类型</option> <option value='B'>请选择类型</option> </select> </strong></dt>" + "<dt><span>报销科目</span> <strong> <select> <option value='A'>请选择类型</option> <option value='B'>请选择类型</option> </select> </strong></dt>" + "<dt><span>金额(元)</span><strong>0.00</strong></dt>" + "<dt><span>产生日期</span><strong><input type='text' id='beginTime' placeholder='2015-04-23'></strong></dt>" + "</dl>" + "</div>" + " <div class='newMeet_list1'> " + "<h3><textarea placeholder='输入该详细的详情备注(限300字)'></textarea></h3>" + "<h4><a href='#'><img src='images/newMeet_icon1.png'/></a><a href='#'><img src='images/newMeet_icon2.png'/></a> </h4> " + "</div>" + "</div>");
				}));
		$(".del").click(function() {
			$(this).parents('.con').remove();
		});
		$(".newMeet_list1 h4 a b").click(function() {
			$(this).parents('a').remove();
		});
	});

	function goChooseEmployee() {
		$("#reimburseForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/wechatTravelClaimsAction!saveOrUpdate.action",
		dataType : "text",
		success : function(json) {
			window.location = "${vix}/wechat/wechatTravelClaimsAction!goChooseEmployee.action?id=" + json;
		}
		});
	};

	function saveOrUpdate() {
		var travelExpenseName = $("#travelExpenseName").val();

		// 验证
		if (!travelExpenseName || $.trim(travelExpenseName).length <= 0) {
			alert("请输入报销主题");
			return;
		}

		$("#reimburseForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/wechatTravelClaimsAction!saveOrUpdate.action?sub=1",
		dataType : "text",
		success : function(json) {
			window.location = "${vix}/wechat/wechatTravelClaimsAction!goTaskList.action";
		}
		});
	};

	function uploaddoc() {
		$("#reimburseForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/wechatTravelClaimsAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/wechatTravelClaimsAction!goSaveOrUpdate.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
</script>
</head>
<body>
	<form theme="simple" enctype="multipart/form-data" id="reimburseForm">
		<input type="hidden" id="travelExpenseId" name="travelExpense.id" value="${travelExpense.id }" />
		<section class="newMeet newCla">
			<div class="newMeet_list2" style="margin-bottom: 0;">
				<dl>
					<dt>
						<span>报销主题</span><strong><input type="text" id="travelExpenseName" name="travelExpense.name" value="${travelExpense.name }" placeholder="报销主题"></strong>
					</dt>
					<dt>
						<span>报销单编号</span><strong><input type="text" id="code" name="travelExpense.code" value="${travelExpense.code }" placeholder="报销单编号"></strong>
					</dt>
				</dl>
			</div>
			<div class="detBox">
				<div class="con">
					<div class="newMeet_list2">
						<dl>
							<dt class="reiDet">
								<span>报销明细</span><strong><a href="#">+复制</a><a href="#" class="red_txt del">-删除</a> </strong>
							</dt>
							<dt>
								<span>所属项目</span> <strong> <select>
										<option value="A">请选择类型</option>
										<option value="B">请选择类型</option>
								</select>
								</strong>
							</dt>
							<dt>
								<span>报销科目</span> <strong> <select name="expenseAccount">
										<option value="A">请选择类型</option>
										<option value="B">请选择类型</option>
								</select>
								</strong>
							</dt>
							<dt>
								<span>金额(元)</span><strong>0.00</strong>
							</dt>
							<dt>
								<span>产生日期</span><strong><input type="text" id="beginTime" placeholder="2015-04-23"></strong>
							</dt>
						</dl>
					</div>
					<div class="newMeet_list1">
						<h3>
							<textarea placeholder="输入该详细的详情备注(限300字)"></textarea>
						</h3>
						<h4>
							<s:if test="wxpQyPictureList.size > 0">
								<s:iterator value="wxpQyPictureList" var="entity" status="s">
									<a href="#" onclick="deletePicture('${entity.id }');"><b></b><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
								</s:iterator>
							</s:if>
							<a id="btn_addPic1" class="btn_addPic1" onclick="chooseImage();"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
						</h4>
					</div>
				</div>
			</div>
			<div id="datePlugin"></div>
			<div class="newCla_list1">
				<h1>
					<a href="#">+添加一条明细</a>
				</h1>
				<h2>
					共<b>1</b>条明细，报销总计：￥<b>0</b>
				</h2>
				<ul>
					<li>报销备注填写注意事项</li>
					<li>1、需实报实销</li>
					<li>2、发票必须真实有效</li>
					<li>3、不能乱写项目</li>
				</ul>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<h1>
					<span>附件（${docNum }）</span><a class="btn_addPic" href="javascript:void(0);">+上传 <input class="filePrew" tabIndex="3" type="file" size="3" id="docToUpload" name="docToUpload" onchange="uploaddoc();"></a>
				</h1>
			</div>
			<!----- 已有的附件--->
			<div class="attLis">
				<s:if test="uploaderList.size > 0">
					<s:iterator value="uploaderList" var="entity" status="s">
						<dl>
							<dt>
								<span><img src="${vix}/vixntcommon/base/images/icon-08.png"></span> <strong><b>${fn:substring(entity.fileName, 0,20)}</b>${entity.filesize }</strong>
							</dt>
							<a onclick="godeletedoc('${entity.id }');"><dd></dd></a>
						</dl>
					</s:iterator>
				</s:if>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<p>审批人</p>
				<h4>
					<a href="#" onclick="goChooseEmployee();"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
			</div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate();">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script>
	$(function() {
		$('.newCla_list2 p span b').click(function() {
			if ($(this).hasClass('cur')) {
				$(this).removeClass('cur');
			} else {
				$(this).addClass('cur');
			}
		})
	});
	var params = {
	fileInput : $("#fileToUpload").get(0),
	url : $("#uploadForm").attr("action"),
	filter : function(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			if (file.type.indexOf("image") == 0) {
				if (file.size >= 5120000) {
					alert('您这张"' + file.name + '"图片大小过大，应小于5M');
				} else {
					arrFiles.push(file);
				}
			} else {
				alert('文件"' + file.name + '"不是图片。');
			}
		}
		return arrFiles;
	},
	onSelect : function(files) {
		var html = '', i = 0;
		$("#btn_addPic1").before(html);
		var funAppendImage = function() {
			file = files[i];
			if (file) {
				var reader = new FileReader();
				reader.onload = function(e) {
					html = html + '<a href="#"><img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></a>';
					i++;
					funAppendImage();
				}
				reader.readAsDataURL(file);
			} else {
				$("#btn_addPic1").before(html);
			}
		};
		funAppendImage();
	}
	};
	ZXXFILE = $.extend(ZXXFILE, params);
	ZXXFILE.init();
</script>