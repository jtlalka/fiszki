package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;

public class WordsActivity extends BasePageActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.words_activity);
        super.getActivityComponent().inject(this);
    }

    @OnClick(R.id.words_layout)
    public void onLayoutClick(View view) {
        super.finish();
    }
}
