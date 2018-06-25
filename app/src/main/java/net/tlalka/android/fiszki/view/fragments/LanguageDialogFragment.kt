package net.tlalka.android.fiszki.view.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.model.dto.LanguagesDto
import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.view.adapters.LanguageAdapter
import org.parceler.Parcels

class LanguageDialogFragment : DialogFragment() {

    private var listener: DialogListener? = null
    private var languagesDto: LanguagesDto? = null

    interface DialogListener {
        fun onLanguageSelected(languageType: LanguageType)
    }

    private inner class LanguageListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            listener?.onLanguageSelected(languagesDto!!.languages[which])
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        listener = activity as DialogListener
    }

    override fun onCreateDialog(bundle: Bundle): Dialog {
        languagesDto = Parcels.unwrap<LanguagesDto>(arguments.getParcelable<Parcelable>(LanguagesDto::class.java.name))

        return AlertDialog.Builder(activity)
                .setIcon(R.drawable.language_icon)
                .setTitle(R.string.language_dialog_title)
                .setAdapter(LanguageAdapter(activity, languagesDto!!.languages), LanguageListener())
                .create()
    }

    companion object {
        fun getInstance(languageTypes: List<LanguageType>): LanguageDialogFragment {
            val bundle = Bundle()
            bundle.putParcelable(LanguagesDto::class.java.name, Parcels.wrap(LanguagesDto(languageTypes)))

            val fragment = LanguageDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
