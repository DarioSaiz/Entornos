package Primerintento;
import java.util.*;
public class Hundir {
	
	public static Scanner dato= new Scanner(System.in); 
	public static final char AGUA='A', AGUA_NO_TOCADO='.', TOCADO='X'; 
	public static final int TAMANNIO=10;
	
	
	public static void main(String[] args) throws InterruptedException{
		
	int puntosJugador, puntosOrdenador, fila, columna; 
	boolean disparoCorrecto=false; 
	char resultadoDisparo='A'; 
	
	puntosJugador=24; 
	puntosOrdenador=24;
	
	char mapaUsuario[][]  = new char [TAMANNIO][TAMANNIO];
	char mapaOrdenador[][] = new char [TAMANNIO][TAMANNIO];
	char mapaOrdenadorParaUsuario[][] = new char [TAMANNIO][TAMANNIO];
	int disparo[] = new int [2];
	

	inicializacionMapa(mapaUsuario);
	inicializacionMapa(mapaOrdenador);
	inicializacionMapa(mapaOrdenadorParaUsuario);
	imprimirMapa("MAPA USUARIO", mapaUsuario);
	imprimirMapa("MAPA ORDENADOR", mapaOrdenador);
	imprimirMapa("MAPA MAPA ORDENADOR PARA USUARIO", mapaOrdenadorParaUsuario);
	
	registraBarcos(mapaUsuario);
	registraBarcos(mapaOrdenador); 
	
	imprimirMapa("MAPA USUARIO", mapaUsuario);
	imprimirMapa("MAPA ORDENADOR", mapaOrdenador);
	imprimirMapa("MAPA MAPA ORDENADOR PARA USUARIO", mapaOrdenadorParaUsuario);   
	
	while (puntosOrdenador!=0 && puntosJugador!=0) {
		System.out.println("Tu turno\n===========\n");
		System.out.println("PUNTOS ORDENADOR"+puntosOrdenador);
		System.out.println("Tus puntos:"+puntosJugador);
		imprimirMapa("MAPA MAPA ORDENADOR PARA USUARIO", mapaOrdenadorParaUsuario);
		disparoCorrecto=false; 
		while(!disparoCorrecto) {
			disparo=pedirCasilla(); 
			if (disparo[0]!=-1) {
					resultadoDisparo=evaluarDisparo(disparo, mapaOrdenador);
					if(resultadoDisparo=='E') 
						System.out.println("DISPARO NO VÁLIDO");
					else {
						disparoCorrecto=true;
						fila=disparo[0];
						columna=disparo[1]; 
						mapaOrdenadorParaUsuario[fila][columna]=resultadoDisparo;
						mapaOrdenador[fila][columna]=resultadoDisparo;
						if(resultadoDisparo=='X') puntosOrdenador--; 
					}
				
			}else 
				System.out.println("COORDENADAS NO VÁLIDAS"); 
			
		}
		System.out.println("PUNTOS ORDENADOR:"+puntosOrdenador);
		imprimirMapa("MAPA MAPA ORDENADOR PARA USUARIO", mapaOrdenadorParaUsuario);
		Thread.sleep(3000);
		
		
		if(puntosJugador!=0) {
			System.out.println("TURNO DEL ORDENADOR\n==========\n");
			System.out.println("PUNTOS ORDENADOR:"+puntosOrdenador);
			System.out.println("Tus puntos:"+puntosJugador);
			
			disparoCorrecto=false; 
			while(!disparoCorrecto) {
				disparo=generarDisparoAleatorio(); 
						resultadoDisparo=evaluarDisparo(disparo,mapaUsuario);
						if(resultadoDisparo=='E') 
							System.out.println("DISPARO NO VÁLIDO");
						else {
							disparoCorrecto=true;
							fila=disparo[0];
							columna=disparo[1]; 
							mapaUsuario[fila][columna]=resultadoDisparo;
							if(resultadoDisparo=='X') puntosOrdenador--; 
						}
					
			}

			System.out.println("PUNTOS ORDENADOR:"+puntosOrdenador);
			System.out.println("Tus puntos:"+puntosJugador);
			System.out.println();
			imprimirMapa("MAPA MAPA ORDENADOR PARA USUARIO", mapaUsuario);
			Thread.sleep(3000);
		}
	}
	if (puntosOrdenador==0) System.out.println("ENHORABUENA. HAS GANADO LA PARTIDA");
	else System.out.println ("HAS PERDIDO");
	
}
	
