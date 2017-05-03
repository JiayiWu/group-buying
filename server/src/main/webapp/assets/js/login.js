/**
 * Created by StevenWu on 17/4/14.
 */

$(function(){
   $("#login").click(function(){
       jQuery.ajax({
           url: '/ad/login',
           type: 'post',
           dataType: 'json',
           data:{
               username:$('#username').val(),
               password:$('#password').val()
           },
           success: function (data) {
               if (data.result == true){
                   top.location = '../main.html'
               }else
                   swal("OMG", "账号密码错误", "error");

           },
           error:function(data){
               swal("OMG", "服务器错误,请稍后重试!", "error");
           }
       });
   });
});