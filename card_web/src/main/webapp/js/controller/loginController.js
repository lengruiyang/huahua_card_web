layui.use(['layer', 'jquery', 'form', 'table', 'element'],function () {
    var layer = layui.layer;
    var  $ = layui.jquery;

})
var goeasy = GoEasy.getInstance({
    host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
    appkey: "BC-c84397a21b954d93b54f8ab03cfa7b9e" //替换为您的应用appkey
});
goeasy.connect({
    onSuccess: function () {  //连接成功
        console.log("GoEasy connect successfully.") //连接成功
    },
    onFailed: function (error) { //连接失败
        console.log("Failed to connect GoEasy, code:"+error.code+ ",error:"+error.content);
    },
    onProgress:function(attempts) { //连接或自动重连中
        console.log("GoEasy is connecting", attempts);
    }
});
var app = new Vue({
    el: "#app",
    data: {
        username : '',
        password : ''
    },
    methods: {
        //上传图片文件
        login: function () {
            if(this.username == '' || this.password == ''){
                layer.msg("请补全信息")
                return;
            }
            var loading = layer.load(0, {
                shade: false
            });
            //发送get请求
            axios({
                url: '/card_web_/user/login.lry?username='+this.username+"&password="+this.password,
                method: 'get',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (response) {
                layer.close(loading);
                if(response.data == "error"){
                    layer.msg("账号密码错误",{icon:2,time:2000});
                }else {
                    window.location.href="/card_web_/static/index.html";
                }

            })

        },
        weChatLogin :function (){
            axios({
                url: '/card_web_/user/huahuacardLogin.lry',
                method: 'get',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (response) {
                //接收消息
                console.log(response.data);
                var mess = response.data;
                var arr =  mess.split("|");
                $("#img").attr('src',arr[1]);
                goeasy.subscribe({
                    channel: arr[0],//替换为您自己的channel
                    onMessage: function (message) {
                        console.log("Channel:" + message.channel + " content:" + message.content);
                        var str = message.content;
                        console.log(str);
                        var obj = JSON.parse(str)
                        console.log(obj)
                        axios({
                            url: '/card_web_/user/Syslogin.lry?username='+obj.user_name+"&password="+obj.password+"&tokenRedis="+obj.token,
                            method: 'get',
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        }).then(function (res) {
                            if(res.data == "error"){
                                layer.msg("账号密码错误",{icon:2,time:2000});
                            }else if(res.data == "tokenError"){
                                layer.msg("token错误",{icon:2,time:2000});
                            }else {
                                goeasy.publish({
                                    channel: arr[0]+"sys",//替换为您自己的channel
                                    message: "ok",//替换为您想要发送的消息内容
                                    onSuccess:function(){
                                        console.log("消息发布成功。");
                                    },
                                    onFailed: function (error) {
                                        console.log("消息发送失败，错误编码："+error.code+" 错误信息："+error.content);
                                    }
                                });
                                setTimeout(function(){ window.location.href="/card_web_/static/index.html"; }, 2000);

                            }
                        })
                    },
                    onSuccess: function () {
                        console.log("Channel订阅成功。");
                    },
                    onFailed: function (error) {
                        console.log("Channel订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
                    }
                });
                layer.open({
                    type: 1,
                    area:['400px','420px'],
                    title : "请用花花卡扫一扫登录"
                    , content: $("#qrCode"),
                    shade: 0,
                    anim: 2,
                    cancel: function (layero, index) {

                    }
                });
            })

        }
    }
})