import Vue from 'vue'
import VueResource from "vue-resource";

Vue.use(VueResource);

const items = Vue.resource('/item');

export default {
    add: item => items.save({}, item),
    update: item => items.update({id: item.id}, item),
    remove: id => items.remove({id}),
    page: page => Vue.http.get('/item', {params: {page}})
}
