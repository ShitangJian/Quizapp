package com.example.cris.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cris.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper3 extends SQLiteOpenHelper {
    //define static information for database
    private static final String DATABASE_NAME = "INFS1603Quiz3.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;



    public QuizDbHelper3(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // the create method for database
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //create the SQL language in String for new table
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        //execute the SQL language to create the table
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        //the method to insert information to table
        fillQuestionsTable();

    }


    // used to upgrade the table version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    //define the attributes and use addQuestion method to insert with those attributes to table in database
    private void fillQuestionsTable() {
        //create question object with those attribute and use addQuestion method to insert the object to table
        Question q1 = new Question("The different classes of relations created by the " +
                "technique for preventing modification anomalies are called:",
                "A. normal forms.", "B. referential integrity constraints.",
                "C. functional dependencies.","D. None of the above is correct.", 1);
        addQuestion(q1);
        Question q2 = new Question("A relation is in this form if it is in BCNF and has no" +
                " multivalued dependencies:",
                "A. second normal form.", "B. third normal form.",
                "C. fourth normal form.","D. domain/key normal form.", 3);
        addQuestion(q2);
        Question q3 = new Question("Row is synonymous with the term:", "A. record.",
                "B. relation", "C. column",
                "D. field", 1);
        addQuestion(q3);
        Question q4 = new Question("The primary key is selected from the:",
                "A. composite keys.", "B. determinants.",
                "C. candidate keys.","D. foreign keys.", 3);
        addQuestion(q4);
        Question q5 = new Question("A relation is considered a:",
                "A. Column.",
                "B. one-dimensional table.",
                "C. two-dimensional table.",
                "D. three-dimensional table.", 3);
        addQuestion(q5);
        Question q6 = new Question("A functional dependency is a relationship between or among:",
                "A. tables.",
                "B. rows.",
                "C. relations",
                "D. attributes", 4);
        addQuestion(q6);
        Question q7 = new Question("Which of the following is not a restriction for a table " +
                "to be a relation?",
                "A. The cells of the table must contain a single value.",
                "B. All of the entries in any column must be of the same kind.",
                "C. The columns must be ordered.","D. No two rows in a table may be" +
                " identical.", 3);
        addQuestion(q7);
        Question q8 = new Question("A key:",
                "A. must always be composed of two or more columns.",
                "B. can only be one column.", "C. identifies a row.",
                "D. identifies a column.", 3);
        addQuestion(q8);
        Question q9 = new Question("An attribute is a(n):",
                "A. column of a table.", "B. two dimensional table.",
                "C. row of a table.","D. key of a table.", 1);
        addQuestion(q9);
        Question q10 = new Question("A tuple is a(n):",
                "A. column of a table.", "B. two dimensional table.",
                "C. row of a table.","D. key of a table.;", 3);
        addQuestion(q10);
    }


    //the method used to insert question object to database with those attributes
    private void addQuestion(Question question){
        ContentValues values = new ContentValues();
        //put information to specific column by using contract class and contenValues method
        values.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        values.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        values.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        values.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        values.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        values.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        //insert the insert question object to database with those attributes
        db.insert(QuestionsTable.TABLE_NAME, null, values);
    }

    //the method to create an arraylist to read the information from database
    //reference from https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-5-quiz-activity
    public List<Question> getAllQuestions3() {
        List<Question> questionList3 = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);

        //check if the result is valid
        if (cursor.moveToFirst()) {
            do {Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList3.add(question);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return questionList3;
    }


}
