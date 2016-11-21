package net.tlalka.android.fiszki.listeners;

import net.tlalka.android.fiszki.LessonActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LessonListener implements OnClickListener {

	public final static int ACTION_GOOD = 1;
	public final static int ACTION_BAD = 2;
	public final static int ACTION_CHECK = 3;

	private LessonActivity context;
	private int action;

	public LessonListener(LessonActivity context, int action) {
		this.context = context;
		this.action = action;
	}

	@Override
	public void onClick(View view) {
		switch (action) {
			case ACTION_GOOD:
				this.actionGood();
				break;
	
			case ACTION_BAD:
				this.actionBad();
				break;

			case ACTION_CHECK:
				this.actionCheck();
				break;

			default:
				Toast.makeText(this.context, "Not implement", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private void actionGood() {
		this.context.progressUp();
		boolean isNextWord = this.context.nextWord();

		if (isNextWord == false) {
			this.context.showSummary();
		}
	}

	private void actionBad() {
		this.context.progressDown();
		boolean isNextWord = this.context.nextWord();

		if (isNextWord == false) {
			this.context.showSummary();
		}
	}

	private void actionCheck() {
		this.context.showWord();
	}
}
