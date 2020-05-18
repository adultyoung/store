<template>
    <v-card class="item_card">
            <div style="height: 196px">
                <v-img :src=item.img class="itemCardImg">
                </v-img>
            </div>
            <v-divider v-show="$vuetify.breakpoint.smAndDown" style="margin: 0 15%"></v-divider>
            <div id="card_title" class="mb-0 text-Century-Gothic text-xs-center" >
                {{item.title}}
            </div>
            <v-card-title id="card_description" class="text-Century-Gothic text-xs-center">
                {{item.description}}
            </v-card-title>
            <v-card-title class="text-Century-Gothic price" style="text-decoration: line-through; color: grey; font-size: 12px; padding-left: 15px">
                {{item.price * 1.2}} руб
            </v-card-title>
            <v-card-title class="text-Century-Gothic price" style="padding-left: 15px; font-size: 16px;">
                {{item.price}} руб
            </v-card-title>
        <v-card-actions style="padding: 2px 5px">
            <v-layout row wrap>
                <v-flex xs8 style="padding-left: 10px">
                    <v-select style="padding-top: 5px"
                              dense
                              hide-details
                              height="30"
                              :items="sizes"
                              :v-model = "size"
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
        <div class="card_sale">
            <span class="text-xs-center">-20%</span>
        </div>
    </v-card>
</template>

<script>
    import {mapGetters} from 'vuex';
    export default {
        name: "ItemView",
        props: ['id'],
        data() {
            return {
                item: null,
            }
        },
        mounted() {
            this.item = this.$store.state.sortedItems.find(x => x.id === id)
        }
    }
</script>

<style scoped>

</style>