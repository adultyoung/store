<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-app v-bind:style="{'background-image': 'url(' + require('./assets/background.png') + ')'}">
        <lazy-loader v-if="this.$route.path === '/' || $vuetify.breakpoint.mdAndUp"></lazy-loader>
        <v-toolbar flat fixed promiment height="101px" v-if="$vuetify.breakpoint.smAndDown" class="navBar"
                   style="background: white">
            <v-layout align-center class="pad_class">
                <v-flex sm1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs3 sm2>
                    <v-toolbar-side-icon @click.stop="drawer = !drawer">
                        <v-icon style="color: black" class="header_icon" size="36">dehaze</v-icon>
                    </v-toolbar-side-icon>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex sm1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs6 sm4 style="cursor: pointer">
                    <router-link to="/" style="color:black"
                                 class="titleClass">
                        <v-layout>
                            <v-flex xs3 style="color: black" class="text-xs-right doghouse_class">
                                DOG
                            </v-flex>
                            <v-flex xs4>
                                <img style="width: 100%" src="./assets/gabi-header.png"/>
                            </v-flex>
                            <v-flex xs4 style="color: black" class="text-xs-left doghouse_class">
                                HOUSE
                            </v-flex>
                        </v-layout>
                    </router-link>
                </v-flex>
                <v-flex sm1>
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs1>
                    <v-btn class="header_icon"
                           icon
                           :to="this.$store.state.profile != null ? '/profile/settings' : '/'"
                    >
                        <v-icon style="color: black" size="36">perm_identity</v-icon>
                    </v-btn>
                </v-flex>
                <v-flex xs1 style="margin-right: 5px">
                    <v-btn style="padding: 0; left: 15px"
                           icon
                           class="header_icon"
                           to="/basket"
                    >
                        <!--                        <v-badge>-->
                        <!--                        <template slot="badge">{{basket.items.length}}</template>-->
                        <img id="basket_icon"
                             style="width: 85%"
                             src="./assets/shopping_cart.png"/>
                        <!--                        </v-badge>-->
                    </v-btn>
                </v-flex>
                <v-flex xs1>
                    <v-spacer></v-spacer>
                </v-flex>
            </v-layout>
        </v-toolbar>

        <template v-if="$vuetify.breakpoint.mdAndUp">
            <v-btn icon @click="toTop" class="toTop">
                <v-icon style="line-height: 0px; font-size: 46px;">keyboard_arrow_up</v-icon>
            </v-btn>
            <p class="text-xs-center text-Century-Gothic" style="background: #585858; margin: 0"
               v-show="$vuetify.breakpoint.mdAndUp">
                <a href="tel: +375(29)270-25-44" style="text-decoration: none; color: white;">+375 (29) 270-25-44</a>
            </p>

            <v-toolbar id="toolbar" flat
                       style="background: white; height: 268px; padding: 0 10%; transition: none !important;"
                       extended
                       v-show="$vuetify.breakpoint.mdAndUp"
            >
                <v-spacer></v-spacer>
                <v-toolbar-title style="padding-top: 54px">
                    <router-link to="/" style="text-decoration: none; color: black; font-size: 50px"
                                 class="titleClass">
                        <v-layout row wrap>
                            <v-flex xs12 style="margin-top: 36px">
                                <img
                                        style="width: 31%; display: block; padding-top: 82px; margin-left: auto; margin-right: auto"
                                        src="./assets/gabi-header.png"/>
                                <img src="./assets/NY_version.png"
                                     style="position: absolute; width: 145px; top: 16px; margin-left: 143px; margin-top: 18px">
                            </v-flex>
                            <v-flex xs12>
                                <v-layout>
                                    <v-spacer></v-spacer>
                                    <v-flex>
                                        <v-flex xs5 style="text-align: right; margin-top: -9%; font-size: 46px">DOG
                                            HOUSE
                                        </v-flex>
                                        <v-spacer></v-spacer>
                                    </v-flex>
                                </v-layout>
                            </v-flex>
                        </v-layout>
                    </router-link>
                </v-toolbar-title>
                <v-spacer></v-spacer>

            </v-toolbar>
            <v-layout row wrap align-center justify-space-around id="sticky_header"
                      style="background:white; height: 66px">
                <v-flex xs7>
                    <v-layout row>
                        <v-spacer></v-spacer>
                        <v-flex xs7>
                            <v-layout class="text-Century-Gothic">
                                <v-flex xs3>
                                    <v-btn flat class="text-Century-Gothic header_btn" to="/catalog?dog=true">
                                        Каталог
                                    </v-btn>
                                </v-flex>
                                <v-flex xs3>
                                    <v-btn flat class="text-Century-Gothic header_btn" to="/terms">Доставка
                                    </v-btn>
                                </v-flex>
                                <v-flex xs3>
                                    <v-btn flat class="text-Century-Gothic header_btn" to="/terms">Оплата
                                    </v-btn>
                                </v-flex>
                                <v-flex xs3>
                                    <v-btn flat class="text-Century-Gothic header_btn" to="/about">О нас
                                    </v-btn>
                                </v-flex>
                                <v-flex xs3>
                                    <v-btn flat class="text-Century-Gothic header_btn" to="/contacts">Контакты
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs2>
                    <v-text-field v-model="searchText"
                                  clearable style="padding: 0" @keyup.enter.native="search"
                                  placeholder="Поиск">
                    </v-text-field>
                </v-flex>
                <v-flex xs1>
                    <v-btn flat @click="search" type="submit" icon>
                        <v-icon>search</v-icon>
                    </v-btn>
                </v-flex>
                <v-flex xs2>
                    <v-btn class="header_icon"
                           icon
                           :to="$store.state.profile != null ? '/profile/settings' : '/'"
                    >
                        <v-icon size="36">perm_identity</v-icon>
                    </v-btn>
                    <v-btn style="padding: 0"
                           to="/basket"
                           flat
                    >
                        <!--                        <v-badge>-->
                        <!--                        <template slot="badge">{{basket.items.length}}</template>-->
                        <img
                                src="./assets/shopping_cart.png"/>
                        <!--                        </v-badge>-->
                    </v-btn>
                </v-flex>
            </v-layout>
        </template>

        <v-expand-transition style="" v-if="$vuetify.breakpoint.smAndDown">
            <v-list v-show="drawer" id="expand_menu"
                    :style="this.$route.path === '/' ? 'background: white; width: 100%; position: fixed; z-index: 1; margin-top: 102px; padding: 13% 0'
                     : 'background: white; width: 100%; position: fixed; z-index: 1; margin-top: 102px; padding: 13% 0'">
                <v-btn class="btnHeader text-Century-Gothic"
                       flat
                       to="/catalog?dog=true"
                       @click="drawer = !drawer"
                       :style="this.$route.path === '/' ? 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px' : 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px'"
                >
                    Каталог
                </v-btn>
                <v-btn class="btnHeader text-Century-Gothic"
                       flat
                       to="/catalog"
                       @click="drawer = !drawer"
                       :style="this.$route.path === '/' ? 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px' : 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px'"
                >
                    О нас
                </v-btn>
                <v-btn class="btnHeader text-Century-Gothic"
                       flat
                       to="/catalog"
                       @click="drawer = !drawer"
                       :style="this.$route.path === '/' ? 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px' : 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px'"
                >
                    Контакты
                </v-btn>
                <v-btn class="btnHeader text-Century-Gothic"
                       flat
                       to="/catalog"
                       @click="drawer = !drawer"
                       :style="this.$route.path === '/' ? 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px' : 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px'"
                >
                    Доставка и оплата
                </v-btn>
                <v-btn class="btnHeader text-Century-Gothic"
                       flat
                       to="/catalog"
                       @click="drawer = !drawer"
                       :style="this.$route.path === '/' ? 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px' : 'width: 100%; color: black; height: auto; text-transform: capitalize; font-size: 18px'"
                >
                    Публичный договор
                </v-btn>
            </v-list>
        </v-expand-transition>

        <v-content id="content"
                   :style="this.$route.path === '/' && $vuetify.breakpoint.smAndDown ? 'margin:0' : 'margin-top: 101px'">
            <router-view :key="$route.path"></router-view>
        </v-content>


        <v-footer class="footerStyle"
                  height="auto"
        >
            <v-container class="text-xs-center" v-show="$vuetify.breakpoint.smAndDown">
                <v-layout row wrap>
                    <v-flex xs12>
                        <p class="titleClass" style="margin-bottom: 0;font-size: 48px; color: white;">DOG HOUSE</p>
                    </v-flex>
                    <v-flex xs12>
                        <v-icon color="white">stay_current_portrait</v-icon>&nbsp;&nbsp;&nbsp;
                        <a href="tel: +375(29)270-25-44"
                           style="text-decoration: none; color: white; vertical-align: text-bottom">+375 (29)
                            270-25-44</a>
                    </v-flex>
                    <v-flex xs12>
                        <v-icon color="white">mail</v-icon>&nbsp;&nbsp;&nbsp;
                        <a href="mailto: support@doghouse.by"
                           style="text-decoration: none; color: white; vertical-align: text-bottom">support@doghouse.by</a>
                    </v-flex>
                    <v-flex xs12>
                        <img align="center" style="width: 40px;height: 40px;"
                             src="./assets/instagram.png"
                             class="socialNetwork"/>
                        <img align="center" style="width: 50px;height: 50px"
                             src="./assets/vk.png"
                             class="socialNetwork"/>
                    </v-flex>
                    <v-flex xs12>
                        <div style="color: white;">
                            {{ new Date().getFullYear() }} — <strong>©Dog House. </strong> Все права защищены.
                        </div>
                    </v-flex>
                </v-layout>
            </v-container>

            <v-container v-show="$vuetify.breakpoint.mdAndUp" style="padding-bottom: 0px">
                <v-layout row wrap style="padding-bottom: 1%">
                    <v-flex xs12>
                        <v-layout>
                            <v-spacer></v-spacer>
                            <v-flex xs6>
                                <v-layout align-center>
                                    <v-flex xs4 class="text-Century-Gothic text-xs-center">
                                        <router-link to="/public" style="color: white; text-decoration: none">Публичный
                                            договор
                                        </router-link>
                                    </v-flex>
                                    <v-divider vertical color="white"></v-divider>
                                    <v-flex xs4 class="text-Century-Gothic text-xs-center">
                                        <router-link to="/" style="color: white; text-decoration: none">
                                            Главная
                                        </router-link>
                                    </v-flex>
                                    <v-divider vertical color="white"></v-divider>
                                    <v-flex xs4 class="text-Century-Gothic text-xs-center">
                                        <router-link to="/contacts" style="color: white; text-decoration: none">
                                            Бренд бук
                                        </router-link>
                                    </v-flex>
                                </v-layout>
                            </v-flex>
                            <v-spacer></v-spacer>
                        </v-layout>
                    </v-flex>
                    <v-flex xs4>
                        <v-container style="padding:0">
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <img src="./assets/logo-alexhur.png" align="center"
                                         style="height: 150px; object-fit: contain; padding-left: 5%"/>
                                </v-flex>
                                <v-flex xs12>
                                    <p style="color: white; font-size: 14px" class="text-Century-Gothic">
                                        ООО "Управляющая компания "Твое дело" <br>
                                        Юр. адрес: 220033, г. Минск, Партизанский <br> пр-т, 23-А, пом.1Н, офис 28А,
                                        <br>
                                        В торговом реестре: <br> №465197, 11 ноября 2019 г., УНП: 391562593</p>
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-flex>
                    <v-flex xs4>
                        <div class="text-xs-center" style="color: white; padding-top: 75px">
                            <p class="text-Century-Gothic text-xs-center" style="font-size: 20px">
                                Мы в социальных сетях
                            </p>
                            <v-layout align-center>
                                <v-spacer></v-spacer>
                                <v-flex xs3>
                                    <img align="center" style="color: white; width: 40px;height: 40px;"
                                         src="./assets/instagram.png"
                                         class="socialNetwork"/>
                                </v-flex>
                                <v-flex xs3>
                                    <img align="center" style="color: white; width: 50px;height: 50px"
                                         src="./assets/vk.png"
                                         class="socialNetwork"/>
                                </v-flex>
                                <v-spacer></v-spacer>
                            </v-layout>
                        </div>
                    </v-flex>
                    <v-flex xs4>
                        <v-container text-xs-center style="padding-top: 60px; margin-left: 40px; padding-right: 0">
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-layout>
                                        <v-flex>
                                            <v-icon color="white" size="30">stay_current_portrait</v-icon>
                                            <a href="tel: +375(29)270-25-44" class="text-Century-Gothic"
                                               style="text-decoration: none; color: white; font-size: 20px; vertical-align: top">+375
                                                (29)
                                                270-25-44
                                            </a>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs12 style="margin-bottom: 29px">
                                    <v-layout>
                                        <v-flex>
                                            <v-icon color="white" size="30">mail</v-icon>&nbsp;
                                            <a href="mailto: support@doghouse.by" class="text-Century-Gothic"
                                               style="text-decoration: none; color: white; font-size: 19px; vertical-align: top">support@doghouse.by
                                            </a>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs12>
                                    <v-layout>
                                        <v-flex>
                                            <img src="./assets/paymentMethods.png"/>
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-footer>
        <div class="text-xs-center text-Century-Gothic" style="background: white; height: auto; font-size: 16px"
             v-show="$vuetify.breakpoint.mdAndUp">
            ©{{ new Date().getFullYear() }}. Все права защищены
        </div>
    </v-app>
