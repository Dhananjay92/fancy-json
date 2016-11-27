package nl.smuldr.fancyjson.shared.model;


public final class GeoLocation {

    private final double lat;
    private final double lon;

    public GeoLocation(final double lat, final double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
