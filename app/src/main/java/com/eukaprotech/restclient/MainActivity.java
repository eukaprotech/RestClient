package com.eukaprotech.restclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.eukaprotech.customdialog.CustomDialog;
import com.eukaprotech.customdialog.CustomDialogHandler;
import com.eukaprotech.networking.AsyncConnection;
import com.eukaprotech.networking.AsyncConnectionHandler;
import com.eukaprotech.networking.Parameters;
import com.eukaprotech.networking.URLBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    Activity activity;
    TextView tv_request, tv_clear_url,tv_clear_params, tv_clear_headers;
    EditText et_url;
    Spinner sp_request_type;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    ArrayList<TextUndoRedoUtil> textUndoRedoUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        activity=MainActivity.this;
        initToolbar();
        progressDialog= new ProgressDialog(context);
        progressDialog.setMessage("Loading..." );
        progressDialog.setCancelable(false);
        sp_request_type=findViewById(R.id.spinner_request_type) ;
        et_url=findViewById(R.id.et_url);
        tv_request=findViewById(R.id.bt_request);
        tv_clear_url=findViewById(R.id.bt_clear_url);
        tv_clear_params=findViewById(R.id.bt_clear_params);
        tv_clear_headers=findViewById(R.id.bt_clear_headers);
        tv_request.setOnClickListener(MainActivity.this);
        tv_clear_url.setOnClickListener(MainActivity.this);
        tv_clear_params.setOnClickListener(MainActivity.this);
        tv_clear_headers.setOnClickListener(MainActivity.this);
        initHistory();
    }
    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem undoItem = menu.findItem(R.id.action_undo);
        undoItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                undoHistory();
                return false;
            }
        });
        MenuItem redoItem = menu.findItem(R.id.action_redo);
        redoItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                redoHistory();
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    protected boolean isValidLink(String link){
        try{
            new URL(link);
        }catch (Exception ex) {
            return false;
        }
        return true;
    }
    public boolean isValidRequest(){
        String url=getURL();
        if(TextUtils.isEmpty(url)) {
            showMessage("URL","Enter URL",true);
            return false;
        }
        if (!isValidLink(url)) {
            showMessage("URL","Enter a valid URL",true);
            return false;
        }
        return true;
    }
    public Parameters getParams(){
        Parameters params=new Parameters();
        addParam(params,   ((EditText)findViewById(R.id.key1)).getText().toString(),  ((EditText)findViewById(R.id.value1)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key2)).getText().toString(),  ((EditText)findViewById(R.id.value2)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key3)).getText().toString(),  ((EditText)findViewById(R.id.value3)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key4)).getText().toString(),  ((EditText)findViewById(R.id.value4)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key5)).getText().toString(),  ((EditText)findViewById(R.id.value5)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key6)).getText().toString(),  ((EditText)findViewById(R.id.value6)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key7)).getText().toString(),  ((EditText)findViewById(R.id.value7)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key8)).getText().toString(),  ((EditText)findViewById(R.id.value8)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key9)).getText().toString(),  ((EditText)findViewById(R.id.value9)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key10)).getText().toString(),  ((EditText)findViewById(R.id.value10)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key11)).getText().toString(),  ((EditText)findViewById(R.id.value11)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key12)).getText().toString(),  ((EditText)findViewById(R.id.value12)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key13)).getText().toString(),  ((EditText)findViewById(R.id.value13)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key14)).getText().toString(),  ((EditText)findViewById(R.id.value14)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key15)).getText().toString(),  ((EditText)findViewById(R.id.value15)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key16)).getText().toString(),  ((EditText)findViewById(R.id.value16)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key17)).getText().toString(),  ((EditText)findViewById(R.id.value17)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key18)).getText().toString(),  ((EditText)findViewById(R.id.value18)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key19)).getText().toString(),  ((EditText)findViewById(R.id.value19)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key20)).getText().toString(),  ((EditText)findViewById(R.id.value20)).getText().toString()  );
        addParam(params,   ((EditText)findViewById(R.id.key21)).getText().toString(),  ((EditText)findViewById(R.id.value21)).getText().toString()  );
        if(params.size() == 0){
            return null;
        }
        return params;
    }
    public void addParam(Parameters params,String key, String value){
        if(!TextUtils.isEmpty(key.trim())){
            params.put(key.trim(),value.trim());
        }
    }
    public void clearParams(){
        ((EditText)findViewById(R.id.key1)).setText("");
        ((EditText)findViewById(R.id.key2)).setText("");
        ((EditText)findViewById(R.id.key3)).setText("");
        ((EditText)findViewById(R.id.key4)).setText("");
        ((EditText)findViewById(R.id.key5)).setText("");
        ((EditText)findViewById(R.id.key6)).setText("");
        ((EditText)findViewById(R.id.key7)).setText("");
        ((EditText)findViewById(R.id.key8)).setText("");
        ((EditText)findViewById(R.id.key9)).setText("");
        ((EditText)findViewById(R.id.key10)).setText("");
        ((EditText)findViewById(R.id.key11)).setText("");
        ((EditText)findViewById(R.id.key12)).setText("");
        ((EditText)findViewById(R.id.key13)).setText("");
        ((EditText)findViewById(R.id.key14)).setText("");
        ((EditText)findViewById(R.id.key15)).setText("");
        ((EditText)findViewById(R.id.key16)).setText("");
        ((EditText)findViewById(R.id.key17)).setText("");
        ((EditText)findViewById(R.id.key18)).setText("");
        ((EditText)findViewById(R.id.key19)).setText("");
        ((EditText)findViewById(R.id.key20)).setText("");
        ((EditText)findViewById(R.id.key21)).setText("");

        ((EditText)findViewById(R.id.value1)).setText("");
        ((EditText)findViewById(R.id.value2)).setText("");
        ((EditText)findViewById(R.id.value3)).setText("");
        ((EditText)findViewById(R.id.value4)).setText("");
        ((EditText)findViewById(R.id.value5)).setText("");
        ((EditText)findViewById(R.id.value6)).setText("");
        ((EditText)findViewById(R.id.value7)).setText("");
        ((EditText)findViewById(R.id.value8)).setText("");
        ((EditText)findViewById(R.id.value9)).setText("");
        ((EditText)findViewById(R.id.value10)).setText("");
        ((EditText)findViewById(R.id.value11)).setText("");
        ((EditText)findViewById(R.id.value12)).setText("");
        ((EditText)findViewById(R.id.value13)).setText("");
        ((EditText)findViewById(R.id.value14)).setText("");
        ((EditText)findViewById(R.id.value15)).setText("");
        ((EditText)findViewById(R.id.value16)).setText("");
        ((EditText)findViewById(R.id.value17)).setText("");
        ((EditText)findViewById(R.id.value18)).setText("");
        ((EditText)findViewById(R.id.value19)).setText("");
        ((EditText)findViewById(R.id.value20)).setText("");
        ((EditText)findViewById(R.id.value21)).setText("");
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String, String> getHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        addAuthorizationHeader(headers);
        addHeader(headers,   ((EditText)findViewById(R.id.header_key1)).getText().toString(),  ((EditText)findViewById(R.id.header_value1)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key2)).getText().toString(),  ((EditText)findViewById(R.id.header_value2)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key3)).getText().toString(),  ((EditText)findViewById(R.id.header_value3)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key4)).getText().toString(),  ((EditText)findViewById(R.id.header_value4)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key5)).getText().toString(),  ((EditText)findViewById(R.id.header_value5)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key6)).getText().toString(),  ((EditText)findViewById(R.id.header_value6)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key7)).getText().toString(),  ((EditText)findViewById(R.id.header_value7)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key8)).getText().toString(),  ((EditText)findViewById(R.id.header_value8)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key9)).getText().toString(),  ((EditText)findViewById(R.id.header_value9)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key10)).getText().toString(),  ((EditText)findViewById(R.id.header_value10)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key11)).getText().toString(),  ((EditText)findViewById(R.id.header_value11)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key12)).getText().toString(),  ((EditText)findViewById(R.id.header_value12)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key13)).getText().toString(),  ((EditText)findViewById(R.id.header_value13)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key14)).getText().toString(),  ((EditText)findViewById(R.id.header_value14)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key15)).getText().toString(),  ((EditText)findViewById(R.id.header_value15)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key16)).getText().toString(),  ((EditText)findViewById(R.id.header_value16)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key17)).getText().toString(),  ((EditText)findViewById(R.id.header_value17)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key18)).getText().toString(),  ((EditText)findViewById(R.id.header_value18)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key19)).getText().toString(),  ((EditText)findViewById(R.id.header_value19)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key20)).getText().toString(),  ((EditText)findViewById(R.id.header_value20)).getText().toString()  );
        addHeader(headers,   ((EditText)findViewById(R.id.header_key21)).getText().toString(),  ((EditText)findViewById(R.id.header_value21)).getText().toString()  );
        if(headers.size() == 0){
            return null;
        }
        return headers;
    }
    public void addHeader(HashMap<String, String> headers,String key, String value){
        if(!TextUtils.isEmpty(key.trim())){
            headers.put(key.trim(),value.trim());
        }
    }
    public void clearHeaders(){
        ((EditText)findViewById(R.id.header_key1)).setText("");
        ((EditText)findViewById(R.id.header_key2)).setText("");
        ((EditText)findViewById(R.id.header_key3)).setText("");
        ((EditText)findViewById(R.id.header_key4)).setText("");
        ((EditText)findViewById(R.id.header_key5)).setText("");
        ((EditText)findViewById(R.id.header_key6)).setText("");
        ((EditText)findViewById(R.id.header_key7)).setText("");
        ((EditText)findViewById(R.id.header_key8)).setText("");
        ((EditText)findViewById(R.id.header_key9)).setText("");
        ((EditText)findViewById(R.id.header_key10)).setText("");
        ((EditText)findViewById(R.id.header_key11)).setText("");
        ((EditText)findViewById(R.id.header_key12)).setText("");
        ((EditText)findViewById(R.id.header_key13)).setText("");
        ((EditText)findViewById(R.id.header_key14)).setText("");
        ((EditText)findViewById(R.id.header_key15)).setText("");
        ((EditText)findViewById(R.id.header_key16)).setText("");
        ((EditText)findViewById(R.id.header_key17)).setText("");
        ((EditText)findViewById(R.id.header_key18)).setText("");
        ((EditText)findViewById(R.id.header_key19)).setText("");
        ((EditText)findViewById(R.id.header_key20)).setText("");
        ((EditText)findViewById(R.id.header_key21)).setText("");

        ((EditText)findViewById(R.id.header_value1)).setText("");
        ((EditText)findViewById(R.id.header_value2)).setText("");
        ((EditText)findViewById(R.id.header_value3)).setText("");
        ((EditText)findViewById(R.id.header_value4)).setText("");
        ((EditText)findViewById(R.id.header_value5)).setText("");
        ((EditText)findViewById(R.id.header_value6)).setText("");
        ((EditText)findViewById(R.id.header_value7)).setText("");
        ((EditText)findViewById(R.id.header_value8)).setText("");
        ((EditText)findViewById(R.id.header_value9)).setText("");
        ((EditText)findViewById(R.id.header_value10)).setText("");
        ((EditText)findViewById(R.id.header_value11)).setText("");
        ((EditText)findViewById(R.id.header_value12)).setText("");
        ((EditText)findViewById(R.id.header_value13)).setText("");
        ((EditText)findViewById(R.id.header_value14)).setText("");
        ((EditText)findViewById(R.id.header_value15)).setText("");
        ((EditText)findViewById(R.id.header_value16)).setText("");
        ((EditText)findViewById(R.id.header_value17)).setText("");
        ((EditText)findViewById(R.id.header_value18)).setText("");
        ((EditText)findViewById(R.id.header_value19)).setText("");
        ((EditText)findViewById(R.id.header_value20)).setText("");
        ((EditText)findViewById(R.id.header_value21)).setText("");
    }
    public void clearURL(){
        et_url.setText("");
    }
    public String getRequestType(){
        return sp_request_type.getSelectedItem().toString();
    }
    public String getURL(){
        return et_url.getText().toString().trim();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_clear_url:
                clearURL();
                break;
            case R.id.bt_clear_params:
                clearParams();
                break;
            case R.id.bt_clear_headers:
                clearHeaders();
                break;
            case R.id.bt_request:
                if(isValidRequest()){
                    executeRequest(new AsyncConnectionHandler() {
                        @Override
                        public void onStart() {
                            progressDialog.show();
                        }

                        @Override
                        public void onSucceed(int responseCode, HashMap<String, String> headers, byte[] response) {
                            String response_data = "";

                            try{
                                response_data = new String(response);
                            }catch (Exception ex){}
                            StringBuilder stringBuilder=new StringBuilder();
                            stringBuilder.append("RESPONSE CODE: ");
                            stringBuilder.append(responseCode);
                            stringBuilder.append("\n\n");
                            stringBuilder.append("HEADERS: ");
                            stringBuilder.append("\n\n");
                            stringBuilder.append(headers.toString());
                            stringBuilder.append("\n\n");
                            stringBuilder.append("RESPONSE: ");
                            stringBuilder.append("\n\n");
                            stringBuilder.append(response_data);
                            String title ="SUCCESS";
                            if (!isFinishing()) {
                                try {
                                    final String final_response_data = response_data;
                                    new AlertDialog.Builder(MainActivity.this).setTitle(title).setMessage(stringBuilder.toString()).setPositiveButton("OK", null).setNegativeButton("WEB VIEW", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent=new Intent(context, ActivityWebView.class);
                                            intent.setAction("browse");
                                            intent.putExtra("html_content", final_response_data);
                                            startActivity(intent);
                                        }
                                    }).setCancelable(false).show();

                                }catch (Exception ex){}
                            }
                        }

                        @Override
                        public void onFail(int responseCode, HashMap<String, String> headers, byte[] response, Exception error) {
                            String response_body = "";

                            try{
                                response_body= new String(response);
                            }catch (Exception ex){}
                            StringBuilder _error = new StringBuilder(error.toString() + "\nStackTrace\n");
                            StackTraceElement[] elements =  error.getStackTrace();
                            for(StackTraceElement el: elements){
                                _error.append(el.toString());
                            }
                            StringBuilder stringBuilder=new StringBuilder();
                            stringBuilder.append("RESPONSE CODE: ");
                            stringBuilder.append(responseCode);
                            stringBuilder.append("\n\n");
                            stringBuilder.append("HEADERS: ");
                            stringBuilder.append("\n\n");
                            stringBuilder.append(headers.toString());
                            stringBuilder.append("\n\n");
                            stringBuilder.append("ERROR: ");
                            stringBuilder.append("\n\n");
                            stringBuilder.append(_error.toString());
                            stringBuilder.append("\n\n");
                            stringBuilder.append("RESPONSE: ");
                            stringBuilder.append("\n\n");
                            stringBuilder.append(response_body);
                            String title ="FAIL";

                            if (!isFinishing()) {
                                try {
                                    final String final_response_body = response_body;
                                    new AlertDialog.Builder(MainActivity.this).setTitle(title).setMessage(stringBuilder.toString()).setPositiveButton("OK", null).setNegativeButton("WEB VIEW", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(context, ActivityWebView.class);
                                            intent.setAction("browse");
                                            intent.putExtra("html_content", final_response_body);
                                            startActivity(intent);
                                        }
                                    }).setCancelable(false).show();

                                } catch (Exception ex) {
                                }
                            }
                        }

                        @Override
                        public void onComplete() {
                            progressDialog.dismiss();
                        }
                    });
                    setHistory();
                }
                break;
        }
    }
    public void addAuthorizationHeader(HashMap<String, String> headers){
        final String username = ((EditText)findViewById(R.id.user_name)).getText().toString();
        final String password = ((EditText)findViewById(R.id.password)).getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            final String usernamePassword = username + ":" + password;
            final String basicAuth = "basic " + Base64.encodeToString(usernamePassword.getBytes(), Base64.NO_WRAP);
            addHeader(headers, "Authorization", basicAuth);
        }
    }
    public void executeRequest(AsyncConnectionHandler asyncConnectionHandler){
        String request_type = getRequestType();
        String url=getURL();
        HashMap<String, String> headers = getHeaders();
        Parameters params=getParams();
        AsyncConnection asyncConnection= new AsyncConnection();
        switch (request_type) {
            case "POST":
                asyncConnection.post(url, headers, params, asyncConnectionHandler);
                break;
            case "GET":
                url = URLBuilder.build(url, params);
                asyncConnection.get(url, headers, asyncConnectionHandler);
                break;
            case "PUT":
                asyncConnection.put(url, headers, params, asyncConnectionHandler);
                break;
            case "DELETE":
                url = URLBuilder.build(url, params);
                asyncConnection.delete(url, headers, asyncConnectionHandler);
                break;
            case "HEAD":
                asyncConnection.head(url, headers, asyncConnectionHandler);
                break;
            case "OPTIONS":
                asyncConnection.options(url, headers, asyncConnectionHandler);
                break;
        }
    }
    public void showMessage(String title,String message,boolean showDialog){

        if(showDialog) {
            if (! isFinishing()) {
                try {
                    new AlertDialog.Builder(MainActivity.this).setTitle(title).setMessage(message).setPositiveButton("OK", null).setCancelable(false).show();

                }catch (Exception ex){}
            }
        }else{
            Toast.makeText(MainActivity.this,title+" "+message, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode ==KeyEvent.KEYCODE_BACK)) {
            CustomDialog dialog = new CustomDialog(context, activity, "Exit " + context.getResources().getString(R.string.app_name), "Yes", "No");
            dialog.show(new CustomDialogHandler() {
                @Override
                public void onAccept() {
                    activity.finish();
                }

                @Override
                public void onCancel() {

                }
            });
        }
        return false;
    }
    public void initHistory(){
        textUndoRedoUtils=new ArrayList<>();
        textUndoRedoUtils.add(new TextUndoRedoUtil(sp_request_type));
        textUndoRedoUtils.add(new TextUndoRedoUtil(et_url));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key1)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key2)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key3)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key4)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key5)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key6)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key7)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key8)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key9)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key10)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key11)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key12)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key13)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key14)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key15)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key16)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key17)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key18)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key19)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key20)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.key21)));

        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value1)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value2)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value3)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value4)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value5)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value6)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value7)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value8)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value9)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value10)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value11)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value12)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value13)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value14)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value15)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value16)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value17)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value18)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value19)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value20)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.value21)));


        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key1)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key2)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key3)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key4)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key5)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key6)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key7)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key8)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key9)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key10)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key11)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key12)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key13)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key14)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key15)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key16)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key17)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key18)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key19)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key20)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_key21)));

        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value1)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value2)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value3)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value4)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value5)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value6)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value7)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value8)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value9)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value10)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value11)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value12)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value13)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value14)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value15)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value16)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value17)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value18)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value19)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value20)));
        textUndoRedoUtils.add(new TextUndoRedoUtil(findViewById(R.id.header_value21)));
    }
    public void setHistory(){
        if(textUndoRedoUtils == null){
            initHistory();
        }
        for(TextUndoRedoUtil util:textUndoRedoUtils){
            util.set();
        }
    }
    public void undoHistory(){
        if(textUndoRedoUtils == null){
            initHistory();
        }
       for(TextUndoRedoUtil util:textUndoRedoUtils){
           util.undo();
       }
    }
    public void redoHistory(){
        if(textUndoRedoUtils == null){
            initHistory();
        }
        for(TextUndoRedoUtil util:textUndoRedoUtils){
            util.redo();
        }
    }
}
