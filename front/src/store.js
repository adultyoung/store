import Vue from 'vue'
import Vuex from 'vuex'
import itemApi from './api/items.js'
import axios from "axios";
import 'regenerator-runtime/runtime';

Vue.use(Vuex);

export default new Vuex.Store({
        state: {
            items,
            ...frontendData

        },
        getters: {
            sortedItems: state => (state.items || []).sort((a, b) => -(a.id - b.id))
        },
        mutations: {
            addItemPageMutation(state, items) {
                const targetItems = state.items
                    .concat(items)
                    .reduce((res, val) => {
                        res[val.id] = val;
                        return res
                    }, {});

                state.items = Object.values(targetItems)
            },
            updateTotalPagesMutation(state, totalPages) {
                state.totalPages = totalPages
            },
            updateCurrentPageMutation(state, currentPage) {
                state.currentPage = currentPage
            }
        },
        actions: {
            searchItem({commit}, payload) {
                const data = payload.text;
                const result = axios.get('/search?=' + data);
            },
            login({commit}, payload) {
                const data = {
                    username: payload.username,
                    password: payload.password,
                };
                const result = axios.post('/login', data);
                return result;
            },

            registration({commit}, payload) {
                const data = {
                    username: payload.username,
                    password: payload.password,
                    email: payload.email,
                };
                const result = axios.post('/registr', data);
                return result;
            },
            async loadPageAction({commit, state}) {
                const response = await itemApi.page(state.currentPage + 1);
                const data = await response.json();

                commit('addItemPageMutation', data.items);
                commit('updateTotalPagesMutation', data.totalPages);
                commit('updateCurrentPageMutation', Math.min(data.currentPage, data.totalPages - 1))

            }
        },
    }
)
