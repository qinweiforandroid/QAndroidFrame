package com.qw.frame;

import android.app.Application;
import android.os.Environment;
import android.test.ApplicationTestCase;

import com.qw.library.http.L;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testPrivateFilePath() throws Exception {
        String filePath = getContext().getFilesDir().getAbsolutePath();
        L.e(filePath);
    }

    public void testSDCardPath() throws Exception {
        String cachePath = getContext().getExternalCacheDir().getAbsolutePath();
        String picturePath = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String sdcardRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        L.e("cachePath:" + cachePath);
        L.e("picturePath:" + picturePath);
        L.e("sdcardRootPath:" + sdcardRootPath);
    }

    public void testDBPath() throws Exception {
        String dbPath = mContext.getDatabasePath("name").getPath();
        L.e("dbPath:" + dbPath);

    }
}