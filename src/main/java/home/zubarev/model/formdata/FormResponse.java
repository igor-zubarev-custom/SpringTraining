package home.zubarev.model.formdata;

import java.util.List;

/**
 * Created by Igor Zubarev on 12.09.2016.
 */
public class FormResponse {
    private String status;
    private List messageList;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List getMessageList() {
        return messageList;
    }
    public void setMessageList(List messageList) {
        this.messageList = messageList;
    }
}
