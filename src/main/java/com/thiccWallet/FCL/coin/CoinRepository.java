package com.thiccWallet.FCL.coin;

//import com.FCL.quizzard.common.data.CrudDAO;
import com.thiccWallet.FCL.common.CrudDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO Refactor to use Spring Data

@Repository
public class CoinRepository implements CrudDAO<Coin> {

    public List<Flashcard> findCardsByCreatorId(String creatorId) {
        return null;
    }

    @Override
    public Flashcard save(Flashcard newCard) {
        return null;
    }

    @Override
    public List<Flashcard> findAll() {
        return null;
    }

    @Override
    public Flashcard findById(String cardId) {
        return null;
    }

    @Override
    public boolean update(Flashcard updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

}
