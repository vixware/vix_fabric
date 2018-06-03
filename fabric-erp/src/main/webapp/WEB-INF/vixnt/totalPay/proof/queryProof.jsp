<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 凭证 <span>> 查询凭证 </span>
			</h1>
		</div>
		<!-- <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
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
							<!-- <div class="retrievalYen"> -->
								<label class="select"> 
									<select class="input-sm">
										<option value="0">借方金额</option>
										<option value="1">贷方金额</option>
									</select>
									<i></i>
								</label>
							<!-- </div>
							<div class="retrievalIf"> -->
								<label class="select"> 
									<select class="input-sm">
										<option value="0">大于</option>
										<option value="1">小于</option>
									</select>
									<i></i>
								</label>
								<label class="select"> 
									<select class="input-sm">
										<option value="0">100</option>
										<option value="1">50</option>
										<option value="2">10</option>
									</select>
									<i></i>
								</label>
								<a href="javascript:void(0);" class="btn btn-primary" style="position:absolute;right:10px;">查询</a>
							<!-- </div> -->
						</div>
						<table class="table table-bordered   smart-form ">
							<thead>
								<tr>
									<th></th>
									<th>日期 </th>
									<th>凭证字号 </th>
									<th>我要 </th>
									<th>会计科目</th>
									<th>币种</th>
									<th>惠币金额</th>
									<th>借方金额</th>
									<th>贷方金额</th>
									<th>立单编号</th>
									<th>制单人</th>
									<th>审核人</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2"><label class="checkbox"> <input type="checkbox" name="checkbox-inline"> <i></i></label></td>
									<td rowspan="2">2017-10-6</td>
									<td rowspan="2">RG-1</td>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td>100000000</td>
									<td>100000000</td>
									<td></td>
									<td rowspan="2">HTC123456789</td>
									<td rowspan="2">王芳</td>
									<td rowspan="2">王刚</td>
								</tr>
								<tr>
									<td></td>
									<td>1002银行存款</td>
									<td>人民币</td>
									<td class="money">100000000</td>
									<td class="money">100000000</td>
									<td></td>
								</tr>
								
							</tbody>
						</table>
					</article>
				</div>
			</section>
		</div>
	<!-- <script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		$(document).ready(function() {
			pageSetUp();
		})
		
		OSREC.CurrencyFormatter.formatAll(
            {
                selector: '.money',
                
            });
	</script>
 -->