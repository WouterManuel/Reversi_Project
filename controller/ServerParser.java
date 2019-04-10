package controller;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerParser {

        ServerListener listener;

        public ServerParser(ServerListener listener) {
            this.listener = listener;
        }
        /**
         * Parses the output from the controller.server and puts each message in an arrayList.
         * By going through the elements in the array the appropriate action is selected.
         * -
         * Any information about a game on the controller.server is stored in the parseGameOutput arrayList
         * and handled by a second switch statement.
         * */

        public ArrayList<String> parseServerOutput(String s) {
			ArrayList<String> outputList = new ArrayList<>(Arrays.asList(s.split(" ")));
			ArrayList<String> playerlist = new ArrayList<>();

            if (outputList.size() >= 1) {
                if(outputList.get(0).equals("ERR")){
                    return outputList;
                }
                if(outputList.get(0).equals("OK")){
                    return outputList;
                }
                else {
                    switch (outputList.get(1)) {
                        case "PLAYERLIST":
                            return listIterator(outputList, playerlist, 2);

                        case "GAME":
                            ArrayList<String> gameInfo = parseGameOutput(outputList);
                            return gameInfo;

                        default:
                            return null;
                    }
                }
            }
            return null;
        }

    /**
     * Method that handles handles all controller.server messages related to game information
     * @param list
     * @return
     */
    public ArrayList<String> parseGameOutput(ArrayList<String> list) {
        ArrayList<String> temp = new ArrayList<>();
            switch(list.get(2)) {
                case "MATCH":
                    listener.notifyObservers(listIterator(list, temp, 2));
					break;
                // TODO
                // Check if the PLAYERTOMOVE name = the clients name.
                // If so, the player on the client starts the game and is notified.

                //Handles the move message from the controller.server to store the value in temp
                // and then passing it on to the model to set the last move to the receiver value
                case "MOVE":
                    listener.notifyObservers(listIterator(list, temp, 2));
					break;

                case "CHALLENGE":
                    if(list.get(3).equals("CANCELLED")) {
                        listIterator(list, temp, 4);
                    } else listIterator(list, temp, 3);
					break;

                case "YOURTURN":
                    listener.notifyObservers(listIterator(list, temp, 2));
					break;

                case "WIN":
                    listIterator(list, temp, 3);
					break;

                case "DRAW":
                    listIterator(list, temp, 3);
					break;

                case "LOSS":
                    listIterator(list, temp, 3);
					break;
            }
            return null;
        }

        private static ArrayList<String> listIterator(ArrayList<String> list, ArrayList<String> temp, int index) {
            for(String item : list.subList(index, list.size())) {
                String player = regexStringClean(item);
                temp.add(player);
            }
            return temp;
        }

        private static String regexStringClean(String s) {
            return s.replaceAll("\"|\\[|]|,|:|\\{|}", "");
        }
}
