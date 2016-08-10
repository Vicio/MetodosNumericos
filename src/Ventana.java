import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import graph.*;


public class Ventana extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8034740934361487845L;
	
	private JPanel panelDibujo = new JPanel();
	private G2Dint grafico = new G2Dint();
	private Axis xAxis;
	private Axis yAxis;
	private DataSet datos;
	Algoritmos alg = new Algoritmos();
	private double[] tabla;
	
	private JPanel panelBotones = new JPanel();
	private JPanel panelBotones2 = new JPanel();	
	private JLabel labelValorMinimo = new JLabel("Valor Mínimo");
	private JLabel labelValorMaximo = new JLabel("Valor Máximo");
	private JLabel labelFuncionEntrada = new JLabel("Función");
	private JLabel labelXI = new JLabel("xi");
	private JLabel labelXF = new JLabel("xf");
	private JLabel labelH = new JLabel("h");
	private JLabel labelX0 = new JLabel("x0");
	private JLabel labelY0 = new JLabel("y0");
	private JTextField XI = new JTextField(3);
	private JTextField XF = new JTextField(3);
	private JTextField H = new JTextField(3);
	private JTextField X0 = new JTextField(3);
	private JTextField Y0 = new JTextField(3);
	private JTextField valorMinimo = new JTextField(10);
	private JTextField valorMaximo = new JTextField(10);
	private JTextField funcionEntrada = new JTextField(20);
	private JButton euler = new JButton("Euler");
	private JButton heun = new JButton("Heun");
	private JButton puntoMedio = new JButton("Punto Medio");
	private JButton real = new JButton("Valor Real");
	private JButton rungeKutta = new JButton("Runge-Kutta 5");
	private JButton rungeKutta4 = new JButton("Runge-Kutta 4");
	private JButton resultados = new JButton("Tabla de Resultados");
	private Container contenedor;
	private BorderLayout layout = new BorderLayout();
	
	public void init()
	{
		valorMinimo.setText("-20");
		valorMaximo.setText("20");
		funcionEntrada.setText("(-2*(x^3))+(12*(x^2))-(20*x)+(8.5)");
		
		panelDibujo.setBackground(Color.DARK_GRAY);
		
		panelBotones.add(labelFuncionEntrada);
		panelBotones.add(funcionEntrada);
		panelBotones.add(labelValorMinimo);
		panelBotones.add(valorMinimo);
		panelBotones.add(labelValorMaximo);
		panelBotones.add(valorMaximo);
		panelBotones2.add(labelXF);
		panelBotones2.add(XF);
		panelBotones2.add(labelXI);
		panelBotones2.add(XI);
		panelBotones2.add(labelH);
		panelBotones2.add(H);
		panelBotones2.add(labelX0);
		panelBotones2.add(X0);
		panelBotones2.add(labelY0);
		panelBotones2.add(Y0);
		panelBotones2.add(euler);
		panelBotones2.add(heun);
		panelBotones2.add(puntoMedio);
		panelBotones2.add(rungeKutta);
		panelBotones2.add(rungeKutta4);
		panelBotones2.add(real);
		panelBotones2.add(resultados);
		
		panelDibujo.add(grafico);
		panelDibujo.setSize(1200, 440);
		grafico.setSize(1200, 540);
		
		xAxis = grafico.createXAxis();
		xAxis.setTitleText("X");
		yAxis = grafico.createYAxis();	
		yAxis.setTitleText("Y");
		
		datos = new DataSet();
		xAxis.attachDataSet(datos);
		yAxis.attachDataSet(datos);
		grafico.attachDataSet(datos);
        grafico.setDataBackground(new Color(255,255,255));
        grafico.setBackground(new Color(230,230,230));
		
		contenedor = getContentPane();
		contenedor.setLayout(layout);
		contenedor.add(panelBotones2, BorderLayout.NORTH);
		contenedor.add(panelDibujo, BorderLayout.CENTER);
		contenedor.add(panelBotones, BorderLayout.SOUTH);
		contenedor.setVisible(true);
		
		real.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						h = 0.00005;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.RK4(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		puntoMedio.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.PuntoMedio(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		heun.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.Heun(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		rungeKutta.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.RungeKutta(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		rungeKutta4.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.RK4(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		euler.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						

						
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tabla = alg.Euler(xi, xf, h, x0, y0);
						datos.deleteData();
						try
						{
							datos.append(tabla, tabla.length/2);
						}
						catch( Exception e )
						{
        		            System.out.println("Error al agregar los datos");
        		            return;
						}
						
						grafico.repaint();
					}
				}
		);
		
		resultados.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent evt )
					{
						double xi, xf, h, x0, y0;
						int l;
						double[] tablaEuler;
						double[] tablaPuntoMedio;
						double[] tablaHeun;
						double[] tablaRunge;
						double[] tablaRunge4;
						
						try
						{
							xi = Double.parseDouble(XI.getText());
							xf = Double.parseDouble(XF.getText());
							h = Double.parseDouble(H.getText());
							x0 = Double.parseDouble(X0.getText());
							y0 = Double.parseDouble(Y0.getText());
						}
						catch( Exception e )
						{
	       		             System.out.println("Error en los datos"+e.getMessage());
	    		             return;														
						}
						alg.obtenerEcuacion(funcionEntrada.getText());
						tablaEuler = alg.Euler(xi, xf, h, x0, y0);
						tablaHeun = alg.Heun(xi, xf, h, x0, y0);
						tablaPuntoMedio = alg.PuntoMedio(xi, xf, h, x0, y0);
						tablaRunge = alg.RungeKutta(xi, xf, h, x0, y0);
						tablaRunge4 = alg.RK4(xi, xf, h, x0, y0);
						
						String lol = "";
						String lol2 = "";
						for( int i = 0; i < tablaEuler.length; i += 2 )
						{
							lol2 = tablaEuler[i] + "";
							l = lol2.length();
							for( int j = l; j < 30; j++ )
								lol2 += "_";
							lol += lol2;
							lol2 = tablaEuler[i+1] + "";
							l = lol2.length();
							for( int j = l; j < 30; j++ )
								lol2 += "_";
							lol += lol2;
							lol2 = tablaHeun[i+1] + "";
							l = lol2.length();
							for( int j = l; j < 30; j++ )
								lol2 += "_";
							lol += lol2;
							lol2 = tablaPuntoMedio[i+1] + "";
							l = lol2.length();
							for( int j = l; j < 30; j++ )
								lol2 += "_";
							lol += lol2;
							lol2 = tablaRunge[i+1] + "";
							l = lol2.length();
							for( int j = l; j < 30; j++ )
								lol2 += "_";
							lol += lol2;
							lol += tablaRunge4[i+1] + "\n";
						}
						
						Resultados res = new Resultados();
						res.setSize(1024, 640);
						res.setVisible(true);
						res.imprimirResultados(lol);
						
					}
				}
		);
	}
	
	
	public Ventana()
	{
		
	}
	
}
