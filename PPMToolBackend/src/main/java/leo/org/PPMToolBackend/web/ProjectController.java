package leo.org.PPMToolBackend.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import leo.org.PPMToolBackend.domain.Project;
import leo.org.PPMToolBackend.services.MapErrorValidationService;
import leo.org.PPMToolBackend.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapErrorValidationService validationService;
	@PostMapping("")
	public ResponseEntity<?> createOrUpdateProject(@Valid @RequestBody Project project, BindingResult result){
		ResponseEntity<?> validate = validationService.mapValidationService(result);
		if(validate != null) return validate;
		Project dbProject = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(dbProject, HttpStatus.CREATED);
	}
}
