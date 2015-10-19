package SnippingTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Upload implements ImgurResponseListener
{
//    private static final AnimatedTrayIcon animatedIcon = AnimatedTrayIcon.getDefaultIcon();

    private BufferedImage image;
    private final boolean uploadToreddit;
    private final Save save;

    public Upload(BufferedImage image)
    {   
        this(image, false);
    }

    public Upload(BufferedImage image, boolean uploadToReddit)
    {
        this.image = image;
        this.uploadToreddit = uploadToReddit;
        this.save = new Save();

        upload();
    }

    private void upload()
    {

        int provider = Preferences.getInstance().getPrimaryProvider();
        if(provider == Provider.STPP)
        {
//        	StppUploader uploader = new StppUploader(image);
//        	uploader.upload();
        }
        else
        {
            doBeforeUpload();
        	ImgurUploader uploader = new ImgurUploader();
        	uploader.upload(image, this);
        }
    }
    private void doBeforeUpload()
    {
        if (OperatingSystem.isWindows())
        {
//            new Thread(animatedIcon, "upload-animation").start();
        } else
        {
            SnippingToolPlusPlus.trayIcon.setImage(new ImageIcon(this.getClass().getResource("/images/uploadMac.png")).getImage());
        }
        NotificationManager.getInstance().showNotification("uploading", STNotificationType.INFO);
    }

    private void doAfterUpload()
    {
        if (OperatingSystem.isWindows())
        {
//            if (Upload.animatedIcon != null)
//            {
//                Upload.animatedIcon.stopAnimating();
//            }
        } else
        {
            SnippingToolPlusPlus.trayIcon.setImage(new ImageIcon(this.getClass().getResource("/images/trayIconMac.png")).getImage());
        }
        
        image = null;
    }

    @Override
    public void onImgurResponseSuccess(ImgurImage uploadedImage)
    {
        if (Preferences.getInstance().isAutoSaveEnabled())
        {
            save.saveUpload(image);
            new LinkDataSaver(uploadedImage.getLink(), uploadedImage.getDeleteLink(),
                "upload(" + Preferences.TOTAL_SAVED_UPLOADS + ")");
        }

        if (!uploadToreddit)
        {
           // ClipboardUtilities.setClipboard(uploadedImage.getLink());
            NotificationManager.getInstance().showNotification("upload-done", STNotificationType.SUCCESS);
        } else
        {
           // Browser.openToReddit(uploadedImage.getLink());
            NotificationManager.getInstance().showNotification("upload-done-reddit", STNotificationType.SUCCESS);
        }
       // SoundNotifications.playDing();
        doAfterUpload();
        
//        DBStats.addHistory(uploadedImage.getLink(), uploadedImage.getDeleteLink());
    }

    @Override
    public void onImgurResponseFail(ImgurResponse response)
    {
        NotificationManager.getInstance().showNotification("upload-failed", STNotificationType.ERROR);
        doAfterUpload();
    }

    public static Upload uploadFile(File imageFile, boolean uploadToReddit) throws IOException
    {
        return new Upload(ImageIO.read(imageFile), uploadToReddit);
    }
}
