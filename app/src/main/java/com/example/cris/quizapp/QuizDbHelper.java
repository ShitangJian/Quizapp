package com.example.cris.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cris.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    //define static information for database
    private static final String DATABASE_NAME = "INFS1603Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;



    public QuizDbHelper(Context context) {
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
        Question q1 = new Question("You can add a row using SQL in a database with which of the following?",
                "A. ADD", "B. CREATE", "C. INSERT","D. MAKE", 3);
        addQuestion(q1);
        Question q2 = new Question("The command to remove rows from a table 'CUSTOMER' is:",
                "A. REMOVE FROM CUSTOMER ...", "B. DROP FROM CUSTOMER ...",
                "C. DELETE FROM CUSTOMER WHERE ...","D. UPDATE FROM CUSTOMER ...", 3);
        addQuestion(q2);
        Question q3 = new Question("The SQL WHERE clause:", "A. limits the column data that are returned.",
                "B. limits the row data are returned.", "C. Both A and B are correct.",
                "D. Neither A nor B are correct.", 2);
        addQuestion(q3);
        Question q4 = new Question("Which of the following is the original purpose of SQL?",
                "A. To specify the syntax and semantics of SQL data definition language",
                "B. To specify the syntax and semantics of SQL manipulation language",
                "C. To define the data structures","D. All of the above.", 4);
        addQuestion(q4);
        Question q5 = new Question("The wildcard in a WHERE clause is useful when?",
                "A. An exact match is necessary in a SELECT statement.",
                "B. An exact match is not possible in a SELECT statement.",
                "C. An exact match is necessary in a CREATE statement.",
                "D. An exact match is not possible in a CREATE statement.", 2);
        addQuestion(q5);
        Question q6 = new Question("A view is which of the following?",
                "A. A virtual table that can be accessed via SQL commands",
                "B. A virtual table that cannot be accessed via SQL commands",
                "C. A base table that can be accessed via SQL commands",
                "D. A base table that cannot be accessed via SQL commands", 1);
        addQuestion(q6);
        Question q7 = new Question("The command to eliminate a table from a database is:",
                "A. REMOVE TABLE CUSTOMER;", "B. DROP TABLE CUSTOMER;",
                "C. DELETE TABLE CUSTOMER;","D. UPDATE TABLE CUSTOMER;", 2);
        addQuestion(q7);
        Question q8 = new Question("ON UPDATE CASCADE ensures which of the following?",
                "A. Normalization", "B. Data Integrity",
                "C. Materialized Views","D. All of the above.", 2);
        addQuestion(q8);
        Question q9 = new Question("SQL data definition commands make up a(n) ________ .",
                "A. DDL", "B. DML", "C. HTML","D. XML", 1);
        addQuestion(q9);
        Question q10 = new Question("Which of the following is valid SQL for an Index?",
                "A. CREATE INDEX ID;", "B. CHANGE INDEX ID;",
                "C. ADD INDEX ID;","D. REMOVE INDEX ID;", 1);
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
        public List<Question> getAllQuestions() {
            List<Question> questionList = new ArrayList<>();
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
                    questionList.add(question);
                } while (cursor.moveToNext());

            }
            cursor.close();
            return questionList;
        }


}
