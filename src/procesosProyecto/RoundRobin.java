package procesosProyecto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class RoundRobin extends JPanel implements Runnable{


	private JLabel lbRaf, lbQua,lblProcesos,lbTiePro;
	private JTextField  txtQua, txtProce, txtTiemPro;	
	private JTextArea txtResul,txtRaf;
	private JScrollPane scrollRes, scrollRaf;
	private JButton btnRound;
	private JButton reiniciar;

	
	int [] temp;
	int commBT, k, tiemQua;
	int[][] d;
	int tiemRafcache;

	public RoundRobin(Principal main){

		setBounds(762, 31, 238, 550);
		setBorder((Border) BorderFactory.createTitledBorder("Round Robin"));
		setBackground(Color.BLACK);
		beginComponens();
		addComponents();
	}

	public void beginComponens(){
		
		lbTiePro = new JLabel("TIEMPO PROMEDIO");
		lbTiePro.setEnabled(false);
		lbTiePro.setForeground(Color.BLUE);
		
		txtTiemPro = new JTextField(15);
		txtTiemPro.setEnabled(false);
		txtTiemPro.setBackground(Color.black);
		txtTiemPro.setForeground(Color.CYAN);
		
		lblProcesos = new JLabel("PROCESOS");
		lblProcesos.setEnabled(false);
		lblProcesos.setForeground(Color.cyan);
		
		txtProce = new JTextField(15);
		txtProce.setEnabled(false);
		txtProce.setBackground(Color.black);
		txtProce.setForeground(Color.CYAN);
		
		lbQua = new JLabel("QUANTUM");
		lbQua.setEnabled(false);
		lbQua.setForeground(Color.cyan);
		
		lbRaf = new JLabel("RAFAGA");
		lbRaf.setEnabled(false);
		lbRaf.setForeground(Color.cyan);
		
		txtQua = new JTextField(15);
		txtQua.setEnabled(false);
		txtQua.setBackground(Color.black);
		txtQua.setForeground(Color.CYAN);
		
		txtRaf = new JTextArea(7,20);
		txtRaf.setEnabled(false);
		txtRaf.setBackground(Color.black);
		txtRaf.setForeground(Color.CYAN);
		
		txtResul = new JTextArea(7,20);
		txtResul.setEnabled(false);
		txtResul.setBackground(Color.black);
		txtResul.setForeground(Color.CYAN);
		
		scrollRes = new JScrollPane(txtResul);
		scrollRaf = new JScrollPane(txtRaf);

		btnRound = new JButton("Iniciar");
		btnRound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");	
				
				Datos();
			}
		});
		

		reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				txtProce.setText("");
				txtQua.setText(String.valueOf(""));
				txtRaf.setText(String.valueOf(""));
				txtResul.setText(String.valueOf(""));
				txtTiemPro.setText(String.valueOf(""));
			}
		});
		

	}


	public void addComponents(){

		this.add(lblProcesos);
		this.add(txtProce);
		this.add(lbQua);
		this.add(txtQua);
		this.add(lbRaf);
		this.add(scrollRaf);
		this.add(scrollRes);
		this.add(lbTiePro);
		this.add(txtTiemPro);
		this.add(btnRound);
		this.add(reiniciar);

	}



	void Datos( ){

		//numero de procesos
		int procesos = (int)(Math.random()*100+1);
		d = new int[procesos][2];
		txtProce.setText(Integer.toString(procesos));

		temp = new int[procesos];
//		System.out.println( "Valor rafaga");
		for (int i = 0; i < procesos; i++) {
			d[i][0] = i;

			int rafaga = (int)(Math.random()*100);
			txtRaf.append("Proceso: "+ i +" rafaga : "+Integer.toString(rafaga)+"\n");
			d[i][1] = rafaga;

			commBT += rafaga;
		}
//		System.out.println( "tiempo quantum ");
		tiemQua = (int)(Math.random()*150);
		txtQua.setText(Integer.toString(tiemQua));
		start();
		display( );

	}
	void start( ){
		for (int i = 0; i < d.length; i++) {
			int tr  = d[i][1];
			if( tr > 0){
				if( tr <= tiemQua){
					temp[i] = tiemRafcache+tr;
					tiemRafcache = temp[i];
					k += tr;
					tr -= tr;

				}
				else{
					temp[i] = tiemRafcache+tiemQua;
					tiemRafcache = temp[i];
					tr -= tiemQua;
					k += tiemQua;
				}

				d[i][1] = tr;


			}
		}
		if( k!= commBT)
			start();
	}


	
	private void display() {
		float val = 0;
		int cont = 1;
		for (int i : temp) {
			txtResul.append("Tiempo rafaga para el proceso: "+cont +" es: "+ i +" nano-segundos \n");
			val += i;
			cont++;
		}
	
		txtTiemPro.setText(Double.toString(val/temp.length));
	}

	@Override
	public void run() {
		
	}
	
}

