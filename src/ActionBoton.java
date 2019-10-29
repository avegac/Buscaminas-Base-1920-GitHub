import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrÃ¡ que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{
	private VentanaPrincipal ventana;
	private int posX;
	private int posY;
	
	/**
	 * Constructor parametrizado
	 * @param ventana
	 * @param posX
	 * @param posY
	 */
	public ActionBoton(VentanaPrincipal ventana, int posX, int posY) {
		this.ventana=ventana;
		this.posX=posX;
		this.posY=posY;
	}
	
	/**
	 *AcciÃ³n que ocurrirÃ¡ cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Si el botón pulsado es una mina se acaba la partida
		if(ventana.juego.abrirCasilla(posX, posY)==false){
			ventana.mostrarFinJuego(true);
			JOptionPane.showMessageDialog(null, "BOOM, perdiste");
		}
		//Si el botón pulsado no es una mina mostramos el número indicando las minas que hay a su alrededor
		else {
			ventana.mostrarNumMinasAlrededor(posX, posY);
		}
	}

}
