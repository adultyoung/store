<template>
    <v-container class="content">
        <v-card class="basket_card">
            <div class="text-Century-Gothic basket_title basket_title_text">Корзина</div>
            <v-divider style="margin: 0 5%"></v-divider>
            <template v-if="basket.items.length > 0">
                <template v-for="(item, index) in basket.items">
                    <basket-row
                            :index="index"
                            :item="item"
                    >

                    </basket-row>
                    <v-divider style="margin: 0 10%" v-if="index +1 < basket.items.length" :key="index"></v-divider>
                </template>
                <v-divider style="margin: 0 5%"></v-divider>
                <v-layout row wrap style="margin-top: 20px">
                    <v-flex xs7>
                        <div style="padding-left: 7%; font-size: 16px;" class="items_costs text-Century-Gothic">
                            Стоимость заказа:
                        </div>
                    </v-flex>
                    <v-flex xs5>
                        <div style="padding-right: 17%; text-align: right; font-size: 16px"
                             class="items_sum text-Century-Gothic">
                            {{basket.sum}} руб.
                        </div>
                    </v-flex>
                    <v-flex xs12 style="padding-left: 7%; font-size: 16px" class="items_costs text-Century-Gothic">
                        {{basket.quantities}} товара
                    </v-flex>
                </v-layout>
                <v-container v-if="!checkout">
                    <v-layout justify-end>
                        <v-btn class="confirm_button text-Century-Gothic" dark color="#3f6b33" @click="checkout = true">
                            Оформить заказ
                        </v-btn>
                    </v-layout>
                </v-container>
                <v-container v-if="checkout">
                    <v-layout row wrap align-center justify-center class="delivery_choice">
                        <v-flex xs12 md3>
                            <v-radio-group class="radio_group" v-model="delivery">
                                <v-radio disabled value="Самовывоз" class="radio_input" label="Самовывоз"></v-radio>
                                <v-radio value="Доставка" class="radio_input" label="Доставка 0 руб."></v-radio>
                            </v-radio-group>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-card>
                                <v-card-title class="delivery_hint" v-if="delivery === 'Самовывоз'"><p
                                        style="margin: 0">Самовывоз осуществляется по адресу
                                    <br> г. Минск, ул. Чернышевского 3/10 <br> Время работы: с 9:00 до 18:00</p>
                                </v-card-title>
                                <v-card-title class="delivery_hint" v-else><p>Доставка осуществляется только по г.
                                    Минск. <br> Время
                                    осуществления доставки: с 9:00 до 12:00; <br> с 14:00 до 20:00</p>
                                </v-card-title>
                            </v-card>
                        </v-flex>
                    </v-layout>
                    <v-expand-transition>
                        <v-layout row wrap align-center v-if="delivery === 'Самовывоз'">
                            <v-flex xs12>Контактная информация</v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        name="FIO"
                                        v-model="FIO"
                                        label="ФИО"
                                        required
                                ></v-text-field>
                                <v-text-field v-if="profile === null"
                                              name="email"
                                              v-model="email"
                                              label="Электронная почта"
                                              required
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        name="telephone"
                                        v-model="telephone"
                                        :rules="telephoneRules"
                                        label="Телефон"
                                        required
                                        :mask="telephoneMask"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        name="comment"
                                        v-model="comment"
                                        label="Комментарий"
                                        multi-line
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                        <v-layout row wrap justify-space-around v-else class="info_layout">
                            <v-flex xs12 md9 class="text-Century-Gothic" style="font-size: 16px">Информация для
                                доставки
                            </v-flex>
                            <v-flex xs12 md8>
                                <v-text-field
                                        name="address"
                                        v-model="address"
                                        label="Адрес доставки"
                                        required
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 md8>
                                <v-text-field v-if="profile === null"
                                              name="email"
                                              v-model="email"
                                              label="Электронная почта"
                                              required
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 md8>
                                <v-text-field
                                        name="FIO"
                                        v-model="FIO"
                                        label="ФИО"
                                        required
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 md8>
                                <v-text-field
                                        name="telephone"
                                        v-model="telephone"
                                        :rules="telephoneRules"
                                        label="Телефон"
                                        required
                                        :mask="telephoneMask"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12 md8>
                                <v-text-field
                                        name="comment"
                                        v-model="comment"
                                        label="Комментарий"
                                        textarea
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-expand-transition>
                    <v-layout row justify-space-around v-if="$vuetify.breakpoint.mdAndUp">
                        <v-flex xs12 md8>
                            <v-btn class="text-Century-Gothic"
                                   style="text-transform: capitalize; width: 21%; font-size: 17px; border-radius: 5px; height: 107%;"
                                   dark color="#3f6b33"
                                   @click="confirmOrder">Далее
                            </v-btn>
                        </v-flex>
                    </v-layout>
                    <v-layout v-if="$vuetify.breakpoint.smAndDown">
                        <v-spacer></v-spacer>
                        <v-flex xs6>
                            <v-btn block class="text-Century-Gothic" style="text-transform: capitalize" dark
                                   color="#3f6b33" @click="confirmOrder">Далее
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                </v-container>

            </template>
            <template v-else>
                <div class="empty_basket">
                    <h4 class="display-1 mb-4 text-Century-Gothic">Корзина пуста
                        <router-link to="/catalog">Вернуться к покупкам</router-link>
                    </h4>
                </div>
                <v-divider style="margin: 0 5%"></v-divider>
            </template>
        </v-card>
    </v-container>
