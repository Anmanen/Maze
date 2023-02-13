import java.util.ArrayList;

public class Game {

    private char[][] gameArray;

    private int[] locationPoint;

    private ArrayList<Integer> possibleMovesList;
    private boolean endPointFound;

    private boolean isMoving;
    private int moves;

    char routeChar;
    public Game(){
        this.gameArray = Maze.mazeArray;
        this.possibleMovesList = new ArrayList<>();
        this.locationPoint = new int[2];
        this.endPointFound = false;
        this.isMoving = true;
        this.routeChar = 'a';
        this.moves = 0;
    }
    public void startMoving(){
        firstAlgorithm();
        if (((moves > 20) && (Maze.fileName.contains("first"))) || ((moves > 50) && (Maze.fileName.contains("second")))){
            System.out.println("Try to code more efficient algorithm!");
        }

    }
    public void firstAlgorithm(){
        while ((isMoving) && (!endPointFound)){
            int[] location = findLocationPoint();
            findPossibleMoves(location);
            moveUp(location);
            if (!isMoving){
                moveLeft(location);
                if (!isMoving){
                    moveRight(location);
                    if (!isMoving){
                        moveDown(location);
                        if ((!isMoving) && (!endPointFound)){
                            System.out.println("Dead end " + routeChar);
                            setNewLocationPoint();
                            routeChar++;
                            firstAlgorithm();
                        }
                    }
                }
            }

        }

    }
    public void setNewLocationPoint(){
        for (int i = possibleMovesList.size(); i > 1; i= i-2){
            int first = possibleMovesList.get(i-2);
            int second = possibleMovesList.get(i-1);
            if (gameArray[first][second] == ' '){
                removeLocationPoint();
                gameArray[first][second] = '^';
                isMoving = true;
                break;
            }
        }
    }

    public void removeLocationPoint(){
        for (int i = 0; i < Maze.mazeHeight; i++) {
            for (int j = 0; j < Maze.mazeWidth; j++) {
                if (gameArray[i][j] == '^'){
                    gameArray[i][j] = routeChar;
                }
            }
        }
    }
    public int[] findLocationPoint() {

        for (int i = 0; i < Maze.mazeHeight; i++) {
            for (int j = 0; j < Maze.mazeWidth; j++) {
                if (gameArray[i][j] == '^') {
                    locationPoint[0] = i;
                    locationPoint[1] = j;
                }
            }
        }
        return locationPoint;
    }


    public void moveUp(int[] location){
        if (gameArray[location[0]-1][location[1]] == ' '){
            gameArray[location[0]-1][location[1]] = '^';
            gameArray[location[0]][location[1]] = routeChar;
            isMoving = true;
            moves++;
        } else if (gameArray[location[0]-1][location[1]] == 'E'){
            endPointFound = true;
            printGameSituation();
            System.out.println("Exit was found with " + moves + " moves!");
        } else {
            isMoving = false;
        }

    }

    public void moveDown(int[] location){

        if (gameArray[location[0]+1][location[1]] == ' '){
            gameArray[location[0]+1][location[1]] = '^';
            gameArray[location[0]][location[1]] = routeChar;
            isMoving = true;
        } else if (gameArray[location[0]+1][location[1]] == 'E') {
            endPointFound = true;
            printGameSituation();
            System.out.println("Exit was found with " + moves + " moves!");
        } else {
            isMoving = false;
        }

    }

    public void moveLeft(int[] location){

        if (gameArray[location[0]][location[1]-1] == ' '){
            gameArray[location[0]][location[1]-1] = '^';
            gameArray[location[0]][location[1]] = routeChar;
            isMoving = true;
        } else if (gameArray[location[0]][location[1]-1] == 'E'){
            endPointFound = true;
            printGameSituation();
            System.out.println("Exit was found with " + moves + " moves!");
        } else {
            isMoving = false;
        }

    }

    public void moveRight(int[] location){

        if (gameArray[location[0]][location[1]+1] == ' '){
            gameArray[location[0]][location[1]+1] = '^';
            gameArray[location[0]][location[1]] = routeChar;
            isMoving = true;
        } else if (gameArray[location[0]][location[1]+1] == 'E') {
            endPointFound = true;
            printGameSituation();
            System.out.println("Exit was found with " + moves + " moves!");
        } else {
            isMoving = false;
        }

    }

    public void findPossibleMoves(int[] location){

        //down
        if (gameArray[location[0]+1][location[1]] == ' '){
            possibleMovesList.add(location[0]+1);
            possibleMovesList.add(location[1]);
        }
        //left
        if (gameArray[location[0]][location[1]-1] == ' '){
            possibleMovesList.add(location[0]);
            possibleMovesList.add(location[1]-1);
        }
        //right
        if (gameArray[location[0]][location[1]+1] == ' '){
            possibleMovesList.add(location[0]);
            possibleMovesList.add(location[1]+1);
        }
        //up
        if (gameArray[location[0]-1][location[1]] == ' '){
            possibleMovesList.add(location[0]-1);
            possibleMovesList.add(location[1]);
        }
    }
    public void printGameSituation(){
        String charChain = "";

            for (int i = 0; i < Maze.mazeHeight; i++) {
                for (int j = 0; j < Maze.mazeWidth; j++) {
                    System.out.print(gameArray[i][j]);
                }
                System.out.print("\n");
            }

        }
}
