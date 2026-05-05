package org.manuel.portfoliobe.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.helpers.ProjectMapper;
import org.manuel.portfoliobe.repositories.ProjectRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class) // IT: Abilito l'uso di mockito | EN: Enable the usage of mockito
class ProjectServiceImplTest {
    // IT: Con i due mock creo un mapper e repository finti | EN: With the mock I can create fakes mapper and repository
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private FileService fileService;
    // IT: inietto i due mock nel service reale | EN: inject the mocks in the real service
    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setId(1L);
        project.setTitolo("Test titolo");
    }

    @Test
    @DisplayName("Should return the list of all projects")
    void findAllProjectShouldReturnList(){
        // given
        when(projectRepository.findAll()).thenReturn(List.of(project));

        //when
        List<Project> results = projectService.findAllProjects();

        //then
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(project, results.getFirst());

        // Verifica che il repository sia chiamato una sola volta | Verify that the repository is called one time only
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Save a project with an image file")
    void saveProjectShouldReturnSaveProject() throws IOException {
        // given
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setTitolo("Nuovo progetto");
        Project projectToSave = new Project();
        projectToSave.setTitolo("Nuovo progetto");
        Project savedProject = new Project();
        savedProject.setId(10L);
        savedProject.setTitolo("Nuovo progetto");
        savedProject.setImg("nomefile.jpg");

        // IT: finto file (mock) per testare l upload | EN: fake file (mock) for upload testing
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg","image/jpeg", "test image content".getBytes());

        when(projectMapper.mapDtoToEntity(any(ProjectRequestDto.class), any(Project.class))).thenReturn(projectToSave);
        when(fileService.saveFile(any(MultipartFile.class))).thenReturn("nomefile.jpg");
        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);

        // when
        Project result = projectService.saveProject(dto, mockFile);

        // then
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Nuovo progetto", result.getTitolo());
        assertEquals("nomefile.jpg", result.getImg());


        verify(fileService, times(1)).saveFile(any(MultipartFile.class));
        verify(projectMapper, times(1)).mapDtoToEntity(any(ProjectRequestDto.class), any(Project.class));
        verify(fileService).saveFile(any(MultipartFile.class));
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    @DisplayName("findProjectById: should find the requested project ")
    void findProjectByID_found(){
        // given
        Project p = new Project();
        p.setId(10L);
        p.setTitolo("Nuovo progetto");
        when(projectRepository.findById(10L)).thenReturn(Optional.of(p));

        // when
        Project result = projectService.findProjectById(10L);
        // then
        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(projectRepository, times(1)).findById(10L);
    }

    @Test
    @DisplayName("findProjectById –> project not found")
    void findProjectById_NotFound() {
        // given
        when(projectRepository.findById(99L)).thenReturn(Optional.empty());
        // when
        Project result = projectService.findProjectById(99L);
        // then
        assertNull(result);
        verify(projectRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Should delete a project and call the repository")
    void deleteProject(){
        projectService.deleteProject(project);
        verify(projectRepository, times(1)).delete(project);
    }
}