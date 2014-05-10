/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.handler.Handler;
import java.util.Date;

/**
 *
 * @author runix
 */
public class Task {

    /**
     * Comando que se aplicara a la tarea
     */
    private final Command command;

    /**
     * Fecha en la que se realiza la accion
     */
    private final Date effectiveDate;

    /**
     * Constructor de la clase
     * @param effectiveDate
     * @param command
     */
    public Task(Date effectiveDate, Command command) {
        this.effectiveDate = effectiveDate;
        this.command = command;
    }

    /**
     * Obtiene el id del comando a aplicar
     * @return identificador del comando
     */
    public Handler getID() {
        return this.command.getId();
    }

    /**
     * Ejecuta el comando
     */
    public void execute() {
        this.command.execute();
    }

    /**
     * Deshace la operacion realizada con el comando
     */
    public void undo() {
        this.command.undo();
    }

    /**
     * Rehace la operacion realizada con el comando
     */
    public void redo() {
        this.command.redo();
    }

    /**
     * Obtiene el dia de realizacion de la tarea
     * @return fecha
     */
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
