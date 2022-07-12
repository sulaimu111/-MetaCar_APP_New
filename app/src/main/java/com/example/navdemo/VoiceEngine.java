package com.example.navdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class VoiceEngine implements RecognitionListener {
    public MutableLiveData<ArrayList<String>> voiceList = new MutableLiveData();//辨識文字陣列
    public MutableLiveData<String> voiceWord = new MutableLiveData();//辨識文字
    public MutableLiveData<Integer> errorCode = new MutableLiveData();//錯誤代碼
    public MutableLiveData<Float> Rms = new MutableLiveData<>();//分貝

    private SpeechRecognizer recognizer;
    private Intent speechIntent;

    public VoiceEngine(Context context) {
        recognizer = SpeechRecognizer.createSpeechRecognizer(context);
        recognizer.setRecognitionListener(this);
        speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    }

    public void start() {
        recognizer.startListening(speechIntent);
    }

    public void destroy() {
        recognizer.stopListening();
        recognizer.destroy();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Rms.setValue(rmsdB);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        errorCode.setValue(error);
    }

    @Override
    public void onResults(Bundle results) {
        voiceList.setValue(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
        voiceWord.setValue(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

}
