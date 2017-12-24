/////////////////////////////////////
//
// Author: Sebastian Garcia Valencia
//
/////////////////////////////////////



import java.util.*;

import javax.swing.*;




/*Clase Nodo, crea una clase con informacion de tipo generico, y apuntadores a 
 * otros dos nodos
 * */
/*
class Nodo
{
	Object info;
	Nodo hijoIzq;
	Nodo hijoDer;
}*/

class Acciones{
	public Frame frame;
	private Arbol arbol = null;
	
	public Arbol buscarPosicion(Object x, Arbol t)
	{
		Arbol p;
		Arbol q = null;
		p = t;
		while(p != null)
		{
			q = p;
			try
			{			
				if(Integer.parseInt(x.toString()) < Integer.parseInt(p.info.toString()))
				{
					
					p = p.hijoIzq;
				}
				else
				{
					p = p.hijoDer;
				}
			}
			catch(Exception e)
			{
				if((x.toString()).compareToIgnoreCase(p.info.toString()) < 0)
				{
					
					p = p.hijoIzq;
				}
				else
				{
					p = p.hijoDer;
				}
			}
		}
		return q;
	}
	
	public void ubicarValor(Object x, Arbol pos)
	{
		try
		{
			if(Integer.parseInt(x.toString()) < Integer.parseInt(pos.info.toString()))
				{
					pos.colocaIzq(x, pos);
					frame.agregarTexto("The left child of "+ pos.info+" is: " + pos.hijoIzq.info + "\n");
				}
			else
				{
					pos.colocaDer(x, pos);
					frame.agregarTexto("The right child of "+ pos.info+ " is: " + pos.hijoDer.info + "\n");
				}
		}
		catch(Exception e)
		{
			if(x.toString().compareToIgnoreCase(pos.info.toString()) < 0)
				{
					pos.colocaIzq(x, pos);
					frame.agregarTexto("The left child of "+ pos.info+" is: " + pos.hijoIzq.info + "\n");
				}
			else
				{
					pos.colocaDer(x, pos);
					frame.agregarTexto("The right child of "+ pos.info+ " is: " + pos.hijoDer.info + "\n");
				}
		}
	}

	public void enOrden(Arbol A)
	{
		
		if(A != null)
		{
			enOrden(A.hijoIzq);
			frame.agregarTexto(A.info + "	");
			enOrden(A.hijoDer);
		}
	}
	
	public void preOrden(Arbol A)
	{
		if(A != null)
		{
			frame.agregarTexto(A.info + "	");
			preOrden(A.hijoIzq);
			preOrden(A.hijoDer);
		}
	}
	
	public void posOrden(Arbol A)
	{
		if(A != null)
		{
			posOrden(A.hijoIzq);
			posOrden(A.hijoDer);
			frame.agregarTexto(A.info + "	");
		}
	}
	
	public void porNiveles(Arbol A)
	{
		Cola q = new Cola();
		//q.cabeza.info = A;
		q.insertaC(A, q);
		while(!q.colaVacia())
		{
			Arbol a = q.remuevaC(q);
			frame.agregarTexto(a.info + "	");
			if(a.hijoIzq != null)
				q.insertaC(a.hijoIzq, q);
			if(a.hijoDer != null)
				q.insertaC(a.hijoDer, q);
		}
		
	}
	
	public void porNivelesArrayList(Arbol A)
	{
		ArrayList<Arbol> q = new ArrayList<Arbol>();
		q.add(A);
		while(q.size() != 0)
		{
			Arbol a = q.get(0);
			q.remove(0);
			System.out.print(a.info + " ");
			if(a.hijoIzq != null)
				q.add(a.hijoIzq);
			if(a.hijoDer != null)
				q.add(a.hijoDer);
		}	
	}
	
	public int altura(Arbol A)
	{
		if(A == null)
		{
			return -1;
		}
		else
			return (1 + Math.max(altura(A.hijoIzq), altura(A.hijoDer)));
	}
	
	public boolean esBalanceado(Arbol A)
	{
		/*como podemos ver la visualizacion de los factores de balance tambien la
		 * tenia aqui, pero no me los imprimia todos en ciertos casos, lo que pasaba era 
		 * que como el return tiene puros && si el valor absoluto no cumplia la
		 * condicion pues era falso, y de inmediato retornaba ese falso sin llegar
		 * a llamar la funcion para los hijos, en resumen, el problema es que esto
		 * no recorre todo el arbol solo indica si esta balanceado o no*/
		if(A != null)
		{
			/*System.out.println("el factor de balance es: " 
					+ (altura(A.hijoIzq) - altura(A.hijoDer)));*/
			
			return ((Math.abs(altura(A.hijoIzq) - altura(A.hijoDer)) <= 1) 
					&& esBalanceado(A.hijoIzq) && esBalanceado(A.hijoDer));		
		}
		else
			return true;
	}
	
