import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String firstMazeFile = "C:\\Users\\amker\\Idea\\Maze\\src\\firstMaze.txt";
        String secondMazeFile = "C:\\Users\\amker\\Idea\\Maze\\src\\secondMaze.txt";
        System.out.println("Choose the maze: 1) easy 2) moderate");
        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1) {
            Maze maze = new Maze(firstMazeFile);
        }
        if (option == 2){
            Maze maze = new Maze(secondMazeFile);
        }

    }
}
