import Vue from 'vue'
import VueResource from "vue-resource";

Vue.use(VueResource);


const profile = Vue.resource('/opders/{id}');

export default {
    orders: profile.get(),
    getOne: id => profile.get(id),
    save: order => Vue.http.post('/order', order)
}