	public void factBalancePreOrd(Arbol A, boolean imprimir)
	{
		if(A != null)
		{
			int fact = (altura(A.hijoIzq) - altura(A.hijoDer));
			A.factBalance = fact;
			if(imprimir)
				frame.agregarTexto("Balance factor of " + A.info.toString() + " is: " 
						+ fact + "\n");
			factBalancePreOrd(A.hijoIzq, imprimir);
			factBalancePreOrd(A.hijoDer, imprimir);
		}
	}
	
	public int numNodos(Arbol A)
	{
		if(A == null)
			return 0;
		else
			return (1 + numNodos(A.hijoIzq) + numNodos(A.hijoDer));
	}
	
	public static int numHojas(Arbol A)
	{
		if(A == null)
			return 0;
		else if(A.hijoIzq == null && A.hijoDer == null)
			return 1;
		else
			return (numHojas(A.hijoIzq) + numHojas(A.hijoDer));
	}
	public Arbol rotarSimpIzq(Arbol A)
	{
		Arbol aux = new Arbol();
		aux = A;
		A = A.hijoDer;
		aux.hijoDer = A.hijoIzq;
		A.hijoIzq = aux;
		return A;

	}
	
	public Arbol rotarSimpDer(Arbol A)
	{
		Arbol aux = new Arbol();
		aux = A;
		A = A.hijoIzq;
		aux.hijoIzq = A.hijoDer;
		A.hijoDer = aux;
		return A;
	}
	
	public Arbol rotarDoblIzq(Arbol A)
	{
		A.hijoDer = rotarSimpDer(A.hijoDer);
		A = rotarSimpIzq(A);
		return A;
	}
	
	public Arbol rotarDoblDer(Arbol A)
	{
		A.hijoIzq = rotarSimpIzq(A.hijoIzq);
		A = rotarSimpDer(A);
		return A;
	}
	
	public Arbol balancear(Arbol A)
	{
		if(A != null)
		{
			A.hijoDer = balancear(A.hijoDer);
			A.hijoIzq = balancear(A.hijoIzq);
			if((altura(A.hijoIzq) - altura(A.hijoDer)) > 1)
			{
				if((altura(A.hijoIzq.hijoIzq) - altura(A.hijoIzq.hijoDer)) >= 0)
					A = rotarSimpDer(A);
				else
					A = rotarDoblDer(A);
					
			}
			else if((altura(A.hijoIzq) - altura(A.hijoDer)) < -1)
			{
				if((altura(A.hijoDer.hijoIzq) - altura(A.hijoDer.hijoDer)) <= 0)
					A = rotarSimpIzq(A);
				else
					A = rotarDoblIzq(A);				
			}
		}
		return A;
	}
	
	
	
	public void metodoCentral(Frame fra)
	{

		//Arbol arbol = null;
		//Frame frame = null;
		//System.out.println("Ingrese el valor del nodo \n(para terminar ingrese fin)");
		//frame.agregarTexto("los resultados son:" + "\n");
		//Scanner sc = new Scanner(System.in); 
		//Object x = sc.next();
		Object x = JOptionPane.showInputDialog("Write the value of root node\n(to finish write end)");

		if(!x.toString().equals("end"))
		{
			arbol = new Arbol();
			arbol.info = x;
			frame = fra;
			frame.setArbol(arbol);
			frame.agregarTexto("The root node is : " + x + "\n");
			frame.repintarArbol(arbol);
			//x = sc.next();
			x = JOptionPane.showInputDialog("to finish write end");
		}
		
		while(!x.toString().equals("end"))
		{
			Arbol pos = buscarPosicion(x, arbol);
			ubicarValor(x, pos);
			factBalancePreOrd(arbol, false);
			frame.repintarArbol(arbol);
			
			if(!esBalanceado(arbol))
				JOptionPane.showMessageDialog(null,"Tree is unbalanced\nlets apply method");
			arbol = balancear(arbol);
			factBalancePreOrd(arbol, false);
			frame.repintarArbol(arbol);
			//x = sc.next();
			x = JOptionPane.showInputDialog("to finish write end");
		}
		/*
		while(!esBalanceado(arbol))
			arbol = balancear(arbol);
		*/
	}
	
}






