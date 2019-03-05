<template>
  <div style="">
    <Table :loading="loading" :columns="columns" height="500" :data="data" stripe ref="table" size="small" :no-data-text="nodatatext"></Table>
    <Page @on-change="loadPage" @on-page-size-change="loadPageSize" :total="pageObjForDetail.totalNum" :current="pageObjForDetail.pageNum" :page-size="pageObjForDetail.pageSize" show-sizer show-total/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //明细分页对象
      pageObjForDetail:{
        pageNum:1,
        pageSize:20,
        totalNum:0,
      },
      loading:false,
      nodatatext:"暂无数据",
      columns: [
        {
          title: "序号",
          type:"index",
          width:70,
        },
        {
          title: "缺陷ID",
          key: "id",
          maxWidth:90,
          align:"center"
        },
        {
          title: "提出时间(H)",
          key: "putHours",
          align:"center"
        },
        {
          title: "系统",
          key: "projectName",
          minwidth:140,
          tooltip:true,
          align:"center"
        },
        {
          title: "BUG类型",
          key: "type",
          tooltip:true,
          align:"center"
        },
        {
          title: "BUG标题",
          key: "title",
          tooltip:true,
          align:"center"
        },
        {
          title: "创建人",
          key: "openedBy",
          align:"center"
        },
        {
          title: "指派人",
          key: "assignedTo",
          align:"center"
        },
        {
          title: "解决人",
          key: "resolvedBy",
          align:"center"
        },
        {
          title: "方案",
          key: "resolution",
          tooltip:true,
          align:"center"
        }
      ],
      data: [],
      reqObj:{}
    };
  },
  created() {
  },
  methods:{
      //分页加载
      loadPage:function(value){
        debugger;
        this.pageObjForDetail.pageNum = value;
        this.fetchData(this.reqObj);
      },
      //分页加载
      loadPageSize:function(value){
        debugger;
        this.pageObjForDetail.pageSize = value;
        this.fetchData(this.reqObj);
      },
      loadDefectData(defectDetailData){
        // 每次从外部加载详情  先重置分页对象
        this.resetPageObj();
        this.loading = true;
        //获取查询结果
        this.fetchData(defectDetailData);
      },
      resetPageObj:function(){
        this.pageObjForDetail.pageNum = 1;
        this.pageObjForDetail.pageSize = 20;
        this.pageObjForDetail.totalNum = 0;
      },
      fetchData:function(defectDetailData){
        debugger;
        let _this = this;
        _this.data = [];
        this.reqObj = defectDetailData;
        this.reqObj.requestObject.pageNum = this.pageObjForDetail.pageNum;
        this.reqObj.requestObject.pageSize = this.pageObjForDetail.pageSize;
        this.$fetch(this.reqObj.requestUrl,this.reqObj.requestObject)
        .then(
            response =>{
              _this.data = response.result.list;
              _this.pageObjForDetail.totalNum = response.result.totalNum;
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