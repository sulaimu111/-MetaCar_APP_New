package com.example.navdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

//    Button button2;
    TextView textView, textView1, textView2, bot_num;
    EditText speechText, editText2;
    ImageButton speechButton;
    String userText, userIpText, targetText, respondText,rasa_response, phone_signal = "", car_signal, SpeechWord, response_ans, response_bad_ans, res;
    RequestQueue queue;
    Button button1, button2;
    WebView webView;
    String bot_number = "1";
    String nodeJs_Ip = "http://140.125.32.138:3000";
    String carBot_Ip = "http://140.125.32.128:5000/carbot";
//    String carBot_Ip = "http://140.125.32.145:5000/carbot";
    TextToSpeech textToSpeech;
    String nowDate;
    String student_school, student_grade, student_class, student_name;
    String total_speech_word = "";
    String score, cheat_word;
    int int_score = 0;
    int total_score = 0;
    int count_time = 0;
    int avg_score = 0;
    Integer count = 0;

    SpeechRecognizer speechRecognizer;

    private static final int RECOGNIZER_RESULT = 1;
    //    private static final String TAG = "MyAppTag";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String AudioSavaPath = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(requestCode == RECOGNIZER_RESULT && resultCode == Activity.RESULT_OK){
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            SpeechWord = matches.get(0).toString();
////            speechText.setText(SpeechWord);
////            textView2.setText(SpeechWord);
//            editText2.setText(SpeechWord);
//
//
//            Log.d("Debug", "student_school = " + student_school);
//            try{
//                mediaRecorder = new MediaRecorder();
//                mediaRecorder.stop();
//                mediaRecorder.release();
//            }catch (Exception e){
//                System.out.println(e);
//            }
//
////                Toast.makeText(MainActivity.this, "Recording stopped", Toast.LENGTH_SHORT).show();
//
////            File file = new File(Environment.getExternalStorageDirectory() + "/Miaudio.mp3");
////            byte[] bytes = new byte[0];
////            bytes = FileUtils.readFileToByteArray(file);
//
//            // Request a string response from the provided URL.
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, carBot_Ip,
//                    null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            // Display the first 500 characters of the response string.
//                            System.out.println(response);
////                                Log.d("brad", response);
//                            res = response.toString();
//                            try {
//                                JSONObject resObject = new JSONObject(res);
////                                respondText = resObject.getString("respond");
//                                rasa_response = resObject.getString("rasa_response");
//                                phone_signal = resObject.getString("phone_signal");
//                                car_signal = resObject.getString("car_signal");
//                                score = resObject.getString("score");
//                                int_score = Integer.parseInt(score);
//                                cheat_word = resObject.getString("rasa_response");
////                                targetText = resObject.getString("target");
////                                textView2 = resObject.getString("respond");
////                                Log.d("Debug", res);
//                                System.out.println("res = " + res);
//                                userIpText = resObject.getString("user_ip");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
////                            Toast.makeText(MainActivity.this, targetText, Toast.LENGTH_SHORT).show();
////                            Toast.makeText(MainActivity.this, userIpText, Toast.LENGTH_SHORT).show();
////                            textView.setText(targetText);
////                            textView1.setText(respondText);
////                            int speech = textToSpeech.speak(respondText, textToSpeech.QUEUE_FLUSH, null);
//                            if(rasa_response.equals("I'm sorry, I didn't quite understand that. Could you repeat that?")){
//                                response_bad_ans = rasa_response;
//                                Toast.makeText(getActivity(), response_bad_ans.toString(), Toast.LENGTH_SHORT).show();
//                                int speech = textToSpeech.speak(response_bad_ans, textToSpeech.QUEUE_FLUSH, null);
//                            }
//                            else{
//                                response_ans = rasa_response;
//                                textView1.setText(rasa_response);
//                                int speech = textToSpeech.speak(rasa_response, textToSpeech.QUEUE_FLUSH, null);
//
//                                total_speech_word = total_speech_word + "." + SpeechWord;
//                                System.out.println("total_speech_word = " + total_speech_word);
//                                total_score = total_score + int_score;
//                                System.out.println("total_score = " + total_score);
//                                count_time = count_time + 1;
//                                System.out.println("count_time = " + count_time);
//                            }
//
//                            System.out.println(rasa_response);
//
//                            if(phone_signal.equals("111") || phone_signal.equals("222") || phone_signal.equals("333")){
//                                System.out.println("finish");
//                                avg_score = total_score / count_time;
//                                System.out.println("avg_score = " + avg_score);
//
//                                //POST_TO_Carbot_result-------------------------------------------------------------
//
//                                JsonObjectRequest stringRequest_Carbot_result = new JsonObjectRequest(Request.Method.POST, carBot_Ip,null,
//                                        new Response.Listener<JSONObject>() {
//                                            @Override
//                                            public void onResponse(JSONObject response) {
//                                                System.out.println(response);
//                                            }
//                                        }, new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        System.out.println(error);
//                                    }
//                                }){
//                                    @Override
//                                    public byte[] getBody() {
//                                        JSONObject jsonBody5 = new JSONObject();
//                                        try {
////                                        jsonBody3.put("target", targetText);
////                                            jsonBody5.put("SpeechWord", SpeechWord);
//                                            jsonBody5.put("avg_score", avg_score);
//                                            jsonBody5.put("total_speech_word", total_speech_word);
//                                            jsonBody5.put("datetime", nowDate);
//                                            jsonBody5.put("text", SpeechWord);
//                                            jsonBody5.put("user","bot01");
//                                            jsonBody5.put("school", student_school);
//                                            jsonBody5.put("grade", student_grade);
//                                            jsonBody5.put("class", student_class);
//                                            jsonBody5.put("name", student_name);
//                                            jsonBody5.put("cheat_word", cheat_word);
////                                        jsonBody3.put("user", "bot01");
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        String requestBody3 = jsonBody5.toString();
//                                        return  requestBody3.getBytes(StandardCharsets.UTF_8);
//                                    }
//                                };
//
//                                queue.add(stringRequest_Carbot_result);
//
//                                //POST_TO_Carbot_result-------------------------------------------------------------
//                            }
//
//
//                            //POST_TO_NODEJS--------------------------------------------------------------------
//
//                            StringRequest stringRequest_nodejs = new StringRequest(Request.Method.POST, nodeJs_Ip + "/posttest" + bot_number,
//                                    new Response.Listener<String>() {
//                                        @Override
//                                        public void onResponse(String response) {
//                                            System.out.println(response);
//                                        }
//                                    }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    System.out.println(error);
////                                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }){
//                                @Override
//                                public byte[] getBody() throws AuthFailureError {
//                                    JSONObject jsonBody2 = new JSONObject();
//                                    try {
//                                        jsonBody2.put("target", phone_signal);
////                                        jsonBody2.put("user_ip", userIpText);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    String requestBody2 = jsonBody2.toString();
//                                    return  requestBody2.getBytes(StandardCharsets.UTF_8);
//                                }
//                            };
//                            queue.add(stringRequest_nodejs);
//
//                            //POST_TO_NODEJS--------------------------------------------------------------------
//
//                            //POST_TO_Jetson_Xavier-------------------------------------------------------------
//
//                            JsonObjectRequest stringRequest_jsonXavier = new JsonObjectRequest(Request.Method.POST, userIpText,null,
//                                    new Response.Listener<JSONObject>() {
//                                        @Override
//                                        public void onResponse(JSONObject response) {
//                                            car_signal = "";
//                                            System.out.println(response);
//                                        }
//                                    }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    System.out.println(error);
//                                }
//                            }){
//                                @Override
//                                public byte[] getBody() {
//                                    JSONObject jsonBody3 = new JSONObject();
//                                    try {
////                                        jsonBody3.put("target", targetText);
//                                        jsonBody3.put("target", car_signal);
////                                        jsonBody3.put("user", "bot01");
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    String requestBody2 = jsonBody3.toString();
//                                    return  requestBody2.getBytes(StandardCharsets.UTF_8);
//                                }
//                            };
//
//                            queue.add(stringRequest_jsonXavier);
//
//                            //POST_TO_Jetson_Xavier-------------------------------------------------------------
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    System.out.println(error);
////                    textView.setText("That didn't work!");
//                }
//            })
//            {
//                @Override
//                public byte[] getBody() {
//                    JSONObject jsonBody = new JSONObject();
//                    try {
////                        jsonBody.put("avg_score", 50);
////                        jsonBody.put("cheat_word", cheat_word);
//                        jsonBody.put("text", SpeechWord);
//                        jsonBody.put("datetime", nowDate);
//                        jsonBody.put("user","bot01");
//                        jsonBody.put("school", student_school);
//                        jsonBody.put("grade", student_grade);
//                        jsonBody.put("class", student_class);
//                        jsonBody.put("name", student_name);
//                        jsonBody.put("cheat_word", cheat_word);
//                        System.out.println("cheat_word = " + cheat_word);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    String requestBody = jsonBody.toString();
//                    return requestBody.getBytes(StandardCharsets.UTF_8);
//                }
//            };
//            // Add the request to the RequestQueue.
//            queue.add(jsonObjectRequest);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        button2 = getView().findViewById(R.id.button2);
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavController controller2 = Navigation.findNavController(view);
//                controller2.navigate(R.id.action_detailFragment2_to_homeFragment2);
//            }
//        });
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView1 = getView().findViewById(R.id.textView4);
        textView2 = getView().findViewById(R.id.textView5);
        editText2 = getView().findViewById(R.id.editText2);
        bot_num = getView().findViewById(R.id.bot_num);
