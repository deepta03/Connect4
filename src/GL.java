import java.util.Arrays;

public class GL implements GameLogic {


    private int size;
    private Status[][] gameBoard;
    private HumanPlayer P1;
    private AI P2;
    private Status currentPlayer;


    public GL() {

        size = (int) (Math.random() * (13 - 6 + 1) + 6);
        gameBoard = new Status[size][size];

        for (Status[] s : gameBoard) {
            Arrays.fill(s, Status.NEITHER);
        }

        P1 = new HumanPlayer();
        P1.setInfo(size, this);
        P2 = new AI();
        P2.setInfo(size, this);

        currentPlayer = choosePlayer();

    }

    private Status choosePlayer() {

        Status result = null;

        int random = (int) Math.random() * 2;

        if (random == 0)
            result = Status.ONE;
        else if(random==1)
            result = Status.TWO;

        return result;
    }


  public void setAnswer(int col) {

      if (!checkWinner()) {



          if (col == -1) {


              if (currentPlayer == Status.ONE)

                  P1.lastMove(col);

              else if (currentPlayer == Status.TWO)
                  P2.lastMove(col);
          }


          else

          {

              int row = drop(col);

              if (currentPlayer == Status.ONE) {
                  gameBoard[row][col] = Status.ONE;


                  currentPlayer = Status.TWO;
                  P2.lastMove(col);


              } else if (currentPlayer == Status.TWO) {

                  gameBoard[row][col] = Status.TWO;

                  currentPlayer = Status.ONE;
                  P1.lastMove(col);
              }

          }


      }

      else {


          if (currentPlayer == Status.ONE) {

              P1.gameOver(Status.TWO);
          }

          else if (currentPlayer == Status.TWO) {

              P1.gameOver(Status.ONE);
          }
      }


  }

    private boolean checkWinner() {

        boolean result = false;

        if(hasSpace()) {

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {


                    if (i < gameBoard.length - 3 && gameBoard[i][j] != Status.NEITHER && gameBoard[i][j] == gameBoard[i + 1][j] && gameBoard[i][j] == gameBoard[i + 2][j] && gameBoard[i][j] == gameBoard[i + 3][j])
                        result = true;

                    else if (j < gameBoard[i].length - 3 && gameBoard[i][j] != Status.NEITHER && gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][j] == gameBoard[i][j + 2] && gameBoard[i][j] == gameBoard[i][j + 3])

                        result = true;

                    else if (i < gameBoard.length - 3 && gameBoard[i][j] != Status.NEITHER && j <= gameBoard[i].length - 4 && gameBoard[i][j] == gameBoard[i + 1][j + 1] && gameBoard[i][j] == gameBoard[i + 2][j + 2] && gameBoard[i][j] == gameBoard[i + 3][j + 3])
                        result = true;

                    else if (i < gameBoard.length - 3 && gameBoard[i][j] != Status.NEITHER && j <= gameBoard[i].length - 4 && gameBoard[i][j] == gameBoard[i + 1][j - 1] && gameBoard[i][j] == gameBoard[i + 2][j - 2] && gameBoard[i][j] == gameBoard[i + 3][j - 3])
                        result = true;


                }
            }

        }


        return result;
    }


    private int drop(int col) {
        int posn = 0;
        while (posn < gameBoard.length && gameBoard[posn][col] == Status.NEITHER) {
            posn++;
        }
        return posn - 1;
    }


    private boolean hasSpace()

    {

        boolean result=false;


        for(int col=0; col<gameBoard[0].length; col++ )

        {

            if (gameBoard[0][col]==Status.NEITHER)
                result=true;
        }

        return result;
    }



}
