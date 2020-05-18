    import Vue from 'vue'
    import Vuex from 'vuex'
import itemApi from './api/items.js'
import orderApi from './api/orders.js'
import axios from "axios";
import 'regenerator-runtime/runtime';

Vue.use(Vuex);

export default new Vuex.Store({
        state: {
            items,
            currentPage,
            totalPages,
            ...frontendData,

        },
        getters: {
            sortedItems: state => state.filteredItems,
            currentPage: state => state.currentPage,
            totalPages: state => state.totalPages,
            profile: state => state.profile,
            sortedOrders: state => (state.orders || []).sort((a, b) => -(a.id - b.id)),
            basket: state => state.basket,
            dogfeedTypeList: state => state.dogFeedTypes,
            catfeedTypeList: state => state.catFeedTypes,
            currentOrder: state => state.currentUnpaidOrder,
        },
        mutations: {
            addOrders(state, orders) {
                state.orders = Object.values(orders);
            },
            addItemPageMutation(state, items) {
                state.items = Object.values(items);
            },
            updateTotalPagesMutation(state, totalPages) {
                state.totalPages = totalPages
            },
            updateCurrentPageMutation(state, currentPage) {
                state.currentPage = currentPage
            },
            updateBasket(state, item) {
                let a = false;
                state.basket.items.forEach(itemFrom => {
                    if (itemFrom.id === item.id) {
                        if (itemFrom.sizeOfItem === item.sizeOfItem) {
                            itemFrom.quantity += item.quantity;
                            a = true;
                        }
                    }
                });
                if (!a) {
                    //блятский js ебал в рот
                    state.basket.items = state.basket.items.concat(item);
                }
                let cost = 0;
                let quantities = 0;
                state.basket.items.forEach(item => {
                    quantities += item.quantity;
                    cost += item.quantity * parseFloat(item.price);
                });
                state.basket.quantities = quantities;
                state.basket.sum = cost.toFixed(2);
                sessionStorage.setItem("basket", JSON.stringify(state.basket))
            },
            deleteBasketItem(state, index) {
                state.basket.items.splice(index, 1);
            },
            filterItems(state, company) {
                state.filteredItems = state.items.filter(item => company === item.company);
            },
            filterRootItems(state, dog) {
                let filter = "cat";
                if (dog) {
                    filter = "dog";
                }
                state.filteredItems = Object.values(state.items.filter(item => filter === item.pet));
            },
            confirmOrder(state, basket) {
                state.basket = basket
            },
            removeBasket(state) {
                state.basket.items = [];
                sessionStorage.removeItem("basket")
            },
            updateProfile(state, profile) {
                state.profile = profile
            }
        },
        actions: {
            async addAddress({commit}, address) {
                const data = await axios.post('/profile/address', {address: address})
                commit('updateProfileAddress', data.address);
            },
            async searchItem({commit}, payload) {
                const text = await payload.text;
                const data = await axios.get('/search/' + text);

                commit('addItemPageMutation', data.items);
                commit('updateTotalPagesMutation', data.totalPages);
                commit('updateCurrentPageMutation', Math.min(data.currentPage + 1, data.totalPages))
            },
            login({commit}, payload) {
                const data = {
                    username: payload.username,
                    password: payload.password,
                };
                const result = axios.post('/login', data);
                return result;
            },

             async registration({state, commit}, payload) {
                const data = {
                    feed: payload.feed,
                    password: payload.password,
                    email: payload.email,
                    address: payload.address,
                    telephone: payload.telephone
                };
                let profile = null;
                await axios.post('/registration', data).then(function (response) {
                    profile = response.data;
                    if (profile) {
                        return "Вы успешно зарегистрированы. Пожалуйста, войдите со указанными данными"
                    } else {
                        return "Ошибка регистрации, неверно заполненная форма"
                    }
                });

            },
            async loadPageAction({commit, state}, path, filter) {
                const response = await itemApi.page("/pets/" + path);
                const data = await response.json();

                commit('addItemPageMutation', data.items);
                commit('filterItems', data.items);
                commit('updateTotalPagesMutation', data.totalPages);
                commit('updateCurrentPageMutation', Math.min(data.currentPage + 1, data.totalPages))
            },
            async loadCertainPage({commit, state}, path, filter) {
                const resp = await itemApi.certainPage("/pets/" + path);
                const data = await resp.json();

                commit('addItemPageMutation');
                commit('filterItems');
                commit('updateTotalPagesMutation', data.totalPages);
                commit('updateCurrentPageMutation', Math.min(data.currentPage + 1, data.totalPages))
            },
            async loadOrders({commit}) {
                const resp = await orderApi.orders();
                const orders = await resp.json();

                commit('addOrders', orders);
            },

            async saveOrderWithoutPayment({state, commit}, basket) {
                await axios.post('/order', basket).then(function (response) {
                    state.currentUnpaidOrder = response.data;
                });
                commit('removeBasket');
            }

        }
    }
)
