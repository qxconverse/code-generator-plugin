<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}"/>
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}"/>
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}"/>
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="BaseColumns">
        <#list table.fields as field>
        <#if  field_has_next>
        ${field.name},
        <#else>
        ${field.name}
        </#if>
        </#list>
    </sql>
</#if>

    <sql id="WhereFilter">
        <where>
            <#list table.fields as field>
            <if test="whereEntity.${field.propertyName} != null and whereEntity.${field.propertyName} != ''">
                and ${field.name}=<#noparse>#{</#noparse>whereEntity.${field.propertyName}<#noparse>}</#noparse>
            </if>
            </#list>
        </where>
    </sql>

    <select id="selectOne" resultType="${package.Entity}.${entity}">
        select
        <include refid="BaseColumns"/>
        from ${table.name}
        <include refid="WhereFilter"/>
    </select>

    <select id="listAll" resultType="${package.Entity}.${entity}">
        select
        <include refid="BaseColumns"/>
        from ${table.name}
        <include refid="WhereFilter"/>
    </select>

    <insert id="insert" parameterType="${package.Entity}.${entity}" useGeneratedKeys="true"
            keyProperty="id" keyColumn="id">
        INSERT INTO ${table.name}
        (
        <#list table.fields as field>
            <#if field.name != "id">
            <#if  field_has_next>
            ${field.name},
            <#else>
            ${field.name}
            </#if>
            </#if>
        </#list>
        )
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list table.fields as field>
            <#if field.name != "id">
            <#if  field_has_next>
            <#noparse>#{</#noparse>addEntity.${field.propertyName}<#noparse>}</#noparse>,
            <#else>
            <#noparse>#{</#noparse>addEntity.${field.propertyName}<#noparse>}</#noparse>
            </#if>
            </#if>
            </#list>
        </trim>
    </insert>

    <update id="update" parameterType="${package.Entity}.${entity}">
        update ${table.name}
        <set>
        <#list table.fields as field>
            <#if field.name !="id" && field.name !="create_time">
                <if test="updateEntity.${field.propertyName} != null">
                    ${field.name}=<#noparse>#{</#noparse>updateEntity.${field.propertyName}<#noparse>}</#noparse>,
                </if>
            </#if>
        </#list>
        </set>
        <include refid="WhereFilter"/>
    </update>

</mapper>
