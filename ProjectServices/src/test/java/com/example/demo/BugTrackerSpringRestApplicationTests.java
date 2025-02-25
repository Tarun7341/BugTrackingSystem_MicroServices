package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.demo.client.TicketClient;
import com.project.demo.dto.ProjectRequest;
import com.project.demo.entity.Project;
import com.project.demo.exception.ProjectNotFoundException;
import com.project.demo.repository.ProjectRepository;
import com.project.demo.serviceImpl.ProjectServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BugTrackerSpringRestApplicationTests {

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private TicketClient ticketClient;

	@InjectMocks
	private ProjectServiceImpl projectService;

	private Project project;
	private ProjectRequest projectRequest;

	@BeforeEach
	void setUp() {
		List<Integer> userIds = Arrays.asList(1, 2, 3);
		project = new Project();
		project.setId(1);
		project.setName("Test Project");
		project.setDescription("Test Description");
		project.setUserId(userIds);

		projectRequest = new ProjectRequest(1, "Test Project", "Test Description", userIds);
	}

	@Test
	void testGetAll() {
		List<Project> projects = new ArrayList<>();
		projects.add(project);

		when(projectRepository.findAll()).thenReturn(projects);

		List<Project> result = projectService.getAll();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(project, result.get(0));

		verify(projectRepository, times(1)).findAll();
	}

	@Test
	void testGetOne_Success() throws ProjectNotFoundException {
		when(projectRepository.findById(1)).thenReturn(Optional.of(project));
		when(ticketClient.getTicketsOfProject(1)).thenReturn(new ArrayList<>());

		Project result = projectService.getOne(1);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals("Test Project", result.getName());
		assertEquals("Test Description", result.getDescription());
		assertEquals(1, result.getUserId());

		verify(projectRepository, times(1)).findById(1);
		verify(ticketClient, times(1)).getTicketsOfProject(1);
	}

	@Test
	void testGetOne_ProjectNotFound() {
		when(projectRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ProjectNotFoundException.class, () -> {
			projectService.getOne(1);
		});

		assertEquals("Project with ID 1 not found !! ", exception.getMessage());

		verify(projectRepository, times(1)).findById(1);
		verify(ticketClient, times(0)).getTicketsOfProject(1);
	}

	@Test
	void testAddNew() {
		projectService.addNew(projectRequest);

		verify(projectRepository, times(1)).save(any(Project.class));
	}

	@Test
	void testUpdate_Success() throws ProjectNotFoundException {
		when(projectRepository.findById(1)).thenReturn(Optional.of(project));

		projectService.Update(1, project);

		verify(projectRepository, times(1)).findById(1);
		verify(projectRepository, times(1)).save(project);
	}

	@Test
	void testUpdate_ProjectNotFound() {
		when(projectRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ProjectNotFoundException.class, () -> {
			projectService.Update(1, project);
		});

		assertEquals("Project with ID 1 not found !!", exception.getMessage());

		verify(projectRepository, times(1)).findById(1);
		verify(projectRepository, times(0)).save(project);
	}

	@Test
	void testDelete_Success() throws ProjectNotFoundException {
		when(projectRepository.existsById(1)).thenReturn(true);

		projectService.delete(1);

		verify(projectRepository, times(1)).existsById(1);
		verify(projectRepository, times(1)).deleteById(1);
	}

	@Test
	void testDelete_ProjectNotFound() {
		when(projectRepository.existsById(1)).thenReturn(false);

		Exception exception = assertThrows(ProjectNotFoundException.class, () -> {
			projectService.delete(1);
		});

		assertEquals("Project with ID 1 not found !!", exception.getMessage());

		verify(projectRepository, times(1)).existsById(1);
		verify(projectRepository, times(0)).deleteById(1);
	}

	@Test
	void testGetProjectsByUserId() {
		List<Integer> userIds = Arrays.asList(1, 2, 3);
		List<Project> projects = new ArrayList<>();
		projects.add(project);

		when(projectRepository.findProjectsByUserId(userIds)).thenReturn(projects);

		List<Project> result = projectService.getProjectsByUserId(userIds);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(project, result.get(0));

		verify(projectRepository, times(1)).findProjectsByUserId(userIds);
	}

	@Test
	void testGetTicketsOfProject() {
		List<Project> projects = new ArrayList<>();
		projects.add(project);

		when(projectRepository.findAll()).thenReturn(projects);
		when(ticketClient.getTicketsOfProject(1)).thenReturn(new ArrayList<>());

		List<Project> result = projectService.getTicketsOfProject();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(project, result.get(0));

		verify(projectRepository, times(1)).findAll();
		verify(ticketClient, times(1)).getTicketsOfProject(1);
	}
}
