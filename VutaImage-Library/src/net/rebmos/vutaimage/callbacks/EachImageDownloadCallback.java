package net.rebmos.vutaimage.callbacks;

import net.rebmos.vutaimage.VutaImageItem;

/**
 * 
 * @author Mutinda Boniface
 *
 */
public interface EachImageDownloadCallback {
	public void onError( VutaImageItem image );
	public void onProgress( VutaImageItem image, boolean success);
	public void done();
}