import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import router from "./router.js"
import store from "./store.js"
import App from "./App.vue"
import {connect} from './util/ws'
import * as axios from "axios"
import '@babel/polyfill'
import './api/resource'
import "regenerator-runtime/runtime";


connect();

axios.defaults.withCredentials = true;

Vue.prototype.$axios = axios;

Vue.use(Vuetify)

export default new Vue({
    el: '#app',
    router,
    store,
    // mounted() {
    //     window.onscroll = function () {
    //         growShrinkLogo()
    //     };
    //
    //     var Logo = document.getElementById("Logo");
    //     var endOfDocumentTop = 150;
    //     var size = 0;
    //
    //     function growShrinkLogo() {
    //         var scroll = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    //
    //         if (size == 0 && scroll > endOfDocumentTop) {
    //             Logo.className = 'smallLogo';
    //             size = 1;
    //         } else if (size == 1 && scroll <= endOfDocumentTop) {
    //             Logo.className = 'largeLogo';
    //             size = 0;
    //         }
    //     }
    // },
    render: a => a(App)
})
