<template>
  <PickTournamentComponent v-if="showTournamentComponent" @tournamentSelected="onTournamentSelected()"/>
  <WeightComponent v-if="showWeightComponent"/>
</template>

<script lang="ts">
import WeightComponent from "@/components/WeightComponent.vue";
import PickTournamentComponent from "@/components/PickTournamentComponent.vue";
import { ref } from "vue";

export default {
  name: "Weight",
    components: {PickTournamentComponent, WeightComponent}
}
</script>

<script lang="ts" setup>
const showTournamentComponent = ref<boolean>(true);
const showWeightComponent = ref<boolean>(false);
function onTournamentSelected(){
  showTournamentComponent.value = false;
  showWeightComponent.value = true;
}
</script>

<style scoped>

</style>