import Vue from 'vue'
import VueRouter from 'vue-router'
import OrderList from './pages/OrderList.vue'
import Basket from './pages/Basket.vue'
import Index from './pages/Index.vue'
import Catalog from './pages/Catalog.vue'
import Settings from './pages/Settings.vue'
import Checkout from './pages/Checkout.vue'
import Profile from "./pages/Profile.vue";
import UserSettings from './pages/UserSettings.vue'
import TermsOfSale from "./pages/TermsOfSale.vue";
import PublicContract from "./pages/PublicContract.vue";
import Search from "./pages/Search.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/", component: Index},
    {path: "/profile", component: Profile,
    children: [
        {path: 'settings', component: UserSettings},
        {path: "security", component: Settings},
        {path: "opders", component: OrderList},
    ]
    },
    {path: "/basket", component: Basket},
    {path: "/catalog*", component: Catalog},
    {path: "/checkout", component: Checkout},
    {path: "/terms", component: TermsOfSale},
    {path: "/public", component: PublicContract},
    {path: "/search*", component: Search},
    {path: "*", component:Index}
];

const router = new VueRouter({mode: "history",
    routes});

// router.beforeEach((to, from, next) => {
//     if (to.fullPath === "/") {
//         document.getElementsByClassName('navBar')[0].style.backgroundColor = "rgba(0,0,0,0.5)"
//         document.getElementsByClassName('doghouse_class')[0].style.color = "white"
//         document.getElementsByClassName('doghouse_class')[1].style.color = "white"
//         document.getElementsByClassName('header_icon')[0].style.color = "white"
//         document.getElementsByClassName('header_icon')[1].style.color = "white"
//         document.getElementById('basket_icon').style.filter = "invert(100%)"
//         document.getElementById('expand_menu').style.background = 'rgba(0,0,0,0.9)'
//         document.getElementsByClassName('btnHeader v-btn')[0].style.color = "white"
//         document.getElementsByClassName('btnHeader v-btn')[1].style.color = "white"
//         document.getElementsByClassName('btnHeader v-btn')[2].style.color = "white"
//         document.getElementsByClassName('btnHeader v-btn')[3].style.color = "white"
//         document.getElementsByClassName('btnHeader v-btn')[4].style.color = "white"
//     } else {
//         document.getElementsByClassName('doghouse_class')[0].style.color = "black"
//         document.getElementsByClassName('doghouse_class')[1].style.color = "black"
//         document.getElementsByClassName('btnHeader v-btn')[0].style.color = "black"
//         document.getElementsByClassName('btnHeader v-btn')[1].style.color = "black"
//         document.getElementsByClassName('btnHeader v-btn')[2].style.color = "black"
//         document.getElementsByClassName('btnHeader v-btn')[3].style.color = "black"
//         document.getElementsByClassName('btnHeader v-btn')[4].style.color = "black"
//         document.getElementsByClassName('header_icon')[0].style.color = "black"
//         document.getElementsByClassName('header_icon')[1].style.color = "black"
//         document.getElementById('basket_icon').style.filter = "invert(0%)"
//         document.getElementById('expand_menu').style.background = 'white'
//         document.getElementsByClassName('navBar')[0].style.backgroundColor = "white"
//     }
//     next()
// });

export default router;