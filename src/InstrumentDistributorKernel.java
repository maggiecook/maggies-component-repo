//package components.instrumentdistributor;

import components.standard.Standard;

/**
 * Instrument distributor componentwith primary methods.
 */
public interface InstrumentDistributorKernel
        extends Standard<InstrumentDistributor> {

    /**
     * Creates a new Instrument object and adds it to {@code this}.
     *
     * @param id
     *            the id of the instrument
     * @param type
     *            the type of instrument
     * @param isDistributed
     *            whether the instrument is currently distributed
     * @param needsRepair
     *            whether the instrument currently needs repair
     * @ensures this contains the new instrument && the size of this has
     *          increased by one
     */
    void addInstrument(String id, String type, boolean isDistributed,
            boolean needsRepair);

    /**
     * Removes the specified Instrument object that matches in id.
     *
     * @param id
     *            the id of the instrument to be removed
     *
     * @ensures this no longer contains instrument with matching id && the size
     *          of this has decreased by one
     */
    void removeInstrument(String id);

    /**
     * Returns whether an instrument with {@code id} is in {@code this}.
     *
     * @param id
     *            the id of the instrument to check
     *
     * @return true iff an instrument with matching id is in this
     * @ensures containsInstrument = this.contains(instrument with matching id)
     */
    boolean containsInstrument(String id);

}
