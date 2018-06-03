/**切换按钮**/
$(function () {
    zoomB.init();
});
var zoomB={
    LEFTB:0,//保存ul起始的左边距
    LIWIDEB:0,//保存每个li的宽度
    moved_b:0,//保存左移的个数
    LISB:0,//保存li总个数
    init:function(){
        this.LEFTB=parseFloat(getComputedStyle($(".tool-second-tab.cur #icon_call")[0]).left);
        this.LIWIDEB=parseFloat(getComputedStyle($(".tool-second-tab.cur #icon_call>div:first-child")[0]).width);
        this.LISB=$(".tool-second-tab.cur #icon_call>div").length;
        if(this.LISB<=5){
            $(".tool-second-tabs>.tab-left>i")[0].className+="_disabled";
        }
        $(".tool-second-tabs>.tab-left>i")[0].addEventListener("click",this.moveLeft.bind(this));
        $(".tool-second-tabs>.tab-right>i")[0].addEventListener("click",this.moveRight.bind(this));
    },
    moveLeft:function(e){
        if(e.target.className.indexOf("_disabled")==-1){
            this.moved_b--;
            $(".tool-second-tab.cur #icon_call")[0].style.left=-this.moved_b*this.LIWIDEB+this.LEFTB+"px";
            this.checkA();
        }
    },
    checkA:function(e){
        if(this.LISB-this.moved_b==5){
            $(".tool-second-tabs>.tab-right>i")[0].className+="_disabled";
            $(".tool-second-tabs>.tab-left>i")[0].className="backward";
        }
        else if(this.moved_b==0){
            $(".tool-second-tabs>.tab-left>i")[0].className+="_disabled";
            $(".tool-second-tabs>.tab-right>i")[0].className="forward";
        }
        else{
            $(".tool-second-tabs>.tab-left>i")[0].className="backward";
        }
    },
    moveRight:function(e){
        if(e.target.className.indexOf("_disabled")==-1){
            this.moved_b++;
            $(".tool-second-tab.cur #icon_call")[0].style.left=-this.moved_b*this.LIWIDEB+this.LEFTB+"px";
            this.checkA();
        }
    },
}