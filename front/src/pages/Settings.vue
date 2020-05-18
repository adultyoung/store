<template>
    <v-container class="content" style="padding: 0">
        <v-card style="padding: 16px; padding-top: 5%" class="settings_card">
            <v-layout row wrap align-center>
                <v-spacer></v-spacer>
                <v-flex md8>
                    <v-layout row wrap align-center>
                        <v-flex xs12>
                            <v-layout row wrap>
                                <v-flex>
                                    <v-text-field
                                            prepend-inner-icon="mail_outline"
                                            name="email"
                                            label="Адрес электронной почты"
                                            v-model="email"
                                            :disabled="!changeEmail"
                                            :placeholder="profile.email"
                                            required
                                    ></v-text-field>
                                </v-flex>
                                <v-flex xs4 md2>
                                    <v-btn flat @click="changeEmail = true" :disabled="changeEmail"
                                           style="color: #3f6b33; text-transform: capitalize">Изменить
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12>
                            <v-layout row>
                                <v-flex>
                                    <v-text-field
                                            prepend-inner-icon="https"
                                            name="password"
                                            label="Пароль"
                                            type="password"
                                            v-model="password"
                                            :disabled="!changePassword"
                                    ></v-text-field>
                                </v-flex>
                                <v-flex xs4 md2>
                                    <v-btn flat @click="changePassword = true" :disabled="changePassword"
                                           style="color: #3f6b33; text-transform: capitalize">Изменить
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12>
                            <v-layout>
                                <v-spacer></v-spacer>
                                <v-flex xs6>
                                    <v-btn block color="#3f6b33" :disabled="!(changeEmail || changePassword)"
                                           style="color: white; text-transform: capitalize">Сохранить
                                    </v-btn>
                                </v-flex>
                                <v-spacer></v-spacer>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-spacer></v-spacer>
            </v-layout>
        </v-card>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex';

    export default {
        name: "Settings",
        data() {
            return {
                changeEmail: false,
                changePassword: false,
                email: null,
                password: null,
            }
        },
        computed: {
            ...mapGetters(['profile'])
        },

        mounted() {
            this.email = this.profile.email
        }
    }
</script>

<style scoped>

    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
        }
        .settings_card {
            min-height: 500px;
        }
    }
</style>