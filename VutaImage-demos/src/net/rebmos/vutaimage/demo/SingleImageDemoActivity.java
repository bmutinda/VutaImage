package net.rebmos.vutaimage.demo;

import net.rebmos.vutaimage.VutaImage;
import net.rebmos.vutaimage.callbacks.ImageDownloadCallback;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SingleImageDemoActivity extends Activity {
	public static String TAG = "VUTA_IMAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// https://avatars0.githubusercontent.com/u/1729141?v=3&s=460
		// http://mutinda.rebmos.net/wp-content/uploads/2014/12/logo-11.png
		final String imageUrl = "http://www.villard.biz/assets/Uploads/projects/portrait-o.jpg";
		
		String sdCard = VutaImage.getExternalStorage( );
		String filename = sdCard+"/file.png";
		
		VutaImage.download( imageUrl, filename, new ImageDownloadCallback() {
			@Override
			public void progress(int elapsed, int totalSize) {
				Log.e( TAG , "Image download progress = "+elapsed+" out of "+totalSize );
			}
			
			@Override
			public void done(boolean success) {
				Log.e( TAG , "Image download done with success = "+success );
			}
		});
	}
}
