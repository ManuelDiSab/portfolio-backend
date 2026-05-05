package org.manuel.portfoliobe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manuel.portfoliobe.dto.ProjectRequestDto;
import org.manuel.portfoliobe.entities.Project;
import org.manuel.portfoliobe.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockitoBean
    private ProjectService projectService;

    // PUBLIC TEST | GET

    @Test
    @DisplayName("GET api/projects should be public and return 200")
    void getAllProjects() throws Exception {
        when(projectService.findAllProjects()).thenReturn(List.of(new Project()));

        mockMvc.perform(get("/api/projects")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/projects/{1} should be public and return 200")
    void getProjectById_NoAuth() throws Exception {
        Project p = new Project();
        p.setId(1L);
        when(projectService.findProjectById(1L)).thenReturn(p);
        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }


    // SECURITY TEST | PUT, POST, DELETE
    @Test
    @DisplayName("POST /api/projects - Access denied with no login and admin role")
    void createProject_NoAuth_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/api/projects"))
                .andExpect(status().isUnauthorized()); //
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/projects - Should create a new Project | only for admin")
    void createProject_WithAdmin_ShouldReturnCreated() throws Exception {
        ProjectRequestDto dto = new ProjectRequestDto();
        dto.setTitolo("Test");
        dto.setDescrizione("Descrizione lunga almeno 50 caratteri per superare la validazione...");
        dto.setTech(List.of("Java"));
        dto.setGithub("http://github.com");
        MockMultipartFile jsonPart = new MockMultipartFile("project", "", "application/json", objectMapper.writeValueAsBytes(dto));
        MockMultipartFile filePart = new MockMultipartFile("file", "test.jpg", "image/jpeg", "content".getBytes());
        when(projectService.saveProject(any(), any())).thenReturn(new Project());
        mockMvc.perform(multipart("/api/projects")
                        .file(jsonPart)
                        .file(filePart))
                .andExpect(status().isCreated());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("DELETE /api/projects/{id} - Only for the admin")
    void deleteProject_WithAdmin_ShouldReturnNoContent() throws Exception {
        Project p = new Project();
        p.setId(1L);
        when(projectService.findProjectById(1L)).thenReturn(p);
        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/projects - Error 400 if the data are invalid")
    void createProject_InvalidData_ShouldReturnBadRequest() throws Exception {
        ProjectRequestDto invalidDto = new ProjectRequestDto();
        MockMultipartFile jsonPart = new MockMultipartFile("project", "", "application/json", objectMapper.writeValueAsBytes(invalidDto));
        mockMvc.perform(multipart("/api/projects").file(jsonPart))
                .andExpect(status().isBadRequest());
    }
}