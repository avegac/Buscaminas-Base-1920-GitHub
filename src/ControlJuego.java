import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posici�n guarda el n�mero -1
 * Si no hay una mina, se guarda cu�ntas minas hay alrededor.
 * Almacena la puntuaci�n de la partida.
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**M�todo para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habr� inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cu�ntas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		int randomX, randomY, cont=0;

		//TODO: Repartir minas e inicializar puntaci�n. Si hubiese un tablero anterior, lo pongo todo a cero para inicializarlo.
		puntuacion = 0;
		
		do {
			randomX=(int)(Math.random()*10);
			randomY=(int)(Math.random()*10);
			
			if(tablero[randomX][randomY]!=MINA) {
				tablero[randomX][randomY]=MINA;
				cont++;
			}
		}while(cont<MINAS_INICIALES);
		
		
		//Al final del m�todo hay que guardar el n�mero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
	}
	
	/**C�lculo de las minas adjuntas: 
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdr�n LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdr�n 0.
	 * @param i: posici�n vertical de la casilla a rellenar.
	 * @param j: posici�n horizontal de la casilla a rellenar.
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int posX, int posY){
		int contadorMinas=0;
		
		for(int i=posX-1; i<=posY+1;i++) {
			for(int j=posY-1; j<=posY+1;j++) {
				if(((i>=0)&&(i<=LADO_TABLERO)) && (j>=0)&&(j<=LADO_TABLERO)) {
					if(tablero[i][j]==MINA) {
						contadorMinas++;
					}
				}
			}
		}
		
		return contadorMinas;
	}
	
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int posX, int posY){
		if(tablero[posX][posY]!=MINA) {
			puntuacion++;
			tablero[posX][posY]=calculoMinasAdjuntas(posX, posY);
			
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		boolean finalizado = false;
		int casillasAbiertas=0;
		
		for(int i=0; i<tablero.length;i++) {
			for(int j=0; j<tablero[i].length;j++) {
				if((tablero[i][j]!=MINA)&&(abrirCasilla(i,j)==true)) {
					if(casillasAbiertas == 80) {
						finalizado=true;
					}
					else {
						finalizado=false;
					}
					
					casillasAbiertas++;
				}
				else {
					finalizado=false;
				}
			}
		}
		return finalizado;
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: "+this.puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return calculoMinasAdjuntas(i, j);
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return this.puntuacion;
	}
	
}
