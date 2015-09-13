<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="shortcut icon" href="images/weixin.ico"/>
	<link rel="stylesheet" href="css/weixin.css" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script> 
	<script type="text/javascript" src="js/weixin.js"></script>
	<title>仿微信</title>
	<style>
	 iframe{border:0;}
	</style>
</head>
<body>
<div class="wrap">
	<div class="left">
		<div class="left-top">
			<div class="self-info">
				<div class="self-details">
					<div class="self-pic">
						<img src="images/photo1.jpg" class="person-pic"/>
					</div>
					<div class="self-name">张桠鑫</div>
				</div>
				<div class="more"></div>
				<div class="more-list">
					<ul class="option-list">
						<li class="option1">添加好友</li>
						<li class="option2">修改资料</li>
						<li class="option3">退出登录</li>
					</ul>
				</div>
				<div class="my-details">
					<div class="pic-top"></div>
					<div class="name-sex-middle"></div>
					<div class="tip-bottom">备注</div>
				</div>
			</div>
			<div class="search">
				<div class="search-ico"></div>
				<input placeholder="搜索">
			</div>
		</div>
		<div class="left-body">
			<div class="nav-btn">
				<div class="nav-ico">
					<div class="ico-first icon"></div>
				</div>
				<div class="divide"></div>
				<div class="nav-ico">
					<div class="ico-second icon"></div>
				</div>
				<div class="divide"></div>
				<div class="nav-ico">
					<div class="ico-third icon"></div>
				</div>
			</div>
			<div class="chat-body-person">
				<div class="all-chat chat-list1">      <!-- 正在对话列表 -->
					<div class="chat">
						<div class="chat-info example">
							<div class="chat-pic">
								<img src="images/photo2.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">张森</div>
							<div class="chat-option"></div>
							<div class="last-chat">最新消息</div>
						</div>
						<div class="chat-info">
							<div class="chat-pic">
								<img src="images/photo2.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">张森</div>
							<div class="chat-option"></div>
							<div class="last-chat">最新消息</div>
						</div>
					</div>
					<div class="chat">
						<div class="chat-info">
							<div class="chat-pic">
								<img src="images/photo3.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">王二</div>
							<div class="chat-option"></div>
							<div class="last-chat">最新消息</div>
						</div>
					</div>
				</div>
				<div class="all-chat chat-list2">               <!-- 公众号列表 -->
					<div class="chat">
						<div class="chat-info">
							<div class="chat-pic">
								<img src="images/photo1.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">屈臣氏</div>
							<div class="chat-option"></div>
							<div class="last-chat">有活动啦</div>
						</div>
					</div>
					<div class="chat">
						<div class="chat-info">
							<div class="chat-pic">
								<img src="images/photo12.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">联华</div>
							<div class="chat-option"></div>
							<div class="last-chat">有活动啦</div>
						</div>
					</div>
				</div>
				<div class="all-chat chat-list3">                        <!-- 好友列表 -->
					<div class="chat" id="model" style="display:none;">
						<a class="chat-info" target="page2.html">
							<div class="chat-pic">
								<img src="images/photo7.jpg" class="person-pic"/>
							</div>
							<div class="chat-name">王婷</div>
						</a>
					</div>
				</div>
			</div>
			<div class="nav-list" style="height: 520px;width: 100%;">
				<iframe id="J_list" border="0" scroll="no" width="100%" height="100%"></iframe>
			</div>
		</div>
	</div>
	<div class="right">
		<iframe id="J_page" border="0" scroll="no" width="100%" height="100%"></iframe>
	</div>
	
	
	<!-- 用户操作 添加 好友 -->
	<div class="tanchu1 tanchu">
		<div class="add">
			<div class="search-ico"></div>
			<input placeholder="要添加的好友名">
		</div>
		<div class="added">请等候被验证通过.</div>
		<div class="add-button">添加</div>
		<div class="close-button"></div>
	</div>
	<!-- 用户操作 添加 好友 -->
	<div class="tanchu2 tanchu">
		<div class="back">您确定要退出吗？</div>
		<a class="back-sure"  href="http://www.w3school.com.cn">离开</a>
		<div class="back-cancel">取消</div>
		<div class="close-button"></div>
	</div>
	<div class="tanchu-mengban"></div>
</div>
</div>
<script>
var current_fri;   // 全局变量，后面使用
$( window ).on( "load", function(){
	//先把取出后台传过来的数据，list3好友列表填满
	var chat_friend=$("#model").clone();     
  	var arr_length=data_all.length;
	for(var i=0;i<arr_length;i++){
		var friend_data=data_all[i];   //把Json数组中的每个Json对象取出来
		chat_friend.find(".chat-name").text(friend_data.nick_name)
		$(".chat-list3").append(chat_friend.clone().show());
	}
	
	//判断点那个按钮，相应的显示菜单
	$(".icon").click(function(){						
		
		if ($(this).hasClass("ico-first")){
			$(".chat-list1").show();
			$(".chat-list1").siblings().hide();
		}else if($(this).hasClass("ico-second")){
			$(".chat-list2").show();
			$(".chat-list2").siblings().hide();
		}else{
			$(".chat-list3").show();
			$(".chat-list3").siblings().hide();
		};
	});
	
	//点第三个列表中的人，出现页面二，好友的详细信息。
	$(".chat-list3 a").click(function(){    	
		var url=$(this).attr("target");
		current_fri=$(this);
		$("#J_page").attr("src",url);
		$(this).parent().addClass("actived");
		$(this).parent().siblings().removeClass("actived");
	});

});

	
</script>
</body>
</html>

