package interactr.cs.kuleuven.be.ui.command;

import interactr.cs.kuleuven.be.ui.control.diagram.DiagramView;
import interactr.cs.kuleuven.be.ui.geometry.Point;

/**
 * A class of commands for moving parties.
 *
 * @author Team 25
 * @version 1.0
 */
public class MovePartyCommand extends DragCommand {

    /**
     * Initialize this new command with given source and end location.
     *
     * @param fromLocation The point to
     */
    public MovePartyCommand(Point fromLocation, Point toLocation) {
        super(fromLocation, toLocation);
    }

    @Override
    public void executeDiagramView(DiagramView view) {
        try {
            view.moveParty(
                    view.getRelativeCoordinates(fromLocation).getX(),
                    view.getRelativeCoordinates(fromLocation).getY(),
                    view.getRelativeCoordinates(toLocation).getX(),
                    view.getRelativeCoordinates(toLocation).getY());
        }
        catch (Exception e) {
            throw new CommandNotProcessedException();
        }
    }

}