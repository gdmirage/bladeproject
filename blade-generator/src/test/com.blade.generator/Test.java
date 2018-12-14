package com.blade.generator;

import com.blade.generator.util.MysqlConnectionUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/14 10:45
 */
public class Test {

    private static int corePoolSize = 4;
    private static int maxPoolSize = 10;
    private static long keepAliveTime = 40000;

    private static LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque();

    private static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("test-thread : ");
            return t;
        }
    };

    public static void main(String[] args) throws Exception {
        testDataBase();
    }

    private static void testDataBase() {
        try {
            Connection connection = MysqlConnectionUtil.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null,null,"%",null);

            while (tables.next()) {

                String tableName = tables.getString("TABLE_NAME");
                String remarks = tables.getString("REMARKS");

                System.out.println(tableName + " -- " + remarks);

                ResultSet columnRs = databaseMetaData.getColumns(null, null, tableName, null);
                while (columnRs.next()) {
                    String columnName = columnRs.getString("COLUMN_NAME");
                    String dataType = columnRs.getString("TYPE_NAME");
                    System.out.println(columnName + " -- " + dataType);
                }

                System.out.println("-----------------------------------------------");
            }

//            ResultSet rs = databaseMetaData.getColumns(null, "%", "%", "%");
//            while (rs.next()) {
////                System.out.println(rs.getRow());
//                System.out.println(rs.getString("COLUMN_NAME"));
//            }
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testThread(){
        ExecutorService service = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory);

        for (int i = 0; i < 10; i++) {
            Job job = new Job();
            service.submit(job);
        }

        service.shutdown();
    }


    static class Job extends Thread {
        @Override
        public void run() {
            try {
                Connection connection = MysqlConnectionUtil.getConnection();
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ResultSet rs = databaseMetaData.getTables(null,"%","%",null);
                while (rs.next()) {
                    System.out.println(rs.getRow());
                }
                System.out.println(this.getName() + " " + connection);
                if (null != connection) {
                    connection.close();
                }
                System.out.println(this.getName() + " " + connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
