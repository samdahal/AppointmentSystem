package com.sd.aptsys.processor

import com.cedarsoftware.servlet.ConfigurationProvider
import com.sd.aptsys.controller.AppointmentController
import groovy.json.JsonBuilder
import org.junit.BeforeClass
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

import javax.servlet.RequestDispatcher
import javax.servlet.ServletConfig
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@SpringBootTest
class CommandProcessorTest {

    /*@Autowired
    protected CommandProcessor commandProcessor*/

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @Mock
    ServletOutputStream servletOut;
    @Mock
    ServletConfig sg;

    @Autowired
    protected ApplicationContext applicationContext

    @Autowired
    protected AppointmentController appointmentController

    @BeforeClass
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void contextLoads() {
       // assert  commandProcessor != null
        assert  appointmentController != null

       // commandProcessor.init()
    }



    @Test
    void testCommandProcessor() {

        CommandProcessor commandProcessor = new CommandProcessor()
        commandProcessor.setApplicationContext(applicationContext)
        commandProcessor.init()
        String.metaClass.encodeURL = {
            URLEncoder.encode(delegate, "UTF-8")
        }

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class)
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class)


        String json = new JsonBuilder([]).toString().encodeURL()


        Mockito.when(httpServletRequest.pathInfo).thenReturn("/appointment/testAuth")
        Mockito.when(httpServletRequest.getParameter('json')).thenReturn(json)

        commandProcessor.doGet(httpServletRequest, httpServletResponse)


    }


}
