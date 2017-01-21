package net.tlalka.android.fiszki.model.dto;

import org.parceler.Parcel;

@Parcel
public class WelcomeDto {

    protected String message;

    public WelcomeDto() {
    }

    public WelcomeDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
