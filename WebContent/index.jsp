<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录和注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="shortcut icon" href="images/weixin.ico"/>
	<script type="text/javascript" src="js/jquery.js"></script> 
	<style>
		*{padding:0;margin:0;font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;}
		body{background:url(images/background.jpg) no-repeat;}
		.wrap{width:300px;margin:0 auto;}
		.center-box{width:100%;height:400px;margin-top:100px;background:#fff;opacity:0.6;filter:alpha(opacity=60);border-radius:10px;}
		.box-head{overflow:hidden;}
		.box-body{width:200px;margin:0 auto;overflow: hidden;}
		.tabs{margin:8px auto;width:200px;overflow:hidden;}
		.login-tab,.reg-tab{width:98px;float:left;background-color:#fff;color:green;height:35px;line-height:35px;text-align:center;border: 1px solid green;border-radius:5px;cursor:pointer;}
		.actived{background-color:green;color:#fff;}
		.login-box{width:200px;margin-top: 30px;display:none;}
		.regeist-box{width:200px;margin-top: 30px;}
		input{line-height:35px;height:35px;width:198px;border:1px solid #aaa;border-radius:5px;margin:5px auto;text-indent:10px;}
		.login-input input{margin-bottom:30px;}
		.log-btns,.reg-btns,.select{width:200px;margin-top:30px;;overflow:hidden;bottom:40px;}
		.reg-btn,.login-btn{border:1px solid green;background-color:#fff;color:green;text-align:center;heihgt:30px;line-height:30px;border-radius:3px;cursor:pointer;}
		.reg-cancel,.login-cancel{float:left;width:68px;border:1px solid green;background-color:#fff;color:green;text-align:center;margin-left: 30px;heihgt:30px;line-height:30px;border-radius:3px;}
		span{width:70px;height:20px;line-height:20px;display:inline-block;}
		select{text-indent:5px;width:198px;height:30px;line-height:30px;margin-top:5px;margin-bottom:5px;border-radius:3px;}
		.reg-back{width:200px;margin-top: 50px;display:none;}
		.suc-alert{width:150px;margin:0 25px 30px 25px;height:30px;line-height:30px;}
		.go{display:inline-block;width:150px;margin:0 25px 30px 25px;background-color:green;color:#fff;height:30px;line-height:30px;border-radius:5px;}
	</style>
  </head>
  <body>
<div class="wrap">
	<div class="center-box">
		<div class="box-head">
			<div class="tabs">
				<div class="login-tab">登陆</div>
				<div class="reg-tab">注册</div>
			</div>
		</div>
		<div class="box-body">
			<div class="login-box">
				<form action="user!login.action" method="post">
					<div class="login-input">
						<input type="text" placeholder="输入注册邮箱或手机号" name="user.email" id="emialinput">
						<input type="password" placeholder="输入密码" name="user.password">
					</div>
					<div class="log-btns">
						<input class="login-btn" type="submit" value="登录"/>
					</div>
				</form>
			</div>
			
			<div class="regeist-box">
				<form action="user!register.action" method="post">
					<input type="text" placeholder="输入注册邮箱" name="user.email" id="regemail">
					<input type="password" placeholder="输入密码" name="user.password" id="regpassword">
					<input type="text" placeholder="输入注册用户名" name="user.name" id="redusername">
					<select name="user.sex">
						<option value="女">女</option>
						<option value="男">男</option>
					</select>
					<select name="user.city" id="shengfen">
						<option value="江苏">江苏</option>
						<option value="浙江">浙江</option>
						<option value="上海">上海</option>
						<option value="安徽">安徽</option>
					</select>
					
					<!--<input type="text" placeholder="备注" name="tips" id="tip">-->
					<div class="reg-btns">
						<input class="reg-btn" type="submit" value="注册"/>
					</div>
				</form>
			</div>
			<div class="reg-back">
				<div class="suc-alert">恭喜您已经注册成功</div>
				<a class="go">去您的微信主页看看</a>
			</div>
		</div>
</div>
</div>  
<script>
	$( window ).on( "load", function(){

		 //点登陆，显示登陆界面
		$(".wrap").on( "click",".login-tab",function(){
			$(this).addClass("actived");
			$(this).siblings().removeClass("actived");
			$(".login-box").siblings().hide();
			$(".login-box").show();
			$(".center-box").css("height","340px");
		});
		//点注册，显示注册页面
		$(".wrap").on( "click",".reg-tab",function(){
			$(this).addClass("actived");
			$(this).siblings().removeClass("actived");
			$(".regeist-box").siblings().hide();
			$(".regeist-box").show();
			$(".center-box").css("height","400px");
		}); 
		$(".login-tab").click(); 
		
});

</script>		
    
  </body>
</html>
