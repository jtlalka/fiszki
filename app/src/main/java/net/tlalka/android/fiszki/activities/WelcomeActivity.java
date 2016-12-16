package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.dto.WelcomeDto;
import org.parceler.Parcels;

import static net.tlalka.android.fiszki.utils.ValidUtils.isNotNull;

public class WelcomeActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.welcome_activity);

        Bundle sendData = getIntent().getExtras();
        if (isNotNull(sendData)) {
            WelcomeDto welcomeDto = Parcels.unwrap(sendData.getParcelable(WelcomeDto.class.getName()));

            TextView textView = (TextView) findViewById(R.id.text_view_info);
            textView.setText(welcomeDto.getMessage());
        }
    }

    @XmlOnClick
    public void onViewClick(View view) {
        super.finish();
    }
}
