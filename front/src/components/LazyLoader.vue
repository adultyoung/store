<template>
    <span></span>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        name: 'LazyLoader',
        methods: mapActions(['loadPageAction']),
        mounted() {
            window.onscroll = () => {
                const el = document.documentElement;
                const isBottomOfScreen = el.scrollTop + window.innerHeight === document.body.scrollHeight;

                if (isBottomOfScreen) {
                    this.loadPageAction()
                }
            }
        },
        beforeDestroy() {
            window.onscroll = null
        }
    }
</script>

<style scoped>

</style>
