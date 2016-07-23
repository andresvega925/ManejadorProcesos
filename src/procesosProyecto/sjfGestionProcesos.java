package procesosProyecto;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class sjfGestionProcesos extends JPanel implements Runnable{

	private List<sjfProcesosObjeto> arrayObjetos;
	private int rafaga;
	private JProgressBar barra;
	private JTextArea txtAreaDes, txtAreaOrd;
	private JButton iniciar;
	private JScrollPane scrollOrden;
	private JScrollPane scrollDesorden;
	private JLabel cantProcesos;
	private JLabel enEjecucion;
	private JTextField txtCanProc;
	private JTextField txtEnEjec, lblEsp;
	private JLabel lblProOrd;
	private JLabel lblProDes;
	private JButton detener,reanudar;
	private Thread h;
	public sjfGestionProcesos(){

		this.setBorder(BorderFactory.createTitledBorder("SJF"));
		this.setBounds(280, 31, 480, 550);
		setBackground(Color.BLACK);
		beginComponents();
		addComponents();


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

		txtAreaDes = new JTextArea(10,40);
		txtAreaDes.setBackground(Color.BLACK);
		txtAreaDes.setForeground(Color.ORANGE);
		txtAreaDes.setEditable(false);

		txtAreaOrd = new JTextArea(10,40);
		txtAreaOrd.setBackground(Color.BLACK);
		txtAreaOrd.setForeground(Color.ORANGE);
		txtAreaOrd.setEditable(false);


		scrollDesorden = new JScrollPane(txtAreaDes);
		scrollDesorden.setBackground(Color.BLACK);
		scrollDesorden.setForeground(Color.CYAN);

		scrollOrden = new JScrollPane(txtAreaOrd);
		scrollOrden.setBackground(Color.BLACK);
		scrollOrden.setForeground(Color.CYAN);


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
		add(lblProDes);
		add(scrollDesorden);
		add(lblProOrd);
		add(scrollOrden);
		add(iniciar);
		add(detener);
		add(reanudar);

	}


	public void llenarArray(){


		int procesos = (int)(Math.random()*500+1);

		for (int i = 0; i <= procesos; i++) {
			rafaga = (int)(Math.random()*5000+1);
			arrayObjetos.add(new sjfProcesosObjeto(rafaga));
		}



		//-----mostrar desordenado-----

		for (int j = 0; j < arrayObjetos.size(); j++) {

			txtAreaDes.append("Proceso # :" + arrayObjetos.indexOf(arrayObjetos.get(j)) + " Rafaga " + 

			arrayObjetos.get(j).getRafaga() +" Prioridad: "+Thread.currentThread().getPriority()+ "\n");
		}

	}





	public void ordenado(){


		int aux;

		for (int i = 0; i < arrayObjetos.size() - 1; i++) {
			for (int x = i + 1; x < arrayObjetos.size(); x++) {

				if (arrayObjetos.get(x).getRafaga() < arrayObjetos.get(i).getRafaga()){

					aux = arrayObjetos.get(i).getRafaga();

					arrayObjetos.get(i).setRafaga(arrayObjetos.get(x).getRafaga());				

					arrayObjetos.get(x).setRafaga(aux); 

				}
			}
		}
	}


	void mostrarOrdenado(){


		for (int j = 0; j < arrayObjetos.size(); j++) {

			txtAreaOrd.append("Proceso # :" + arrayObjetos.indexOf(arrayObjetos.get(j))+ " Rafaga: " + arrayObjetos.get(j).getRafaga() +" Prioridad: "+Thread.currentThread().getPriority()+ "\n");
		}
	}




	public void iniciar(){

		llenarArray();
		ordenado();
		mostrarOrdenado();
		h.start();
	}




	@Override
	public void run() {


		txtCanProc.setText(Integer.toString(arrayObjetos.size()));	
		
		for (int i = 1; i < arrayObjetos.size(); i++) {
			txtEnEjec.setText(Integer.toString(arrayObjetos.indexOf(arrayObjetos.get(i))));
			lblEsp.setText("Velocidad de procesamiento: "+(arrayObjetos.get(i).getRafaga())+ " ms por iteración");

			for (int j = 0; j <=100; j++) {

				barra.setValue(j); //pintar barra
				try {
					Thread.sleep(arrayObjetos.get(i).getRafaga());//tiempo qe va a durar pintando barra					
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
				+ ", txtAreaDes=" + txtAreaDes + ", txtAreaOrd=" + txtAreaOrd + ", iniciar=" + iniciar
				+ ", scrollOrden=" + scrollOrden + ", scrollDesorden=" + scrollDesorden + ", cantProcesos="
				+ cantProcesos + ", enEjecucion=" + enEjecucion + ", txtCanProc=" + txtCanProc + ", txtEnEjec="
				+ txtEnEjec + ", lblEsp=" + lblEsp + ", lblProOrd=" + lblProOrd + ", lblProDes=" + lblProDes + "]";
	}




}
