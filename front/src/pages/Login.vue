<template>
    <v-dialog v-model="dialog" max-width="500px">
        <v-btn
                icon
                slot="activator"
        >
            <v-icon>perm_identity</v-icon>
        </v-btn>
        <v-card>
            <v-card-title style="padding: 0">
                <v-layout row>
                    <v-flex xs6>
                        <v-btn flat block @click="register = false">
                            Авторизация
                        </v-btn>
                    </v-flex>
                    <v-flex xs6>
                        <v-btn flat block @click="register = true">
                            Регистрация
                        </v-btn>
                    </v-flex>
                </v-layout>
            </v-card-title>
            <v-card-text>
                <v-container grid-list-md>
                    <v-layout wrap>
                        <v-flex xs12>
                            <v-form ref="form" v-model="valid" lazy-validation>
                                <v-text-field prepend-inner-icon="person"
                                              v-model="username"
                                              :rules="usernameRules"
                                              :counter="50"
                                              label="Имя пользователя"
                                              required
                                ></v-text-field>
                                <v-flex xs12>
                                    <v-text-field type="password"
                                                  :prepend-inner-icon="lock"
                                                  v-model="password"
                                                  :rules="passwordRules"
                                                  :counter="50"
                                                  label="Пароль"
                                                  required
                                    ></v-text-field>
                                </v-flex>
                                <template v-if="register">
                                    <v-text-field
                                            type="password"
                                            :prepend-inner-icon="lock"
                                            v-model="password2"
                                            :rules="passwordRules"
                                            :counter="50"
                                            label="Подтверждение пароля"
                                            required
                                    ></v-text-field>
                                    <v-text-field
                                            prepend-inner-icon="mail"
                                            v-model="email"
                                            :rules="emailRules"
                                            label="Email"
                                            required
                                    ></v-text-field>
                                </template>
                            </v-form>
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <template v-if="!register">
                    <v-btn
                            block
                            color="green"
                            :disabled="!valid"
                            @click.native="submitLogin"
                    >
                        Войти
                    </v-btn>
                </template>
                <template v-else>
                    <v-layout column>
                        <v-flex>
                            <v-btn
                                    block
                                    color="green"
                                    :disabled="!valid"
                                    @click.native="submitReg"
                            >
                                Зарегистрироваться
                            </v-btn>
                        </v-flex>
                        <v-flex @click="register=false" mt-2>
                            <div class="subtitle-1 text-xs-center" style="text-decoration: underline">Уже есть
                                аккаунт?
                            </div>
                        </v-flex>
                    </v-layout>
                </template>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

    import {mapActions} from 'vuex'

    export default {
        name: "Login",
        data: () => ({
            register: false,
            dialog: false,
            valid: true,
            username: '',
            usernameRules: [
                v => !!v || 'Имя пользователя обязательно',
                v => (v && v.length <= 50) || 'Имя пользователя должно быть менее 50 символов'
            ],
            password: '',
            password2: '',
            passwordRules: [
                v => !!v || 'Пароль обязателен',
                v => (v && v.length <= 50) || 'Пароль должен быть менее 50 знаков'
            ],
            email: '',
            emailRules: [
                v => !!v || 'E-mail обязателен',
                v => /.+@.+/.test(v) || 'Введите валидный E-mail',
            ],
        }),

        methods: {
            ...mapActions(['login', 'registration']),
            submitLogin() {
                this.$store.dispatch('login', {username: this.username, password: this.password}).then(res => {
                    this.$router.go(0);
                })
            },
            submitReg() {
                this.$store.dispatch('registration',
                    {
                        username: this.username,
                        password: this.password,
                        email: this.email
                    })
            },
            clear() {
                this.$refs.form.reset()
            }
        }

    }
</script>

<style scoped>

</style>