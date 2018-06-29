package net.tlalka.fiszki.model.dto.parcel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import net.tlalka.fiszki.model.types.LanguageType

@Parcelize
data class LanguagesDto constructor(var languages: List<LanguageType>) : Parcelable
