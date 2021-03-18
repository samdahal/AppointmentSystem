package com.sd.aptsys.processor

import com.cedarsoftware.servlet.Envelope
import com.cedarsoftware.servlet.JsonCommandServlet
import com.sd.aptsys.annotation.NoAuthenticationRequired
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Configuration

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.lang.annotation.Annotation

@Configuration
class CommandProcessor extends JsonCommandServlet implements ApplicationContextAware {

    List publicMethods = []

    private static ApplicationContext context;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String controller = request.pathInfo.split("/")[1]
        String method = request.pathInfo.split("/")[2]

        /**
         * Check if the controller name exists as bean
         * If it doesn't exist, then there isn't a method we can call...therefore
         * we send the invalid call back to client
         */
        if (!context.beanDefinitionNames.contains(controller))
        {
            sendJsonResponse(request, response, new Envelope("Access denied/ Invalid call", false, null))
            return
        }

        if (authRequired(controller, method))
        {
            String authParam = request.getParameter("auth")

            // 1. Check if the security header exists. If exists process it otherwise return to client error message

            // 2. Check if the token is valid. If valid proceed return otherwise

            // 3. Parse and get all the roles from token

            // 4. Get the roles of user from the database

            // 5. Match if the role in token exists in the database

            // 6 If step 5 is true then user has access. If false, then user shouldn't be able to view the resource

            sendJsonResponse(request, response, new Envelope("Access denied", false, null))
        }
        else
        {
            super.doGet(request, response)
        }


   /*     if (!hasAccess(request)) {
            sendJsonResponse(request, response, new Envelope("Access denied", false, null))
            return
        }*/

    }


    private static boolean authRequired(String controllerName, String methodName)
    {
        boolean hasClassLevelNoAuthAccess = context.getBean(controllerName).class.getAnnotations().find {it.annotationType() == NoAuthenticationRequired.class} != null

        Annotation[] methodAnnotations =  context.getBean(controllerName).class.getDeclaredMethod(methodName).getAnnotations()

        boolean hasAccess = methodAnnotations.find{it.annotationType() == NoAuthenticationRequired.class} != null

       return !hasAccess

    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext
    }

    @Override
    void init() {
        super.init()
    }
}

