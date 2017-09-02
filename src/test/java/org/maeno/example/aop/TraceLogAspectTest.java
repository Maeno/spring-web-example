package org.maeno.example.aop;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;
import static org.mockito.Mockito.*;

public class TraceLogAspectTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private TraceLogAspect sut;

    private Logger logger;

    @Captor
    private ArgumentCaptor<ILoggingEvent> captor;

    @Test
    public void testAround() throws Throwable {

        final ProceedingJoinPoint joinPoint = createJoinPoint();

        sut = new TraceLogAspect();
        sut.around(joinPoint);

        verify(joinPoint, times(1)).proceed();
    }


    @Test
    public void testDebugLog() throws Throwable {
        final ProceedingJoinPoint joinPoint = createJoinPoint();
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.DEBUG);

        final Appender mockAppender = mock(Appender.class);
        when(mockAppender.getName()).thenReturn("MOCK");
        logger.addAppender((ch.qos.logback.core.Appender<ILoggingEvent>) mockAppender);


        sut = new TraceLogAspect(logger);
        sut.around(joinPoint);

        verify(mockAppender, times(2)).doAppend(captor.capture());

        captor.getAllValues().forEach(iLoggingEvent -> {
            final String message = iLoggingEvent.getFormattedMessage();
            assertThat(message, isOneOf(
                    "Enter: TYPENAME.NAME() with argument[s] = [ARG, 999]",
                    "Exit: TYPENAME.NAME() with result = null"));
        });
    }

    @Test
    public void testInfoLog() throws Throwable {
        final ProceedingJoinPoint joinPoint = createJoinPoint();
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        final Appender mockAppender = mock(Appender.class);
        when(mockAppender.getName()).thenReturn("MOCK");
        logger.addAppender((ch.qos.logback.core.Appender<ILoggingEvent>) mockAppender);


        sut = new TraceLogAspect(logger);
        sut.around(joinPoint);

        verify(mockAppender, never()).doAppend(captor.capture());
    }

    private ProceedingJoinPoint createJoinPoint() {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        MockSignature signature = mock(MockSignature.class);

        when(joinPoint.getSignature()).thenReturn(signature);
        when(joinPoint.getSignature().getDeclaringTypeName()).thenReturn("TYPENAME");
        when(joinPoint.getSignature().getName()).thenReturn("NAME");
        when(joinPoint.getArgs()).thenReturn(new Object[]{"ARG", 999});
        return joinPoint;
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
}
