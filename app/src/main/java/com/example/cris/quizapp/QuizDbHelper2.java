package com.example.cris.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cris.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper2 extends SQLiteOpenHelper {
    //define static information for database
    private static final String DATABASE_NAME = "INFS1603Quiz2.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;



    public QuizDbHelper2(Context context) {
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
        Question q1 = new Question("Which of the following indicates the maximum number of " +
                "entities that can be involved in a relationship?",
                "A. Minimum cardinality", "B. Maximum cardinality", "C. ERD",
                "D. Greater Entity Count (GEC)", 2);
        addQuestion(q1);
        Question q2 = new Question("In a one-to-many relationship, the entity that is on" +
                " the one side of the relationship is called a(n) ________ entity.",
                "A. parent", "B. child",
                "C. instance","D. subtype", 1);
        addQuestion(q2);
        Question q3 = new Question("Which type of entity represents an actual occurrence" +
                " of an associated generalized entity?", "A. Supertype entity",
                "B. Subtype entity", "C. Archetype entity",
                "D. Instance entity", 1);
        addQuestion(q3);
        Question q4 = new Question("In which of the following is a single-entity instance " +
                "of one type related to many entity instances of another type?",
                "A. One-to-One Relationship", "B. One-to-Many Relationship",
                "C. Many-to-Many Relationship","D. Composite Relationship", 2);
        addQuestion(q4);
        Question q5 = new Question("Which of the following refers to something that can be " +
                "identified in the users' work environment, something that the users want to track?",
                "A. Entity",
                "B. Attribute",
                "C. Identifier",
                "D. Relationship", 1);
        addQuestion(q5);
        Question q6 = new Question("An attribute that names or identifies entity instances is a(n):",
                "A. entity.",
                "B. attribute.",
                "C. identifier.",
                "D. relationship.", 3);
        addQuestion(q6);
        Question q7 = new Question("Entities of a given type are grouped into a(n):",
                "A. database.", "B. entity class.",
                "C. attribute.","D. ERD", 2);
        addQuestion(q7);
        Question q8 = new Question("Properties that describe the characteristics of entities are called:",
                "A. entities.", "B. attributes.",
                "C. identifiers","D. relationships.", 2);
        addQuestion(q8);
        Question q9 = new Question(" Which of the following is NOT a basic element of all " +
                "versions of the E-R model?",
                "A. Entities", "B. Attributes", "C. Relationships",
                "D. Primary keys", 4);
        addQuestion(q9);
        Question q10 = new Question("Entities can be associated with one another in which " +
                "of the following?",
                "A. Entities", "B. Attributes",
                "C. Identifiers","D. Relationships", 4);
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
    public List<Question> getAllQuestions2() {
        List<Question> questionList2 = new ArrayList<>();
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
                questionList2.add(question);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return questionList2;
    }


}
