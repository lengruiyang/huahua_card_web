layui.use(['table','jquery','element','form','layer','upload'],function () {
    var table = layui.table;
    var upload = layui.upload;
    var form = layui.form;
    var $ = layui.jquery;
    var element = layui.element;
    $("#addDHS").hide();
    $("#imgUP").hide();
    $("#BJDH").hide();
    $("#imgUP2").hide();
    $("#LunBoBJ").hide();
    $("#imgUP3").hide();
    $("#LunBoAdd").hide();
    $("#imgUP4").hide();
    $("#goodsRadio").hide();
    $("#goodsRadio2").hide();
    $("#goodsRadio3").hide();
    $("#goodsRadio4").hide();
    $("#sy1").hide();
    $("#sy2").hide();
    form.on('switch',function (switchs) {
        var id = $(switchs.elem).data('id');
        // var status = $(switchs.elem).data('status');
        if(this.checked){
            $.ajax({
                type: "GET",
                url : '/card_web_/index/update_dao_hang.lry',
                data:{
                    id : id
                },
                success(res){
                    console.log(res);
                }
            })
            layer.msg("已启动")
        }else {
            $.ajax({
                type: "GET",
                url : '/card_web_/index/update_dao_hang.lry',
                data:{
                    id : id
                },
                success(res){
                    console.log(res);
                }
            })
            layer.msg("已禁用")
        }
    })
    //第一个实例
    table.render({
        elem: '#demo'
        ,height: 700
        ,url: '/card_web_/index/sysDH.lry' //数据接口
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80,  fixed: 'left'}
            ,{field: 'name', title: '导航名', width:100}
            ,{ title: '图片', width:90,templet:'#ImgDH'}
            ,{ title: '去向', width:200,templet:'#qx'}
            , { title: '状态', width: 100,templet:'#status'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}

        ]]
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            layer.confirm('确认要永久删除吗？', function(index) {
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/index/delete_dao_hang.lry",
                    data : {
                        id : data.id
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("删除成功",{icon:1,time:2000});
                        obj.del();
                    },
                });
            })
        }else if(layEvent === 'detail'){
            $("#inputT").val(data.name);
            $("#btnId").val(data.id);
            $("#imgUpload2").attr("src", data.img);
            $("#imgUP2").show();
            layer.open({
                type: 1,
                area:['350px','350px'],
                title: '编辑导航栏'
                , content: $("#BJDH"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                    console.log("关闭");
                    $("#BJDH").hide();
                    $("#imgUP2").hide();
                }
            });
        }
    });

    form.on('radio(radioCh)', function(data){
        $("#radioKey").val(data.value);
        console.log(data.value); //被点击的radio的value值
        if(data.value == "0"){
            $("#goodsRadio").show();
            $("#goodsRadio2").hide();
        }else {
            $("#goodsRadio").hide();
            $("#goodsRadio2").show();
        }
    });

    form.on('radio(radioCh1)', function(data){
        $("#radioKey2").val(data.value);
        console.log(data.value); //被点击的radio的value值
        if(data.value == "0"){
            $("#goodsRadio4").show();
            $("#goodsRadio3").hide();
        }else {
            $("#goodsRadio4").hide();
            $("#goodsRadio3").show();
        }
    });

    form.on('radio(radioCh2)', function(data){
        $("#radioKey3").val(data.value);
        console.log(data.value); //被点击的radio的value值
        if(data.value == "0"){
            $("#sy1").hide();
        }else {
            $("#sy1").show();
        }
    });

    form.on('radio(radioCh3)', function(data){
        $("#radioKey4").val(data.value);
        console.log(data.value); //被点击的radio的value值
        if(data.value == "0"){
            $("#sy2").hide();
        }else {
            $("#sy2").show();
        }
    });

    table.render({
        elem: '#demoImg'
        ,height: 700
        ,url: '/card_web_/img/lun_boSys.lry' //数据接口
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80,  fixed: 'left'}
            ,{ title: '图片', width:180,templet:'#ImgLunBo'}
            ,{ title: '指向', width:380,templet:'#goto'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo2'}
        ]]
    });

    //监听行工具事件
    table.on('tool(testImg)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            layer.confirm('确认要永久删除吗？', function(index) {
                $.ajax({
                    type : "GET",
                    url : "/card_web_/img/delete_lun_boSys.lry",
                    data : {
                        id : data.id
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.msg("删除成功",{icon:1,time:2000});
                        obj.del();
                    },
                });
            })
        }else if(layEvent === 'detail'){
            if(data.to_url == null){

            }
            $("#imgUP3").show();
            $("#gxId").val(data.id);
            $("#imgUpload3").attr("src", data.url);
            layer.open({
                type: 1,
                area:['550px','550px'],
                title: '编辑轮播图'
                , content: $("#LunBoBJ"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                    console.log("关闭");
                    $("#LunBoADD").hide();
                    $("#imgUP3").hide();
                }
            });
        }
    });

    var loading4 = null;
    var uploadInst = upload.render({
        elem: '#test4' //绑定元素
        ,url: '/card_web_/upload/imgsys.lry',
        data:{
            status : function () {
                return $("#radioKey4").val();
            },
            sy : function () {
                return $("#inputsy2").val();
            }
        }
        ,before : function (res) {
            loading4 = layer.load(0, {
                shade: false
            });
        }
        ,done: function(res){
            layer.close(loading4);
            console.log(res);
            $("#imgUP4").show();
            $('#imgUpload4').attr("src", res.url);
        }
        ,error: function(res){
            console.log(res);
            //请求异常回调
        }
    });

    var loading3 = null;
    var uploadInst = upload.render({
        elem: '#test3' //绑定元素
        ,url: '/card_web_/upload/img.lry'
        ,before : function (res) {
            loading3 = layer.load(0, {
                shade: false
            });
        }
        ,done: function(res){
            layer.close(loading3);
            console.log(res);
            $("#imgUP3").show();
            $('#imgUpload3').attr("src", res.url);
        }
        ,error: function(res){
            layer.close(loading3);
            console.log(res);
            //请求异常回调
        }
    });

    var loading2 = null;
    var uploadInst = upload.render({
        elem: '#test2' //绑定元素
        ,url: '/card_web_/upload/img.lry'
        ,before : function (res) {
            loading2 = layer.load(0, {
                shade: false
            });
        }
        ,done: function(res){
            layer.close(loading2);
            console.log(res);
            $("#imgUP2").show();
            $('#imgUpload2').attr("src", res.url);
        }
        ,error: function(res){
            console.log(res);
            //请求异常回调
        }
    });

    //执行实例
    var loading = null;
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        ,url: '/card_web_/upload/imgsys.lry',
        data:{
            status : function () {
                return $("#radioKey3").val();
            },
            sy : function () {
                return $("#inputsy").val();
            }
        }
        ,before : function (res) {
            loading = layer.load(0, {
                shade: false
            });
        }
        ,done: function(res){
            layer.close(loading);
            console.log(res);
            $("#imgUP").show();
            $('#imgUpload').attr("src", res.url);
        }
        ,error: function(res){
            console.log(res);
            //请求异常回调
        }
    });

})

