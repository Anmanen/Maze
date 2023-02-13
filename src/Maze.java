import java.io.*;

public class Maze {

    static String fileName;
    static int mazeWidth;

    static int mazeHeight;

    static char[][] mazeArray;

    public Maze(String fileName){
        Maze.mazeHeight = calculateMazeHeight(fileName);
        Maze.mazeWidth = calculateMazeWidth(fileName);
        this.fileName = fileName;
        printMaze(this.fileName);
        Maze.mazeArray = createMazeArray();
        Game game = new Game();
        game.startMoving();
    }
    public char[][] createMazeArray(){
        String line = "";
        int lineCounter = 0;
        mazeArray = new char[mazeHeight][mazeWidth];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = (String) reader.readLine()) != null){
                for (int i = 0; i < line.length(); i++){
                        mazeArray[lineCounter][i] = line.charAt(i);
                }
                lineCounter++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return mazeArray;
    }

    public int calculateMazeWidth(String fileName) {
        String firstLine = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            firstLine = (String) reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return firstLine.length();
    }

    public int calculateMazeHeight(String fileName){
        int numberOfLines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            do {
                numberOfLines += 1;
            } while (reader.readLine() != null);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numberOfLines;
    }


    public void printMaze(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
           while (true){
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    break;
                }
            }
            System.out.println("Starting point is marked with '^'");
            System.out.println("Exit points are marked with 'E'");
           reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
