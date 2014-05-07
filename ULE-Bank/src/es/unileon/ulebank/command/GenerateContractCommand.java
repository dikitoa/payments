package es.unileon.ulebank.command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.CardType;

/**
 * @author Israel
 * Comando para generar el contrato segun el tipo de tarjeta
 */
public class GenerateContractCommand implements Command {
	//Identificador del comando que para este caso no es necesario
	private Handler id;
	//Tipo de tarjeta 
	private CardType type;
	
	/**
	 * Constructor de la clase
	 * @param type
	 */
	public GenerateContractCommand(CardType type) {
		this.type = type;
	}
	
	/**
	 * Genera el contrato y lo muestra en el escritorio en formato PDF
	 */
	@Override
	public void execute() {
		//Crea un fichero para generar el contrato
		File file = null;
		//Obtiene una instancia del escritorio
		Desktop desktop = Desktop.getDesktop();
		try {
			//Genera un contrato en funcion del tipo de tarjeta
			switch (type) {
			case DEBIT:
				file = new File("contratos/debitcontract.pdf");
				desktop.open(file);
				break;
			case CREDIT:
				file = new File("contratos/creditcontract.pdf");
				desktop.open(file);
				break;
			case PURSE:
				
				break;
			case REVOLVING:
				
				break;
			default:
				break;
			}
		} catch (IOException e) {
			Logger.getLogger(GenerateContractCommand.class.toString()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Operacion no soportada, no se puede deshacer la generacion del contrato
	 */
	@Override
	public void undo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Operacion no soportada, como no se puede deshacer la generacion del contrato tampoco se puede rehacer
	 */
	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve el identificador del comando
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
	
}
