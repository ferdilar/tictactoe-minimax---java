// package tictactoeJar;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.Applet;
import java.applet.AudioClip;
public class Interface implements ActionListener{//Class inheritance dari kelas Actionlistener java
	//Attribut
	/*semua Button*/
	private JButton[] cell = new JButton[9];
	private JFrame frame;
	/*semua ImageIcon get dari resource*/
	private ImageIcon blank;
	private ImageIcon O;
	private ImageIcon X;
	/*Keterangan permainan beberapa atribut yang akan tampil sbg keterangan saat permainan*/
	private JButton cmdNew;
	private JButton cmdExit;
	/*Atribut lain*/
	private AudioClip sClick;
	private JLabel lblMoveScore;
		MiniMax mm = new MiniMax();
		Board b = new Board();
				
	/*Constructor*/
	public Interface(){//panggil user interface
		frame=new JFrame("TIC TAC TOE");
		frame.setResizable(false);
		frame.setSize(345,450);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);	
	}
	public void addComponent(){//masukkkan componen dalan interface yang akan tampil
		// memasukkan gambar ke Image Icon
		setInsta();
		cell[0].setBounds(20,50,100,100);//x,y,width,height
		cell[1].setBounds(120,50,100,100);
		cell[2].setBounds(220,50,100,100);
		cell[3].setBounds(20,150,100,100);
		cell[4].setBounds(120,150,100,100);
		cell[5].setBounds(220,150,100,100);
		cell[6].setBounds(20,250,100,100);
		cell[7].setBounds(120,250,100,100);
		cell[8].setBounds(220,250,100,100);
		//tombol aksi untuk new game dan keluar
		cmdNew.setBounds(20,15,300,30);
		cmdExit.setBounds(20,360,300,30);
		lblMoveScore.setBounds(250,385,70,40);
	}
	public void addListener(){//inisialisasi
		for(int i=0;i<9;i++){
			cell[i].addActionListener(this);
		}
		cmdNew.addActionListener(this);
		cmdExit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){//aksi hidup/mati
		int x;
		int y;
		int put=0;
		int withScore=0;
		sClick.play();
		for(int i=0;i<9;i++){
			if(e.getSource()==cell[i]){
				/* ---------------------------------------------------------------- */
				if (!b.isGameOver()) {
					cell[i].setIcon(O);
					// System.out.println("Pilihan anda"+i);
					b = b.makeMove(i);
					b.print();
					if(b.isGameOver() && b.isWon()){//karena pemain mulai duluan.
						JOptionPane.showMessageDialog(null,"Game Over You Won", "Permainan Selesai",
						JOptionPane.INFORMATION_MESSAGE);
						setButton(false);
						break;
					}else if(b.isGameOver()){
						JOptionPane.showMessageDialog(null,"Game Over Draw", "Permainan Selesai",
						JOptionPane.INFORMATION_MESSAGE);
						setButton(false);
						break;
					}
					if (!b.isGameOver()){
						// System.out.println("giliran komputer..");
						MoveScore ms = mm.getBestMove(b, -1);
						// System.out.println("Pilihan komputer.." + ms.move);
						b = b.makeMove(ms.move);
						b.print();
						put=ms.move;
						withScore=ms.score;
					}
						// lblMoveScore.setText(String.valueOf(withScore));
						cell[put].setIcon(X);
						if(b.isGameOver()){
							JOptionPane.showMessageDialog(null,"Game Over You Lost", "Permainan Selesai",
							JOptionPane.INFORMATION_MESSAGE);
							setButton(false);
						}
				}
				/* ---------------------------------------------------------------- */
			}
		}
		if(e.getSource()==cmdNew){//jika button new game di klik
			setButton(true);//aktifkan papan permainan
			restart();
		}else if(e.getSource()==cmdExit){//jika button exit untuk keluar
			System.exit(0);
		}
	}
	public void setButton(boolean status){//status gambar true berarti standby untuk dimainkan/dapat menerima aksi user
		for(int i=0;i<9;i++){
			cell[i].setEnabled(status);
		}
	}
	public void setInsta(){//status gambar true berarti standby untuk dimainkan/dapat menerima aksi user
		sClick = Applet.newAudioClip(getClass().getResource("aset/click.wav"));
		O=new ImageIcon(getClass().getResource("aset/O.jpg"));
		X=new ImageIcon(getClass().getResource("aset/X.jpg"));
		blank=new ImageIcon(getClass().getResource("aset/blank.jpg"));
		for(int i=0;i<9;i++){
			cell[i]=new JButton("tile");
			cell[i].setIcon(blank);
			frame.add(cell[i]);
		}
		lblMoveScore=new JLabel("MoveScore");
		frame.add(lblMoveScore);
		cmdNew=new JButton("New Game");
		frame.add(cmdNew);
		cmdExit=new JButton("Exit");
		frame.add(cmdExit);
		setButton(false);
	}
	public void restart(){//status gambar true berarti standby untuk dimainkan/dapat menerima aksi user
		for(int i=0;i<9;i++){
			cell[i].setIcon(blank);
			b = new Board();
		}
	}
}

