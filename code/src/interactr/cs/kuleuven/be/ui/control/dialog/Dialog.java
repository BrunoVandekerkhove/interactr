package interactr.cs.kuleuven.be.ui.control.dialog;

import interactr.cs.kuleuven.be.domain.Diagram;
import interactr.cs.kuleuven.be.ui.command.Command;
import interactr.cs.kuleuven.be.ui.control.SubWindow;

/**
 * A class of dialog windows.
 *
 * @author Team 25
 * @version 1.0
 */
public abstract class Dialog extends SubWindow {

    /**
     * Initialize this new dialog with given diagram.
     *
     * @param diagram The diagram to initialize this new dialog with.
     */
    public Dialog(Diagram diagram) {
        if (diagram == null)
            throw new IllegalArgumentException("Diagram cannot be null.");
        this.diagram = diagram;
    }

    /**
     * Returns the diagram associated with this dialog.
     */
    public Diagram getDiagram() {
        return diagram;
    }

    /**
     * Registers the diagram associated with this dialog.
     */
    private Diagram diagram;



    public void goUp(){

    }
    public void goDown(){

    }

    public void focusNext(){

    }


    //TODO
    //execute method
    public void executeDialog(Command command){

    }
}