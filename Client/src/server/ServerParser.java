package server;

import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;

public class ServerParser {

        public static List<String> tempList = new ArrayList<>();

        public ServerParser() {
        }

        /** Parses the output from the server and puts each message in an arrayList.
         * By going through the elements in the array the appropriate action is selected.
         * - Any information about a game on the server is stored in the parseGame arrayList
         * and handled by a second switch statement.*/
        public static List parseOutput(String s) throws Exception {
            String[] list = s.split(" ");
            List<String> playerList = new ArrayList<>();
            String error = "";

            // If the first element in the arrayList is an ERR set the message as string value of variable ErrorMessage in the GUI controller
            if(list[0].equals("ERR")){
                for(int i = 1 ; i<= (list.length-1); i++){
                    error += regexStringClean(list[i]) + " ";
                }
                gui.errorMessage = error.trim() + ".";
            }

            if (list.length > 1){
                switch (list[1]) {
                    // Gets players from server and adds each player to a list
                    case "PLAYERLIST":  for(int i = 2 ; i<= (list.length-1); i++){
                        String player = regexStringClean(list[i]);
                        break;
                    }
                        model.playerList = playerList;
                        break;
                    case "GAME": List gamelist =  parseGame(list);
                        return gamelist;
                    default: return null;
                }
            }

            return null;
        }

    /**
     * Method that handles handles all server messages related to game information
     * @param list
     * @return
     */
    private static List parseGame(String[] list) {
            tempList.clear();
            switch(list[2]) {
                case "MATCH":   //Handles information related to a MATCH message from the server side.
                                //Splits information and puts it into an arrayList.

                    //check if the PLAYERTOMOVE name = the clients name.
                    //If so, the player on the client starts the game and is notified.
                    break;

                case "MOVE":    //Handles the move message from the server to store the value in tempList
                                // and then passing it on to the model to set the last move to the receiver value
                    break;
                case "CHALLENGE":   //Catches the CHALLENGE message from the server to store the related information about the
                                    //challenge in an arrayList.
                    break;

                case "YOURTURN":    //Catches the YOURTURN messsage from the server to notify the game that it is the clients' turn.
                    controller.setAllowMoves(true);
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            controller.myTurnLabel();
                            if (gui.isAI){
                                controller.playAIMove();
                            }
                        }
                    });
                    break;

                case "WIN": //Set result to 'win' and updates the endGame results that puts a message on screen.
                    break;

                case "DRAW": //Set result to 'draw' and updates the endGame results that puts a message on screen.
                    break;

                case "LOSS": //Set result to 'loss' and updates the endGame results that puts a message on screen.
                    break;

            }
            return null;
        }

        /** Regex method for cleaning the server data output, so it will be possible store the information cleanly as String values*/
        private static String regexStringClean(String s) {
            return s.replaceAll("\"|\\[|]|,|:|\\{|}", "");
        }
}
