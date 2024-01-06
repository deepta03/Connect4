public class HumanPlayer implements Human, Player  {

    private UI ui;
    private GameLogic gl;

    public HumanPlayer()
    {

        ui=new SwingGUI();
    }

    public void setAnswer(int col)
    {
        gl.setAnswer(col);


    }

    public void lastMove(int lastCol)

    {

        ui.lastMove(lastCol);


    }
    public void gameOver(Status winner)

    {
        ui.gameOver(winner);


    }
    public void setInfo(int size, GameLogic gl)

    {

        this.gl=gl;

        ui.setInfo(this, size);




    }
}
