<template>
    <v-app>
        <v-toolbar app absolute flat style="background: ghostwhite">
            <v-spacer></v-spacer>
            <v-toolbar-title>
                <img class="phoneImg" src="./assets/mts.jpg" style="object-fit: contain" height="25"/>
                <a href="tel:+375336635095" style="text-decoration: none; color: brown; font-size: 125%">
                    +375336635095
                </a>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-flex text-xs-center>
                <img class="largeLogo" id="Logo" src="./assets/Logo.png" height="75px"/>
            </v-flex>
            <v-spacer></v-spacer>
            <v-toolbar-title>
                <img class="phoneImg" src="./assets/mts.jpg" height="25"/>
                <a href="tel:+375256499702" style="text-decoration: none; color: brown; font-size: 125%">
                    +375256499702
                </a>
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>
                <v-container class="mt-5" style="background: #ffffff">
                    <v-layout row wrap>
                        <v-flex xs3>
                            <v-layout row wrap>
                                <v-spacer></v-spacer>
                                <v-flex xs7>
                                    <p class="title text-xs-center">Мы работаем</p>
                                    <p class="headline text-xs-center">c 9:00 до 21:00</p>
                                </v-flex>
                                <v-spacer></v-spacer>
                            </v-layout>
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs3>
                            <v-layout row wrap>
                                <v-spacer></v-spacer>
                                <v-flex xs7>
                                    <p class="display-1 text-xs-center">DOG HOUSE</p>
                                </v-flex>
                                <v-spacer></v-spacer>
                            </v-layout>
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs3>
                            <v-layout row wrap>
                                <v-flex xs7>
                                    <p class="title text-xs-center">Доставка</p>
                                    <v-list>
                                        <v-list-tile-content>
                                            <v-list-tile>Самовывоз</v-list-tile>
                                            <v-list-tile-sub-title>м. Академия наук, улю Чернышевского, 3
                                            </v-list-tile-sub-title>
                                        </v-list-tile-content>
                                        <v-divider></v-divider>
                                        <v-list-tile-content>
                                            <v-list-tile>Курьером</v-list-tile>
                                            <v-list-tile-sub-title>при заказе от 100 р. - бесплатно</v-list-tile-sub-title>
                                        </v-list-tile-content>
                                    </v-list>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-container>
        <v-container style="margin-top: 50px">
            <v-layout row>
                <v-flex xs1>
                    <v-menu disabled
                            close-on-content-click
                            close-on-click
                            offset-y
                    >
                        <v-btn slot="activator"
                               flat
                        >
                            Коты
                            <v-icon>arrow_drop_down</v-icon>
                        </v-btn>
                        <v-list>
                            <v-list-tile>
                                <v-list-tile-title>
                                    <v-menu
                                            open-on-hover
                                            close-on-content-click
                                            close-on-click
                                            offset-x
                                    >
                                        <v-btn slot="activator"
                                               flat
                                               to="/cat/food"
                                        >
                                            Корм
                                        </v-btn>
                                        <v-list>
                                            <v-list-tile>
                                                <v-list-tile-title>
                                                    <v-btn slot="activator"
                                                           flat
                                                           to="/cat/food?=Brit"
                                                    >
                                                        Brit
                                                    </v-btn>
                                                </v-list-tile-title>
                                            </v-list-tile>
                                        </v-list>
                                    </v-menu>
                                </v-list-tile-title>
                            </v-list-tile>
                        </v-list>
                    </v-menu>
                </v-flex>
                <v-flex xs1>
                    <v-btn disabled
                           flat
                           to="/dogs"
                    >
                        Собаки
                        <v-icon>arrow_drop_down</v-icon>
                    </v-btn>
                </v-flex>
                <v-flex xs3>
                    <v-btn
                            color="error"
                            to="/"
                    >
                        <div class="headline">ПОЛУЧИТЬ СКИДКУ</div>
                    </v-btn>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs2>
                    <v-text-field v-model="searchText"
                                  clearable style="padding: 0" placeholder="Поиск"></v-text-field>
                </v-flex>
                <v-flex xs1>
                    <v-btn flat @click="search" type="submit" icon>
                        <v-icon>search</v-icon>
                    </v-btn>
                </v-flex>
                <v-flex xs1>
                    <login></login>
                    <v-btn
                            icon
                            to="/basket"
                    >
                        <v-icon>shopping_cart</v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
        </v-container>
        <v-content style="background: floralwhite">
            <router-view></router-view>
        </v-content>
        <v-footer
                absolute
                class="font-weight-medium"
        >
            <v-container fluid grid-list-xs text-xs-center style="padding-left: 0; padding-right: 0; padding-bottom: 0">
                <v-layout row wrap class="mt-4">
                    <v-flex d-flex xs3 style="padding-left: 24px">
                        <v-layout row>
                            <v-flex xs4>
                                <v-layout row wrap>
                                    <v-flex d-flex xs12>
                                        <img src="./assets/tvoe_logo.png" height="100"
                                             style="margin-top: 5px; object-fit: contain">
                                    </v-flex>
                                    <v-flex d-flex xs12 >
                                        <img src="./assets/logo-alexhur.jpg" height="100"
                                             style="margin-bottom: 15px; object-fit: contain">
                                    </v-flex>
                                </v-layout>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs4>
                        <v-layout row wrap>
                            <v-flex d-flex xs12 id="siteMap">
                            </v-flex>
                            <v-layout row style="margin-bottom: 5px">
                                <v-spacer></v-spacer>
                                <v-flex d-flex xs6>
                                    <img class="paymentMethod" src="./assets/visa.png"/>
                                    <img class="paymentMethod" src="./assets/mastercard.png"/>
                                    <img class="paymentMethod" src="./assets/visa-electron.png"/>
                                    <img class="paymentMethod" src="./assets/maestro.png"/>
                                    <img class="paymentMethod" src="./assets/american-express.png"/>
                                </v-flex>
                                <v-spacer></v-spacer>
                            </v-layout>
                        </v-layout>

                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs4 style="padding-right: 24px">
                        <v-layout row wrap>
                            <v-spacer></v-spacer>
                            <v-flex d-flex xs1>
                                <img src="./assets/instagram_ico.png"
                                     style="width: 10px; height: 70%; object-fit: contain;">
                            </v-flex>
                            <v-flex d-flex xs1>
                                <img src="./assets/facebook_ico.png"
                                     style="width: 10px; height: 70%; object-fit: contain;">
                            </v-flex>
                            <v-flex d-flex xs1>
                                <img src="./assets/twitter_ico.png"
                                     style="width: 10px; height: 70%; object-fit: contain;">
                            </v-flex>
                            <v-flex d-flex xs12>
                                <p style="text-align: right; margin-top: 50px">
                                    ООО "Управляющая компания "Твое дело"
                                    <br>
                                    Юр. адрес: 220033, г. Минск, Партизанский
                                    <br>
                                    пр-т, 23-А, пом.1Н, офис 28А
                                    <br>
                                    В торговом реестре:
                                    <br>
                                    ****************, УНП 391562593
                                </p>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex xs12 style="background: darkslategray; color: white; text-align: left; padding: 0">
                        {{ new Date().getFullYear() }} — <strong>Dog House. </strong> Все права защищены.
                    </v-flex>
                </v-layout>
            </v-container>

        </v-footer>
    </v-app>
</template>

<script>

    import Login from "./pages/Login.vue"
    import {mapActions} from 'vuex'

    export default {
        name: "App",
        components: {
            Login,
        },
        data: () => ({
            searchText: '',
            dialog: false,
        }),
        methods: {
            ...mapActions(['searchItem']),
            search() {
                this.$store.dispatch('searchItem', {text: this.searchText})
            }
        }
    }
</script>

<style scoped>
    .phoneImg {
        border-radius: 50%;
    }

    .largeLogo {
        border-radius: 50%;
        height: 75px;
    }

    .smallLogo {
        border-radius: 50%;
        height: 50px;
    }

    #Logo {
        transition: all 0.5s;
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
</style>