import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Models instrument storage and distribution.
 *
 * @author Maggie Cook
 *
 */
class InstrumentDistributor {

    private int storageCapacity;
    private int currentInventory;
    private Sequence<Instrument> storage;
    private SimpleWriter out;

    public InstrumentDistributor(int storageCapacity, SimpleWriter out) {
        this.storageCapacity = storageCapacity;
        this.currentInventory = 0;
        this.storage = new Sequence1L<>();
        this.out = out;
    }

    public void addInstrument(String id, String type, boolean isDistributed,
            boolean needsRepair) {
        Instrument instrument = new Instrument(id, type, isDistributed,
                needsRepair);
        this.storage.add(this.currentInventory, instrument);
        this.currentInventory++;
        this.out.println("Added " + instrument.getFullName());
    }

    public void removeInstrument(String id) {
        if (this.containsInstrument(id)) {
            Instrument removed = this.storage
                    .remove(this.getInstrumentIndex(id));
            this.out.println("Removed " + removed.getFullName());
            this.currentInventory--;
        } else {
            this.out.println("Instrument not found");
        }
    }

    // helper
    public boolean containsInstrument(String id) {
        int pos = -1;
        for (int i = 0; i < this.currentInventory; i++) {
            if (this.storage.entry(i).getId() == id) {
                pos = i;
            }
        }

        return pos >= 0;
    }

    // helper
    public int getInstrumentIndex(String id) {
        // add an assert here to make sure storage contains the instrument?

        int pos = 0;
        for (int i = 0; i < this.currentInventory; i++) {
            if (this.storage.entry(i).getId() == id) {
                pos = i;
            }
        }

        return pos;
    }

    public int getAvailibleSpace() {
        return this.storageCapacity - this.currentInventory;
    }

    public int getNumOfAvailableInstruments() {
        int available = 0;
        for (Instrument current : this.storage) {
            if (!current.isDistributed()) {
                available++;
            }
        }

        return available;
    }

    public void checkCondition(String id) {
        if (this.containsInstrument(id)) {
            Instrument current = this.storage
                    .entry(this.getInstrumentIndex(id));
            if (current.needsRepair()) {
                this.out.println(current.getFullName() + " needs repair");
            } else {
                this.out.println(
                        current.getFullName() + " does not need repair");
            }
        } else {
            this.out.println(id + " was not found");
        }
    }

    public void checkIfDistributed(String id) {
        if (this.containsInstrument(id)) {
            Instrument current = this.storage
                    .entry(this.getInstrumentIndex(id));
            if (current.isDistributed()) {
                this.out.println(current.getFullName() + " is distributed");
            } else {
                this.out.println(current.getFullName() + " is in storage");
            }
        } else {
            this.out.println(id + " was not found");
        }
    }

    public void distributeInstrument(String id) {
        if (this.containsInstrument(id)) {
            Instrument current = this.storage
                    .entry(this.getInstrumentIndex(id));
            if (current.isDistributed()) {
                this.out.println(current.getFullName()
                        + " is already distributed, please return it to storage first");
            } else {
                current.changeDistribution();
                this.out.println(
                        current.getFullName() + " has been distributed");
            }
        } else {
            this.out.println(id + " was not found");
        }
    }

    public void returnInstrument(String id) {
        if (this.containsInstrument(id)) {
            Instrument current = this.storage
                    .entry(this.getInstrumentIndex(id));
            if (current.isDistributed()) {
                current.changeDistribution();
                this.out.println(current.getFullName()
                        + " has been returned to storage");
            } else {
                this.out.println(
                        current.getFullName() + " is already in storage");
            }
        } else {
            this.out.println(id + " was not found");
        }
    }
}

/**
 * Models an instrument and its state.
 *
 * @author Maggie Cook
 *
 */
class Instrument {

    private String id;
    private String type;
    private boolean isDistributed;
    private boolean needsRepair;

    public Instrument(String id, String type, boolean isDistributed,
            boolean needsRepair) {
        this.id = id;
        this.type = type;
        this.isDistributed = isDistributed;
        this.needsRepair = needsRepair;

    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getFullName() {
        return this.type + " " + this.id;
    }

    public boolean isDistributed() {
        return this.isDistributed;
    }

    public boolean needsRepair() {
        return this.needsRepair;
    }

    public void changeCondition(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }

    public void changeDistribution() {
        this.isDistributed = !this.isDistributed;
    }
}

/**
 * Main method to test implementation.
 */
public class Main {

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        InstrumentDistributor dublinJeromeStorage = new InstrumentDistributor(
                80, out);

        dublinJeromeStorage.addInstrument("XYZ123", "Baritone", true, false);
        dublinJeromeStorage.addInstrument("BDH224", "Sousaphone", false, true);

        out.println(dublinJeromeStorage.getAvailibleSpace());

        dublinJeromeStorage.checkCondition("BDH224");
        dublinJeromeStorage.checkCondition("XYZ123");
        dublinJeromeStorage.checkIfDistributed("BDH224");
        dublinJeromeStorage.checkIfDistributed("XYZ123");

        out.println(dublinJeromeStorage.getNumOfAvailableInstruments());
        dublinJeromeStorage.distributeInstrument("BDH224");
        out.println(dublinJeromeStorage.getNumOfAvailableInstruments());

        dublinJeromeStorage.removeInstrument("XYZ123");
        dublinJeromeStorage.returnInstrument("BDH224");
        out.println(dublinJeromeStorage.getNumOfAvailableInstruments());
        out.println(dublinJeromeStorage.getAvailibleSpace());

        in.close();
        out.close();
    }
}