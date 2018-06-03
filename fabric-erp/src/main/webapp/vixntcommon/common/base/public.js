/////////////////////// 系统内动态效果绑定开始 ////////////////////////////
/** 统计图标线条颜色定义 */
var $chrt_border_color = "#efefef";
var $chrt_grid_color = "#DDD"
var $chrt_main = "#E24913";
/* red       */
var $chrt_second = "#6595b4";
/* blue      */
var $chrt_third = "#FF9F01";
/* orange    */
var $chrt_fourth = "#7e9d3a";
/* green     */
var $chrt_fifth = "#BD362F";
/* dark red  */
var $chrt_mono = "#000";

/** 统计工具条下拉列表效果 */
$(".js-status-update a").click(function() {
	var selText = $(this).text();
	var $this = $(this);
	$this.parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
	$this.parents('.dropdown-menu').find('li').removeClass('active');
	$this.parent().addClass('active');
});
/** 绑定弹出窗口皮肤 */
layer.config({
    extend: ['skin/moon/style.css'], //加载新皮肤
    skin: 'layer-ext-moon' //一旦设定，所有弹层风格都采用此主题。
});

/** 汉化日期控件 */
$.datepicker.regional['zh-CN'] = {   
	clearText: '清除',   
	clearStatus: '清除已选日期',   
	closeText: '关闭',   
	closeStatus: '不改变当前选择',   
	prevText: '<<',//上月   
	prevStatus: '显示上月',   
	prevBigText: '<<',   
	prevBigStatus: '显示上一年',   
	nextText: '>>',//下月   
	nextStatus: '显示下月',   
	nextBigText: '>>',   
	nextBigStatus: '显示下一年',   
	currentText: '今天',   
	currentStatus: '显示本月',   
	monthNames: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],   
	monthNamesShort: ['一月','二月','三月','四月','五月','六月', '七月','八月','九月','十月','十一月','十二月'],   
	monthStatus: '选择月份',   
	yearStatus: '选择年份',   
	weekHeader: '周',   
	weekStatus: '年内周次',   
	dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],   
	dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],   
	dayNamesMin: ['日','一','二','三','四','五','六'],   
	dayStatus: '设置 DD 为一周起始',   
	dateStatus: '选择 m月 d日, DD',   
	dateFormat: 'yy-mm-dd',   
	firstDay: 1,   
	initStatus: '请选择日期',   
	isRTL: false
};
$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
/////////////////////// 系统内动态效果绑定结束 ////////////////////////////