package us.syh.filesharingoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;

public class FileSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);
        Intent intent=new Intent();
        Bundle extras = intent.getExtras();
        String fileUri2=extras.getString("aaa");
        String fileUri=intent.getDataString();
        System.out.println("FileUri2:"+fileUri2);
        TextView textView_fs_1=findViewById(R.id.textView_fs_1);
        textView_fs_1.setText(fileUri);
    }
}