</template>

<script>
    import BasketRow from '../components/BasketRow.vue'
    import {mapGetters} from 'vuex'

    export default {
        data() {
            return {
                onDeleteItem: null,
                checkout: false,
                delivery: "Доставка",
                telephone: null,
                FIO: null,
                address: null,
                comment: null,
                cost: 0,
                quantities: 0,
                email: null,
            }
        },
        components: {
            BasketRow
        },
        computed: {
            ...mapGetters(['basket', 'profile']),

        },
        methods: {
            getSrc(item) {
                Array.prototype.forEach.call(item.sizes, size => {
                        if (size.size === item.sizeOfItem) {
                            return size.img;
                        }
                    }
                );
            },
            confirmOrder() {
                this.basket.address = this.address;
                this.basket.comment = this.comment;
                this.basket.delivery = this.delivery;
                this.basket.telephone = this.telephone;
                this.basket.name = this.FIO;
                this.basket.email = this.email;
                this.basket.comment = this.comment;
                this.$store.commit('confirmOrder', this.basket);
                this.$store.dispatch("saveOrderWithoutPayment", this.basket).then(resp => {
                    this.$router.push("/checkout");
                });
            },

        },
        mounted() {

        }
    }
</script>

<style scoped>
    @media only screen and (max-width: 960px) {
        .info_layout {
            padding-top: 40px;
        }

        .basket_item_title {
            margin-top: 15px;
        }

        .basket_sale {
            text-align: right;
            font-size: 25px;
            color: red;
            padding-right: 7%;
            padding-top: 5px;
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

        .basket_price {
            font-size: 13px;
            color: gray;
        }

        .minus_size {
            font-size: 14px
        }

        .quantity_size {
            font-size: 14px
        }

        .plus_size {
            font-size: 14px
        }

        .card_basket_sale {
            height: 10px;
            right: 37px;
            position: absolute;
            top: -7px;
            width: 10px;
        }

        .radio_input {
            font-size: 12px;
        }

        .delivery_hint {
            font-size: 12px;
            padding: 5%;
            border-radius: 15px;
        }

        .v-input--selection-controls.v-input .v-label {
            font-size: 12px;
        }

        .basket_title {
            font-size: 21px;
            padding-left: 5%;
            padding-bottom: 5px;
            padding-top: 5px;
        }

        .confirm_button {
            text-transform: capitalize;
        }
    }

    @media only screen and (min-width: 960px) {

        .info_layout {
            padding-top: 20px;
        }

        .delivery_choice {
            padding-top: 50px;
        }

        .confirm_button {
            text-transform: none;
            margin-right: 160px;
        }

        .items_sum {
            padding-right: 185px !important;
        }

        .radio_group {
            padding-left: 5%;
        }

        .content {
            max-width: 1200px;
        }

        .delivery_hint {
            font-size: 17px;
            padding: 5%;
            border-radius: 15px;
        }

        .basket_item_title {
            margin-top: 15px;
            font-size: 18px;
        }

        .basket_sale {
            text-align: right;
            font-size: 25px;
            color: red;
            padding-right: 7%;
            padding-top: 5px;
        }

        .basket_layout_xs {
            padding: 3% 5%;
        }

        .basket_title {
            font-size: 32px;
            padding-top: 10px;
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

        .basket_price {
            font-size: 18px;
            color: gray;
        }

        .items_costs {
            padding-left: 276px !important;
            font-size: 16px;
        }

        .minus_size {
            font-size: 28px
        }

        .quantity_size {
            padding-top: 8px;
            font-size: 20px
        }

        .plus_size {
            font-size: 28px
        }

        .card_basket_sale {
            height: 10px;
            right: 161px;
            position: absolute;
            top: 0px;
            width: 10px;
        }

        .basket_title_text {
            padding-left: 7%;
        }

        .empty_basket {
            margin: 20%;
            height: 300%;
        }
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, Ap . pleGothic, sans-serif !important;
    }


    .basket_card {
        border-radius: 10px;
    }
</style>
