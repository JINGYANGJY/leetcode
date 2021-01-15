package amazon.oa;

public class RoverControl {
    /*
    A Mars rover is directed to move within a square matrix.
    It accepts a sequence of commands to move in any of the four directions from each cell:
    [UP, DOWN, LEFT or RIGHT]. The rover starts from cell 0. and may not move diagonally or outside of the boundary.

    Each cell in the matrix has a position equal to:
    (row * size) + column
    where row and column are zero-indexed, size = row length of the matrix.

    Return the final position of the rover after all moves.

    Example
    n = 4
    commands = [RIGHT, UP, DOWN, LEFT, DOWN, DOWN]
     */
    /*
    row = 0, col = 0
    right col + 1
    up row - 1
    down: row + 1
    left left - 1
    row >= 0 && row < size col >= 0 && col < size
     */

    public static int roverControl(String[] commands, int n) {
        int row = 0;
        int column = 0;
        for (String s : commands) {
            if (s.equals("RIGHT")) {
                column = column < n - 1 ? column + 1 : column;
            } else if (s.equals("LEFT")) {
                column = column > 0 ? column - 1 : column;
            } else if (s.equals("UP")) {
                row = row > 0 ? row - 1 : row;
            } else {
                row = row < n - 1 ? row + 1 : row;
            }
        }
        return row * n + column;
    }
    public static void main(String[] args) {
        String[] commands = {"RIGHT", "UP", "DOWN", "LEFT", "DOWN", "DOWN"};
        System.out.println(roverControl(commands, 4));

        commands = new String[]{"RIGHT", "DOWN", "LEFT", "LEFT", "DOWN"};
        System.out.println(roverControl(commands, 4));
    }
}
