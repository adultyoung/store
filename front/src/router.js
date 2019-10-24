import Vue from 'vue'
import VueRouter from 'vue-router'
import FeedList from './pages/FeedList.vue'
import PollPage from "./pages/PollPage.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/", component: PollPage},
    {path: "/poll", component: PollPage}
];

export default new VueRouter({
    mode: "history",
    routes
})