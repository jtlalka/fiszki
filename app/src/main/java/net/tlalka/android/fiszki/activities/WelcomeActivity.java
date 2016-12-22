package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.dto.WelcomeDto;

import javax.inject.Inject;

public class WelcomeActivity extends AbstractActivity {

    @BindView(R.id.text_view_info)
    protected TextView textView;

    @Inject
    protected WelcomeDto welcomeDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.welcome_activity);
        super.getActivityComponent().inject(this);

        this.textView.setText(welcomeDto.getMessage());
    }

    @XmlOnClick
    public void onViewClick(View view) {
        super.finish();
    }
}
