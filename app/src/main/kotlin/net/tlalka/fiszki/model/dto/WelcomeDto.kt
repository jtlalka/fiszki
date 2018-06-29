package net.tlalka.fiszki.model.dto

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class WelcomeDto {

    var message: String = ""

    @ParcelConstructor
    constructor()

    constructor(message: String) {
        this.message = message
    }
}
