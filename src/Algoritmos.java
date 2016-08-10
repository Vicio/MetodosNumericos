import graph.*;

public class Algoritmos 
{
	private ParseFunction funcion;
	private double[] x;
	private double[] y;
	private double[] fdxm;
	private double[] fdx;
	private double[] k = new double[6];
	
	public Algoritmos()
	{
		
	}
		
	public void obtenerEcuacion( String Ecuacion )
	{
		funcion = new ParseFunction(Ecuacion);
        if( !funcion.parse() ) 
        {
            System.out.println("Falló al procesar la función");
            return;
       }		
	}
	
	
	
	public double[] PuntoMedio( double xi, double xf, double h, double x0, double y0 )
	{
		x = new double[(int) (((xf-xi)/h)+1)];
		y = new double[(int) (((xf-xi)/h)+1)];
		fdx = new double[(int) (((xf-xi)/h)+1)];
		fdxm = new double[(int) (((xf-xi)/h)+1)];
		double [] tabla = new double[(int) (((xf-xi)/h)+1)*2];
		
		x[0] = x0;
		y[0] = y0;
		
		for( int i = 0; i < (xf-xi)/h; i++ )
		{
			try
			{
				fdx[i] = funcion.getResult(x[i], y[i]);
				fdxm[i] = y[i] + (fdx[i] * (h/2));
				fdxm[i] = funcion.getResult(x[i] + (h/2), fdxm[i]);
				y[i+1] = y[i] + (fdxm[i] * h);
				x[i + 1] = x[i] + h;
				//System.out.println(x[i] + " " + y[i]);
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		int i = 0;
		for( int j = 0; j < tabla.length; j+=2 )
		{
				tabla[j] = x[i];
				tabla[j+1] = y[i];
				i++;
		}
		
		return tabla;
		
	}
	
	public double[] Heun( double xi, double xf, double h, double x0, double y0 )
	{
		x = new double[(int) (((xf-xi)/h)+1)];
		y = new double[(int) (((xf-xi)/h)+1)];
		fdx = new double[(int) (((xf-xi)/h)+1)];
		fdxm = new double[(int) (((xf-xi)/h)+1)];
		double [] tabla = new double[(int) (((xf-xi)/h)+1)*2];
		
		x[0] = x0;
		y[0] = y0;
		for( int i = 0; i < (xf-xi)/h; i++ )
		{
			try
			{
				fdx[i] = funcion.getResult(x[i], y[i]);
				//System.out.println(fdx[i]);				
				fdxm[i] = y[i] + (fdx[i]*h);
				//System.out.println(fdxm[i]);
				x[i+1] = x[i] + h;
				fdx[i+1] = funcion.getResult(x[i+1], fdxm[i]);
				//System.out.println(fdx[i+1]);
				fdx[i+1] = (fdx[i] + fdx[i+1])/2;
				//System.out.println(fdx[i+1]);
				y[i+1] = y[i] + (fdx[i+1]*h);
				//System.out.println(y[i+1]);
				for( int j = 0; j < 20; j++ )
					y[i+1] = y[i] + ((fdx[i] + funcion.getResult(x[i+1], y[i+1]))/2)*h;
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			//System.out.println(y[i]);
			//System.out.println();
			//System.out.println(x[i] + " " + y[i]);
			
			
		}
		
		int i = 0;
		for( int j = 0; j < tabla.length; j+=2 )
		{
				tabla[j] = x[i];
				tabla[j+1] = y[i];
				i++;
		}
		
		return tabla;
		
	}
	
	public double[] RungeKutta( double xi, double xf, double h, double x0, double y0 )
	{
		x = new double[(int) (((xf-xi)/h)+1)];
		y = new double[(int) (((xf-xi)/h)+1)];
		
		double [] tabla = new double[(int) (((xf-xi)/h)+1)*2];
		
		x[0] = x0;
		y[0] = y0;
		for( int i = 0; i < (xf-xi)/h; i++ )
		{
			try
			{
				k[0] = funcion.getResult(x[i], y[i]);
				k[1] = funcion.getResult(x[i] + (h/4), y[i] + ( (k[0]*h) / 4) );
				k[2] = funcion.getResult(x[i] + (h/4), y[i] + ( (k[0]*h) / 8) + ( (k[2]*h) / 8) );
				k[3] = funcion.getResult(x[i] + (h/2), y[i] - ( (k[1]*h) / 2) + (k[2]*h) );
				k[4] = funcion.getResult(x[i] + (3/4)*h, y[i] + ( (k[0]*h*3) / 16) + ((k[3]*h*9) / 16) );
				k[5] = funcion.getResult(x[i] + h, y[i] - (3/7)*(k[0]*h) + (2/7)*(k[1]*h) + (12/7)*(k[2]*h - (12/7)*(k[3]*h) + (8/7)*(k[4]*h) ) );
				x[i+1] = x[i] + h;
				y[i+1] = y[i] + ((7*k[0] + 32*k[2] + 12*k[3] + 32*k[4] + 7*k[5])*h)/90;
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			//System.out.println(y[i]);
			//System.out.println();
			//System.out.println(x[i] + " " + y[i]);
			
			
		}
		
		int i = 0;
		for( int j = 0; j < tabla.length; j+=2 )
		{
				tabla[j] = x[i];
				tabla[j+1] = y[i];
				i++;
		}
		
		return tabla;
		
	}
	public double[] RK4( double xi, double xf, double h, double x0, double y0 )
	{
		x = new double[(int) (((xf-xi)/h)+1)];
		y = new double[(int) (((xf-xi)/h)+1)];
		
		double [] tabla = new double[(int) (((xf-xi)/h)+1)*2];
		
		x[0] = x0;
		y[0] = y0;
		for( int i = 0; i < (xf-xi)/h; i++ )
		{
			try
			{
				System.out.print(x[i] + "\t");
				System.out.print(y[i] + "\t");
				k[0] = funcion.getResult(x[i], y[i]);
				System.out.print(k[0] + "\t");
				k[1] = funcion.getResult(x[i] + (h/2), y[i] + ( (k[0]*h) / 2) );
				System.out.print(k[1] + "\t");
				k[2] = funcion.getResult(x[i] + (h/2), y[i] + ( (k[1]*h) / 2) );
				System.out.print(k[2] + "\t");
				k[3] = funcion.getResult(x[i] + (h), y[i] + (k[2]*h));
				System.out.print(k[3] + "\t");
				x[i+1] = x[i] + h;
				y[i+1] = y[i] + (h/6)*(k[0] + 2*k[1] + 2*k[2] + k[3]);
				System.out.print(y[i+1] + "\n");
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			//System.out.println(y[i]);
			//System.out.println();
			//System.out.println(x[i] + " " + y[i]);
			
			
		}
		
		int i = 0;
		for( int j = 0; j < tabla.length; j+=2 )
		{
				tabla[j] = x[i];
				tabla[j+1] = y[i];
				i++;
		}
		
		return tabla;
		
	}

	public double[] Euler( double xi, double xf, double h, double x0, double y0 )
	{
		x = new double[(int) (((xf-xi)/h)+1)];
		y = new double[(int) (((xf-xi)/h)+1)];
		fdx = new double[(int) (((xf-xi)/h)+1)];
		double [] tabla = new double[(int) (((xf-xi)/h)+1)*2];
		x[0] = x0;
		y[0] = y0;
		
		for( int i = 0; i < (xf-xi)/h; i++ )
		{
			try 
			{
				fdx[i] = funcion.getResult(x[i], y[i]);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			y[i + 1] = y[i] + (fdx[i]*h);
			x[i + 1] = x[i] + h;
			//System.out.println(x[i] + " " + fdx[i] + " " + y[i] );
		}
		
		int i = 0;
		for( int j = 0; j < tabla.length; j+=2 )
		{
				tabla[j] = x[i];
				tabla[j+1] = y[i];
				i++;
		}
		
		return tabla;

	}
	
}
