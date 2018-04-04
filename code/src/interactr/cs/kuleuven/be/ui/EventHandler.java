package interactr.cs.kuleuven.be.ui;

import interactr.cs.kuleuven.be.exceptions.InvalidAddPartyException;


import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import interactr.cs.kuleuven.be.domain.*;
import interactr.cs.kuleuven.be.exceptions.*;
import interactr.cs.kuleuven.be.exceptions.NoSuchPartyException;
import interactr.cs.kuleuven.be.ui.geometry.Point;


/**
 * A class of event handlers for interpreting incoming mouse/key events
 *  and forwarding them to the associated diagram controller.
 *
 * @author Team 25
 * @version 1.0
 */
public class EventHandler {

    /**
     * Initialize this new event handler with given diagram controller.
     *
     * @param diagramController The diagram controller to associate this event handler with.
     */
    public EventHandler(DiagramController diagramController) {
        setDiagramController(diagramController);
    }

    /**
     * Handle the mouse event at given location, of given type and with given click count.
     * Nothing can be done when in editing mode
     *
     * @param id The type of the mouse event.
     * @param x The x coordinate for the mouse event.
     * @param y The y coordinate for the mouse event.
     * @param clickCount The amount of clicks for this mouse events.
     */
    public void handleMouseEvent(int id, int x, int y, int clickCount) throws IllegalArgumentException{

        // Mouse events are ignored when the diagram controller is editing
        if (getDiagramController() != null && ! getDiagramController().isEditing()) {

            // Mouse click
            if (id == MouseEvent.MOUSE_CLICKED) {
                switch (clickCount) {
                    case 1: // Single click
                        getDiagramController().selectComponentAt(x,y);
                        break;
                    case 2: // Double click
                        try {
                            getDiagramController().addPartyAt(x,y);
                        }
                        catch (InvalidAddPartyException exception) {
                           getDiagramController().switchPartyTypeAt(x, y);
                        }
                        break;
                }
            }

            //If there is no party to move save the exception to get the coordenate out of
            if(id == MouseEvent.MOUSE_PRESSED){
                this.lastPressedX = x;
                this.lastPressedY = y;

                getDiagramController().switchSubWindow(x,y);

                try{
                    getDiagramController().resizeSubWindowAt(x,y);
                    mousepressOperationType = 1;
                }
                catch(InvalidResizeWindowException e) {

                    try{
                        getDiagramController().moveSubWindowAt(x,y);
                        mousepressOperationType = 2;
                    } catch (InvalidMoveWindowException e2){
                        try {
                            getDiagramController().movePartyAt(x,y);
                            mousepressOperationType = 3;
                        }
                        catch (NoSuchPartyException e3) {
                            mousepressOperationType = 0;
                        }
                    }


                }
            }

            // Mouse drag
            if(id == MouseEvent.MOUSE_DRAGGED){
                switch(mousepressOperationType){
                    case 1:
                        getDiagramController().resizeSubWindowTo(x,y);
                        break;
                    case 2:
                        getDiagramController().moveSubWindowTo(x,y);
                        break;

                    case 3:
                        getDiagramController().movePartyTo(x, y);
                        break;
                }
            }

            // Mouse release
            if(id == MouseEvent.MOUSE_RELEASED){
                if (mousepressOperationType == 0)
                    getDiagramController().addMessageFrom(lastPressedX, lastPressedY,x,y);

                if(mousepressOperationType == 1)
                    getDiagramController().resetResizeRhumb();



                mousepressOperationType = 0;

            }

        }

    }

    /**
     * Handle the key event of given type, having the given key code and key char.
     *
     * @param id The type of key event.
     * @param keyCode The key code for the key event.
     * @param keyChar The key char for the key event.
     */
    void handleKeyEvent(int id, int keyCode, char keyChar) {

        if (getDiagramController() != null) {
            switch (id) {
                case KeyEvent.KEY_PRESSED:
                    if (keyCode == KeyEvent.VK_ENTER)
                        getDiagramController().abortEditing();
                    else if (keyCode == KeyEvent.VK_BACK_SPACE) {
                        if (getDiagramController().isEditing())
                            getDiagramController().removeLastChar();
                        else
                            getDiagramController().deleteSelection();
                    }
                    else if (keyCode == KeyEvent.VK_CONTROL)
                        controlIsPressed = true;

                    else if(keyCode == KeyEvent.VK_N && controlIsPressed)
                        getDiagramController().addNewSubWindow();

                    else if(keyCode == KeyEvent.VK_D && controlIsPressed)
                        getDiagramController().addDuplicateSubWindow();

                    break;
                case KeyEvent.KEY_TYPED:
                    if (keyChar == KeyEvent.VK_TAB && !getDiagramController().isEditing())
                        getDiagramController().nextView();

                    else if(keyChar == KeyEvent.VK_CONTROL)
                        controlIsPressed = false;

                    else if (Character.isLetter(keyChar)
                            || Character.isDigit(keyChar)
                            || ":();-_<>*&[]".indexOf(Character.toString(keyChar)) != -1)
                        getDiagramController().appendChar(keyChar);

            }
        }
    }

    /**
     * Registers the focused party for this event handler.
     */
    private Party focusedParty = null;

    /**
     * Registers the focus coordinate for this event handler.
     */
    private Point focusCoordinate = null;

    /**
     * Returns the diagram controller associated with this event handler.
     */
    public DiagramController getDiagramController() {
        return diagramController;
    }

    /**
     * Set the diagram controller for this event handler to the given one.
     *
     * @param diagramController The new diagram controller for this event handler.
     * @post The diagram controller associated with this event handler matches the given one.
     */
    public void setDiagramController(DiagramController diagramController) {
        this.diagramController = diagramController;
    }

    /**
     * Variable registering the diagram controller of this event handler.
     */
    private DiagramController diagramController;

    /**
     * A boolean that returns if the control key is pressed on the moment
     */
    private boolean controlIsPressed = false;

    /**
     * An Int that is used as a flag to indicate the drag operation what to do
     *
     * 0 for nothing
     * 1 for resize a subwindow
     * 2 for moving a subwindow
     * 3 for moving a party
     */
    private int mousepressOperationType = 0;

    /**
     * The x coordinate of the last mouse press
     */
    private int lastPressedX;

    /**
     * The y coordinate of the last mouse press
     */
    private int lastPressedY;


}