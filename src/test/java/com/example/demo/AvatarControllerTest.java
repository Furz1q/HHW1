package com.example.demo;

import com.example.demo.model.Avatar;
import com.example.demo.repository.AvatarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvatarControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AvatarRepository avatarRepository;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/avatars";
        avatarRepository.deleteAll();
    }

    @Test
    public void testGetAvatarsPaginated() {
        for (int i = 1; i <= 15; i++) {
            avatarRepository.save(new Avatar(null, "Avatar" + i, "image" + i + ".jpg"));
        }

        ResponseEntity<AvatarPageResponse> response = restTemplate.exchange(
                baseUrl + "?page=0&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AvatarPageResponse>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(10, response.getBody().getContent().size());
    }

    @Test
    public void testGetAvatarsSecondPage() {
        for (int i = 1; i <= 15; i++) {
            avatarRepository.save(new Avatar("Avatar" + i, "image" + i + ".jpg"));
        }

        ResponseEntity<AvatarPageResponse> response = restTemplate.exchange(
                baseUrl + "?page=1&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AvatarPageResponse>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().getContent().size()); // Должно быть 5 записей
    }

    public static class AvatarPageResponse {
        private List<Avatar> content;
        private int totalPages;
        private long totalElements;
        private boolean last;

        public List<Avatar> getContent() { return content; }
        public void setContent(List<Avatar> content) { this.content = content; }

        public int getTotalPages() { return totalPages; }
        public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

        public long getTotalElements() { return totalElements; }
        public void setTotalElements(long totalElements) { this.totalElements = totalElements; }

        public boolean isLast() { return last; }
        public void setLast(boolean last) { this.last = last; }
    }
}
