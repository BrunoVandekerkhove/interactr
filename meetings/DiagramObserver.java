package interactr.cs.kuleuven.be.ui;

import interactr.cs.kuleuven.be.domain.*;
import interactr.cs.kuleuven.be.ui.geometry.Point;

/**
 * A class of diagram observers.
 *
 * @author Team 25
 * @version 1.0
 */
public interface DiagramObserver {

    /**
     * Notify observers that the given party was added to the given diagram.
     *
     * @param diagram The diagram to which the party was added.
     * @param party The party that was added.
     * @param coordinates The coordinates at which the party was added.
     */
    default void diagramDidAddParty(Diagram diagram, Party party, Point coordinates) {}

    /**
     * Notify observers that the given party was replaced in the given diagram.
     *
     * @param diagram The diagram to which the party was added.
     * @param party The party that was replaced.
     * @param newParty The replacement of the party.
     */
    default void diagramDidReplaceParty(Diagram diagram, Party party, Party newParty) {}

    /**
     * Notify observers that the given messages were added to the given diagram.
     *
     * @param diagram The diagram to which the party was added.
     * @param invocationMessage The invocation message that was added.
     * @param resultMessage The result message that was added.
     * @param startCoordinates The start coordinates for the invocation message.
     * @param endCoordinates The end coordinates for the invocation message.
     */
    default void diagramDidAddMessages(Diagram diagram,
                                      InvocationMessage invocationMessage,
                                      ResultMessage resultMessage,
                                      Point startCoordinates,
                                      Point endCoordinates) {}

    /**
     * Notify observers that the given component was removed from the given diagram.
     *
     * @param diagram The diagram from which the component was removed.
     * @param component The component that was deleted.
     */
    default void diagramDidDeleteComponent(Diagram diagram, DiagramComponent component) {}

    /**
     * Notify observers that the label of the component belonging to the given diagram was edited.
     *
     * @param diagram The diagram to which the component belongs.
     * @param component The component whose label was edited.
     */
    default void diagramDidEditLabel(Diagram diagram, DiagramComponent component) {}

}