package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PersonalTrainer;
import com.mycompany.myapp.repository.PersonalTrainerRepository;
import com.mycompany.myapp.service.dto.PersonalTrainerDTO;
import com.mycompany.myapp.service.mapper.PersonalTrainerMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PersonalTrainerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PersonalTrainerResourceIT {

    private static final Integer DEFAULT_PROFILE_ID = 1;
    private static final Integer UPDATED_PROFILE_ID = 2;

    private static final Float DEFAULT_YEAR_EXPERIENCE = 1F;
    private static final Float UPDATED_YEAR_EXPERIENCE = 2F;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK_URL = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_URL = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM_URL = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM_URL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_JOIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOIN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ZALO_URL = "AAAAAAAAAA";
    private static final String UPDATED_ZALO_URL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_HIRE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HIRE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/personal-trainers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PersonalTrainerRepository personalTrainerRepository;

    @Autowired
    private PersonalTrainerMapper personalTrainerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonalTrainerMockMvc;

    private PersonalTrainer personalTrainer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalTrainer createEntity(EntityManager em) {
        PersonalTrainer personalTrainer = new PersonalTrainer()
            .profile_id(DEFAULT_PROFILE_ID)
            .year_experience(DEFAULT_YEAR_EXPERIENCE)
            .description(DEFAULT_DESCRIPTION)
            .facebook_url(DEFAULT_FACEBOOK_URL)
            .instagram_url(DEFAULT_INSTAGRAM_URL)
            .join_date(DEFAULT_JOIN_DATE)
            .zalo_url(DEFAULT_ZALO_URL)
            .hire_date(DEFAULT_HIRE_DATE);
        return personalTrainer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalTrainer createUpdatedEntity(EntityManager em) {
        PersonalTrainer personalTrainer = new PersonalTrainer()
            .profile_id(UPDATED_PROFILE_ID)
            .year_experience(UPDATED_YEAR_EXPERIENCE)
            .description(UPDATED_DESCRIPTION)
            .facebook_url(UPDATED_FACEBOOK_URL)
            .instagram_url(UPDATED_INSTAGRAM_URL)
            .join_date(UPDATED_JOIN_DATE)
            .zalo_url(UPDATED_ZALO_URL)
            .hire_date(UPDATED_HIRE_DATE);
        return personalTrainer;
    }

    @BeforeEach
    public void initTest() {
        personalTrainer = createEntity(em);
    }

    @Test
    @Transactional
    void createPersonalTrainer() throws Exception {
        int databaseSizeBeforeCreate = personalTrainerRepository.findAll().size();
        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);
        restPersonalTrainerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalTrainer testPersonalTrainer = personalTrainerList.get(personalTrainerList.size() - 1);
        assertThat(testPersonalTrainer.getProfile_id()).isEqualTo(DEFAULT_PROFILE_ID);
        assertThat(testPersonalTrainer.getYear_experience()).isEqualTo(DEFAULT_YEAR_EXPERIENCE);
        assertThat(testPersonalTrainer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPersonalTrainer.getFacebook_url()).isEqualTo(DEFAULT_FACEBOOK_URL);
        assertThat(testPersonalTrainer.getInstagram_url()).isEqualTo(DEFAULT_INSTAGRAM_URL);
        assertThat(testPersonalTrainer.getJoin_date()).isEqualTo(DEFAULT_JOIN_DATE);
        assertThat(testPersonalTrainer.getZalo_url()).isEqualTo(DEFAULT_ZALO_URL);
        assertThat(testPersonalTrainer.getHire_date()).isEqualTo(DEFAULT_HIRE_DATE);
    }

    @Test
    @Transactional
    void createPersonalTrainerWithExistingId() throws Exception {
        // Create the PersonalTrainer with an existing ID
        personalTrainer.setId(1L);
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        int databaseSizeBeforeCreate = personalTrainerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalTrainerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPersonalTrainers() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        // Get all the personalTrainerList
        restPersonalTrainerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalTrainer.getId().intValue())))
            .andExpect(jsonPath("$.[*].profile_id").value(hasItem(DEFAULT_PROFILE_ID)))
            .andExpect(jsonPath("$.[*].year_experience").value(hasItem(DEFAULT_YEAR_EXPERIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].facebook_url").value(hasItem(DEFAULT_FACEBOOK_URL)))
            .andExpect(jsonPath("$.[*].instagram_url").value(hasItem(DEFAULT_INSTAGRAM_URL)))
            .andExpect(jsonPath("$.[*].join_date").value(hasItem(DEFAULT_JOIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].zalo_url").value(hasItem(DEFAULT_ZALO_URL)))
            .andExpect(jsonPath("$.[*].hire_date").value(hasItem(DEFAULT_HIRE_DATE.toString())));
    }

    @Test
    @Transactional
    void getPersonalTrainer() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        // Get the personalTrainer
        restPersonalTrainerMockMvc
            .perform(get(ENTITY_API_URL_ID, personalTrainer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personalTrainer.getId().intValue()))
            .andExpect(jsonPath("$.profile_id").value(DEFAULT_PROFILE_ID))
            .andExpect(jsonPath("$.year_experience").value(DEFAULT_YEAR_EXPERIENCE.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.facebook_url").value(DEFAULT_FACEBOOK_URL))
            .andExpect(jsonPath("$.instagram_url").value(DEFAULT_INSTAGRAM_URL))
            .andExpect(jsonPath("$.join_date").value(DEFAULT_JOIN_DATE.toString()))
            .andExpect(jsonPath("$.zalo_url").value(DEFAULT_ZALO_URL))
            .andExpect(jsonPath("$.hire_date").value(DEFAULT_HIRE_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPersonalTrainer() throws Exception {
        // Get the personalTrainer
        restPersonalTrainerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPersonalTrainer() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();

        // Update the personalTrainer
        PersonalTrainer updatedPersonalTrainer = personalTrainerRepository.findById(personalTrainer.getId()).get();
        // Disconnect from session so that the updates on updatedPersonalTrainer are not directly saved in db
        em.detach(updatedPersonalTrainer);
        updatedPersonalTrainer
            .profile_id(UPDATED_PROFILE_ID)
            .year_experience(UPDATED_YEAR_EXPERIENCE)
            .description(UPDATED_DESCRIPTION)
            .facebook_url(UPDATED_FACEBOOK_URL)
            .instagram_url(UPDATED_INSTAGRAM_URL)
            .join_date(UPDATED_JOIN_DATE)
            .zalo_url(UPDATED_ZALO_URL)
            .hire_date(UPDATED_HIRE_DATE);
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(updatedPersonalTrainer);

        restPersonalTrainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, personalTrainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isOk());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
        PersonalTrainer testPersonalTrainer = personalTrainerList.get(personalTrainerList.size() - 1);
        assertThat(testPersonalTrainer.getProfile_id()).isEqualTo(UPDATED_PROFILE_ID);
        assertThat(testPersonalTrainer.getYear_experience()).isEqualTo(UPDATED_YEAR_EXPERIENCE);
        assertThat(testPersonalTrainer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPersonalTrainer.getFacebook_url()).isEqualTo(UPDATED_FACEBOOK_URL);
        assertThat(testPersonalTrainer.getInstagram_url()).isEqualTo(UPDATED_INSTAGRAM_URL);
        assertThat(testPersonalTrainer.getJoin_date()).isEqualTo(UPDATED_JOIN_DATE);
        assertThat(testPersonalTrainer.getZalo_url()).isEqualTo(UPDATED_ZALO_URL);
        assertThat(testPersonalTrainer.getHire_date()).isEqualTo(UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    void putNonExistingPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, personalTrainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePersonalTrainerWithPatch() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();

        // Update the personalTrainer using partial update
        PersonalTrainer partialUpdatedPersonalTrainer = new PersonalTrainer();
        partialUpdatedPersonalTrainer.setId(personalTrainer.getId());

        partialUpdatedPersonalTrainer
            .year_experience(UPDATED_YEAR_EXPERIENCE)
            .description(UPDATED_DESCRIPTION)
            .facebook_url(UPDATED_FACEBOOK_URL)
            .join_date(UPDATED_JOIN_DATE)
            .zalo_url(UPDATED_ZALO_URL)
            .hire_date(UPDATED_HIRE_DATE);

        restPersonalTrainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPersonalTrainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPersonalTrainer))
            )
            .andExpect(status().isOk());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
        PersonalTrainer testPersonalTrainer = personalTrainerList.get(personalTrainerList.size() - 1);
        assertThat(testPersonalTrainer.getProfile_id()).isEqualTo(DEFAULT_PROFILE_ID);
        assertThat(testPersonalTrainer.getYear_experience()).isEqualTo(UPDATED_YEAR_EXPERIENCE);
        assertThat(testPersonalTrainer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPersonalTrainer.getFacebook_url()).isEqualTo(UPDATED_FACEBOOK_URL);
        assertThat(testPersonalTrainer.getInstagram_url()).isEqualTo(DEFAULT_INSTAGRAM_URL);
        assertThat(testPersonalTrainer.getJoin_date()).isEqualTo(UPDATED_JOIN_DATE);
        assertThat(testPersonalTrainer.getZalo_url()).isEqualTo(UPDATED_ZALO_URL);
        assertThat(testPersonalTrainer.getHire_date()).isEqualTo(UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    void fullUpdatePersonalTrainerWithPatch() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();

        // Update the personalTrainer using partial update
        PersonalTrainer partialUpdatedPersonalTrainer = new PersonalTrainer();
        partialUpdatedPersonalTrainer.setId(personalTrainer.getId());

        partialUpdatedPersonalTrainer
            .profile_id(UPDATED_PROFILE_ID)
            .year_experience(UPDATED_YEAR_EXPERIENCE)
            .description(UPDATED_DESCRIPTION)
            .facebook_url(UPDATED_FACEBOOK_URL)
            .instagram_url(UPDATED_INSTAGRAM_URL)
            .join_date(UPDATED_JOIN_DATE)
            .zalo_url(UPDATED_ZALO_URL)
            .hire_date(UPDATED_HIRE_DATE);

        restPersonalTrainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPersonalTrainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPersonalTrainer))
            )
            .andExpect(status().isOk());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
        PersonalTrainer testPersonalTrainer = personalTrainerList.get(personalTrainerList.size() - 1);
        assertThat(testPersonalTrainer.getProfile_id()).isEqualTo(UPDATED_PROFILE_ID);
        assertThat(testPersonalTrainer.getYear_experience()).isEqualTo(UPDATED_YEAR_EXPERIENCE);
        assertThat(testPersonalTrainer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPersonalTrainer.getFacebook_url()).isEqualTo(UPDATED_FACEBOOK_URL);
        assertThat(testPersonalTrainer.getInstagram_url()).isEqualTo(UPDATED_INSTAGRAM_URL);
        assertThat(testPersonalTrainer.getJoin_date()).isEqualTo(UPDATED_JOIN_DATE);
        assertThat(testPersonalTrainer.getZalo_url()).isEqualTo(UPDATED_ZALO_URL);
        assertThat(testPersonalTrainer.getHire_date()).isEqualTo(UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, personalTrainerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPersonalTrainer() throws Exception {
        int databaseSizeBeforeUpdate = personalTrainerRepository.findAll().size();
        personalTrainer.setId(count.incrementAndGet());

        // Create the PersonalTrainer
        PersonalTrainerDTO personalTrainerDTO = personalTrainerMapper.toDto(personalTrainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPersonalTrainerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(personalTrainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PersonalTrainer in the database
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePersonalTrainer() throws Exception {
        // Initialize the database
        personalTrainerRepository.saveAndFlush(personalTrainer);

        int databaseSizeBeforeDelete = personalTrainerRepository.findAll().size();

        // Delete the personalTrainer
        restPersonalTrainerMockMvc
            .perform(delete(ENTITY_API_URL_ID, personalTrainer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonalTrainer> personalTrainerList = personalTrainerRepository.findAll();
        assertThat(personalTrainerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
