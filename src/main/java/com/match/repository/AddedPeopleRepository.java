package com.match.repository;

import com.match.domain.entity.AddedPeople;

public class AddedPeopleRepository extends AbstractRepositoryFirebase<AddedPeople>{
    public AddedPeopleRepository() {
        super("addedPeople", AddedPeople.class);
    }
}
