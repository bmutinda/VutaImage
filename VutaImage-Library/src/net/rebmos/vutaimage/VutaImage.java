package net.rebmos.vutaimage;

import java.util.List;

import net.rebmos.vutaimage.callbacks.EachImageDownloadCallback;
import net.rebmos.vutaimage.callbacks.ImageDownloadCallback;
import net.rebmos.vutaimage.callbacks.ImagesDownloadCallback;
import android.os.Environment;

/**
 * 
 * @author Mutinda Boniface <http://mutindaz.github.io>
 * @version 1.0
 * @website http://mutindaz.github.io/vutaimage
 */
public class VutaImage {
	
	// Keeps the default storage dir for the downloaded images 
	private static String defaultStorageDir = getExternalStorage();
	
	// Debug enabled or not
	public static boolean debug = false;
	
	/**
	 * Controls whether debugging is enabled or not 
	 * @param debug
	 */
	public static void setDebug( boolean debug ){
		VutaImage.debug = debug;
	}
	  
	/**
	 * Sets the default storage directory for the downloaded images 
	 * if the filepath was not supplied 
	 * <p></p>
	 * NB: This is only used if we are resolving filename from the supplied image source url
	 * 
	 * @param dirPath {@link String} - the storage path as a string e.g. /emulated/sdcard0/dir/
	 */
	public static void setDefaultStorageDir( String dirPath ){
		VutaImage.defaultStorageDir = dirPath;
	}
	
	/**
	 * Get the default set storage directory for the images 
	 * 
	 * @return {@link String} - Storage directory as a string e.g. /emulates/sdcard0/dir/
	 */
	public static String getDefaultStorageDir( ){
		return VutaImage.defaultStorageDir;
	}
		
	/**
	 * Gets the external storage directory as a {@link String}
	 * 
	 * @return {@link String} - External storage directory as a string e.g. /emulates/sdcard0
	 */
	public static String getExternalStorage(  ){
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
	
	/**
	 * Downloads a single image give the image url 
	 * <p> NB:
	 * <ul>
	 * <li>We resolve image filename from the given url.</li>
	 * <li>The final destination of the image will be in {@link VutaImage#getDefaultStorageDir()}
	 * so you need to set this up otherwise we save it in the sdcard</li>
	 * </ul>
	 *  </p>
	 * 
	 * @param url - {@link String} - The image source url 
	 * @param callback - {@link ImageDownloadCallback} - The callback to be fired during the download progress 
	 */
	public static void download( String url, final ImageDownloadCallback callback ){
		new VutaImageDownloader( url ).download( callback );
	}
	
	/**
	 * Downloads a single image give the image url  and the filename
	 * <p> </p>
	 * @param url - {@link String} - The image source
	 * @param filename - {@link String} - Final destination of the image
	 * @param callback - {@link ImageDownloadCallback} - The callback to be fired during the download progress 
	 */
	public static void download( String url, String filename, final ImageDownloadCallback callback ){
		new VutaImageDownloader( url, filename ).download( callback );
	}
	
	/**
	 * Downloads a single image give the image object 
	 * <p> </p>
	 * @param image - {@link VutaImageItem} - An object with url and filename set 
	 * @param callback {@link ImageDownloadCallback} - The callback to be fired during the download progress 
	 */
	public static void download( VutaImageItem image, final ImageDownloadCallback callback ){
		new VutaImageDownloader(image.url, image.filename ).download( callback );
	}

	/**
	 * Downloads multiple images with a callback triggered when all the images are downloaded 
	 * <p>
	 * <ul>
	 * <li> {@link ImagesDownloadCallback#onError(VutaImageItem)} - Triggered when an error is encountered during
	 * 		an image download </li>
	 * </li> {@link ImagesDownloadCallback#done()} - triggered after all the images are download - with/ without errors</li>
	 * </ul>
	 * </p>
	 * @param images {@link List} - A list of images passed in as a collection of {@link VutaImageItem}
	 * @param callback {@link ImagesDownloadCallback} - The callback to be fired during the download progress and done 
	 */
	public static void download( List<VutaImageItem> images, final ImagesDownloadCallback callback ){
		new VutaImagesDownloader( images ).download( callback );
	}
	
	/**
	 * Downloads multiple images with a callback triggered in these states:
	 * <p>
	 * <ul>
	 * <li> {@link EachImageDownloadCallback#onProgress(VutaImageItem, boolean)} - Triggered when a single image 
	 * 		has been downloaded completely - with or without errors <li>
	 * <li> {@link EachImageDownloadCallback#onError(VutaImageItem)} - Triggered when an error is encountered during
	 * 		an image download </li>
	 * </li> {@link EachImageDownloadCallback#done()} - triggered after all the images are download - with/ without errors</li>
	 * </ul>
	 * </p>
	 * 
	 * @param images {@link List} - A list of images passed in as a collection of {@link VutaImageItem}
	 * @param callback {@link EachImageDownloadCallback} - The callback to be fired during the download progress and done 
	 */
	public static void download( List<VutaImageItem> images, final EachImageDownloadCallback callback ){
		new VutaImagesDownloader( images ).download( callback );
	}
}