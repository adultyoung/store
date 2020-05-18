<template>
    <v-container style="padding: 0" fluid>
        <v-layout row wrap v-if="$vuetify.breakpoint.smAndDown" style="background: white; padding-top: 25px">
            <v-flex xs12 style="height: 401px; padding-top: 124px">
                <p class="text-Century-Gothic-Bold" style="color: #3f6b33; text-align: center; font-size: 27px">Мы любим
                    <br> наших с вами питомцев
                </p>
                <img style="position: absolute; width: 225px; margin-left: 70px; margin-right: 70px" align="right"
                     src="../assets/cat_index.png"/>
            </v-flex>
        </v-layout>
        <div v-if="$vuetify.breakpoint.smAndDown"
             style="width: 100%;height: 85px;box-shadow: 0 -0.001px 13px 0 #0000003d; background: white"></div>
        <v-container class="form_container" v-show="$vuetify.breakpoint.smAndDown">
            <div class="title">
                <p style="color: white;" class="title text-xs-center text-Century-Gothic-Bold">Корм для Ваших <br>
                    домашних любимцев!</p>
            </div>
            <div class="subtitle-2">
                <p style="color: white" class="text-xs-center text-Sitka">При заполнении формы 20% скидка на ЛЮБОЙ <br>
                    корм для котов и
                    собак
                    <br>
                    + БЕСПЛАТНАЯ доставка по городу Минск </p>
            </div>
            <v-card style="background: transparent; box-shadow: none">
                <v-card-title style="padding: 0">
                </v-card-title>
                <v-card-text style="padding-top: 0">
                    <v-container grid-list-md style="padding-top: 0; padding-bottom: 0">
                        <v-layout wrap>
                            <v-flex xs12>
                                <v-form action="https://doghouse.by/login" method="post" v-model="valid" id="formLogin" lazy-validation>
                                    <v-container style="color: white; padding: 0;" v-if="this.profile">Вы успешно
                                        зарегистрированы. <br> Пожалуйста, войдите в свой профиль.
                                    </v-container>
                                    <template v-if="register">
                                        <v-text-field
                                                dark
                                                color="white"
                                                name="feed"
                                                v-model="feed"
                                                label="Введите название корма"
                                                required
                                        ></v-text-field>
                                        <v-combobox
                                                dark
                                                color="white"
                                                name="city"
                                                v-model="city"
                                                :items="cities"
                                                label="Город"
                                                required
                                        ></v-combobox>
                                        <v-text-field
                                                dark
                                                color="white"
                                                prepend-inner-icon="call"
                                                name="telephone"
                                                v-model="telephone"
                                                :rules="telephoneRules"
                                                label="Телефон"
                                                required
                                                :mask="telephoneMask"
                                        ></v-text-field>
                                    </template>
                                    <v-text-field
                                            color="white"
                                            dark
                                            prepend-inner-icon="mail"
                                            id="username"
                                            name="username"
                                            v-model="email"
                                            :rules="emailRules"
                                            label="Адрес эл. почты"
                                            required
                                    ></v-text-field>
                                    <v-text-field
                                            dark
                                            color="white"
                                            id="password"
                                            type="password"
                                            name="password"
                                            prepend-inner-icon="lock"
                                            v-model="password"
                                            :rules="passwordRules"
                                            :counter="50"
                                            label="Пароль"
                                            required
                                    ></v-text-field>
                                </v-form>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-card-text>
            </v-card>
            <div class="text-xs-center">
                <template v-if="!register">
                    <v-layout row class="text-Century-Gothic">
                        <v-spacer></v-spacer>
                        <v-flex xs10>
                            <v-btn
                                    type="submit"
                                    form="formLogin"
                                    class="btnReg"
                                    color="rgba(63,107,51,0.9)"
                                    :disabled="!valid"
                                    style="width: 100%; border-radius: 0"
                                    block
                            >
                                Войти
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                    <v-layout row align-center>
                        <v-spacer></v-spacer>
                        <v-flex xs3>
                            <v-divider dark color="white"></v-divider>
                        </v-flex>
                        <v-flex xs4 class="text-xs-center" style="color: white;">или</v-flex>
                        <v-flex xs3>
                            <v-divider dark color="white"></v-divider>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                    <v-btn
                            type="button"
                            class="btnReg"
                            style="border: 2px solid white; border-radius: 0; background: transparent; width: 43%;"
                            @click="register = !register"
                    >
                        Регистрация
                    </v-btn>
                </template>
                <template v-else>
                    <v-layout row class="text-Century-Gothic">
                        <v-spacer></v-spacer>
                        <v-flex xs10>
                            <v-btn
                                    class="btnReg"
                                    color="rgba(63,107,51,0.9)"
                                    :disabled="!valid"
                                    @click.native="submitReg"
                                    style="width: 100%; border-radius: 0"
                                    block
                            >
                                Регистрация
                            </v-btn>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                    <v-layout row align-center>
                        <v-spacer></v-spacer>
                        <v-flex xs3>
                            <v-divider dark color="white"></v-divider>
                        </v-flex>
                        <v-flex xs4 class="text-xs-center" style="color: white;">или</v-flex>
                        <v-flex xs3>
                            <v-divider dark color="white"></v-divider>
                        </v-flex>
                        <v-spacer></v-spacer>
                    </v-layout>
                    <v-btn
                            type="button"
                            class="btnReg"
                            style="border: 2px solid white; border-radius: 0; background: transparent; width: 43%;"
                            @click="changeReg"
                    >
                        Войти
                    </v-btn>
                </template>
            </div>
        </v-container>
        <v-container v-if="$vuetify.breakpoint.smAndDown" fluid
                     style="padding: 0; background: white; padding-top: 27px">
            <v-layout row wrap>
                <v-flex xs12>
                    <img src="../assets/dog_and_cat_index.png"
                         style="width: 89%; display: block; margin-right: auto;margin-left: auto"/>
                </v-flex>
                <v-flex xs12 style="margin-bottom: 27px">
                    <p style="margin: 0; font-size: 15px" class="text-Century-Gothic text-xs-center">
                        Процен от Ваших покупок идёт на помощь <br>
                        собакам и кошкам на Гурского. <br>
                        Мы с Вами отдаём корм нуждающимся <br>
                        питомцам без всяких накруток
                    </p>
                    <div style="margin-bottom: 25px; font-size: 16px" class="text-Century-Gothic-Bold text-xs-center">
                        Делаем добро вместе!
                    </div>
                    <v-spacer></v-spacer>
                    <v-btn color="#3f6b33" to="/catalog?dog=true" block class="text-Century-Gothic to_catalog_btn"
                           style="text-transform: none; width: 54%; margin-left: auto; margin-right: auto; border-radius: 4px; height: 43px; font-size: 16px">
                        Перейти к каталогу
                    </v-btn>
                    <v-spacer></v-spacer>
                </v-flex>
            </v-layout>
        </v-container>


        <!--        <nav class="floating-menu" v-show="$vuetify.breakpoint.mdAndUp">-->

        <!--            <div class="text-xs-center">-->
        <!--                <v-layout justify-space-around>-->
        <!--                    <img @click="expand = !expand" src="../assets/dogForm.png" id="dogForm"/>-->
        <!--                    <v-flex shrink>-->
        <!--                        <v-expand-x-transition>-->
        <!--                            <v-form v-show="expand" style="white-space: nowrap">-->
        <!--                                <v-textarea-->
        <!--                                        solo-->
        <!--                                        label="Введите ваш вопрос"-->
        <!--                                        v-model="question"-->
        <!--                                ></v-textarea>-->
        <!--                            </v-form>-->
        <!--                        </v-expand-x-transition>-->
        <!--                    </v-flex>-->
        <!--                </v-layout>-->
        <!--            </div>-->
        <!--        </nav>-->

        <div style="background: white" class="align-content-center" v-if="$vuetify.breakpoint.mdAndUp">
            <v-container class="content" style="padding-top: 35px;">
                <v-layout>
                    <v-flex xs8 style="height: 306px; padding: 84px 0">
                        <p class="text-Century-Gothic-Bold" style="color: #3f6b33; text-align: center; font-size: 40px">
                            Мы любим </p>
                        <p class="text-Century-Gothic-Bold" style="color: #3f6b33; text-align: center; font-size: 34px">
                            наших с вами питомцев
                        </p>
                    </v-flex>
                    <v-flex xs4>
                        <img style="position: absolute; width: 361px; padding-top: 41px" align="right"
                             src="../assets/cat_index.png"/>
                    </v-flex>
                </v-layout>
            </v-container>
            <div style="width: 100%;height: 106px;box-shadow: 0 -0.001px 13px 0 #0000003d"></div>
            <v-layout
                    :style="'background-image: url(' + require('../assets/form_background.jpg') + '); background-attachment: fixed; background-size: cover'">
                <div style="padding: 0; width: 100%; max-height: 1000px; background: rgba(0,0,0,0.8)"
                     class="align-center"
                     v-show="$vuetify.breakpoint.mdAndUp">
                    <v-container class="formReg vertical-center" style="padding: 50px 0;">
                        <v-layout id="formReg" style="padding: 20px 0" row wrap align-center>
                            <v-spacer></v-spacer>
                            <v-flex xs5 ml-1>
                                <p class="text-Century-Gothic-Bold text-xs-center"
                                   style="font-size: 30px; color: white;">
                                    Корм для Ваших <br>
                                    домашних любимцев!
                                </p>
                                <p class="text-xs-center text-Sitka" style="font-size: 20px; color: #EBEFF2;">
                                    При заполнении формы 20% скидка на ЛЮБОЙ <br>
                                    корм для котов и собак <br>
                                    + БЕСПЛАТНАЯ доставка по г. Минск
                                </p>
                            </v-flex>
                            <v-flex xs1>
                                <v-divider dark color="white" vertical="true" style="height: 435px;"></v-divider>
                            </v-flex>
                            <v-flex xs5 mr-1>
                                <p class="text-Century-Gothic">{{msg}}</p>
                                <v-form id="regForm" action="/login" v-model="valid" method="post" dark
                                        style="padding-top: 18px">
                                    <template v-if="register">
                                        <v-text-field
                                                color="white"
                                                dark
                                                name="feed"
                                                v-model="feed"
                                                label="Введите название корма"
                                                required
                                        ></v-text-field>
                                        <v-combobox
                                                color="white"
                                                dark
                                                name="city"
                                                v-model="city"
                                                :items="cities"
                                                label="Город"
                                                required
                                        ></v-combobox>
                                        <v-text-field
                                                color="white"
                                                dark
                                                prepend-inner-icon="call"
                                                name="telephone"
                                                v-model="telephone"
                                                :rules="telephoneRules"
                                                label="Телефон"
                                                required
                                                :mask="telephoneMask"
                                        ></v-text-field>
                                    </template>
                                    <v-text-field
                                            color="white"
                                            dark
                                            prepend-inner-icon="mail"
                                            name="username"
                                            v-model="email"
                                            :rules="emailRules"
                                            label="Адрес эл. почты"
                                            required
                                    ></v-text-field>
                                    <v-text-field
                                            color="white"
                                            dark
                                            type="password"
                                            name="password"
                                            prepend-inner-icon="lock"
                                            v-model="password"
                                            :rules="passwordRules"
                                            :counter="50"
                                            label="Пароль"
                                            required
                                    ></v-text-field>
                                    <template v-if="!register">
                                        <div style="padding-top: 25px">
                                            <v-btn class="btnDesktop" dark
                                                   color="rgba(63,107,51,0.9)"
                                                   :disabled="!valid"
                                                   type="submit"
                                                   form="regForm"
                                            >
                                                Войти
                                            </v-btn>
                                        </div>
                                        <v-layout row align-center style="padding: 10px 50px">
                                            <v-spacer></v-spacer>
                                            <v-flex xs3>
                                                <v-divider dark color="white"></v-divider>
                                            </v-flex>
                                            <v-flex xs4 class="text-xs-center" style="color: white;">или</v-flex>
                                            <v-flex xs3>
                                                <v-divider dark color="white"></v-divider>
                                            </v-flex>
                                            <v-spacer></v-spacer>
                                        </v-layout>
                                        <div>
                                            <v-btn class="btnDesktop"
                                                   style="width: 50%"
                                                   dark
                                                   @click="register=false"
                                                   outline
                                                   color="transient"
                                            >
                                                Регистрация
                                            </v-btn>
                                        </div>
                                    </template>
                                </v-form>
                                <template v-if="register">
                                    <div class="text-xs-center text-Century-Gothic" style="padding-top: 25px">
                                        <v-btn class="btnDesktop" dark
                                               color="rgba(63,107,51,0.9)"
                                               :disabled="!valid"
                                               @click="submitReg"
                                               type="button"
                                        >
                                            Регистрация
                                        </v-btn>
                                        <v-layout row align-center style="padding: 10px 50px">
                                            <v-spacer></v-spacer>
                                            <v-flex xs3>
                                                <v-divider dark color="white"></v-divider>
                                            </v-flex>
                                            <v-flex xs4 class="text-xs-center" style="color: white;">или</v-flex>
                                            <v-flex xs3>
                                                <v-divider dark color="white"></v-divider>
                                            </v-flex>
                                            <v-spacer></v-spacer>
                                        </v-layout>
                                        <div>
                                            <v-btn class="btnDesktop"
                                                   style="width: 50%"
                                                   dark
                                                   @click="register=false"
                                                   outline
                                                   color="transient"
                                            >
                                                Войти
                                            </v-btn>
                                        </div>
                                    </div>
                                </template>
                            </v-flex>
                            <v-spacer></v-spacer>
                        </v-layout>
                    </v-container>
                </div>
            </v-layout>
            <v-container class="content" style="padding: 0;">
                <v-layout style="padding: 5vh 0">
                    <v-flex md6>
                        <img src="../assets/dog_and_cat_index.png" style="width: 85%; display: block; margin: auto"/>
                    </v-flex>
                    <v-flex md6 style="display: block; margin-top: auto; margin-bottom: auto">
                        <p style="margin: 0; font-size: 21px" class="text-Century-Gothic text-xs-center">
                            Процен от Ваших покупок идёт на помощь <br>
                            собакам и кошкам на Гурского. <br>
                            Мы с Вами отдаём корм нуждающимся <br>
                            питомцам без всяких накруток
                        </p>
                        <div style="margin-bottom: 25px; font-size: 21px"
                             class="text-Century-Gothic-Bold text-xs-center"> Делаем добро вместе!
                        </div>
                        <v-spacer></v-spacer>
                        <v-btn color="#3f6b33" to="/catalog?dog=true" block class="text-Century-Gothic to_catalog_btn"
                               style="text-transform: none; width: 46%; margin-left: auto; margin-right: auto; border-radius: 25px; height: 53px;">
                            Перейти к каталогу
                        </v-btn>
                        <v-spacer></v-spacer>
                    </v-flex>
                </v-layout>
            </v-container>
        </div>

        <v-container style="padding: 0; background: white; max-width: 8000px">
            <div style="overflow: hidden">
                <img class="lapki" src="../assets/lapki.png"/>
            </div>
            <v-layout class="content" row wrap
                      style="padding: 3%; display: block; margin-right: auto; margin-left: auto">
                <v-flex xs12>
                    <div class="text-xs-center text-Century-Gothic FAQ">
                        Часто задаваемые вопросы
                    </div>
                </v-flex>
                <v-flex xs12>
                    <v-container class="questions text-Century-Gothic">
                        <v-layout class="pad_FAQ" row wrap>
                            <v-flex xs12 @click="question = 1" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Сотрудничаете ли вы с юр. лицами? Сотрудничаете ли вы с физ. лицами?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 1">
                                    <v-container style="padding-left: 6%">
                                        Мы работаем как с физическими, так и с юридическими лицами
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 2" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какие есть способы оплаты на сайте?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 2">
                                    <v-container style="padding-left: 6%">
                                        По безналичному расчету (перевод на банковский счет);
                                        Онлайн-оплата (оплата банковской картой на сайте);
                                        По системе Ерип;
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question  = 3" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Входит ли НДС в указанную на сайте цену товара?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 3">
                                    <v-container style="padding-left: 6%">
                                        Да, цена указана с учетом НДС.
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 4" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Есть ли минимальная сумма заказа? Какая?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 4">
                                    <v-container style="padding-left: 6%">
                                        Нет, минимальной суммы заказа нет.
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 5" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какие есть способы доставки?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 5">
                                    <v-container style="padding-left: 6%">
                                        Курьером
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 6" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какие сроки обработки заказа?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 6">
                                    <v-container style="padding-left: 6%">
                                        1 рабочий день
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 7" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какой срок доставки?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 7">
                                    <v-container style="padding-left: 6%">
                                        до 3 рабочих дней
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 8" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какие документы будут у меня после оплаты товара?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 8">
                                    <v-container style="padding-left: 6%">
                                        После оплаты у Вас будет электронный чек, сохраните его.
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                            <v-flex xs12 @click="question = 10" style="cursor: pointer">
                                <v-icon>keyboard_arrow_right</v-icon>
                                <span class="text-Sitka">
                                Какие у вас есть акции, скидки, бонусы, накопительные программы?
                            </span>
                            </v-flex>
                            <v-expand-transition style="background: white;">
                                <v-flex xs12 v-if="question === 10">
                                    <v-container style="padding-left: 6%">
                                        В данный момент таких мероприятий мы не проводим.
                                    </v-container>
                                </v-flex>
                            </v-expand-transition>
                        </v-layout>
                    </v-container>
                </v-flex>
            </v-layout>
            <v-layout>
                <v-spacer></v-spacer>
                <v-flex xs6 md3>
                    <v-carousel v-if="$vuetify.breakpoint.smAndDown" interval=3000
                                style="border: none; height: 186px; box-shadow: none" hide-controls hide-delimiters>
                        <v-carousel-item><img style="width: 100%" :src="require('../assets/brit_logo.png')"/>
                        </v-carousel-item>
                        <v-carousel-item><img style="width: 100%" :src="require('../assets/bosch_logo.png')"/>
                        </v-carousel-item>
                        <v-carousel-item><img style="width: 100%;" :src="require('../assets/sanabelle_logo.png')"/>
                        </v-carousel-item>
                        <v-carousel-item><img style="width: 100%" :src="require('../assets/royal_canin_logo.png')"/>
                        </v-carousel-item>
                    </v-carousel>
                    <v-carousel v-if="$vuetify.breakpoint.mdAndUp" interval=3000
                                style="border: none; height: 357px; box-shadow: none" hide-controls hide-delimiters>
                        <v-carousel-item><img style="width: 75%; display: block; margin-left: auto; margin-right: auto"
                                              :src="require('../assets/brit_logo.png')"/></v-carousel-item>
                        <v-carousel-item><img style="width: 75%; display: block; margin-left: auto; margin-right: auto"
                                              :src="require('../assets/bosch_logo.png')"/></v-carousel-item>
                        <v-carousel-item><img style="width: 75%; display: block; margin-left: auto; margin-right: auto"
                                              :src="require('../assets/sanabelle_logo.png')"/></v-carousel-item>
                        <v-carousel-item><img style="width: 75%; display: block; margin-left: auto; margin-right: auto"
                                              :src="require('../assets/royal_canin_logo.png')"/></v-carousel-item>
                    </v-carousel>
                </v-flex>
                <v-spacer></v-spacer>
            </v-layout>
        </v-container>
    </v-container>
