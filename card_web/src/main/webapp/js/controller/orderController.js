
layui.use(['layer', 'jquery', 'form', 'table', 'element'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    var form = layui.form;
    //监听导航点击
    element.on('nav(demo)', function (elem) {
        layer.msg(elem.text());
    });
    //第一个实例
    table.render({
        elem: '#OrderMessList'
        , height: 600
        , url: '/card_web_/order/getOrderSys.lry' //数据接口
        , page: true //开启分页
        , toolbar: true
        , cols: [[ //表头
            {  title: '订单主键', width: 100 ,templet: '#IDKey'},
            {  title: '订单用户', width: 200 ,templet: '#UserImg'},
            {  title: '店铺头像', width: 200 ,templet: '#shopname'},
            {  title: '支付状态', width: 100 ,templet: '#payStatus'},
            {  title: '订单类型', width: 100 ,templet: '#orderLX'},
            {  title: '创建时间', width: 180 ,templet: '#createTime'},
            {  title: '总金额', width: 100 ,templet: '#price'},
            {  title: '配送费', width: 100 ,templet: '#kdMess'},
            {  title: '优惠金额', width: 100 ,templet: '#YH'},
            {  title: '支付时间', width: 180 ,templet: '#payTime'},
            {  title: '支付方式', width: 100 ,templet: '#payWay'},
            {  title: '订单ID', width: 350 ,templet: '#orderNum'},
            {  title: '发货时间', width: 180 ,templet: '#KDT'},
            {  title: '快递单号', width: 180 ,templet: '#KD'},
            {  title: '商户订单号', width: 350 ,templet: '#PayNum'},
            {fixed: 'right', width: 250, align: 'center', toolbar: '#barDemo'}
        ]]
    });


    //监听行工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'fahuo') {
            $("#order_num").val(data.orderSys.order_num);
            $("#addressUser").html("收货人："+data.addressSys.user_name);
            $("#addressPhone").html("联系方式："+data.addressSys.user_phone);
            $("#address").html("发货地址："+data.addressSys.user_address);
            if(data.orderSys.goods_notic == null){
                $("#bz").html("订单备注：无备注");
            }else {
                $("#bz").html("订单备注："+data.orderSys.goods_notic);
            }
            layer.open({
                type: 1,
                area:['400px','250px'],
                title: '订单发货'
                , content: $("#fahuo"),
                shade: 0,
                anim: 2,
                cancel: function (layero, index) {
                    layer.closeAll();
                }
            });
        } else if (layEvent === 'detail') {
            $("#addressU").html("收货人："+data.addressSys.user_name);
            $("#addressP").html("联系方式："+data.addressSys.user_phone);
            $("#addres").html("发货地址："+data.addressSys.user_address);

            if(data.orderSys.goods_notic == null){
                $("#bzc").html("订单备注：无备注");
            }else {
                $("#bzc").html("订单备注："+data.orderSys.goods_notic);
            }

            console.log(data);
            var orderGoodsHtml = ""
            $.each(data.returnGoods, function (index, val) {
                orderGoodsHtml = " <div  class=\"layui-card\"> " +
                    "<div class=\"layui-card-header\">商品"+(index+1)+"</div>" +
                    "<div class=\"layui-card-body\">"+
                       "<div style='margin-left: 10px;margin-top: 10px;display: flex;justify-content: space-between'>" +
                    "<img style='width: 100px;height: 100px' src="+val.img+">"+
                    "<div>" +
                       val.name +"<br>"+
                    "<div style='color: red;margin-top: 10px'>"
                      +"规格："+val.size+";"+val.color+"<br>"+"数量："+val.num+"件"
                    "</div>"+
                    "</div>"+
                    "</div>"+
                    "</div>"+
                    "</div>"
                $(".OrderGoodsList").append(orderGoodsHtml);
            })
            layer.open({
                type: 1,
                area:['600px','600px'],
                title: '订单商品详情'
                , content: $("#goodsList"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                    $(".OrderGoodsList").empty();
                    layer.closeAll();
                }
            });
        }else if(layEvent == "quxiao"){
            console.log(data);
            layer.confirm('确认要退款关闭订单吗？', function(index) {
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/order/tui_kuan_sys.lry",
                    data : {
                        order_num : data.orderSys.order_num
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("退款成功",{icon:1,time:2000});

                        setTimeout(function () {
                            location.replace(location.href);
                        },300);
                    },
                });
            })
        }else if(layEvent == "del"){
            console.log(data);
            layer.confirm('确认要永久删除订单吗？', function(index) {
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/order/deleteByOrderNum.lry",
                    data : {
                        order_num : data.orderSys.order_num
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("删除成功",{icon:1,time:2000});

                        setTimeout(function () {
                            location.replace(location.href);
                        },300);
                    },
                });
            })
        }else if(layEvent == "pay"){
            console.log(data);
            layer.confirm('确认要支付该订单吗？', function(index) {
                var loading = layer.load(0, {
                    shade: false
                });
                $.ajax({
                    type : "GET",
                    url : "/card_web_/order/sysPay.lry",
                    data : {
                        out_trade_no : data.orderSys.order_num
                    },
                    success : function(result) {
                        layer.closeAll();
                        layer.close(loading);
                        layer.msg("支付成功",{icon:1,time:2000});

                        setTimeout(function () {
                            location.replace(location.href);
                        },300);
                    },
                });
            })
        }else if(layEvent == "pj"){
            console.log(data);
            $("#pjimg").attr('src',data.pingJiaSys.img_one);
            $("#pjMess").html("评价详情："+data.pingJiaSys.pingjia);
            layer.open({
                type: 1,
                area:['500px','500px'],
                title: '用户评价详情'
                , content: $("#pjList"),
                shade: 0,
                anim: 1,
                cancel: function (layero, index) {
                }
            });
        }
    });

});

var app = new Vue({
    el: "#app",
    data: {
        fahuo_num : ''
    },
    methods: {
        fahuoNow(){
            if(this.fahuo_num == ''){
                layer.msg("请填写快递单号");
                return;
            }
            var order_num = $("#order_num").val();
            var loading = layer.load(0, {
                shade: false
            });
            //发送get请求
            axios({
                url: '/card_web_/order/faHuo.lry?order_num='+order_num+"&fa_huo_num="+this.fahuo_num,
                method: 'get',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (res) {
                layer.close(loading);
                if(res.data.success){
                    layer.msg("发货成功");
                    setTimeout(function () {
                        location.replace(location.href);
                    },300);
                }
            })
        }
    },
    created(){

    }
})
$(function(){
    var clipboard = new ClipboardJS(".copy");
    clipboard.on("success", function(e) {
        layer.msg("复制成功",{icon:1,time:1000});
    });
    clipboard.on("error", function(e) {
        layer.msg("复制失败",{icon:1,time:1000});
    });
})
