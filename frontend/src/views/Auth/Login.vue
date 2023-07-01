<template>
  <v-app>

    <v-container
        fluid
        class="fill-height align-center justify-center"
    >

      <v-card class="elevation-12 w-50" min-width="300">
        <v-toolbar color="primary">
          <v-toolbar-title>Login</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form @submit.prevent>
            <v-autocomplete
                v-model="selectedUser"
                label="User"
                :items="dropdown"
                item-title="field"
                item-value="username"
            ></v-autocomplete>
            <v-text-field v-if="selectedUser === 'Other'"
                prepend-icon="mdi-account" name="Username" label="Username"
                v-model="otherUser"
                id="username"
                :error-messages="fieldErrors.get('UserName')!"
                @update:modelValue=""
                @keydown.enter="login()"
            ></v-text-field>
            <v-text-field v-if="selectedUser === 'Other'"
                id="password"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                prepend-icon="mdi-lock"
                name="password"
                label="Password"
                :type="showPassword ? 'text' : 'password'"
                v-model="password"
                :error-messages="fieldErrors.get('password')!"
                @click:append="showPassword = !showPassword"
                @keydown.enter="login()"
            ></v-text-field>
          </v-form>
          <v-alert
              id="alert_login"
              v-model="showError"
              variant="tonal"
              border="start"
              closable
              close-label="Close Alert"
              color="error"
              title="Something went wrong: wrong email or password"
          ></v-alert>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn id="login" color="primary" @click="login()">Login</v-btn>
        </v-card-actions>
      </v-card>


    </v-container>
  </v-app>
</template>

<script setup lang="ts">

import {ref} from "vue";
import {useThemeStore} from "@/stores/ThemeStore";
import {useAuthStore} from "@/stores/AuthStore";
import {useRouter} from "vue-router";
import {UserCredentials} from "@/models/User";

const selectedUser =ref<string>("");
const dropdown = ref(
    [
      {field:"Manager",username:"Manager"},
      {field:"Tatami 1",username:"Tatami1"},
      {field:"Tatami 2",username:"Tatami2"},
      {field:"Tatami 3",username:"Tatami3"},
      {field:"Tatami 4",username:"Tatami4"},
      {field:"Tatami 5",username:"Tatami5"},
      {field:"Tatami 6",username:"Tatami6"},
      {field:"Tatami 7",username:"Tatami7"},
      {field:"Tatami 8",username:"Tatami8"},
      {field:"Info",username:"Info"},
      {field:"Weeg computer",username:"Weeg"},
      {field:"Other",username:"Other"}
    ]
)
const otherUser = ref<string>('')
const password = ref<string>('')
const showPassword = ref<boolean>(false)
const showError = ref<boolean>(false)
const router = useRouter()
const authStore = useAuthStore()
useThemeStore() // this is needed to load the theme

const fieldErrors=ref<Map<string,string>>(new Map<string,string>());

function init(){}
function login(){
  let username: string = selectedUser.value;
  let password:string = "Admin12345!";

  if (username === "Other"){
      username = otherUser.value;
  }

  authStore.login(new UserCredentials(username, password)).then(async () => {
    console.log('login success')
    if (username.startsWith("Tatami")){
       const id = username.replace("Tatami","");
      await router.push('/tatami/'+id);
    }
    else if (username.includes("Weeg")) await router.push('/weight')
    else if (username.includes("Info")) await router.push('/info')
    else await router.push('/home')
  }).catch(() => {
    console.log('login error')
    showError.value = true
  })
}
</script>

<style scoped>

</style>