<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="jobInfo">
	<input type="hidden" id="id" name="channelDistributorSet.id" value="${channelDistributorSet.id}" /> <input type="hidden" id="channelDistributorId" name="channelDistributorSet.channelDistributor.id" value="${channelDistributorSet.channelDistributor.id}" />
	<legend>POS端配置信息:</legend>
	<div class="form-group">
		<label class="col-md-2 control-label"> 是否需要同步到门店: </label>
		<div class="col-md-4">
			<div data-toggle="buttons" class="btn-group">
				<label class="btn btn-default <s:if test="channelDistributorSet.isUpload == 0">active</s:if>"> <input type="radio" value="0" id="isUpload" name="channelDistributorSet.isUpload" <s:if test="channelDistributorSet.isUpload == 0">checked="checked"</s:if>>是
				</label> <label class="btn btn-default <s:if test="channelDistributorSet.isUpload == 1">active</s:if>"> <input type="radio" value="1" id="isUpload" name="channelDistributorSet.isUpload" <s:if test="channelDistributorSet.isUpload == 1">checked="checked"</s:if>>否
				</label>
			</div>
		</div>
		<label class="col-md-2 control-label"> 门店内部编码:</label>
		<div class="col-md-4">
			<input id="innerCode" name="channelDistributorSet.innerCode" value="${channelDistributorSet.innerCode}" class="form-control" type="text" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label"> 门店商品分类接口地址:</label>
		<div class="col-md-10">
			<input id="zf_pos_menutype_upload" name="channelDistributorSet.zf_pos_menutype_upload" value="${channelDistributorSet.zf_pos_menutype_upload}" class="form-control" type="text" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label"> 门店商品接口地址:</label>
		<div class="col-md-10">
			<input id="zf_pos_menu_upload" name="channelDistributorSet.zf_pos_menu_upload" value="${channelDistributorSet.zf_pos_menu_upload}" class="form-control" type="text" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label"> 接口调用账号:</label>
		<div class="col-md-4">
			<input id="zf_pos_useraccount" name="channelDistributorSet.zf_pos_useraccount" value="${channelDistributorSet.zf_pos_useraccount}" class="form-control" type="text" />
		</div>
		<label class="col-md-2 control-label"> 接口调用密码:</label>
		<div class="col-md-4">
			<input id="zf_pos_password" name="channelDistributorSet.zf_pos_password" value="${channelDistributorSet.zf_pos_password}" class="form-control" type="password" />
		</div>
	</div>
	<legend>电商端配置信息:</legend>
	<div class="form-group">
		<label class="col-md-2 control-label"> 是否需要同步到电商: </label>
		<div class="col-md-4">
			<div data-toggle="buttons" class="btn-group">
				<label class="btn btn-default <s:if test="channelDistributorSet.isUploadToEc == 0">active</s:if>"> <input type="radio" value="0" id="isUploadToEc" name="channelDistributorSet.isUploadToEc" <s:if test="channelDistributorSet.isUploadToEc == 0">checked="checked"</s:if>>是
				</label> <label class="btn btn-default <s:if test="channelDistributorSet.isUploadToEc == 1">active</s:if>"> <input type="radio" value="1" id="isUploadToEc" name="channelDistributorSet.isUploadToEc" <s:if test="channelDistributorSet.isUploadToEc == 1">checked="checked"</s:if>>否
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label"> 电商接口地址:</label>
		<div class="col-md-10">
			<input id="ec_useraccount_upload" name="channelDistributorSet.ec_useraccount_upload" value="${channelDistributorSet.ec_useraccount_upload}" class="form-control" type="text" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label"> 电商接口调用账号:</label>
		<div class="col-md-4">
			<input id="ec_useraccount" name="channelDistributorSet.ec_useraccount" value="${channelDistributorSet.ec_useraccount}" class="form-control" type="text" />
		</div>
		<label class="col-md-2 control-label"> 电商接口调用密码:</label>
		<div class="col-md-4">
			<input id="ec_password" name="channelDistributorSet.ec_password" value="${channelDistributorSet.ec_password}" class="form-control" type="password" />
		</div>
	</div>
</div>