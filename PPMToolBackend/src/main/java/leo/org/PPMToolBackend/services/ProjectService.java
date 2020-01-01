package leo.org.PPMToolBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leo.org.PPMToolBackend.domain.Project;
import leo.org.PPMToolBackend.exceptions.ProjectIdException;
import leo.org.PPMToolBackend.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project Id: " + project.getProjectIdentifier().toUpperCase()
					+ " has already existed.");
		}
		
	}
	
	public Project findByProjectIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("project id " + projectId.toUpperCase() + " is not found.");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProject(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("Cannot delete project with id: "
					+ projectId + ". The project doesn't exist");
		}
		projectRepository.delete(project);
	}
}
