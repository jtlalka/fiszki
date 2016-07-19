package net.tlalka.android.fiszki;

import java.sql.SQLException;
import java.util.List;

import net.tlalka.android.fiszki.listeners.LessonListener;
import net.tlalka.android.fiszki.models.DatabaseManager;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entity.LessonEntity;
import net.tlalka.android.fiszki.models.entity.WordEntity;
import net.tlalka.android.fwork.FworkActivity;
import net.tlalka.android.fwork.FworkInit;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class LessonActivity extends FworkActivity {

	public static final String LESSON_NAME = "LessonElement.name";
	public static final String LESSON_DESC = "LessonElement.desc";

	private LessonDao lessonDao;
	private WordDao wordDao;
	
	private List<WordEntity> wordList;
	private WordEntity wordEntity;
	private LessonEntity lessonEntity;
	
	private TextView textViewTopic;
	private Button buttonWordShow;
	private Button buttonWordCheck;
	private Button buttonGood;
	private Button buttonBad;
	
	private String lessonName;
	private String lessonDesc;
	private int wordCount;
	private int wordNumber;
	private int wordGood;
	private int wordBad;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle, R.layout.lesson_view);

		this.initElements();
		this.initListeners();
		this.initBundle();
		this.initDataBase();
		
		this.runActivity();
	}

	private void initElements() {
		this.textViewTopic = (TextView) findViewById(R.id.textViewTopic);
		this.buttonWordShow = (Button) findViewById(R.id.buttonWordShow);
		this.buttonWordCheck = (Button) findViewById(R.id.buttonWordCheck);
		this.buttonGood = (Button) findViewById(R.id.buttonGood);
		this.buttonBad = (Button) findViewById(R.id.buttonBad);
	}

	private void initListeners() {
		this.buttonWordCheck.setOnClickListener(new LessonListener(this, LessonListener.ACTION_CHECK));
		this.buttonGood.setOnClickListener(new LessonListener(this, LessonListener.ACTION_GOOD));
		this.buttonBad.setOnClickListener(new LessonListener(this, LessonListener.ACTION_BAD));
	}

	private void initBundle() {
		Bundle argsBundle = super.getIntent().getExtras();

		if (FworkInit.Valid.isNotNull(argsBundle)) {
			this.lessonName = argsBundle.getString(LESSON_NAME);
			this.lessonDesc = argsBundle.getString(LESSON_DESC);

			this.textViewTopic.setText(lessonName + " - " + lessonDesc);
		}
	}
	
	private void initDataBase() {	
		try {
			this.lessonDao = DatabaseManager.getHelper(this).getLessonDao();
			this.wordDao = DatabaseManager.getHelper(this).getWordDao();
			
			this.lessonEntity = this.lessonDao.getLessonByLessonName(lessonName);
			this.wordList = this.wordDao.getWordsByLessnoName(lessonName);
			
			for(WordEntity wordEntity : wordList) {
				Log.d(wordEntity.getWordPL(), "" + wordEntity.getProgress());
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void runActivity() {
		this.lessonEntity.setProgress(this.lessonEntity.getProgress() + 1);
		
		this.wordCount = this.wordList.size();
		this.wordNumber = 0;
		this.wordGood = 0;
		this.wordBad = 0;
		
		this.generateView(this.wordNumber);
	}

	private void generateView(int number) {
		this.wordEntity = this.wordList.get(number);
		
		this.buttonWordShow.setText(this.wordEntity.getWordEN());
		this.buttonWordCheck.setText("");
	}

	public void showWord() {
		this.buttonWordCheck.setText(this.wordEntity.getWordPL());
	}

	public boolean nextWord() {
		this.wordNumber++;
		
		if (this.wordNumber < this.wordCount) {
			this.generateView(this.wordNumber);
			return true;
		}
		return false;
	}

	public boolean privWord() {
		this.wordNumber--;
		
		if (this.wordNumber >= 0) {
			this.generateView(this.wordNumber);
			return true;
		}
		return false;
	}

	public void progressUp() {
		this.wordEntity.setProgress(this.wordEntity.getProgress() + 1);
		this.wordDao.save(this.wordEntity);
		
		this.wordGood++;
	}

	public void progressDown() {
		this.wordEntity.setProgress(this.wordEntity.getProgress() - 1);
		this.wordDao.save(this.wordEntity);
		
		this.wordBad++;
	}

	public void showSummary() {
		Bundle bundleToSend = new Bundle();
		bundleToSend.putString(LessonSumActivity.LESSON_NAME, this.lessonName);
		bundleToSend.putString(LessonSumActivity.LESSON_DESC, this.lessonDesc);
		bundleToSend.putInt(LessonSumActivity.TOTAL_COUNT, this.wordNumber);
		bundleToSend.putInt(LessonSumActivity.TOTAL_GOOD, this.wordGood);
		bundleToSend.putInt(LessonSumActivity.TOTAL_BAD, this.wordBad);

		super.startActivity(LessonSumActivity.class, bundleToSend);
		super.finish();
	}
}
