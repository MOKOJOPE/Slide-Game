package edu.byuh.cis.cs300.slidegameinterface.logic;
/**
 * A class representing a game board for a game of Connect 5.
 * This class provides methods for making moves, checking for wins, and drawing the game board.
 *
 */
public class GameBoard {

    private Player[][] grid;
    private final int DIM = 5;
    private Player whoseTurnIsIt;
    /**
     * Constructs a new GameBoard object.
     *
     * Initializes the game board with a 5x5 grid of blank cells and sets the current player to X.
     */
    public GameBoard() {
        //Create a 5x5 gameboard of BLANK cells
        grid = new Player[DIM][DIM];
        for (int i=0; i<DIM; ++i) {
            for (int j=0; j<DIM; ++j) {
                grid[i][j] = Player.BLANK;
            }
        }

        //Arbitrarily, we make X the first player.
        whoseTurnIsIt = Player.X;
    }
    /**
     * Submits a move to the game board.
     *
     * @param move the move to submit, either a letter (A-E) for a horizontal move or a number (1-5) for a vertical move
     * @param p the player making the move
     */
    public void submitMove(char move, Player p) {
        if (move >= '1' && move <= '5') {
            //vertical move, move tokens down
            int col = (int)(move - '1');
            Player newVal = p;
            for (int i=0; i<DIM; ++i) {
                if (grid[i][col] == Player.BLANK) {
                    grid[i][col] = newVal;
                    break;
                } else {
                    Player tmp = grid[i][col];
                    grid[i][col] = newVal;
                    newVal = tmp;
                }
            }

        } else { //A-E
            //horizontal move, move tokens right
            int row = (int)(move - 'A');
            Player newVal = p;
            for (int i=0; i<DIM; ++i) {
                if (grid[row][i] == Player.BLANK) {
                    grid[row][i] = newVal;
                    break;
                } else {
                    Player tmp = grid[row][i];
                    grid[row][i] = newVal;
                    newVal = tmp;
                }
            }
        }

        //change whose turn it is
        if (whoseTurnIsIt == Player.X) {
            whoseTurnIsIt = Player.O;
        } else {
            whoseTurnIsIt = Player.X;
        }
    }
    /**
     * Checks the game board for a win.
     *
     * @return the player who won, or Player.BLANK if no one has won
     */
    public Player checkForWin() {
        Player winner = Player.BLANK;
        boolean xWins = false;
        boolean oWins = false;
        // boolean isTie = true;

        //check all rows
        for (int i=0; i<DIM; ++i) {
            if (grid[i][0] != Player.BLANK) {
                winner = grid[i][0];
                for (int j=0; j<DIM; ++j) {
                    if (grid[i][j] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    // return winner; //5 in a row!

                    //instead of return winner; we can write into a if statement
                    if(winner == Player.X) {
                        xWins = true;
                    }else if(winner == Player.O){
                        oWins = true;
                    }
                }
            }
        }

        //check all columns
        for (int i=0; i<DIM; ++i) {
            if (grid[0][i] != Player.BLANK) {
                winner = grid[0][i];
                for (int j=0; j<DIM; ++j) {
                    if (grid[j][i] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    // return winner; //5 in a column!
                    //instead of return winner; we can write into a if statement
                    if(winner == Player.X) {
                        xWins = true;
                    }else if(winner == Player.O){
                        oWins = true;
                    }
                }
            }
        }


        //check top-left -> bottom-right diagonal
        if (grid[0][0] != Player.BLANK) {
            winner = grid[0][0];
            for (int i=0; i<DIM; ++i) {
                if (grid[i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                // return winner; //5 in a diagonal!
                //instead of return winner; we can write into a if statement
                if(winner == Player.X) {
                    xWins = true;

                }else if(winner == Player.O){
                    oWins = true;
                }
            }
        }


        //check bottom-left -> top-right diagonal
        if (grid[DIM-1][0] != Player.BLANK) {
            winner = grid[DIM-1][0];
            for (int i=0; i<DIM; ++i) {
                if (grid[DIM-1-i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                // return winner; //5 in a diagonal!
                //instead of return winner; we can write into a if statement
                if(winner == Player.X) {
                    xWins = true;
                }else if(winner == Player.O){
                    oWins = true;
                }
            }
        }


        //return winner;

        if(xWins == true && oWins == false){
            return Player.X;
        }else if(xWins == false && oWins == true){
            return Player.O;
        }else if(xWins == true && oWins == true){
            return Player.TIE;}
        else{
            return Player.BLANK;}

    }

    public void consoleDraw() {
        System.out.print("  ");
        for (int i=0; i<DIM; ++i) {
            System.out.print(i+1);
        }
        System.out.println();
        System.out.print(" /");
        for (int i=0; i<DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("\\");
        for (int i=0; i<DIM; ++i) {
            System.out.print(((char)('A'+i)) + "|");
            for (int j=0; j<DIM; ++j) {
                if (grid[i][j] == Player.BLANK) {
                    System.out.print(" ");
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.print(" \\");
        for (int i=0; i<DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("/");

    }

    public Player getCurrentPlayer() {
        return whoseTurnIsIt;
    }
}
