package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.PersonalTrainerRepository;
import com.mycompany.myapp.service.PersonalTrainerService;
import com.mycompany.myapp.service.dto.PersonalTrainerDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PersonalTrainer}.
 */
@RestController
@RequestMapping("/api")
public class PersonalTrainerResource {

    private final Logger log = LoggerFactory.getLogger(PersonalTrainerResource.class);

    private static final String ENTITY_NAME = "personalTrainer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonalTrainerService personalTrainerService;

    private final PersonalTrainerRepository personalTrainerRepository;

    public PersonalTrainerResource(PersonalTrainerService personalTrainerService, PersonalTrainerRepository personalTrainerRepository) {
        this.personalTrainerService = personalTrainerService;
        this.personalTrainerRepository = personalTrainerRepository;
    }

    /**
     * {@code POST  /personal-trainers} : Create a new personalTrainer.
     *
     * @param personalTrainerDTO the personalTrainerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personalTrainerDTO, or with status {@code 400 (Bad Request)} if the personalTrainer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personal-trainers")
    public ResponseEntity<PersonalTrainerDTO> createPersonalTrainer(@RequestBody PersonalTrainerDTO personalTrainerDTO)
        throws URISyntaxException {
        log.debug("REST request to save PersonalTrainer : {}", personalTrainerDTO);
        if (personalTrainerDTO.getId() != null) {
            throw new BadRequestAlertException("A new personalTrainer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonalTrainerDTO result = personalTrainerService.save(personalTrainerDTO);
        return ResponseEntity
            .created(new URI("/api/personal-trainers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personal-trainers/:id} : Updates an existing personalTrainer.
     *
     * @param id the id of the personalTrainerDTO to save.
     * @param personalTrainerDTO the personalTrainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalTrainerDTO,
     * or with status {@code 400 (Bad Request)} if the personalTrainerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personalTrainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personal-trainers/{id}")
    public ResponseEntity<PersonalTrainerDTO> updatePersonalTrainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PersonalTrainerDTO personalTrainerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PersonalTrainer : {}, {}", id, personalTrainerDTO);
        if (personalTrainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personalTrainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personalTrainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PersonalTrainerDTO result = personalTrainerService.save(personalTrainerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalTrainerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /personal-trainers/:id} : Partial updates given fields of an existing personalTrainer, field will ignore if it is null
     *
     * @param id the id of the personalTrainerDTO to save.
     * @param personalTrainerDTO the personalTrainerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalTrainerDTO,
     * or with status {@code 400 (Bad Request)} if the personalTrainerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the personalTrainerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the personalTrainerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/personal-trainers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PersonalTrainerDTO> partialUpdatePersonalTrainer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PersonalTrainerDTO personalTrainerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PersonalTrainer partially : {}, {}", id, personalTrainerDTO);
        if (personalTrainerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personalTrainerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personalTrainerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PersonalTrainerDTO> result = personalTrainerService.partialUpdate(personalTrainerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalTrainerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /personal-trainers} : get all the personalTrainers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personalTrainers in body.
     */
    @GetMapping("/personal-trainers")
    public ResponseEntity<List<PersonalTrainerDTO>> getAllPersonalTrainers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of PersonalTrainers");
        Page<PersonalTrainerDTO> page = personalTrainerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /personal-trainers/:id} : get the "id" personalTrainer.
     *
     * @param id the id of the personalTrainerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personalTrainerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personal-trainers/{id}")
    public ResponseEntity<PersonalTrainerDTO> getPersonalTrainer(@PathVariable Long id) {
        log.debug("REST request to get PersonalTrainer : {}", id);
        Optional<PersonalTrainerDTO> personalTrainerDTO = personalTrainerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personalTrainerDTO);
    }

    /**
     * {@code DELETE  /personal-trainers/:id} : delete the "id" personalTrainer.
     *
     * @param id the id of the personalTrainerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personal-trainers/{id}")
    public ResponseEntity<Void> deletePersonalTrainer(@PathVariable Long id) {
        log.debug("REST request to delete PersonalTrainer : {}", id);
        personalTrainerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
