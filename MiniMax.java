// package tictactoeJar;
import java.util.ArrayList;
import java.util.Scanner;
public class MiniMax {

    static final int MAXDEPTH = 8;

    static final int INFINITY = Integer.MAX_VALUE;

    public MoveScore minimax(Board mBoard,int player,int currentDepth) {//intial player -1
        MoveScore best = new MoveScore();
        //kondisi berhenti, gameover atau sudah terlalu dalam
        if(mBoard.isGameOver()||currentDepth==MAXDEPTH){
            best.score = mBoard.evaluasi(player);
            best.move = -1; //tidak ada move
        }else{
            best.move = -1;
            if(mBoard.currentPlayer()==player)
                best.score = -INFINITY;  //cari maks
            else
                best.score = INFINITY;
				
            ArrayList<Integer> alMoves = mBoard.getMoves();//yang masih bernilai 0
            for(int move:alMoves){
                Board newBoard = mBoard.makeMove(move);
				
				MoveScore currentMS = minimax(newBoard,player,currentDepth+1); //rekursif

                if(mBoard.currentPlayer()==player){
                    if (currentMS.score>best.score){
                        best.score = currentMS.score;
                        best.move = move;
                    }
                }else{
                    if(currentMS.score<best.score){
                        best.score =currentMS.score;
                        best.move = move;
                    }
                }
            }
			// best.print();
        }
        return best;
    }

    public MoveScore getBestMove(Board mBoard,int player) {
        MoveScore out;
        out  = minimax(mBoard, player,0);
		// System.out.println("-----------------------------------");
		// out.print();
        return out;
    }
}

