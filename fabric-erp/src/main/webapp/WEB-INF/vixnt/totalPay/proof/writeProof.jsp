<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<style>
	.newSelect{appearance:none;-moz-appearance:none;-webkit-appearance:none;border:0px}
	.newInput-xs{width:20px;border:0px;}
	.newInput-ms{width:60px;border:0px;}
	.money,.pecuniary_unit,.moneyTotal{padding:0 !important;width:275px}
	.money span,.moneyTotal span{display: block;float: left;height: 50px;line-height: 50px;width: 24px;text-align: center;border-right: 1px solid #e0e0e0;}
	.pecuniary_unit span{display: block;float: left;height: 34px;line-height: 34px;width: 24px;text-align: center;border-right: 1px solid #e0e0e0;}
	.money input{width:253px;height:50px;border:1px solid #83A4DB;text-align:right;padding:0 10px;}
	.pecuniary_unit span:last-child,.moneyTotal span:last-child,.money span:nth-last-child(2){border-right:none;}
	.money span:nth-child(3),.moneyTotal span:nth-child(3),.money span:nth-child(6),.moneyTotal span:nth-child(6),.pecuniary_unit span:nth-child(3),.pecuniary_unit span:nth-child(6){border-right-color:rgba(74,144,226,.5)}
	.money span:nth-child(9),.moneyTotal span:nth-child(9),.pecuniary_unit span:nth-child(9){border-right-color:rgba(226,106,74,.5)}
	.testInput{display:none;}
</style>
		<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-table fa-fw "></i> 凭证<span>> 填制凭证</span>
					</h1>
				</div>
			<!-- 	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
					<ul id="sparks" class="">
						<li class="sparks-info">
							<h5>
								My Income <span class="txt-color-blue">$47,171</span>
							</h5>
							<div class="sparkline txt-color-blue hidden-mobile hidden-md hidden-sm">1300, 1877, 2500, 2577, 2000, 2100, 3000, 2700, 3631, 2471, 2700, 3631, 2471</div>
						</li>
						<li class="sparks-info">
							<h5>
								Site Traffic <span class="txt-color-purple"><i class="fa fa-arrow-circle-up" data-rel="bootstrap-tooltip" title="Increased"></i>&nbsp;45%</span>
							</h5>
							<div class="sparkline txt-color-purple hidden-mobile hidden-md hidden-sm">110,150,300,130,400,240,220,310,220,300, 270, 210</div>
						</li>
						<li class="sparks-info">
							<h5>
								Site Orders <span class="txt-color-greenDark"><i class="fa fa-shopping-cart"></i>&nbsp;2447</span>
							</h5>
							<div class="sparkline txt-color-greenDark hidden-mobile hidden-md hidden-sm">110,150,300,130,400,240,220,310,220,300, 270, 210</div>
						</li>
					</ul>
				</div> -->
			</div>

			<!-- widget grid -->
			<section id="widget-grid" class="">
				<div class="row">
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="retrieval">
							<div class="col-xs-4">
								凭证字
								<label class="select"> 
									<select class="input-sm">
										<option value="0">凭证字</option>
										<option value="1">贷方金额</option>
									</select>
									<i></i>
								</label>
								凭证号
								<label class="select"> 
									<select class="input-sm">
										<option value="0">凭证号</option>
										<option value="1">小于</option>
									</select>
									<i></i>
								</label>
								
							</div>
							<div class="col-xs-4 text-center">
								<h1 style="margin-top:0">记账凭证</h1>
							</div>
							<div class="col-xs-4 text-right">
								附件数
								<label class="select"> 
									<select class="input-sm">
										<option value="0">100</option>
										<option value="1">50</option>
										<option value="2">10</option>
									</select>
									<i></i>
								</label>
							</div>
						</div>
						<table class="table table-bordered   smart-form ">
											<thead>
												<tr>
													<th rowspan="2">操作 </th>
													<th rowspan="2">摘要 </th>
													<th rowspan="2">会计科目 </th>
													<th rowspan="2">辅助核算</th>
													<th colspan="3">外币核算</th>
													<th colspan="">借方金额</th>
													<th colspan="">贷方金额</th>
												</tr>
												<tr>
													<th>币种</th>
													<th>汇率</th>
													<th>原币</th>
													<th class="pecuniary_unit"><span>亿</span><span>千</span><span>百</span><span>十</span><span>万</span><span>千</span><span>百</span><span>十</span><span>元</span><span>角</span><span>分</span></th>
													<th class="pecuniary_unit"><span>亿</span><span>千</span><span>百</span><span>十</span><span>万</span><span>千</span><span>百</span><span>十</span><span>元</span><span>角</span><span>分</span></th>
												</tr>
											</thead>
											<tbody class="tableP">
												
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span> 
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
												</tr>
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
												</tr>
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
												</tr>
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
												</tr>
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
													
												</tr>
												<tr>
													<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>
													<td><input class="form-control newSelect"  type="text"></td>
													<td><select class="form-control newSelect" id="select-1">
														<option></option>
														<option>Atlanta</option>
														<option>Baltimore</option>
													</select></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td><input class="form-control newInput-ms"  type="text"></td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													<td class="money">
														<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
														<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>
													</td>
													
													
												</tr>
												<tr>
													<td colspan="7">合计</td>
													<td class="moneyTotal"><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span></td>
													<td class="moneyTotal"><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span></td>

												</tr>
												<tr>
													<td colspan="2" rowspan="2">备注</td>
													<td colspan="2"><label class="col-md-2">项目</label><div class="col-md-10"><input class="form-control newSelect"  type="text"></div></td>
													<td colspan="2"><label class="col-md-2">部门</label><div class="col-md-10"><input class="form-control newSelect"  type="text"></div></td>
													<td colspan="3"><label class="col-md-2">个人</label><div class="col-md-10"><input class="form-control newSelect"  type="text"></div></td>	
												</tr>
												<tr>
													<td colspan="2"><label class="col-md-2">客户</label><div class="col-md-10"><input class="form-control newSelect"  type="text"></div></td>
													<td colspan="2"><label class="col-md-2">业务员</label><div class="col-md-10"><input class="form-control newSelect "  type="text"></div></td>
													<td colspan="3"></td>	
												</tr>
												
												
											</tbody>
										</table>
					</article>
				</div>
			</section>
		</div>

	<script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		$(document).ready(function() {
			pageSetUp();
		})

	</script>
	 <script type="text/javascript">
	 	$(".money").click(function(){
	 		$(this).children("span").hide();
	 		$(this).children("input").show().focus();
	 	});
	 	$(".testInput").blur(function(){
	 		$(this).hide();
	 		$(this).siblings("span").show();
	 		var str1=$(this).val().replace(/,/g,"");
	 		var str2=$(this).parents(".money").children("span");
	 		for(var i=0;i<9;i++){
	 			str2[i].innerHTML=""
	 		}
	 		var len=str1.length;
	 		switch(len)
	 		{
	 		case 1:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 		  break;
	 		case 2:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 		  break;
	 		case 3:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			break;
	 		case 4:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			break;
	 		case 5:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			str2[4].innerHTML=str1[len-5];
	 			break;
	 		case 6:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			str2[4].innerHTML=str1[len-5];
	 			str2[3].innerHTML=str1[len-6];
	 			break;
	 		case 7:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			str2[4].innerHTML=str1[len-5];
	 			str2[3].innerHTML=str1[len-6];
	 			str2[2].innerHTML=str1[len-7];
	 			break;
	 		case 8:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			str2[4].innerHTML=str1[len-5];
	 			str2[3].innerHTML=str1[len-6];
	 			str2[2].innerHTML=str1[len-7];
	 			str2[1].innerHTML=str1[len-8];
	 			break;
	 		case 9:
	 			str2[10].innerHTML=0;
	 			str2[9].innerHTML=0;
	 			str2[8].innerHTML=str1[len-1];
	 			str2[7].innerHTML=str1[len-2];
	 			str2[6].innerHTML=str1[len-3];
	 			str2[5].innerHTML=str1[len-4];
	 			str2[4].innerHTML=str1[len-5];
	 			str2[3].innerHTML=str1[len-6];
	 			str2[2].innerHTML=str1[len-7];
	 			str2[1].innerHTML=str1[len-8];
	 			str2[0].innerHTML=str1[len-9];
	 			break;
	 		
	 		}
	 	});
	 	$(".testInput").keypress(function(e){
	 		if(e.which==13){
	 			$(this).hide();
		 		$(this).siblings("span").show();
		 		var str1=$(this).val().replace(/,/g,"");
		 		var str2=$(this).parents(".money").children("span");
		 		for(var i=0;i<9;i++){
		 			str2[i].innerHTML=""
		 		}
		 		var len=str1.length;
		 		switch(len)
		 		{
		 		case 1:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 		  break;
		 		case 2:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 		  break;
		 		case 3:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			break;
		 		case 4:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			break;
		 		case 5:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			str2[4].innerHTML=str1[len-5];
		 			break;
		 		case 6:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			str2[4].innerHTML=str1[len-5];
		 			str2[3].innerHTML=str1[len-6];
		 			break;
		 		case 7:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			str2[4].innerHTML=str1[len-5];
		 			str2[3].innerHTML=str1[len-6];
		 			str2[2].innerHTML=str1[len-7];
		 			break;
		 		case 8:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			str2[4].innerHTML=str1[len-5];
		 			str2[3].innerHTML=str1[len-6];
		 			str2[2].innerHTML=str1[len-7];
		 			str2[1].innerHTML=str1[len-8];
		 			break;
		 		case 9:
		 			str2[10].innerHTML=0;
		 			str2[9].innerHTML=0;
		 			str2[8].innerHTML=str1[len-1];
		 			str2[7].innerHTML=str1[len-2];
		 			str2[6].innerHTML=str1[len-3];
		 			str2[5].innerHTML=str1[len-4];
		 			str2[4].innerHTML=str1[len-5];
		 			str2[3].innerHTML=str1[len-6];
		 			str2[2].innerHTML=str1[len-7];
		 			str2[1].innerHTML=str1[len-8];
		 			str2[0].innerHTML=str1[len-9];
		 			break;
		 		
		 		}
	 		}
	 	})
	 	/* 输入金额使用了onkeyup事件暂时不支持输入小数部分，默认为0 */
        function outputmoney(number) {
            number = number.replace(/\,/g, "");
            if(isNaN(number) || number == "")return "";
            number = Math.round(number * 100) / 100;
            if (number < 0)
                return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
            else
                return outputdollars(Math.floor(number - 0) + '');
        }
        //格式化金额
        function outputdollars(number) {
            if (number.length <= 3)
                return (number == '' ? '0' : number);
            else {
                var mod = number.length % 3;
                var output = (mod == 0 ? '' : (number.substring(0, mod)));
                for (i = 0; i < Math.floor(number.length / 3); i++) {
                    if ((mod == 0) && (i == 0))
                        output += number.substring(mod + 3 * i, mod + 3 * i + 3);
                    else
                        output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
                }
                return (output);
            }
        }
        function outputcents(amount) {
            amount = Math.round(((amount) - Math.floor(amount)) * 100);
            return (amount < 10 ? '.0' + amount : '.' + amount);
        }
        
        function putNumber(number){
        	var str1=$(".moneyTotal span")
        	console.log(str1)
        	for(var i=number.length-1;i>=0;i--){
        		console.log(number[i])
        	}
        }
        
        var trNew='<tr>'+
					'<td class="text-center"><i class="fa fa-plus"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus"></i></td>'+
					'<td><input class="form-control newSelect"  type="text"></td>'+
					'<td><select class="form-control newSelect" id="select-1">'+
						'<option></option>'+
						'<option>Atlanta</option>'+
						'<option>Baltimore</option>'+
						'</select></td>'+
					'<td><input class="form-control newInput-ms"  type="text"></td>'+
					'<td><input class="form-control newInput-ms"  type="text"></td>'+
					'<td><input class="form-control newInput-ms"  type="text"></td>'+
					'<td><input class="form-control newInput-ms"  type="text"></td>'+
					'<td class="money">'+
						'<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>'+
						'<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>'+
					'</td>'+
					'<td class="money">'+
						'<span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span>'+
						'<input type="text"  maxlength="11" class="testInput" onkeyup="this.value=outputmoney(this.value);"/>'
					'</td>'
				'</tr>'
			//添加或删除一行
			$(".tableP").on("click","tr td .fa-plus",function(){
				$(this).parents("tr").after(trNew); 

			});
			$(".tableP").on("click","tr td .fa-minus",function(){
				$(this).parents("tr").remove(); 
			});
    </script>
