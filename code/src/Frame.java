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
	Arbol arb;
	
	public Panel(Frame fra)
	{
        //setLayout(new BorderLayout());
		setLayout(new GridLayout(4,1));

		a = new JButton("Crear arbol");
		b = new JButton("Eliminar");
		c = new JButton("Buscar");
		d = new JButton("Reiniciar");
		
		a.setActionCommand("crear");
		b.setActionCommand("eliminar");
		c.setActionCommand("buscar");
		d.setActionCommand("reiniciar");
		
		a.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		
		a.setBackground(Color.lightGray);
		b.setBackground(Color.lightGray);
		c.setBackground(Color.lightGray);
		d.setBackground(Color.lightGray);
			
		add(a);
		add(b);
		add(c);
		add(d);
		
		
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
		
		}
}