//        speechText = findViewById(R.id.editText);
        speechButton = getView().findViewById(R.id.imageButton);
        button1 = getView().findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        webView = getView().findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(nodeJs_Ip + "/Metaverse_RoboMaster" + bot_number);
//        String spinner1 = getArguments().getString("spinner1");
        student_school = getArguments().getString("school");
        student_grade = getArguments().getString("grade");
        student_class = getArguments().getString("class");
        student_name = getArguments().getString("name");

        bot_num.setText("bot" + bot_number);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());

        final Intent speechRecognizerInetent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count == 0){
                    speechButton.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_mic_24));

                    //startlistening
                    speechRecognizer.startListening(speechRecognizerInetent);
                    count = 1;
                }
                else{
                    speechButton.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_mic_off_24));

                    //stoplistening
                    speechRecognizer.stopListening();
                    count = 0;
                }
            }
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                editText2.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        textToSpeech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int language = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        int speech = textToSpeech.speak("Where do you want to go?", textToSpeech.QUEUE_FLUSH, null);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Debug", nowDate);
                nowDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//                System.out.println(nowDate);
                Log.d("Debug", nowDate);
                SpeechWord = editText2.getText().toString();
                // Request a string response from the provided URL.
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, carBot_Ip,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Display the first 500 characters of the response string.
                                System.out.println(response);
