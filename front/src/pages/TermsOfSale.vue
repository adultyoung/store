<template>
    <v-container class="content" style="border-radius: 25px; background: white">
        <div class="text-Century-Gothic">
            <p class="title" style="font-weight: bold">Доставка и оплата</p>
            <p class="title" style="font-weight: bold">Доставка</p>
            <p class="title" style="font-weight: bold">Возврат товара</p>
            <p class="title" style="font-weight: bold">Прием заказа</p>


        <p>Вместе с покупками курьер предоставляет товарный чек, в котором перечислены все доставленные в заказе позиции, их количество и стоимость.

            В момент доставки товара Вы должны внимательно проверить товары на соответствие количеству и качеству. Время приемки всех товаров заказа не должно превышать 10 (десяти) минут. После проверки Вы ставите подпись в этом товарном чеке, подтверждая, что заказ полностью соответствует по количеству и качеству товаров и принят.</p>
            <p class="title" style="font-weight: bold">Оплата</p>
        <p>пластиковой банковской карточкой Visa, VisaElectron, MasterCard, MasterCardElectronic, MasterCard Maestro, Белкарт на сайте (онлайн) после формирования заказа.
        Ерип
        На банковский счет организации

        При оплате онлайн, в момент доставки заказа, курьер выдает товарный чек (чтобы получить товарный чек с подписью и печатью, необходимо написать об этом в примечании к заказу при его оформлении). Покупатель обязан при приеме заказа поставить свою подпись в экземпляре товарного чека интернет-магазина.
        </p>
            <p class="title" style="font-weight: bold">Товарный чек</p>
        </div>

        <img style="width: 100%" src="../assets/tovarniy-chek.jpg" />


    </v-container>
</template>

<script>
    export default {
        name: "TermsOfSale"
    }
</script>

<style scoped>
    @media only screen and (min-width: 960px) {
        .content {
            max-width: 1200px;
            margin-top: 48px;
            margin-bottom: 48px;
        }
    }
    @media only screen and (max-width: 960px) {

    }

    .text-Century-Gothic {
        font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif !important;
    }
</style>