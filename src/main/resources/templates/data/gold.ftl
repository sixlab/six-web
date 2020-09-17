<#-- @ftlvariable name="dataList" type="java.util.List<cn.sixlab.six.web.models.RankItem>" -->
<#-- @ftlvariable name="group" type="cn.sixlab.six.web.models.RankGroup" -->
<@FrameBody title="黄金价格趋势" keywords="金价,黄金价格,趋势,黄金价格趋势" description="黄金价格趋势图表。">
    <script src="/static/plugins/echarts/echarts.min.js"></script>
    <div id="charts" style="width: 600px;height:400px;">

    </div>

    <button data-type="11" class="btn btn-outline-danger my-2 my-sm-0 change">小时</button>
    <button data-type="5" class="btn btn-outline-danger my-2 my-sm-0 change">日</button>
    <button data-type="2" class="btn btn-outline-danger my-2 my-sm-0 change">月</button>

    <script>
        $(function () {
            var myChart = echarts.init(document.getElementById('charts'));

            $(".change").click(function () {
                var type = $(this).data("type");
                var title = $(this).text();

                requestData(type, title);
            });

            function requestData(type, title) {
                myChart.showLoading();
                var times = 10;
                $.ajax({
                    url:"https://datastrend.com/data/gold/data/" + type,
                    type:"post",
                    dataType:"json",
                    data:{
                        times:times
                    },
                    success:function (data) {
                        myChart.hideLoading();

                        if (data.success) {
                            // 填入数据
                            var dataOption = {
                                title: {
                                    text: title + '金价图',
                                    subtext: data.data.date[0] + " ~ " + data.data.date[times-1],
                                },
                                tooltip: {
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    },
                                    formatter: function (params) {
                                        var dataIndex = params[0].dataIndex;
                                        return params[0].name
                                            + '<br/>差值 : ' + params[1].value
                                            + '<br/>' + params[1].seriesName + ' : ' + data.data.max[dataIndex]
                                            + '<br/>' + params[0].seriesName + ' : ' + params[0].value;
                                    }
                                },
                                grid: {
                                    left: '3%',
                                    right: '4%',
                                    bottom: '3%',
                                    containLabel: true
                                },
                                xAxis: {
                                    type: 'category',
                                    splitLine: {show: false},
                                    data: data.data.date
                                },
                                yAxis: {
                                    type: 'value',
                                    scale:true,
                                },
                                series: [
                                    {
                                        name: '最低',
                                        type: 'bar',
                                        stack: '总量',
                                        itemStyle: {
                                            normal: {
                                                barBorderColor: 'rgba(0,0,0,0)',
                                                color: 'rgba(0,0,0,0)'
                                            },
                                            emphasis: {
                                                barBorderColor: 'rgba(0,0,0,0)',
                                                color: 'rgba(0,0,0,0)'
                                            }
                                        },
                                        data: data.data.min
                                    },
                                    {
                                        name: '最高',
                                        type: 'bar',
                                        stack: '总量',
                                        label: {
                                            normal: {
                                                show: true,
                                                position: 'top',
                                                formatter: function(params) {
                                                    return data.data.max[params.dataIndex];
                                                }
                                            }
                                        },
                                        data: function () {
                                            var list = [];
                                            var min = data.data.min;
                                            var max = data.data.max;

                                            for (let i = 0; i < min.length; i++) {
                                                var item = (max[i]*10000-min[i]*10000)/10000;

                                                list.push(item);
                                            }

                                            return list;
                                        }()
                                    }
                                ]
                            };
                            myChart.setOption(dataOption);
                        }
                    },
                    error:function (data) {
                        myChart.hideLoading();
                    }
                });
            }

            requestData(11, "小时");
        });
    </script>
</@FrameBody>