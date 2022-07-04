package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PersonalTrainerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalTrainerDTO.class);
        PersonalTrainerDTO personalTrainerDTO1 = new PersonalTrainerDTO();
        personalTrainerDTO1.setId(1L);
        PersonalTrainerDTO personalTrainerDTO2 = new PersonalTrainerDTO();
        assertThat(personalTrainerDTO1).isNotEqualTo(personalTrainerDTO2);
        personalTrainerDTO2.setId(personalTrainerDTO1.getId());
        assertThat(personalTrainerDTO1).isEqualTo(personalTrainerDTO2);
        personalTrainerDTO2.setId(2L);
        assertThat(personalTrainerDTO1).isNotEqualTo(personalTrainerDTO2);
        personalTrainerDTO1.setId(null);
        assertThat(personalTrainerDTO1).isNotEqualTo(personalTrainerDTO2);
    }
}
