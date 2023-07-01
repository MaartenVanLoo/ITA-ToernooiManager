package be.ita.toernooimanager.service.util.competitiontree;

import be.ita.toernooimanager.model.local.Competitor;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreateCompetitionTree {
    /**
     * Key of tree contains a concatenation of round and match ID separated by a colon;
     * Example:
     * "0:0" is the location of the first place
     * "1:0" is the first competitor of the final
     * "1:1" is the second competitor of the final
     * "4:15" is the last field of the last 16 competitors in the tree
     * @param competitors list of all competitors in the tree
     * @return
     */
    public static HashMap<String, UUID> createCompetitionTree(List<Competitor> competitors){
        //First sort competitors
        competitors.sort(Competitor.createCompetitorLambdaComparator());

        Node root = new Node();
        for (Competitor competitor: competitors){
            root.insert(competitor.getId());
        }
        return root.toHashMap();
    }

    private static class Tuple {
        int round;
        int position;

        public Tuple(int key, int timestamp) {
            this.round = key;
            this.position = timestamp;
        }

        @Override
        public String toString() {
            return round + ":" + position;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Tuple))
                return false;
            Tuple tuple = (Tuple) o;
            return round == tuple.round && position == tuple.position;
        }

        @Override
        public int hashCode() {
            return Objects.hash(round, position);
        }
    }

    /**
     * Self balancing tree
     */
    private static class Node{
        Node left = null;
        Node right = null;
        UUID uuid = null;

        Tuple nodeId;

        boolean lastInsert = true;

        public Node() {
            this.nodeId = new Tuple(0,0);
        }

        public Node(UUID uuid, Tuple nodeId) {
            this.uuid = uuid;
            this.nodeId = nodeId;
        }

        private boolean isLeafNode(){
            return this.uuid != null && this.left == null && this.right == null;
        }
        public void insert(UUID newUUID){
            if (this.uuid == null && this.left == null && this.right == null){ //initial root node
                this.uuid = newUUID;
                return;
            }

            if (this.isLeafNode()){
                Tuple leftId = new Tuple(this.nodeId.round + 1, this.nodeId.position * 2);
                Tuple rightId = new Tuple(this.nodeId.round + 1, this.nodeId.position * 2 + 1);

                //move this uuid to left
                this.left = new Node(this.uuid, leftId);
                //insert new value in right
                this.right = new Node(newUUID, rightId);
                this.uuid = null;
                return; //successful insertion;
            }
            assert(this.left != null);
            assert(this.right != null);

            //Alternate insertions to remain balanced;
            if (lastInsert) this.left.insert(newUUID);
            else this.right.insert(newUUID);
            this.lastInsert = !this.lastInsert;
        }

        public HashMap<String, UUID> toHashMap(){
            HashMap<String, UUID> tree = new HashMap<>();
            tree.put(this.nodeId.toString(), this.uuid);
            if (this.left != null) this.left.toHashMap(tree);
            if (this.right != null) this.right.toHashMap(tree);
            return tree;
        }
        private void toHashMap(HashMap<String, UUID> tree){
            tree.put(this.nodeId.toString(), this.uuid);
            if (this.left != null)  this.left.toHashMap(tree);
            if (this.right != null)  this.right.toHashMap(tree);
        }
    }
}
