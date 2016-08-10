import javax.swing.*;

import java.awt.*;


public class Resultados extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3307141682222743268L;
	
	private JTextArea texto;
	private JScrollPane panelScroll;
	private Container contenedor;
	private BorderLayout layout;
	
	public void init()
	{
		texto = new JTextArea();
		contenedor = getContentPane();
		layout = new BorderLayout();
		panelScroll = new JScrollPane(texto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contenedor.setLayout(layout);
		contenedor.add(panelScroll, BorderLayout.CENTER);
		
	}
	
	public Resultados()
	{
		init();
	}
	
	public void imprimirResultados(String lol)
	{
		String res = "";
		for( int i = 0; i < 25; i++ )
		{
			res+="  ";
		}
		texto.setText("");
		texto.append("x______________________________" + "y(Euler)_______________________" + "y(Heun)________________________" + "y(Punto Medio)_________________" + "y(Runge-Kutta)\n");
		texto.append(lol);
	}
}
