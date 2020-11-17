layui.use(['layer', 'jquery', 'form', 'table', 'element'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    var form = layui.form;
    //监听导航点击
    element.on('nav(demo)', function (elem) {
        layer.msg(elem.text());
    });

    form.on('switch',function (switchs) {
        var id = $(switchs.elem).data('id');
        var status = $(switchs.elem).data('status');
        if(this.checked){
            layer.msg("已启动用户")
        }else {
            layer.msg("已禁用用户")
        }
    })
    //第一个实例
    table.render({
        elem: '#userMessData'
        , height: 500
        , url: '/card_web_/user/findAll.lry' //数据接口
        , page: true //开启分页
        , toolbar: 'default'
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 50, fixed: 'left'}
            , {
                field: 'head_img',
                title: '头像',
                width: 130,
                align: 'center',
                templet: '#userHead'
            }
            , {field: 'nick_name', title: '昵称', width: 180}
            , {field: 'open_id', title: 'OPENID', width: 300,templet:'#userOpenId'}
            , {field: 'sex', title: '性别', width: 80, templet: '<div>{{d.sex == 1 ? "男" : "女" }}</div>'}
            , {field: 'phone_number', title: '手机号', width: 180}
            , {field: 'money', title: '余额', width: 80}
            , {field: 'jifen', title: '积分', width: 80}
            , {field: 'status', title: '状态', width: 100,templet:'#status'}
            , {field: 'create_time', title: '注册时间', width: 180}
            , {field: 'is_vip', title: '是否VIP', width: 80, templet: '<div>{{d.is_vip == 0 ? "否" : "是" }}</div>'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}
        ]]

    });

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        console.log(data);
        switch (obj.event) {
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：' + checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        }
        ;
    });

});
$(function(){
    var clipboard = new ClipboardJS(".copy");
    clipboard.on("success", function(e) {
        layer.msg("复制成功",{icon:1,time:1000});
    });
    clipboard.on("error", function(e) {
        layer.msg("复制失败",{icon:1,time:1000});
    });
})
