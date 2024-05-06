import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input parameters
        int Q = scanner.nextInt();
        int S = scanner.nextInt();
        int F = scanner.nextInt();
        int N = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        // Read states
        String[] states = scanner.nextLine().split(" ");

        // Read symbols
        String[] symbols = scanner.nextLine().split(" ");

        // Populate actionOnStates map
        Map<String, Map<String, String>> actionOnStates = new HashMap<>();
        for (String state : states) {
            Map<String, String> innerMap = new HashMap<>();
            for (String symbol : symbols) {
                innerMap.put(symbol, null);
            }
            actionOnStates.put(state, innerMap);
        }

        // Process transitions
        for (long q = 0; q < (new Long(Q) * new Long(S)); q++) {
            String[] newCase = scanner.nextLine().split(" ");
            String initialState = newCase[0];
            String move = newCase[1];
            String finiteState = newCase[2];

            if (actionOnStates.containsKey(initialState)) {
                Map<String, String> innerMap = actionOnStates.get(initialState);
                if (innerMap.containsKey(move)) {
                    innerMap.put(move, finiteState);
                }
            }
        }

        // Read first and last states
        String firstState = scanner.next();
        List <String> lastState = new ArrayList<>();
        for (int f = 0; f < F; f++){
            String ls = scanner.next();
            lastState.add(ls);
        }

        // Process input strings and obtain results
        List<String> result = new ArrayList<>();

        for (int n = 0; n < N; n++){
            String location = firstState;
            String newWord = scanner.next();
            for (int l = 0; l < newWord.length(); l++) {
                String letter = newWord.substring(l, l + 1);
                if (letter.equals("_")) {
                    if (lastState.contains(location)) {
                        result.add("A");
                    } else {
                        result.add("R");
                    }
                    location = "_";
                } else {
                    if (actionOnStates.containsKey(location)) {
                        location = actionOnStates.get(location).get(letter);
                    }
                }
            }
            if (lastState.contains(location)) {
                result.add("A");
            } else if (actionOnStates.containsKey(location)){
                result.add("R");
            }
        }
        for (int r = 0; r < result.size(); r++){
            System.out.print(result.get(r) + " ");
        }
    }
}
