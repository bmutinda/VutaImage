package net.rebmos.vutaimage;

import java.util.ArrayList;
import java.util.List;

import net.rebmos.vutaimage.callbacks.EachImageDownloadCallback;
import net.rebmos.vutaimage.callbacks.ImageDownloadCallback;
import net.rebmos.vutaimage.callbacks.ImagesDownloadCallback;

/**
 * 
 * @author Mutinda Boniface
 *
 */
public class VutaImagesDownloader {
	List<VutaImageItem> images = new ArrayList<VutaImageItem>();
	int total = 0;
	int downloaded = 0;
	
	public VutaImagesDownloader( List<VutaImageItem> images ) {
		this.images = images;
		this.total = images.size();
	}
	
	public void download( final ImagesDownloadCallback callback ){
		this.doDownload(callback);
	}
	
	public void download( final EachImageDownloadCallback callback ){
		this.doDownload(callback);
	}
	
	
	private void doDownload( final Object callback ){
		for( final VutaImageItem image: this.images ){
			new VutaImageDownloader(image).download( new ImageDownloadCallback() {
				@Override
				public void progress(int elapsed, int totalSize) {
				}
				
				@Override
				public void done(boolean success) {
					downloaded++;
					// Trigger progress for every downloaded image 
					triggerDownloadProgress( image, success, callback );
					
					// Trigger on error 
					if( !success ){
						triggerOnError(image, callback );
					}
					
					// Check if we are done with downloading all images 
					if( downloaded>= total ){
						triggerDownloadComplete( callback );
					}
				}
			});
		}
	}
	
	private void triggerDownloadProgress( VutaImageItem image, boolean success, Object callback ){
		if( callback instanceof EachImageDownloadCallback ){
			((EachImageDownloadCallback) callback).onProgress(image, success);
		}
	}
	
	private void triggerOnError( VutaImageItem image, Object callback ){
		if( callback instanceof EachImageDownloadCallback ){
			((EachImageDownloadCallback) callback).onError(image);
		}else if( callback instanceof ImagesDownloadCallback ){
			((ImagesDownloadCallback) callback).onError(image);
		}
	}
	
	/**
	 * Triggered when all the images passed in are downloaded whether with erros or not  
	 * 
	 * @param callback - The callback to be triggered. This is any of {@link EachImageDownloadCallback} OR {@link ImagesDownloadCallback}
	 */
	private void triggerDownloadComplete( Object callback ){
		if( callback instanceof EachImageDownloadCallback ){
			((EachImageDownloadCallback) callback).done();
		}else if( callback instanceof ImagesDownloadCallback ){
			((ImagesDownloadCallback) callback).done();
		}
	}
}