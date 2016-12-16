package net.tlalka.android.fiszki.models.dto;

import org.parceler.Parcel;

@Parcel
public class WelcomeDto {

    String message;

    public WelcomeDto() {
    }

    public WelcomeDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
