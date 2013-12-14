package com.example.testify;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper1 extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuiz1";
	// tasks table name
	private static final String TABLE_QUEST = "quest1";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	public DbHelper1(Context context) {
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
		Question q1=new Question("Which header file should be included to use functions like malloc() and calloc()?","memory.h", "dos.h", "stdlib.h", "stdlib.h");
		this.addQuestion(q1);
		Question q2=new Question("If a function contains two return statements successively, the compiler will generate warnings?","Yes", "No", "Can't say", "Yes");
		this.addQuestion(q2);
		Question q3=new Question("Which of the following special symbol allowed in a variable name?","* (asterisk)", "- (hyphen)", "_ (underscore)", "_ (underscore)"); 
		this.addQuestion(q3);
		Question q4=new Question("Which interface provides the capability to store objects using a key-value pair?","Java.util.Map", "Java.util.List", "Java.util.Collection", "Java.util.Map");
		this.addQuestion(q4);
		Question q5=new Question("Which is valid declaration of a float in Java?","float f = 1F", "float f = 1", "float f = 1.0d", "float f = 1F");
		this.addQuestion(q5);
		Question q6=new Question("What will be the output of the program? System.out.println(Math.sqrt(-4D));","-2", "NaN", "Compile Error", "Nan");
		this.addQuestion(q6);
		Question q7=new Question("How many instances of an abstract class can be created?","1", "0", "10", "0");
		this.addQuestion(q7);
		Question q8=new Question("Which of the following cannot be friend?","Object", "Class", "Function", "Object");
		this.addQuestion(q8);
		Question q9=new Question("Which of the following approach is adapted by C++?","Top-down", "Bottom-Up", "Right-left", "Bottom-Up");
		this.addQuestion(q9);
		Question q10=new Question("Which of the following is not a type of inheritance?","Distributive", "Multiple", "Hierarchial", "Distributive");
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
