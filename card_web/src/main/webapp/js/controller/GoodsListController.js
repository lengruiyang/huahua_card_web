layui.use(['layer', 'jquery', 'form', 'table', 'element'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    var form = layui.form;

    //第一个实例
    table.render({
        elem: '#GoodsMessList'
        , height: 600
        , url: '/card_web_/goods/findAllSys.lry' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {  title: '订单主键', width: 100 ,templet: '#IDKey'},
            {  title: '商品主图', width: 100 ,templet: '#goodsImg'},
            {  title: '商品名称', width: 700 ,templet: '#goodsName'},
            {  title: '小名', width: 400 ,templet: '#goodsSmallName'},
            {  title: '标签', width: 100 ,templet: '#tip'},
            {  title: '状态', width: 100,templet:'#status'}

            // {fixed: 'right', width: 250, align: 'center', toolbar: '#barDemo'}
        ]]
    });
})