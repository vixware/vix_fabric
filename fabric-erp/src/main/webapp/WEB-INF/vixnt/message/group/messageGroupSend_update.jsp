<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-cogs"></i> 短信管理<span onclick="">&gt; 短信群发配置</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/message/messageGroupSendAction!goList.action');">
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
			<form class="form-horizontal" id="messageGroupSendForm">
				<fieldset>
					<input type="hidden" id="id" name="messageGroupSend.id" value="${messageGroupSend.id}" />
					<div class="form-group">
						<label class="col-md-1 control-label"><span class="text-danger">*</span> 群发主题:</label>
						<div class="col-md-5">
							<input id="name" name="messageGroupSend.title" value="${messageGroupSend.title}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-1 control-label"> 群发类型:</label>
						<div class="col-md-5">
							<select id="sendType" name="messageGroupSend.sendType" class="form-control validate[required]">
								<option value="1" <c:if test='${messageGroupSend.sendType == 0}'>selected="selected"</c:if>>节假日群发</option>
								<option value="1" <c:if test='${messageGroupSend.sendType == 1}'>selected="selected"</c:if>>新店开业群发</option>
								<option value="2" <c:if test='${messageGroupSend.sendType == 2}'>selected="selected"</c:if>>新品上市群发</option>
								<option value="3" <c:if test='${messageGroupSend.sendType == 3}'>selected="selected"</c:if>>会员唤醒群发</option>
							</select>
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
										<textarea class="form-control no-border validate[required]" rows="4" id="content" name="messageGroupSend.content"> ${messageGroupSend.content }</textarea>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/message/messageGroupSendAction!goList.action');">
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
	$("#messageGroupSendForm").validationEngine();
	function saveOrUpdate() {
		if ($("#messageGroupSendForm").validationEngine('validate')) {
			$("#messageGroupSendForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/message/messageGroupSendAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/message/messageGroupSendAction!goList.action');
				}
			});
		} else {
			return false;
		}
	};
</script>