package be.ita.toernooimanager.service.util.competitiontree;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;

import java.util.*;
import java.lang.reflect.*;

public class CreateCompetitionPool {
    /**
     * Key of tree contains a concatenation of pool name and position in pool separated by a colon;<br>
     * Example:<br>
     * "A:0" for Pool A, position 0<br>
     * "D:4" for Pool D, position 4<br>
     * "E:1" for Pool A, position 1<br>
     * "C:5"for Pool A, position 5 (Note: according to regulations a pool of size > 5 is not possible, hence this would be illegal, however the datastructure does allow this)
     * @param competitors list of all competitors in the Pool
     * @return
     */
    public static HashMap<String, UUID> createCompetitionPool(List<Competitor> competitors, int poolCount){
        //First sort competitors
        competitors.sort(Competitor.createCompetitorLambdaComparator());

        //Create pools
        List<Pool> pools = new ArrayList<>();
        for (int i = 0; i < poolCount; i++){
            pools.add(new Pool(CreateCompetitionPool.nameFromInt(i)));
        }

        //Assign competitors
        for (int i = 0; i < competitors.size(); i++){
            Pool currentPool = pools.get(i%poolCount);
            assert(currentPool != null);
            currentPool.competitors.add(competitors.get(i));
        }

        //Make sure all rules regarding pool ordering are valid
        for (Pool pool : pools) {
            pool.validate();
        }

        //Build hashmap
        HashMap<String, UUID> map =  new HashMap<>(competitors.size());
        for (Pool pool: pools){
            for (int i = 0; i < pool.competitors.size(); i++) {
                map.put(pool.name + ":" + i, pool.competitors.get(i).getId());
            }
        }
        return map;
    }

    /**
     * Convert a number to a string. Number must be zero based.<br>
     * 0 => A<br>
     * 15 => Q<br>
     * 25 => Z<br>
     * 26 => AA <br>
     * @param number
     * @return
     */
    private static String nameFromInt(int number){
        if (number < 0) return "";
        if (number < 26){
            return Character.toString((char) (number+0x41));
        }
        else{
            return nameFromInt(number/26-1) + (char) (number % 26 + 0x41);
        }
    }
    private static class Pool{
        String name;
        List<Competitor> competitors = new ArrayList<>();

        public Pool(String name) {
            this.name = name;
        }

        /**
         * Make sure the pool complies with the rules;
         * Rule 1: 2 competitors from the same club must fight first (assumption only 1 pair with equal clubs exists)
         */
        public void validate(){
            validateRule1();
        }
        private void validateRule1(){
            List<Competitor> sortedList = competitors.stream().sorted(Comparator.comparing(Competitor::getClub)).toList();
            //Look for 2 consecutive elements from the same club
            Club duplicateClub = null;
            for (int i = 0; i < sortedList.size() - 1; i++){
                if (sortedList.get(i).getClub().equals(sortedList.get(i+1).getClub())){
                    duplicateClub = sortedList.get(i).getClub();
                    break; //Assumption only 1 duplicate is present
                }
            }
            if (duplicateClub == null) return; //No 2 competitors from the same club

            //Duplicate found, make sure they are placed in position 1 and 2
            int currentIndexCompetitor1 = -1;
            int currentIndexCompetitor2 = -1;
            for (int i = 0; i < this.competitors.size(); i++) {
                if (!this.competitors.get(i).getClub().equals(duplicateClub)) continue;
                if (currentIndexCompetitor1 == -1) currentIndexCompetitor1 = i;
                else currentIndexCompetitor2 = i;
            }

            assert(currentIndexCompetitor1 != currentIndexCompetitor2); //This should never be possible!

            if (currentIndexCompetitor1 == 0 && currentIndexCompetitor2 == 1) return; //Condition is already satisfied;

            //swap current indexes with the target indices
            Collections.swap(this.competitors, currentIndexCompetitor1,0);
            Collections.swap(this.competitors, currentIndexCompetitor2,1);

            //Rule 1 should be satisfied now
        }
        private Map<Club, Integer> countClubs(){
            HashMap<Club, Integer> count = new HashMap<>();
            for (Competitor competitor : competitors){
                if (count.containsKey(competitor.getClub())){
                    count.put(competitor.getClub(), count.get(competitor.getClub()) + 1);
                }else{
                    count.put(competitor.getClub(),1);
                }
            }
            return count;
        }
        private void filterClubCount(Map<Club, Integer> count){
            for (Club club: count.keySet()){
                if (count.get(club) <= 1){
                    count.remove(club);
                }
            }
        }

    }

}
