<template>
    <v-container fluid>
        <v-layout row>
            <v-flex xs2>
            </v-flex>
            <v-flex>
                <v-layout row wrap>
                    <item-row
                            :key="item.id"
                            :item="item"
                            v-for="item in sortedItems"/>
                    <lazy-loader></lazy-loader>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import ItemRow from "../components/items/ItemRow.vue"
    import LazyLoader from "../components/LazyLoader.vue"

    export default {
        components: {
            LazyLoader,
            ItemRow
        },
        data() {
            return {
                item: null
            }
        },
        computed: mapGetters(['sortedItems'])
    }
</script>

<style scoped>

</style>