/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */


package com.mifos.mifosxdroid.online;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mifos.mifosxdroid.R;
import com.mifos.objects.survey.ScorecardValues;
import com.mifos.objects.survey.Survey;
import com.mifos.utils.MyPreference;
import com.mifos.services.data.ScorecardPayload;
import com.mifos.utils.MifosApplication;
import com.mifos.objects.survey.Scorecard;
import android.content.SharedPreferences;
import android.widget.Toast;
import java.util.Date;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Nasim Banu on 28,January,2016.
 */
public class SurveyLastFragment extends Fragment {
    public static final String QUESTION = "question";
    public static final String PREFS_NAME = "MY_PREFS";
    SharedPreferences sharedPreferences;
    public static final String SCOREVALUES = "Score_Values";
    public static final String ANSWERS = "answers";
    public static final String ID = "id";
    public Context context;
    private Button btnNext;

    List<ScorecardValues> scorecardValues;
    MyPreference myPreference;
    Activity activity;

    private TextView tvQuestion;
    public static int qid;
    public static int rid;
    public static int rvalue;



    public static final SurveyLastFragment newInstance(int id, String question) {
        SurveyLastFragment fragment = new SurveyLastFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ID, id);
        bundle.putString(QUESTION, question);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = getActivity();
      //  ((SurveyQuestion)context).fragmentCommunicator = this;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_survey_last, container, false);
        tvQuestion = (TextView) view.findViewById(R.id.survey_question_textView);
        setQuestion(getArguments().getString(QUESTION));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPreference = new MyPreference();
        scorecardValues = myPreference.getScorecards(activity);

    }

    public void setQuestion(String question){
        tvQuestion.setText(question);
    }




    @Override
    public void onStop() {
        scorecardValues.clear();
        myPreference = new MyPreference();
        myPreference.resetScorecard(activity);
        super.onStop();

    }

}

