<template>
  <div id="app" style="width:95%;height:100%;margin:0 auto;padding-top:7px">
    <Layout style="width:100%;height:100%">
      <Header style="height:100px;background:#6b7386">
        <div class="header_page float-left">
          <h1 style="font-size: 40px;color: #565454;">缺陷质量管理驾驶舱</h1>
        </div>
        <div class="float_right versions">
          <span style="font-size: 15px;color:darkgrey">版本:</span>
          <Select
            multiple
            v-model="versionId"
            style="width:260px"
            clearable
            @on-change="changeVersionOrSys(1)"
          >
            <Option
              v-for="versions in versionList"
              :value="versions.value"
              :key="versions.value"
            >{{ versions.label }}</Option>
          </Select>
        </div>
      </Header>
      <Content style="width:100%;height:100%">
        <div style="width:100%;height:30%">
          <!-- 仪表盘start -->
          <Collapse value="dash" accordion simple class="collapse">
            <Panel name="dash" style>
              仪表盘
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统:
              <Select
                v-model="systemId"
                style="width:150px"
                size="small"
                clearable
                @on-change="changeVersionOrSys(2)"
                @click.native.stop="() => {}"
              >
                <Option
                  v-for="sys in systemList"
                  :value="sys.value"
                  :key="sys.value"
                >{{ sys.label }}</Option>
              </Select>
              <!-- <Icon type="md-settings" @click="showThreshold = true" v-on:click.native.stop="() => {}" size="24"/> -->
              <Icon
                type="md-settings"
                @click="showSetting"
                v-on:click.native.stop="() => {}"
                size="24"
              />
              <Modal
                v-model="showThreshold"
                title="阀值设置"
                :mask-closable="false"
                width="580"
                @on-ok="settingThenShow"
              >
                <ThresholdSetting ref="thresholdSetting"></ThresholdSetting>
              </Modal>
              <p slot="content" style="height:200px;width:100%">
                <DashBoard ref="loadDashBoard0"></DashBoard>
                <DashBoard ref="loadDashBoard1"></DashBoard>
                <DashBoard ref="loadDashBoard2"></DashBoard>
              </p>
            </Panel>
          </Collapse>
          <!-- 仪表盘end -->
        </div>
        <div style="width:100%;height:70%;margin-top:3px;clear:both;background:white">
          <div class="page-div">
            <Select
              style="width:200px"
              v-model="pageSelectValue"
              placeholder="已解决缺陷"
              @on-change="jumpToPage"
            >
              <Option value="1">未解决缺陷</Option>
              <Option value="2">所有缺陷</Option>
            </Select>
          </div>
          <div id="pageCol">
            <router-view :versionIds="versionIds"></router-view>
          </div>
        </div>
      </Content>
    </Layout>
  </div>
</template>

<script>
// import Java from './components/Java'
import DashBoard from "./components/DashBoard";
import ThresholdSetting from "./components/ThresholdSetting";

