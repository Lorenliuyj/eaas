<template>
  <div class="resolved-defect unfloat">
    <div class="defect-main">
      <Row>
        <Col span="6">
          <PieBoard ref="pieForSystem"></PieBoard>
        </Col>
        <Col span="6">
          <PieBoard ref="pieForHours"></PieBoard>
          <div style="width:100%">
            <Select v-model="sysFromPie" style="width:30%;margin-left:35%" @on-change="loadPieForHours">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
          </div>
        </Col>
        <Col span="6">
          <PieBoard ref="pieForRework"></PieBoard>
        </Col>
        <Col span="6">
          <PieBoard ref="pieForOver48"></PieBoard>
        </Col>
      </Row>
      <div class="bottom unfloat">
        <div class="bottom-left float-left">
          <Card style="width:100%">
            <p slot="title">
              <Icon type="md-apps" size="20"/>
                超48小时未处理缺陷排名
            </p>
            <p slot="extra">
                <Select v-model="sysFrom48UnDeal" style="width:150px" @on-change="loadTableFor48UnDeal">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right" @click="exportDataFor48unDeal('超48小时未处理缺陷排名')" />
            </p>
            <Table :columns="columnForUserBug" ref="tablsFor48unDeal" height="300" stripe :data="tableDataFor48unDeal" size="small" class="table-left"  @on-row-click="showDefectFrom48UnDeal"></Table>
          </Card>
        </div>
        <div class="bottom-right float-left">
          <Card style="width:100%">
            <p slot="title">
              <Icon type="md-apps" size="20"/>
                缺陷排名前十
            </p>
            <p slot="extra">
                <Select v-model="sysFromRank10" style="width:150px" @on-change="loadTableForRank10">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right"  @click="exportDataForRank10('缺陷排名前十')" />
            </p>
            <Table :columns="columnForUserBug" height="300" stripe :data="tableDataForRank10" size="small"  @on-row-click="showDefectFromRank10"></Table>
          </Card>
        </div>
        <div class="bottom-bottom unfloat">
          <Card style="width:100%">
            <p slot="title">
              <Icon type="md-apps" size="20"/>
                缺陷超5个的cq单明细
            </p>
            <p slot="extra">
                <Select v-model="sysFromCQOver5" style="width:150px" @on-change="loadTableForCQOver5">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right" @click="exportTableForCQOver5('缺陷超5个的cq单明细')" />
            </p>
            <Table :columns="columnForCQOver5" height="400" stripe :data="tableDataForCQOver5" ref="tableForCQOver5" size="small" @on-row-click="showDefectFromSystem"></Table>
            <Page :total="40" show-sizer show-total />
          </Card>
        </div>
      </div>
    </div>
    <Modal v-model="showDefectDetail" title="缺陷明细列表" :mask-closable="false" width="1300">
      <DefectDetail ref="defectDetailRef" ></DefectDetail>
    </Modal>
  </div>
