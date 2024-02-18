// Program for Four in row  game
// Michael Maseko
// 01-12-2022
import java.util.*;
public class Four_in_a_row {
    static String[][] matrix = new String[15][15];
    static String variable;
    static String position;
    static int row;
    static int column;
    static Scanner input = new Scanner(System.in);
    static HashMap<String, Integer> positions_empty_row = new HashMap<>();
    static HashMap<String, Integer> positions_column = new HashMap<>();
    static ArrayList<String> positions_list = new ArrayList<>();
    static String[][] create_grid(){
        for (row = 0; row < 13; row++) {
            for (column = 0; column < 15; column++) {
                matrix[row][column] = "   ";
            }
        }
        int row2n;
        int column2n;
        for (row2n = 0; row2n < 15; row2n += 2) {
            for (column2n = 1; column2n < 14; column2n += 2) {
                matrix[row2n][column2n] = "---";
                matrix[column2n][row2n] = " | ";
            }
        }
        return matrix;
    }
    static void drawGrid(String[][] matrix){
        System.out.println("     A     B     C     D     E     F     G     ");
        for (row = 0; row < 13; row ++){
            System.out.print(" ");
            for (column = 0; column < 15; column ++){
                System.out.print(matrix[row][column]);
            }
            System.out.print("\n");
        }
    }
    static String check_validity(String position,String variable){
        positions_list.add(position);
        String input_options = "ABCDEFG";
        if (position.length() != 1){
            System.out.println("Your response should be just 1 characters, the Letter for the column, no space or " +
                    "anything else, eg.'B'.\nEnter the position where you want to place " +variable + ':');
            position = input.nextLine().toUpperCase();
            positions_list.add(position);
            return (check_validity(position, variable));
        } else if (!(input_options.contains(position))) {
            System.out.println("Invalid input, please enter just one of the alphabets between A and G that corresponds " +
                    "to where you want to place " + variable);
            position = input.nextLine().toUpperCase();
            positions_list.add(position);
            return check_validity(position, variable);
        } else if (positions_empty_row.get(position.toUpperCase()) < 0){
            System.out.println("Sorry, the column is full, try another column:");
            position = input.nextLine().toUpperCase();
            positions_list.add(position);
            return check_validity(position, variable);
        }
        return positions_list.get(positions_list.size() - 1);
    }
    static void insert(String[][] matrix, String position, String variable){
        matrix[positions_empty_row.get(position.toUpperCase())][positions_column.get(position.toUpperCase())] = variable;
        positions_empty_row.put(position, positions_empty_row.get(position) -2);
    }
    static String check_left_right(String[][] matrix, int row) {
        if (row <= 11) {
            for (column = 1; column < 8; column += 2) {
                if (matrix[row][column].equals(" X ") && matrix[row][column].equals(matrix[row][column + 2]) && matrix
                        [row][column + 2].equals(matrix[row][column + 4]) && matrix[row][column + 4].equals(matrix[row]
                        [column + 6])) {
                    return "X";
                } else if (matrix[row][column].equals(" O ") && matrix[row][column].equals(matrix[row][column + 2]) &&
                        matrix[row][column + 2].equals(matrix[row][column + 4]) && matrix[row][column + 4].equals(matrix
                        [row][column + 6])) {
                    return "O";
                }
            }return check_left_right(matrix, row + 2);
        } else return "none";
    }
    static String check_up_down(String[][] matrix,int column){
        if (column <= 13) {
            for (row = 1; row < 6; row += 2) {
                if (matrix[row][column].equals(" X ") && matrix[row][column].equals(matrix[row + 2][column]) && matrix
                        [row + 2][column].equals(matrix[row + 4][column]) && matrix[row + 4][column].equals(matrix
                        [row + 6][column])) {
                    return "X";
                } else if (matrix[row][column].equals(" O ") && matrix[row][column].equals(matrix[row + 2][column]) &&
                        matrix[row + 2][column].equals(matrix[row + 4][column]) && matrix[row + 4][column].equals(
                                matrix[row + 6][column])) {
                    return "O";
                }
            }return check_up_down(matrix, column + 2);
        } else return "none";
    }
    static String check_botLeft_topRight(String[][] matrix) {
        //Start checking diagonals from the first diagonal having at least 4 diagonal boxes
        if (matrix[5][1].equals(" X ") && matrix[5][1].equals(matrix[7][3]) && matrix[7][3].equals(matrix[9][5]) &&
                matrix[9][5].equals(matrix[11][7])) {
            return "X";
        } else if (matrix[5][1].equals(" O ") && matrix[5][1].equals(matrix[7][3]) && matrix[7][3].equals(matrix[9][5])
                && matrix[9][5].equals(matrix[11][7])) {
            return "O";
        }
        // The ff is for checking the diagonal from D
        if (matrix[1][7].equals(" X ") && matrix[1][7].equals(matrix[3][9]) && matrix[3][9].equals(matrix[5][11]) &&
                matrix[5][11].equals(matrix[7][13])){
            return "X";
        } else if (matrix[1][7].equals(" O ") && matrix[1][7].equals(matrix[3][9]) && matrix[3][9].equals(matrix[5][11])
                && matrix[5][11].equals(matrix[7][13])){
            return "O";
        }
        int index;
        for (index = 1; index < 7; index += 2){
            if (matrix[index][index].equals(" X ") && matrix[index][index].equals(matrix[index + 2][index + 2]) &&
                    matrix[index + 2][index + 2].equals(matrix[index + 4][index + 4]) && matrix[index + 4][index + 4]
                    .equals(matrix[index + 6][index + 6])){
                return "X";
            } else if (matrix[index][index].equals(" O ") && matrix[index][index].equals(matrix[index + 2][index + 2])
                    && matrix[index + 2][index + 2].equals(matrix[index + 4][index + 4]) && matrix[index + 4][index + 4]
                    .equals(matrix[index + 6][index + 6])){
                return "O";
            }
            // The following is for checking the diagonal row starting from B
            else if (matrix[index][index + 2].equals(" X ") && matrix[index][index + 2].equals(matrix[index + 2][index +
                    4]) && matrix[index + 2][index + 4].equals(matrix[index + 4][index + 6]) && matrix[index + 4]
                    [index + 6].equals(matrix[index + 6][index + 8])) {
                return "X";
            } else if (matrix[index][index + 2].equals(" O ") && matrix[index][index + 2].equals(matrix[index + 2]
                    [index + 4]) && matrix[index + 2][index + 4].equals(matrix[index + 4][index + 6]) && matrix
                    [index + 4][index + 6].equals(matrix[index + 6][index + 8])) {
                return "O";
            }
        }return "none";
    }
    static String check_topLeft_botRight(String[][] matrix) {
        if (matrix[1][7].equals(" X ") && matrix[1][7].equals(matrix[3][5]) && matrix[3][5].equals(matrix[5][3]) &&
                matrix[5][3].equals(matrix[7][1])) {
            return "X";
        } else if (matrix[1][7].equals(" O ") && matrix[1][7].equals(matrix[3][5]) && matrix[3][5].equals(matrix[5][3])
                && matrix[5][3].equals(matrix[7][1])) {
            return "O";
        } else if (matrix[5][13].equals(" X ") && matrix[5][13].equals(matrix[7][11]) &&
                matrix[7][11].equals(matrix[9][9]) && matrix[9][9].equals(matrix[11][7])) {
            return "X";
        } else if (matrix[5][13].equals(" O ") && matrix[5][13].equals(matrix[7][11]) && matrix[7][11].equals
                (matrix[9][9]) && matrix[9][9].equals(matrix[11][7])) {
            return "O";
        }
        // Now from E and the corresponding
        int index1, index2;
        for (index1 = 1, index2 = 13; index1 <= 3 && index2 >= 11; index1 += 2, index2 -= 2) {
            if (matrix[index1][index2 - 4].equals(" X ") && matrix[index1][index2 - 4].equals(matrix[index1 + 2]
                    [index2 - 6]) && matrix[index1 + 2][index2 - 6].equals(matrix[index1 + 4][index2 - 8]) &&
                    matrix[index1 + 4][index2 - 8].equals(matrix[index1 + 6][index2 - 10])) {
                return "X";
            } else if (matrix[index1][index2 - 4].equals(" O ") && matrix[index1][index2 - 4].equals(matrix[index1 + 2]
                    [index2 - 6]) && matrix[index1 + 2][index2 - 6].equals(matrix[index1 + 4][index2 - 8]) &&
                    matrix[index1 + 4][index2 - 8].equals(matrix[index1 + 6][index2 - 10])) {
                return "O";
            }// Now from G
            else if (matrix[index1][index2].equals(" X ") && matrix[index1][index2].equals(matrix[index1 + 2]
                    [index2 - 2]) && matrix[index1 + 2][index2 - 2].equals(matrix[index1 + 4][index2 - 4]) &&
                    matrix[index1 + 4][index2 - 4].equals(matrix[index1 + 6][index2 - 6])){
                return "X";
            } else if (matrix[index1][index2].equals(" O ") && matrix[index1][index2].equals(matrix[index1 + 2]
                    [index2 - 2]) && matrix[index1 + 2][index2 - 2].equals(matrix[index1 + 4][index2 - 4]) &&
                    matrix[index1 + 4][index2 - 4].equals(matrix[index1 + 6][index2 - 6])){
                return "O";
            }
        }return "none";
    }
    static String check_winner(String[][] matrix) {
        if (!(check_left_right(matrix, 1).equals("none"))) {
            return check_left_right(matrix, 1);
        } else if (!(check_up_down(matrix, 1).equals("none"))) {
            return check_up_down(matrix, 1);
        } else if (!(check_botLeft_topRight(matrix).equals("none"))) {
            return check_botLeft_topRight(matrix);
        } else if (!(check_topLeft_botRight(matrix).equals("none"))) {
            return check_topLeft_botRight(matrix);
        } else return "none";
    }
    static void stay_leave(String decision){
        if (decision.equals("1")) {
            reset_empty_rows();
            matrix = create_grid();
            Multiplayer_main(matrix, 0);
            if ((check_winner(matrix).equals("none"))) {
                System.out.println("Game over, It's a draw!! Thanks for playing!, see you again.");
            } else {
                System.out.println("Game over!! The winner is " + check_winner(matrix) + ", Thanks for playing!, see you " +
                        "again.\nSelect 1 to play again or any other key to exit:");
            }
            decision = input.nextLine();
            if (decision.equals("!")) {
                System.out.println("We're glad to see you back again!!");
            }
            stay_leave(decision);
        } else {
            System.out.println("We're sad to see you leave, hoping to see you soon again.");
            System.exit(0);
        }
    }


