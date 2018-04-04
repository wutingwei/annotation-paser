package src.main.java.com.cesec.smartcity.model;


import src.main.java.com.cesec.smartcity.annotation.COLUMNTYPE;
import src.main.java.com.cesec.smartcity.annotation.ColumnDef;
import src.main.java.com.cesec.smartcity.annotation.TableDef;

/**
 * <p>文件名称: com.cesec.smartcity.model</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.model</p >
 * <p>完成日期: 2018/4/3</p >
 *
 * @author 武庭伟
 * @version 1.0
 */

@TableDef(value = "bizNews")
public class News {

    /**
     * 新闻ID
     */
    @ColumnDef(pk = true,nullable = false,comment = "新闻ID")
    private int newsId;
    /**
     * 新闻标题
     */
    @ColumnDef(comment = "新闻标题",length = 512)
    private String newsTitle;
    /**
     * 新闻副标题
     */
    @ColumnDef(comment = "新闻副标题",length = 512)
    private String newsShortTitle;
    /**
     * 添加时间
     */
    @ColumnDef(comment = "添加时间",length = 20)
    private String newsAddTime;
    /**
     * 关键字
     */
    @ColumnDef(comment = "关键字",length = 512)
    private String newsKeyword;
    /**
     * 新闻摘要
     */
    @ColumnDef(comment = "新闻摘要",length = 512)
    private String newsAbstract;
    /**
     * 新闻作者
     */
    @ColumnDef(comment = "新闻作者",length = 30)
    private String newsAuthor;
    /**
     * 新闻类型
     */
    @ColumnDef(comment = "新闻类型",type = COLUMNTYPE.TINYINT)
    private String newsType;
    /**
     * 新闻内容
     */
    @ColumnDef(comment = "新闻内容",length = 3000)
    private String newsContent;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsShortTitle() {
        return newsShortTitle;
    }

    public void setNewsShortTitle(String newsShortTitle) {
        this.newsShortTitle = newsShortTitle;
    }

    public String getNewsAddTime() {
        return newsAddTime;
    }

    public void setNewsAddTime(String newsAddTime) {
        this.newsAddTime = newsAddTime;
    }

    public String getNewsKeyword() {
        return newsKeyword;
    }

    public void setNewsKeyword(String newsKeyword) {
        this.newsKeyword = newsKeyword;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
