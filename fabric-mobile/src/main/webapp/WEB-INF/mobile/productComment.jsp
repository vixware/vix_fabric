<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-商品评论</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>商品评论
    <!-- <a href="#" class="b-header-more"></a> -->
</header>
<section class="myOrder">
    <div class="tab_menu">
        <ul>
            <li class="selected">全部</li>
            <li>好评</li>
            <li>中评</li>
            <li>差评</li>
            <li>晒图</li>
        </ul>
    </div>
    <div class="tab_con">
        <!--- 全部-->
        <div class="common pro-evaluate back_w">
            <section>
                <ul class="e-list mb15">
                    <li class="e-item">
                        <div class="e-item-user mui-flex">
                            <div class="cell t_l u-info">
                                <span class="u-head">
                                    <img src="http://www.bennongji.com/img_server/user/2cb5dc22023e438eae27d6f386/c30/1c9/face/small/1453445281377.jpeg" alt="">
                                </span>
                                <span class="u-nick">一字兰舟</span>
                            </div>
                            <div class="cell t_r">
                                <span>2016-04-28 10:18:12</span>
                            </div>
                        </div>
                        <div class="e-item-con">推荐大家购买</div>
                    </li>
                    <li class="e-item">
                        <div class="e-item-user mui-flex">
                            <div class="cell t_l u-info">
                                <span class="u-head">
                                    <img src="http://www.bennongji.com/img_server/common/user/face/small/default_user_face.jpg" alt="">
                                </span>
                                <span class="u-nick">76我心飞翔~</span>
                            </div>
                            <div class="cell t_r">
                                <span>2016-04-22 11:55:52</span>
                            </div>
                        </div>
                        <div class="e-item-con">推荐大家购买</div>
                    </li>
                    <li class="e-item">
                        <div class="e-item-user mui-flex">
                            <div class="cell t_l u-info">
                                <span class="u-head">
                                    <img src="http://www.bennongji.com/img_server/user/7195d19d287442e7a8f62903e0/d8e/941/face/small/1453709282165.jpeg" alt="">
                                </span>
                                <span class="u-nick">木槿天堂的彼岸花</span>
                            </div>
                            <div class="cell t_r">
                                <span>2016-04-22 09:05:21</span>
                            </div>
                        </div>
                        <div class="e-item-con">推荐大家购买</div>
                    </li>
                    <li class="e-item">
                        <div class="e-item-user mui-flex">
                            <div class="cell t_l u-info">
                                <span class="u-head">
                                    <img src="http://www.bennongji.com/img_server/common/user/face/small/default_user_face.jpg" alt="">
                                </span>
                                <span class="u-nick">9平平淡淡2</span>
                            </div>
                            <div class="cell t_r">
                                <span>2016-04-20 11:06:05</span>
                            </div>
                        </div>
                        <div class="e-item-con">推荐大家购买</div>
                    </li>
                    <li class="e-item">
                        <div class="e-item-user mui-flex">
                            <div class="cell t_l u-info">
                                <span class="u-head">
                                    <img src="http://www.bennongji.com/img_server/common/user/face/small/default_user_face.jpg" alt="">
                                </span>
                                <span class="u-nick">8幸福5</span>
                            </div>
                            <div class="cell t_r">
                                <span>2016-04-19 14:23:00</span>
                            </div>
                        </div>
                        <div class="e-item-con">推荐大家购买</div>
                    </li>
                </ul>
                <!-- <div id="paging_span"><div class="bs_loading"><p><a href="javascript:;" onclick="B_mobile_comm.paging.callBack();" style="font-size:14px;">点击加载下一页</a></p></div></div> -->
            </section>
        </div>
        <!---好评-->
        <div class="common">
            <div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
        </div>
        <!--- 中评-->
        <div class="common">
            <div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
        </div>
        <!---差评-->
        <div class="common">
            <div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
        </div>
        <!--- 晒图-->
        <div class="common">
            <div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
        </div>
    </div>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
    $(function(){
        var tab_menu_li = $('.tab_menu li');
        $('.tab_con .common:gt(0)').hide();

        tab_menu_li.click(function(){
            var index = $(this).index()+1;
            $(this).addClass('selected')
                    .siblings().removeClass('selected');
            var tab_menu_li_index = tab_menu_li.index(this);
            $('.tab_con .common').eq(tab_menu_li_index).show()
                    .siblings().hide();
        }).click(function(){
            $(this).addClass('hover');
        },function(){
            $(this).removeClass('hover');
        });
    });
</script>
