<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html style="height: 100%;overflow: hidden;">
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-新增收货地址</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>新增收货地址
    <a href="javascript:void(0);" onclick="saveOrUpdateConsignee();" class="b-header-edit">保存</a>
</header>
<section class="mt_50">
	<form id="consigneeForm" action="" method="post">
		<input type="hidden" id="id" name="address.id" value="${address.id}">
		<input type="hidden" id="isDefault" name="address.isDefault" value="${address.isDefault}">
		<input type="hidden" id="createTime" name="address.createTime" value="${address.createTime}">
	    <div class="addrset">
	        <div class="input-item mb15">
	        	<input type="text" class="input-common" name="address.consigneeName" value="${address.consigneeName}" placeholder="请填写收货人姓名" id="consigneeName">
	        </div>
	        <div class="input-item mb15">
	        	<input type="text" class="input-common" name="address.mobilePhone" value="${address.mobilePhone}" placeholder="请填写联系电话" id="mobilePhone">
	        </div>
	        <div class="addr-sel">
	            <div class="input-item addr-sel-item mb15">
	                <span class="f_l addr-label">省份</span>
	                <div class="addr-sel-inp">
	                    <select type="text" class="input-common" name="address.province.id" placeholder="省份" readonly="" id="provinceId" onchange="loadCity();">
	                        <option value="" reset="true">请选择省份</option>
	                        <c:forEach items="${provinceList}" var="province">
	                        	<option value="${province.id}" <c:if test="${address.province.id == province.id}">selected="selected"</c:if> >${province.name}</option>
	                        </c:forEach>
	             		</select>
	                </div>
	            </div>
	            <div class="input-item addr-sel-item  mb15">
	                <div class="f_l addr-label">城市</div>
	                <div class="addr-sel-inp">
	                    <select type="text" class="input-common" name="address.city.id" placeholder="城市" readonly="" id="cityId" onchange="loadRegion();">
	                    	<option value="">请选择城市</option>
	                    	<c:forEach items="${cityList}" var="city">
	                        	<option value="${city.id}" <c:if test="${address.city.id == city.id}">selected="selected"</c:if> >${city.name}</option>
	                        </c:forEach>
	            		</select>
	                </div>
	            </div>
	            <div class="input-item addr-sel-item  mb15">
	                <div class="f_l addr-label">区县</div>
	                <div class="addr-sel-inp">
	                    <select type="text" class="input-common" name="address.district.id" placeholder="区县" readonly="" id="regionId">
	                    	<option value="">请选择区县</option>
	                    	<c:forEach items="${districtList}" var="district">
	                        	<option value="${district.id}" <c:if test="${address.district.id == district.id}">selected="selected"</c:if> >${district.name}</option>
	                        </c:forEach>
	                    </select>
	                </div>
	            </div>
	        </div>
	        <div class="input-item mb15">
	            <textarea class="addr-textarea" name="address.address" placeholder="请填写详细收货地址" id="address">${address.address}</textarea>
	        </div>
	    </div>
	</form>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function saveOrUpdateConsignee(){
		if(!$("#consigneeName").val()){
			$.toptip('请填写收货人姓名');
			return;
		}
		if(!$("#mobilePhone").val()){
			$.toptip('请填写联系电话');
			return;
		}
		if (!/^0?1[3|4|5|7|8][0-9]\d{8}$/.test($("#mobilePhone").val())) {
			$.toptip('手机号输入有误，请您重新输入');
			return;
		}
		if(!$("#provinceId").val()){
			$.toptip('请选择省份');
			return;
		}
		if(!$("#cityId").val()){
			$.toptip('请选择城市');
			return;
		}
		if(!$("#regionId").val()){
			$.toptip('请选择区县');
			return;
		}
		if(!$("#address").val()){
			$.toptip('请填写详细收货地址');
			return;
		}
		$("#consigneeForm").ajaxSubmit({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			url : "${beiken}/beiken/beikenUserCenterAction!saveOrUpdateConsignee.action",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					document.location.href = '${beiken}/beiken/beikenUserCenterAction!goConsignee.action?type=${type}';
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
	}
	
	function loadCity(){
		var id = $("#provinceId").val();
		var url = "${beiken}/beiken/beikenUserCenterAction!loadCity.action?id="+id;
		$("#cityId").load(url);
		$("#regionId").val("");
	}
	
	function loadRegion(){
		var id = $("#cityId").val();
		var url = "${beiken}/beiken/beikenUserCenterAction!loadRegion.action?id="+id;
		$("#regionId").load(url);
	}
</script>