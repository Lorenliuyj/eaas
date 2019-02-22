<!-- 阀值设置 -->
<template>
  <div class="modal">
    <Form>
         <span class="item-title">返工缺陷数阀值</span>
    <div>
        安全值:
        <Input type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <br>
    <span class="item-title">返工缺陷数阀值</span>
    <div>
        安全值:
        <Input type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input  type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <br>
    <span class="item-title">返工缺陷数阀值</span>
    <div>
        安全值:
        <Input type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        橙色预警:
        <Input type="text" placeholder="正整数" class="setting-input" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    </Form>
  </div>
</template>

<script>
export default {
  data () {
    return {
    };
  },
  props:{
    thresholdSettings: {
      type: Object,
      validator: function(dashParameter) {
        return true;
      },
      default: function() {
        return null;
      },
      require: true
    }
  }
}

</script>
<style>
.item-title{
    font-size: 15px;
    font-weight:bolder
}

.modal{
    width:800px;
    height:250px
}

.setting-input{
    width:90px
}
</style>
