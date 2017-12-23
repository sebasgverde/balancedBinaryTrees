/*Definicion de la clase ArbolBinario*/
class Arbol
{
	//private Nodo encabezador;
	Object info;
	int factBalance;
	boolean pintar;
	Arbol hijoIzq;
	Arbol hijoDer;
	//private Nodo ventana; 

	public Arbol()
	{
		//encabezador = new Nodo();
		//encabezador.hijoIzq = padre;
		hijoIzq = null;
		hijoDer = null;
		//ventana = padre;
	}
	
	/*public Nodo getPadre()
	{
		return padre;
	}*/
	
	public void colocaIzq(Object v, Arbol t)
	{
		Arbol V = new Arbol();
		V.info = v;
		t.hijoIzq = V;
	}
	
	public void colocaDer(Object v, Arbol t)
	{
		Arbol V = new Arbol();
		V.info = v;
		t.hijoDer = V;
	}

}