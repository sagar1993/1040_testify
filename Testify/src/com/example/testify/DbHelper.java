package com.example.testify;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuiz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
		db.execSQL(sql);		
		addQuestions();
		//db.close();
	}
	private void addQuestions()
	{
		Question q1=new Question("Two boys begin together to write out a booklet containing 535 lines. The first boy starts with the first line, writing at the rate of 100 lines an hour; and the second starts with the last line then writes line 534 and so on, backward proceeding at the rate of 50 lines an hour. At what line will they meet?","356", "277", "357", "357");
		this.addQuestion(q1);
		Question q2=new Question("A shopkeeper charges 44% more from his customer. A customer paid Rs. 648 for a wallet. What is the cost price of wallet?", "450", "575", "435", "450");
		this.addQuestion(q2);
		Question q3=new Question("In a mixture, R is 2 parts and S is 1 part. In order to make S to 25% of the mixture, how much of R is to be added?","One part of R", "Three part of R","Two part of R","One part of R");
		this.addQuestion(q3);
		Question q4=new Question("I drove 60 km at 30 kmph and then an additional 60 km at 50 kmph. Compute my average speed over my 120 km.",	"40", "37 1/2", "25 1/2","37 1/2");
		this.addQuestion(q4);
		Question q5=new Question("The average of 11 numbers is 50. If the average of first 6 numbers is 49 and that of last 6 is 52.Find the 6th number","49","52","56","56");
		this.addQuestion(q5);
		
		Question q6=new Question("Anoop and Suyesh invested in the ratio of 4 : 7 respectively. If both invested Rs. 49500 together, how much amount did Anoop invest?","31500", "18000", "18500", "18000"); 
		this.addQuestion(q6);
		Question q7=new Question("7, 10, 8, 11, 9, 12, ... What number should come next?","7", "10", "12", "10"); 
		this.addQuestion(q7);
		Question q8=new Question("AUGUST (synonym)","Common", "Ridiculous", "Dignified", "Dignified");
		this.addQuestion(q8);
		Question q9=new Question("Life is to death as pleasure is to ......","poverty", "pain", "suffering", "pain");
		this.addQuestion(q9);
		Question q10=new Question("EXPAND (opposite)","convert", "congest", "condense", "condense");
		this.addQuestion(q10);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
