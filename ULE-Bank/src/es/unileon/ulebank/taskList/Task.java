/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import java.util.Date;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.handler.Handler;

/**
 *
 * @author runix
 */
public class Task {

    
    private final Command command;

    private final Date effectiveDate;

    public Task(Date effectiveDate, Command command) {
        this.effectiveDate = effectiveDate;
        this.command = command;
    }

    public Handler getID() {
        return this.command.getId();
    }

    public void execute() throws Exception {
        this.command.execute();
    }

    public void undo() throws Exception {
        this.command.undo();
    }

    public void redo() throws Exception {
        this.command.redo();
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
