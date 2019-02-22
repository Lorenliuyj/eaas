<template>
  <div v-bind:style="dashStyle"></div>
</template>
<script>
export default {
  name: "dash-board",
  data() {
    return {
      dashStyle: {
        active: true,
        width: "500px",
        height: "250px",
        float: "left",
        dashParameter:{}
      }
    };
  },
  created() {
    var dashWidth = (document.body.clientWidth * 0.85) / 3;
    this.dashStyle.width = dashWidth + "px";
  },
  methods: {
    // loadDashBoard: function(initeParameter){
    //   debugger;
    //   this.dashParameter = initeParameter;
    //   this.setDashParameter(this.dashParameter);
    // },
    loadDashBoard: function(dashParameter) {
      var options = {
        title: {
          text: ""
        },
        series: {
          type: "gauge",
          data: [{ value: 0 }],
          max: 0,
          axisLine: {
            show: true,
            lineStyle: {
              color: [[0, "#3dc710"], [0, "#fff039"], [1, "#e20419"]] //第一个为安全阀值，第二个为橙色预警，第三个为红色预警
            }
          },
          axisTick: {
            show: false
          }
        }
      };
      options.title.text = dashParameter.title;
      options.series.data[0].value = dashParameter.currentValue;
      options.series.max = dashParameter.max;
      options.series.axisLine.lineStyle.color[0][0] =
        dashParameter.action / dashParameter.max;
      options.series.axisLine.lineStyle.color[1][0] =
        dashParameter.warnning / dashParameter.max;
      let dashBoard = this.$echarts.init(this.$el);
      dashBoard.setOption(options);
      this.dashParameter = options;
    },
    reloadDashBoard:function(thresholdParam){
      this.dashParameter.series.max =  thresholdParam.max;
      this.dashParameter.series.axisLine.lineStyle.color[0][0] =  thresholdParam.action/ thresholdParam.max;
      this.dashParameter.series.axisLine.lineStyle.color[1][0] = thresholdParam.warnning/ thresholdParam.max;
      let dashBoard = this.$echarts.init(this.$el);
      dashBoard.setOption(this.dashParameter);

    }
  },
  mounted() {
    
  }
};

/**
 * 初始化仪表盘前校验及初始化部分默认数据
 */
function validateAndSetDefaultBeforeInit(dashParameter) {
  // if (dashParameter.dashBoardId == null) {
  //   dashParameter.dashBoardId = 0;
  // }
  // //默认最大值为40
  // if (dashParameter.max == null || dashParameter.max == 0) {
  //   dashParameter.max = 40;
  // }
  // // 如果未设置阀值则抛出异常
  // if (
  //   dashParameter.greenThreshold == null ||
  //   dashParameter.yellowThreshold == null ||
  //   dashParameter.redThreshold == null
  // ) {
  //   return false;
  // }
  // //默认当前数据为0
  // if (dashParameter.currentValue == null) {
  //   dashParameter.currentValue = 0;
  // }
  return true;
}
</script>
