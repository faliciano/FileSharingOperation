package us.syh.filesharingoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Intent mResultIntent;
    private File mPrivateRootDir;//内部储存根目录
    private File mImagesDir;//images子目录
    File[] mImageFiles;//images子目录内文件对象组
    String[] mImageFilenames;//images子目录内文件名组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultIntent=new Intent("us.syh.fileshareoperation.ACTION_RETURN_FILE");
        mPrivateRootDir=getFilesDir();
        mImagesDir=new File(mPrivateRootDir,"images");
        mImageFiles=mImagesDir.listFiles();
        mImageFilenames= mImagesDir.list();
        setResult(Activity.RESULT_CANCELED,null);
        LinearLayout LinearLayout_1=findViewById(R.id.LinearLayout_1);
        if(mImageFiles.length>0) {
            for (int i = 0; i < mImageFiles.length; i++) {
                //添加图片
                ImageView imageView_new = new ImageView(this);
                try {
                    InputStream inputStream_new = new FileInputStream(mImageFiles[i]);
                    imageView_new.setImageBitmap(BitmapFactory.decodeStream(inputStream_new));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                TextView textView_new=new TextView(this);
                textView_new.setText(mImageFilenames[i]);
                textView_new.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                final int finalI = i;
                textView_new.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri fileUri= FileProvider.getUriForFile(MainActivity.this,"us.syh.filesharingoperation.fileprovider",mImageFiles[finalI]);
                        Intent intent=new Intent(MainActivity.this,FileSelectActivity.class);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(fileUri,getContentResolver().getType(fileUri));
                        //MainActivity.this.setResult(Activity.RESULT_OK,intent);
                        startActivity(intent);
                    }
                });
                LinearLayout_1.addView(imageView_new);
                LinearLayout_1.addView(textView_new);
            }
        }else{
            TextView textView_new=new TextView(this);
            textView_new.setText("无内容");
            LinearLayout_1.addView(textView_new);
        }
    }
}
