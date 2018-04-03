package src.main.java.com.cesec.smartcity.entity;

/**
 * <p>文件名称: com.cesec.smartcity.entity</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.entity</p >
 * <p>完成日期: 2018/4/3</p >
 *
 * @author 武庭伟
 * @version 1.0
 */
public class Table {
    private String tableName;
    private String dbEngine;
    private String charset;
    private boolean deleteExist;
    private String comment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbEngine() {
        return dbEngine;
    }

    public void setDbEngine(String dbEngine) {
        this.dbEngine = dbEngine;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isDeleteExist() {
        return deleteExist;
    }

    public void setDeleteExist(boolean deleteExist) {
        this.deleteExist = deleteExist;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
