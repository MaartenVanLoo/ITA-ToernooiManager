<template>
    <v-card v-for="competition in competitions"
            :key="competition.id"
           class="mx-1 my-2">
      <v-card-title>
        {{competition.name}}
      </v-card-title>
      <v-card-actions>
        <v-btn color="primary" @click="onOpenCompetition(competition)">Open</v-btn>
      </v-card-actions>
    </v-card>

</template>

<script lang="ts">
export default {
  name: "PickWeightCompetition"
}
</script>
<script lang="ts" setup>
import {ref} from "vue";

const competitions = ref<Array<Competition>>();

function onOpenCompetition(competition: Competition){

}
</script>

<style scoped>

</style>