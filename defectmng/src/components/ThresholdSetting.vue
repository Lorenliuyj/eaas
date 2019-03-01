<!-- 阀值设置 -->
<template>
  <div class="modal">
    <Form>
      <span class="item-title">返工缺陷数阀值：</span>
      <div>橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashBoardRedev.action"/>红色预警:
        <Input
          type="text"
          placeholder="正整数"
          class="setting-input"
          v-model="dashBoardRedev.warnning"
        />最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashBoardRedev.max"/>
      </div>
      <br>
      <span class="item-title">超48小时未解决缺陷数阀值：</span>
      <div>橙色预警:
        <Input
          type="text"
          placeholder="正整数"
          class="setting-input"
          v-model="dashBoard48Hour.action"
        />红色预警:
        <Input
          type="text"
          placeholder="正整数"
          class="setting-input"
          v-model="dashBoard48Hour.warnning"
        />最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashBoard48Hour.max"/>
      </div>
      <br>
      <span class="item-title">24小时内未解决缺陷数阀值：</span>
      <div>橙色预警:
        <Input
          type="text"
          placeholder="正整数"
          class="setting-input"
          v-model="dashBoard24Hour.action"
        />红色预警:
        <Input
          type="text"
          placeholder="正整数"
          class="setting-input"
          v-model="dashBoard24Hour.warnning"
        />最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashBoard24Hour.max"/>
      </div>
    </Form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dashBoardRedev: {},
      dashBoard48Hour: {},
      dashBoard24Hour: {}
    };
  },
  methods: {
    getThresholdSettings: function() {
      return {
        dashBoardRedev: {
          action: this.dashBoardRedev.action,
          warnning: this.dashBoardRedev.warnning,
          max: this.dashBoardRedev.max
        },
        dashBoard48Hour: {
          action: this.dashBoard48Hour.action,
          warnning: this.dashBoard48Hour.warnning,
          max: this.dashBoard48Hour.max
        },
        dashBoard24Hour: {
          action: this.dashBoard24Hour.action,
          warnning: this.dashBoard24Hour.warnning,
          max: this.dashBoard24Hour.max
        }
      };
    },
    initDashBoardSetting: function(data) {
      this.dashBoardRedev = data.dashBoardRedev;
      this.dashBoard48Hour = data.dashBoard48Hour;
      this.dashBoard24Hour = data.dashBoard24Hour;
    }
  }
};
</script>
<style>
.item-title {
  font-size: 15px;
  font-weight: bolder;
}

.modal {
  width: 800px;
  height: 250px;
}

.setting-input {
  width: 90px;
}
</style>