</template>

<script>

    export default {
        name: "Index",

        data() {
            return {
                cities: [
                    "Минск"
                ],
                register: true,
                question: 0,
                expand: false,
                valid: true,
                password: '',
                passwordRules: [
                    v => !!v || 'Пароль обязателен',
                    v => (v && v.length <= 50) || 'Пароль должен быть менее 50 знаков'
                ],
                telephoneRules: [
                    v => !!v || 'Телефон обязателен',
                ],
                email: '',
                emailRules: [
                    v => !!v || 'E-mail обязателен',
                    v => /.+@.+/.test(v) || 'Введите валидный E-mail',
                ],
                telephoneMask: "+375 (##) ###-##-##",
                feed: null,
                msg: null
            }
        },
        methods: {
            submitReg() {
                this.$store.dispatch('registration',
                    {
                        password: this.password,
                        email: this.email,
                        address: this.city,
                        telephone: this.telephone,
                        feed: this.feed,
                    }).then(resp => {
                        this.msg = resp;
                });

            },
            submitLogin() {
                this.$store.dispatch('login', {username: this.username, password: this.password}).then(res => {
                    this.$router.go(0);
                })
            },
            changeReg() {
                this.register = !this.register
            }
        },
        beforeMount() {
            console.log("path:" + this.$route.path)
        },
        beforeRouteLeave() {
            this.msg = null;
        }
    }
