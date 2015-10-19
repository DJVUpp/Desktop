package SnippingTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class ImgurUploader implements UploadListener
{
    private static final Gson gson = new Gson();
    private static final String IMGUR_URI = "https://api.imgur.com/3";
    private static final String CLIENT_ID = "6311d570cd54953";

    private ImgurResponseListener listener;
    
    private File tempFile;
    

    public void upload(BufferedImage image, ImgurResponseListener listener)
    {
        float quality = Preferences.getInstance().getUploadQuality();
        if(quality < 1f)
         //   image = ImageUtilities.compressImage(image, quality);
        
        tempFile = saveTemporarily(image);
        upload(tempFile, listener);
//        if(Preferences.getInstance().getFTPUploadAlways() && Preferences.getInstance().isFTPReady())
//           // new SimpleFTPUploader(tempFile);
    }

    public void upload(File imageFile, ImgurResponseListener listener)
    {
        this.listener = listener;
//       // SimpleFileUploader uploader = new SimpleFileUploader(
//            IMGUR_URI + "/image.json",
//            imageFile,
//            Config.STPP_USER_AGENT,
//            CLIENT_ID);
//        uploader.addField("description", "Uploaded via " + Config.WEBSITE_URL);
//        uploader.uploadAsync(this);
    }

    @Override
    public void onUploadSuccess(String content)
    {
        ImgurResponse response = null;

        try
        {
            response = gson.fromJson(content, ImgurResponse.class);
        } catch (JsonSyntaxException ex)
        {
            Logger.Log(ex);
            listener.onImgurResponseFail(response);
        }

        if (response != null && response.wasSuccessful())
        {
            ImgurImage uploadedImage = gson.fromJson(response.getRawData(), ImgurImage.class);
            if (uploadedImage != null)
            {
                listener.onImgurResponseSuccess(uploadedImage);
            } else
            {
                listener.onImgurResponseFail(response);
            }
        } else
        {
            listener.onImgurResponseFail(response);
        }
    }

    @Override
    public void onUploadFail(int statusCode, String reason)
    {
        System.out.println(statusCode + ": Failed to upload image: " + reason);
        listener.onImgurResponseFail(null);
    }

    private File saveTemporarily(BufferedImage image)
    {
        try
        {
            File file = File.createTempFile("stpp-", "-snip.png");
            ImageIO.write(image, "png", file);

            return file;
        } catch (IOException e)
        {
            Logger.Log(e);
            e.printStackTrace();
        }
        return null;
    }
}
