// package br.com.jonascamargo.placesmanager.logic.services;

// import org.junit.Before;
// import org.junit.Test;
// import org.junit.jupiter.api.Assertions;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.test.context.junit4.SpringRunner;

// import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;

// @RunWith(SpringRunner.class)
// public class PassengerServiceTest {

//     @TestConfiguration
//     static class PassengerServiceTestConfig {
//         @Bean
//         public PassengerService passengerService() {
//             return new PassengerService(); //nao preciso acessar o repository, posso apenas fazer o mock
//         }
//     }


//     @Autowired
//     PassengerService passengerService;

//     @Test
//     public void passengerTestServiceGeneral() {
//         int result = passengerService.numValidatorTest(10);
//         Assertions.assertEquals(10, result);
        
//     }

    

//     // @Test
//     // public void passengerTestServiceIsLegalAge() {
        
//     // }

// }
