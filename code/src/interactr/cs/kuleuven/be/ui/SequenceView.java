package interactr.cs.kuleuven.be.ui;

import interactr.cs.kuleuven.be.domain.Diagram;
import interactr.cs.kuleuven.be.domain.DiagramComponent;
import interactr.cs.kuleuven.be.domain.Party;
import interactr.cs.kuleuven.be.ui.exceptions.InvalidAddPartyException;
import interactr.cs.kuleuven.be.ui.geometry.Figure;

import java.awt.*;

/**
 * A class of sequence diagram views. Sequence diagram views display diagrams
 *  as a composite of parties with messages sent between them.
 *  The parties are drawn in a top row, while the messages are drawn in the rows
 *  underneath this top row.
 *  Below each party, a lifeline is drawn.
 *
 * @author Team 25
 * @version 1.0
 */
public class SequenceView extends DiagramView {

    /**
     * The height of the party row.
     */
    private static int PARTY_ROW_HEIGHT = 75;

    /**
     * The height of each message row.
     */
    private static int MESSAGE_ROW_HEIGHT = 30;

    @Override
    public void display(PaintBoard paintBoard, Diagram diagram, SelectionManager selectionManager) {
        super.displayFigures(paintBoard, diagram, selectionManager);
        paintBoard.setColor(Color.GRAY);
        for (Party party : figures.keySet()) {
            Figure partyFigure = figures.get(party);
            paintBoard.drawLine(partyFigure.getX() + partyFigure.getWidth() / 2,
                    PARTY_ROW_HEIGHT,
                    partyFigure.getX() + partyFigure.getWidth() / 2,
                    paintBoard.getHeight());
        }
        paintBoard.setColor(Color.BLACK);
        paintBoard.drawLine(0, PARTY_ROW_HEIGHT, paintBoard.getWidth(), PARTY_ROW_HEIGHT);
    }

    @Override
    public void addParty(Diagram diagram, Party party, int x, int y) throws InvalidAddPartyException {
        if (y >= PARTY_ROW_HEIGHT)
            throw new InvalidAddPartyException();
        super.addParty(diagram, party, x, 5);
    }

    @Override
    public void registerParty(Party party, int x, int y) {
        super.registerParty(party, x, 5);
    }

    @Override
    public DiagramComponent selectableComponentAt(Diagram diagram, int x, int y) {
        for (Party party : diagram.getParties()) {
            System.out.println(figureForParty(party).getLabelBounds().getY());
            System.out.println(figureForParty(party).getLabelBounds().getWidth());
            if (figureForParty(party).isLabelHit(x,y))
                return party;
        }
        return null;
    }

    @Override
    public String viewName() {
        return "Sequence View";
    }

    @Override
    public void moveParty(Diagram diagram, Party party, int x, int y){
        super.moveParty(diagram, party, x, figures.get(party).getY());
    }

}