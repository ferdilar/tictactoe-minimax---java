// package tictactoeJar;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Board {
    private int vCurrentPlayer=1;
    private boolean vIsGameOver = false;
    private boolean vWon = false;
	private int rows[][]={{0,2},{3,5},{6,8},{0,6},{1,7},{2,8},{0,8},{2,6}};//sisi2 persegi
    private int[] state  = new int[9];
    public void setCurrentPlayer(int cp) {
        vCurrentPlayer = cp;
    }
    public void print() {  //untuk debug, print board
      // System.out.println(); //spasi
      for (int i=0; i<9; i=i+3) {
          // System.out.println(String.format("%3d%3d%3d",state[i],state[i+1],state[i+2]) );
      }
      // System.out.println();
    }
    public ArrayList<Integer> getMoves() {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i=0;i<9;i++) {
            if (state[i]==0) {
                out.add(i);
            }
        }
        return out;
    }
    //constructor
    public Board() {
        //init board
        for (int i:state) {
            state[i]=0;
        }
    }
    public boolean isGameOver() {
        return vIsGameOver;
    }
    public boolean isWon() {
        return vWon;
    }
    public int currentPlayer() {
        return vCurrentPlayer;
    }
    public void copyTo(Board b) {
        //isi this.state ke b.state
        for (int i=0;i<9;i++) {
            b.state[i]=this.state[i];
        }
    }
    public Board makeMove(int m) {
        Board out = new Board();
        this.copyTo(out);    //copy state board ini ke board yang baru
        out.state[m]       =  this.currentPlayer();  //gerakan player
        out.vCurrentPlayer = -this.currentPlayer();  //gantian
        int player = this.currentPlayer();
        //cek kondisi menang
		for (int i=0; i<8; i++){
			int a=rows[i][0];
			int b=rows[i][1];
			if(out.state[a]==player && out.state[b]==player && out.state[(a+b)/2]==player){
				out.vIsGameOver=true;
			}
		}
		if(out.vIsGameOver==true && player==1){
			out.vWon=true;
		}
        boolean anyStep=false;
        for (int i=0;i<9;i++) {
            if (out.state[i]==0) {
                anyStep = true;
                break;
            }
        }
        if (!anyStep) {
            out.vIsGameOver = true;
        }
        return out;
    }
    public int evaluasi (int player){
        int skor=0;
		//jika move yang diambil menang terbaik
		for (int i=0; i<8; i++){
			int a=rows[i][0];
			int b=rows[i][1];
			if(state[a]==player && state[b]==player && state[(a+b)/2]==player){
				skor = skor + 10000;
			}
		}
        //jika move yang diambil, blocking lawan row dgn 2 -player 1 player
		/*---------------------------------------------------------------------------------*/
		for (int i=0; i<8; i++){
			int a=rows[i][0];
			int b=rows[i][1];
			if(state[a]==player && state[b]==-player && state[(a+b)/2]==-player){
				skor = skor + 1500;
			}
			if(state[a]==-player && state[b]==player && state[(a+b)/2]==-player){
				skor = skor + 1500;
			}
			if(state[a]==-player && state[b]==-player && state[(a+b)/2]==player){
				skor = skor + 1500;
			}
		}
		/*---------------------------------------------------------------------------------*/
         //jika move yang diambil, menyerang 2player 1 blank, blank mengapit lebih besar
		/*---------------------------------------------------------------------------------*/
		for (int i=0; i<8; i++){
			int a=rows[i][0];
			int b=rows[i][1];
			if(state[a]==0 && state[b]==player && state[(a+b)/2]==player){
				skor = skor + 30;
			}
			if(state[a]==player && state[b]==0 && state[(a+b)/2]==player){
				skor = skor + 30;
			}
			if(state[a]==player && state[b]==player && state[(a+b)/2]==0){
				skor = skor + 50;
			}
		}
		/*---------------------------------------------------------------------------------*/
        return skor;
    }
}

