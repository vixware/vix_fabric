<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>联盟链-新建合约</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-icon--"></i></a>
    <p class="coupons">新建合约</p>
    <a href="javascript:releaseContract();" class="header_r_txt">发布</a>
</header>
<section class="mt_45 mb_53">
<form action="${ws}/fabric/fabricContractAction!saveOrUpdateContract.action" method="post" id="contract-form">
 	<div class="weui-cells">
 		<div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>合约编码</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="contractCode" name="fabricContract.contractCode" value="${fabricContract.contractCode}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>标题</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="title" name="fabricContract.title" value="${fabricContract.title}" placeholder="标题">
			</div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>经营人</p>
            </div>
              <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="operator" name="fabricContract.operator" value="${fabricContract.operator}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>产权证明</p>
            </div>
            <div class="weui-cell__ft">请上传</div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>种植面积（㎡）</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="plantingArea" name="fabricContract.plantingArea" value="${fabricContract.plantingArea}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>预计产量</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="estimatedOutput" name="fabricContract.estimatedOutput" value="${fabricContract.estimatedOutput}" placeholder="请填写">
			</div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>预订价格</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="predeterminedPrice" name="fabricContract.predeterminedPrice" value="${fabricContract.predeterminedPrice}" placeholder="请填写">
			</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>现货价格</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="spotPrice" name="fabricContract.spotPrice" value="${fabricContract.spotPrice}" placeholder="请填写">
			</div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><font color="red">*</font>预付定金</p>
            </div>
            <div class="weui-cell__bd">
			       <input type="text" class="weui-input tar" id="prepaidDeposit" name="fabricContract.prepaidDeposit" value="${fabricContract.prepaidDeposit}" placeholder="请填写">
			</div>
        </div>
    </div>
    <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>交易模式</p>
            </div>
            <div class="weui-cell__bd">
			      <input type="text" class="weui-input tar" id="tradingMode" name="fabricContract.tradingMode" value="${fabricContract.tradingMode}" placeholder="请填写">
			</div>
        </a>
    </div>
    <!-- <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>支付方式</p>
            </div>
             <div class="weui-cell__bd">
             	<div class="weui_cell_bd weui_cell_primary">
			      <select class="weui_select" name="select1">
			        <option value="1">微信号</option>
			        <option value="2">银联</option>
			        <option value="3">支付宝</option>
			      </select>
			    </div>
             </div>
        </a>
    </div> -->
    <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>关联资产</p>
            </div>
            <div class="weui-cell__ft">未关联</div>
        </a>
    </div>
    </form>
</section>
</body>
<%-- <%@ include file="/mobilecommon/page/foot.jsp"%> --%>
<script type="text/javascript">
	function releaseContract(){
		var title = $("#title").val();
		var operator = $("#operator").val();
		var plantingArea = $("#plantingArea").val();
		var predeterminedPrice = $("#predeterminedPrice").val();
		var prepaidDeposit = $("#prepaidDeposit").val();
		 if (!title) {
			$.alert( "请输入标题！");
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
		 $("#contract-form").ajaxSubmit({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				url : "${ws}/front/fabricContractAction!saveOrUpdateContract.action",
				success : function(result) {
					var r = result.split(":");
					if(r[0] != '0'){
						document.location.href = '${ws}/front/fabricContractAction!goContractList.action';
					}else{
						$.toptip(r[1]);
						return;
					}
				}
			});
		
		
	}



</script>
</html>