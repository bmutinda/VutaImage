package net.rebmos.vutaimage;

/**
 * 
 * @author Mutinda Boniface
 *
 */
public class VutaImageItem {
	String filename;
	String url;
	
	public VutaImageItem( String url ) {
		this.url = url;
		this.filename = VutaImageItem.resolveFileName( url );
	}
	
	public VutaImageItem( String url, String filename ) {
		this.filename = filename;
		this.url = url;
	}

	public static String resolveFileName( String url ){
		String name = null;
		try{
			String[] segments = url.split("/");
			name = segments[(segments.length - 1)];
			name = VutaImage.getDefaultStorageDir()+"/"+name;
		}catch( Exception e ){
			e.printStackTrace();
		}
		return name;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String getFilename(){
		return this.filename;
	}
}