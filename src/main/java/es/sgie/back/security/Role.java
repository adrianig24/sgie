package es.sgie.back.security;

public enum Role {
    /**
     * ROLE_CTM: Customers - App Users
     * ROLE_PSY: Diagnostic center
     * ROLE_ADM: Admins
     */
    ROLE_CTM("CTM"), ROLE_PSY("PSY"), ROLE_ADM("ADM");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
