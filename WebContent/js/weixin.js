
$( window ).on( "load", function(){
	var chat_friend=$("#model").clone();
	var append_t=$(".example").clone();
	var	prepend_t=$(".chat-list1 chat").first().clone() ;
	var can_lis;
    var data_all;                     //定义一个全局变量，用来盛放返回的data
	var myfriend_list=$.ajax({ 
		    type: "POST", 	
			url: "user!getFriendList.action",
			dataType: "json",
			success: function(data){
				data_all=data;        //把返回的Json对象取出
			},
			error: function(jqXHR){     
				alert("发生错误：" + jqXHR.status);  
			}, 
			async:false       //取消异步，可以把data数据取出，并且保持原格式。
		});
		//console.log(data_all);
		
		var arr_length=data_all.length;
		console.log(data_all);
		for(var i=0;i<arr_length;i++){
			var friend_data=data_all[i];   //把Json数组中的每个Json对象取出来
			chat_friend.find(".chat-name").text(friend_data.nick_name)
			$(".chat-list3").append(chat_friend.clone().show());
		}
		//var arr1_length=myfriend_list.length;
		//console.log(arr1_length);
		/*for(var i=0;i<arr_length;i++){
			var friend_data=data[i];   //把Json数组中的每个Json对象取出来
			chat_friend.find(".chat-name").text(friend_data.nick_name)
			$(".chat-list3").append(chat_friend.clone().show());
		}*/
		/*var aArray =new Array();//定义一个数组
		console.log(aArray.length);
		aArray[0] = "张三";
		aArray[1] = "男";
		aArray[2] = "123456@qq.com";//把值一个个添加到数组中。
		var arrayValue = aArray[0];//取出其中一个值
		console.log("");
		
		var json1={"name":"zhang","sex":"man"}
		console.log(json1.name);*/
	
		
	
	
	//用户的更多信息，三明治图标，显示一个菜单栏，包括三个选项：添加好友	，修改资料，退出登录
	$(".wrap").on( "click",".more",function(){
	    $(".more-list").toggle();
	});
	//左侧主菜单的第一个按钮，气泡，点击出现第一个菜单，当前通话菜单	
	$(".wrap").on( "click",".ico-first",function(){
	    $(".ico-first").css("background-position","0px -2083px");
		$(".ico-second").css("background-position","0px -2232px");
		$(".ico-third").css("background-position","0px -2140px");
		$(".chat-list1").siblings().hide();
		$(".chat-list1").show();
	}); 
	//左侧主菜单的第二个按钮，文章，点击出现第二个菜单，公众号菜单	
	$(".wrap").on( "click",".ico-second",function(){
		$(".ico-first").css("background-position","0px -2048px");
	    $(".ico-second").css("background-position","0px -2267px");
		$(".ico-third").css("background-position","0px -2140px");
		$(".chat-list2").siblings().hide();
		$(".chat-list2").show();
	}); 
	//左侧主菜单的第三个按钮，人像，点击出现第三个菜单，我的好友菜单	
	$(".wrap").on( "click",".ico-third",function(){
		$(".ico-first").css("background-position","0px -2048px");
		$(".ico-second").css("background-position","0px -2232px");
	    $(".ico-third").css("background-position","0px -2175px");
		$(".chat-list3").siblings().hide();
		$(".chat-list3").show();
	}); 
	//模拟事件触发，默认显示第一个当前通话菜单，第一个页面
    $(".ico-first").click();
	$(".page1").show();
	//第一个页面是空白页面，空白人像图片
	//第二个页面是好友详细信息页面，有发送消息按钮
	//第三个页面是聊天窗口页面，接受好友信息，发送我给好友的信息。
	//第四个页面是好友详细信息页面，有发送消息按钮
		
	
	//点击第一个列表，出现page3,与好友的聊天窗口
	$(".wrap").on( "click",".chat-list1 .chat-info",function(){
		$(".page3").siblings().hide();
		$(".page3").find(".now-person").html($(this).find("img").clone());
		$(".page3").find(".others-img").html($(this).find("img").clone());
		$(".page3").find(".chat-friend").text($(this).find('.chat-name').text());
		$(".page3").show();
		$(this).parent().addClass("actived");
		$(this).parent().siblings().removeClass("actived");
		
	}); 
	//点第三个 人像，出现页面四
	$(".wrap").on( "click",".ico-third",function(){
		$(".page4").siblings().hide();
		$(".page4").show();
	});
	//点第一个气泡 ，出现页面一
	$(".wrap").on( "click",".ico-first",function(){
		$(".page1").siblings().hide();
		$(".page1").show();
	}); 
	
	//点第三个列表中的人，出现页面二，好友的详细信息。	
	$(".wrap").on( "click",".chat-list3 .chat-info",function(){
		$(".page2").siblings().hide();
		//根据列表中的好友头像，改变好友详细信息中的头像
		$(".page2").find(".friend-photo").html($(this).find("img").clone().css("height","110px"));
		//根据列表中好友的昵称，修改好友详细信息中的昵称
		var fri_now=$(this).find('.chat-name').text()
		$(".page2").find(".name-sex>.firend-name").html(fri_now);
		//为了取得好友的全部信息，先取出该好友的昵称字符串，再去查找属于data_all中的index。
		//console.log($(this).find('.chat-name').text());//取出该用户的名字，再去查找该用户的用户信息。
		for(var i=0;i<arr_length;i++){
			var friend_data=data_all[i];   //把Json数组中的每个Json对象取出来
			
			if(data_all[i].nick_name==fri_now){
				$(".page2").find(".name-sex>.firend-sex").html(data_all[i].phone_Num);
				$(".page2").find(".xian").html(data_all[i].nick_name);
			}
		}
		$(".page2").show();
		$(this).parent().addClass("actived");
		$(this).parent().siblings().removeClass("actived");
	}); 
		
	//点击好友的详细信息中的  “发消息”  按钮，弹出与好友的对话框。
	$(".wrap").on( "click",".chat-button",function(){   
		$(".page3").siblings().hide();
		//把自己的头像填好
		$(".page3").find(".now-person").html($(this).parent().find("img").clone().css("height","50px"));
		//把聊天好友的头像填好
		$(".page3").find(".others-img").html($(this).parent().find("img").clone().css("height","50px"));
		//把好友的昵称放在页面上面显示
		$(".page3").find(".chat-friend").text($(this).parent().find(".name-sex>.firend-name").text());
		$(".page3").show(); 
		//can_lis=true;
		//alert(can_lis);
	}); 
	
	
	//设置定时器，定时取数据。
	var clock=function(){
		var myfriend_list=$.ajax({ 
		    type: "POST", 	
			url: "user!getFriendList.action",
			dataType: "json",
			success: function(data){
				data_all=data;  
				console.log(data_all);
			},
			error: function(jqXHR){     
				alert("发生错误：" + jqXHR.status);  
			}, 
			async:false       
		});
	}; 
	window.setInterval(clock,1000);
	//点击与好友对话框中的下拉按钮，可以slide出好友的头像等信息。
	$(".wrap").on( "click",".pull-down",function(){
		$(this).toggleClass("pulled");
		$(".chat-person").slideToggle(200);  //改动！！！！！！
	}); 
	
	//与好友聊天框的   发送  按钮，绑定onclick事件。
	$(".wrap").on( "click",".send-btn",function(){
	  
		 
		//console.log(append_t);
		var text_content=$(this).parents(".write").find("textarea").val();  //取出文本框中的内容
		//console.log(text_content);
		if(text_content==""){	
		}else{
			$.ajax({ 
			    type: "POST", 	
				url: "要处理我传送过去的数据的地方",
				data: {
					message: text_content
				},
				dataType: "json" 
			});
			//把文本框中的内容装好，通话内容最后。
			append_t.removeClass("example").find(".content").text(text_content)
			$(".chat-body").append(append_t.clone());
			msg_end.scrollIntoView(); 
			//初始化文本框，使之内容清空，并且获取光标
			$(this).parents(".write").find("textarea").val("");
			$(this).parents(".write").find("textarea").focus();
		
		}
	});

	
	//添加好友操作	
	$('.option1').click(function(){
		$('.tanchu-mengban').fadeIn(100);
		$('.tanchu1').slideDown(200);
	});
	$('.add-button').click(function(){
		$(".add").hide();
		$(".add-button").hide();
		$(".added").show();
	});
	$('.close-button').click(function(){
		$('.tanchu-mengban').fadeOut(100);
		$('.tanchu').slideUp(200);
	});
	//退出操作
	$('.option3').click(function(){
		$('.tanchu-mengban').fadeIn(100);
		$('.tanchu2').slideDown(200);
	});
	$('.back-sure').click(function(){
	});
	$('.back-cancel').click(function(){
		$('.tanchu-mengban').fadeOut(100);
		$('.tanchu2').slideUp(200);
	});
	
	//自己的信息
	$('.self-details').click(function(){
		$(".my-details").find(".pic-top").empty();
		$(".my-details").find(".pic-top").html($(this).parent().find("img").clone());
		$(".my-details").find(".name-sex-middle").text($(this).parent().find(".self-name").text());
		$(".my-details").toggle();
	});
		
		
});
