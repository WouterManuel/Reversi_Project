package server;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerParser {

        /**
         * Parses the output from the server and puts each message in an arrayList.
         * By going through the elements in the array the appropriate action is selected.
         * -
         * Any information about a game on the server is stored in the parseGameOutput arrayList
         * and handled by a second switch statement.
         * */

        public static ArrayList parseServerOutput(String s) {
            ArrayList<String> outputList = new ArrayList<>(Arrays.asList(s.split(" ")));
            ArrayList<String> playerlist = new ArrayList<>();

            if (outputList.size() > 1) {
                if(outputList.get(1).equals("ERR")){
                    return outputList;
                }
                else {
                    switch (outputList.get(1)) {
                        case "PLAYERLIST":
                            for(String item : outputList.subList(2, outputList.size()-1)) {
                                String player = regexStringClean(item);
                                playerlist.add(player);
                            }
                            return playerlist;

                        case "GAME":
                            ArrayList gameInfo = parseGameOutput(outputList);
                            return gameInfo;

                        default:
                            return null;
                    }
                }
            }
            return null;
        }

    /**
     * Method that handles handles all server messages related to game information
     * @param list
     * @return
     */
    public static ArrayList parseGameOutput(ArrayList<String> list) {
        ArrayList<String> temp = new ArrayList<>();
            switch(list.get(2)) {
                case "MATCH":
                    gamelistIterator(list, temp);
                // TODO
                // Check if the PLAYERTOMOVE name = the clients name.
                // If so, the player on the client starts the game and is notified.

                //Handles the move message from the server to store the value in temp
                // and then passing it on to the model to set the last move to the receiver value
                case "MOVE":
                    gamelistIterator(list, temp);

                case "CHALLENGE":
                    gamelistIterator(list, temp);

                case "YOURTURN":
                    gamelistIterator(list, temp);

                case "WIN":
                    gamelistIterator(list, temp);

                case "DRAW":
                    gamelistIterator(list, temp);

                case "LOSS":
                    gamelistIterator(list, temp);
            }
            return null;
        }

        private static ArrayList<String> gamelistIterator(ArrayList<String> list, ArrayList<String> temp) {
            for(String item : list.subList(3, list.size()-1)) {
                String player = regexStringClean(item);
                temp.add(player);
            }
            return temp;
        }

        private static String regexStringClean(String s) {
            return s.replaceAll("\"|\\[|]|,|:|\\{|}", "");
        }
}
