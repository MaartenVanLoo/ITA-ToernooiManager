<template>
  <PickTournamentcomponent @tournamentSelected=""/>
  <!--<WeightComponent/>-->
</template>

<script>
import WeightComponent from "@/components/WeightComponent.vue";
import PickTournamentcomponent from "@/components/PickTournamentcomponent.vue";

export default {
  name: "Weight",
    components: {PickTournamentcomponent, WeightComponent}
}
</script>

<style scoped>

</style>