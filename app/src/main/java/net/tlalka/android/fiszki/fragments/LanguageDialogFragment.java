package net.tlalka.android.fiszki.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LanguageAdapter;
import net.tlalka.android.fiszki.models.dto.LanguagesDto;
import net.tlalka.android.fiszki.models.types.LanguageType;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (DialogListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        languagesDto = Parcels.unwrap(getArguments().getParcelable(LanguagesDto.class.getName()));

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon_lesson_stats)
                .setTitle(R.string.language_dialog_title)
                .setAdapter(new LanguageAdapter(getActivity(), languagesDto.getLanguages()), new LanguageListener())
                .create();
    }
}
