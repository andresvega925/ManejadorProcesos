package procesosProyecto;

/*
 * @author: andres vega
 * @version: 1.0
 * "Prueba GitHub"
 */
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JFrame  {


	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem info;
	private Fifo pnlFifo;
	private sjfGestionProcesos sjf;
	private RoundR round;
	int i;
	int conta;

	public Principal(){

		this.setTitle("Planificador De Procesos");
		this.setBounds(300, 300, 1110, 617);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		beginComponents();
		addComponents();

	}



	public void beginComponents() {

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1108, 30);;
		menu = new JMenu("Opciones");
		sjf = new sjfGestionProcesos();
		info = new JMenuItem("Info");
		pnlFifo = new Fifo();
		round = new RoundR();
		
	}


	public void addComponents() {

		this.add(menuBar);
		menuBar.add(menu);
		menu.add(info);

		this.add(pnlFifo);
		this.add(sjf);
		this.add(round);
	}



	public static void main(String[] args) {

		Principal pp = new Principal();

	}




}
