<!-- 阀值设置 -->
<template>
  <div class="modal">
    <Form>
      <span class="item-title">返工缺陷数阀值：</span>
      <div>
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter0.action"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        红色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter0.warnning"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter0.max"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </div>
      <br>
      <span class="item-title">超48小时未解决缺陷数阀值：</span>
      <div>
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter1.action"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        红色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter1.warnning"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter1.max"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </div>
      <br>
      <span class="item-title">24小时内未解决缺陷数阀值：</span>
      <div>
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter2.action"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        红色预警:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter2.warnning"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        最大值:
        <Input type="text" placeholder="正整数" class="setting-input" v-model="dashParameter2.max"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </div>
    </Form>
  </div>
</template>
<script>
export default {
  data() {
    return {
        dashParameter0:{
            action:7,
            warnning:15,
            max:30,
        },
        dashParameter1:{
            action:6,
            warnning:15,
            max:30,
        },
        dashParameter2:{
            action:6,
            warnning:15,
            max:30,
        }
    };
  },
  created(){

    // this.$fetch("").then(resp=>{

    // }).catch(error=>{

    // });
  },
  props: {},
  methods: {
    getThresholdSettings: function() {
      return {
        dashParameter0:{
            action: this.dashParameter0.action,
            warnning: this.dashParameter0.warnning,
            max: this.dashParameter0.max,
        },
        dashParameter1:{
            action: this.dashParameter1.action,
            warnning: this.dashParameter1.warnning,
            max: this.dashParameter1.max,
        },
        dashParameter2:{
            action: this.dashParameter2.action,
            warnning: this.dashParameter2.warnning,
            max: this.dashParameter2.max,
      }
    }
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
