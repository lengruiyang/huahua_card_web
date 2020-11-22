layui.use(['table','jquery','element','form','layer','upload'],function () {
    // 基于准备好的dom，初始化echarts实例
    $.ajax({
        type : "GET",
        url : "/card_web_/user/findSysBin.lry",
        success : function(result) {
            var myChart = echarts.init(document.getElementById('main'));
            option = {
                title: {
                    text: '用户一览表',
                    subtext: '用户分类',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['女性','男性']
                },
                series: [
                    {
                        name: '性别:',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: result,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
    });

    // 基于准备好的dom，初始化echarts实例
    $.ajax({
        type : "GET",
        url : "/card_web_/order/getOrderMessSys.lry",
        success : function(result) {
            var myChart2 = echarts.init(document.getElementById('main2'));
            option2 = {
                xAxis: {
                    type: 'category',
                    data: result.timeList
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: result.numList,
                    type: 'line'
                }]
            };
            myChart2.setOption(option2);
        },
    });

})