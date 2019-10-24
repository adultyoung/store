<template>
    <v-flex xs3>
        <v-card class="itemCard">
            <v-img :src=item.img class="itemCardImg">
            </v-img>
            <v-card-title primary-title>
                <div>
                    <h3 class="headline mb-0">{{item.title}}</h3>
                    <div>{{item.description}}</div>
                </div>
            </v-card-title>
        </v-card>
    </v-flex>
</template>

<script>

    export default {
        props: ['item'],
        name: "ItemRow"
    }
</script>

<style>
    .itemCardImg {
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 300px;
        width: 200px;
    }

    .itemCard {
        width: 300px;
        margin: 30px;
    }
</style>