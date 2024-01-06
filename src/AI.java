import java.util.Arrays;

public class AI implements Player {

    private GameLogic gl;
    private int size;
    private Status [][] board;


    public AI()
    {


    }

    public void lastMove(int lastCol) {

        if (lastCol != -1) {
            int lastPosn = drop(lastCol);
            board[lastPosn][lastCol] = Status.ONE;



        int aiCol= 1;
        int row=drop(aiCol);

        if(row!=-1) {
            board[row][aiCol] = Status.TWO;
            this.gl.setAnswer(aiCol);
        }

            /*int aiCol = lastCol;
            int row = drop(lastCol);
            board[row][aiCol] = Status.TWO;
            this.gl.setAnswer(aiCol);
*/
        }
    }

    public void gameOver(Status winner)

    {
        System.out.println("Game over!");
        if (winner == Status.NEITHER) {
            System.out.println("Game is a draw.");
        } else if (winner == Status.ONE) {
            System.out.println("You win.");
        } else {
            System.out.println("Computer wins.");
        }
    }



    public void setInfo(int size, GameLogic gl)

    {

        this.size=size;
        this.gl=gl;
        this.board=new Status[size][size];

        for (Status[] s : board) {
            Arrays.fill(s, Status.NEITHER);
        }


    }


    private int drop(  int col) {
        int posn = 0;
        while (posn < board.length && board[posn][col] == Status.NEITHER) {
            posn ++;
        }
        return posn-1;
    }
}
