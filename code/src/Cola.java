/////////////////////////////////////
//
// Author: Sebastian Garcia Valencia
//
/////////////////////////////////////

class Nodo
{
	Arbol info;
	Nodo sig;
}

class Cola
{
	Nodo cabeza;
	Nodo ventana;
	
	public Cola()
	{
		cabeza = new Nodo();
		cabeza.sig = null;
		ventana = cabeza;
	}
	
	
	public void insertaC(Arbol x, Cola t)
	{
		primCola();
		Nodo aux = new Nodo();
		aux.info = x;
		if(colaVacia())
			cabeza.info = x;
		//tuve tremendos problemas con el recorrido por niveles por culpa de este
		//metodo, las llaves del else no estaban, por lo cual siempre asignaba aux
		//a ventana.sig, y luego puse las llaves pero en el while, por lo cual
		//hacia la asignacion una y otra ves, la pregunta ¿porque no me dio
		//problemas cuando utilice la cola con numeros?
		//la respuesta ha llegado, recorde que cuando lo hice con numeros no me daba
		//cuando colocaba insertar el primer numero, asi que tenia que asignar cabeza
		//directamente, es decir,el if de arriba nunca sucedia, por lo cual siempre habia
		//que insertar el aux y por ende no era necesario que estuviera dentro del else
		else
		{
			while(ventana.sig != null)
				ventana = ventana.sig;
		
			ventana.sig = aux;
		}
	
	}
	
	public Arbol remuevaC(Cola c)
	{
		Nodo aux = new Nodo();
		aux.info = c.cabeza.info;
		if(cabeza.sig != null)
			c.cabeza = c.cabeza.sig;
		else
			cabeza.info = null;

		
		return aux.info;
		
	}
	
	public boolean colaVacia()
	{
		if(cabeza == null)
			return true;
		else
			return(cabeza.info == null && cabeza.sig == null);
	}
	
	public void primCola()
	{
		ventana = cabeza;
	}
	

}