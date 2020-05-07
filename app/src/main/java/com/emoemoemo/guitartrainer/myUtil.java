package com.emoemoemo.guitartrainer;

import android.app.Activity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class myUtil
{

    final static Random rnd = new Random();

    public static final String notes[][] = {
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"},
            {"Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез"},
            {"Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез"},
            {"Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез"},
            {"Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез"},
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"}};

    private static int size = 0;

    public static int GuessedString, GuessedFret;

    public static void NextQuestion(TextView Fret, TextView String)
    {
        GuessedFret = rnd.nextInt(12);
        Fret.setText(notes[GuessedString][GuessedFret]);

        GuessedString = rnd.nextInt(6);
        String.setText(notes[GuessedString][0]);
    }

    public static void SetModeOne(final Button[][] GuitarNeck, final TextView answerText, final TextView stringQuestion,
                                  final  TextView noteQuestion, final Context context)
    {
        for (int string = 0; string < 6; string++)
        {
            for (int fret = 0; fret < 12; fret++)
            {
                final int CurrentFret = fret, CurrentString = string;

                GuitarNeck[string][fret].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myUtil.ResetButtons(GuitarNeck);

                        if (CurrentFret == GuessedFret && myUtil.notes[CurrentString][0].equals( myUtil.notes[GuessedString][0]))
                            myUtil.RightAnswer(answerText, GuitarNeck[CurrentString][CurrentFret],
                                    context);
                        else
                            myUtil.WrongAnswer(answerText, GuitarNeck[GuessedString][GuessedFret],
                                    myUtil.notes[CurrentString][CurrentFret], GuitarNeck[CurrentString][CurrentFret],
                                    myUtil.notes[GuessedString][GuessedFret],
                                    context);

                        NextQuestion(noteQuestion, stringQuestion);
                    }
                });
            }
        }
    }

    public static void ButtonsInit(final Button[][] neck, final Activity activity, LinearLayout neckView)
    {
        LinearLayout NumLine = new LinearLayout(activity);
        NumLine.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams numsLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        numsLayout.weight = 7;
        NumLine.setLayoutParams(numsLayout);
        for (int i = 0; i < 12; i++)
        {
            TextView TextNums = new TextView(activity);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            lParams.weight = 1;
            TextNums.setLayoutParams(lParams);
            TextNums.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextNums.setText("" + i);
            NumLine.addView(TextNums);
        }
        neckView.addView(NumLine);

        LinearLayout.LayoutParams lineLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        lineLayout.weight = 10;

        for (int string = 0; string < 6; string++)
        {
            final LinearLayout CurrentLine = new LinearLayout(activity);
            CurrentLine.setOrientation(LinearLayout.HORIZONTAL);
            CurrentLine.setLayoutParams(lineLayout);
            CurrentLine.setBackgroundResource(R.drawable.line_horizontal);

            final int CurrentString = string;
            CurrentLine.post(new Runnable() {
                @Override
                public void run() {
                    if (size == 0)
                        size = CurrentLine.getHeight();

                    for (int fret = 0; fret < 12; fret++)
                    {

                        final int CurrentFret = fret;

                        LinearLayout.LayoutParams buttonLayout = new LinearLayout.LayoutParams(size * 80 / 100, size * 80 / 100);

                        neck[CurrentString][CurrentFret].setBackgroundResource(R.drawable.button_neutral);
                        neck[CurrentString][CurrentFret].setLayoutParams(buttonLayout);
                        neck[CurrentString][CurrentFret].setPadding(0, 0, 0, 0);

                        buttonLayout.gravity = Gravity.CENTER;
                        buttonLayout.setMargins
                                (size * 20 / 100, size * 20 / 100, size * 20 / 100, size * 20 / 100);

                        neck[CurrentString][CurrentFret].setLayoutParams(buttonLayout);
                        CurrentLine.addView(neck[CurrentString][CurrentFret]);


                        neck[CurrentString][CurrentFret].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        neck[CurrentString][CurrentFret].setGravity(Gravity.FILL);

                        LinearLayout VerticalLine = new LinearLayout(activity);
                        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                                size / 20 + 1, ViewGroup.LayoutParams.MATCH_PARENT);
                        VerticalLine.setLayoutParams(lParams);
                        VerticalLine.setBackgroundResource(R.drawable.line_vertical);
                        CurrentLine.addView(VerticalLine);
                    }
                }
            });

            neckView.addView(CurrentLine);
        }
    }

    public static String NoteTranslation(String note)
    {
        switch (note)
        {
            case "До":
                return "C";
            case "До-диез":
                return "C#";
            case "Ре":
                return "D";
            case "Ре-диез":
                return "D#";
            case "Ми":
                return "E";
            case "Фа":
                return "F";
            case "Фа-диез":
                return "F#";
            case "Соль":
                return "G";
            case "Соль-диез":
                return "G#";
            case "Ля":
                return "A";
            case "Ля-диез":
                return "A#";
            case "Си":
                return "B";
        }
        return "";
    }

    public static void ResetButtons(Button[][] neck)
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 12; j++) {
                neck[i][j].setBackgroundResource(R.drawable.button_neutral);
                neck[i][j].setText("");
            }
        }
    }

    public static void RightAnswer(TextView textView, Button rightButton, Context context)
    {
        textView.setText("Правильно");
        textView.setTextColor(context.getResources().getColor(R.color.colorRightAnswer));
        rightButton.setBackgroundResource(R.drawable.button_right);
    }

    public static void WrongAnswer(TextView textView, Button rightButton, String wrongNote,
                                   Button wrongButton, String rightNote, Context context)
    {
        textView.setText("Неправильно\nВы выбрали " + wrongNote);
        textView.setTextColor(context.getResources().getColor(R.color.colorWrongAnswer));
        rightButton.setBackgroundResource(R.drawable.button_right);
        rightButton.setText(NoteTranslation(rightNote));

        wrongButton.setBackgroundResource(R.drawable.button_wrong);
        wrongButton.setText(NoteTranslation(wrongNote));
    }
}