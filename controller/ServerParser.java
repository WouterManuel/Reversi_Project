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
                            ArrayList<String> temp = new ArrayList<>();
                            listener.notifyObservers(listIterator(outputList, temp, 2));
                            break;

                        default:
                            return null;
                    }
                }
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
