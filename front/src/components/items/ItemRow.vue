<template>
    <v-flex xs6 md4 md3>
        <v-card class="item_card">
            <div @click="dialog = !dialog">
                <div style="height: 196px">
                    <img style="display: block; padding: 2% 0" :src="imgOfItem" class="itemCardImg">
                    </img>
                </div>
                <v-divider style="margin: 0 15%"></v-divider>
                <div class="card_title mb-0 text-Century-Gothic text-xs-center divider_title">
                    {{item.title}}
                </div>
                <v-card-title class="card_description text-Century-Gothic text-xs-center">
                    {{item.description}}
                </v-card-title>
                <template v-if="priceOfItem !== null">
<!--                    <v-card-title class="text-Century-Gothic price divider_price"-->
<!--                                  style="text-decoration: line-through; color: grey; font-size: 12px; padding-left: 15px">-->
<!--                        {{priceOfItem * 1.2}} руб-->
<!--                    </v-card-title>-->
                    <v-card-title class="text-Century-Gothic price" style="padding: 17px; font-size: 16px;">
                        {{parseFloat(priceOfItem).toFixed(2)}} руб
                    </v-card-title>
                </template>
                <template v-else>
                    <div style="padding: 17px" class="text-Century-Gothic">
                        Цену уточняйте
                    </div>
                </template>
            </div>
            <v-card-actions style="padding: 2px 5px;">
                <v-layout row wrap>
                    <v-flex xs8 style="padding-left: 10px">
                        <v-select style="padding-top: 5px"
                                  dense
                                  hide-details
                                  height="30"
                                  :items="sizesOfItems"
                                  v-model="sizeOfItem"
                                  outline
                        ></v-select>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs3 style="padding-right: 10px">
                        <v-btn flat icon @click="addToCart(item)">
                            <v-icon outlined :id=item.id>add_shopping_cart</v-icon>
                        </v-btn>
                    </v-flex>
                </v-layout>
            </v-card-actions>
