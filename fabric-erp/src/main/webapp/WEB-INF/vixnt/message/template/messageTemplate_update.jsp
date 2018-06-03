<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-envelope"></i> 短信管理<span onclick="">&gt; 短信模板配置</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/message/messageTemplateAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>短信接口</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="messageTemplateForm">
				<fieldset>
					<input type="hidden" id="id" name="messageSendTemplate.id" value="${messageSendTemplate.id}" />
					<div class="form-group">
						<label class="col-md-1 control-label"><span class="text-danger">*</span> 模板主题:</label>
						<div class="col-md-5">
							<input id="name" name="messageSendTemplate.name" value="${messageSendTemplate.name}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-1 control-label"> 模板类型:</label>
						<div class="col-md-5">
							<select id="messageTemplateType" name="messageSendTemplate.messageTemplateType" class="form-control validate[required]">
								<option value="1" <c:if test='${messageSendTemplate.messageTemplateType == 1}'>selected="selected"</c:if>>开卡通知</option>
								<option value="2" <c:if test='${messageSendTemplate.messageTemplateType == 2}'>selected="selected"</c:if>>充值通知</option>
								<option value="3" <c:if test='${messageSendTemplate.messageTemplateType == 3}'>selected="selected"</c:if>>消费通知</option>
								<option value="4" <c:if test='${messageSendTemplate.messageTemplateType == 4}'>selected="selected"</c:if>>兑换通知</option>
								<option value="5" <c:if test='${messageSendTemplate.messageTemplateType == 5}'>selected="selected"</c:if>>生日通知</option>
								<option value="6" <c:if test='${messageSendTemplate.messageTemplateType == 6}'>selected="selected"</c:if>>纪念日通知</option>
								<option value="7" <c:if test='${messageSendTemplate.messageTemplateType == 7}'>selected="selected"</c:if>>服务计划提醒</option>
								<option value="8" <c:if test='${messageSendTemplate.messageTemplateType == 8}'>selected="selected"</c:if>>营业额通知</option>
								<option value="9" <c:if test='${messageSendTemplate.messageTemplateType == 9}'>selected="selected"</c:if>>来电宝挂机通知</option>
								<option value="10" <c:if test='${messageSendTemplate.messageTemplateType == 10}'>selected="selected"</c:if>>节假日群发</option>
								<option value="11" <c:if test='${messageSendTemplate.messageTemplateType == 11}'>selected="selected"</c:if>>新店开业群发</option>
								<option value="12" <c:if test='${messageSendTemplate.messageTemplateType == 12}'>selected="selected"</c:if>>新品上市群发</option>
								<option value="13" <c:if test='${messageSendTemplate.messageTemplateType == 13}'>selected="selected"</c:if>>会员唤醒群发</option>
								<option value="14" <c:if test='${messageSendTemplate.messageTemplateType == 14}'>selected="selected"</c:if>>验证码</option>
								<option value="15" <c:if test='${messageSendTemplate.messageTemplateType == 14}'>selected="selected"</c:if>>活动通知</option>
								<option value="16" <c:if test='${messageSendTemplate.messageTemplateType == 14}'>selected="selected"</c:if>>支付验证码</option>
								<option value="17" <c:if test='${messageSendTemplate.messageTemplateType == 14}'>selected="selected"</c:if>>会员卡到期提醒</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label"><span class="text-danger">*</span> 基础变量:</label>
						<div class="col-md-11">
							<div class="input-group-btn">
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','username')">用户姓名</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','sex')">用户性别</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','cardNo')">卡号</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','password')">密码</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','integral')">可用积分</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','amount')">可用储值</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','companyName')">公司名称</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','servicephone')">客服电话</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','title')">文章标题</button>
								<button class="btn btn-sm btn-success" type="button" onclick="check('msgContent','commentcontent')">内容</button>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label"> <span class="text-danger">*</span> 短信内容:
						</label>
						<div class="col-md-11">
							<div class="jarviswidget jarviswidget-color-gray">
								<header>
									<h2>
										<strong></strong> <i>短信内容</i>
									</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<textarea class="form-control no-border validate[required]" rows="4" id="msgContent" name="messageSendTemplate.msgContent"> ${messageSendTemplate.msgContent }</textarea>
										<div class="widget-footer">
											<button class="btn btn-sm btn-default" type="button" onclick="$('#msgContent').val('');">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/message/messageTemplateAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#messageTemplateForm").validationEngine();
	function saveOrUpdate() {
		if ($("#messageTemplateForm").validationEngine('validate')) {
			$("#messageTemplateForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/message/messageTemplateAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/message/messageTemplateAction!goList.action');
				}
			});
		} else {
			return false;
		}
	};
	(function($) {
		$.fn.extend({
			insertAtCaret : function(myValue) {
				var $t = $(this)[0];
				if (document.selection) {
					this.focus();
					sel = document.selection.createRange();
					sel.text = myValue;
					this.focus();
				} else if ($t.selectionStart || $t.selectionStart == '0') {
					var startPos = $t.selectionStart;
					var endPos = $t.selectionEnd;
					var scrollTop = $t.scrollTop;
					$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
					this.focus();
					$t.selectionStart = startPos + myValue.length;
					$t.selectionEnd = startPos + myValue.length;
					$t.scrollTop = scrollTop;
				} else {
					this.value += myValue;
					this.focus();
				}
			}
		})
	})(jQuery);
	function check(a, b) {
		$("#" + a).insertAtCaret("," + b);
	}
</script>