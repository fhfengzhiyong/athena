package com.straw.maker.utils;

public class NameConverter {
    /**
     * 数据库命名方式转换成java的命名方式
     *
     * @param s
     * @return
     */
    public static String toJavaCase(String s) {
        if (s == null || s.trim().length() == 0) {
            return s;
        }
        // 去掉第一个下划线及其前面的字母
        //s = s.substring(s.indexOf("_") + 1);
        StringBuffer sb = new StringBuffer();
        String[] array = s.split("_");
        boolean firstTime = true;
        for (String e : array) {
            if (e.length() == 0) {
                continue;
            } else if (e.length() == 1) {
                sb.append(!firstTime ? e.toUpperCase() : e);
            } else {
                sb.append(!firstTime ? (e.substring(0, 1).toUpperCase() + e.substring(1)) : e);
            }
            firstTime = false;
        }
        return sb.toString();
    }

    public static String dealLine(String tableName) {
        // 处理下划线情况，把下划线后一位的字母变大写；  
        tableName = dealName(tableName);
        return tableName;
    }

    //下划线后一位字母大写
    private static String dealName(String columnName) {
        //System.out.println("字段名称:"+columnName);
        if (columnName == null) {
            return "xxx";
        }
        if (columnName.contains("_")) {
            StringBuffer names = new StringBuffer();
            String arrayName[] = columnName.split("_");
            names.append(arrayName[0]);
            for (int i = 1; i < arrayName.length; i++) {
                String arri = arrayName[i];
                //System.out.println(arri+"  长度为:"+arri.length());
                String tmp = arri.substring(0, 1).toUpperCase() + arri.substring(1, arri.length());
                names.append(tmp);
            }
            columnName = names.toString();
        }
        return columnName;
    }


    /**
     * Java命名方式转换成数据库的命名方式
     *
     * @param s
     * @return
     */
    public static String toDbCase(String s) {
        if (s == null || s.trim().length() == 0)
            return s;
        char[] chars = s.toCharArray();
        boolean firstTime = true;
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (c >= 'A' && c <= 'Z') {
                char c1 = (char) (c + 32);
                sb.append(firstTime ? c1 : "_" + c1);
            } else
                sb.append(c);
            firstTime = false;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // System.out
        // .println(toDbCase("theyBeganToHumWhenSeeingJacksonWalkIntoTheHall"));
        // System.out
        // .println(toJavaCase(toDbCase("theyBeganToHumWhenSeeingJacksonWalkIntoTheHall")));
        StringBuffer sb = new StringBuffer("sdsdfds1");
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(NameConverter.toJavaCase("ww_ww"));
    }
}
