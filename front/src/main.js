import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import router from "./router.js"
import store from "./store.js"
import App from "./App.vue"
// import {connect} from './util/ws'
import * as axios from "axios"
import '@babel/polyfill'
import './api/resource'
import 'md5'

// connect();

axios.defaults.withCredentials = true;


Vue.use(Vuetify)

export default new Vue({
    el: '#app',
    router,
    store,
    mounted() {
        window.addEventListener('resize', this.nothing, { passive: true });
        if (sessionStorage.getItem("basket")) {
            this.$store.state.basket = JSON.parse(sessionStorage.getItem("basket"))
        }
    },
    render: a => a(App),
})
