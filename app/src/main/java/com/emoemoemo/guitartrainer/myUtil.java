package com.emoemoemo.guitartrainer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myUtil
{
    public static void ButtonsInit(Button[][] neck, Activity activity, int dpCoef, LinearLayout neckView)
    {
        LinearLayout NumLine = new LinearLayout(activity);
        NumLine.setOrientation(LinearLayout.HORIZONTAL);
        NumLine.setLayoutParams(new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 12; i++)
        {
            TextView TextNums = new TextView(activity);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(dpCoef * 50, dpCoef * 40);
            lParams.setMargins(10, 0, 30, 0);
            TextNums.setLayoutParams(lParams);
            TextNums.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextNums.setText("" + i);
            TextNums.setTextSize(17);
            NumLine.addView(TextNums);
        }
        neckView.addView(NumLine);

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(dpCoef * 50, dpCoef *50);
        lParams.setMargins(10, 10, 30, 10);

        for (int string = 0; string < 6; string++)
        {
            LinearLayout CurrentLine = new LinearLayout(activity);
            CurrentLine.setOrientation(LinearLayout.HORIZONTAL);
            CurrentLine.setLayoutParams(new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            CurrentLine.setBackgroundResource(R.drawable.square);

            for (int fret = 0; fret < 12; fret++)
            {
                final int CurrentString = string, CurrentFret = fret;

                neck[string][fret] = new Button(activity);
                neck[string][fret].setBackgroundResource(R.drawable.circle);
                neck[string][fret].setLayoutParams(lParams);
                CurrentLine.addView(neck[string][fret]);
            }
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

    public static void ResetColors(Button[][] neck)
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 12; j++) {
                neck[i][j].setBackgroundResource(R.drawable.circle);
                neck[i][j].setText("");
            }
        }
    }

    public static void RightAnswer(TextView textView, Button rightButton, int color)
    {
        textView.setText("Правильно");
        textView.setTextColor(color);
        rightButton.setBackgroundResource(R.drawable.circle_right);
    }

    public static void WrongAnswer(TextView textView, Button rightButton, String note, Button wrongButton, int color)
    {
        textView.setText("Неправильно\nВы выбрали " + note);
        textView.setTextColor(color);
        rightButton.setBackgroundResource(R.drawable.circle_right);
        wrongButton.setBackgroundResource(R.drawable.circle_wrong);
        wrongButton.setText(NoteTranslation(note));
    }
}