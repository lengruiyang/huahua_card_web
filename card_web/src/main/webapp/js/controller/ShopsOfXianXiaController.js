layui.use(['layer', 'jquery', 'form', 'table', 'element'],function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;

    /**
     * 获取table数据
     */
    table.render({
        elem: '#shopList'
        ,height: 570
        ,url: '/card_web_/shop/find_xianxia_shop_sys.lry' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80, fixed: 'left'}
            ,{field: 'shop_name', title: '商铺名', width:120}
            ,{title: '头像', width:120, templet: '#shopHead'}
            , { title: '状态', width: 100,templet:'#status'}
            , {field: 'bindtap',  title: '点击量', width: 100}
            , {field: 'fire_num',  title: '热度', width: 100}
            , {field: 'fans',  title: '关注度', width: 100}
            , {title: '是否线下', width: 180,templet:'#trueOrFalse'}
            , {title: '最高权限ID(用户OpenID)', width: 280,templet:'#userOpenId'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}
        ]]
    });
})

function bindSearch(){
    layer.msg("搜索");
}
function AddShop(){
    layer.msg("添加");
}
//复制关键代码
$(function(){
    var clipboard = new ClipboardJS(".copy");
    clipboard.on("success", function(e) {
        layer.msg("复制成功",{icon:1,time:1000});
    });
    clipboard.on("error", function(e) {
        layer.msg("复制失败",{icon:1,time:1000});
    });
})