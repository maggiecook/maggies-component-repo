//package components.instrumentdistributor;

/**
 * {@code InstrumentDistributorKernel} enhanced with secondary methods.
 */
public interface InstrumentDistributor extends InstrumentDistributorKernel {

    /**
     * Calculates and reports the amount of available space left in {@code this}
     *
     * @return the total space - the taken slots
     * @ensures getAvailibleSpace = the total space - the taken slots
     *
     */
    int getAvailibleSpace();

    /**
     * Calculates and reports the number of instruments which are not
     * distributed.
     *
     * @return number of instruments which are not distributed
     * @ensures getNumOfAvailableInstruments = number of instruments which are
     *          not distributed
     */
    public int getNumOfAvailableInstruments();

    /**
     * Reports whether the instrument with the given id currently needs repair.
     *
     * @param id
     *            the id of the instrument to check
     *
     * @return true iff the instrument with matching id needs repair
     * @ensures needsRepair = (the instrument needs repair)
     */
    public boolean needsRepair(String id);

    /**
     * Reports whether the instrument with the given id is currently
     * distributed.
     *
     * @param id
     *            the id of the instrument to check
     *
     * @return true iff the instrument with matching id is distributed
     * @ensures isDistributed = (the instrument is distributed)
     */
    public boolean isDistributed(String id);

    /**
     * Distributes the instrument with given id.
     *
     * @param id
     *            the id of the instrument to distribute
     *
     * @ensures instrument with given id is distributed
     */
    public void distributeInstrument(String id);

    /**
     * Resturns the instrument with given id to storage.
     *
     * @param id
     *            the id of the instrument to return
     *
     * @ensures instrument with given id is in storage
     */
    public void returnInstrument(String id);
}