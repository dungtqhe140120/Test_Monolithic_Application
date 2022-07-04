package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonalTrainerMapperTest {

    private PersonalTrainerMapper personalTrainerMapper;

    @BeforeEach
    public void setUp() {
        personalTrainerMapper = new PersonalTrainerMapperImpl();
    }
}
