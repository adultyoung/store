<template>
    <v-container fluid class="content" style="padding: 0">
        <v-card style="padding: 5%;" class="order_card">
            <v-layout row wrap>
                <order-row
                        :key="order.id"
                        :order="order"
                        v-for="order in sortedOrders"/>
            </v-layout>
        </v-card>
    </v-container>
</template>

<script>
    import OrderRow from '../components/order/OrderRow.vue'
    import {mapGetters} from 'vuex'

    export default {
        components: {
            OrderRow
        },
        computed: {
            ...mapGetters(['sortedOrders'])
        }
    }
</script>

<style scoped>
    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
        }
        .order_card {
            min-height: 500px;
        }
    }
</style>