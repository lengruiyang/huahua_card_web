layui.use(['layer', 'jquery', 'form', 'table', 'element'],function () {

})
var app = new Vue({
    el:"#app",
    data:{
        sKey:'',
        sNum:''
    },
    methods:{
        sKeyMess(){
            if(this.sKey == '' || this.sNum == ''){
                layer.msg("请完善填写内容")
                return;
            }
            var loading = layer.load(0, {
                shade: false
            });
            axios({
                url:"/card_web_/rabbit/getGoods.lry?input="+this.sKey+"&num="+this.sNum,
                method: "GET",
            }).then(function (res) {
                layer.close(loading);
                layer.msg("添加成功",{icon:1,time:2000});
            })
        }
    }

})