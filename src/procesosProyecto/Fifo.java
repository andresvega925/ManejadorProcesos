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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Fifo extends JPanel implements Runnable  {

	private int time, procesos;
	private JLabel lblFifo;
	private JProgressBar barFifo;
	private JTextField txtActFifo, txtTerFifo;
	private JButton btnFifo;
	private	Queue<Object> fifo;
	private int i=0, conta = 0;
	private Thread h;



	public Fifo(){

		setBounds(0, 31, 280, 550);
		setBorder((Border) BorderFactory.createTitledBorder("FIFO"));
		setBackground(Color.BLACK);
		beginComponents();
		addComponents();

	}


	private void beginComponents() {
		h	 = new Thread(this);
		fifo = new LinkedList<Object>();
		procesos = (int) (Math.random()*100);
		lblFifo = new JLabel("Procesos");
		lblFifo.setForeground(Color.cyan);
		barFifo = new JProgressBar(0, 100);
		barFifo.setStringPainted(true);
		barFifo.setForeground(Color.GREEN);
		txtActFifo  = new JTextField(23);
		txtActFifo.setBackground(Color.black);
		txtActFifo.setForeground(Color.CYAN);
		txtActFifo.setEditable(false);
		txtTerFifo = new JTextField(23);
		txtTerFifo.setBackground(Color.black);
		txtTerFifo.setForeground(Color.CYAN);
		txtTerFifo.setEditable(false);
		btnFifo = new JButton("Iniciar");
		


		btnFifo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					
					iniciar();			
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "¡Ya esta en ejecución!");
				}
			}
		});
	}


	private void addComponents() {

		add(lblFifo);		
		add(barFifo);
		add(txtActFifo);
		add(txtTerFifo);
		add(btnFifo);
	}


	public void iniciar(){

		h.start();
	}


	public void run(){

		for(i=1;i<=procesos;i++)
		{
			conta++;
			fifo.offer(i);
			lblFifo.setText("Procesos: "+ Integer.toString(fifo.size()) );
		}   

		conta=0;
		Object data;
		while ((data = fifo.poll()) != null) {
			conta++;
			time= (int) (Math.random()*500);

			try {

				for (int j = 0; j <= 100; j++) {
					barFifo.setValue(j);

					txtActFifo.setText("Proceso # : "+conta + " Estado: " + Thread.currentThread().getState());
					Thread.sleep(time);
				}
				txtTerFifo.setText("Proceso #  : " + conta + " Estado " + Thread.State.TERMINATED);


			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}
	}


}






