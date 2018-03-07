package interactr.cs.kuleuven.be.ui.exceptions;

import interactr.cs.kuleuven.be.domain.Party;

/**
 * A class of exceptions for use when attempting to add a party
 *  at a coordinate held by an other diagram component.
 *
 * @author Team 25
 * @version 1.0
 */
public class InvalidAddPartyException extends RuntimeException {

    /**
     * Initialize this new invalid add exception with given party.
     *
     * @param party The party associated with this invalid add exception.
     */
    public InvalidAddPartyException(Party party) {
        setParty(party);
    }

    /**
     * Returns the party associated with this invalid add exception.
     */
    public Party getParty() {
        return party;
    }

    /**
     * Set the party associated with this invalid add exception to the given one.
     *
     * @param party The new party to associate this exception with.
     */
    public void setParty(Party party) {
        this.party = party;
    }

    /**
     * Variable registering the party associated with this invalid add exception.
     */
    private Party party;

}