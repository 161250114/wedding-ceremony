import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'

Vue.config.productionTip = false
Vue.use(ElementUI)
// Vue对象添加原型:属性名是$ajax
Vue.prototype.$axios = axios
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
