<template>
  <v-app id="inspire">
    <v-app-bar>
      <v-app-bar-nav-icon
        class="hidden-md-and-up"
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-img src="@/assets/ita.png" min-width="50" max-width="50" class="ma-5 fill-height"/>
      <v-container class="fill-height d-flex align-center justify-center">
        <span
          v-for="link in navigation_links"
          :key="link.name"
        >
          <v-btn
            class="hidden-sm-and-down mx-1"
            v-if="checkRoles(link.allowedRoles)"
            :key="link.name"
            :prepend-icon="link.icon"
            :to="link.location"
          >
            {{ link.name }}
          </v-btn>
        </span>

      </v-container>
      <v-btn @click="themeStore.toggleTheme()" icon="mdi-theme-light-dark"></v-btn>
      <v-btn @click="authStore.logout()" icon="mdi-logout"></v-btn>
    </v-app-bar>

    <v-navigation-drawer
      class="hidden-md-and-up"
      v-model="drawer"
      temporary
    >
      <v-list
        :lines="false"
        density="compact"
        nav
      >
        <span
          v-for="link in navigation_links"
          :key="link.name"
        >
          <v-list-item
            v-if="checkRoles(link.allowedRoles)"
            :key="link.name"
            :title="link.name"
            :to="link.location"
            :prepend-icon="link.icon"
            active-color="primary"
          >
          </v-list-item>
        </span>
      </v-list>
    </v-navigation-drawer>

    <v-main :class="theme.global.current.value.dark ? 'black': 'bg-grey-lighten-3'">
      <v-container class="justify-center"  style="display: flex; ">
        <v-sheet
          min-height="70vh"
          rounded="lg"
          width="100%"
        >
          <RouterView :key="$route.fullPath"/>
        </v-sheet>
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts" setup>
import {useTheme} from 'vuetify'
import {ref} from "vue";
import {useAuthStore} from "@/stores/AuthStore";
import {useThemeStore} from "@/stores/ThemeStore";
import config from "../../config";

const theme = useTheme()
const themeStore = useThemeStore()
const authStore = useAuthStore()
const drawer = ref(false)
const navigation_links = config["NAVIGATION"]

function checkRoles(roles: string[]): boolean {
  if (roles.includes("Any") || roles.includes("*")) {return true;}
  const intersection = authStore.userInfo?.roles.filter(element => roles.includes(element));
  if (!intersection) {
    return false
  }
  return intersection.length > 0
}

</script>
