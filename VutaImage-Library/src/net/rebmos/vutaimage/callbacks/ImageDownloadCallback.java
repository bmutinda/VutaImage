package net.rebmos.vutaimage.callbacks;

public interface ImageDownloadCallback {
	public void progress( int elapsed, int totalSize );
	public void done( boolean success );
}