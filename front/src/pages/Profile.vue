<template>
    <v-container class="content">
        <v-layout row v-if="$vuetify.breakpoint.smAndDown">
            <v-flex xs4>
                <v-btn
                        class="btn_navigation1"
                        :color="this.$route.path === '/profile/settings' ? '#3f6b33' : 'transient'"
                        :outline="this.$route.path !== '/profile/settings'"
                        to="/profile/settings"
                        :style="this.$route.path === '/profile/settings' ? 'color:white' : 'color:#3f6b33; background: white !important'"
                >
                    Профиль
                </v-btn>
            </v-flex>
            <v-flex xs5>
                <v-btn
                        class="btn_navigation2"
                        :color="this.$route.path === '/profile/opders' ? '#3f6b33' : 'transient'"
                        :outline="this.$route.path !== '/profile/opders'"
                        :style="this.$route.path === '/profile/opders' ? 'color: white' : 'color: #3f6b33; background: white !important'"
                        to="/profile/opders"
                >
                    История заказов
                </v-btn>
            </v-flex>
            <v-flex xs4>
                <v-btn
                        class="btn_navigation3"
                        :color="this.$route.path === '/profile/security' ? '#3f6b33' : 'transient'"
                        :outline="this.$route.path !== '/profile/security'"
                        :style="this.$route.path === '/profile/security' ? 'color: white' : 'color: #3f6b33; background: white !important'"
                        to="/profile/security"
                >
                    Настройки
                </v-btn>
            </v-flex>
        </v-layout>
        <v-layout justify-space-beetween v-else style="margin-bottom: 6vh">
            <v-spacer></v-spacer>
            <v-flex xs2>
                <v-btn
                        class="btn_navigation1"
                        :color="this.$route.path === '/profile/settings' ? '#3f6b33' : 'gray'"
                        :style="this.$route.path === '/profile/settings' ? 'color: white; z-index: 1' : 'color: #3f6b33; z-index:0'"
                        to="/profile/settings"
                >
                    Профиль
                </v-btn>
            </v-flex>
            <v-flex xs3>
                <v-btn
                        class="btn_navigation2"
                        :color="this.$route.path === '/profile/opders' ? '#3f6b33' : 'transient'"
                        :style="this.$route.path === '/profile/opders' ? 'color: white; z-index: 1' : 'color: #3f6b33; z-index:0'"
                        to="/profile/opders"
                >
                    История заказов
                </v-btn>
            </v-flex>
            <v-flex xs2>
                <v-btn
                        class="btn_navigation3"
                        :color="this.$route.path === '/profile/security' ? '#3f6b33' : 'transient'"
                        :style="this.$route.path === '/profile/security' ? 'color: white; z-index: 1' : 'color: #3f6b33; z-index:0'"
                        to="/profile/security"
                >
                    Настройки
                </v-btn>
            </v-flex>
            <v-spacer></v-spacer>
        </v-layout>

        <router-view></router-view>

    </v-container>
</template>

<script>

    export default {
        name: 'Profile',
        data() {
            return {
                profile: {}
            }
        },
        computed: {},
        watch: {},
        mounted() {
            console.log(this.$route.path + "")
        }
    }
</script>

<style scoped>
    img {
        max-width: 100%;
        height: auto;
    }

    @media only screen and (max-width: 960px) {
        .btn_navigation1 {
            width: 8vh;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
        }

        .btn_navigation2 {
            width: 16vh;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
        }

        .btn_navigation3 {
            width: 11vh;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
        }
    }

    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
        }
        .btn_navigation1 {
            width: 8%;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
            position: absolute;
            height: 6vh;
        }
        .btn_navigation2 {
            width: 13%;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
            position: absolute;
            height: 6vh;
        }
        .btn_navigation3 {
            width: 9%;
            border-radius: 5px;
            font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
            color: white;
            text-transform: capitalize;
            position: absolute;
            height: 6vh;
        }
    }
</style>
