package nl.smuldr.fancyjson.shared.model;


public final class User {

    private final long id;
    private final String name;
    private final String username;
    private final String email;
    private final Address address;
    private final String telephone;
    private final String website;
    private final Company company;

    public User(final long id, final String name, final String username, final String email, final Address address, final String telephone, final String website, final Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.website = website;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }
}
