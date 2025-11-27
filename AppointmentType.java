package healthadmin;

public enum AppointmentType {
    CHECKUP, VACCINE, SURGERY, CONSULTATION;

    // 6. Using a Switch Expression
    public int getEstimatedMinutes() {
        return switch (this) {
            case CHECKUP -> 30;
            case VACCINE -> 15;
            case CONSULTATION -> 60;
            case SURGERY -> 180;
            // no default needed, all enum values covered
        };
    }
}