</template>

<script>

    import Login from "./pages/Login.vue"
    import {mapActions, mapGetters} from 'vuex'
    import LazyLoader from "./components/LazyLoader.vue"

    export default {
        name: "App",
        components: {
            Login,
            LazyLoader
        },
        computed: {
            ...mapGetters(['profile', 'basket']),
        },
        data: () => ({
            searchText: '',
            dialog: false,
            drawer: false,
            catalog: false,
        }),
        methods: {
            ...mapActions(['searchItem']),
            async dogs() {
                const data = await this.$store.dispatch('loadCertainPage', 'pets/dog');
                this.$router.push("/pets/dog");
            },
            async cats() {
                const data = await this.$store.dispatch('loadCertainPage', 'pets/cat');
                this.$router.push("/pets/cat");
            },
            search() {
                if (this.searchText !== '') {
                    this.$router.push('/search?search=' + this.searchText)
                    // this.$router.go(0);
                }
            },
            openCards() {
                this.classList.toggle("active");
                var content = this.nextElementSibling;
                if (content.style.display === "block") {
                    content.style.display = "none";
                } else {
                    content.style.display = "block";
                }
            },
            toTop() {
                document.getElementById('toolbar').scrollIntoView({
                    behavior: 'smooth'
                });
            }
        },

    }
