import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

axios.defaults.baseURL = 'http://localhost:8443/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.user.username) {
      next()
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>',
  mounted() {
    history.pushState(null, null, document.URL)
    window.addEventListener("popstate", function(e){
      history.pushState(null, null, this.document.URL)
    }, false)
  }
})
