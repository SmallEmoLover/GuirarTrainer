package com.emoemoemo.guitartrainer.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.emoemoemo.guitartrainer.R;
import com.emoemoemo.guitartrainer.myUtil;



public class DashboardFragment extends Fragment {

    private int pxFromDp(int dp) {
        return dp * (int) getContext().getResources().getDisplayMetrics().density;
    }

    final String notes[][] = {
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"},
            {"Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез"},
            {"Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез"},
            {"Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез"},
            {"Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез", "Ми", "Фа", "Фа-диез", "Соль", "Соль-диез"},
            {"Ми", "Фа", "Фа-диез", "Соль", "Соль-диез", "Ля", "Ля-диез", "Си", "До", "До-диез", "Ре", "Ре-диез"}};

    Button[][] neck = new Button[6][12];

    final Random rnd = new Random();

    int GuessedFret = rnd.nextInt(12);
    int GuessedString = rnd.nextInt(6);

    TextView stringQuestion, noteQuestion;
    TextView answerText;



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

        myUtil.ButtonsInit(neck, getActivity(), pxFromDp(1), neckView);

        for (int string = 0; string < 6; string++)
        {
            for (int fret = 0; fret < 12; fret++)
            {
                final int CurrentFret = fret, CurrentString = string;

                neck[string][fret].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myUtil.ResetColors(neck);

                        if (CurrentFret == GuessedFret && notes[CurrentString][0].equals(notes[GuessedString][0]))
                            myUtil.RightAnswer(answerText, neck[CurrentString][CurrentFret],
                                    getResources().getColor(R.color.colorRightAnswer));
                        else
                            myUtil.WrongAnswer(answerText, neck[GuessedString][GuessedFret],
                                    notes[CurrentString][CurrentFret], neck[CurrentString][CurrentFret],
                                    getResources().getColor(R.color.colorWrongAnswer));

                        GuessedFret = rnd.nextInt(12);
                        GuessedString = rnd.nextInt(6);
                        stringQuestion.setText(notes[GuessedString][0]);
                        noteQuestion.setText(notes[GuessedString][GuessedFret]);
                        return;
                    }
                });
            }
        }

        return root;
    }
}
