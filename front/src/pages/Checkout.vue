<template>
    <v-container style="background-color: white" class="content">
        <v-container class="layout_checkout">
            <v-layout row wrap>
                <v-flex class="text-xs-right" xs4>
                    ФИО
                </v-flex>
                <v-flex xs8>
                    <div class="text-xs-center">{{currentOrder.name}}</div>
                </v-flex>
                <v-flex class="text-xs-right" xs4>Способ доставки</v-flex>
                <v-flex class="text-xs-center" xs8>{{currentOrder.delivery}}</v-flex>
                <template v-if="currentOrder.delivery === 'Самовывоз'">
                    <v-flex class="text-xs-right" xs4>Адрес самовывоза</v-flex>
                    <v-flex class="text-xs-center" xs8> Чернышевского 3/10</v-flex>
                </template>
                <template v-else>
                    <v-flex class="text-xs-right" xs4>Адрес доставки</v-flex>
                    <v-flex class="text-xs-center" xs8>{{currentOrder.address}}</v-flex>
                </template>
                <template v-if="!(currentOrder.email === null || currentOrder.email === undefined)">
                    <v-flex class="text-xs-right" xs4>Электронный адрес</v-flex>
                    <v-flex class="text-xs-center" xs8>{{currentOrder.email}}</v-flex>
                </template>
                <v-flex xs4 class="text-xs-right">Товар</v-flex>
                <v-flex xs8>
                    <v-layout row wrap class="basket_layout_xs" style=" position: relative"
                              v-for="(item, index) in currentOrder.items">
                        <v-flex xs3>
                            <div>
                                <img :src="item.sizes[0].img" class="imgSelection">
                                </img>
                            </div>
                        </v-flex>
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
                                <v-flex xs12 class="basket_size text-Century-Gothic">
                                    {{item.sizeOfItem}}
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs4 class="text-xs-right">Количество товаров в заказе:</v-flex>
                <v-flex xs8 class="text-xs-center">
                    <div class="text-xs-right">{{quantities}}</div>
                </v-flex>
                <v-flex xs4 class="text-xs-right">Стоимость заказа</v-flex>
                <v-flex xs8 class="text-xs-center">
                    <div class="text-xs-right">{{currentOrder.sum}}</div>
                </v-flex>
                <v-flex xs4 class="text-xs-right">Комментарий</v-flex>
                <v-flex xs8 class="text-xs-center">
                    <div class="text-xs-right">{{currentOrder.comment}}</div>
                </v-flex>
                <v-flex xs12>
                    <v-layout>
                        <v-spacer></v-spacer>
                        <v-flex xs12 md4>
                            <form action="https://securesandbox.webpay.by/" method="post">
                                <input type='hidden' name='*scart'>
                                <input type='hidden' name='wsb_storeid'
                                       value='685451115'>
                                <input type='hidden' name='wsb_store'
                                       value='Интернет-магазин Dog House'>
                                <input type='hidden' name='wsb_order_num'
                                       :value="currentOrder.id">
                                <input type='hidden' name='wsb_currency_id'
                                       value='BYN'>
                                <input type='hidden' name='wsb_seed'
                                       :value=randomInt>
                                <input type='hidden' name='wsb_signature'
                                       :value=getMd5Signature()>
                                <input type='hidden' name='wsb_return_url'
                                       value='http://doghouse.by/success'>
                                <input type='hidden' name='wsb_cancel_return_url' value='http://doghouse.by/'>
                                <input type='hidden' name='wsb_notify_url'
                                       value='http://doghouse.by/notify'>
                                <input type='hidden' name='wsb_test' value='1'>
                                <input type='hidden' name='wsb_customer_name' :value=currentOrder.name>
                                <template v-for="(item, index) in currentOrder.items">
                                    <input type="hidden" :name="'wsb_invoice_item_name[' + index + ']'"
                                           :value="item.title + item.sizeOfItem">
                                    <input type="hidden" :name="'wsb_invoice_item_quantity[' + index + ']'"
                                           :value="item.quantity">
                                    <input type='hidden' :name="'wsb_invoice_item_price[' + index + ']'" :value="item.price">
                                </template>
                                <input type='hidden' name='wsb_shipping_name' :value=currentOrder.delivery>
                                <input type='hidden' name='wsb_shipping_price' value='0'>
                                <input type='hidden' name='wsb_total'
                                       :value=currentOrder.sum>
                                <input type='hidden' name='wsb_email'
                                       :value=currentOrder.email>
                                <v-btn type="submit" style="text-transform: none" class="text-Century-Gothic" dark>
<!--                                       @click.prevent="saveOrder"-->
                                       block color="#3f6b33">Подтвердить заказ
                                </v-btn>
                            </form>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-container>
        <v-container style="padding: 150px 0; display: none">
            <p class="title" style="font-weight: bold">Номер вашего заказа - {{currentOrder.id}}</p>
            Все данные заказа будут высланы вам на email. В скором времени с вами свяжутся наши менеджера для
            подтверждения заказа и согласования условий доставки.
        </v-container>
    </v-container>
</template>

<script>

    import {mapGetters} from 'vuex'

    export default {
        name: "Checkout",
        computed: {
            ...mapGetters(['currentOrder'])
        },
        data() {
            return {
                quantities: 0,
                order: null,
                randomInt: null,
            }
        },
        methods: {
            saveOrder() {
                this.$store.dispatch("saveOrderWithoutPayment");
            },
            getMd5Signature() {
                let md5 = require('md5')
                return md5(this.randomInt + "685451115" + this.currentOrder.id + 1 + "BYN" + this.currentOrder.sum + 13336612)
            }
        },
        mounted() {
            let quantities = 0;
            this.currentOrder.items.forEach(item => {
                quantities += item.quantity;
            });
            this.quantities = quantities;
            this.randomInt = Math.floor(Math.random() * 1000000000)+1000000;
        },

    }
</script>

<style scoped>
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
        .layout_checkout {
            max-width: 1000px;
        }

        .content {
            max-width: 1200px;
        }

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

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

</style>