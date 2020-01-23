import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCar as fasCar, faDesktop as fasDesktop, faMedal as fasMedal, 
         faUsers as fasUsers, faInfoCircle as fasInfoCircle, faRoad as fasRoad, 
         faClock as fasClock, faPlayCircle as fasPlayCircle, faTrophy as fasTrophy} from '@fortawesome/free-solid-svg-icons'
import {faClock as farClock} from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(fasCar, fasDesktop, fasMedal, 
            fasUsers, fasInfoCircle, 
            fasRoad, fasClock, fasPlayCircle, fasTrophy,
            farClock)
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
// Use moment plugin for Vue
Vue.use(require('vue-moment'));

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
