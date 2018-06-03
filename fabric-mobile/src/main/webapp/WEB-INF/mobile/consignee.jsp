<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html style="height: 100%;overflow: hidden;">
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-收货地址</title>
</head>
<body>
<header>
	<c:if test="${type == 'order'}">
		<a href="${beiken}/beiken/beikenOrderAction!goConfirmOrder.action" class="b-header-code b-header-return"></a>收货地址
	</c:if>
	<c:if test="${type == 'user'}">
		<a href="${beiken}/beiken/beikenUserCenterAction!goIndex.action?key=user" class="b-header-code b-header-return"></a>收货地址
	</c:if>
    <a href="${beiken}/beiken/beikenUserCenterAction!goSaveOrUpdateConsignee.action?type=${type}" class="b-header-edit">新建</a>
</header>
<section class="shiAdd">
	<c:choose>
		<c:when test="${addressList != null && fn:length(addressList) > 0}">
			<c:forEach items="${addressList}" var="address">
				<div class="shiAdd_list1 mb10 b_b b_t">
			        <dl class="b_b">
			            <dt><span>收货人：${address.consigneeName}</span><strong>电话：${address.mobilePhone}</strong></dt>
			            <dd>${address.province.name}${address.city.name}${address.district.name}${address.address}</dd>
			        </dl>
			        <h2>
			        	<span onclick="javascript:setDefaultConsignee('${address.id}')"><b <c:if test="${address.isDefault == 'Y'}">class="check"</c:if> ></b>设置默认</span>
			        	<strong>
			        		<a href="javascript:deleteConsigneeById('${address.id}')"">删除</a>
			        		<a href="${beiken}/beiken/beikenUserCenterAction!goSaveOrUpdateConsignee.action?id=${address.id}&type=${type}" class="red">编辑</a> 
						</strong>
					</h2>
			    </div>
			</c:forEach>
		</c:when>
		<c:otherwise>
   			<div class="bs_nodata"><p class="nodata-img" style="margin-top: 0px;"></p><p><a href="javascript:;">暂无数据</a></p></div>
   		</c:otherwise>
	</c:choose>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function setDefaultConsignee(id){
		$.ajax({
			url : '${beiken}/beiken/beikenUserCenterAction!setDefaultConsignee.action?id='+id,
			cache : false,
			success : function(result){
				var r = result.split(":"); 
		    	if(r[0] != '0'){
		   			location.reload();
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
	}
	
	function deleteConsigneeById(id){
		$.confirm("确定要删除该收货地址吗？", "确认删除?", function() {
			$.ajax({
				url : '${beiken}/beiken/beikenUserCenterAction!deleteConsigneeById.action?id='+id,
				cache : false,
				success : function(result){
					var r = result.split(":");
					if(r[0] != '0'){
				  		location.reload();
					}else{
						$.toptip(r[1]);
						return;
					}
				}
			});
		}, function() {
		});
	}
</script>