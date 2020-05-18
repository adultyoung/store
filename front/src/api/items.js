import Vue from 'vue'
import VueResource from "vue-resource";

Vue.use(VueResource);

const items = Vue.resource('/pets');

export default {
    add: item => items.save({}, item),
    update: item => items.update({id: item.id}, item),
    remove: id => items.remove({id}),
    page: path => Vue.http.get(path),
    certainPage: path => Vue.http.get(path)
}
