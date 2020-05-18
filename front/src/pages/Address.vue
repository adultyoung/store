<template>
    <v-form v-model="valid" lazy-validation>
        <v-text-field
                v-model="address.city"
                :rules="commonRules"
                label="Город"
                placeholder="Минск"
                required
        ></v-text-field>
        <v-text-field
                v-model="address.street"
                :rules="commonRules"
                label="Улица/Проспект"
                placeholder="Чернышевского"
                required
        ></v-text-field>
        <v-text-field
                v-model="address.house"
                :rules="commonRules"
                label="Дом"
                placeholder="3"
                required
        ></v-text-field>
        <v-text-field
                v-model="address.number"
                :rules="commonRules"
                label="Квартира"
                placeholder="Чернышевского"
                required
        ></v-text-field>
        <v-text-field
                v-model="address.phone"
                :rules="commonRules"
                label="Номер телефона"
                placeholder="+375 (99) 270-25-44"
                hint="В формате +375 (ХХ) XXX-XX-XX, пожалуйста"
                required
        ></v-text-field>
        <v-btn
                :disabled="!valid"
                @click="submitAddress"
        >
            submit
        </v-btn>
        <v-btn @click="clear">clear</v-btn>
    </v-form>
</template>

<script>

    export default {
        name: "Address",
        data() {
            return {
                valid: true,
                address: null,
                commonRules: [
                    v => !!v || 'Это поле обязательно',
                ],
            }
        },
        mounted() {
            this.address = this.$store.state.profile.address;
        },
        methods: {
            submitAddress() {
                this.$store.dispatch("addAddress", address);
            }
        }
    }
</script>

<style scoped>

</style>