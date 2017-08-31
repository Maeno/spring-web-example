package org.maeno.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TraceLogAspectTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Autowired
    private TraceLogAspect sut;

    @Test
    public void testAround() throws Throwable {

        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        MockSignature signature = mock(MockSignature.class);

        when(joinPoint.getSignature()).thenReturn(signature);
        when(joinPoint.getSignature().getDeclaringTypeName()).thenReturn("TYPENAME");
        when(joinPoint.getSignature().getName()).thenReturn("NAME");
        when(joinPoint.getArgs()).thenReturn(new Object[] {"ARG", 999});

        sut.around(joinPoint);

        verify(joinPoint, times(1)).proceed();
    }

    private class MockSignature implements Signature {

        @Override
        public String toShortString() {
            return null;
        }

        @Override
        public String toLongString() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public int getModifiers() {
            return 0;
        }

        @Override
        public Class getDeclaringType() {
            return null;
        }

        @Override
        public String getDeclaringTypeName() {
            return null;
        }
    }

    @Configuration
    @ComponentScan("org.maeno.example.aop")
    public static class AopConfig {
        // do nothing
    }

}
