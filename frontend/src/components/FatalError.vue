<template>
  <!--Fatal error message-->
  <v-dialog
    v-model="showDialog"
    width="auto"
    persistent
  >
    <v-card color="error">
      <v-card-title color="error">Fatal error</v-card-title>
      <v-card-item v-for="text in dialogText" density="compact">
        {{text}}<br>
      </v-card-item>
      <v-card-actions>
        <v-btn color="black" block @click="router.push('/home')">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style>

</style>

<script lang="ts" setup>
//Executed for each instance (in setup() scope)
import {ref} from "vue";
import {Router, useRouter} from "vue-router";
const router = useRouter()

const showDialog = ref<boolean>(false);
const dialogText = ref<Array<string>>([]);

function show(){
  showDialog.value = true;
}
async function throwFatalError(){
  dialogText.value.push("You will automatically be redirected to the home screen in 5 seconds.");
  show()
  const currentRoute = currentPath(router)
  await new Promise(resolve => setTimeout(resolve, 5000));
  if (currentPath(router) == currentRoute) { //only redirect if still on the same page!
    await router.push("/home");
  }
}
function hide(){
  showDialog.value = false;
}
function getText():string[]{
  return dialogText.value;
}
function clear(){
  dialogText.value = []
}
defineExpose({
  show,
  hide,
  getText,
  clear,
  throwFatalError
});

function currentPath(router:Router){
  return router.currentRoute.value.fullPath
}

</script>


<script lang="ts">
//Executed only once
export default {
  name:'FatalError',
}
</script>
