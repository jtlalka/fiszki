package net.tlalka.fiszki.model.dto.parcel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WelcomeDto constructor(val message: String = "") : Parcelable
