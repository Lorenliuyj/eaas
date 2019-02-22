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
        float: "left"
      }
    };
  },
  created: function() {},
  props: {
    dashParameter: {
      type: Object,
      validator: function(dashParameter) {
        return validateAndSetDefaultBeforeInit(dashParameter);
      },
      default: function() {
        return null;
      },
      require: true
    }
  },
  mounted() {
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
        },
      }
    };
    options.title.text = this.dashParameter.title;
    options.series.data[0].value = this.dashParameter.currentValue;
    options.series.max = this.dashParameter.max;
    options.series.axisLine.lineStyle.color[0][0] =
      this.dashParameter.greenThreshold / this.dashParameter.max;
    options.series.axisLine.lineStyle.color[1][0] =
      this.dashParameter.yellowThreshold / this.dashParameter.max;
    let dashBoard = this.$echarts.init(this.$el);
    dashBoard.setOption(options);
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
