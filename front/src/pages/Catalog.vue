<template>
    <v-container class="content " style="padding: 0;">
        <v-layout row class="pets_layout">
            <v-spacer></v-spacer>
            <v-flex xs3 sm2 md2>
                <v-btn color="#3f6b33" v-bind:outline="!dog" @click="switchToDog" class="pets text-xs-center"
                       v-if="$vuetify.breakpoint.smAndDown">
                    Собака
                </v-btn>
                <v-btn :color="dog ? '#3f6b33' : 'grey'" :style="dog ? 'z-index: 1' : 'z-index: 0'"
                       class="pets text-xs-center" @click="switchToDog" v-else>
                    Собака
                </v-btn>
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs3 sm2 md2>
                <v-btn color="#3f6b33" v-bind:outline="dog" @click="switchToCat" class="pets text-xs-center"
                       v-if="$vuetify.breakpoint.smAndDown">
                    Кот
                </v-btn>
                <v-btn :color="!dog ? '#3f6b33' : 'grey'" :style="!dog ? 'z-index: 1' : 'z-index: 0'"
                       class="pets text-xs-center" @click="switchToCat" v-else>
                    Кот
                </v-btn>
            </v-flex>
            <v-spacer></v-spacer>
        </v-layout>
        <v-layout row wrap class="choice_layout">
            <v-spacer></v-spacer>
            <v-flex xs3 md2 v-if="type!=null" class="text-Century-Gothic">
                <v-menu offset-y>
                    <v-btn style="font-size: 12px !important; padding: 0"
                           flat
                           slot="activator"
                           class="feed_filter"
                    >
                        Корм
                        <v-icon>arrow_drop_down</v-icon>
                    </v-btn>
                    <v-list class="feed_filter" style="font-size: 12px">
                        <v-list-tile @click="type='dry'">
                            <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Сухой корм
                            </v-list-tile-title>
                        </v-list-tile>
                        <v-list-tile @click="type='wet'">
                            <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Влажный корм
                            </v-list-tile-title>
                        </v-list-tile>
                    </v-list>
                </v-menu>
            </v-flex>
            <template v-if="dog">
                <v-flex xs3 md2 v-if="size!=null" class="text-Century-Gothic">
                    <v-menu offset-y>
                        <v-btn style="font-size: 12px !important; padding: 0"
                               flat
                               slot="activator"
                               class="feed_filter"
                        >
                            Порода
                            <v-icon>arrow_drop_down</v-icon>
                        </v-btn>
                        <v-list class="feed_filter">
                            <v-list-tile @click="size='small'">
                                <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Малые
                                </v-list-tile-title>
                            </v-list-tile>
                            <v-list-tile @click="type='middle'">
                                <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Средние
                                </v-list-tile-title>
                            </v-list-tile>
                            <v-list-tile @click="type='big'">
                                <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Большие
                                </v-list-tile-title>
                            </v-list-tile>
                        </v-list>
                    </v-menu>
                </v-flex>
            </template>
            <v-flex xs3 md2 v-if="age!=null" class="text-Century-Gothic">
                <v-menu offset-y>
                    <v-btn style="font-size: 12px !important; padding: 0"
                           flat
                           slot="activator"
                           class="feed_filter"
                    >
                        Возраст
                        <v-icon>arrow_drop_down</v-icon>
                    </v-btn>
                    <v-list class="feed_filter">
                        <v-list-tile @click="age='young'">
                            <v-list-tile-title class="text-Century-Gothic" v-if="dog" style="font-size: 12px">Щенки
                            </v-list-tile-title>
                            <v-list-tile-title v-else style="font-size: 12px">Котята</v-list-tile-title>
                        </v-list-tile>
                        <v-list-tile @click="age='adult'">
                            <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Взрослые
                            </v-list-tile-title>
                        </v-list-tile>
                        <v-list-tile @click="age='elder'">
                            <v-list-tile-title class="text-Century-Gothic" style="font-size: 12px">Пожилые
                            </v-list-tile-title>
                        </v-list-tile>
                    </v-list>
                </v-menu>
            </v-flex>
            <v-flex xs3 md1 v-if="age!=null" class="text-Century-Gothic">
                <v-menu full-width offset-y :close-on-content-click=false class="brand_menu"
                        style="left: 0 !important; right: 0 !important;">
                    <v-btn style="font-size: 12px !important; padding: 0"
                           flat
                           slot="activator"
                           class="feed_filter"
                    >
                        Бренд
                        <v-icon>arrow_drop_down</v-icon>
                    </v-btn>
                    <v-list class="feed_filter" style="padding-top: 20px">
                        <template v-if="type==='dry'">
                            <v-layout row wrap>
                                <template v-if="dog">
                                    <v-flex xs6 v-for="brand in dogfeedTypeList">
                                        <v-list-tile-content>
                                            <v-checkbox class="text-Century-Gothic" style="margin: 2px"
                                                        v-model="brandFilter" :label=brand :value=brand>
                                                {{brand}}
                                            </v-checkbox>
                                        </v-list-tile-content>
                                    </v-flex>
                                </template>
                                <template v-else>
                                    <v-flex xs6 v-for="brand in catfeedTypeList">
                                        <v-checkbox class="text-Century-Gothic" style="margin: 2px"
                                                    v-model="brandFilter" :label=brand :value=brand>
                                            {{brand}}
                                        </v-checkbox>
                                    </v-flex>
                                </template>
                            </v-layout>
                        </template>
                        <template v-else>
                            <v-layout row wrap style="padding-left: 15%">
                                <template v-if="dog">
                                    <v-flex xs6 v-for="brand in dogfeedTypeList">
                                        <v-checkbox class="text-Century-Gothic" style="margin: 2px"
                                                    v-model="brandFilter" :label=brand :value=brand>
                                            {{brand}}
                                        </v-checkbox>
                                    </v-flex>
                                </template>
                                <template v-else>
                                    <v-flex xs6 v-for="brand in catfeedTypeList">
                                        <v-checkbox class="text-Century-Gothic" style="margin: 2px"
                                                    v-model="brandFilter" :label=brand :value=brand>
                                            {{brand}}
                                        </v-checkbox>
                                    </v-flex>
                                </template>
                            </v-layout>
                        </template>
                    </v-list>
                </v-menu>
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs5 md12 v-if="age!=null" class="text-xs-center">
                <a @click="showAll" class="text-Sitka padding-show-all"
                   style="color: grey; margin: 0 14.5%; font-size: 14px; line-height: 3.5 !important; font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important; text-decoration: underline">Показать
                    все</a>
            </v-flex>
            <v-flex xs4 v-if="$vuetify.breakpoint.smAndDown">
                <v-text-field v-model="searchText"
                              clearable dense hide-details row-height="16px"
                              @keyup.enter.native="search"
                              style="position: absolute; padding: 0; margin-right: 20px; margin-bottom: 20px; color: #909090">
                </v-text-field>
            </v-flex>
            <v-flex xs1 v-if="$vuetify.breakpoint.smAndDown">
                <v-btn flat @click="search"
                       style="margin: 0;padding: 0; position: absolute; right: 20px; padding-top: 10px;" type="submit"
                       icon>
                    <v-icon color="#909090" size="20">search</v-icon>
                </v-btn>
            </v-flex>
        </v-layout>

        <template v-if="type == null">
            <v-layout row wrap justify-space-around
                      class="position_layout text-Century-Gothic">
                <v-spacer></v-spacer>
                <v-flex xs12 md5 @click="type='dry'" class="choice">
                    <img class="imgSelection choice-img" src="../assets/dryFeedDog.png" v-if="dog"/>
                    <img class="imgSelection choice-img" src="../assets/dryFeedCat.png" v-else/>
                    <div class="vertical-center text-Century-Gothic choice_text">Сухой корм</div>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs12 md2 v-if="$vuetify.breakpoint.mdAndDown" class="text-xs-center">
                    <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
                </v-flex>
                <v-divider v-show="$vuetify.breakpoint.mdAndUp" vertical></v-divider>
                <v-spacer></v-spacer>
                <v-flex xs12 md5 @click="type='wet'" class="choice">
                    <img class="imgSelection choice-img" src="../assets/wetFeedDog.png" v-if="dog"/>
                    <img class="imgSelection choice-img" src="../assets/wetFeedCat.png" v-else/>
                    <div class="vertical-center text-Century-Gothic choice_text">Влажный корм</div>
                </v-flex>
                <v-spacer></v-spacer>
            </v-layout>
        </template>
        <template v-else>
            <template v-if="size == null && dog">
                <v-layout class="position_layout" row wrap justify-space-around>
                    <v-spacer></v-spacer>
                    <v-flex xs12 md3 @click="size='small'" class="choice">
                        <img class="imgSelection choice-img" src="../assets/smallDog.png"/>
                        <div class="vertical-center text-Century-Gothic choice_text">Мелкие породы</div>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs12 md1 v-if="$vuetify.breakpoint.mdAndDown" class="text-xs-center">
                        <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
                    </v-flex>
                    <v-divider v-show="$vuetify.breakpoint.mdAndUp" vertical></v-divider>
                    <v-spacer></v-spacer>
                    <v-flex xs12 md3 @click="size='middle'" class="choice">
                        <img class="imgSelection choice-img" src="../assets/middleDog.png"/>
                        <div class="vertical-center text-Century-Gothic choice_text">Средние породы</div>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs12 md1 v-if="$vuetify.breakpoint.mdAndDown" class="text-xs-center">
                        <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
                    </v-flex>
                    <v-divider v-show="$vuetify.breakpoint.mdAndUp" vertical></v-divider>
                    <v-spacer></v-spacer>
                    <v-flex xs12 md3 @click="size='big'" class="choice">
                        <img class="imgSelection choice-img" src="../assets/bigDog.png"/>
                        <div class="vertical-center text-Century-Gothic choice_text">Большие породы</div>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </template>
            <template v-else>
                <template v-if="age == null">
                    <v-layout row wrap justify-space-around class="position_layout">
                        <v-spacer></v-spacer>
                        <v-flex xs12 md3 @click="age='young'" class="choice">
                            <img class="imgSelection choice-img" src="../assets/youngDog.png" v-if="dog"/>
                            <img class="imgSelection choice-img" src="../assets/youngCat.png" v-else/>
                            <div class="vertical-center text-Century-Gothic choice_text" v-if="dog">Щенки</div>
                            <div class="vertical-center text-Century-Gothic choice_text" v-else>Котята</div>
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs12 md1 v-if="$vuetify.breakpoint.mdAndDown" class="text-xs-center">
                            <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
                        </v-flex>
                        <v-divider v-show="$vuetify.breakpoint.mdAndUp" vertical></v-divider>
                        <v-spacer></v-spacer>
                        <v-flex xs12 md3 @click="age='adult'" class="choice">
                            <img class="imgSelection choice-img" src="../assets/middleAgeDog.png" v-if="dog"/>
                            <img class="imgSelection choice-img" src="../assets/middleAgeCat.png" v-else/>
                            <div class="vertical-center text-Century-Gothic choice_text" v-if="dog">Взрослые
                                собаки(1-6)
                            </div>
                            <div class="vertical-center text-Century-Gothic choice_text" v-else>Взрослые коты</div>
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs12 md1 v-if="$vuetify.breakpoint.mdAndDown" class="text-xs-center">
                            <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
                        </v-flex>
                        <v-divider v-show="$vuetify.breakpoint.mdAndUp" vertical></v-divider>
                        <v-spacer></v-spacer>
                        <v-flex xs12 md3 @click="age='elder'" class="choice">
                            <img class="imgSelection choice-img" src="../assets/elderDog.png" v-if="dog"/>
                            <img class="imgSelection choice-img" src="../assets/elderCat.png" v-else/>
                            <div class="vertical-center text-Century-Gothic choice_text" v-if="dog">Пожилые(7+)</div>
                            <div class="vertical-center text-Century-Gothic choice_text" v-else>Пожилые коты</div>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                </template>
                <template v-else>
                    <v-layout class="items_content_layout" justify-space-around row wrap style="padding-top: 1.5%">
                        <v-flex md3>
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
                                    v-for="item in sortedItems"/>
                        </v-layout>
                        <v-flex md3>
                            <div class="text-xs-center">
                                <v-pagination
                                        text-Century-Gothic
                                        circle
                                        color="#3f6b33"
                                        :length="totalPages"
                                        @input="next"
                                        v-model="currentPage"
                                ></v-pagination>
                            </div>
                        </v-flex>
                    </v-layout>
                </template>
            </template>
        </template>
    </v-container>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import ItemRow from "../components/items/ItemRow.vue"
    import ItemView from "../components/items/ItemView.vue"

    export default {
        name: "Catalog",
        components: {
            ItemRow,
            ItemView
        },
        data() {
            return {
                path: null,
                type: null,
                size: null,
                age: null,
                dog: false,
                brandFilter: [],
                searchText: null,
                currentPage: 1,
                totalPages: null,
                dogfeedTypeList: null,
                catfeedTypeList: null,
            }
        },
        methods:
            {
                ...mapActions(['getPageAction', "loadCertainPage", "loadPageAction"]),
                getPage() {
                    let pet = '';
                    if (this.dog) {
                        pet = 'dog'
                    } else {
                        pet = 'cat'
                    }
                    this.path = pet;
                    if (this.type != null && "" !== this.type) {
                        this.path = this.path + '-' + this.type;
                        if (this.age != null && "" !== this.age) {
                            this.path = this.path + '-' + this.age;

                        }
                    }
                    // this.$store.dispatch('loadPage', this.path)
                    this.loadCertainPage(this.path, this.company);
                    console.log(this.$route.path);
                },
                next(page) {
                    page = page - 1
                    const path = this.path + "?page=" + page
                    this.loadPageAction(path, this.company);
                    document.getElementById('content').scrollIntoView({
                        behavior: 'smooth'
                    });
                },
                showAll() {
                    this.type = "any";
                    this.size = "any";
                    this.age = "any";
                },
                resolvePath() {
                    this.$router.replace({
                        path: '/catalog',
                        query: {dog: this.dog, type: this.type, size: this.size, age: this.age, page: this.currentPage}
                    })
                },
                search() {
                    if (this.searchText !== '') {
                        this.$router.push('/search?search=' + this.searchText)
                        // this.$router.go(0);
                    }
                },
                switchToCat() {
                    if (this.dog) {
                        this.$router.push('/catalog?dog=false');
                        this.$router.go(0)
                    }
                },
                switchToDog() {
                    if (!this.dog) {
                        this.$router.push('/catalog?dog=true');
                        this.$router.go(0)

                    }
                }
            },
        computed: {
            // ...mapGetters(["dogfeedTypeList", "catfeedTypeList"]),
            sortedItems: function () {
                let filter = "cat";
                if (this.dog) {
                    filter = "dog";
                }
                let items = this.$store.state.items.filter(item => filter === item.pet);
                if (this.type != null && this.type !== 'any') {
                    items = items.filter(item => this.type === item.type || "any" === item.type)
                }
                if (this.size != null && this.size !== 'any') {
                    items = items.filter(item => this.size === item.size || "any" === item.size)
                }
                if (this.age != null && this.age !== 'any') {
                    items = items.filter(item => this.age === item.age || "any" === item.age)
                }
                let brand = new Set()
                if (this.dog) {
                    items.forEach(item => {
                        brand.add(item.company)
                    });
                    this.dogfeedTypeList = brand;
                } else {
                    items.forEach(item => {
                        brand.add(item.company)
                    });
                    this.catfeedTypeList = brand;
                }
                if (this.brandFilter.length !== 0) {
                    let brandItems = [];
                    this.brandFilter.forEach(brand => {
                        brandItems = brandItems.concat(items.filter(item => item.company === brand))
                    });
                    items = brandItems;
                }
                this.totalPages = Math.ceil(items.length / 10);
                items = items.sort((a, b) => -(a.id - b.id));
                const fIndex = (this.currentPage - 1) * 10;
                const lIndex = fIndex + 10 < items.length ? fIndex + 10 : items.length;
                return items.slice(fIndex, lIndex);
            }

        },
        watch: {
            type: function () {
                this.resolvePath()
            },
            size: function () {
                this.resolvePath()
            },
            age: function () {
                this.resolvePath()
            },
            dog: function () {
                this.resolvePath()
            },
            currentPage: function () {
                document.getElementById('content').scrollIntoView({
                    behavior: 'smooth'
                });
                this.resolvePath()
            },
            brandFilter: function () {
                this.resolvePath()
            }
        },
        mounted() {
            this.dog = this.$route.query.dog === 'true';
            this.type = this.$route.query.type;
            this.size = this.$route.query.size;
            this.age = this.$route.query.age;
            this.currentPage = this.$route.query.page > 1 ? parseInt(this.$route.query.page) : 1;
        },
        beforeMount() {
        },
    }
