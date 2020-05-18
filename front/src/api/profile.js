import Vue from 'vue'
import VueResource from "vue-resource";

Vue.use(VueResource);


const profile = Vue.resource('/profile{/id}');

export default {
    get: id => profile.get({id}),
    upgrade: id => profile.post({id}),

}
