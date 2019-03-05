// 这里面负责写路由映射，便于管理
// 引入路由模块并使用它
import Vue from 'vue'
import VueRouter from 'vue-router'
import ResolvedDefect from '../components/ResolvedDefect.vue'
import UnresolvedDefect from '../components/UnresolvedDefect.vue'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import axios from 'axios'
import {post,fetch,patch,put} from '../../static/js/http'
import {getCookie,setCookie,delCookie} from '../../static/js/Cookie'

//定义全局变量
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;
//定义cookie方法
Vue.prototype.$getCookie = getCookie;
Vue.prototype.$setCookie = setCookie;
Vue.prototype.$delCookie = delCookie;

Vue.use(iView);
Vue.use(VueRouter);
Vue.prototype.$axios= axios
// axios.defaults.baseURL='http://182.119.90.145:8080'//生产
// axios.defaults.baseURL='http://182.207.113.235:8080' //简松
// axios.defaults.baseURL='http://182.207.113.161:8080' //彭信
axios.defaults.baseURL='http://182.207.113.95:8080' //广宇
// axios.defaults.baseURL='http://182.207.113.169:8088' //本地
// 创建路由实例并配置路由映射  
// path:'*',redirect:'/unresolved-defect'  重定向到path是/unresolved-defect的映射
export default new VueRouter({
  routes: [{
    path: '/unresolved-defect',
    name: 'unresolved-defect',
    component: UnresolvedDefect
  }, {
    path: '/resolved-defect',
    name: 'resolved-defect',
    component: ResolvedDefect,
  }, {
    path: '/',
    name: 'base',
    component: UnresolvedDefect
  }]
})