</script>

<style scoped>
    @font-face {
        font-family: "Sitka Banner";
        src: url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.eot"); /* IE9*/
        src: url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.eot?#iefix") format("embedded-opentype"), /* IE6-IE8 */ url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.woff2") format("woff2"), /* chrome、firefox */ url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.woff") format("woff"), /* chrome、firefox */ url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.ttf") format("truetype"), /* chrome、firefox、opera、Safari, Android, iOS 4.2+*/ url("./fonts/7a8bc7c29b5bcadb9510cca51210ac46.svg#Sitka Banner") format("svg"); /* iOS 4.1- */
    }

    .rounded-card {
        border-radius: 15px;
        background: rgba(255, 255, 255, 0.8);
    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }

    .text-Century-Gothic-Bold {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
        font-weight: bold;
    }

    .text-Sitka {
        font-family: "Sitka", Sitka !important;
    }

    @media only screen and (min-width: 960px) {

        .lapki {
            width: 100%;
        }
        .pad_FAQ {
            padding-left: 200px;
        }

        .content {
            max-width: 1200px;
        }

        .formReg {
            max-width: 1000px;
        }

        .questions {
            font-size: 20px !important;
        }

        .FAQ {
            font-size: 28px;
        }
    }

    #formReg {
    }

    .vertical-center {
        padding: 70px 0;
        text-align: center;
    }

    .floating-menu {
        float: right;
        font-family: sans-serif;
        top: 70vh;
        right: 0;
        padding: 5px;;
        z-index: 100;
        position: fixed;
    }

    #dogForm {
        transform: rotate(270deg);
        height: 20vh;
    }

    .btnReg {
        margin-top: 20px;
        color: white;
        font-family: "Sitka", Sitka;
        font-size: 16px;
        text-transform: capitalize;
        width: 50%;
        border-radius: 7px;
    }

    .btnDesktop {
        color: white;
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
        font-size: 20px;
        text-transform: capitalize;
        width: 70%;
    }

    .to_catalog_btn {
        font-size: 19px;
        color: white;
    }

    @media only screen and (max-width: 960px) {
        .btnReg {
            margin-top: 7px !important;
        }

        .FAQ {
            font-size: 18px;
        }

        .questions {
            padding-top: 0;
        }

        main .v-content {
            margin-top: 0 !important;
        }

        .form_container {
            background-color: black;
            position: relative;
            display: block;
            z-index: 0;
        }

        .form_container::after {
            content: "";
            background: url("/fd4c4cfa214eb92ac2162eaec25b8db1.jpg") center bottom;
            background-size: 1435px;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            position: absolute;
            z-index: -1;
            opacity: 0.5;
        }
    }

    .theme--light.v-btn.v-btn--disabled:not(.v-btn--icon):not(.v-btn--flat) {
        background-color: gray !important;
    }
</style>