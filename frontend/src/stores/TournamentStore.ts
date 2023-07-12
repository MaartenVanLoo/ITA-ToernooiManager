import {defineStore} from "pinia";
import {Tournament} from "@/models/Tournament";
import {Ref, ref} from "vue";

export const useTournamentStore = defineStore('tournamentStore', () => {
    const tournament: Ref<Tournament |undefined> = ref<Tournament|undefined>(undefined);

    function save(){
        sessionStorage.setItem("tournamentStore/tournament",JSON.stringify(tournament.value));
    }
    function load(){
        const jsonTournament = sessionStorage.getItem("tournamentStore/tournament");
        if (jsonTournament) tournament.value = JSON.parse(jsonTournament);
    }
    function init(){
        try {
            load()
        }catch{}
    }
    init()

    function setTournament(selectedTournament: Tournament): void{
        tournament.value = selectedTournament;
        save();
    }
    function getTournament(): Tournament|undefined{
        return tournament.value
    }

    return {setTournament, getTournament }
})