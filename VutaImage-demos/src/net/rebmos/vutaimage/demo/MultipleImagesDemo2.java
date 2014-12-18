package net.rebmos.vutaimage.demo;

import java.util.ArrayList;
import java.util.List;

import net.rebmos.vutaimage.VutaImage;
import net.rebmos.vutaimage.VutaImageItem;
import net.rebmos.vutaimage.callbacks.EachImageDownloadCallback;
import net.rebmos.vutaimage.callbacks.ImagesDownloadCallback;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MultipleImagesDemo2 extends Activity {
	public static String TAG = "VUTA_IMAGE";
	
	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_main );
		
		// Get the sdcard directory 
		String sdcard = VutaImage.getExternalStorage();
		
		// Create a list of all the images we want to download 
		List<VutaImageItem> images = new ArrayList<VutaImageItem>();
		images.add( new VutaImageItem("https://avatars0.githubusercontent.com/u/1729141?v=3&s=460" , sdcard+"/Github.png" ));
		images.add( new VutaImageItem("http://trendingnewsroom.com/img/logo.png" , sdcard+"/Logo1.png" ));
		images.add( new VutaImageItem("http://trendingnewsroom.com/img/logo.png" , sdcard+"/Logo2.png" ));
		
		// Use all images download callback 
		VutaImage.download(images, new ImagesDownloadCallback() {

			@Override
			public void onError(VutaImageItem image) {
				Log.e(TAG, "Image failed to download..."+image.getUrl()+"->"+image.getFilename() );
			}

			@Override
			public void done() {
				Log.e(TAG, "All images downloaded" );
			}
		});
		
		// Each image download callback 
		VutaImage.download(images, new EachImageDownloadCallback() {
			
			@Override
			public void onProgress(VutaImageItem image, boolean success) {
				Log.e(TAG, "2 Image downloads progress.."+image.getUrl()+"->success = "+success );
			}
			
			@Override
			public void onError(VutaImageItem image) {
				Log.e(TAG, "2 Image downloads error.."+image.getUrl() );
			}
			
			@Override
			public void done() {
				Log.e(TAG, "2 All images downloaded" );
			}
		});
	}

}
