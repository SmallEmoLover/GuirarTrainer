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

    Button[][] GuitarNeck = new Button[6][12];





    TextView stringQuestion, noteQuestion;
    TextView answerText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        stringQuestion = root.findViewById(R.id.string_question);
        noteQuestion = root.findViewById(R.id.note_question);
        answerText = root.findViewById(R.id.answer_text);

        LinearLayout neckView = root.findViewById(R.id.neck_view);

        myUtil.NextQuestion(noteQuestion, stringQuestion);

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 12; j++)
                GuitarNeck[i][j] = new Button(getActivity());
        }

        myUtil.ButtonsInit(GuitarNeck, getActivity(), neckView);

        myUtil.SetModeOne(GuitarNeck, answerText, stringQuestion, noteQuestion, getContext());

        return root;
    }
}
