<template>
  <div style="width:100%;height:100%">
    <Table :loading="loading" :columns="columns" height="500" :data="data" stripe ref="table" size="small" :no-data-text="nodatatext"></Table>
  </div>
</template>

<script>
export default {
  props: {
    defectDetailData: {
      type: Object,
      validator: function(defectDetailData) {
        return true;
      },
      default: function() {
        return null;
      },
      require: true
    }
  },
  data() {
    return {
      loading:false,
      nodatatext:"暂无数据",
      columns: [
        {
          title: "序号",
          type:"index",
          width:70
        },
        {
          title: "缺陷ID",
          key: "id",
          maxWidth:90
        },
        {
          title: "提出时间(H)",
          key: "putHours",
        },
        {
          title: "系统",
          key: "projectName",
          minwidth:140,
          tooltip:true
        },
        {
          title: "BUG类型",
          key: "type",
          tooltip:true
        },
        {
          title: "BUG标题",
          key: "title",
          tooltip:true
        },
        {
          title: "创建人",
          key: "openedBy"
        },
        {
          title: "指派人",
          key: "assignedTo"
        },
        {
          title: "解决人",
          key: "resolvedBy"
        },
        {
          title: "方案",
          key: "resolution",
          tooltip:true
        }
      ],
      data: []
    };
  },
  created() {
  },
  methods:{
      loadDefectData(defectDetailData){
        this.loading = true;
        let _this = this;
        _this.data = [];
          //发起后台请求  返回查询结果
        this.$fetch(defectDetailData.requestUrl,defectDetailData.requestObject)
        .then(
            response =>{
              _this.data = response.result;
              _this.loading = false;
              _this.nodatatext="暂无数据";
            },
            function(response) {
              console.log("查询失败"+response);
              _this.loading = false;
              _this.nodatatext="查询异常";
            }
        );
      }
  }
};
</script>