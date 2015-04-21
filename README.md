VutaImage
=========

Android Library that makes it easy to download images in Android with a simple and straight forward api.

=========
For a complete documentation head to 
<a href="http://mutindaz.github.io/VutaImage/">http://mutindaz.github.io/VutaImage/</a>

Example1
``` java 
final String imageUrl = "http://myexample.com/img/sample1.png";
VutaImage.download( imageUrl, new ImageDownloadCallback() {
   @Override
   public void progress(int elapsed, int totalSize) {
      Log.e( TAG , "Image download progress = "+elapsed+" out of "+totalSize );
   }

   @Override
   public void done(boolean success) {
      Log.e( TAG , "Image download done with success = "+success );
   }
});
```