	public static int[] generarDisparoAleatorio() {
		int[] c= new int[2];
		c[0]=(int)(Math.random()*TAMANNIO); 
		c[1]=(int)(Math.random()*TAMANNIO);
		return c; 
	}
	
	
	public static int [] pedirCasilla() {
		//Declaración del vector 
		int [] c=new int[2]; 
		String coordenadas; 
		char coordenada1, coordenada2; 
		System.out.println("Introduce las coordenadas del disparo:");
		coordenadas=dato.next();
		coordenada1=Character.toUpperCase(coordenadas.charAt(0));
		coordenada2=coordenadas.charAt(1);
		if (Character.getNumericValue(coordenada1)<Character.getNumericValue('A')+TAMANNIO && Character.getNumericValue(coordenada2)<Character.getNumericValue('0')+TAMANNIO) {
			c[0]=Character.getNumericValue(coordenada1)-Character.getNumericValue('A');
		    c[1]=Character.getNumericValue(coordenada1)-Character.getNumericValue('A');
		}else {
			c[0]=-1;
			c[1]=-1;
		
		    
		}
		return c; 
	}
	
	public static char evaluarDisparo(int[] d, char[][] m){
		int fila, columna; 
		fila=d[0];
		columna=d[1];
		if(m[fila][columna]>=1 && m[fila][columna]<=5) return 'X';
		else if (m[fila][columna]==AGUA_NO_TOCADO) return 'A'; 
		else return 'E'; 
		
	}
	
	public static void inicializacionMapa (char m[][]) {
		int c, f; 
		c=0; 
		f=0; 
		
		for (f=0; f<m.length; f++) {
			for (c=0; c<m[f].length; c++) {
					m[f][c]= AGUA_NO_TOCADO;
			}
		}	
	}
	
	public static void imprimirMapa (String titulo,char m[][]) {
				
		char letrasCabecera[] = new char[TAMANNIO];
		for (int f=0; f<TAMANNIO; f++) {
			 letrasCabecera[f]= (char)('A'+f);
		}	
		
		System.out.print("\n"+titulo+"\n    ");
		
		for (int f=0; f<TAMANNIO; f++) {
			System.out.printf("[%1d]", f);
		}
		
		for (int c1=0; c1<m.length; c1++) {
			System.out.printf("\n[%1S]", letrasCabecera[c1]);
			for (int c2=0; c2<m.length;c2++) {
			System.out.printf(" %1S ",m[c1][c2]);
			}
		}
		
	}
	
	public static void registraBarcos(char m[][]) {
		int barcosColocar[] = {5,5,3,3,3,1,1,1,1,1};	
		char direccion[] = {'V','H'};
		boolean colocado;
		int fila, columna, vertHor; 
		
		for (int tamanioBarco : barcosColocar) { //variable del mismo tipo del vector y variable conde lo quiero guardar
			colocado=false;
			while(!colocado) {
				
				columna=(int)(Math.random()*TAMANNIO);
				fila=(int)(Math.random()*TAMANNIO);
				vertHor=(int)(Math.random()*2);
			
			
			if (direccion[vertHor]=='V' && (fila+tamanioBarco<TAMANNIO) || direccion[vertHor]=='H' && (columna+tamanioBarco<TAMANNIO)) {
				boolean vacio=true; 	
				if (direccion[vertHor]=='V') {
					int f=0; 
					while (f<tamanioBarco && vacio) {
						if ((m[fila+f][columna])!=AGUA_NO_TOCADO)
							vacio=false; 
							else f++;
					} 
				
				}else {
					int c=0; 
					while (c<tamanioBarco && vacio) {
						if ((m[fila][columna+c])!=AGUA_NO_TOCADO)
							vacio=false; 
						else c++;
				
					}
				}
			 
			if (vacio) {
			   for (int i=0 ; i<tamanioBarco ; i++) {			
				if (direccion[vertHor]=='V') m[fila+i][columna]=Integer.toString(tamanioBarco).charAt(0);
				else (m[fila][columna+i])=Integer.toString(tamanioBarco).charAt(0);
					
				}
			   colocado=true; 
			}
		}
		}
	}
	}

}		

/*
Modificadores de formato %.2f para dos decimales
%03d rellena con ceros los espacios en blanco en 3 posiciones
% s para string contenido en minuscula nos lo escribe
% S para string contenido en minuscula nos lo escribe


*/






