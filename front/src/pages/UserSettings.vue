<template>
    <v-container class="content" style="padding: 0">
        <v-card style="padding: 16px; padding-top: 5%" class="usr_settings_card">
            <v-layout>
                <v-spacer></v-spacer>
                <v-flex xs12 md6>
                    <v-form>
                        <v-layout row wrap>
                            <v-flex>
                                <v-text-field
                                        color="#3f6b33"
                                        name="first_name"
                                        v-model="first_name"
                                        :rules="nameRules"
                                        label="Имя"
                                        :placeholder="profile.firstName"
                                        required
                                        :disabled="!changeName"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs5 md2>
                                <v-btn flat @click="changeName = true"
                                       style="color: #3f6b33; text-transform: capitalize"
                                       :disabled="changeName">Изменить
                                </v-btn>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        color="#3f6b33"
                                        name="last_name"
                                        v-model="last_name"
                                        :rules="nameRules"
                                        label="Фамилия"
                                        :placeholder="profile.lastName"
                                        required
                                        :disabled="!changeName"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        color="#3f6b33"
                                        name="otchestvo"
                                        v-model="otchestvo"
                                        :rules="nameRules"
                                        label="Отчество"
                                        :placeholder="profile.otchestvo"
                                        required
                                        :disabled="!changeName"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                        <v-layout>
                            <v-flex>
                                <v-text-field
                                        color="#3f6b33"
                                        name="telephone"
                                        v-model="telephone"
                                        :rules="telephoneRules"
                                        label="Контактный телефон"
                                        :placeholder="profile.telephone"
                                        required
                                        :disabled="!changeInfo"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs5 md2 v-if="!changeInfo">
                                <v-btn flat @click="changeInfo = true"
                                       style="color: #3f6b33; text-transform: capitalize"
                                       :disabled="changeInfo">Изменить
                                </v-btn>
                            </v-flex>
                        </v-layout>
                        <v-text-field
                                color="#3f6b33"
                                name="address"
                                v-model="address"
                                label="Адрес доставки"
                                :placeholder="profile.address"
                                required
                                :disabled="!changeInfo"
                        ></v-text-field>
                    </v-form>
                    <v-layout>
                        <v-spacer></v-spacer>
                        <v-flex xs6>
                            <v-btn block color="#3f6b33" style="text-transform: capitalize" @click="saveSettings"
                                   :disabled="!(changeInfo || changeName)">
                                Сохранить
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
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
        name: "UserSettings",
        data() {
            return {
                first_name: null,
                last_name: null,
                otchestvo: null,
                changeName: false,
                nameRules: [
                    v => !!v || 'Поле обязательно обязателен',
                ],

                telephone: null,
                address: null,
                changeInfo: false,
            }
        },
        methods: {
            saveSettings() {

            }
        },
        computed: {
            ...mapGetters(['profile'])
        },
        mounted() {
            this.first_name = this.profile.firstName;
            this.last_name = this.profile.lastName;
            this.otchestvo = this.profile.otchestvo;
            this.telephone = this.profile.telephone;
            this.address = this.profile.address;
        }
    }
</script>

<style scoped>
    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
        }
        .usr_settings_card {
            min-height: 500px;
        }
    }

</style>