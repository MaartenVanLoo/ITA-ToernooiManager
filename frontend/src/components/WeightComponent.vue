<template>
  <!-- Error alert -->
  <v-alert
      v-model="showError"
      border="start"
      variant="tonal"
      closable
      close-label="Close Alert"
      color="error"
      :title="errorMessage">
  </v-alert>

  <v-form class="pa-4" @submit.prevent="getCompetitor(weightId)">
    <v-row no-gutters>
      <v-spacer/>
      <v-col cols="12" md="6" class="pl-md-2">
        <v-text-field
            v-model="weightId"
            id="Weight ID"
            label="Weight ID"
            color="primary"
            variant="underlined"
            type="number"
        />
      </v-col>
      <v-spacer/>
    </v-row>
    <v-btn type="submit" block class="mt-2">Submit</v-btn>
  </v-form>
  <v-form class="pa-4"
          @submit.prevent="updateCompetitor">
    <v-row no-gutters>
      <v-spacer/>
      <h2 v-if="currentCompetitor.id !== undefined && currentCompetitor.id !== ''">{{currentCompetitor.firstName}} {{currentCompetitor.secondName}}</h2>
      <h2 v-else>somethiing</h2>
      <v-spacer/>
    </v-row>


    <v-row no-gutters>
      <v-spacer/>
      <v-col cols="12" md="6" class="pl-md-2">
        <v-text-field
            v-model="currentCompetitor.weight"
            label="Weight"
            id="Weight"
            color="primary"
            variant="underlined"
            type="number"
            suffix="kg"
        />
      </v-col>
      <v-spacer/>
    </v-row>

    <v-btn type="submit" block class="mt-2">Submit</v-btn>
  </v-form>
</template>

<script lang="ts">

export default {
  name: "WeightComponent"
}
</script>

<script setup lang="ts">
import {Competitor} from "@/models/Competitor";
import {ref} from "vue";
import {getCompetitorByWeightId} from "@/models/Utils/Requests";
import {useTournamentStore} from "@/stores/TournamentStore";

const currentCompetitor = ref<Competitor>(new Competitor("",0,"","",0,0,0,"",""))
const weightId= ref<number>(0);

const errorMessage = ref<string>("");
const showError = ref<boolean>(false);

function init(){
  currentCompetitor.value = new Competitor("",0,"","",0,0,0,"","");
}
init()


function getCompetitor(id: number){
  getCompetitorByWeightId(id)
      .then(res=>{
        currentCompetitor.value = res;
      })
      .catch((error)=>{
        currentCompetitor.value = new Competitor("",0,"","",0,0,0,"","");
        errorMessage.value = "Wrong weight id"
        showError.value = true;
      })
}
function updateCompetitor(){

}
</script>

<style scoped>

</style>