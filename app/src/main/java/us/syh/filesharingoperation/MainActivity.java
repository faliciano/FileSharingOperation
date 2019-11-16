package us.syh.filesharingoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
        setResult(Activity.RESULT_CANCELED,null);
        ListView listView_1=findViewById(R.id.listView_1);
        for(int i=0;i<mImageFiles.length;i++){
            ImageView imageView_new=new ImageView(this);
            try {
                InputStream inputStream_new=new FileInputStream(mImageFiles[i]);
                imageView_new.setImageBitmap(BitmapFactory.decodeStream(inputStream_new));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            listView_1.addView(imageView_new);
        }
    }
}
