/**
 * Created by zyj on 2016/9/23.
 */
// JavaScript Document


(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = 10 * (clientWidth / 320) + 'px';
        };

    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
    recalc();
})(document, window);




$(function(){
    var tab_menu_li = $('.tab_menu li');
    $('.tab_con .common:gt(0)').hide();

    tab_menu_li.click(function(){
        var index = $(this).index()+1;
        $(this).addClass('selected')
            .siblings().removeClass('selected');

        tab_menu_li.each(function(){
            var i = $(this).index()+1;

        })

        var tab_menu_li_index = tab_menu_li.index(this);
        $('.tab_con .common').eq(tab_menu_li_index).show()
            .siblings().hide();
    }).click(function(){
        $(this).addClass('hover');
    },function(){
        $(this).removeClass('hover');
    });

});


$(function(){

//我的佣金
    $('.myComm .show dd').show('slow');
    $('.myComm dl').click(function(){
        $(this).children('dd').slideDown().end().siblings().children('dd').slideUp();
        $(this).children('dt').addClass('icon').end().siblings().children('dt').removeClass();
        $('.myComm .show').children('dt').css({'background-image' : 'url(images/sort_down.png)'});
    });
    $('.myComm .show').click(function(){
        $(this).children('dt').css({'background-image' : 'url(images/sort_up.png)' });
    });


//添加帐号
    $('.addAccount ul a').click(function(){
        $(this).addClass('cur').parent().siblings('li').find('a').removeClass('cur');
    });

    $('.weiXinPay_list3 ul a').click(function(){
        $(this).addClass('cur').parent().siblings('li').find('a').removeClass('cur');
    });



});
