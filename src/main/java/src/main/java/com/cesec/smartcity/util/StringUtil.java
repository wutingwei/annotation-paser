package src.main.java.com.cesec.smartcity.util;

/**
 * <p>文件名称: com.cesec.smartcity.util</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.util</p >
 * <p>完成日期: 2018/4/3</p >
 *
 * @author 武庭伟
 * @version 1.0
 */
public class StringUtil {

    public static boolean isEmpty(String args){
        if(args == null) return true;
        if(args.trim().length() == 0) return true;
        return false;
    }
}
