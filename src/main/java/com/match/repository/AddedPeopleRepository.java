package com.match.repository;

import com.match.domain.entity.AddedPeople;
import org.springframework.stereotype.Repository;

@Repository
public class AddedPeopleRepository extends AbstractRepositoryFirebase<AddedPeople>{
    public AddedPeopleRepository() {
        super("added_people", AddedPeople.class);
    }
}
