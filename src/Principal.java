import javax.swing.*;

import java.awt.*;

public class Principal 
{
	
	public static void main(String[] args) 
	{
		int ancho = 1200;
		int alto = 640;
		Dimension dim = new Dimension( Toolkit.getDefaultToolkit().getScreenSize());
		Ventana aplicacion = new Ventana();
		aplicacion.init();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.setBounds((dim.width-ancho)/2, (dim.height-alto)/2, ancho, alto);
		aplicacion.setResizable(false);
		aplicacion.setVisible(true);
	}

}
