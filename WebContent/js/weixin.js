
$( window ).on( "load", function(){
	var chat_friend=$("#model").clone();

		$.ajax({ 
		    type: "POST", 	
			url: "user!getFriendList.action",
			dataType: "json",
			success: function(data){
				
				var data_recieve=data;
				//alert(data_recieve[0].nick_name); 
				var arr_length=data.length;
				//alert(arr_length);
				for(var i=0;i<arr_length;i++){
					var friend_data=data[i];   //把Json数组中的每个Json对象取出来
					//alert(friend_data.nick_name);
					chat_friend.find(".chat-name").text(friend_data.nick_name)
					$(".chat-list3").append(chat_friend.clone().show());
					//$(".chat-list3>chat").append("<div>123</div>");
					//console.log($(".chat-list3 .chat"));
				}
				
				return data_recieve;
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});

		/*var aArray =new Array();//定义一个数组
		console.log(aArray.length);
		aArray[0] = "张三";
		aArray[1] = "男";
		aArray[2] = "123456@qq.com";//把值一个个添加到数组中。
		var arrayValue = aArray[0];//取出其中一个值
		console.log("");
		
		var json1={"name":"zhang","sex":"man"}
		console.log(json1.name);*/
	
	var append_t=$(".example").clone();
	var	prepend_t=$(".chat-list1 chat").first().clone() ;		
		$(".wrap").on( "click",".send-btn",function(){
		  
			 
			console.log(append_t);
			var text_content=$(this).parents(".write").find("textarea").val();
			console.log(text_content);
			if(text_content==""){	
			}else{
				
				append_t.removeClass("example").find(".content").text(text_content)
				$(".chat-body").append(append_t.clone());
				msg_end.scrollIntoView(); 
				$(this).parents(".write").find("textarea").val("");
				$(this).parents(".write").find("textarea").focus();
			
			}
		});
		
		$(".wrap").on( "click",".more",function(){
		    $(".more-list").toggle();
		});
		
		$(".wrap").on( "click",".ico-first",function(){
		    $(".ico-first").css("background-position","0px -2083px");
			$(".ico-second").css("background-position","0px -2232px");
			$(".ico-third").css("background-position","0px -2140px");
			$(".chat-list1").siblings().hide();
			$(".chat-list1").show();
		}); 
		$(".wrap").on( "click",".ico-second",function(){
			$(".ico-first").css("background-position","0px -2048px");
		    $(".ico-second").css("background-position","0px -2267px");
			$(".ico-third").css("background-position","0px -2140px");
			$(".chat-list2").siblings().hide();
			$(".chat-list2").show();
		}); 
		$(".wrap").on( "click",".ico-third",function(){
			$(".ico-first").css("background-position","0px -2048px");
			$(".ico-second").css("background-position","0px -2232px");
		    $(".ico-third").css("background-position","0px -2175px");
			$(".chat-list3").siblings().hide();
			$(".chat-list3").show();
		}); 
        $(".ico-first").click();
		$(".page1").show();
		
		$(".wrap").on( "click",".chat-list1 .chat-info",function(){
			$(".page3").siblings().hide();
			$(".page3").find(".now-person").html($(this).find("img").clone());
			$(".page3").find(".others-img").html($(this).find("img").clone());
			$(".page3").find(".chat-friend").text($(this).find('.chat-name').text());
			$(".page3").show();
			$(this).parent().addClass("actived");
			$(this).parent().siblings().removeClass("actived");
			
		}); 
		$(".wrap").on( "click",".ico-third",function(){
			$(".page4").siblings().hide();
			$(".page4").show();
		}); 
		$(".wrap").on( "click",".chat-list3 .chat-info",function(){
			$(".page2").siblings().hide();
			$(".page2").find(".friend-photo").html($(this).find("img").clone().css("height","110px"));
			$(".page2").find(".name-sex>.firend-name").html($(this).find('.chat-name').text());
			console.log($(this).find('.chat-name').text());//取出该用户的名字，再去查找该用户的用户信息。
			$(".page2").show();
			$(this).parent().addClass("actived");
			$(this).parent().siblings().removeClass("actived");
		}); 
		
		$(".wrap").on( "click",".chat-button",function(){   //点击发送消息按钮
			$(".page3").siblings().hide();
			$(".page3").find(".now-person").html($(this).parent().find("img").clone().css("height","50px"));
			$(".page3").find(".others-img").html($(this).parent().find("img").clone().css("height","50px"));
			$(".page3").find(".chat-friend").text($(this).parent().find(".name-sex>.firend-name").text());
			$(".page3").show(); 
			// var first_chat=$(this).parent().find(".name-sex>.firend-name").text();
			//$(".chat-list1 .chat-info").each(function(){
			//	if($(this).find(".chat-name").html()==first_chat){
			//		$(".chat-list1").prepend($(this).parent().addClass("actived"));
			//	}
			//});  
			//$(".chat-list1").siblings().hide();
			//$(".chat-list1").show(); 
		}); 
		
		$(".wrap").on( "click",".pull-down",function(){
			$(this).toggleClass("pulled");
			$(".chat-person").slideToggle(200);  //改动！！！！！！
		}); 
	//添加好有	
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
