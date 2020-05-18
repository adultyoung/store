<template>
    <v-layout row wrap class="basket_layout_xs" style=" position: relative">
        <v-flex xs3>
            <div>
                <img :src="item.sizes[0].img" class="imgSelection"/>
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
                <v-flex xs4 class="basket_sale text-Century-Gothic">
                    <v-spacer></v-spacer>
                </v-flex>
                <v-flex xs12 class="basket_size text-Century-Gothic">
                    {{item.sizeOfItem}}
                </v-flex>
                <v-flex xs1 @click="minus(item, index)"
                        style="text-align: right; cursor: pointer;"
                        class="text-Century-Gothic minus_size">-
                </v-flex>
                <v-flex xs1><p class="text-xs-center text-Century-Gothic quantity_size">
                    {{item.quantity}} <br> шт</p></v-flex>
                <v-flex xs1 @click="plus(item, index)" style="cursor:pointer;" class="text-Century-Gothic plus_size">+
                </v-flex>
                <v-flex xs5 class="basket_price text-Century-Gothic" style="text-align: center">
                    {{item.price}} р. за шт.
                </v-flex>
                <v-flex xs3 class="basket_price text-Century-Gothic"
                        style="text-align: right;"> {{parseFloat(item.price*item.quantity).toFixed(2)}} руб.
                </v-flex>
            </v-layout>
            <div class="card_basket_sale" @click="setOnDeleteItem(index)">
                <v-btn flat icon>
                    <v-icon color="#3f6b33">clear</v-icon>
                </v-btn>
            </div>
        </v-flex>
        <v-dialog v-model="deleteDialog" style="margin: 0; width: 100%">
            <v-card>
                <v-container style="padding-bottom: 0">
                    <div class="headline text-Century-Gothic delete_title">Удалить</div>
                    <div class="subheading text-Century-Gothic text-xs-center delete_desc">Уверены, что хотите удалить
                        этот
                        товар?
                    </div>
                    <v-layout>
                        <v-spacer></v-spacer>
                        <v-flex xs2>
                            <v-btn flat style="text-transform: capitalize; color: #3f6b33;"
                                   class="text-Century-Gothic" @click="deleteDialog=false">
                                <div class="text-xs-center">
                                    Отмена
                                </div>
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
                        <v-flex xs2>
                            <v-btn flat style="text-transform: capitalize; color: #3f6b33; padding: 10px 0"
                                   class="text-Century-Gothic" @click="deleteItem()">
                                <div class="text-xs-center">
                                    Удалить
                                </div>
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                </v-container>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>
    import {mapGetters} from 'vuex'
    export default {
        props: ['item', 'index'],
        name: "BasketRow",
        data() {
            return {
                deleteDialog: false,
            }
        },
        computed : {
          ...mapGetters(['basket'])
        },
        methods: {
            deleteItem() {
                this.$store.commit('deleteBasketItem', this.onDeleteItem);
                this.deleteDialog = false;
            },
            setOnDeleteItem(index) {
                this.onDeleteItem = index;
                this.deleteDialog = true;
            },
            minus(item, index) {
                if (item.quantity > 0) {
                item.quantity -= 1;
                this.basket.quantities -= 1;
                }
                this.basket.sum = parseFloat(this.basket.sum) - parseFloat(item.price);
                this.basket.sum = this.basket.sum.toFixed(2);
                sessionStorage.setItem("basket", JSON.stringify(this.basket))
            },
            plus(item,index) {
                item.quantity += 1;
                this.basket.quantities += 1;
                this.basket.sum = parseFloat(this.basket.sum) + parseFloat(item.price);
                this.basket.sum = this.basket.sum.toFixed(2);
                sessionStorage.setItem("basket", JSON.stringify(this.basket))
            },
        },
    }
</script>

<style scoped>
    @media only screen and (max-width: 960px) {
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
            font-size: 24px;
            padding-left: 5%;
        }
    }

    @media only screen and (min-width: 960px) {
        .delete_title {
            padding-bottom: 19px;
        }

        .delete_desc {
            padding-bottom: 23px;
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
            font-size: 45px
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
            padding-left: 50%;
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

        .v-dialog {
            width: 52% !important;
        }
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }


    .basket_card {
        border-radius: 10px;
    }
</style>