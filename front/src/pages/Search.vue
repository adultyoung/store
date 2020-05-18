<template>
    <v-container class="content " style="padding: 0;">

        <v-layout class="items_content_layout" justify-space-around row wrap style="padding-top: 1.5%">
            <v-flex xs10 v-if="$vuetify.breakpoint.smAndDown">
                <v-layout>
                    <v-flex xs1><v-spacer></v-spacer></v-flex>
                    <v-flex xs10>
                        <v-text-field v-model="searchText"
                                      dense hide-details row-height="16px"
                                      @keyup.enter.native="this.$router.push('/search?search=' + searchText)"
                                      style="padding: 0; margin-right: 20px; margin-bottom: 20px; color: #909090">
                        </v-text-field>
                    </v-flex>
                    <v-flex xs1>
                        <v-btn flat @click="this.$router.push('/search?search=' + searchText)"
                               style="margin: 0;padding: 0; position: absolute; right: 80px; padding-top: 10px;"
                               type="submit"
                               icon>
                            <v-icon color="#909090" size="20">search</v-icon>
                        </v-btn>
                    </v-flex>
                </v-layout>
            </v-flex>
            <v-flex xs12>
                <div class="text-xs-left text-Century-Gothic-Bold"
                     style="font-size: 20px; padding-left: 100px; margin-top: 2%; margin-bottom: 1%">Результаты поиска
                </div>
                <v-divider style="margin: 0 7%; margin-bottom: 3%"></v-divider>
            </v-flex>
            <template v-if="filteredItems">
                <v-flex lg3>
                    <div class="text-xs-center" v-show="$vuetify.breakpoint.mdAndUp">
                        <v-pagination text-Century-Gothic
                                      circle
                                      color="#3f6b33"
                                      v-model="currentPage"
                                      :length="totalPages"
                                      @input="next"
                        ></v-pagination>
                    </div>
                </v-flex>
                <v-layout row wrap class="items_layout">
                    <item-row
                            :key="item.id"
                            :item="item"
                            v-for="item in filteredItems"/>
                </v-layout>
                <div class="text-xs-center" style="margin-bottom: 4%">
                    <v-pagination
                            circle
                            color="#3f6b33"
                            :length="totalPages"
                            @input="this.currentPage = page"
                            v-model="currentPage"
                    ></v-pagination>
                </div>
            </template>
            <template v-else>
                <div class="text-Century-Gothic" style="font-size: 22px; padding: 5% 0">Ничего не найдено <br>
                    <router-link to="/catalog?dog=true">Перейтив каталог</router-link>
                </div>
            </template>
        </v-layout>
    </v-container>
</template>

<script>
    import ItemRow from '../components/items/ItemRow.vue'

    export default {
        name: "Search",
        components: {
            ItemRow
        },
        data() {
            return {
                currentPage: 1,
                totalPages: 0,
                filter: null,
                searchText: null,
            }
        },
        computed: {
            filteredItems: function () {
                this.filter = this.$route.query.search.split(' ');
                let searchMatches = 1;
                let items = [];
                this.$store.state.items.forEach(item => {
                    if (item.title !== null) {
                        let matches = 0;
                        if (items.length === 15) {
                            return;
                        }
                        this.filter.forEach(text => {
                            if (item.title.toLowerCase().includes(text.toLowerCase())) {
                                matches++;
                            }
                        });
                        if (matches >= searchMatches) {
                            searchMatches = matches;
                            items.unshift(item);
                        } else if (matches > 0) {
                            items.push(item);
                        }
                    }
                });
                this.totalPages = Math.ceil(items.length / 10);
                const fIndex = (this.currentPage - 1) * 10;
                const lIndex = fIndex + 10 < items.length ? fIndex + 10 : items.length;
                if (items.length >= 1) {
                    return items.slice(fIndex, lIndex);
                }
            }
        },
        methods: {
            next(page) {
                page = page - 1
                const path = this.path + "?page=" + page
                this.loadPageAction(path, this.company);
                document.getElementById('content').scrollIntoView({
                    behavior: 'smooth'
                });
            },
        },
    }
</script>

<style scoped>
    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
            margin-top: 48px;
            margin-bottom: 48px;
        }

        .items_layout {
            padding: 0 30px;
        }

        .items_content_layout {
            background-color: white;

        }
    }

    @media only screen and (max-width: 960px) {

        .items_content_layout {
            background-color: white;

        }

        .v-menu__content .theme--light .menuable__content__active {
            left: 0px;
        }
    }
</style>