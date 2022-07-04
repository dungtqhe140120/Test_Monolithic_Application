package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Trainee;
import com.mycompany.myapp.repository.TraineeRepository;
import com.mycompany.myapp.service.dto.TraineeDTO;
import com.mycompany.myapp.service.mapper.TraineeMapper;
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
 * Integration tests for the {@link TraineeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TraineeResourceIT {

    private static final Integer DEFAULT_PROFILE_ID = 1;
    private static final Integer UPDATED_PROFILE_ID = 2;

    private static final String ENTITY_API_URL = "/api/trainees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private TraineeMapper traineeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTraineeMockMvc;

    private Trainee trainee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trainee createEntity(EntityManager em) {
        Trainee trainee = new Trainee().profile_id(DEFAULT_PROFILE_ID);
        return trainee;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trainee createUpdatedEntity(EntityManager em) {
        Trainee trainee = new Trainee().profile_id(UPDATED_PROFILE_ID);
        return trainee;
    }

    @BeforeEach
    public void initTest() {
        trainee = createEntity(em);
    }

    @Test
    @Transactional
    void createTrainee() throws Exception {
        int databaseSizeBeforeCreate = traineeRepository.findAll().size();
        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);
        restTraineeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traineeDTO)))
            .andExpect(status().isCreated());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeCreate + 1);
        Trainee testTrainee = traineeList.get(traineeList.size() - 1);
        assertThat(testTrainee.getProfile_id()).isEqualTo(DEFAULT_PROFILE_ID);
    }

    @Test
    @Transactional
    void createTraineeWithExistingId() throws Exception {
        // Create the Trainee with an existing ID
        trainee.setId(1L);
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        int databaseSizeBeforeCreate = traineeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraineeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traineeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTrainees() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        // Get all the traineeList
        restTraineeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trainee.getId().intValue())))
            .andExpect(jsonPath("$.[*].profile_id").value(hasItem(DEFAULT_PROFILE_ID)));
    }

    @Test
    @Transactional
    void getTrainee() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        // Get the trainee
        restTraineeMockMvc
            .perform(get(ENTITY_API_URL_ID, trainee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trainee.getId().intValue()))
            .andExpect(jsonPath("$.profile_id").value(DEFAULT_PROFILE_ID));
    }

    @Test
    @Transactional
    void getNonExistingTrainee() throws Exception {
        // Get the trainee
        restTraineeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTrainee() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();

        // Update the trainee
        Trainee updatedTrainee = traineeRepository.findById(trainee.getId()).get();
        // Disconnect from session so that the updates on updatedTrainee are not directly saved in db
        em.detach(updatedTrainee);
        updatedTrainee.profile_id(UPDATED_PROFILE_ID);
        TraineeDTO traineeDTO = traineeMapper.toDto(updatedTrainee);

        restTraineeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, traineeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
        Trainee testTrainee = traineeList.get(traineeList.size() - 1);
        assertThat(testTrainee.getProfile_id()).isEqualTo(UPDATED_PROFILE_ID);
    }

    @Test
    @Transactional
    void putNonExistingTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, traineeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traineeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTraineeWithPatch() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();

        // Update the trainee using partial update
        Trainee partialUpdatedTrainee = new Trainee();
        partialUpdatedTrainee.setId(trainee.getId());

        partialUpdatedTrainee.profile_id(UPDATED_PROFILE_ID);

        restTraineeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrainee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrainee))
            )
            .andExpect(status().isOk());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
        Trainee testTrainee = traineeList.get(traineeList.size() - 1);
        assertThat(testTrainee.getProfile_id()).isEqualTo(UPDATED_PROFILE_ID);
    }

    @Test
    @Transactional
    void fullUpdateTraineeWithPatch() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();

        // Update the trainee using partial update
        Trainee partialUpdatedTrainee = new Trainee();
        partialUpdatedTrainee.setId(trainee.getId());

        partialUpdatedTrainee.profile_id(UPDATED_PROFILE_ID);

        restTraineeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrainee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrainee))
            )
            .andExpect(status().isOk());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
        Trainee testTrainee = traineeList.get(traineeList.size() - 1);
        assertThat(testTrainee.getProfile_id()).isEqualTo(UPDATED_PROFILE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, traineeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTrainee() throws Exception {
        int databaseSizeBeforeUpdate = traineeRepository.findAll().size();
        trainee.setId(count.incrementAndGet());

        // Create the Trainee
        TraineeDTO traineeDTO = traineeMapper.toDto(trainee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraineeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(traineeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Trainee in the database
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTrainee() throws Exception {
        // Initialize the database
        traineeRepository.saveAndFlush(trainee);

        int databaseSizeBeforeDelete = traineeRepository.findAll().size();

        // Delete the trainee
        restTraineeMockMvc
            .perform(delete(ENTITY_API_URL_ID, trainee.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Trainee> traineeList = traineeRepository.findAll();
        assertThat(traineeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
