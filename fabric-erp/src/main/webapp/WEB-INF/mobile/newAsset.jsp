<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<title>联盟链-新建资产</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-icon--"></i></a>
    <p class="coupons">新建资产</p>
    <a href="javascript:releaseAsset();" class="header_r_txt">发布</a>
</header>
<section class="mt_45 mb_53">
<form action="${ws}/front/fabricAssetAction!saveOrUpdateAsset.action" method="post" id="asset-form">
    	<div class="weui-cells">
	        <div class="weui-cell">
	            <div class="weui-cell__bd">
	                <p><font color="red">*</font>资产编码</p>
	            </div>
	            <div class="weui-cell__bd">
				      <input type="text" class="weui-input tar" id="assetCode" name="fabricAsset.assetCode" value="${fabricAsset.assetCode}" placeholder="请填写">
				</div>
	        </div>
	          <div class="weui-cell">
	            <div class="weui-cell__bd">
	                <p><font color="red">*</font>资产名称</p>
	            </div>
	            <div class="weui-cell__bd">
				      <input type="text" class="weui-input tar" id="assetName" name="fabricAsset.assetName" value="${fabricAsset.assetName}" placeholder="请填写">
				</div>
	        </div>
	    </div>
    <div class="weui-cells">
    	 <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>所在地点</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="site" name="fabricAsset.site" value="${fabricAsset.site}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>面积</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="plantingArea" name="fabricAsset.plantingArea" value="${fabricAsset.plantingArea}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>取得使用权日期</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="date" class="weui-input tar" id="dateOfAccess" name="fabricAsset.dateOfAccess" value="${fabricAsset.dateOfAccess}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>账面原值</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="originalValue" name="fabricAsset.originalValue" value="${fabricAsset.originalValue}" placeholder="请填写（数字）">
			</div>
        </div>
        <%--  <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>使用类型</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="useType" name="fabricAsset.useType" value="${fabricAsset.useType}" placeholder="请填写">
			</div>
        </div> --%>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>非转经或闲置起始日期</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="date" class="weui-input tar" id="inidleTime" name="fabricAsset.inidleTime" value="${fabricAsset.inidleTime}" placeholder="请填写">
			</div>
        </div>
		 <%-- <div class="weui-cell weui-cell_switch">
		    <div class="weui-cell__bd"><p><font color="red">*</font>有无协议或合同</p></div>
		    <div class="weui-cell__ft">
		      <input class="weui-switch" type="checkbox" id="hsContract" name="fabricAsset.hsContract" value="${fabricAsset.hsContract}" <c:if test="${fabricAsset.hsContract == 1}" > checked="checked"</c:if> >
		    </div>
		  </div> --%>
		  
		  <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>非转经年收益</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="annualEarnings" name="fabricAsset.annualEarnings" value="${fabricAsset.annualEarnings}" placeholder="请填写">
			</div>
        </div>
    </div>
    <div class="weui-cells">
    	<div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>经营单位或个人名称</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="operation" name="fabricAsset.operation" value="${fabricAsset.operation}" placeholder="请填写">
			</div>
        </div>
	 	 <a class="weui-cell weui-cell_access" href="javascript:goMapPage();">
		    <div class="weui-cell__bd">
		      <p>登记地块坐标信息</p>
		    </div>
		    <div class="weui-cell__ft">进入地图
		    </div>
		  </a>
    </div>
    <input type="hidden"   name="fabricAsset.id" value="${fabricAsset.id}" >
    </form>
</section>
<!-- 底部代码 -->
</body>
<script type="text/javascript">
	function releaseAsset(){
		var assetName = $("#assetName").val();
		 if (!assetName) {
			$.alert("请输入名称！");
			return;
		}
		 /**if (!operator) {
			$.alert( "请输入经营人！");
			return;
		}
		if (!plantingArea) {
			$.alert( "请输入种植面积！");
			return;
		}
		if (!predeterminedPrice) {
			$.alert( "请输入预定价格");
			return;
		}
		if (!prepaidDeposit) {
			$.alert( "请输入预付定金");
			return;
		} */
		
		$("#asset-form").ajaxSubmit({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			url : "${ws}/front/fabricAssetAction!saveOrUpdateAsset.action",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					document.location.href = '${ws}/front/fabricAssetAction!goAssetList.action';
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
	}
	
	function goMapPage(){
		var assetName = $("#assetName").val();
		 if (!assetName) {
			$.alert("请输入名称！");
			return;
		}
		$("#asset-form").ajaxSubmit({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			url : "${ws}/front/fabricAssetAction!saveOrUpdateAssetInner.action",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					document.location.href = '${ws}/front/fabricAssetAction!goMapPage.action?id='+r[2];
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
		
	}
	
</script>
</html>