var app = new Vue({
    el:"#app",
    data:{
        name : '',
        to_url : ''
    },
    methods:{
        updateLB(){
            var img = $("#imgUpload3")[0].src;
            var radioKey = $("#radioKey2").val();
            var id = $("#gxId").val();
            var  mess = "";
            if(radioKey == "0"){
                mess = $("#inputG2").val();
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/img/update_lun_boSys.lry",
                    data : {
                        id : id,
                        imgurl : img,
                        to_url : '',
                        goods_id : mess
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("更改成功");
                        setTimeout(function () {
                            location.replace(location.href);
                        },1000);
                    },
                });
            }else {
                mess = $("#job4").val();
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/img/update_lun_boSys.lry",
                    data : {
                        id : id,
                        imgurl : img,
                        to_url : mess,
                        goods_id : ''
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("更改成功");
                        setTimeout(function () {
                            location.replace(location.href);
                        },1000);
                    },
                });
            }
        },
        saveLB(){
            var img = $("#imgUpload4")[0].src;
            var radioKey = $("#radioKey").val();
            var  mess = "";
            if(radioKey == "0"){
                mess = $("#inputG").val();
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/img/save_lun_boSys.lry",
                    data : {
                        url : img,
                        to_url : '',
                        goods_id : mess
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("添加成功");
                        setTimeout(function () {
                            location.replace(location.href);
                        },1000);
                    },
                });
            }else {
                mess = $("#job3").val();
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/img/save_lun_boSys.lry",
                    data : {
                        url : img,
                        to_url : mess,
                        goods_id : ''
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("添加成功");
                        setTimeout(function () {
                            location.replace(location.href);
                        },1000);
                    },
                });
            }

        },
        genggai(){
            var name = $("#inputT").val();
            var img = $("#imgUpload2")[0].src;
            var doMess = $("#job2").val();
            var id = $("#btnId").val();
            axios({
                url: '/card_web_/index/update_dao_hang_mess.lry?name='+name+"&url="+img+"&to_url="+doMess+"&id="+id,
                method: 'get',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (res) {
                    layer.msg("更改成功");
                    setTimeout(function () {
                        location.replace(location.href);
                    },1000);

            })
        },
        addLB(){
            layer.open({
                type: 1,
                area:['350px','350px'],
                title: '添加轮播图'
                , content: $("#LunBoAdd"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                    console.log("关闭");
                    $("#LunBoAdd").hide();
                    $("#imgUP4").hide();
                }
            });
        },
        addDH(){
            layer.open({
                type: 1,
                area:['350px','350px'],
                title: '添加导航栏'
                , content: $("#addDHS"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                    console.log("关闭");
                    $("#addDHS").hide();
                    $("#imgUP").hide();
                }
            });
        },
        save(){
            console.log();
            var url = $("#imgUpload")[0].src;
            if($("#imgUpload")[0].src == ''){
                layer.msg("请上传图片");
                return;
            }
            if(this.name == ""){
                layer.msg("请输入导航栏名");
                return;
            }
            var loading = layer.load(0, {
                shade: false
            });
            var itemValue = $("#job").val();
            console.log('Value:' + itemValue);
            axios({
                url: '/card_web_/index/save_dao_hang.lry?name='+this.name+"&img="+url+"&to_url="+itemValue,
                method: 'get',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (res) {
                layer.close(loading);
                if(res.data.success){
                    layer.msg("保存成功");
                    setTimeout(function () {
                        location.replace(location.href);
                    },1000);
                }
            })
        },
        created(){

        }
    }
})
