package com.emoemoemo.guitartrainer.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import com.emoemoemo.guitartrainer.R;

public class DashboardFragment extends Fragment {

    final String notes[][] = {
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"},
            {"Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез"},
            {"Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез"},
            {"Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез"},
            {"Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез"},
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"}};

    final Random rnd = new Random();

    int GuessedFret = rnd.nextInt(12);
    int GuessedString = rnd.nextInt(6);

    TextView stringQuestion, noteQuestion;
    TextView answerText;

    private int pxFromDp(int dp) {
        return dp * (int)getContext().getResources().getDisplayMetrics().density;
    }

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        stringQuestion = root.findViewById(R.id.string_question);
        noteQuestion = root.findViewById(R.id.note_question);
        stringQuestion.setText(notes[GuessedString][0]);
        noteQuestion.setText(notes[GuessedString][GuessedFret]);
        answerText = root.findViewById(R.id.answer_text);

        LinearLayout neckView = root.findViewById(R.id.neck_view);

        LinearLayout NumLine = new LinearLayout(getActivity());
        NumLine.setOrientation(LinearLayout.HORIZONTAL);
        NumLine.setLayoutParams(new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 12; i++)
        {
            TextView TextNums = new TextView(getActivity());
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(pxFromDp(50), pxFromDp(40));
            lParams.setMargins(10, 0, 30, 0);
            TextNums.setLayoutParams(lParams);
            TextNums.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextNums.setText("" + i);
            TextNums.setTextSize(17);
            NumLine.addView(TextNums);
        }
        neckView.addView(NumLine);

        for (int string = 0; string < 6; string++)
        {
            LinearLayout CurrentLine = new LinearLayout(getActivity());
            CurrentLine.setOrientation(LinearLayout.HORIZONTAL);
            CurrentLine.setLayoutParams(new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            CurrentLine.setBackgroundResource(R.drawable.square);
            for (int fret = 0; fret < 12; fret++)
            {
                final int CurrentString = string, CurrentFret = fret;

                Button clickButton = new Button(getActivity());
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(pxFromDp(50), pxFromDp(50));
                lParams.setMargins(10, 10, 30, 10);
                clickButton.setBackgroundResource(R.drawable.circle);
                clickButton.setLayoutParams(lParams);


                clickButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CurrentFret == GuessedFret && notes[CurrentString][0].equals(notes[GuessedString][0]))
                        {
                            answerText.setText("Правильно");
                            answerText.setTextColor(getResources().getColor(R.color.colorRightAnswer));
                        }
                        else
                        {
                            answerText.setText("Неправильно");
                            answerText.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                        }

                        GuessedFret = rnd.nextInt(12);
                        GuessedString = rnd.nextInt(6);
                        stringQuestion.setText(notes[GuessedString][0]);
                        noteQuestion.setText(notes[GuessedString][GuessedFret]);
                        return;
                    }
                });
                CurrentLine.addView(clickButton);
            }
            neckView.addView(CurrentLine);
        }

        return root;
    }
}
