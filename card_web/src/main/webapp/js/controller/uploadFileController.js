var app = new Vue({
    el: "#app",
    data: {
        mess: {}
    },
    methods: {
        //上传图片文件
        uploadFile: function () {
            // 声明一个FormData对象
            var formData = new FormData();
            // file 这个名字要和后台获取文件的名字一样; querySelector获取选择器对应的第一个元素
            formData.append('file', document.querySelector('input[type=file]').files[0]);
            //post提交
            axios({
                url: '/card_web/upFile/img.lry',
                data: formData,
                method: 'post',
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (response) {
                //结果
                app.mess = response.data;
            })

        }
    }
})