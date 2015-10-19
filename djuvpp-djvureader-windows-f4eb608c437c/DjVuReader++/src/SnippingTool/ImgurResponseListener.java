package SnippingTool;

public interface ImgurResponseListener
{
    public void onImgurResponseSuccess(ImgurImage uploadedImage);

    public void onImgurResponseFail(ImgurResponse response);
}
