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
               超48小时未解决缺陷排名
            </p>
            <p slot="extra">
                <Select v-model="sysFrom48UnDeal" style="width:150px" @on-change="loadTableFor48UnDeal">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right" @click="exportData(1)" />
            </p>
            <Table :columns="columnForUserBug" height="300" stripe :data="tableDataFor48unDeal" size="small" class="table-left"  @on-row-click="showDefectFrom48UnDeal"></Table>
          </Card>
        </div>
        <div class="bottom-right float-left">
          <Card style="width:100%">
            <p slot="title">
              <Icon type="md-apps" size="20"/>
                未解决缺陷排名前十
            </p>
            <p slot="extra">
                <Select v-model="sysFromRank10" style="width:150px" @on-change="loadTableForRank10">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right" @click="exportData(2)" />
            </p>
            <Table :columns="columnForUserBug" height="300" stripe :data="tableDataForRank10" ref="table" size="small"  @on-row-click="showDefectFromRank10"></Table>
          </Card>
        </div>
        <div class="bottom-bottom unfloat">
          <Card style="width:100%">
            <p slot="title">
              <Icon type="md-apps" size="20"/>
                未解决返工缺陷明细
            </p>
            <p slot="extra">
                <Select v-model="sysFromCQOver5" style="width:150px" @on-change="loadTableForRework">
                  <Option
                    v-for="item in systemList"
                    :value="item.value"
                    :key="item.value"
                  >{{item.label}}</Option>
                </Select>
                <Icon type="md-download" size="28" color="grey" class="float-right" @click="exportData(3)" />
            </p>
            <Table :columns="columnForRework" height="400" stripe :data="tableDataForRework" ref="table" size="small"></Table>
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
      sysFrom48UnDeal:"eaas",
      sysFromRank10:"eaas",
      sysFromCQOver5:"eaas",
      sysFromPie:"eaas",
      tableDataFor48unDeal: [],
      tableDataForRank10: [],
      tableDataForRework:[],
      //table表
      columnForRework: [
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
      columnForUserBug: [
        {
          title: "名次",
          type:"index",
        },
        {
          title: "用户",
          key: "userName"
        },
        {
          title: "bug数",
          key: "bugsNum"
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
    // 加载饼图
    this.loadPies();
    // 加载表格
    this.loadTables();
  },
  methods:{
    loadTables:function(){
      //加载表格  超48小时未解决缺陷排名
      this.loadTableFor48UnDeal();
      //加载表格  缺陷排名前十
      this.loadTableForRank10();
      //加载表格   缺陷超过5个的CQ单明细
      this.loadTableForRework();
    },
    loadTableFor48UnDeal:function(){
      var reqObj = {};
      reqObj.unDeal = true;
      reqObj.system = "受理平台";
      reqObj.version = "V1.0";
      this.$fetch("/home/tableFor48UnDeal",reqObj).then(
        response =>{
          this.tableDataFor48unDeal = response.result;
        },function(){

        }
      )
    },
    loadTableForRank10:function(){
      var reqObj = {};
      reqObj.system = this.sysFrom48UnDeal;
      reqObj.version = this.version;
      reqObj.unDeal = true;
      this.$fetch("/home/tableForRank10",reqObj).then(
        response =>{
          debugger;
          this.tableDataForRank10 = response.result;
        },function(){

        }
      )
    },
    loadTableForRework:function(){
      var reqObj = {};
      reqObj.system = this.sysFrom48UnDeal;
      reqObj.version = this.version;
      reqObj.unDeal = true;
      this.$fetch("/unsolvedBugDetial/findUnsolvedBug",reqObj).then(
        response =>{
          debugger;
          this.tableDataForRework = response.result;
        },function(){

        }
      )
    },
    getAppParam(){
      this.version = this.$route.params.version;
    },
    // 超48小时未解决缺陷排名 表格穿透弹窗方法
    showDefectFrom48UnDeal:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/loadDefectFromUserAccount";
      var reqParam = {};
      reqParam.account =  data.account;
      reqParam.system =  this.sysFrom48UnDeal;
      reqParam.version = this.version;
      reqParam.undeal = true;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 未解决缺陷排名前十 表格穿透弹窗方法
    showDefectFromRank10:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/loadDefectFromUserAccount";
      var reqParam = {};
      reqParam.account =  data.account;
      reqParam.system =  this.sysFromRank10;
      reqParam.version = this.version;
      reqParam.undeal = true;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 未解决返工缺陷明细表格穿透弹窗方法
    showDefectFromSystem:function(data,index){
      var defectDetailData = {};
      defectDetailData.requestUrl = "/loadDefectFromSystemParam";
       var reqParam = {};
      reqParam.system =  data.systems;
      reqParam.version = data.versions;
      reqParam.undeal = true;
      defectDetailData.requestObject = reqParam;
      this.showDefectDetailModal(defectDetailData);
    },
    // 传入封装好的参数  显示穿透弹窗
    showDefectDetailModal:function(defectDetailData){
      this.$refs.defectDetailRef.loadDefectData(defectDetailData);
      this.showDefectDetail = true;
    },
    loadPies:function(){
      //加载饼图 未解决缺陷按系统占比
      this.loadPieForCommon();
      //加载饼图 未解决缺陷按时间占比
      this.loadPieForHours();
      //加载饼图 未解决返工缺陷按系统占比
      // this.loadPieForRework();
      //加载饼图 超过48小时未解决缺陷按系统占比
      // this.loadPieForOver48();
    },
    loadPieForCommon:function(){
      let _this = this;
      var reqObj = {};
      reqObj.version = this.version;
      reqObj.undeal = true;
      this.$fetch("/home/getUnsolve",reqObj)
      // this.$fetch("pieForSys",reqObj)
      .then(
        response =>{
            var pieForSys = {};
            pieForSys.pieName = "未解决缺陷按系统占比";
            pieForSys.data = response.result.pieForSys;
            var pieForRework = {};
            pieForRework.pieName = "未解决返工缺陷按系统占比";
            pieForRework.data = response.result.pieForSys;
            var pieForOver48 = {};
            pieForOver48.pieName = "超过48小时未解决缺陷按系统占比";
            pieForOver48.data = response.result.pieForSys;
            debugger;
            _this.$refs.pieForSystem.loadPie(pieForSys);
            _this.$refs.pieForRework.loadPie(pieForRework);
            _this.$refs.pieForOver48.loadPie(pieForOver48);
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
      reqObj.system = this.sysFromPie;
      reqObj.undeal = true;
      this.$fetch("/home/getUnsolveByHour",reqObj)
      .then(
        response =>{
            var pieParameter = {};
            pieParameter.pieName = "未解决缺陷按时间占比";
            pieParameter.data = response.result;
              _this.$refs.pieForHours.loadPie(pieParameter);
            },
            function(response) {
              // TODO
            }
            
      )
    },
    // loadPieForRework:function(){
    //   let _this = this;
    //   var reqObj = {};
    //   reqObj.undeal = true;
    //   reqObj.version = this.version;
    //   this.$fetch("/pieForSys",reqObj)
    //   .then(
    //     response =>{
    //         var pieParameter = {};
    //         pieParameter.pieName = "未解决返工缺陷按系统占比";
    //         pieParameter.data = response;
    //           _this.$refs.pieForRework.loadPie(pieParameter);
    //         },
    //         function(response) {
    //           // TODO
    //         }
    //   )
    // },
    // loadPieForOver48:function(){
    //   let _this = this;
    //   var reqObj = {};
    //   reqObj.version = this.version;
    //   reqObj.undeal = true;
    //   this.$fetch("/pieForSys",reqObj)
    //   .then(
    //     response =>{
    //         var pieParameter = {};
    //         pieParameter.pieName = "超过48小时未解决缺陷按系统占比";
    //         pieParameter.data = response;
    //           _this.$refs.pieForOver48.loadPie(pieParameter);
    //         },
    //         function(response) {
    //           // TODO
    //         }
    //   )
    // },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>




