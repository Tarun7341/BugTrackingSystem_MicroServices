package com.project.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.client.TicketClient;
import com.project.demo.exception.ProjectNotFound;
import com.project.demo.model.Project;
import com.project.demo.repository.ProjectRepository;
import com.project.demo.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectrepository;

	@Autowired
	TicketClient ticketclient;

	@Override
	public List<Project> getAll() {
		List<Project> pjlist = (List<Project>) projectrepository.findAll();

		List<Project> newpjlist = pjlist.stream().map(pjt -> {
			pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
			return pjt;
		}).collect(Collectors.toList());
		return newpjlist;
	}

	@Override
	public void Update(Integer id,Project updateProject) throws ProjectNotFound {
		Optional<Project> optionalProject = projectrepository.findById(id);
		
		if(optionalProject.isPresent()) {
			Project existingProject = optionalProject.get();
			
			if(updateProject.getName()!=null) {
				existingProject.setName(updateProject.getName());
			}
			
			if(updateProject.getDescription()!=null) {
				existingProject.setDescription(updateProject.getDescription());
			}
			projectrepository.save(existingProject);
			
		}else {
			throw new ProjectNotFound("Project with ID " + id + " not found !!");
		}
		
	}

	@Override
	public void addNew(Project project) {
		System.out.print(project);
		projectrepository.save(project);

	}

	@Override
	public Project getOne(Integer id) throws ProjectNotFound {
		Project pjt = projectrepository.findById(id)
				.orElseThrow(() -> new ProjectNotFound("Project with ID " + id + " not found !! "));
		pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
		return pjt;
	}

	@Override
	public void delete(Integer id) throws ProjectNotFound{
		if(projectrepository.existsById(id)) {
			projectrepository.deleteById(id);
		}
		else {
			throw new ProjectNotFound("Project with ID " + id + " not found !!");
		}

	}

	@Override
	public List<Project> getProjectsByUserId(Integer userId) {

		return projectrepository.findProjectsByUserId(userId);
	}

//	public List<Ticket> getTicketsByProject(Integer id){
//		return projectrepository.getTicketsByProject(id);
//	}
//	
//	public List<User> getMembersByProject(Integer id){
//		return projectrepository.getMembersByProject(id);
//	} 
//	

}
