package ru.bobrova.services;

import org.springframework.stereotype.Service;
import ru.bobrova.models.dao.HistoryDAO;

/**
 * Created by Tanusha on 14/03/2017.
 */
@Service
public class HistoryService implements IHistoryService {
    private HistoryDAO historyDAO;

    @Override
    public boolean insertComment(int id_book, int id_user){
        return historyDAO.insertComment(id_book, id_user);
    }
}
