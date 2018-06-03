<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.error-text-2 {
	text-align: center;
	font-size: 700%;
	font-weight: bold;
	font-weight: 100;
	color: #333;
	line-height: 1;
	letter-spacing: -.05em;
	background-image: -webkit-linear-gradient(92deg, #333, #ed1c24);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

.particle {
	position: absolute;
	top: 10%;
	left: 50%;
	width: 1rem;
	height: 1rem;
	border-radius: 100%;
	background-color: #ed1c24;
	background-image: -webkit-linear-gradient(rgba(0, 0, 0, 0),
		rgba(0, 0, 0, .3) 75%, rgba(0, 0, 0, 0));
	box-shadow: inset 0 0 1px 1px rgba(0, 0, 0, .25);
}

.particle--a {
	-webkit-animation: particle-a 1.4s infinite linear;
	-moz-animation: particle-a 1.4s infinite linear;
	-o-animation: particle-a 1.4s infinite linear;
	animation: particle-a 1.4s infinite linear;
}

.particle--b {
	-webkit-animation: particle-b 1.3s infinite linear;
	-moz-animation: particle-b 1.3s infinite linear;
	-o-animation: particle-b 1.3s infinite linear;
	animation: particle-b 1.3s infinite linear;
	background-color: #00A300;
}

.particle--c {
	-webkit-animation: particle-c 1.5s infinite linear;
	-moz-animation: particle-c 1.5s infinite linear;
	-o-animation: particle-c 1.5s infinite linear;
	animation: particle-c 1.5s infinite linear;
	background-color: #57889C;
}

@
-webkit-keyframes particle-a { 0% {
	-webkit-transform: translate3D(-3rem, -3rem, 0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-webkit-transform
:
 
translate3D
(4rem
,
3
rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-webkit-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.75rem
;


height
:
 
.75rem
;


opacity
:
 
.5
;


}
100%
{
-webkit-transform
:
 
translate3D
(-3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-moz-keyframes particle-a { 0% {
	-moz-transform: translate3D(-3rem, -3rem, 0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-moz-transform
:
 
translate3D
(4rem
,
3
rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-moz-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.75rem
;


height
:
 
.75rem
;


opacity
:
 
.5
;


}
100%
{
-moz-transform
:
 
translate3D
(-3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-o-keyframes particle-a { 0% {
	-o-transform: translate3D(-3rem, -3rem, 0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-o-transform
:
 
translate3D
(4rem
,
3
rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-o-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.75rem
;


height
:
 
.75rem
;


opacity
:
 
.5
;


}
100%
{
-o-transform
:
 
translate3D
(-3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
keyframes particle-a { 0% {
	transform: translate3D(-3rem, -3rem, 0);
	z-index: 1;
	animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
transform
:
 
translate3D
(4rem
,
3
rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.75rem
;


height
:
 
.75rem
;


opacity
:
 
.5
;


}
100%
{
transform
:
 
translate3D
(-3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-webkit-keyframes particle-b { 0% {
	-webkit-transform: translate3D(3rem, -3rem, 0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-webkit-transform
:
 
translate3D
(-3rem
,
3
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-webkit-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-webkit-transform
:
 
translate3D
(3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-moz-keyframes particle-b { 0% {
	-moz-transform: translate3D(3rem, -3rem, 0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-moz-transform
:
 
translate3D
(-3rem
,
3
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-moz-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-moz-transform
:
 
translate3D
(3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-o-keyframes particle-b { 0% {
	-o-transform: translate3D(3rem, -3rem, 0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
-o-transform
:
 
translate3D
(-3rem
,
3
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-o-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-o-transform
:
 
translate3D
(3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
keyframes particle-b { 0% {
	transform: translate3D(3rem, -3rem, 0);
	z-index: 1;
	animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.5rem
;


height
:
 
1
.5rem
;


}
50%
{
transform
:
 
translate3D
(-3rem
,
3
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
transform
:
 
translate3D
(3rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-webkit-keyframes particle-c { 0% {
	-webkit-transform: translate3D(-1rem, -3rem, 0);
	z-index: 1;
	-webkit-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.3rem
;


height
:
 
1
.3rem
;


}
50%
{
-webkit-transform
:
 
translate3D
(2rem
,
2
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-webkit-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-webkit-transform
:
 
translate3D
(-1rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-moz-keyframes particle-c { 0% {
	-moz-transform: translate3D(-1rem, -3rem, 0);
	z-index: 1;
	-moz-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.3rem
;


height
:
 
1
.3rem
;


}
50%
{
-moz-transform
:
 
translate3D
(2rem
,
2
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-moz-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-moz-transform
:
 
translate3D
(-1rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
-o-keyframes particle-c { 0% {
	-o-transform: translate3D(-1rem, -3rem, 0);
	z-index: 1;
	-o-animation-timing-function: ease-in-out;
}

25%
{
width
:
 
1
.3rem
;


height
:
 
1
.3rem
;


}
50%
{
-o-transform
:
 
translate3D
(2rem
,
2
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
-o-animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
-o-transform
:
 
translate3D
(-1rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
@
keyframes particle-c { 0% {
	transform: translate3D(-1rem, -3rem, 0);
	z-index: 1;
	animation-timing-function: ease-in-out;
}
25%
{
width
:
 
1
.3rem
;


height
:
 
1
.3rem
;


}
50%
{
transform
:
 
translate3D
(2rem
,
2
.5rem
,
0);
opacity
:
 
1;
z-index
:
 
1;
animation-timing-function
:
 
ease-in-out
;


}
55%
{
z-index
:
 
-1;
}
75%
{
width
:
 
.5rem
;


height
:
 
.5rem
;


opacity
:
 
.5
;


}
100%
{
transform
:
 
translate3D
(-1rem
,
-3
rem
,
0);
z-index
:
 
-1;
}
}
</style>
<s:if test="trendsList.size > 0">
	<s:iterator value="trendsList" var="entity" status="s">
		<h4 class="margin-bottom-10">
			<a href="#" onclick="newsDetail('${entity.id}');"> ${entity.title} </a>
		</h4>
		<div class="margin-bottom-10">
			<p class="note">
				<a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-child"></i> ${entity.uploadPersonName}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i>
					${entity.readTimes}条阅读记录&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i> 新闻来源：${entity.sourceFrom}&nbsp;&nbsp;</a> <a href="javascript:void(0);" style="color: #E63F00;"><i class="fa fa-caret-down"></i> <s:if test="%{#entity.bulletinType == 0}">全体新闻</s:if> <s:elseif test="%{#entity.bulletinType == 1}">内部新闻</s:elseif>
				</a>
			</p>
			<div class="url text-success margin-bottom-10">
				<a href="${entity.sourceFromUrl}">${entity.sourceFromUrl}</a> <i class="fa fa-caret-down"></i>
			</div>
			<p class="description margin-bottom-10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${entity.content}</p>
		</div>
	</s:iterator>
</s:if>
<s:else>
	<div id="main" role="main">
		<div id="content">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="col-sm-12">
							<div class="text-center error-box">
								<h1 class="error-text-2 bounceInDown animated">
									温馨提示 <span class="particle particle--c"></span> <span class="particle particle--a"></span> <span class="particle particle--b"></span>
								</h1>
								<h2 class="font-xl">
									<strong><i class="fa fa-fw fa-warning fa-lg text-warning"></i> 找 <u>不到</u> 新闻</strong>
								</h2>
								<br />
								<p class="lead">
									新闻是企业内部发布新闻的一个窗口，每个人都可以发布新闻，新闻可以是针对整个企业来发布，也可以针对人员内部发布；<b>发布新闻</b>的时候需要选择发送对象，是发给所有人还是某些人。
								</p>
								<p class="font-md">
									<b>无该新闻内容，请在上方输入框重新输入新闻标题！</b>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:else>