package procesosProyecto;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class RoundR extends JPanel implements Runnable{

	private List<procesosRoundRobin> arrayObjetos;
	private int rafaga;
	private JProgressBar barra;
	private JButton iniciar;
	private JLabel cantProcesos;
	private JLabel enEjecucion;
	private JTextField txtCanProc;
	private JTextField txtEnEjec, lblEsp;
	private JLabel lblProOrd;
	private JLabel lblProDes;
	private JButton detener,reanudar;
	private JLabel lblQuantum;
	private JLabel lblRafaga;
	private JTextField txtRafaga;
	private JTextField txtQuantum;
	private JTextField txtTiempoTotal;
	private JTextField txtTiempoPromdio;
	private JLabel lblTiempoTotal;
	private JLabel lblTiempoPromedio;
	private Thread h;
	private int quantum;
	private int procesos;


	public RoundR(){

		setBounds(760, 31, 348, 550);
		setBorder((Border) BorderFactory.createTitledBorder("Round Robin"));
		setBackground(Color.BLACK);
		beginComponents();
		addComponents();
		llenarArray();
	}

	private void beginComponents() {


		h= new Thread(this);

		iniciar = new JButton("Iniciar");
		detener = new JButton("Detener");
		reanudar = new JButton("Reanudar");


		iniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					iniciar();		
					detener.setVisible(true);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "¡Ya esta en ejecución!");			}
			}
		});



		detener.setVisible(false);
		detener.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				detenerProcesos();
				reanudar.setVisible(true);
			}
		});


		reanudar.setVisible(false);
		reanudar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reanudar();
				reanudar.setVisible(false);

			}
		});


		txtTiempoTotal = new JTextField(30);
		txtTiempoTotal.setBackground(Color.BLACK);
		txtTiempoTotal.setForeground(Color.ORANGE);
		txtTiempoTotal.setEditable(false);
		
		
		txtTiempoPromdio = new JTextField(30);
		txtTiempoPromdio.setBackground(Color.BLACK);
		txtTiempoPromdio.setForeground(Color.ORANGE);
		txtTiempoPromdio.setEditable(false);

		txtQuantum = new JTextField(30);
		txtQuantum.setBackground(Color.BLACK);
		txtQuantum.setForeground(Color.ORANGE);
		txtQuantum.setEditable(false);


		txtRafaga = new JTextField(30);
		txtRafaga.setBackground(Color.BLACK);
		txtRafaga.setForeground(Color.ORANGE);
		txtRafaga.setEditable(false);
		
		
		lblTiempoTotal = new JLabel("Tiempo Total");
		lblTiempoTotal.setForeground(Color.CYAN);
	
		
		lblTiempoPromedio = new JLabel("Tiempo Promedio");
		lblTiempoPromedio.setForeground(Color.CYAN);
		

		lblQuantum = new JLabel("Quantum");
		lblQuantum.setForeground(Color.CYAN);

		lblRafaga = new JLabel("Rafaga proceso actual");
		lblRafaga.setForeground(Color.cyan);
		lblRafaga.setBackground(Color.BLACK);

		lblProDes = new JLabel("Procesos Desordenados");
		lblProDes.setForeground(Color.CYAN);
		lblProDes.setBounds(400, 400, 50, 200);

		lblProOrd = new JLabel("Procesos Ordenados");
		lblProOrd.setForeground(Color.CYAN);

		txtCanProc = new JTextField(30);
		txtCanProc.setBackground(Color.black);
		txtCanProc.setForeground(Color.orange);
		txtCanProc.setEditable(false);

		txtEnEjec = new JTextField(30);
		txtEnEjec.setBackground(Color.black);
		txtEnEjec.setForeground(Color.ORANGE);
		txtEnEjec.setEditable(false);


		cantProcesos = new JLabel("Numero Procesos: ");
		cantProcesos.setForeground(Color.CYAN);

		enEjecucion = new JLabel("Proceso en ejecución #: ");
		enEjecucion.setBackground(Color.BLACK);
		enEjecucion.setForeground(Color.CYAN);

		arrayObjetos = new ArrayList<>();

		barra = new JProgressBar(0,100);
		barra.setStringPainted(true);
		barra.setForeground(Color.GREEN);
		barra.getBounds();

		lblEsp = new JTextField(30);
		lblEsp.setEditable(false);
		lblEsp.setBackground(Color.BLACK);
		lblEsp.setForeground(Color.ORANGE);

	}

	private void addComponents() {

		add(cantProcesos);
		add(txtCanProc);
		add(enEjecucion);
		add(txtEnEjec);
		add(barra);
		add(lblEsp);
		add(lblQuantum);
		add(txtQuantum);
		add(lblRafaga);
		add(txtRafaga);
		add(lblTiempoPromedio);
		add(txtTiempoPromdio);
		add(lblTiempoTotal);
		add(txtTiempoTotal);
		add(iniciar);
		add(detener);
		add(reanudar);

	}


	public void llenarArray(){

		procesos = (int)(Math.random()*5+1);
		quantum = (int)(Math.random()*100);

		for (int i = 0; i <= procesos; i++) {
			rafaga = (int)(Math.random()*500+1);
			arrayObjetos.add(new procesosRoundRobin(rafaga,quantum));
		}

	}



	public void iniciar(){

		h.start();
	}

	

	int n;
	public void pintar(){
		n++;
		barra.setValue(n);
		barra.repaint();
	}


	@Override
	public void run() {

		int aux2=0;

		txtCanProc.setText(Integer.toString(arrayObjetos.size()));	
		for (int i = 0; i < arrayObjetos.size(); i++) {
			txtQuantum.setText(Integer.toString(arrayObjetos.get(i).getQuantum()));
			txtRafaga.setText(Integer.toString(arrayObjetos.get(i).getRafaga()));
			txtEnEjec.setText(Integer.toString(arrayObjetos.indexOf(arrayObjetos.get(i))+1));
			lblEsp.setText("Velocidad de procesamiento: "+(arrayObjetos.get(i).getRafaga())+ " ms por iteración");

			int aux = arrayObjetos.get(i).getRafaga();
						
			aux2 = aux2 +aux;
			
			double total = aux2/arrayObjetos.size();
			
			txtTiempoPromdio.setText(Double.toString(total));
			txtTiempoTotal.setText(Integer.toString(aux2));
			
			for (int j = 0; j <=100; j++) {

				pintar();

				if (j == 100) {

					barra.setValue(0);//pintar barra
					n=0;
				}
			
				try {
					Thread.sleep(arrayObjetos.get(i).getRafaga());//tiempo que va a durar pintando barra					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}


	}

	public void detenerProcesos(){

		h.suspend();
	}

	public void reanudar(){
		h.resume();

	}
	
	@Override
	public String toString() {
		return "sjfGestionProcesos [arrayObjetos=" + arrayObjetos + ", rafaga=" + rafaga + ", barra=" + barra
				+ ", txtAreaDes=" + ", txtAreaOrd=" + ", iniciar=" + iniciar
				+ ", scrollOrden=" + ", scrollDesorden=" +  ", cantProcesos="
				+ cantProcesos + ", enEjecucion=" + enEjecucion + ", txtCanProc=" + txtCanProc + ", txtEnEjec="
				+ txtEnEjec + ", lblEsp=" + lblEsp + ", lblProOrd=" + lblProOrd + ", lblProDes=" + lblProDes + "]";
	}



}
