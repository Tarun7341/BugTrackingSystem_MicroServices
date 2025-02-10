package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.demo.BugTrackerSpringRestApplication;
import com.project.demo.client.TicketClient;
import com.project.demo.exception.ProjectNotFound;
import com.project.demo.model.Project;
import com.project.demo.repository.ProjectRepository;
import com.project.demo.serviceImpl.ProjectServiceImpl;

@ExtendWith(MockitoExtension.class) // Enables Spring Test support
@SpringBootTest(classes = BugTrackerSpringRestApplication.class) // Loads the full application context
class BugTrackerSpringRestApplicationTests {

	@InjectMocks
	private ProjectServiceImpl projectService;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private TicketClient ticketClient;

	private Project project;

	@BeforeEach
	public void setup() {
		project = Project.build(1, "Issue Tracking System", "A system to track and manage bugs in software projects.",
				2, new ArrayList<>());
	}

	@Test
	void testGetOne() throws ProjectNotFound {
		// Pass case
		when(projectRepository.findById(1)).thenReturn(Optional.of(project));
		when(ticketClient.getTicketsOfProject(1)).thenReturn(new ArrayList<>());

		Project result = projectService.getOne(1);
		assertEquals("Issue Tracking System", result.getName());
		assertEquals("A system to track and manage bugs in software projects.", result.getDescription());
	}

	@Test
	void testGetOneProjectNotFound() {
		// Fail case
		when(projectRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ProjectNotFound.class, () -> projectService.getOne(1));
	}
}