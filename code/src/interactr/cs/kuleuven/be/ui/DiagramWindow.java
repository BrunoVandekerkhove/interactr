package interactr.cs.kuleuven.be.ui;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * A class of windows for displaying and interacting with interaction diagrams.
 *
 * @author Team 25
 * @version 1.0
 */
public class DiagramWindow extends CanvasWindow {

    /**
     * Initializes this new window with given title and diagram handler.
     *
     * @post The title of this window equals the given one.
     */
    public DiagramWindow() {
        this("",null);
    }

    /**
     * Initializes this new window with given title and diagram handler.
     *
     * @param title The title for the new window.
     * @param diagramController The diagram handler associated with this window.
     * @post The title of this window equals the given one.
     * @post The diagram handler associated with this window equals the given one.
     */
    public DiagramWindow(String title, DiagramController diagramController) {
        super(title);
        setDiagramController(diagramController);
    }

    /**
     * Paints the contents of this window in the given graphics context.
     *
     * @param context The graphics context in which to draw.
     */
    @Override
    protected void paint(Graphics context) {
        if (getDiagramController() != null)
            getDiagramController().paint(context);
    }

    /**
     * Called when the user presses (id == MouseEvent.MOUSE_PRESSED),
     *  releases (id == MouseEvent.MOUSE_RELEASED),
     *  or drags (id == MouseEvent.MOUSE_DRAGGED) the mouse.
     *
     * @param id The type of mouseEvent (PRESSSED, RELEASED, DRAGGED).
     * @param x The x value where the mouseEvent happened.
     * @param y The y value where the mouseEvent happened.
     * @param clickCount How many clicks there were.
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        if (getDiagramController() != null)
            getDiagramController().handleMouseEvent(id, x, y, clickCount);
    }

    /**
     * Called when the user presses a key (id == KeyEvent.KEY_PRESSED)
     *  or enters a character (id == KeyEvent.KEY_TYPED).
     *
     * @param id the type of mouseEvent (PRESSSED, TYPED).
     * @param keyCode The key code for the event.
     * @param keyChar The key char for the event.
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        if (getDiagramController() != null)
            getDiagramController().handleKeyEvent(id, keyCode, keyChar);
    }

    /**
     * Returns the diagram handler of this diagram window.
     */
    public DiagramController getDiagramController() {
        return this.diagramController;
    }

    /**
     * Associate this diagram window with the given diagram handler.
     *
     * @param diagramController The diagram handler that is to be associated with this window.
     */
    public void setDiagramController(DiagramController diagramController) {
        this.diagramController = diagramController;
    }

    /**
     * Variable registering this diagram window's diagram handler.
     */
    private DiagramController diagramController;

}