package com.straw.maker.run;

import com.straw.maker.dao.DataBaseRepository;
import com.straw.maker.run.exec.*;
import com.straw.maker.utils.ColumnProperties;
import com.straw.maker.utils.EntityBean;
import com.straw.maker.utils.NameConverter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/8
 */
@Component
public class Runner implements ApplicationRunner, Ordered {
    @Autowired
    DataBaseRepository dataBase;
    Logger logger = LoggerFactory.getLogger(Runner.class);

    /**
     * 加载资源
     * 处理任务
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        long start = System.currentTimeMillis();
        BaseConfig baseConfig = new BaseConfig();
        baseConfig.setEntityPath("F:/code-src/src/");
        Connection connection = dataBase.queryDb();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tablesResultSet = metaData.getTables(null, null, null, null);
        List<String> listTables = new ArrayList<String>();

        while (tablesResultSet.next()) {
            String tableName = tablesResultSet.getString(3);
            listTables.add(tableName);
        }
        String sql = " select * from ";
        //提交任务
        ExecutorService service = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        CountDownLatch latch = new CountDownLatch(listTables.size() * 6);
        Map<String, Object> data = new HashMap<>();

        for (int i = 0; i < listTables.size(); i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql + listTables.get(i) + " limit 1 ");
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            //总列数
            int columnCount = resultSetMetaData.getColumnCount();
            String className = NameConverter.toJavaCase(listTables.get(i));
            EntityBean entityBean = initEntityBean(className, metaData, resultSetMetaData, listTables.get(i), columnCount);
            List<ColumnProperties> list = new ArrayList<>();
            data.put("author", "fengzy");
            data.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            data.put("package", "com.chejiyang.client");
            data.put("start", "${start}");
            data.put("jh", "#");
            data.put("end", "${end}");
            data.put("datetime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            for (int e = 0; e < columnCount; e++) {
                ColumnProperties columnProperties = new ColumnProperties();
                columnProperties.setColumnNameLower(NameConverter.dealLine(entityBean.getColumnNames()[e]));
                columnProperties.setSimpleJavaType(sqlType2JavaType(entityBean.getColTypes()[e]));
                columnProperties.setColumnName(firsetLetterUpper(columnProperties.getColumnNameLower()));
                columnProperties.setOriginalColumnName(entityBean.getColumnNames()[e]);
                columnProperties.setRemarks(entityBean.getRemarks()[e]);
                columnProperties.setColType(entityBean.getColTypes()[e]);
                list.add(columnProperties);
            }
            data.put("columns", list);
            data.put("className", entityBean.getClassName());
            data.put("pk", NameConverter.dealLine(entityBean.getPrimaryKey()));
            data.put("table", listTables.get(i));
            data.put("entity", entityBean);
            entityBean.setData(data);
            entityBean.getData().put("module", "pos");
            service.submit(new EntityExector(baseConfig, entityBean, latch));
            service.submit(new RepositoryExector(baseConfig, entityBean, latch));
            service.submit(new DocExector(baseConfig, entityBean, latch));
            service.submit(new ControllerExector(baseConfig, entityBean, latch));
            service.submit(new ServiceExector(baseConfig, entityBean, latch));
            service.submit(new ServiceImplExector(baseConfig, entityBean, latch));
            service.submit(new MapperExector(baseConfig, entityBean, latch));

        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println(new BigDecimal((end - start)).divide(new BigDecimal(1000)).setScale(2, RoundingMode.HALF_UP) + "秒");
    }

    public static String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("integer")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("double") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    public EntityBean initEntityBean(String className, DatabaseMetaData dbmd, ResultSetMetaData rsmd, String tb, int size) {
        EntityBean entityBean = new EntityBean();
        entityBean.setClassName(firsetLetterUpper(className));
        String[] colnames = new String[size], colTypes = new String[size], remarks = new String[size];
        entityBean.setColTypes(colTypes);
        entityBean.setColumnNames(colnames);
        entityBean.setRemarks(remarks);
        try {
            ResultSet columnSet = dbmd.getColumns(null, "%", tb, "%");
            int k = 0;
            while (columnSet.next()) {
                String columnComment = columnSet.getString("REMARKS");
                remarks[k] = columnComment;
                k++;
            }
            ResultSet _ResultSet = dbmd.getPrimaryKeys(null, null, tb);
            if (_ResultSet.next()) {
                entityBean.setPrimaryKey(_ResultSet.getObject(4) + "");
            }
            for (int i = 0; i < size; i++) {

                colnames[i] = rsmd.getColumnName(i + 1);
                String jdbcType = rsmd.getColumnTypeName(i + 1);
                if (jdbcType.equalsIgnoreCase("int")) {
                    jdbcType = "INTEGER";
                }
                if (jdbcType.equalsIgnoreCase("DATETIME")) {
                    jdbcType = "TIMESTAMP";
                }
                colTypes[i] = jdbcType;
                if (colnames[i].equalsIgnoreCase(entityBean.getPrimaryKey())) {
                    entityBean.setPrimaryKeyType(colTypes[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityBean;
    }

    /**
     * @param str
     * @return
     */
    public static String firsetLetterUpper(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
