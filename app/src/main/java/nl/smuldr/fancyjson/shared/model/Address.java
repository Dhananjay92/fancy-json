package nl.smuldr.fancyjson.shared.model;


public final class Address {

    private final String street;
    private final String suite;
    private final String city;
    private final String zipcode;
    private final GeoLocation geo;

    public Address(final String street, final String suite, final String city, final String zipcode, final GeoLocation geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public GeoLocation getGeo() {
        return geo;
    }
}
