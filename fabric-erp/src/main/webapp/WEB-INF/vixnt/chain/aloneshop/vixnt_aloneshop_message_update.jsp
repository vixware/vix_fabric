<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> <a style="cursor: pointer;">连锁管理</a><span>> 门店管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntAloneShopAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>短信设置</h2>
		</header>
		<div>
			<div class="widget-body">
				<form id="channelDistributorMessageSetForm" class="form-horizontal">
					<fieldset>
						<input type="hidden" id="id" name="channelDistributorMessageSet.id" value="${channelDistributorMessageSet.id}" /> 
						<input type="hidden" id="amount" name="channelDistributorMessageSet.amount" value="${channelDistributorMessageSet.amount}" /> 
						<input type="hidden" id="num" name="channelDistributorMessageSet.num" value="${channelDistributorMessageSet.num}" /> 
						<input type="hidden" id="channelDistributorId" name="channelDistributorMessageSet.channelDistributor.id" value="${channelDistributorMessageSet.channelDistributor.id}" />
						<div class="form-group">
							<label class="col-md-2 control-label"> 是否启用: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="channelDistributorMessageSet.isEnable" <s:if test="channelDistributorMessageSet.isEnable == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="channelDistributorMessageSet.isEnable" <s:if test="channelDistributorMessageSet.isEnable == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 消费自动通知: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.salesInform == 0">active</s:if>"> <input type="radio" value="0" id="salesInform" name="channelDistributorMessageSet.salesInform" <s:if test="channelDistributorMessageSet.salesInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.salesInform == 1">active</s:if>"> <input type="radio" value="1" id="salesInform" name="channelDistributorMessageSet.salesInform" <s:if test="channelDistributorMessageSet.salesInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 生日自动通知: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.birthdayInform == 0">active</s:if>"> <input type="radio" value="0" id="birthdayInform" name="channelDistributorMessageSet.birthdayInform" <s:if test="channelDistributorMessageSet.birthdayInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.birthdayInform == 1">active</s:if>"> <input type="radio" value="1" id="birthdayInform" name="channelDistributorMessageSet.birthdayInform" <s:if test="channelDistributorMessageSet.birthdayInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 纪念日通知: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.commemorationInform == 0">active</s:if>"> <input type="radio" value="0" id="commemorationInform" name="channelDistributorMessageSet.commemorationInform" <s:if test="channelDistributorMessageSet.commemorationInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.commemorationInform == 1">active</s:if>"> <input type="radio" value="1" id="commemorationInform" name="channelDistributorMessageSet.commemorationInform" <s:if test="channelDistributorMessageSet.commemorationInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 服务计划提醒: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.servicePlansInform == 0">active</s:if>"> <input type="radio" value="0" id="servicePlansInform" name="channelDistributorMessageSet.servicePlansInform" <s:if test="channelDistributorMessageSet.servicePlansInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.servicePlansInform == 1">active</s:if>"> <input type="radio" value="1" id="servicePlansInform" name="channelDistributorMessageSet.servicePlansInform" <s:if test="channelDistributorMessageSet.servicePlansInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 营业额短信通知: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.businessVolumeInform == 0">active</s:if>"> <input type="radio" value="0" id="businessVolumeInform" name="channelDistributorMessageSet.businessVolumeInform" <s:if test="channelDistributorMessageSet.businessVolumeInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.businessVolumeInform == 1">active</s:if>"> <input type="radio" value="1" id="businessVolumeInform" name="channelDistributorMessageSet.businessVolumeInform" <s:if test="channelDistributorMessageSet.businessVolumeInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 节假日问候/促销群发: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.holidayInform == 0">active</s:if>"> <input type="radio" value="0" id="holidayInform" name="channelDistributorMessageSet.holidayInform" <s:if test="channelDistributorMessageSet.holidayInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.holidayInform == 1">active</s:if>"> <input type="radio" value="1" id="holidayInform" name="channelDistributorMessageSet.holidayInform" <s:if test="channelDistributorMessageSet.holidayInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 新店开业促销群发: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.startBusinessInform == 0">active</s:if>"> <input type="radio" value="0" id="startBusinessInform" name="channelDistributorMessageSet.startBusinessInform" <s:if test="channelDistributorMessageSet.startBusinessInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.startBusinessInform == 1">active</s:if>"> <input type="radio" value="1" id="startBusinessInform" name="channelDistributorMessageSet.startBusinessInform" <s:if test="channelDistributorMessageSet.startBusinessInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 新品上市促销群发: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.newItemInform == 0">active</s:if>"> <input type="radio" value="0" id="newItemInform" name="channelDistributorMessageSet.newItemInform" <s:if test="channelDistributorMessageSet.newItemInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.newItemInform == 1">active</s:if>"> <input type="radio" value="1" id="newItemInform" name="channelDistributorMessageSet.newItemInform" <s:if test="channelDistributorMessageSet.newItemInform == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 沉睡会员唤醒群发: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="channelDistributorMessageSet.sleepingMemberInform == 0">active</s:if>"> <input type="radio" value="0" id="sleepingMemberInform" name="channelDistributorMessageSet.sleepingMemberInform" <s:if test="channelDistributorMessageSet.sleepingMemberInform == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="channelDistributorMessageSet.sleepingMemberInform == 1">active</s:if>"> <input type="radio" value="1" id="sleepingMemberInform" name="channelDistributorMessageSet.sleepingMemberInform" <s:if test="channelDistributorMessageSet.sleepingMemberInform == 1">checked="checked"</s:if>>否
									</label>
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
								<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntAloneShopAction!goList.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#channelDistributorMessageSetForm").validationEngine();
	function saveOrUpdate() {
		if ($("#channelDistributorMessageSetForm").validationEngine('validate')) {
			$("#channelDistributorMessageSetForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntAloneShopAction!saveOrUpdateChannelDistributorMessageSet.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntAloneShopAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>