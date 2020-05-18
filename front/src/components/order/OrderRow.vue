<template>
    <v-flex xs12>
        <v-expand-transition>
            <v-layout align-center justify-space-around row v-show="!fullOrder">
                <v-flex xs11>
                    <v-layout justify-space-between row wrap @click="fullOrder = true">
                        <v-flex xs9>
                            <div>
                                Дата заказа: {{order.createdAt}}

                            </div>
                        </v-flex>
                        <v-flex xs3 text-xs-right>
                            <div>
                                <div>{{order.status}}</div>
                            </div>
                        </v-flex>

                        <v-flex xs6>
                            <div>
                                Номер заказа: {{order.id}}
                            </div>
                        </v-flex>
                        <v-flex xs6>
                            <div>
                                Сумма заказа: {{order.sum}}
                            </div>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs1>
                    <v-btn flat icon>
                        <v-icon color="#3f6b33">replay</v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
        </v-expand-transition>
        <v-expand-transition>
            <v-layout row wrap v-show="fullOrder">
                <v-flex xs8>
                        Номер заказа: {{order.id}}
                </v-flex>
                <v-flex xs4>
                    <a @click="fullOrder = false" style="color: #888888;">Свернуть</a>
                </v-flex>
                <v-flex xs12></v-flex>
                <div>
                    Дата заказа: {{order.createdAt}}
                </div>
                <v-flex xs12>
                    Количество товаров в заказе: ??? шт
                </v-flex>
                <v-flex xs12>
                    <div>
                        Сумма заказа: {{order.sum}}
                    </div>
                </v-flex>
                <v-flex xs12>
                    <v-layout row wrap class="basket_layout_xs" style=" position: relative" v-for="(item, index) in order.items">
                        <v-flex xs3>
                            <div>
                                <img :src="item.sizes[0].img" class="imgSelection">
                                </img>
                            </div>
                        </v-flex>
                        <v-flex xs8>
                            <v-layout row wrap>
                                <v-flex xs8>
                                    <v-layout row wrap>
                                        <v-flex xs12>
                                            <div class="text-Century-Gothic basket_item_title">
                                                {{item.title}}
                                            </div>
                                        </v-flex>
                                        <v-flex xs12 class="text-Century-Gothic basket_item_description"
                                                style="white-space:nowrap; overflow:hidden; ">
                                            {{item.description}}
                                        </v-flex>
                                    </v-layout>
                                </v-flex>
                                <v-flex xs12 class="basket_size text-Century-Gothic">
                                    {{item.sizeOfItem}}
                                </v-flex>
                                <v-flex xs12 v-if="index +1 === order.items.length">
                                    <v-btn color="#3f6b33" block>Повторить заказ</v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-expand-transition>

    </v-flex>
</template>

<script>

    export default {
        props: ['order'],
        name: "OrderRow",
        data() {
            return {
                fullOrder: false,
            }
        }
    }
</script>

<style>
    @media only screen and (max-width: 960px) {
        .basket_item_title {
            margin-top: 15px;
        }


        .basket_layout_xs {
            padding: 7% 0;
        }

        .imgSelection {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 80%;
        }

        .basket_size {
            color: gray;
            font-size: 12px;
        }



        .v-input--selection-controls.v-input .v-label {
            font-size: 12px;
        }

    }
    @media only screen and (min-width: 960px) {


        .basket_item_title {
            margin-top: 15px;
            font-size: 18px;
        }


        .basket_layout_xs {
            padding: 3% 5%;
        }

        .imgSelection {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 40%;
        }

        .basket_item_description {
            font-size: 18px;
        }

        .basket_size {
            margin-top: 15px;
            color: gray;
            font-size: 18px;
        }

    }
</style>