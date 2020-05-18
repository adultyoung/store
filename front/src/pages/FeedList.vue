<template>

        <v-container fluid>
            <v-layout row wrap>
                <item-row
                        :key="item.id"
                        :item="item"
                        v-for="item in sortedItems"/>
            </v-layout>
        </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import ItemRow from "../components/items/ItemRow.vue"

    export default {
        name: "FeedList",
        components: {
            ItemRow
        },
        computed: {
            ...mapGetters(['sortedItems', 'feedTypeList']),
        },
    }
</script>

<style scoped>

</style>