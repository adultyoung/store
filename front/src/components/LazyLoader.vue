<template>
    <span></span>
</template>

<script>

    export default {
        name: 'LazyLoader',
        beforeMount() {
            window.onscroll = () => {
                if (window.innerWidth >= 1000) {
                    let header = document.getElementById("sticky_header");

                    if (window.pageYOffset > 289) {
                        header.classList.add("sticky");
                        document.getElementById('toolbar').classList.add("large_header");
                    } else {
                        document.getElementById('toolbar').classList.remove("large_header");
                        header.classList.remove("sticky");
                    }
                    if (window.pageYOffset > 500) {
                        document.getElementsByClassName('toTop')[0].style.display = "block"
                    } else {
                        document.getElementsByClassName('toTop')[0].style.display = "none"
                    }
                } else {
                    if (window.location.pathname === "/") {
                        const el = document.documentElement;
                        const isBottomOfScreen = el.scrollTop >= document.getElementsByClassName('form_container')[0].offsetTop + document.getElementsByClassName('form_container')[0].offsetHeight || el.scrollTop <= document.getElementsByClassName('form_container')[0].offsetTop;
                        const isTop = el.scrollTop >= document.getElementsByClassName('form_container')[0].offsetTop;

                        if (isBottomOfScreen) {
                            // document.getElementsByClassName('navBar')[0].style.backgroundColor = "white";
                            // document.getElementsByClassName('doghouse_class')[0].style.color = "black";
                            // document.getElementsByClassName('doghouse_class')[1].style.color = "black";
                            // document.getElementsByClassName('v-icon material-icons')[0].style.color = "black";
                            // document.getElementsByClassName('v-icon material-icons')[1].style.color = "black";
                            document.getElementsByClassName('btnHeader v-btn')[0].style.color = "black";
                            document.getElementsByClassName('btnHeader v-btn')[1].style.color = "black";
                            document.getElementsByClassName('btnHeader v-btn')[2].style.color = "black";
                            document.getElementsByClassName('btnHeader v-btn')[3].style.color = "black";
                            document.getElementsByClassName('btnHeader v-btn')[4].style.color = "black";
                            // document.getElementById('basket_icon').style.filter = "invert(0%)";
                            document.getElementById('expand_menu').style.background = 'white';
                        } else {
                            if (isTop) {
                                // document.getElementsByClassName('navBar')[0].style.backgroundColor = "rgba(0,0,0,0.5)";
                                // document.getElementsByClassName('doghouse_class')[0].style.color = "white";
                                // document.getElementsByClassName('doghouse_class')[1].style.color = "white";
                                // document.getElementsByClassName('v-icon material-icons')[0].style.color = "white";
                                // document.getElementsByClassName('v-icon material-icons')[1].style.color = "white";
                                // document.getElementById('basket_icon').style.filter = "invert(100%)";
                                document.getElementById('expand_menu').style.background = 'rgba(0,0,0,0.9)';
                                document.getElementsByClassName('btnHeader v-btn')[0].style.color = "white";
                                document.getElementsByClassName('btnHeader v-btn')[1].style.color = "white";
                                document.getElementsByClassName('btnHeader v-btn')[2].style.color = "white";
                                document.getElementsByClassName('btnHeader v-btn')[3].style.color = "white";
                                document.getElementsByClassName('btnHeader v-btn')[4].style.color = "white";


                            }
                        }
                    }
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
