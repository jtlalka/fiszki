package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;

public class HelpActivity extends BasePageActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.help_activity);
        super.getActivityComponent().inject(this);
    }

    @OnClick(R.id.help_layout)
    public void onViewClick(View view) {
        super.finish();
    }
}
