package Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Main.GUI.*;

public class SQL extends BD {
    public static List<String> RLyear = new ArrayList<>();

    public List<String> SQLText() {
        Statement statementYEAR = null;
        try {
            if (sTestKF.isSelected() && y22.isSelected()) {
                statementYEAR = getConnectionBudget22KF().createStatement();
            } else if (sTestKF.isSelected() && y23.isSelected()) {
                statementYEAR = getConnectionBudget23KF().createStatement();
            } else if (sTestUKT.isSelected() && y22.isSelected()) {
                statementYEAR = getConnectionBudget22UKT().createStatement();
            } else if (sTestUKT.isSelected() && y23.isSelected()) {
                statementYEAR = getConnectionBudget23UKT().createStatement();
            }
            String sqlYEAR = SQLSTATIC;
            ResultSet ResultYEAR = statementYEAR.executeQuery(sqlYEAR);
            System.out.println(sqlYEAR);
            while (ResultYEAR.next()) {
                String ListYEARformRIO = ResultYEAR.getString(SQLSTATICCOLUM);
                RLyear.add(String.join(" ", ListYEARformRIO).trim());
                Collections.sort(RLyear);

            }
            System.out.println("RLyear" + RLyear);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return RLyear;
    }
}
