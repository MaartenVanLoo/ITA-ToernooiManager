<template>
<v-hover v-for="tournament in tournaments"
         :key="tournament.id"
         >
 <template v-slot:default="{ isHovering, props }">
    <v-card v-bind="props" :color = "isHovering?'cards_hover':'cards'" class="mx-1 my-2">
      <v-card-title>
        {{tournament.name}}
      </v-card-title>
      <v-card-actions>
        <v-btn color="primary" :onclick="onOpenTournament(tournament)">Open</v-btn>
      </v-card-actions>
    </v-card>
 </template>
</v-hover>
</template>

<script lang="ts">
import {useTournamentStore} from "@/stores/TournamentStore";

export default {
  name: "PickTournamentcomponent"
}
</script>

<script lang="ts" setup>
import {useTournamentStore} from "@/stores/TournamentStore";
import {ref} from "vue";
import {Tournament} from "@/models/Tournament";
import {getAllTournaments} from "@/models/Utils/Requests"
const tournamentStore = useTournamentStore();
const tournaments = ref<Array<Tournament>>();

const emit = defineEmits<{
  (e: 'tournamentSelected', id: string): void
}>()
function init(){
  console.log("init");
  getAllTournaments()
      .then(result=>{
        tournaments.value = result.content;
      });
}
init()


function onOpenTournament(tournament: Tournament){
  tournamentStore.setTournament(tournament);
  emit('tournamentSelected', tournament.id);
  console.log("Tournament: " + tournament.id)
}
</script>

<style scoped>

</style>