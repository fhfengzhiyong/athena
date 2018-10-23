package com.straw.maker.run;

import java.sql.SQLException;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/9
 */
public interface Task {
    void exec() throws SQLException;
}
