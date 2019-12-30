package leo.org.PPMToolBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leo.org.PPMToolBackend.domain.Project;
import leo.org.PPMToolBackend.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {
		return projectRepository.save(project);
	}
}
