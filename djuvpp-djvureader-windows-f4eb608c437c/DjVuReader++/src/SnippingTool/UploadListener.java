package SnippingTool;

public interface UploadListener
{
    public void onUploadSuccess(String content);

    public void onUploadFail(int httpStatus, String reason);
}
