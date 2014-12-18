package net.rebmos.vutaimage.callbacks;

import net.rebmos.vutaimage.VutaImageItem;

public interface ImagesDownloadCallback {
	public void onError( VutaImageItem image );
	public void done( );
}
