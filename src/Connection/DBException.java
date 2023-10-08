package Connection;

import java.sql.SQLException;

public class DBException extends RuntimeException {
    private static final long serialVersionUID = 1l;
    public DBException(String msg){
        super(msg);
    }
}
