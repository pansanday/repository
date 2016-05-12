package com.git.po;

public class ImageMessage {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private ImageMessage Image;
    
    private String PicUrl;
    private String MediaId;
    private String MsgId;
    private String Content;
    
    public String getToUserName() {
        return ToUserName;
    }
    
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    
    public String getFromUserName() {
        return FromUserName;
    }
    
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    
    public long getCreateTime() {
        return CreateTime;
    }
    
    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }
    
    public String getMsgType() {
        return MsgType;
    }
    
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
    
    public ImageMessage getImage() {
        return Image;
    }

    public void setImage(ImageMessage image) {
        Image = image;
    }

    public String getPicUrl() {
        return PicUrl;
    }
    
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
    
    public String getMediaId() {
        return MediaId;
    }
    
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    
    public String getMsgId() {
        return MsgId;
    }
    
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
    
    public String getContent() {
        return Content;
    }
    
    public void setContent(String content) {
        Content = content;
    }

   
}
