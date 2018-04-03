package src.main.java.com.cesec.smartcity.annotation;

import java.lang.annotation.*;

/**
 * <p>文件名称: com.cesec.smartcity.annotation</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.annotation</p >
 * <p>完成日期: 2018/4/3</p >
 *
 * @author 武庭伟
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface TableDef {
    String value() default "";
    String dbengine() default "innodb";
    String charset() default "utf8";
    boolean deleteExist() default false;
    String comment() default "";
}
