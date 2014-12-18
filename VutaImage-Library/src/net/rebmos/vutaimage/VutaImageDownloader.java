package net.rebmos.vutaimage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.rebmos.vutaimage.callbacks.ImageDownloadCallback;
import android.os.AsyncTask;

public class VutaImageDownloader {
	String imageUrl;
	String imageFilename;
	ImageDownloadCallback callback = null ;
	
	public VutaImageDownloader( VutaImageItem image ) {
		this.imageUrl = image.getUrl();
		this.imageFilename = image.getFilename();
	}
	
	public VutaImageDownloader( String imageUrl ) {
		this.imageUrl = imageUrl;
		this.imageFilename = VutaImageItem.resolveFileName(imageUrl);
	}

	public VutaImageDownloader( String imageUrl, String imageFilename ) {
		this.imageUrl = imageUrl;
		this.imageFilename = imageFilename;
	}
	
	public void download( ImageDownloadCallback callback ){
		this.callback = callback;
		
		if( this.imageUrl == null ){
			this.callback.done(false);
			return;
		}
		
		// Resolve the filename if their was no filename supplied 
		if( this.imageFilename == null ){
			this.imageFilename = VutaImageItem.resolveFileName( this.imageUrl );
			
			// By default we donwload the image to sdcard 
			this.imageFilename = VutaImage.getExternalStorage()+"/"+this.imageFilename;
		}
		
		new DownloadAsyncTask().execute();
	}
	
	private class DownloadAsyncTask extends AsyncTask<String, String, String> {
		boolean download_ok = false;

		@Override
		protected String doInBackground(String... params) {
			try {
				// set the download URL, a url that points to a file on the
				// internet
				// this is the file to be downloaded
				URL url = new URL( imageUrl );

				// create the new connection
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				urlConnection.setConnectTimeout(30000);
				urlConnection.setReadTimeout(30000);
				urlConnection.setInstanceFollowRedirects(true);
				// set up some things on the connection
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);

				// and connect!
				urlConnection.connect();

				// set the path where we want to save the file
				File file = new File(imageFilename);

				// this will be used to write the downloaded data into the file
				// we created
				FileOutputStream fileOutput = new FileOutputStream(file);

				// this will be used in reading the data from the internet
				InputStream inputStream = urlConnection.getInputStream();

				// this is the total size of the file
				int totalSize = urlConnection.getContentLength();
				// variable to store total downloaded bytes
				int downloadedSize = 0;

				// create a buffer...
				byte[] buffer = new byte[1024];
				int bufferLength = 0; // used to store a temporary size of the
										// buffer

				// now, read through the input buffer and write the contents to
				// the file
				while ((bufferLength = inputStream.read(buffer)) > 0) {
					// add the data in the buffer to the file in the file output
					// stream (the file on the sd card
					fileOutput.write(buffer, 0, bufferLength);
					// add up the size so we know how much is downloaded
					downloadedSize += bufferLength;
					// this is where you would do something to report the
					// prgress, like this maybe
					
					if( callback !=null ){
						callback.progress(downloadedSize, totalSize);
					}
				}
				// close the output stream when done
				fileOutput.close();

				download_ok = true;
				// catch some possible errors...
			} catch (MalformedURLException e) {
				 e.printStackTrace();
			} catch (IOException e) {
				 e.printStackTrace();
			} catch( Exception e ){
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if( callback !=null ){
				callback.done( download_ok );
			}
		}
	}
}
