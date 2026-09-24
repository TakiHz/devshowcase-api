package br.com.palm.devshowcase.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.palm.devshowcase.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByTechnologies_NameIgnoreCase(String technology, Pageable pageable);
    List<Project> findByProfileId(Long profileId);
}
