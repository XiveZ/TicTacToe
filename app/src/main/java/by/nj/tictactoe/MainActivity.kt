package by.nj.tictactoe

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var Player: Int = 1;
    private var player1 = ArrayList<Int>();
    private var player2 = ArrayList<Int>();
    private  var player1WinsCounts = 0;
    private  var player2WinsCounts = 0;

    fun onClick(view: View) {
        var a: Int = 0;
        var btnID: Button = view as Button;
        when (btnID.id) {
            R.id.bt1 -> a = 1
            R.id.bt2 -> a = 2
            R.id.bt3 -> a = 3
            R.id.bt4 -> a = 4
            R.id.bt5 -> a = 5
            R.id.bt6 -> a = 6
            R.id.bt7 -> a = 7
            R.id.bt8 -> a = 8
            R.id.bt9 -> a = 9
        }
        gamePlay(a, btnID)
    }



    private fun gamePlay(numberPlayer: Int, btnID: Button) {
        if (this.Player == 1) {
            btnID.text = "X";
            btnID.setTextColor(resources.getColor(android.R.color.white))
            btnID.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light));
            player1.add(numberPlayer);
            Player = 2;
            autoplay();
        } else {
            btnID.text = "O";
            btnID.setTextColor(resources.getColor(android.R.color.white))
            btnID.setBackgroundColor(resources.getColor(android.R.color.holo_green_light));
            player2.add(numberPlayer)
            Player = 1;
        }
        winner();
    }

    private fun autoplay() {
        var emptyCells = ArrayList<Int>();

        for (cellID in 1..9){
            if (!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID);
            }
        }

        if (emptyCells.size == 0)restartGame();

        var random: Int = emptyCells[Random.nextInt(emptyCells.size)];
        var buSelected:Button?;
        buSelected = when(random){
            1 -> bt1;
            2 -> bt2;
            3 -> bt3;
            4 -> bt4;
            5 -> bt5;
            6 -> bt6;
            7 -> bt7;
            8 -> bt8;
            9 -> bt9;
            else -> bt1;
        }

        gamePlay(random, buSelected);

    }

    private fun winner() {
        var winner = -1
        if (player1.containsAll(arrayListOf(1, 2, 3)) || player1.containsAll(arrayListOf(4, 5, 6)) || player1.containsAll(arrayListOf(7, 8, 9)) ||
            player1.containsAll(arrayListOf(1, 4, 7)) || player1.containsAll(arrayListOf(2, 5, 8)) || player1.containsAll(arrayListOf(3, 6, 9))) winner = 1

        if (player2.containsAll(arrayListOf(1, 2, 3)) || player2.containsAll(arrayListOf(4, 5, 6)) || player2.containsAll(arrayListOf(7, 8, 9)) ||
            player2.containsAll(arrayListOf(1, 4, 7)) || player2.containsAll(arrayListOf(2, 5, 8)) || player2.containsAll(arrayListOf(3, 6, 9))) winner = 2

        if (winner == 1){player1WinsCounts+=1; Toast.makeText(this, "Winner player 1", Toast.LENGTH_LONG).show(); restartGame()}
        if (winner == 2) {player2WinsCounts+=1; Toast.makeText(this, "Winner player 2", Toast.LENGTH_LONG).show(); restartGame()}

    }

    fun restartGame(){
        Player = 1;
        player1.clear();
        player2.clear()

        for (index in 1..9){
            var buSelected:Button?;
            buSelected = when(index){
                1 -> bt1;
                2 -> bt2;
                3 -> bt3;
                4 -> bt4;
                5 -> bt5;
                6 -> bt6;
                7 -> bt7;
                8 -> bt8;
                9 -> bt9;
                else -> bt1;
            }
            buSelected.text = ""
            buSelected.setBackgroundResource(android.R.color.white);
            buSelected.isEnabled = true;

        }

        Toast.makeText(this, "Player 1: wins - $player1WinsCounts,\n Player2: wins - $player1WinsCounts", Toast.LENGTH_LONG).show()
    }
}