//                                Log.d("brad", response);
                                res = response.toString();
                                try {
                                    JSONObject resObject = new JSONObject(res);
//                                respondText = resObject.getString("respond");
                                    rasa_response = resObject.getString("rasa_response");
                                    phone_signal = resObject.getString("phone_signal");
                                    car_signal = resObject.getString("car_signal");
                                    score = resObject.getString("score");
                                    int_score = Integer.parseInt(score);
                                    cheat_word = resObject.getString("rasa_response");
//                                targetText = resObject.getString("target");
//                                textView2 = resObject.getString("respond");
//                                Log.d("Debug", res);
                                    System.out.println("res = " + res);
                                    userIpText = resObject.getString("user_ip");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                            Toast.makeText(MainActivity.this, targetText, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, userIpText, Toast.LENGTH_SHORT).show();
//                            textView.setText(targetText);
//                            textView1.setText(respondText);
//                            int speech = textToSpeech.speak(respondText, textToSpeech.QUEUE_FLUSH, null);
                                if(rasa_response.equals("I'm sorry, I didn't quite understand that. Could you repeat that?")){
                                    response_bad_ans = rasa_response;
                                    Toast.makeText(getActivity(), response_bad_ans.toString(), Toast.LENGTH_SHORT).show();
                                    int speech = textToSpeech.speak(response_bad_ans, textToSpeech.QUEUE_FLUSH, null);
                                }
                                else{
                                    response_ans = rasa_response;
                                    textView1.setText(rasa_response);
                                    int speech = textToSpeech.speak(rasa_response, textToSpeech.QUEUE_FLUSH, null);

                                    total_speech_word = total_speech_word + SpeechWord + "." ;
                                    System.out.println("total_speech_word = " + total_speech_word);
                                    total_score = total_score + int_score;
                                    System.out.println("total_score = " + total_score);
                                    count_time = count_time + 1;
                                    System.out.println("count_time = " + count_time);
                                }

                                System.out.println(rasa_response);

                                if(phone_signal.equals("111") || phone_signal.equals("222") || phone_signal.equals("333")){
                                    System.out.println("finish");
                                    avg_score = total_score / count_time;
                                    System.out.println("avg_score = " + avg_score);

                                    //POST_TO_Carbot_result-------------------------------------------------------------

                                    JsonObjectRequest stringRequest_Carbot_result = new JsonObjectRequest(Request.Method.POST, carBot_Ip,null,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    System.out.println(response);
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            System.out.println(error);
                                        }
                                    }){
                                        @Override
                                        public byte[] getBody() {
                                            JSONObject jsonBody5 = new JSONObject();
                                            try {
//                                        jsonBody3.put("target", targetText);
//                                            jsonBody5.put("SpeechWord", SpeechWord);
                                                jsonBody5.put("avg_score", avg_score);
                                                jsonBody5.put("total_speech_word", total_speech_word);
                                                jsonBody5.put("datetime", nowDate);
                                                jsonBody5.put("text", SpeechWord);
                                                jsonBody5.put("user","bot01");
                                                jsonBody5.put("school", student_school);
                                                jsonBody5.put("grade", student_grade);
                                                jsonBody5.put("class", student_class);
                                                jsonBody5.put("name", student_name);
//                                        jsonBody3.put("user", "bot01");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            String requestBody3 = jsonBody5.toString();
                                            return  requestBody3.getBytes(StandardCharsets.UTF_8);
                                        }
                                    };

                                    queue.add(stringRequest_Carbot_result);

                                    //POST_TO_Carbot_result-------------------------------------------------------------
                                }


                                //POST_TO_NODEJS--------------------------------------------------------------------

                                StringRequest stringRequest_nodejs = new StringRequest(Request.Method.POST, nodeJs_Ip + "/posttest" + bot_number,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                System.out.println(response);
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        System.out.println(error);
//                                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }){
                                    @Override
                                    public byte[] getBody() throws AuthFailureError {
                                        JSONObject jsonBody2 = new JSONObject();
                                        try {
                                            jsonBody2.put("target", phone_signal);
//                                        jsonBody2.put("user_ip", userIpText);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        String requestBody2 = jsonBody2.toString();
                                        return  requestBody2.getBytes(StandardCharsets.UTF_8);
                                    }
                                };
                                queue.add(stringRequest_nodejs);

                                //POST_TO_NODEJS--------------------------------------------------------------------

                                //POST_TO_Jetson_Xavier-------------------------------------------------------------

                                JsonObjectRequest stringRequest_jsonXavier = new JsonObjectRequest(Request.Method.POST, userIpText,null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                car_signal = "";
                                                System.out.println(response);
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        System.out.println(error);
                                    }
                                }){
                                    @Override
                                    public byte[] getBody() {
                                        JSONObject jsonBody3 = new JSONObject();
                                        try {
//                                        jsonBody3.put("target", targetText);
                                            jsonBody3.put("target", car_signal);
//                                        jsonBody3.put("user", "bot01");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        String requestBody2 = jsonBody3.toString();
                                        return  requestBody2.getBytes(StandardCharsets.UTF_8);
                                    }
                                };

                                queue.add(stringRequest_jsonXavier);

                                //POST_TO_Jetson_Xavier-------------------------------------------------------------

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
//                    textView.setText("That didn't work!");
                    }
                })
                {
                    @Override
                    public byte[] getBody() {
                        JSONObject jsonBody = new JSONObject();
                        try {
//                        jsonBody.put("avg_score", 50);
//                        jsonBody.put("cheat_word", cheat_word);
                            jsonBody.put("text", SpeechWord);
                            jsonBody.put("datetime", nowDate);
                            jsonBody.put("user","bot01");
                            jsonBody.put("school", student_school);
                            jsonBody.put("grade", student_grade);
                            jsonBody.put("class", student_class);
                            jsonBody.put("name", student_name);
                            jsonBody.put("cheat_word", cheat_word);
                            System.out.println("cheat_word = " + cheat_word);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String requestBody = jsonBody.toString();
                        return requestBody.getBytes(StandardCharsets.UTF_8);
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(jsonObjectRequest);
            }
        });
    }


    private boolean checkPermissions() {
        int first = ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT);
            }
            else{
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT);
            }
        }
    }
}

