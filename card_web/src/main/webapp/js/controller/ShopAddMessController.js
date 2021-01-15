layui.use(['layer', 'jquery', 'form', 'table', 'element'],function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;

    /**
     * 获取table数据
     */
    table.render({
        elem: '#shopAddMess'
        ,height: 570
        ,url: '/card_web_/shop/find_Sh_shop_sys.lry' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80, fixed: 'left'}
            ,{field: 'name', title: '商铺名', width:220}
            ,{title: '头像', width:120, templet: '#shopHead'}
            ,{field: 'id_card_img_url',title: '身份证号', width:220}
            ,{field: 'user_name',title: '真实姓名', width:120}
            ,{field: 'shop_mess',title: '店铺介绍', width:320}
            ,{field: 'sell_goods',title: '主营商品', width:120}
            ,{field: 'user_open_id',title: '申请人OPENID', width:320}
            ,{title: '是否线下', width:120,templet: '#isNo'}
            ,{title: '营业执照', width:120,templet: '#yinyezhizhao'}
            ,{field: 'add_time',title: '申请时间', width:220}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}

        ]]
    });
})