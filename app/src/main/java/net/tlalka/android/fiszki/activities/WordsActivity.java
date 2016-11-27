package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import net.tlalka.android.fiszki.R;

public class WordsActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.words_view);
    }

    public void onViewClick(View view) {
        super.finish();
    }
}
