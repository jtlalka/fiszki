package net.tlalka.android.fiszki.view.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.LanguagesDto;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.view.adapters.LanguageAdapter;
import org.parceler.Parcels;

import java.util.List;

public class LanguageDialogFragment extends DialogFragment {

    private DialogListener listener;
    private LanguagesDto languagesDto;

    public interface DialogListener {
        void onLanguageSelected(LanguageType languageType);
    }

    private class LanguageListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onLanguageSelected(languagesDto.getLanguages().get(which));
        }
    }

    public static LanguageDialogFragment getInstance(List<LanguageType> languageTypes) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(LanguagesDto.class.getName(), Parcels.wrap(new LanguagesDto(languageTypes)));

        LanguageDialogFragment fragment = new LanguageDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (DialogListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        languagesDto = Parcels.unwrap(getArguments().getParcelable(LanguagesDto.class.getName()));

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.language_icon)
                .setTitle(R.string.language_dialog_title)
                .setAdapter(new LanguageAdapter(getActivity(), languagesDto.getLanguages()), new LanguageListener())
                .create();
    }
}
