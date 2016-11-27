package nl.smuldr.fancyjson.shared.model;


public final class Company {

    private final String name;
    private final String catchPhrase;
    private final String bs;

    public Company(final String name, final String catchPhrase, final String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }
}