</script>

<style scoped>
    @font-face {
        font-family: "Playbill";
        src: url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.eot");
        src: url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.eot?#iefix") format("embedded-opentype"), url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.woff2") format("woff2"), url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.woff") format("woff"), url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.ttf") format("truetype"), url("./pages/fonts/13df2824b3f7c3ee824795faff222e15.svg#Playbill") format("svg");
    }

    @font-face {
        font-family: "Century Gothic";
        src: url("./pages/fonts/GOTHIC_745063ed9142edcdcdfaeed69c36e6d2.ttf") format("truetype")
    }

    @media only screen and (max-width: 960px) {
        #content {
            padding: 0;
            margin-top: 48px
        }

        v-toolbar__content {
            height: 101px !important;
        }

        .pad_class {
            padding-top: 5%;
        }

        .doghouse_class {
            padding-top: 7%;
        }
    }

    @media only screen and (min-width: 960px) {
        #content {
            padding: 0;
            margin: 0 !important;
        }

        .header_btn {
            text-transform: capitalize;
            font-size: 16px;
        }
    }

    #siteMap {
        margin-top: -5px;
        border-radius: 25px;
        border: 2px solid #006666;
        padding: 20px;
        width: 100%;
        height: 200px;
    }

    .paymentMethod {
        object-fit: contain;
    }

    .titleClass {
        text-decoration: none;
        color: black;
        font-size: 36px;
        font-family: 'Playbill', arial !important;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
    }

    .socialNetwork {
        object-fit: contain;
        filter: invert(100%);
    }

    .footerStyle {
        background: rgba(63, 107, 51, 1)
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

    .vertical-center {
        padding: 70px 0;
        text-align: center;
    }

    .text-Sitka {
        font-family: "Sitka", Sitka;
    }

    .navBar:after {
        content: ""; /* This is necessary for the pseudo element to work. */

        display: block; /* This will put the pseudo element on its own line. */
        margin: 0 auto; /* This will center the border. */
        width: 90%; /* Change this to whatever width you want. */
        /* This creates some space between the element and the border. */
        border-bottom: 1px solid black; /* This creates the border. Replace black with whatever color you want. */
    }

    .btnHeader {
        width: 50%;
    }

    .v-btn--active:before, .v-btn:focus:before, .v-btn:hover:before {
        background-color: transparent;
    }

    .sticky {
        position: fixed;
        top: 0;
        width: 100%;
        z-index: 2;
        height: 66px;
    }

    .large_header {
        height: 334px !important;
    }

    .toTop {
        display: none; /* Hidden by default */
        position: fixed; /* Fixed/sticky position */
        width: 46px;
        height: 46px;
        background: #3f6b33;
        bottom: 80px; /* Place the button at the bottom of the page */
        right: 70px; /* Place the button 30px from the right */
        z-index: 99; /* Make sure it does not overlap */
        color: white; /* Text color */
        cursor: pointer; /* Add a mouse pointer on hover */
    }

</style>