package ss.mod.demo.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.BeforeTransaction;
import ss.mod.demo.MainApplication;
import ss.mod.demo.api.constant.ContURI;
import ss.mod.demo.api.model.request.UserRequest;
import ss.mod.demo.api.model.response.ResponseBody;
import ss.mod.demo.api.model.response.UserResponse;
import ss.mod.demo.dao.repository.UserMasterRepository;
import ss.mod.demo.util.ContainerContextInitializer;
import ss.mod.demo.util.TestHelper;
import ss.mod.demo.util.constant.TestDataUtil;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { MainApplication.class })
@ActiveProfiles("test")
@ContextConfiguration(initializers = { ContainerContextInitializer.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserManagementResourceTest {


    @Autowired
    UserMasterRepository userMasterRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;


    @BeforeAll
    public final void onlyOnce() {

    }

    /**
     * This method execute before every test method execute and before any transaction in database happen and create company.
     */
    @BeforeEach
    @BeforeTransaction
    public final void before() {
        userMasterRepository.deleteAll();
    }

    @Test
    void createUser_200() {
        ResponseEntity<ResponseBody<UserResponse>> responseEntity = this.createUser(TestDataUtil.getUserRequest());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertNotNull(responseEntity.getBody().getBody());
        Assertions.assertNotNull(responseEntity.getBody().getBody().id());
    }


    private ResponseEntity<ResponseBody<UserResponse>> createUser(UserRequest userRequest) {
        return restTemplate.exchange(ContURI.USER, HttpMethod.POST, new HttpEntity<>(userRequest, TestHelper.prepareHeader(true)),
                new ParameterizedTypeReference<>() {
                });
    }

}
