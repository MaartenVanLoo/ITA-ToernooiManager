<template>
    <v-app id="inspire">
        <v-app-bar>
            <v-img src="@/assets/ita.png" min-width="50" max-width="50" class="ma-5 fill-height"/>
            <v-container class="fill-height d-flex align-center justify-center">
                <h1>ITA-tournament Weigh In</h1>
            </v-container>
            <v-btn @click="themeStore.toggleTheme()" icon="mdi-theme-light-dark"></v-btn>
            <v-btn @click="authStore.logout()" icon="mdi-logout"></v-btn>
        </v-app-bar>

        <v-main :class="theme.global.current.value.dark ? 'black': 'bg-grey-lighten-3'">
            <v-container class="justify-center"  style="display: flex; ">
                <v-sheet
                        min-height="70vh"
                        rounded="lg"
                        width="100%"
                        min-width="100%"
                >
                    <RouterView :key="$route.fullPath"/>
                </v-sheet>
            </v-container>
        </v-main>
    </v-app>

</template>

<script setup lang="ts">
import {useTheme} from 'vuetify'
import {useThemeStore} from "@/stores/ThemeStore";
import {useAuthStore} from "@/stores/AuthStore";

const theme = useTheme()
const themeStore = useThemeStore()
const authStore = useAuthStore()
</script>
<style scoped>

</style>