</script>

<style scoped>
    @media only screen and (min-width: 960px) {
        .feed_filter {
            text-transform: none;
        }
        .choice_text {
            font-size: 22px;
        }

        .content {
            max-width: 1200px;
            margin-top: 48px;
            margin-bottom: 48px;
        }

        .padding-show-all {
            margin: 0 48% 0 0 !important;
        }

        .formReg {
            max-width: 1200px;
        }

        .imgSelection {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 236px;
        }

        .pets {
            width: 145px;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
            position: absolute;
            height: 6vh;
        }

        .pets_layout {
            margin-bottom: 6vh;
        }

        .position_layout {
            position: relative;
            z-index: 0;
            padding-top: 80px;

            background-color: white;
            padding-bottom: 150px;
        }

        .vertical-center {
            padding: 22px 0;
            text-align: center;
        }

        .choice_layout {
            padding-top: 40px;
            position: relative;
            z-index: 0;
            background-color: white;
        }

        .items_layout {
            padding: 0 30px;
        }

        .items_content_layout {
            background-color: white;

        }
    }

    @media only screen and (max-width: 960px) {

        .position_layout {
            position: relative;
            z-index: 0;

            background-color: white;
            padding-bottom: 150px;
            padding-top: 40px
        }

        .vertical-center {
            padding: 15px 0;
            text-align: center;
        }

        .imgSelection {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 50%;
        }

        .text-Sitka {
            font-family: "Sitka", Sitka !important;
        }

        .pets {
            height: 96%;
            width: 9vh;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
        }

        .choice {
            padding-top: 8%;
            padding-bottom: 2%;
        }

        .choice-img {
            width: 30%;
        }

        .feed_filter {
            text-transform: capitalize;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
        }

        .v-menu__content {
            max-width: 100%;
        }

        .v-menu__content .theme--light .menuable__content__active {
            left: 0px;
        }

        .button.v-pagination__item {
            border-radius: 35px !important;
        }

        .pagination_class {
            border-radius: 35px;
        }

        .pets_layout {
            background-color: white;
            padding-top: 3vh;
            padding-bottom: 2vh
        }

        .choice_layout {
            background-color: white;
            box-shadow: 0 5px 8px -6px #888888;
        }
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }
</style>