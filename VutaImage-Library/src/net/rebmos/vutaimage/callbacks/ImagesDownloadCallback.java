package net.rebmos.vutaimage.callbacks;

import net.rebmos.vutaimage.VutaImageItem;

/**
 * 
 * @author Mutinda Boniface
 *
 */
public interface ImagesDownloadCallback {
	public void onError( VutaImageItem image );
	public void done( );
}
