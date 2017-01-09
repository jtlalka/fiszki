package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;

import javax.inject.Inject;

public class WelcomeActivity extends AbstractActivity {

    @BindView(R.id.welcome_message)
    protected TextView message;

    @Inject
    protected WelcomeDto welcomeDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.welcome_activity);
        super.getActivityComponent().inject(this);

        this.message.setText(welcomeDto.getMessage());
    }

    @OnClick(R.id.welcome_layout)
    public void onLayoutClick(View view) {
        super.finish();
    }
}
