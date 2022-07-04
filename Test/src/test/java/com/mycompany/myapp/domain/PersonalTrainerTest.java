package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PersonalTrainerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalTrainer.class);
        PersonalTrainer personalTrainer1 = new PersonalTrainer();
        personalTrainer1.setId(1L);
        PersonalTrainer personalTrainer2 = new PersonalTrainer();
        personalTrainer2.setId(personalTrainer1.getId());
        assertThat(personalTrainer1).isEqualTo(personalTrainer2);
        personalTrainer2.setId(2L);
        assertThat(personalTrainer1).isNotEqualTo(personalTrainer2);
        personalTrainer1.setId(null);
        assertThat(personalTrainer1).isNotEqualTo(personalTrainer2);
    }
}
