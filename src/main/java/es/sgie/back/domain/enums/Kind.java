package es.sgie.back.domain.enums;

/**
 * Enum for kind of users
 */
public enum Kind {
    CUSTOMER("CTM"), MEDICAL("DGC"), ADMIN("ADM");

    private String kind;

    private Kind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return this.kind;
    }
}
