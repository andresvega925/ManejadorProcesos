package procesosProyecto;


public class procesosRoundRobin {


	private int rafaga;
	private int quantum;

	public procesosRoundRobin( int rafaga, int quantum) {

		this.rafaga = rafaga;	
		this.quantum = quantum;

	}
	
	public procesosRoundRobin(){
		
	}


	public int getRafaga() {
		return rafaga;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}
	
	

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	@Override
	public String toString() {
		return "procesosObjeto [rafaga=" + rafaga + "]";
	}

	
	
	
	

	
}
