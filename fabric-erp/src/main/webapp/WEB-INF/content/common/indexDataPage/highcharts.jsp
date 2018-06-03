<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/plugin/highcharts/modules/exporting.js"></script>
<script src="${vix}/plugin/highcharts/highcharts.js"></script>
<script src="${vix}/plugin/highcharts/highcharts-more.js"></script>
<script type="text/javascript">
	$(function () {
			$ ('#container').highcharts ({
			chart : {
				type : 'column'
			} ,
			title : {
				text : 'Stacked column chart'
			} ,
			xAxis : {
				categories : [
				'Apples' , 'Oranges' , 'Pears' , 'Grapes' , 'Bananas'
				]
			} ,
			yAxis : {
			min : 0 ,
			title : {
				text : 'Total fruit consumption'
			} ,
			stackLabels : {
			enabled : true ,
			style : {
			fontWeight : 'bold' ,
			color : (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			}
			}
			} ,
			legend : {
			align : 'right' ,
			x : - 70 ,
			verticalAlign : 'top' ,
			y : 20 ,
			floating : true ,
			backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white' ,
			borderColor : '#CCC' ,
			borderWidth : 1 ,
			shadow : false
			} ,
			tooltip : {
				formatter : function (){
					return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
				}
			} ,
			plotOptions : {
				column : {
				stacking : 'normal' ,
				dataLabels : {
				enabled : true ,
				color : (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
				}
				}
			} ,
			series : [
			{
			name : 'John' ,
			data : [
			5 , 3 , 4 , 7 , 2
			]
			} , {
			name : 'Jane' ,
			data : [
			2 , 2 , 3 , 2 , 1
			]
			} , {
			name : 'Joe' ,
			data : [
			3 , 4 , 4 , 2 , 5
			]
			}
			]
			});
        $('#container2').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Historic World Population by Region'
            },
            subtitle: {
                text: 'Source: Wikipedia.org'
            },
            xAxis: {
                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Population (millions)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' millions'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'Year 1800',
                data: [107, 31, 635, 203, 2]
            }, {
                name: 'Year 1900',
                data: [133, 156, 947, 408, 6]
            }, {
                name: 'Year 2008',
                data: [973, 914, 4054, 732, 34]
            }]
        });
	    $('#container3').highcharts({
		    chart: {
		        polar: true
		    },
		    title: {
		        text: 'Highcharts Polar Chart'
		    },
		    pane: {
		        startAngle: 0,
		        endAngle: 360
		    },
		    xAxis: {
		        tickInterval: 45,
		        min: 0,
		        max: 360,
		        labels: {
		        	formatter: function () {
		        		return this.value + '°';
		        	}
		        }
		    },
		    yAxis: {
		        min: 0
		    },
		    plotOptions: {
		        series: {
		            pointStart: 0,
		            pointInterval: 45
		        },
		        column: {
		            pointPadding: 0,
		            groupPadding: 0
		        }
		    },
		    series: [{
		        type: 'column',
		        name: 'Column',
		        data: [8, 7, 6, 5, 4, 3, 2, 1],
		        pointPlacement: 'between'
		    }, {
		        type: 'line',
		        name: 'Line',
		        data: [1, 2, 3, 4, 5, 6, 7, 8]
		    }, {
		        type: 'area',
		        name: 'Area',
		        data: [1, 8, 2, 7, 3, 6, 4, 5]
		    }]
		});
	    $('#container1').highcharts({
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: 'Speedometer'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    yAxis: {
		        min: 0,
		        max: 200,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: 'km/h'
		        },
		        plotBands: [{
		            from: 0,
		            to: 120,
		            color: '#55BF3B' // green
		        }, {
		            from: 120,
		            to: 160,
		            color: '#DDDF0D' // yellow
		        }, {
		            from: 160,
		            to: 200,
		            color: '#DF5353' // red
		        }]        
		    },
		
		    series: [{
		        name: 'Speed',
		        data: [80],
		        tooltip: {
		            valueSuffix: ' km/h'
		        }
		    }]
		
		}, 
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {
			        var point = chart.series[0].points[0],
			            newVal,
			            inc = Math.round((Math.random() - 0.5) * 20);
			        
			        newVal = point.y + inc;
			        if (newVal < 0 || newVal > 200) {
			            newVal = point.y - inc;
			        }
			        
			        point.update(newVal);
			        
			    }, 3000);
			};
		});
    });
	</script>
<div class="left_box connectedSortable">
	<div class="box_content">
		<div class="c_title clearfix">
			<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>采购</strong>
		</div>
		<div class="c_box">
			<div id="container" style="min-width: 350px; max-width: 600px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
	<div class="box_content">
		<div class="c_title clearfix">
			<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>销售</strong>
		</div>
		<div class="c_box">
			<div id="container2" style="min-width: 450px; max-width: 600px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
</div>
<div class="right_box connectedSortable">
	<div class="box_content">
		<div class="c_title clearfix">
			<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>人资</strong>
		</div>
		<div class="c_box">
			<div id="container1" style="min-width: 350px; max-width: 500px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
	<div class="box_content">
		<div class="c_title clearfix">
			<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>其他</strong>
		</div>
		<div class="c_box">
			<div id="container3" style="min-width: 350px; max-width: 500px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
</div>
