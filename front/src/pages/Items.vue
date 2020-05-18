<template>
    <v-container fluid>
        <v-layout row wrap>
            <v-flex xs3>
                <v-container style="border: thick; border: #006666">
                    <p class="text-justify subtitle-1">{{feedTypeList.pet}}</p>
                    <ul id="myUL">
                        <li v-for="type in feedTypeList.types"><span class="caret">{{type.type}}</span>
                            <ul class="nested">
                                <li v-for="company in type.companies">
                                    <v-btn flat @click="getPage(type.type, company)">{{company}}</v-btn>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </v-container>
            </v-flex>
            <v-flex>
                <v-container>
                    <div style="align-content: center">
                        <v-pagination
                                v-model="currentPage"
                                :length="totalPages"
                                @input="next"
                        ></v-pagination>
                    </div>
                    <v-container fluid>
                        <v-layout row wrap>
                            <item-row
                                    :key="item.id"
                                    :item="item"
                                    v-for="item in sortedItems"/>
                        </v-layout>
                    </v-container>
                    <div style="align-content: center">
                        <v-pagination
                                v-model="currentPage"
                                :length="totalPages"
                                @input="next"
                        ></v-pagination>
                    </div>
                </v-container>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import LazyLoader from "../components/LazyLoader.vue"
    import ItemRow from "../components/items/ItemRow.vue"

    export default {
        name: "Items",
        components: {
            LazyLoader,
            ItemRow
        },
        data() {
            return {
                path: null,
            }
        },
        methods:
            {
                ...mapActions(['getPageAction', "loadCertainPage", "loadPageAction"]),
                getPage(type, company) {
                    this.path = '/pets/' + 'dog' + '-' + type + '-' + company;
                    this.loadCertainPage(this.path);
                    this.$router.push(this.path);
                    console.log(this.$route.path);
                },
                next(page) {
                    page = page-1
                    const path = this.path + "?page=" + page
                    this.loadPageAction(path);
                    document.getElementById('content').scrollIntoView({
                        behavior: 'smooth'
                    });
                }
            },
        computed: {
            ...mapGetters(['sortedItems', 'currentPage', 'totalPages', "feedTypeList"]),
        },
        mounted() {
            console.log(this.path)
            var toggler = document.getElementsByClassName("caret");
            var i;

            for (i = 0; i < toggler.length; i++) {
                toggler[i].addEventListener("click", function () {
                    this.parentElement.querySelector(".nested").classList.toggle("active");
                    this.classList.toggle("caret-down");
                });
            }
        },
        beforeMount() {
            this.path = this.$route.path
        },
    }
</script>

<style scoped>
    /* Remove default bullets */
    ul, #myUL {
        list-style-type: none;
    }

    /* Remove margins and padding from the parent ul */
    #myUL {
        margin: 0;
        padding: 0;
    }

    /* Style the caret/arrow */
    .caret {
        cursor: pointer;
        user-select: none; /* Prevent text selection */
    }

    /* Create the caret/arrow with a unicode, and style it */
    .caret::before {
        content: "\25B6";
        color: black;
        display: inline-block;
        margin-right: 6px;
    }

    /* Rotate the caret/arrow icon when clicked on (using JavaScript) */
    .caret-down::before {
        transform: rotate(90deg);
    }

    /* Hide the nested list */
    .nested {
        display: none;
    }

    /* Show the nested list when the user clicks on the caret/arrow (with JavaScript) */
    .active {
        display: block;
    }
</style>