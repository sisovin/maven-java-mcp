package dev.danvega.mcp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import dev.danvega.mcp.controller.ApiController;
import dev.danvega.mcp.service.MessageService;

@SpringBootTest
@AutoConfigureMockMvc
class McpApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @InjectMocks
    private ApiController apiController;

    @Test
    void contextLoads() {
        assertThat(apiController).isNotNull();
    }

    @Test
    void testGetMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/message"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
    }

    @Test
    void testPostMessage() throws Exception {
        String message = "Test Message";
        when(messageService.processMessage(message)).thenReturn(message);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/message")
                .content(message)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(message));
    }

    @Test
    void testProcessMessage() {
        String message = "Test Message";
        when(messageService.processMessage(message)).thenReturn(message);

        String result = messageService.processMessage(message);
        assertThat(result).isEqualTo(message);
    }
}