    static void Multiplayer_main(String[][] matrix, int count){
        if (count < 42) {
            drawGrid(matrix);
            if (count > 6 && (!check_winner(matrix).equals("none"))){
                System.out.println("Game over!!The winner is " + check_winner(matrix) + " , Thanks for playing!, see you" +
                        " again.\nSelect 1 to play again or 0 to exit:");
                String decision = input.nextLine();
                if (decision.equals("1")){
                    System.out.println("We're glad to see you back again!!");
                }
                stay_leave(decision);
            }
            if (count % 2 == 0){
                variable = " X ";
            } else {
                variable = " O ";
            }
            System.out.println("Enter the position where you want to place "+ variable + ":");
            position = check_validity(input.nextLine().toUpperCase(), variable);
            insert(matrix, position.toUpperCase(), variable);
            Multiplayer_main(matrix, count +1);
        }
        drawGrid(matrix);
    }
    static void reset_empty_rows(){
        positions_empty_row.put("A", 11);
        positions_empty_row.put("B", 11);
        positions_empty_row.put("C", 11);
        positions_empty_row.put("D", 11);
        positions_empty_row.put("E", 11);
        positions_empty_row.put("F", 11);
        positions_empty_row.put("G", 11);
    }
    public static void main(String[] Mics){
        reset_empty_rows();
        positions_column.put("A", 1);
        positions_column.put("B", 3);
        positions_column.put("C", 5);
        positions_column.put("D", 7);
        positions_column.put("E", 9);
        positions_column.put("F", 11);
        positions_column.put("G", 13);
        System.out.println("""
                Welcome to Sir Michael's Tic-Tac-Toe Game!
                The instructions are:
                Enter the column's Alphabet where you want to enter 'X' or 'O', note that the variable 'X' or 'O' will
                go to the most bottom empty box, for example 'B'  will result in the following:""");
        matrix = create_grid();
        insert(matrix, "B", " X ");
        drawGrid(matrix);
        System.out.println("Press enter to start playing:");
        input.nextLine();
        stay_leave("1");
        if (check_winner(matrix).equals("none")){
            System.out.println("Game over, It's a draw!! Thanks for playing!, see you again.");
        } else {
            System.out.println("Game over!! The winner is " + check_winner(matrix) + ", Thanks for playing!, see you " +
                    "again.");
        }
        System.out.println("Select 1 to play again or any other key to exit:");
        String decision = input.nextLine();
        if (decision.equals("1")){System.out.println("We're glad to see you back again!!");}
        stay_leave(decision);
    }
}

