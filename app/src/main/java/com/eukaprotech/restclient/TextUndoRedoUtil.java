package com.eukaprotech.restclient;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by APPS KIT on 15/02/2017.
 */
public class TextUndoRedoUtil {
    View view;
    Deque<String> main_stack, cache_stack;
    public TextUndoRedoUtil(View view){
        main_stack = new ArrayDeque<String>();
        cache_stack = new ArrayDeque<String>();
        this.view=view;
    }
    public void set(){
        if(view == null){
            return;
        }
        String text = "";
        if(view instanceof Spinner){
            Spinner spinner=(Spinner)view;
            text=spinner.getSelectedItem().toString();
        }else if(view instanceof EditText){
            EditText editText=(EditText)view;
            text=editText.getText().toString();
        }
        main_stack.push(text);
    }
    public void undo(){
        if(main_stack.size() == 0){
            return;
        }
        String text=main_stack.pop();
        cache_stack.push(text);
        if(main_stack.size() == 0){
            return;
        }
        text=main_stack.peek();
        if(view == null){
            return;
        }
        if(view instanceof Spinner){
            Spinner spinner=(Spinner)view;
            spinner.setSelection(getSpinnerIndex(spinner,text));
        }else if(view instanceof EditText){
            EditText editText=(EditText)view;
            editText.setText(text);
        }
    }
    public void redo(){
        if(cache_stack.size() == 0){
            return;
        }
        String text=cache_stack.pop();
        main_stack.push(text);
        text=main_stack.peek();//redundant but lets do it
        if(view == null){
            return;
        }
        if(view instanceof Spinner){
            Spinner spinner=(Spinner)view;
            spinner.setSelection(getSpinnerIndex(spinner,text));
        }else if(view instanceof EditText){
            EditText editText=(EditText)view;
            editText.setText(text);
        }
    }
    private int getSpinnerIndex(Spinner spinner, String value) {
        int index = 0;
        for(int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)){
                index=i;
                break;
            }
        }
        return index;
    }

}
