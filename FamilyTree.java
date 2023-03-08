import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTree {
    public static void main(String[] args) {

        List<Relative> relatives = Arrays.asList(
                new Relative(1, "John Smith", "John", 0),
                new Relative(2, "Jane Doe", "Jane", 0),
                new Relative(3, "Jack Smith", "Jack", 1),
                new Relative(4, "Jill Smith", "Jill", 1),
                new Relative(5, "Jamie Doe", "Jamie", 2),
                new Relative(6, "Janet Doe", "Janet", 2),
                new Relative(7, "Joey Doe", "Joey", 5));

        Map<Integer, List<Relative>> tree = new HashMap<>();

        for (Relative relative : relatives) {
            if (relative.parentId == 0) {
                if (!tree.containsKey(relative.id)) {
                    tree.put(relative.id, new ArrayList<>());
                }
            } else {
                if (!tree.containsKey(relative.parentId)) {
                    // create parent node if it doesn't exist
                    tree.put(relative.parentId, new ArrayList<>());
                }
                // add current node to parent node's list
                tree.get(relative.parentId).add(relative);
            }
        }

        // print family tree
        for (int key : tree.keySet()) {
            System.out.println(key);
            printRelatives(tree.get(key), 1, tree);
        }
    }

    private static void printRelatives(List<Relative> relatives, int level, Map<Integer, List<Relative>> tree) {
        for (Relative relative : relatives) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(relative.name);
            printRelatives(tree.getOrDefault(relative.id, new ArrayList<>()), level + 1, tree);
        }
    }
}
