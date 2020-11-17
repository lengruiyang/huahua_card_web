layui.use('layer',function () {

})
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

        }
    }
})