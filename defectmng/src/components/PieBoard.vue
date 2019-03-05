<!--  -->
<template>
  <div v-bind:style="pieStyle">
    <Modal v-model="showDefectDetail" title="缺陷明细列表" :draggable="true" ok-text="关闭" cancel-text="" width="95">
      <DefectDetail ref="defectDetail"></DefectDetail>
    </Modal>
  </div>
</template>

<script>
import DefectDetail from "./DefectDetail";
export default {
  data() {
    return {
      pieStyle: {
        width: "100%",
        height: "350px"
      },
      showDefectDetail:false,
      defectDetailData:{},
    };
  },
  components:{
    DefectDetail
  },
  methods:{
    loadPie:function(pieParameter,proName,proValue,status,versionIds){
      var pieOptions = {
        title: {
          text: "",
          left: 'left',
          top: 20,
        },
        series: [
          {
            type: "pie",
            radius: "60%",
            label: {
              //饼图图形上的文本标签
              normal: {
                show: true,
              // position: "inner", //标签的位置
                textStyle: {
                  fontWeight: 100,
                  fontSize: 12 //文字的字体大小
                },
                formatter:function(val){   //让series 中的文字进行换行
                      return val.name+"\n"+"数量:"+val.value;
                    }
              }
            },
            clockwise:false,
            // color: [ "#c23531", "#9c1515","#c71919"],
            data: [],
            labelLine: {
                  normal: {
                      lineStyle: {
                          color: 'darkgrey'
                      },
                      smooth: 0.2,
                      length: 10,
                      length2: 20,
                  }
              },
            itemStyle: {
                normal: {
                    // color: '#c23531',
                    shadowBlur: 20,
                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                }
            },
            tooltip:{
              formatter: '数量:{c}<br />百分比:{d}'
            }
          }
        ]
      };
      pieOptions.title.text = pieParameter.pieName;
      if(null == pieParameter.data || pieParameter.data.length == 0){
        pieParameter.data = [{name:"无数据",value:0}]
      }
      pieOptions.series[0].data = pieParameter.data;
      let pie = this.$echarts.init(this.$el);
      pie.proName = proName;
      pie.proValue = proValue;
      pie.status = status;
      pie.versionIds = versionIds;
      pie.setOption(pieOptions);
      let _this = this;
      pie.on("click", function(params) {
        var requestObject = {};
        requestObject.requestUrl = "/home/getDetailDataByPie";
        var obj = params.data;
        obj[this.proName] = this.proValue;
        obj.status = this.status;
        obj.versionIds = this.versionIds;
        requestObject.requestObject = obj;
        //调用缺陷详细组件方法
        _this.$refs.defectDetail.loadDefectData(requestObject);
        _this.showDefectDetail = true;
      });
    }
  },
};
</script>