<!--            <div class="card_sale">-->
<!--                <span class="text-xs-center">-20%</span>-->
<!--            </div>-->
        </v-card>
        <v-dialog v-model="dialog" class="dialog_xs" v-if="$vuetify.breakpoint.smAndDown">
            <v-card style="padding: 5% 5%">
                <div class="arrow-back" @click="dialog=false">
                    <v-btn flat icon>
                        <v-icon color="#3f6b33">clear</v-icon>
                    </v-btn>
                </div>
                <v-responsive style="padding: 0 7%">
                    <div style="height: 250px">
                        <img :src="imgOfItem" class="itemCardImg"
                             style="display:block; max-height: 250px; height: 250px">
                        </img>
                    </div>
                    <div class="card_title mb-0 text-Century-Gothic text-xs-center">
                        {{item.title}}
                    </div>
                    <p style="text-align: right; margin: 0; font-size: 16px; color: red;"
                       class="text-Century-Gothic">В наличии</p>
                    <p style="text-align: right; margin: 0; font-size: 14px; color: grey"
                       class="text-Century-Gothic">Артикул: <br> {{item.id}}</p>
                </v-responsive>
                <v-card-title style="padding: 0 7%; text-align: left"
                              class="card_description text-Century-Gothic text-xs-center">
                    <v-layout style="margin-bottom: 3%">
                        <v-flex xs6>
                            <div style="text-align: left" class="text-Century-Gothic">
                                {{item.description}}
                            </div>
                        </v-flex>
                        <v-flex xs6>
                            <div style="text-align: right; padding: 24px 0; font-size: 20px; color: black;"
                                 class="text-Century-Gothic">
                                <template v-if="priceOfItem !== null">
                                    {{parseFloat(priceOfItem*item.quantity).toFixed(2)}} <sup>руб.</sup>
                                </template>
                                <template v-else>
                                    Цену уточняйте
                                </template>
                            </div>
                        </v-flex>
                    </v-layout>
                </v-card-title>
                <a class="text-Century-Gothic" style="padding: 1% 7%; font-size: 12px; color: #3f6b33;"
                   @click="getFullDescription">
                    <template v-if="!fullDescription">
                        Полное описание
                    </template>
                    <template v-else>
                        Скрыть полное описание
                    </template>
                </a>
                <v-card-actions style="padding: 0 7%; margin-top: 5%">
                    <v-layout row wrap>
                        <v-flex xs7>
                            <v-select style="padding-top: 5px"
                                      dense
                                      hide-details
                                      height="40"
                                      :items="sizesOfItems"
                                      v-model="sizeOfItem"
                                      outline
                            ></v-select>
                        </v-flex>
                        <v-flex xs5 style="padding-right: 10px">
                            <v-layout row>
                                <v-spacer></v-spacer>
                                <v-flex xs1 @click="item.quantity -= 1"
                                        style="text-align: right; font-size: 18px; margin-right: 5%"
                                        class="text-Century-Gothic">-
                                </v-flex>
                                <v-flex xs2><p class="text-xs-center text-Century-Gothic" style="font-size: 18px">
                                    {{item.quantity}} <br> шт</p></v-flex>
                                <v-flex xs1 @click="item.quantity += 1" class="text-Century-Gothic"
                                        style="font-size: 18px; margin-left: 2%">+
                                </v-flex>
                                <v-flex xs1>
                                    <v-spacer></v-spacer>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-card-actions>
                <v-btn block color="#3f6b33" @click="addToCart(item)">
                    <div class="text-Century-Gothic" style="color: white; text-transform: capitalize;">
                        В корзину
                    </div>
                </v-btn>
                <v-expand-transition>
                    <v-card-text id="features" v-show="fullDescription">
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Особенности:</p>
                            {{item.fullDescription[0].features}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Описание:</p>
                            {{item.fullDescription[0].fullDescription}}
                        </div>
                        <div class="text-Century-Gothic fullDescription">
                            <p class="title" style="font-weight: bold">Состав:</p>
                            {{item.fullDescription[0].consists}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Пищевые добавки:</p>
                            {{item.fullDescription[0].additives}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Анализ:</p>
                            {{item.fullDescription[0].analysis}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Микроэлементы:</p>
                            {{item.fullDescription[0].microelements}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Дополнительно:</p>
                            {{item.fullDescription[0].additional}}
                        </div>
                    </v-card-text>
                </v-expand-transition>
            </v-card>
        </v-dialog>
        <v-dialog v-model="dialog" class="dialog_xs" v-if="$vuetify.breakpoint.mdAndUp">
            <v-card style="padding: 5% 5%">
                <div class="arrow-back" @click="dialog=false">
                    <v-btn flat icon>
                        <v-icon color="#3f6b33">clear</v-icon>
                    </v-btn>
                </div>
                <v-layout row wrap>
                    <v-flex xs5>
                        <div style="height: 300px; padding-top: 15%">
                            <img :src="imgOfItem" class="itemCardImg"
                                 style="display:block; height: 300px; max-height: 300px;">
                            </img>
                        </div>
                    </v-flex>
                    <v-flex xs7>
                        <v-responsive>
                            <div class="card_title mb-0 text-Century-Gothic"
                                 style="text-align: left; font-size: 20px; overflow: visible">
                                {{item.title}}
                            </div>
                            <p style="text-align: right; margin: 0; font-size: 18px; color: red;"
                               class="text-Century-Gothic">В наличии</p>
                            <p style="text-align: right; margin: 0; font-size: 16px; color: grey"
                               class="text-Century-Gothic">Артикул: <br> {{item.id}}</p>
                        </v-responsive>
                        <v-card-title style="text-align: left; margin-top: 10%; overflow: visible"
                                      class="card_description text-Century-Gothic text-xs-center">
                            <v-layout style="margin-bottom: 3%">
                                <v-flex xs4>
                                    <v-select style="padding-top: 5px"
                                              dense
                                              hide-details
                                              height="40"
                                              :items="sizesOfItems"
                                              v-model="sizeOfItem"
                                              outline
                                    ></v-select>
                                </v-flex>
                                <v-flex xs5>
                                    <v-layout row>
                                        <v-spacer></v-spacer>
                                        <v-flex xs2 @click="item.quantity -= 1"
                                                style="text-align: right; font-size: 32px; margin-right: 5%"
                                                class="text-Century-Gothic">-
                                        </v-flex>
                                        <v-flex xs2><p class="text-xs-center text-Century-Gothic"
                                                       style="font-size: 16px; padding-top: 15px">
                                            {{item.quantity}} <br> шт</p></v-flex>
                                        <v-flex xs2 @click="item.quantity += 1" class="text-Century-Gothic"
                                                style="font-size: 32px; margin-left: 2%">+
                                        </v-flex>
                                        <v-spacer></v-spacer>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs3>
                                    <div style="text-align: right; padding: 12px 0; font-size: 20px; color: black;"
                                         class="text-Century-Gothic">
                                        <template v-if="priceOfItem !== null">
                                            {{parseFloat(priceOfItem*item.quantity).toFixed(2)}} <sup>руб.</sup>
                                        </template>
                                        <template v-else>
                                            Цену уточняйте
                                        </template>
                                    </div>
                                </v-flex>
                            </v-layout>
                        </v-card-title>
                        <v-card-actions style="padding-top: 10%;">
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <div style="text-align: justify" class="text-Century-Gothic">
                                        {{item.description}}
                                    </div>
                                    <a class="text-Century-Gothic"
                                       style="padding: 1% 0; font-size: 16px; color: #3f6b33;"
                                       @click="getFullDescription()">
                                        <template v-if="!fullDescription">
                                            Полное описание
                                        </template>
                                        <template v-else>
                                            Скрыть полное описание
                                        </template>
                                    </a>
                                </v-flex>
                            </v-layout>

                        </v-card-actions>
                        <v-btn style="width:70%; margin-left: 10%; margin-top: 10%" color="#3f6b33"
                               @click="addToCart(item)">
                            <div class="text-Century-Gothic" style="color: white; text-transform: capitalize;">
                                В корзину
                            </div>
                        </v-btn>
                    </v-flex>

                </v-layout>
                <v-expand-transition>
                    <v-card-text v-show="fullDescription">
                        <div class="text-Century-Gothic fullDescription">
                            <p class="title" style="font-weight: bold">Особенности:</p>
                            {{item.fullDescription[0].features}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Описание:</p>
                            {{item.fullDescription[0].fullDescription}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Состав:</p>
                            {{item.fullDescription[0].consists}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Пищевые добавки:</p>
                            {{item.fullDescription[0].additives}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Анализ:</p>
                            {{item.fullDescription[0].analysis}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Микроэлементы:</p>
                            {{item.fullDescription[0].microelements}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Дополнительно:</p>
                            {{item.fullDescription[0].additional}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Производитель:</p>
                            {{item.fullDescription[0].manufacturer}}
                        </div>
                        <div class="text-Century-Gothic">
                            <p class="title" style="font-weight: bold">Импортер:</p>
                            {{item.fullDescription[0].importer}}
                        </div>
                    </v-card-text>
                </v-expand-transition>
            </v-card>
        </v-dialog>
    </v-flex>

</template>

<script>

    export default {
        props: ['item'],
        name: "ItemRow",
        data() {
            return {
                click: false,
                dialog: false,
                fullDescription: false,
                sizeOfItem: "",
                imgOfItem: null,
                priceOfItem: null,
                sizesOfItems: null,
            }
        },
        methods: {
            addToCart(item) {
                const itemToBasket = JSON.parse(JSON.stringify(item));
                itemToBasket.sizeOfItem = this.sizeOfItem;
                itemToBasket.price = this.priceOfItem;
                this.$store.commit('updateBasket', itemToBasket);
                document.getElementById(item.id).setAttribute("class", "v-icon material-icons theme--light red--text")
                this.dialog = false;
            },
            getSizes(item) {

            },
            setSizeOfItem(item, sizeOfItem) {
                Array.prototype.forEach.call(item.sizes, size => {
                        if (size.size === sizeOfItem) {
                            this.imgOfItem = size.img;
                            this.priceOfItem = size.price;
                        }
                    }
                );
            },
            getFullDescription() {
                this.fullDescription = !this.fullDescription;
                document.getElementsByClassName('fullDescription')[0].scrollIntoView({
                    behavior: 'smooth'
                });
            },
        },
        watch: {
            sizeOfItem: function () {
                this.setSizeOfItem(this.item, this.sizeOfItem)
            }
        },
        beforeMount() {
            this.sizeOfItem = this.item.sizes[0].size;
            this.priceOfItem = this.item.sizes[0].price;
        },
        mounted() {
            let sizes = new Array();
            Array.prototype.forEach.call(this.item.sizes, size =>
                sizes.push(size.size)
            );
            this.sizesOfItems = sizes;
        }
    }
</script>

<style>
    /*@font-face {*/
    /*    font-family: "Sitka";*/
    /*    src: url("/SitkaBanner-06.ttf") format('truetype');*/
    /*}*/

    .itemCardImg {
        margin-left: auto;
        margin-right: auto;
        width: auto;
        max-height: 195px;
    }

    @media only screen and (min-width: 960px) {
        .item_card {
            margin: 8.2%;
            position: relative;
            overflow: hidden;
            padding-bottom: 5%;

        }

        .card_title {
            font-size: 14px;
            padding: 2px 10px;
            text-align: center;
            height: 42px;
            overflow: hidden;
        }

        .card_description {
            padding: 2px 10px;
            color: #696969;
            font-size: 14px !important;
            text-align: center;
            height: 65px;
            overflow: hidden;
        }

        #card_title {
            font-size: 14px !important;
        }

        .itemCardImg {

        }

        .v-dialog {
            width: 50%;
        }

        .divider_title {
            margin-top: 5%;
        }

        .divider_price {
            margin-top: 4%;
        }
    }

    @media only screen and (max-width: 960px) {
        .item_card {
            height: 400px;
            margin: 3%;
            position: relative;
            overflow: hidden;
        }

        .card_title {
            font-size: 14px !important;
            padding: 2px 10px;
            text-align: center;
            height: 42px;
            overflow: hidden;
        }

        .card_description {
            padding: 2px 10px;
            color: #696969;
            font-size: 14px !important;
            text-align: center;
            height: 60px;
            overflow: hidden;
        }

        .dialog_xs {
            margin: 0;
            width: 100%
        }
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

    .text-Sitka {
        font-family: "Sitka", Sitka !important;
    }

    .card_sale {
        background-color: rgba(210, 74, 67, 1);
        height: 100px;
        right: -50px;
        position: absolute;
        top: -50px;
        width: 100px;

        -webkit-transform: rotate(45deg);
    }

    .card_sale span {
        color: white;
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
        font-size: 1.2em;
        right: 11px;
        top: 70px;
        position: absolute;
        width: 80px;
        -webkit-transform: rotate(-45deg);
    }

    .price {

        padding: 2px 10px;
    }

    .v-text-field--box .v-input__slot, .v-text-field--outline .v-input__slot {
        min-height: auto !important;
        display: flex !important;
        align-items: center !important;
        font-size: 13px;
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

    .v-select.v-text-field--enclosed:not(.v-text-field--single-line) .v-select__selections {
        padding: 0;
    }

    .v-text-field.v-text-field--enclosed .v-input__append-inner, .v-text-field.v-text-field--enclosed .v-input__append-outer, .v-text-field.v-text-field--enclosed .v-input__prepend-inner, .v-text-field.v-text-field--enclosed .v-input__prepend-outer {
        margin-top: 4%;
    }

    .v-list--dense .v-list__tile:not(.v-list__tile--avatar) {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

    .theme--light.v-text-field--outline .v-input__slot {
        border: 1px solid;
        border-color: grey;
    }

    .material-icons.material-icons--outlined {
        font-family: 'Material Icons Outlined';
    }

    .arrow-back {
        height: 10px;
        right: 37px;
        position: absolute;
        top: -7px;
        width: 10px;
    }
</style>