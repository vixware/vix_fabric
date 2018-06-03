<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="widget-body no-padding">
	<div id="customerAccountDistribute" style="height: 350px"></div>
	<script type="text/javascript">
		var customerAccountDistribute = echarts.init(document.getElementById('customerAccountDistribute'));
		var	option1 = {
			    title : {
			        text: '分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            }
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'分布',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:[${customerNum}]
			        }
			    ]
			};
			customerAccountDistribute.clear();
			customerAccountDistribute.setOption(option1);
		    $(window).resize(customerAccountDistribute.resize);
	</script>
</div>