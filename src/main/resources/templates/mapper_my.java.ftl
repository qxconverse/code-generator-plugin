package ${package.Mapper};

import ${package.Entity}.${entity};
import com.ke.shequ.core.OrderSortItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} {

    /**
     * 插入
     *
     * @param addEntity
     * @return bool
     */
    int insert(@Param("addEntity") ${entity} addEntity);

    /**
     * 更新
     *
     * @param updateEntity
     * @param whereEntity
     * @return bool
     */
    boolean update(@Param("updateEntity") ${entity} updateEntity, @Param("whereEntity") ${entity} whereEntity);


    /**
     * 获取一个
     *
     * @param whereEntity
     * @return bool
     */
    ${entity} selectOne(@Param("whereEntity") ${entity} whereEntity);

    /**
     * 获取所有
     *
     * @param whereEntity
     * @return
     */
    List<${entity}> listAll(@Param("whereEntity") ${entity} whereEntity);


     /**
      * 通过条件排序
      *
      * @param whereEntity
      * @param orderSort
      * @return
      */
    List<${entity}> listAndSort(@Param("whereEntity") ${entity} whereEntity,
                                @Param("orderSort") List<OrderSortItem> orderSort);
}
</#if>
