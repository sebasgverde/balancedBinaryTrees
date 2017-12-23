import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


//@SuppressWarnings("serial")
class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Lienzo lienzo;
	private Arbol A;
	private Panel panel;
	private TextArea texto;
	private String text = "";
	
	public Frame(Arbol a)
	{
		A= a;
		
		this.setTitle("Arbol");
        this.setLocation(10, 10);
        this.setSize(1000, 700);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
       
        panel = new Panel(this);
        add(panel, BorderLayout.WEST);

        lienzo = new Lienzo(A);
        lienzo.setBackground(Color.gray);

        add(lienzo, BorderLayout.CENTER);
        
        texto = new TextArea();
        texto.setBackground(Color.black);
        texto.setForeground(Color.WHITE);
        add(texto, BorderLayout.SOUTH);
                
        setVisible(true);
        
	}	
	
	public void setArbol(Arbol a)
	{
		A = a;
	}
	
	public Arbol getArbol()
	{
		return A;
	}
	
	//cuando intente activar los botones, por esto fue que no me funciono
	//yo usaba la funcion get arbol, pero este solo me retornaba el nodo padre, pues
	//no lo actualizaba, pues despues de que instanciaba el frame, ya no volvia a
	//modificar arbol A, la clave due, actulizarlo en el metodo de abajo
	public void repintarArbol(Arbol A)
	{
		this.A = A;
		lienzo.repintarArbol(A);
	}
	
	public void agregarTexto(String s)
	{
		texto.setText(text += s);
	}
	
	public void borrarTexto()
	{
		texto.setText("");
		text = "";
	}
	
	public boolean buscar(Arbol A, Object x)
	{
		if(A == null)
			return false;
		else 
			try
			{
				if(Integer.parseInt(x.toString()) == Integer.parseInt(A.info.toString()))
				{
					A.pintar = true;
					return true;
				}
				else if(Integer.parseInt(x.toString()) < Integer.parseInt(A.info.toString()))
					return (buscar(A.hijoIzq, x));
				else
					return (buscar(A.hijoDer, x));
			}
			catch(Exception e)
			{
				if((x.toString()).compareToIgnoreCase(A.info.toString()) == 0)
				{
					A.pintar = true;
					return true;
				}
				else if((x.toString()).compareToIgnoreCase(A.info.toString()) < 0)
					return (buscar(A.hijoIzq, x));
				else
					return (buscar(A.hijoDer, x));
			}
	}
	
	public Arbol eliminar(Arbol A, Object x)
	{
		try
		{
			if(A != null)
			{
				if(Integer.parseInt(x.toString()) < Integer.parseInt(A.info.toString()))
					A.hijoIzq = eliminar(A.hijoIzq, x);
				else if(Integer.parseInt(x.toString()) > Integer.parseInt(A.info.toString()))
					A.hijoDer = eliminar(A.hijoDer, x);
				else
				{
					Arbol otro = A;
					if(A.hijoDer == null)
						A = A.hijoIzq;
					else if(A.hijoIzq == null)
						A = A.hijoDer;
					else
					{
						Arbol aux = A.hijoIzq;
						Arbol aux2 = aux;
						while(aux.hijoDer != null)
						{
							aux2 = aux;
							aux = aux.hijoDer;
						}
						if(aux2.hijoDer == null)
						{
							otro.pintar = aux.pintar;
							otro.info = aux.info;					
							otro = aux;
							A.hijoIzq = aux2.hijoIzq;
						}
						otro.pintar = aux.pintar;
						otro.info = aux.info;
						otro = aux;
						aux2.hijoDer = aux.hijoIzq;
					}
				}
			}
			else
				agregarTexto("\nEl valor a eliminar no existe" + "\n");
		}
		catch(Exception e)
		{
			if(A != null)
			{
				if((x.toString()).compareToIgnoreCase(A.info.toString()) < 0)
					A.hijoIzq = eliminar(A.hijoIzq, x);
				else if((x.toString()).compareToIgnoreCase(A.info.toString()) > 0)
					A.hijoDer = eliminar(A.hijoDer, x);
				else
				{
					Arbol otro = A;
					if(A.hijoDer == null)
						A = A.hijoIzq;
					else if(A.hijoIzq == null)
						A = A.hijoDer;
					else
					{
						Arbol aux = A.hijoIzq;
						Arbol aux2 = aux;
						while(aux.hijoDer != null)
						{
							aux2 = aux;
							aux = aux.hijoDer;
						}
						if(aux2.hijoDer == null)
						{
							otro.pintar = aux.pintar;
							otro.info = aux.info;
							otro = aux;
							A.hijoIzq = aux2.hijoIzq;
						}
						otro.pintar = aux.pintar;
						otro.info = aux.info;
						otro = aux;
						aux2.hijoDer = aux.hijoIzq;
					}
				}
			}
			else
				agregarTexto("\nEl valor a eliminar no existe" + "\n");

		}
		
		return A;
	}
	

	public void enOrden(Arbol A)
	{
		
		if(A != null)
		{
			enOrden(A.hijoIzq);
			agregarTexto(A.info + "	");
			enOrden(A.hijoDer);
		}
	}
	
	public void preOrden(Arbol A)
	{
		if(A != null)
		{
			agregarTexto(A.info + "	");
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
			agregarTexto(A.info + "	");
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
			agregarTexto(a.info + "	");
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
				agregarTexto("El factor de balance de " + A.info.toString() + " es: " 
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
	
	public int numHojas(Arbol A)
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
}

//@SuppressWarnings("serial")
class Lienzo extends Canvas
{
	private static final long serialVersionUID = 1L;

	private Arbol arbol;
	
	private int x = 400;
	private int y = 20;
	
	public Lienzo(Arbol A)
	{
		arbol = A;
	}
	
	public void repintarArbol(Arbol A)
	{
		arbol = A;
		repaint();
	}
	
	public void paint(Graphics g)
	{
		Arbol d = arbol;
		int i = 0;
		ArrayList<Arbol> pila = new ArrayList<Arbol>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		while(d != null || pila.size() != 0)
		{
			if(d != null)
			{
				g.setColor(Color.magenta);
				if(d.pintar)
					g.fillOval(x, (y + 50*i), 50, 50);
				g.setColor(Color.white);
				g.drawString(d.info.toString(), x + 21, (y + 50*i) + 30 );
				System.out.println(x +","+ (y + 50*i));
				g.setColor(Color.ORANGE);
				g.drawString(Integer.toString(d.factBalance), x + 21, (y + 50*i) + 13);
				//y += 50; 
				if(d.hijoIzq != null)
					x -= 5*(32/Math.pow(2, i));
				else if(d.hijoDer != null)
					x += 5*(32/Math.pow(2, i));

				pila.add(d);
				indices.add(i);
				d = d.hijoIzq;
				i += 1;
			}
			else
			{
				d = pila.get(pila.size() - 1);
				i = indices.get(indices.size() - 1);
				pila.remove(pila.size() - 1);
				indices.remove(indices.size() - 1);
				if(d.hijoDer == null)
					x += 10*(64/Math.pow(2, i));
				//x += 5;
				d = d.hijoDer;
				i += 1;
			}
		}
		System.out.println();
		x = 400;
		y = 20;
	}
	
}

//@SuppressWarnings("serial")
class Panel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	Frame frame;
	JButton b;
	JButton a;
	JButton c;
	JButton d;
	JButton e;
	Arbol arb;
	
	public Panel(Frame fra)
	{
        //setLayout(new BorderLayout());
		setLayout(new GridLayout(5,1));

		a = new JButton("Crear arbol");
		b = new JButton("Eliminar");
		c = new JButton("Buscar");
		d = new JButton("Reiniciar");
		e = new JButton("Mostrar Info");
		
		a.setActionCommand("crear");
		b.setActionCommand("eliminar");
		c.setActionCommand("buscar");
		d.setActionCommand("reiniciar");
		e.setActionCommand("mostrar");
		
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		e.addActionListener(this);
		
		a.setBackground(Color.lightGray);
		b.setBackground(Color.lightGray);
		c.setBackground(Color.lightGray);
		d.setBackground(Color.lightGray);
		e.setBackground(Color.lightGray);
			
		add(a);
		add(b);
		add(c);
		add(d);
		add(e);
		
		
		frame = fra;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String comando = evt.getActionCommand();
		Acciones accion = new Acciones();
		if(comando == ("crear"))
		{
			frame.borrarTexto();
			accion.metodoCentral(frame);
		}
		else if(comando == "eliminar")
		{
			Object x = JOptionPane.showInputDialog("Ingrese el valor que desea eliminar");
			
			arb = frame.getArbol();
			arb = frame.eliminar(arb, x);
			if(frame != null)
				frame.repintarArbol(arb);
			
			if(!accion.esBalanceado(arb))
				JOptionPane.showMessageDialog(null,"El arbol quedo desbalaceado\napliquemos el balanceo");
			
			arb = accion.balancear(arb);
			
			if(frame != null)
				frame.repintarArbol(arb);
		}
		else if(comando == "buscar")
		{
			Object x = JOptionPane.showInputDialog("Ingrese el valor que desea buscar");		
			arb = frame.getArbol();
			
			if(frame.buscar(arb, x))
				frame.agregarTexto("\nEl valor " + x + " si pertenece" + "\n");
			else
				frame.agregarTexto("\nEl valor " + x + " no pertenece" + "\n");
			
			if(frame != null)
				frame.repintarArbol(arb);
		}
		else if(comando == "reiniciar")
		{		
			frame.borrarTexto();
			frame.repintarArbol(null);
		}
		else if(comando == "mostrar")
		{
			arb = frame.getArbol();
			
			frame.agregarTexto("\n\nLos valores del arbol al recorrerlo en orden : " + "\n");
			frame.enOrden(arb);
			
			frame.agregarTexto("\n\nLos valores del arbol al recorrerlo en preorden : " + "\n");
			frame.preOrden(arb);
			
			frame.agregarTexto("\n\nLos valores del arbol al recorrerlo en posorden : " + "\n");
			frame.posOrden(arb);
			
			frame.agregarTexto("\n\nLos valores del arbol al recorrerlo por niveles : " + "\n");
			frame.porNiveles(arb);
			
			frame.agregarTexto("\n\nLa altura del arbol es:	" + frame.altura(arb) + "\n");
			
			frame.agregarTexto("\n\nEl numero de nodos es:	" + frame.numNodos(arb) + "\n");
			
			frame.agregarTexto("\n\nEl numero de hojas es:	" + frame.numHojas(arb) + "\n\n");
			
			frame.agregarTexto("\nLos factores de balanceo de cada nodo en preorden son : " + "\n\n");
			frame.factBalancePreOrd(arb, true);
			
			if(frame.esBalanceado(arb))
				frame.agregarTexto("\n\nEl arbol esta balanceado" + "\n");
			else
				frame.agregarTexto("\n\nEl arbol esta desbalanceado" + "\n");
			
			if(frame != null)
				frame.repintarArbol(arb);
		}
		
		}
}

