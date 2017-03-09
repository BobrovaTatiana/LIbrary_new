package ru.bobrova.models.dao;

import marshaling.Lib_history;
import ru.bobrova.models.pojo.History;

import java.sql.*;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Tanusha on 27/02/2017.
 */
public class HistoryDAO {
    public Lib_history loadHistory (Connection con) throws SQLException, ClassNotFoundException {
        Lib_history history = new Lib_history();
        history.setHistories("List of history");
        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"public\".\"History\"");
                while (rs.next()) {
                    Integer id = Integer.parseInt(rs.getString("id"));
                    Integer id_user = Integer.parseInt(rs.getString("id_user"));
                    Integer id_book = Integer.parseInt(rs.getString("id_book"));
                    Date date = rs.getDate("date");

                    history.getHistory().add(createHistory(id, id_user, id_book, date));
                }
                rs.close();
                stmt.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public History createHistory(int id, int id_user, int id_book, Date date) throws ParseException {
        History history = new History();
        history.setId(id);
        history.setId_user(id_user);
        history.setId_book(id_book);
        history.setDate_red(date);

        return history;
    }

    public void uploadHistory (Object object, Connection dbConnection) throws SQLException, ClassNotFoundException, InterruptedException {
        Lib_history history = (Lib_history) object;
        String insertTableSQL = "INSERT INTO public.\"History\"(\n" +
                "\t\"id\", \"id_user\", \"id_book\", \"date\")\n" +
                "\tVALUES (?, ?, ?, ?);";
        PreparedStatement prepareStatement = dbConnection.prepareStatement(insertTableSQL);
        dbConnection.setAutoCommit(false);

        for (int i = 0; i < history.getHistory().size(); i++) {
            prepareStatement.setInt(1, history.getHistory().get(i).getId());
            prepareStatement.setInt(2, history.getHistory().get(i).getId_user());
            prepareStatement.setInt(3, history.getHistory().get(i).getId_book());
            prepareStatement.setDate(4, new java.sql.Date(history.getHistory().get(i).getDate_red().getTime()));
            prepareStatement.addBatch();
        }
        prepareStatement.executeBatch();
        dbConnection.commit();
        System.out.println("Record is inserted into History table!");
        prepareStatement.close();
    }
}
