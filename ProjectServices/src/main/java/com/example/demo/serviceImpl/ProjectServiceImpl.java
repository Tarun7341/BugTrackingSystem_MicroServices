package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Project;
//import com.example.demo.model.Ticket;
//import com.example.demo.model.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TicketClient;

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
	public void Update(Project project) {
		projectrepository.save(project);
	}

	@Override
	public void addNew(Project project) {
		System.out.print(project);
		projectrepository.save(project);

	}

	@Override
	public Project getOne(Integer id) {
		Project pjt = projectrepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cannot find the project with Id: " + id));
		pjt.setTickets(ticketclient.getTicketsOfProject(pjt.getId()));
		return pjt;
	}

	@Override
	public void delete(Integer id) {
		projectrepository.deleteById(id);

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
