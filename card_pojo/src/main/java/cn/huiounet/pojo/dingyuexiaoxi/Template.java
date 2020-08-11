package cn.huiounet.pojo.dingyuexiaoxi;

/**
 * @author yd
 * @version 1.0
 * @date 2020/1/11 13:57
 */
import java.util.List;

public class Template {

    private String touser;
    private String template_id;
    private String page;
    private List<TemplateParam> templateParamList;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.touser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.template_id)).append(",");
        buffer.append(String.format("\"page\":\"%s\"", this.page)).append(",");
        buffer.append("\"data\":{");
        TemplateParam param = null;
        for (int i = 0; i < this.templateParamList.size(); i++) {
            param = templateParamList.get(i);
            // 判断是否追加逗号
            if (i < this.templateParamList.size() - 1) {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"},", param.getKey(), param.getValue()));
            } else {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"}", param.getKey(), param.getValue()));
            }
        }
        buffer.append("}");
        buffer.append("}");
        return buffer.toString();
    }

    public List<TemplateParam> getTemplateParamList() {
        return templateParamList;
    }

    public void setTemplateParamList(List<TemplateParam> templateParamList) {
        this.templateParamList = templateParamList;
    }
}