</template>
<script>
/*使用echarts时，首先在项目目录下运行npm install echarts --save，就会在node_modules下下载插件包*/
//然后引入echarts包，这里全部引用，考虑性能就得用到啥引用啥
import PieBoard from  "./PieBoard"
import DefectDetail from "./DefectDetail"
export default {
  name: "resolved-defect",
  /*************************************数据**************************************** */
  data() {
    return {
      //版本
      versionList: [
        {
          value: "capital_20180221",
          label: "capital_20180221"
        },
        {
          value: "capital_20180220",
          label: "capital_20180220"
        }
      ],
      model1: "capital_20180220", //默认
      //系统
      systemList: [
        {
          value: "eaas",
          label: "企业年金受理平台"
        },
        {
          value: "acvs",
          label: "帐管系统"
        }
      ],
      sysFrom48UnDeal:"",
      sysFromRank10:"",
      sysFromCQOver5:"",
      sysFromPie:"",
      tableDataFor48unDeal: [],
      tableDataForRank10: [],
      tableDataForCQOver5:[],
      //table表
      columnForCQOver5: [
        {
          title: "序号",
          type:"index"
        },
        {
          title: "需求名",
          key: "title"
        },
        {
          title: "版本",
          key: "version"
        },
        {
          title: "系统",
          key: "projectName"
        },
        {
          title: "bug数",
          key: "bugSum"
        }
      ],
      columnForUserBug: [
        {
          title: "名次",
          type:"index",
        },
        {
          title: "用户",
          key: "realName"
        },
        {
          title: "bug数",
          key: "bugCount"
        }
      ],
      resolvedData: [],
      textName: "ECharts 入门示例",
      count: 10,
      showDefectDetail:false,
      version:""
    };
  },
  components:{
    PieBoard,
    DefectDetail
  },
  created(){
    //获取APP页面传入的参数
    this.getAppParam();
    // 加载所有系统下拉框
    this.loadSystem();
    // 加载饼图
    this.loadPies();
    // 加载表格
    this.loadTables();
  },
  methods:{
    loadSystem:function(){
      let _this = this;
      //初始系统
      this.$fetch("home/getBaseData").then(response => {
      _this.systemList = response.result.systemList;
      });
    },
    loadTables:function(){
      //加载表格  超48小时未解决缺陷排名
      this.loadTableFor48UnDeal();
      //加载表格  缺陷排名前十
      this.loadTableForRank10();
      //加载表格   缺陷超过5个的CQ单明细
      this.loadTableForCQOver5();
    },
    loadTableFor48UnDeal:function(){
      var reqObj = {};
      reqObj.systemName = this.sysFrom48UnDeal;
      reqObj.version = this.version;
      reqObj.unDeal = false;
      this.$fetch("/home/tableFor48UnDeal",reqObj).then(
        response =>{
          this.tableDataFor48unDeal = response.result;
        },function(){

        }
      )
    },
    loadTableForRank10:function(){
      var reqObj = {};
      reqObj.systemName = this.sysFrom48UnDeal;
      reqObj.version = this.version;
      reqObj.unDeal = false;
      this.$fetch("/home/tableForRank10",reqObj).then(
        response =>{
          this.tableDataForRank10 = response.result;
        },function(){

        }
      )
    },
    loadTableForCQOver5:function(){
      var reqObj = {};
      reqObj.system = this.sysFrom48UnDeal;
      reqObj.version = this.version;
      reqObj.unDeal = false;
      this.$fetch("/home/tableDataForCQOver5",reqObj).then(
        response =>{
          this.tableDataForCQOver5 = response.result;
        },function(){

        }
      )
    },
    loadPies:function(){
      //加载饼图 未解决缺陷按系统占比
      this.loadPieForCommon();
      //加载饼图 未解决缺陷按时间占比
      this.loadPieForHours();
    },
    getAppParam(){
      this.version = this.$route.params.version;
    },
    // 超48小时未处理缺陷排名 表格穿透弹窗方法
    showDefectFrom48UnDeal:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/home/tableFor48UnDealDetail";
      var reqParam = {};
      reqParam.account =  data.account;
      reqParam.systemName =  this.sysFrom48UnDeal;
      reqParam.version = this.version;
      reqParam.undeal = false;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 缺陷排名前十 表格穿透弹窗方法
    showDefectFromRank10:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/home/tableForRank10Detail";
      var reqParam = {};
      reqParam.account =  data.account;
      reqParam.systemName =  this.sysFromRank10;
      reqParam.version = this.version;
      reqParam.undeal = false;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 缺陷超5个的cq单明细表格穿透弹窗方法
    showDefectFromSystem:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/home/tableDataForCQOver5Detail";
       var reqParam = {};
      reqParam.id = data.id;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 传入封装好的参数  显示穿透弹窗
    showDefectDetailModal:function(defectDetailData){
      this.$refs.defectDetailRef.loadDefectData(defectDetailData);
      this.showDefectDetail = true;
    },
    loadPieForCommon:function(){
      let _this = this;
      var reqObj = {};
      reqObj.version = this.version;
      reqObj.undeal = false;
      this.$fetch("/home/getBugPercent",reqObj)
      .then(
        response =>{
            var pieForSys = {};
            pieForSys.pieName = "缺陷按系统占比";
            pieForSys.data = response.result.pieForSys;
            var pieForRework = {};
            pieForRework.pieName = "返工缺陷按系统占比";
            pieForRework.data = response.result.pieForSys;
            var pieForOver48 = {};
            pieForOver48.pieName = "超过48小时未处理缺陷按系统占比";
            pieForOver48.data = response.result.pieForSys;
            _this.$refs.pieForSystem.loadPie(pieForSys);
            _this.$refs.pieForRework.loadPie(pieForRework,"type","redev");
            _this.$refs.pieForOver48.loadPie(pieForOver48,"mintime","48");
            },
            function(response) {
              // TODO
            }
      )
    },
    loadPieForHours:function(){
      let _this = this;
      var reqObj = {};
      reqObj.version = this.version;
      reqObj.systemName = this.sysFromPie;
      reqObj.undeal = false;
      this.$fetch("/home/getBugPercentHour",reqObj)
      .then(
        response =>{
            var pieParameter = {};
            pieParameter.pieName = "缺陷按时间占比";
            pieParameter.data = response.result;
              _this.$refs.pieForHours.loadPie(pieParameter,"","","");
            },
            function(response) {
              // TODO
            }
      )
    },
    exportDataFor48unDeal:function(filename){
      this.$refs.tablsFor48unDeal.exportCsv({
          filename: filename
      });
    },
    exportDataForRank10:function(filename){
      this.$refs.tableForRank10.exportCsv({
          filename: filename
      });
    },
    exportTableForCQOver5:function(filename){
      this.$refs.tableForCQOver5.exportCsv({
          filename: filename
      });
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>




