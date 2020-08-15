package cn.sixlab.six.web.vo;

import cn.sixlab.six.web.models.PostInfo;
import lombok.Data;

@Data
public class FullPostInfo extends PostInfo {

    private String tagCodes;
    private String tagNames;

    private String categoryCodes;
    private String categoryNames;

}
