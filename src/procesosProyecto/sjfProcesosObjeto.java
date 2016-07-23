package procesosProyecto;

import java.util.ArrayList;
import java.util.Comparator;

public class sjfProcesosObjeto {


	private int rafaga;

	public sjfProcesosObjeto( int rafaga) {

		this.rafaga = rafaga;	

	}
	
	public sjfProcesosObjeto(){
		
	}


	public int getRafaga() {
		return rafaga;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}

	@Override
	public String toString() {
		return "procesosObjeto [rafaga=" + rafaga + "]";
	}

	
	
	
	

	
}