export default {
  name: "app",
  components: {
    DashBoard,
    dashboard: DashBoard,
    ThresholdSetting
  },

  data() {
    return {
      pageSelectValue: "1",
      isActivated: true,
      showThreshold: false,
      button1: "我的测试",
      versionList: [],
      versionIds: "",
      versionId: [],
      systemList: [],
      systemId: "",
      dashParameter0: {},
      dashParameter1: {},
      dashParameter2: {}
    };
  },
  //方法
  methods: {
    //显示阀值设置框
    showSetting() {
      this.showThreshold = true;
    },
    //阀值设置完成后的处理
    settingThenShow() {
      var result = this.$refs.thresholdSetting.getThresholdSettings();
      let _this = this;
      this.$fetch("home/setDashBoardThreshold", {
        json: JSON.stringify(result)
      }).then(resp => {
        _this.$refs.loadDashBoard0.reloadDashBoard(result.dashBoardRedev);
        _this.$refs.loadDashBoard1.reloadDashBoard(result.dashBoard48Hour);
        _this.$refs.loadDashBoard2.reloadDashBoard(result.dashBoard24Hour);
      });
    },
    /**
     * 更新仪表盘
     * url :访问后台地址
     * parameters:传递到后台的数据
     */
    updateDashBorad(url, parameters) {
      let _this = this;
      this.$fetch(url, parameters)
        .then(response => {
          response.result.dashBoardRedev.title = "返工缺陷仪表盘";
          //初始化阀值设置参数
          _this.$refs.thresholdSetting.initDashBoardSetting(response.result);
          _this.$refs.loadDashBoard0.loadDashBoard(
            response.result.dashBoardRedev
          );
          response.result.dashBoard48Hour.title = "超48小时未解决仪表盘";
          _this.$refs.loadDashBoard1.loadDashBoard(
            response.result.dashBoard48Hour
          );
          response.result.dashBoard24Hour.title = "24小时未解决仪表盘";
          _this.$refs.loadDashBoard2.loadDashBoard(
            response.result.dashBoard24Hour
          );
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    //版本或者系统更改时触发事件
    changeVersionOrSys(type) {
      if (type === 1) {
        //更改版本
        this.versionIds = "";
        for (var i = 0; i < this.versionId.length; i++) {
          if (i == this.versionId.length - 1) {
            this.versionIds += this.versionId[i];
          } else {
            this.versionIds += this.versionId[i] + ",";
          }
        }
        let expireDays = 1000 * 60 * 60;
        this.$setCookie("versionIds", this.versionIds, expireDays); //设置Cookie
        this.updateDashBorad("/home/dashBoardData", {
          versionIds: this.versionIds,
          systemId: this.systemId
        });
        this.jumpToPage();
        console.log("versionIds==" + this.versionIds);
      } else if (type === 2) {
        //更改系统
        console.log("systemId ==" + this.systemId);
        this.updateDashBorad("/home/dashBoardData", {
          versionIds: this.versionIds,
          systemId: this.systemId
        });
      }
    },
    //跳转页面
    jumpToPage: function() {
      switch (this.pageSelectValue) {
        case "2":
          this.$router.push({
            name: "resolved-defect",
            params: { versionIds: this.versionIds }
          });
          break;
        case "1":
          this.$router.push({
            name: "unresolved-defect",
            params: { versionIds: this.versionIds }
          });
          break;
        default:
          break;
      }
    }
  },
  created() {
    let _this = this;
    //初始化版本和系统
    this.$fetch("home/getBaseData").then(response => {
      _this.versionList = response.result.versionList;
      _this.systemList = response.result.systemList;
    });
    //获取cookie
    if (this.$getCookie("versionIds")) {
      let str = this.$getCookie("versionIds");
      let temp = str.replace(/\%2C/g, ",");
      this.versionIds = temp;
      this.versionId = str.split(/\%2C/g);
    }

    //初始化仪表盘
    this.updateDashBorad("/home/dashBoardData", {
      versionIds: this.versionIds,
      systemId: this.systemId
    });
  }
};
</script>

<style>
.page-div {
  margin: 30px 0 0 0;
  padding: 20px;
  height: 60px;
  width: 250px;
}

.page-font {
  color: #666;
  font-size: 16px;
}

.font-center {
  text-align: center;
}

.header_page {
  margin-left: 20px;
  margin-top: 18px;
  width: 50%;
}

.collapse {
  margin-top: 5px;
  width: 100%;
  height: 100%;
}

* {
  padding: 0px;
  margin: 0px;
}
.float-left {
  float: left;
}
.float-right {
  float: right;
}
#app {
  background-color: #f8f8ff;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  color: #2c3e50;
  padding: 15px;
}

.defect-main {
  padding: 15px;
}
/**********************************头部**********************************/
.header {
  width: 100%;
  height: 73px;
  background-color: #1ec4fc;
  border: 1px solid gray;
}
#header-left {
  font-family: "Tangerine", serif;
  font-size: 24px;
  color: #c7ebf7;
}
#header-right {
  margin-left: 40%;
  position: relative;
}
.unfloat {
  clear: both;
}
.resolved-defect p {
  clear: both;
  line-height: 35px;
  height: 35px;
  background: #fff;
}
/**********************************左边**********************************/
.top-left {
  width: 40%;
  height: 600px;
  background: #fff;
}
.top-left p {
  background: #1ec4fc;
}

.top-left dd {
  font-weight: bolder;
}
.top-left dt {
  margin-left: 100px;
}
.top-right {
  width: 59.5%;
  height: 600px;
}
.top-right dl {
  width: 48%;
  height: 290px;
  margin: 0 0 10px 10px;
  float: left;
  background: #fff;
}
.top-right dt {
  height: 260px;
  vertical-align: middle;
}
.top-right dd {
  height: 35px;
  line-height: 35px;
  background: #1ec4fc;
  text-align: center;
}
.dash-board {
  width: 200px;
  height: 180px;
}
.bg {
  width: 100%;
  height: 35px;
  line-height: 35px;
  margin: 5px 0px 0px 0px;
  background: #1ec4fc;
  text-align: center;
}
.bottom {
  padding-top: 15px;
}
.bottom-left {
  width: 40%;
  margin: 10px 10px 10px 1px;
}
.bottom-right {
  width: 59%;
  margin-top: 10px;
}
.bottom-bottom {
  padding-top: 10px;
}

.float_right {
  float: right;
}
.versions {
  margin: 40px -20px 0 0;
}
.activated {
  background-color: #d6e1ec;
}
</style>
